package service;

import beans.HarvestLevel;
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
import beans.Treatment;
import beans.HarvestEvent;
import converter.HarvestLevelsConverter;
import converter.HarvestLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/harvestLevels/")
public class HarvestLevelsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of HarvestLevelsResource */
	public HarvestLevelsResource() {
	}

	/**
	 * Get method for retrieving a collection of HarvestLevels instance in XML format.
	 *
	 * @return an instance of HarvestLevelssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public HarvestLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM HarvestLevel e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new HarvestLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of HarvestLevels using XML as the input format.
	 *
	 * @param data an HarvestLevelsConverter entity that is deserialized from an XML stream
	 * @return an instance of HarvestLevelsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(HarvestLevelConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			HarvestLevel entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getHarvestLevelPK().getExpId() + "," + entity.getHarvestLevelPK().getHa() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of HarvestLevelResource used for entity navigation.
	 *
	 * @return an instance of HarvestLevelResource
	 */
	@Path("{expId},{ha}/")
	public HarvestLevelResource getHarvestLevelsResource(@PathParam("expId") Integer id1, @PathParam("ha") Integer id2) {
		HarvestLevelResource harvestLevelsResource = resourceContext.getResource(HarvestLevelResource.class);
		harvestLevelsResource.setId1(id1);
		harvestLevelsResource.setId2(id2);
		return harvestLevelsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of HarvestLevels instances
	 */
	protected Collection<HarvestLevel> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(HarvestLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		for (HarvestEvent value : entity.getHarvestEventsCollection()) {
			HarvestLevel oldEntity = value.getHarvestLevel();
			value.setHarvestLevel(entity);
			if (oldEntity != null) {
				oldEntity.getHarvestEventsCollection().remove(value);
			}
		}
		for (Treatment value : entity.getTreatmentsCollection()) {
			HarvestLevel oldEntity = value.getHarvestLevels();
			value.setHarvestLevels(entity);
			if (oldEntity != null) {
				oldEntity.getTreatmentsCollection().remove(value);
			}
		}
	}
}
