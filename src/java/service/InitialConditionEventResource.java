package service;

import beans.InitialConditionEvent;
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
import beans.InitialConditionLevelPK;
import beans.InitialConditionLevel;
import converter.InitialConditionEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class InitialConditionEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of InitialConditionEventResource */
	public InitialConditionEventResource() {
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
	 * Get method for retrieving an instance of InitialConditionEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of InitialConditionEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public InitialConditionEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new InitialConditionEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of InitialConditionEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an InitialConditionEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(InitialConditionEventConverter data) {
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
	 * Delete method for deleting an instance of InitialConditionEvents identified by id.
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
	 * Returns an instance of InitialConditionEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of InitialConditionEvents
	 */
	protected InitialConditionEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.InitialConditionEventPK id = new beans.InitialConditionEventPK(id1, id2, id3);
			return (InitialConditionEvent) em.createQuery("SELECT e FROM InitialConditionEvent e where e.initialConditionEventPK = :initialConditionEventPK").setParameter("initialConditionEventPK", id).getSingleResult();
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
	private InitialConditionEvent updateEntity(InitialConditionEvent entity, InitialConditionEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		InitialConditionLevel initialConditionLevels = entity.getInitialConditionLevel();
		InitialConditionLevel initialConditionLevelsNew = newEntity.getInitialConditionLevel();
		entity = em.merge(newEntity);
		if (initialConditionLevels != null && !initialConditionLevels.equals(initialConditionLevelsNew)) {
			initialConditionLevels.getInitialConditionEventsCollection().remove(entity);
		}
		if (initialConditionLevelsNew != null && !initialConditionLevelsNew.equals(initialConditionLevels)) {
			initialConditionLevelsNew.getInitialConditionEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(InitialConditionEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		InitialConditionLevel initialConditionLevels = entity.getInitialConditionLevel();
		if (initialConditionLevels != null) {
			initialConditionLevels.getInitialConditionEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of InitialConditionLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of InitialConditionLevelResource
	 */
	@Path("initialConditionLevels/")
	public InitialConditionLevelResource getInitialConditionLevelsResource() {
		InitialConditionLevelsResourceSub initialConditionLevelsResourceSub = resourceContext.getResource(InitialConditionLevelsResourceSub.class);
		initialConditionLevelsResourceSub.setParent(getEntity());
		return initialConditionLevelsResourceSub;
	}

	public static class InitialConditionLevelsResourceSub extends InitialConditionLevelResource {

		private InitialConditionEvent parent;

		public void setParent(InitialConditionEvent parent) {
			this.parent = parent;
		}

		@Override
		protected InitialConditionLevel getEntity() {
			InitialConditionLevel entity = parent.getInitialConditionLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
