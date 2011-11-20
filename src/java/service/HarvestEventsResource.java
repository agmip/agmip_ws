package service;

import beans.HarvestEvent;
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
import beans.HarvestLevel;
import converter.HarvestEventsConverter;
import converter.HarvestEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/harvestEvents/")
public class HarvestEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of HarvestEventsResource */
	public HarvestEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of HarvestEvents instance in XML format.
	 *
	 * @return an instance of HarvestEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public HarvestEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM HarvestEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new HarvestEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of HarvestEvents using XML as the input format.
	 *
	 * @param data an HarvestEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of HarvestEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(HarvestEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			HarvestEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getHarvestEventPK().getExpId() + "," + entity.getHarvestEventPK().getHa() + "," + entity.getHarvestEventPK().getHaOpsNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of HarvestEventResource used for entity navigation.
	 *
	 * @return an instance of HarvestEventResource
	 */
	@Path("{expId},{ha},{haOpsNo}/")
	public HarvestEventResource getHarvestEventsResource(@PathParam("expId") Integer id1, @PathParam("ha") Integer id2, @PathParam("haOpsNo") Integer id3) {
		HarvestEventResource harvestEventsResource = resourceContext.getResource(HarvestEventResource.class);
		harvestEventsResource.setId1(id1);
		harvestEventsResource.setId2(id2);
		harvestEventsResource.setId3(id3);
		return harvestEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of HarvestEvents instances
	 */
	protected Collection<HarvestEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(HarvestEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		HarvestLevel harvestLevels = entity.getHarvestLevel();
		if (harvestLevels != null) {
			harvestLevels.getHarvestEventsCollection().add(entity);
		}
	}
}
