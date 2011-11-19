package service;

import beans.TillageEvent;
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
import beans.TillageLevelPK;
import beans.TillageLevel;
import converter.TillageEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class TillageEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of TillageEventResource */
	public TillageEventResource() {
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
	 * Get method for retrieving an instance of TillageEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of TillageEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public TillageEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new TillageEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of TillageEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an TillageEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(TillageEventConverter data) {
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
	 * Delete method for deleting an instance of TillageEvents identified by id.
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
	 * Returns an instance of TillageEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of TillageEvents
	 */
	protected TillageEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.TillageEventPK id = new beans.TillageEventPK(id1, id2, id3);
			return (TillageEvent) em.createQuery("SELECT e FROM TillageEvent e where e.tillageEventPK = :tillageEventPK").setParameter("tillageEventPK", id).getSingleResult();
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
	private TillageEvent updateEntity(TillageEvent entity, TillageEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		TillageLevel tillageLevels = entity.getTillageLevel();
		TillageLevel tillageLevelsNew = newEntity.getTillageLevel();
		entity = em.merge(newEntity);
		if (tillageLevels != null && !tillageLevels.equals(tillageLevelsNew)) {
			tillageLevels.getTillageEventsCollection().remove(entity);
		}
		if (tillageLevelsNew != null && !tillageLevelsNew.equals(tillageLevels)) {
			tillageLevelsNew.getTillageEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(TillageEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		TillageLevel tillageLevels = entity.getTillageLevel();
		if (tillageLevels != null) {
			tillageLevels.getTillageEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of TillageLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of TillageLevelResource
	 */
	@Path("tillageLevels/")
	public TillageLevelResource getTillageLevelsResource() {
		TillageLevelsResourceSub tillageLevelsResourceSub = resourceContext.getResource(TillageLevelsResourceSub.class);
		tillageLevelsResourceSub.setParent(getEntity());
		return tillageLevelsResourceSub;
	}

	public static class TillageLevelsResourceSub extends TillageLevelResource {

		private TillageEvent parent;

		public void setParent(TillageEvent parent) {
			this.parent = parent;
		}

		@Override
		protected TillageLevel getEntity() {
			TillageLevel entity = parent.getTillageLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
