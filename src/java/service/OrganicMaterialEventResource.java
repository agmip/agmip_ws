package service;

import beans.OrganicMaterialEvent;
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
import beans.OrganicMaterialLevel;
import converter.OrganicMaterialEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class OrganicMaterialEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of OrganicMaterialEventResource */
	public OrganicMaterialEventResource() {
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
	 * Get method for retrieving an instance of OrganicMaterialEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of OrganicMaterialEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public OrganicMaterialEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new OrganicMaterialEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of OrganicMaterialEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an OrganicMaterialEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(OrganicMaterialEventConverter data) {
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
	 * Delete method for deleting an instance of OrganicMaterialEvents identified by id.
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
	 * Returns an instance of OrganicMaterialEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of OrganicMaterialEvents
	 */
	protected OrganicMaterialEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.OrganicMaterialEventPK id = new beans.OrganicMaterialEventPK(id1, id2, id3);
			return (OrganicMaterialEvent) em.createQuery("SELECT e FROM OrganicMaterialEvent e where e.organicMaterialEventPK = :organicMaterialEventPK").setParameter("organicMaterialEventPK", id).getSingleResult();
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
	private OrganicMaterialEvent updateEntity(OrganicMaterialEvent entity, OrganicMaterialEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		OrganicMaterialLevel organicMaterialLevels = entity.getOrganicMaterialLevel();
		OrganicMaterialLevel organicMaterialLevelsNew = newEntity.getOrganicMaterialLevel();
		entity = em.merge(newEntity);
		if (organicMaterialLevels != null && !organicMaterialLevels.equals(organicMaterialLevelsNew)) {
			organicMaterialLevels.getOrganicMaterialEventsCollection().remove(entity);
		}
		if (organicMaterialLevelsNew != null && !organicMaterialLevelsNew.equals(organicMaterialLevels)) {
			organicMaterialLevelsNew.getOrganicMaterialEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(OrganicMaterialEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		OrganicMaterialLevel organicMaterialLevels = entity.getOrganicMaterialLevel();
		if (organicMaterialLevels != null) {
			organicMaterialLevels.getOrganicMaterialEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of OrganicMaterialLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of OrganicMaterialLevelResource
	 */
	@Path("organicMaterialLevels/")
	public OrganicMaterialLevelResource getOrganicMaterialLevelsResource() {
		OrganicMaterialLevelsResourceSub organicMaterialLevelsResourceSub = resourceContext.getResource(OrganicMaterialLevelsResourceSub.class);
		organicMaterialLevelsResourceSub.setParent(getEntity());
		return organicMaterialLevelsResourceSub;
	}

	public static class OrganicMaterialLevelsResourceSub extends OrganicMaterialLevelResource {

		private OrganicMaterialEvent parent;

		public void setParent(OrganicMaterialEvent parent) {
			this.parent = parent;
		}

		@Override
		protected OrganicMaterialLevel getEntity() {
			OrganicMaterialLevel entity = parent.getOrganicMaterialLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
