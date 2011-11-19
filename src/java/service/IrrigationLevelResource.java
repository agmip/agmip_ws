package service;

import beans.IrrigationLevel;
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
import beans.IrrigationEvent;
import java.util.Collection;
import converter.IrrigationLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class IrrigationLevelResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of IrrigationLevelResource */
	public IrrigationLevelResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * Get method for retrieving an instance of IrrigationLevels identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of IrrigationLevelsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public IrrigationLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new IrrigationLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of IrrigationLevels identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an IrrigationLevelsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(IrrigationLevelConverter data) {
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
	 * Delete method for deleting an instance of IrrigationLevels identified by id.
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
	 * Returns an instance of IrrigationLevels identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of IrrigationLevels
	 */
	protected IrrigationLevel getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.IrrigationLevelPK id = new beans.IrrigationLevelPK(id1, id2);
			return (IrrigationLevel) em.createQuery("SELECT e FROM IrrigationLevel e where e.irrigationLevelPK = :irrigationLevelPK").setParameter("irrigationLevelPK", id).getSingleResult();
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
	private IrrigationLevel updateEntity(IrrigationLevel entity, IrrigationLevel newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<IrrigationEvent> irrigationEventsCollection = entity.getIrrigationEventsCollection();
		Collection<IrrigationEvent> irrigationEventsCollectionNew = newEntity.getIrrigationEventsCollection();
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
		entity = em.merge(newEntity);
		for (IrrigationEvent value : irrigationEventsCollection) {
			if (!irrigationEventsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from irrigationEventsCollection"));
			}
		}
		for (IrrigationEvent value : irrigationEventsCollectionNew) {
			if (!irrigationEventsCollection.contains(value)) {
				IrrigationLevel oldEntity = value.getIrrigationLevel();
				value.setIrrigationLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getIrrigationEventsCollection().remove(value);
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
				IrrigationLevel oldEntity = value.getIrrigationLevel();
				value.setIrrigationLevel(entity);
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
	private void deleteEntity(IrrigationLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getIrrigationEventsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because irrigationEventsCollection is not empty."));
		}
		if (!entity.getTreatmentsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of IrrigationEventsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of IrrigationEventsResource
	 */
	@Path("irrigationEventsCollection/")
	public IrrigationEventsResource getIrrigationEventsCollectionResource() {
		IrrigationEventsCollectionResourceSub irrigationEventsCollectionResourceSub = resourceContext.getResource(IrrigationEventsCollectionResourceSub.class);
		irrigationEventsCollectionResourceSub.setParent(getEntity());
		return irrigationEventsCollectionResourceSub;
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

	public static class IrrigationEventsCollectionResourceSub extends IrrigationEventsResource {

		private IrrigationLevel parent;

		public void setParent(IrrigationLevel parent) {
			this.parent = parent;
		}

		protected Collection<IrrigationEvent> getEntities(int start, int max, String query) {
			Collection<IrrigationEvent> result = new java.util.ArrayList<IrrigationEvent>();
			int index = 0;
			for (IrrigationEvent e : parent.getIrrigationEventsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}

	public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

		private IrrigationLevel parent;

		public void setParent(IrrigationLevel parent) {
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
