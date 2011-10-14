package service;

import beans.SoilProfileLayer;
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
import beans.SoilProfile;
import converter.SoilProfileLayerConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class SoilProfileLayerResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id4;
	protected String id3;
	protected String id2;
	protected Integer id1;

	/** Creates a new instance of SoilProfileLayerResource */
	public SoilProfileLayerResource() {
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public void setId4(Integer id4) {
		this.id4 = id4;
	}

	/**
	 * Get method for retrieving an instance of SoilProfileLayer identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilProfileLayerConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilProfileLayerConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilProfileLayerConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of SoilProfileLayer identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an SoilProfileLayerConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(SoilProfileLayerConverter data) {
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
	 * Delete method for deleting an instance of SoilProfileLayer identified by id.
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
	 * Returns an instance of SoilProfileLayer identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of SoilProfileLayer
	 */
	protected SoilProfileLayer getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.SoilProfileLayerPK id = new beans.SoilProfileLayerPK(id1, id2, id3, id4);
			return (SoilProfileLayer) em.createQuery("SELECT e FROM SoilProfileLayer e where e.soilProfileLayerPK = :soilProfileLayerPK").setParameter("soilProfileLayerPK", id).getSingleResult();
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
	private SoilProfileLayer updateEntity(SoilProfileLayer entity, SoilProfileLayer newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		SoilProfile soilProfile = entity.getSoilProfile();
		SoilProfile soilProfileNew = newEntity.getSoilProfile();
		entity = em.merge(newEntity);
		if (soilProfile != null && !soilProfile.equals(soilProfileNew)) {
			soilProfile.getSoilProfileLayerCollection().remove(entity);
		}
		if (soilProfileNew != null && !soilProfileNew.equals(soilProfile)) {
			soilProfileNew.getSoilProfileLayerCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(SoilProfileLayer entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		SoilProfile soilProfile = entity.getSoilProfile();
		if (soilProfile != null) {
			soilProfile.getSoilProfileLayerCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of SoilProfileResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of SoilProfileResource
	 */
	@Path("soilProfile/")
	public SoilProfileResource getSoilProfileResource() {
		SoilProfileResourceSub soilProfileResourceSub = resourceContext.getResource(SoilProfileResourceSub.class);
		soilProfileResourceSub.setParent(getEntity());
		return soilProfileResourceSub;
	}

	public static class SoilProfileResourceSub extends SoilProfileResource {

		private SoilProfileLayer parent;

		public void setParent(SoilProfileLayer parent) {
			this.parent = parent;
		}

		@Override
		protected SoilProfile getEntity() {
			SoilProfile entity = parent.getSoilProfile();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
