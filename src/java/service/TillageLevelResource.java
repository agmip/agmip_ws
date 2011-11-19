package service;

import beans.TillageLevel;
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
import beans.TillageEvent;
import java.util.Collection;
import converter.TillageLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class TillageLevelResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of TillageLevelResource */
	public TillageLevelResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * Get method for retrieving an instance of TillageLevels identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of TillageLevelsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public TillageLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new TillageLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of TillageLevels identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an TillageLevelsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(TillageLevelConverter data) {
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
	 * Delete method for deleting an instance of TillageLevels identified by id.
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
	 * Returns an instance of TillageLevels identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of TillageLevels
	 */
	protected TillageLevel getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.TillageLevelPK id = new beans.TillageLevelPK(id1, id2);
			return (TillageLevel) em.createQuery("SELECT e FROM TillageLevel e where e.tillageLevelPK = :tillageLevelPK").setParameter("tillageLevelPK", id).getSingleResult();
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
	private TillageLevel updateEntity(TillageLevel entity, TillageLevel newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<TillageEvent> tillageEventsCollection = entity.getTillageEventsCollection();
		Collection<TillageEvent> tillageEventsCollectionNew = newEntity.getTillageEventsCollection();
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
		entity = em.merge(newEntity);
		for (TillageEvent value : tillageEventsCollection) {
			if (!tillageEventsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from tillageEventsCollection"));
			}
		}
		for (TillageEvent value : tillageEventsCollectionNew) {
			if (!tillageEventsCollection.contains(value)) {
				TillageLevel oldEntity = value.getTillageLevel();
				value.setTillageLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getTillageEventsCollection().remove(value);
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
				TillageLevel oldEntity = value.getTillageLevels();
				value.setTillageLevels(entity);
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
	private void deleteEntity(TillageLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getTillageEventsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because tillageEventsCollection is not empty."));
		}
		if (!entity.getTreatmentsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of TillageEventssResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of TillageEventssResource
	 */
	@Path("tillageEventsCollection/")
	public TillageEventsResource getTillageEventsCollectionResource() {
		TillageEventsCollectionResourceSub tillageEventsCollectionResourceSub = resourceContext.getResource(TillageEventsCollectionResourceSub.class);
		tillageEventsCollectionResourceSub.setParent(getEntity());
		return tillageEventsCollectionResourceSub;
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

	public static class TillageEventsCollectionResourceSub extends TillageEventsResource {

		private TillageLevel parent;

		public void setParent(TillageLevel parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<TillageEvent> getEntities(int start, int max, String query) {
			Collection<TillageEvent> result = new java.util.ArrayList<TillageEvent>();
			int index = 0;
			for (TillageEvent e : parent.getTillageEventsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}

	public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

		private TillageLevel parent;

		public void setParent(TillageLevel parent) {
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
