package service;

import beans.SoilProfile;
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
import converter.SoilProfileConverter;
import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.Path;
import beans.SoilProfileLayer;
import java.util.Collection;

/**
 *
 * @author fonini
 */
public class SoilProfileResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id;

	/** Creates a new instance of SoilProfileResource */
	public SoilProfileResource() {
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method for retrieving an instance of SoilProfile identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilProfileConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilProfileConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilProfileConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of SoilProfile identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an SoilProfileConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(SoilProfileConverter data) {
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
	 * Delete method for deleting an instance of SoilProfile identified by id.
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
	 * Returns an instance of SoilProfile identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilProfile
	 */
	protected SoilProfile getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			return (SoilProfile) em.createQuery("SELECT e FROM SoilProfile e where e.sid = :sid").setParameter("sid", id).getSingleResult();
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
	private SoilProfile updateEntity(SoilProfile entity, SoilProfile newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		Collection<SoilProfileLayer> soilProfileLayerCollection = entity.getSoilProfileLayerCollection();
		Collection<SoilProfileLayer> soilProfileLayerCollectionNew = newEntity.getSoilProfileLayerCollection();
		entity = em.merge(newEntity);
		for (SoilProfileLayer value : soilProfileLayerCollection) {
			if (!soilProfileLayerCollectionNew.contains(value)) {
				throw new WebApplicationException(new Throwable("Cannot remove items from soilProfileLayerCollection"));
			}
		}
		for (SoilProfileLayer value : soilProfileLayerCollectionNew) {
			if (!soilProfileLayerCollection.contains(value)) {
				SoilProfile oldEntity = value.getSoilProfile();
				value.setSoilProfile(entity);
				if (oldEntity != null && !oldEntity.equals(entity)) {
					oldEntity.getSoilProfileLayerCollection().remove(value);
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
	private void deleteEntity(SoilProfile entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		if (!entity.getSoilProfileLayerCollection().isEmpty()) {
			throw new WebApplicationException(new Throwable("Cannot delete entity because soilProfileLayerCollection is not empty."));
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of SoilProfileLayersResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of SoilProfileLayersResource
	 */
	@Path("soilProfileLayerCollection/")
	public SoilProfileLayersResource getSoilProfileLayerCollectionResource() {
		SoilProfileLayerCollectionResourceSub soilProfileLayerCollectionResourceSub = resourceContext.getResource(SoilProfileLayerCollectionResourceSub.class);
		soilProfileLayerCollectionResourceSub.setParent(getEntity());
		return soilProfileLayerCollectionResourceSub;
	}

	public static class SoilProfileLayerCollectionResourceSub extends SoilProfileLayersResource {

		private SoilProfile parent;

		public void setParent(SoilProfile parent) {
			this.parent = parent;
		}

		@Override
		protected Collection<SoilProfileLayer> getEntities(int start, int max, String query) {
			Collection<SoilProfileLayer> result = new java.util.ArrayList<SoilProfileLayer>();
			int index = 0;
			for (SoilProfileLayer e : parent.getSoilProfileLayerCollection()) {
				if (index >= start && (index - start) < max) {
					result.add(e);
				}
				index++;
			}
			return result;
		}
	}
}
