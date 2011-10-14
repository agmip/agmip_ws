/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.SoilProfileLayer;
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
import beans.SoilProfile;
import converter.SoilProfileLayersConverter;
import converter.SoilProfileLayerConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/soilProfileLayers/")
public class SoilProfileLayersResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of SoilProfileLayersResource */
	public SoilProfileLayersResource() {
	}

	/**
	 * Get method for retrieving a collection of SoilProfileLayer instance in XML format.
	 *
	 * @return an instance of SoilProfileLayersConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilProfileLayersConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM SoilProfileLayer e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilProfileLayersConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of SoilProfileLayer using XML as the input format.
	 *
	 * @param data an SoilProfileLayerConverter entity that is deserialized from an XML stream
	 * @return an instance of SoilProfileLayerConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(SoilProfileLayerConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			SoilProfileLayer entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getSoilProfileLayerPK().getSid() + "," + entity.getSoilProfileLayerPK().getSoilId() + "," + entity.getSoilProfileLayerPK().getSoilFileId() + "," + entity.getSoilProfileLayerPK().getSllb() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of SoilProfileLayerResource used for entity navigation.
	 *
	 * @return an instance of SoilProfileLayerResource
	 */
	@Path("{sid},{soilId},{soilFileId},{sllb}/")
	public SoilProfileLayerResource getSoilProfileLayerResource(@PathParam("sid") Integer id1, @PathParam("soilId") String id2, @PathParam("soilFileId") String id3, @PathParam("sllb") Integer id4) {
		SoilProfileLayerResource soilProfileLayerResource = resourceContext.getResource(SoilProfileLayerResource.class);
		soilProfileLayerResource.setId1(id1);
		soilProfileLayerResource.setId2(id2);
		soilProfileLayerResource.setId3(id3);
		soilProfileLayerResource.setId4(id4);
		return soilProfileLayerResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of SoilProfileLayer instances
	 */
	protected Collection<SoilProfileLayer> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(SoilProfileLayer entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		SoilProfile soilProfile = entity.getSoilProfile();
		if (soilProfile != null) {
			soilProfile.getSoilProfileLayerCollection().add(entity);
		}
	}
}
