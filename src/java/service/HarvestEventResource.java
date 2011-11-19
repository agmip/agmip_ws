package service;

import beans.HarvestEvent;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import beans.HarvestLevelPK;
import beans.HarvestLevel;
import converter.HarvestEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class HarvestEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of HarvestEventResource */
	public HarvestEventResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public void setId3(Integer id3) {
		this.id3 = id3;
	}

	/**
	 * Get method for retrieving an instance of HarvestEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of HarvestEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public HarvestEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new HarvestEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of HarvestEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an HarvestEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(HarvestEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			updateEntity(getEntity(), data.resolveEntity(em));
			persistenceSvc.commitTx();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Delete method for deleting an instance of HarvestEvents identified by id.
	 *
	 * @param id identifier for the entity
	 */
	@DELETE
	public void delete() {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			deleteEntity(getEntity());
			persistenceSvc.commitTx();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns an instance of HarvestEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of HarvestEvents
	 */
	protected HarvestEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.HarvestEventPK id = new beans.HarvestEventPK(id1, id2, id3);
			return (HarvestEvent) em.createQuery("SELECT e FROM HarvestEvent e where e.harvestEventPK = :harvestEventPK").setParameter("harvestEventPK", id).getSingleResult();
		}
		catch (NoResultException ex) {
			throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
		}
	}

	/**
	 * Updates entity using data from newEntity.
	 *
	 * @param entity the entity to update
	 * @param newEntity the entity containing the new data
	 * @return the updated entity
	 */
	private HarvestEvent updateEntity(HarvestEvent entity, HarvestEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		HarvestLevel harvestLevels = entity.getHarvestLevel();
		HarvestLevel harvestLevelsNew = newEntity.getHarvestLevel();
		entity = em.merge(newEntity);
		if (harvestLevels != null && !harvestLevels.equals(harvestLevelsNew)) {
			harvestLevels.getHarvestEventsCollection().remove(entity);
		}
		if (harvestLevelsNew != null && !harvestLevelsNew.equals(harvestLevels)) {
			harvestLevelsNew.getHarvestEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(HarvestEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		HarvestLevel harvestLevels = entity.getHarvestLevel();
		if (harvestLevels != null) {
			harvestLevels.getHarvestEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of HarvestLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of HarvestLevelResource
	 */
	@Path("harvestLevels/")
	public HarvestLevelResource getHarvestLevelsResource() {
		HarvestLevelsResourceSub harvestLevelsResourceSub = resourceContext.getResource(HarvestLevelsResourceSub.class);
		harvestLevelsResourceSub.setParent(getEntity());
		return harvestLevelsResourceSub;
	}

	public static class HarvestLevelsResourceSub extends HarvestLevelResource {

		private HarvestEvent parent;

		public void setParent(HarvestEvent parent) {
			this.parent = parent;
		}

		@Override
		protected HarvestLevel getEntity() {
			HarvestLevel entity = parent.getHarvestLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
