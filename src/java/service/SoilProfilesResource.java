package service;

import beans.SoilProfile;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.persistence.EntityManager;
import converter.SoilProfilesConverter;
import converter.SoilProfileConverter;
import com.sun.jersey.api.core.ResourceContext;
import beans.SoilProfileLayer;

/**
 *
 * @author fonini
 */
@Path("/soilProfiles/")
public class SoilProfilesResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of SoilProfilesResource */
	public SoilProfilesResource() {
	}

	/**
	 * Get method for retrieving a collection of SoilProfile instance in XML format.
	 *
	 * @return an instance of SoilProfilesConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilProfilesConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM SoilProfile e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilProfilesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of SoilProfile using XML as the input format.
	 *
	 * @param data an SoilProfileConverter entity that is deserialized from an XML stream
	 * @return an instance of SoilProfileConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(SoilProfileConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			SoilProfile entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getSid() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of SoilProfileResource used for entity navigation.
	 *
	 * @return an instance of SoilProfileResource
	 */
	@Path("{sid}/")
	public SoilProfileResource getSoilProfileResource(@PathParam("sid") Integer id) {
		SoilProfileResource soilProfileResource = resourceContext.getResource(SoilProfileResource.class);
		soilProfileResource.setId(id);
		return soilProfileResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of SoilProfile instances
	 */
	protected Collection<SoilProfile> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(SoilProfile entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		for (SoilProfileLayer value : entity.getSoilProfileLayerCollection()) {
			SoilProfile oldEntity = value.getSoilProfile();
			value.setSoilProfile(entity);
			if (oldEntity != null) {
				oldEntity.getSoilProfileLayerCollection().remove(value);
			}
		}
	}
}
