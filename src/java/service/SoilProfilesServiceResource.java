package service;

import beans.SoilProfile;
import beans.SoilProfileLayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author fonini
 */
@Path("/soilProfilesService")
public class SoilProfilesServiceResource {

	@Context
	private UriInfo context;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of SoilProfilesServiceResource */
	public SoilProfilesServiceResource() {
	}

	/**
	 * Retrieves representation of an instance of service.SoilProfilesServiceResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces("application/xml")
	public String getXml() {
		//TODO return proper representation object
		throw new UnsupportedOperationException();
	}

	/**
	 * POST method for creating an instance of WeatherDailyServiceResource
	 * @param content representation for the new resource
	 * @return an HTTP response with content of the created resource
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response postJson(String content) {
		Gson json = new Gson();
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();

			Type collectionType = new TypeToken<Collection<SoilProfile>>() {
			}.getType();
			Collection<SoilProfile> collection = json.fromJson(content, collectionType);

			for (SoilProfile profile : collection) {
				Integer max = (Integer) em.createQuery("select Max(s.sid) from SoilProfile s").getSingleResult();
				if (max == null){
					max = 0;
				}
				profile.setSid(++max);

				Collection <SoilProfileLayer> newLayers = new ArrayList<SoilProfileLayer>();
				for (SoilProfileLayer layer : profile.getSoilProfileLayerCollection()){
					layer.getSoilProfileLayerPK().setSid(max);
					newLayers.add(layer);
				}

				profile.setSoilProfileLayerCollection(newLayers);
				em.persist(profile);
			}

			persistenceSvc.commitTx();

			return Response.created(uriInfo.getAbsolutePath()).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	/**
	 * Sub-resource locator method for {id}
	 */
	@Path("{id}")
	public SoilProfileServiceResource getSoilProfileServiceResource(@PathParam("id") String id) {
		return SoilProfileServiceResource.getInstance(id);
	}
}
