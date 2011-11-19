package service;

import beans.OrganicMaterialLevel;
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
import beans.OrganicMaterialEvent;
import java.util.Collection;
import converter.OrganicMaterialLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.OrganicMaterialLevelConverter;

/**
 *
 * @author fonini
 */
public class OrganicMaterialLevelResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of OrganicMaterialLevelResource */
	public OrganicMaterialLevelResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * Get method for retrieving an instance of OrganicMaterialLevels identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of OrganicMaterialLevelsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public OrganicMaterialLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new OrganicMaterialLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of OrganicMaterialLevels identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an OrganicMaterialLevelsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(OrganicMaterialLevelConverter data) {
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
	 * Delete method for deleting an instance of OrganicMaterialLevels identified by id.
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
	 * Returns an instance of OrganicMaterialLevels identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of OrganicMaterialLevels
	 */
	protected OrganicMaterialLevel getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.OrganicMaterialLevelPK id = new beans.OrganicMaterialLevelPK(id1, id2);
			return (OrganicMaterialLevel) em.createQuery("SELECT e FROM OrganicMaterialLevel e where e.organicMaterialLevelPK = :organicMaterialLevelPK").setParameter("organicMaterialLevelPK", id).getSingleResult();
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
	private OrganicMaterialLevel updateEntity(OrganicMaterialLevel entity, OrganicMaterialLevel newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<OrganicMaterialEvent> organicMaterialEventsCollection = entity.getOrganicMaterialEventsCollection();
		Collection<OrganicMaterialEvent> organicMaterialEventsCollectionNew = newEntity.getOrganicMaterialEventsCollection();
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
		entity = em.merge(newEntity);
		for (OrganicMaterialEvent value : organicMaterialEventsCollection) {
			if (!organicMaterialEventsCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from organicMaterialEventsCollection"));
			}
		}
		for (OrganicMaterialEvent value : organicMaterialEventsCollectionNew) {
			if (!organicMaterialEventsCollection.contains(value)) {
				OrganicMaterialLevel oldEntity = value.getOrganicMaterialLevel();
				value.setOrganicMaterialLevel(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getOrganicMaterialEventsCollection().remove(value);
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
				OrganicMaterialLevel oldEntity = value.getOrganicMaterialLevel();
				value.setOrganicMaterialLevel(entity);
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
	private void deleteEntity(OrganicMaterialLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getOrganicMaterialEventsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because organicMaterialEventsCollection is not empty."));
		}
		if (!entity.getTreatmentsCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of OrganicMaterialEventsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of OrganicMaterialEventsResource
	 */
	@Path("organicMaterialEventsCollection/")
	public OrganicMaterialEventsResource getOrganicMaterialEventsCollectionResource() {
		OrganicMaterialEventsCollectionResourceSub organicMaterialEventsCollectionResourceSub = resourceContext.getResource(OrganicMaterialEventsCollectionResourceSub.class);
		organicMaterialEventsCollectionResourceSub.setParent(getEntity());
		return organicMaterialEventsCollectionResourceSub;
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

	public static class OrganicMaterialEventsCollectionResourceSub extends OrganicMaterialEventsResource {

		private OrganicMaterialLevel parent;

		public void setParent(OrganicMaterialLevel parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<OrganicMaterialEvent> getEntities(int start, int max, String query) {
			Collection<OrganicMaterialEvent> result = new java.util.ArrayList<OrganicMaterialEvent>();
			int index = 0;
			for (OrganicMaterialEvent e : parent.getOrganicMaterialEventsCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}

	public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

		private OrganicMaterialLevel parent;

		public void setParent(OrganicMaterialLevel parent) {
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
