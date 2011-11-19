package service;

import beans.SoilAnalysesLevel;
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
import beans.Treatment;
import beans.SoilAnalysesEvent;
import java.util.Collection;
import converter.SoilAnalysesLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.SoilAnalysesLevelConverter;

/**
 *
 * @author fonini
 */
public class SoilAnalysesLevelResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of SoilAnalysesLevelResource */
	public SoilAnalysesLevelResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * Get method for retrieving an instance of SoilAnalysesLevels identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilAnalysesLevelsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilAnalysesLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilAnalysesLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of SoilAnalysesLevels identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an SoilAnalysesLevelsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(SoilAnalysesLevelConverter data) {
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
	 * Delete method for deleting an instance of SoilAnalysesLevels identified by id.
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
	 * Returns an instance of SoilAnalysesLevels identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilAnalysesLevels
	 */
	protected SoilAnalysesLevel getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.SoilAnalysesLevelPK id = new beans.SoilAnalysesLevelPK(id1, id2);
			return (SoilAnalysesLevel) em.createQuery("SELECT e FROM SoilAnalysesLevel e where e.soilAnalysesLevelPK = :soilAnalysesLevelsPK").setParameter("soilAnalysesLevelsPK", id).getSingleResult();
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
	private SoilAnalysesLevel updateEntity(SoilAnalysesLevel entity, SoilAnalysesLevel newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<SoilAnalysesEvent> soilAnalysesEventsCollection = entity.getSoilAnalysesEventsCollection();
		Collection<SoilAnalysesEvent> soilAnalysesEventsCollectionNew = newEntity.getSoilAnalysesEventsCollection();
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
		entity = em.merge(newEntity);
		for (SoilAnalysesEvent value : soilAnalysesEventsCollection) {
			if (!soilAnalysesEventsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from soilAnalysesEventsCollection"));
			}
		}
		for (SoilAnalysesEvent value : soilAnalysesEventsCollectionNew) {
			if (!soilAnalysesEventsCollection.contains(value)) {
				SoilAnalysesLevel oldEntity = value.getSoilAnalysesLevel();
				value.setSoilAnalysesLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getSoilAnalysesEventsCollection().remove(value);
				}
			}
		}
		for (Treatment value : treatmentsCollection) {
			if (!treatmentsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from treatmentsCollection"));
			}
		}
		for (Treatment value : treatmentsCollectionNew) {
			if (!treatmentsCollection.contains(value)) {
				SoilAnalysesLevel oldEntity = value.getSoilAnalysesLevel();
				value.setSoilAnalysesLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getTreatmentsCollection().remove(value);
				}
			}
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(SoilAnalysesLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getSoilAnalysesEventsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because soilAnalysesEventsCollection is not empty."));
		}
		if (!entity.getTreatmentsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of SoilAnalysesEventssResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of SoilAnalysesEventssResource
	 */
	@Path("soilAnalysesEventsCollection/")
	public SoilAnalysesEventsResource getSoilAnalysesEventsCollectionResource() {
		SoilAnalysesEventsCollectionResourceSub soilAnalysesEventsCollectionResourceSub = resourceContext.getResource(SoilAnalysesEventsCollectionResourceSub.class);
		soilAnalysesEventsCollectionResourceSub.setParent(getEntity());
		return soilAnalysesEventsCollectionResourceSub;
	}

	/**
	 * Returns a dynamic instance of TreatmentssResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of TreatmentssResource
	 */
	@Path("treatmentsCollection/")
	public TreatmentsResource getTreatmentsCollectionResource() {
		TreatmentsCollectionResourceSub treatmentsCollectionResourceSub = resourceContext.getResource(TreatmentsCollectionResourceSub.class);
		treatmentsCollectionResourceSub.setParent(getEntity());
		return treatmentsCollectionResourceSub;
	}

	public static class SoilAnalysesEventsCollectionResourceSub extends SoilAnalysesEventsResource {

		private SoilAnalysesLevel parent;

		public void setParent(SoilAnalysesLevel parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<SoilAnalysesEvent> getEntities(int start, int max, String query) {
			Collection<SoilAnalysesEvent> result = new java.util.ArrayList<SoilAnalysesEvent>();
			int index = 0;
			for (SoilAnalysesEvent e : parent.getSoilAnalysesEventsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}

	public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

		private SoilAnalysesLevel parent;

		public void setParent(SoilAnalysesLevel parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<Treatment> getEntities(int start, int max, String query) {
			Collection<Treatment> result = new java.util.ArrayList<Treatment>();
			int index = 0;
			for (Treatment e : parent.getTreatmentsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}
}
