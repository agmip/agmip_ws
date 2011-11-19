package service;

import beans.IrrigationEvent;
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
import beans.IrrigationLevel;
import beans.IrrigationLevelPK;
import converter.IrrigationEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class IrrigationEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of IrrigationEventResource */
	public IrrigationEventResource() {
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
	 * Get method for retrieving an instance of IrrigationEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of IrrigationEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public IrrigationEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new IrrigationEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of IrrigationEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an IrrigationEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(IrrigationEventConverter data) {
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
	 * Delete method for deleting an instance of IrrigationEvents identified by id.
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
	 * Returns an instance of IrrigationEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of IrrigationEvents
	 */
	protected IrrigationEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.IrrigationEventPK id = new beans.IrrigationEventPK(id1, id2, id3);
			return (IrrigationEvent) em.createQuery("SELECT e FROM IrrigationEvent e where e.irrigationEventPK = :irrigationEventPK").setParameter("irrigationEventPK", id).getSingleResult();
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
	private IrrigationEvent updateEntity(IrrigationEvent entity, IrrigationEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		IrrigationLevel irrigationLevels = entity.getIrrigationLevel();
		IrrigationLevel irrigationLevelsNew = newEntity.getIrrigationLevel();
		entity = em.merge(newEntity);
		if (irrigationLevels != null && !irrigationLevels.equals(irrigationLevelsNew)) {
			irrigationLevels.getIrrigationEventsCollection().remove(entity);
		}
		if (irrigationLevelsNew != null && !irrigationLevelsNew.equals(irrigationLevels)) {
			irrigationLevelsNew.getIrrigationEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(IrrigationEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		IrrigationLevel irrigationLevels = entity.getIrrigationLevel();
		if (irrigationLevels != null) {
			irrigationLevels.getIrrigationEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of IrrigationLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of IrrigationLevelResource
	 */
	@Path("irrigationLevels/")
	public IrrigationLevelResource getIrrigationLevelsResource() {
		IrrigationLevelsResourceSub irrigationLevelsResourceSub = resourceContext.getResource(IrrigationLevelsResourceSub.class);
		irrigationLevelsResourceSub.setParent(getEntity());
		return irrigationLevelsResourceSub;
	}

	public static class IrrigationLevelsResourceSub extends IrrigationLevelResource {

		private IrrigationEvent parent;

		public void setParent(IrrigationEvent parent) {
			this.parent = parent;
		}

		@Override
		protected IrrigationLevel getEntity() {
			IrrigationLevel entity = parent.getIrrigationLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
