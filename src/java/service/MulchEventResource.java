package service;

import beans.MulchEvent;
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
import beans.MulchLevel;
import beans.MulchLevelPK;
import converter.MulchEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class MulchEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of MulchEventResource */
	public MulchEventResource() {
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
	 * Get method for retrieving an instance of MulchEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of MulchEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public MulchEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new MulchEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of MulchEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an MulchEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(MulchEventConverter data) {
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
	 * Delete method for deleting an instance of MulchEvents identified by id.
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
	 * Returns an instance of MulchEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of MulchEvents
	 */
	protected MulchEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.MulchEventPK id = new beans.MulchEventPK(id1, id2, id3);
			return (MulchEvent) em.createQuery("SELECT e FROM MulchEvent e where e.mulchEventPK = :mulchEventPK").setParameter("mulchEventPK", id).getSingleResult();
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
	private MulchEvent updateEntity(MulchEvent entity, MulchEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		MulchLevel mulchLevels = entity.getMulchLevel();
		MulchLevel mulchLevelsNew = newEntity.getMulchLevel();
		entity = em.merge(newEntity);
		if (mulchLevels != null && !mulchLevels.equals(mulchLevelsNew)) {
			mulchLevels.getMulchEventsCollection().remove(entity);
		}
		if (mulchLevelsNew != null && !mulchLevelsNew.equals(mulchLevels)) {
			mulchLevelsNew.getMulchEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(MulchEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		MulchLevel mulchLevels = entity.getMulchLevel();
		if (mulchLevels != null) {
			mulchLevels.getMulchEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of MulchLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of MulchLevelResource
	 */
	@Path("mulchLevels/")
	public MulchLevelResource getMulchLevelsResource() {
		MulchLevelsResourceSub mulchLevelsResourceSub = resourceContext.getResource(MulchLevelsResourceSub.class);
		mulchLevelsResourceSub.setParent(getEntity());
		return mulchLevelsResourceSub;
	}

	public static class MulchLevelsResourceSub extends MulchLevelResource {

		private MulchEvent parent;

		public void setParent(MulchEvent parent) {
			this.parent = parent;
		}

		@Override
		protected MulchLevel getEntity() {
			MulchLevel entity = parent.getMulchLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
