package service;

import beans.SoilAnalysesEvent;
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
import beans.SoilAnalysesLevel;
import beans.SoilAnalysesLevelPK;
import converter.SoilAnalysesEventsConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.SoilAnalysesEventConverter;

/**
 *
 * @author fonini
 */
public class SoilAnalysesEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of SoilAnalysesEventResource */
	public SoilAnalysesEventResource() {
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
	 * Get method for retrieving an instance of SoilAnalysesEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilAnalysesEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilAnalysesEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilAnalysesEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of SoilAnalysesEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an SoilAnalysesEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(SoilAnalysesEventConverter data) {
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
	 * Delete method for deleting an instance of SoilAnalysesEvents identified by id.
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
	 * Returns an instance of SoilAnalysesEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilAnalysesEvents
	 */
	protected SoilAnalysesEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.SoilAnalysesEventPK id = new beans.SoilAnalysesEventPK(id1, id2, id3);
			return (SoilAnalysesEvent) em.createQuery("SELECT e FROM SoilAnalysesEvent e where e.soilAnalysesEventPK = :soilAnalysesEventPK").setParameter("soilAnalysesEventPK", id).getSingleResult();
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
	private SoilAnalysesEvent updateEntity(SoilAnalysesEvent entity, SoilAnalysesEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		SoilAnalysesLevel soilAnalysesLevels = entity.getSoilAnalysesLevel();
		SoilAnalysesLevel soilAnalysesLevelsNew = newEntity.getSoilAnalysesLevel();
		entity = em.merge(newEntity);
		if (soilAnalysesLevels != null && !soilAnalysesLevels.equals(soilAnalysesLevelsNew)) {
			soilAnalysesLevels.getSoilAnalysesEventsCollection().remove(entity);
		}
		if (soilAnalysesLevelsNew != null && !soilAnalysesLevelsNew.equals(soilAnalysesLevels)) {
			soilAnalysesLevelsNew.getSoilAnalysesEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(SoilAnalysesEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		SoilAnalysesLevel soilAnalysesLevels = entity.getSoilAnalysesLevel();
		if (soilAnalysesLevels != null) {
			soilAnalysesLevels.getSoilAnalysesEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of SoilAnalysesLevelResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of SoilAnalysesLevelResource
	 */
	@Path("soilAnalysesLevels/")
	public SoilAnalysesLevelResource getSoilAnalysesLevelsResource() {
		SoilAnalysesLevelsResourceSub soilAnalysesLevelsResourceSub = resourceContext.getResource(SoilAnalysesLevelsResourceSub.class);
		soilAnalysesLevelsResourceSub.setParent(getEntity());
		return soilAnalysesLevelsResourceSub;
	}

	public static class SoilAnalysesLevelsResourceSub extends SoilAnalysesLevelResource {

		private SoilAnalysesEvent parent;

		public void setParent(SoilAnalysesEvent parent) {
			this.parent = parent;
		}

		protected SoilAnalysesLevel getEntity() {
			SoilAnalysesLevel entity = parent.getSoilAnalysesLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
