package service;

import beans.MulchLevel;
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
import java.util.Collection;
import beans.MulchEvent;
import converter.MulchLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class MulchLevelResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of MulchLevelResource */
	public MulchLevelResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * Get method for retrieving an instance of MulchLevels identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of MulchLevelsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public MulchLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new MulchLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of MulchLevels identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an MulchLevelsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(MulchLevelConverter data) {
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
	 * Delete method for deleting an instance of MulchLevels identified by id.
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
	 * Returns an instance of MulchLevels identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of MulchLevels
	 */
	protected MulchLevel getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.MulchLevelPK id = new beans.MulchLevelPK(id1, id2);
			return (MulchLevel) em.createQuery("SELECT e FROM MulchLevel e where e.mulchLevelPK = :mulchLevelPK").setParameter("mulchLevelPK", id).getSingleResult();
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
	private MulchLevel updateEntity(MulchLevel entity, MulchLevel newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<MulchEvent> mulchEventsCollection = entity.getMulchEventsCollection();
		Collection<MulchEvent> mulchEventsCollectionNew = newEntity.getMulchEventsCollection();
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
		entity = em.merge(newEntity);
		for (MulchEvent value : mulchEventsCollection) {
			if (!mulchEventsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from mulchEventsCollection"));
			}
		}
		for (MulchEvent value : mulchEventsCollectionNew) {
			if (!mulchEventsCollection.contains(value)) {
				MulchLevel oldEntity = value.getMulchLevel();
				value.setMulchLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getMulchEventsCollection().remove(value);
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
				MulchLevel oldEntity = value.getMulchLevels();
				value.setMulchLevels(entity);
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
	private void deleteEntity(MulchLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getMulchEventsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because mulchEventsCollection is not empty."));
		}
		if (!entity.getTreatmentsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of MulchEventsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of MulchEventsResource
	 */
	@Path("mulchEventsCollection/")
	public MulchEventsResource getMulchEventsCollectionResource() {
		MulchEventsCollectionResourceSub mulchEventsCollectionResourceSub = resourceContext.getResource(MulchEventsCollectionResourceSub.class);
		mulchEventsCollectionResourceSub.setParent(getEntity());
		return mulchEventsCollectionResourceSub;
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

	public static class MulchEventsCollectionResourceSub extends MulchEventsResource {

		private MulchLevel parent;

		public void setParent(MulchLevel parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<MulchEvent> getEntities(int start, int max, String query) {
			Collection<MulchEvent> result = new java.util.ArrayList<MulchEvent>();
			int index = 0;
			for (MulchEvent e : parent.getMulchEventsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}

	public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

		private MulchLevel parent;

		public void setParent(MulchLevel parent) {
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
