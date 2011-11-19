package service;

import beans.EnvironModifEvent;
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
import beans.EnvironModifLevel;
import beans.EnvironModifLevelPK;
import converter.EnvironModifEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class EnvironModifEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of EnvironModifEventResource */
	public EnvironModifEventResource() {
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
	 * Get method for retrieving an instance of EnvironModifEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of EnvironModifEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public EnvironModifEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new EnvironModifEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of EnvironModifEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an EnvironModifEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(EnvironModifEventConverter data) {
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
	 * Delete method for deleting an instance of EnvironModifEvents identified by id.
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
	 * Returns an instance of EnvironModifEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of EnvironModifEvents
	 */
	protected EnvironModifEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.EnvironModifEventPK id = new beans.EnvironModifEventPK(id1, id2, id3);
			return (EnvironModifEvent) em.createQuery("SELECT e FROM EnvironModifEvent e where e.environModifEventPK = :environModifEventPK").setParameter("environModifEventPK", id).getSingleResult();
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
	private EnvironModifEvent updateEntity(EnvironModifEvent entity, EnvironModifEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		EnvironModifLevel environModifLevels = entity.getEnvironModifLevel();
		EnvironModifLevel environModifLevelsNew = newEntity.getEnvironModifLevel();
		entity = em.merge(newEntity);
		if (environModifLevels != null && !environModifLevels.equals(environModifLevelsNew)) {
			environModifLevels.getEnvironModifEventsCollection().remove(entity);
		}
		if (environModifLevelsNew != null && !environModifLevelsNew.equals(environModifLevels)) {
			environModifLevelsNew.getEnvironModifEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(EnvironModifEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		EnvironModifLevel environModifLevels = entity.getEnvironModifLevel();
		if (environModifLevels != null) {
			environModifLevels.getEnvironModifEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of EnvironModifLevelsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of EnvironModifLevelsResource
	 */
	@Path("environModifLevels/")
	public EnvironModifLevelsResource getEnvironModifLevelsResource() {
		EnvironModifLevelsResourceSub environModifLevelsResourceSub = resourceContext.getResource(EnvironModifLevelsResourceSub.class);
		environModifLevelsResourceSub.setParent(getEntity());
		return environModifLevelsResourceSub;
	}

	public static class EnvironModifLevelsResourceSub extends EnvironModifLevelsResource {

		private EnvironModifEvent parent;

		public void setParent(EnvironModifEvent parent) {
			this.parent = parent;
		}

		protected EnvironModifLevel getEntity() {
			EnvironModifLevel entity = parent.getEnvironModifLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
