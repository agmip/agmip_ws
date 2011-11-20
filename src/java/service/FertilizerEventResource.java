package service;

import beans.FertilizerEvent;
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
import beans.FertilizerLevel;
import converter.FertilizerEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class FertilizerEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of FertilizerEventResource */
	public FertilizerEventResource() {
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
	 * Get method for retrieving an instance of FertilizerEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of FertilizerEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public FertilizerEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new FertilizerEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of FertilizerEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an FertilizerEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(FertilizerEventConverter data) {
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
	 * Delete method for deleting an instance of FertilizerEvents identified by id.
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
	 * Returns an instance of FertilizerEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of FertilizerEvents
	 */
	protected FertilizerEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.FertilizerEventPK id = new beans.FertilizerEventPK(id1, id2, id3);
			return (FertilizerEvent) em.createQuery("SELECT e FROM FertilizerEvent e where e.fertilizerEventPK = :fertilizerEventPK").setParameter("fertilizerEventPK", id).getSingleResult();
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
	private FertilizerEvent updateEntity(FertilizerEvent entity, FertilizerEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		FertilizerLevel fertilizerLevels = entity.getFertilizerLevel();
		FertilizerLevel fertilizerLevelsNew = newEntity.getFertilizerLevel();
		entity = em.merge(newEntity);
		if (fertilizerLevels != null && !fertilizerLevels.equals(fertilizerLevelsNew)) {
			fertilizerLevels.getFertilizerEventsCollection().remove(entity);
		}
		if (fertilizerLevelsNew != null && !fertilizerLevelsNew.equals(fertilizerLevels)) {
			fertilizerLevelsNew.getFertilizerEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(FertilizerEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		FertilizerLevel fertilizerLevels = entity.getFertilizerLevel();
		if (fertilizerLevels != null) {
			fertilizerLevels.getFertilizerEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of FertilizerLevelsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of FertilizerLevelsResource
	 */
	@Path("fertilizerLevels/")
	public FertilizerLevelsResource getFertilizerLevelsResource() {
		FertilizerLevelsResourceSub fertilizerLevelsResourceSub = resourceContext.getResource(FertilizerLevelsResourceSub.class);
		fertilizerLevelsResourceSub.setParent(getEntity());
		return fertilizerLevelsResourceSub;
	}

	public static class FertilizerLevelsResourceSub extends FertilizerLevelsResource {

		private FertilizerEvent parent;

		public void setParent(FertilizerEvent parent) {
			this.parent = parent;
		}

		protected FertilizerLevel getEntity() {
			FertilizerLevel entity = parent.getFertilizerLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
