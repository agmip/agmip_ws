package service;

import beans.ChemicalEvent;
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
import beans.ChemicalLevel;
import beans.ChemicalLevelPK;
import converter.ChemicalEventsConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.ChemicalEventConverter;

/**
 *
 * @author fonini
 */
public class ChemicalEventResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of ChemicalEventResource */
	public ChemicalEventResource() {
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
	 * Get method for retrieving an instance of ChemicalEvents identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of ChemicalEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public ChemicalEventConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new ChemicalEventConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of ChemicalEvents identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an ChemicalEventsConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(ChemicalEventConverter data) {
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
	 * Delete method for deleting an instance of ChemicalEvents identified by id.
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
	 * Returns an instance of ChemicalEvents identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of ChemicalEvents
	 */
	protected ChemicalEvent getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.ChemicalEventPK id = new beans.ChemicalEventPK(id1, id2, id3);
			return (ChemicalEvent) em.createQuery("SELECT e FROM ChemicalEvent e where e.chemicalEventPK = :chemicalEventPK").setParameter("chemicalEventPK", id).getSingleResult();
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
	private ChemicalEvent updateEntity(ChemicalEvent entity, ChemicalEvent newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		ChemicalLevel chemicalLevels = entity.getChemicalLevel();
		ChemicalLevel chemicalLevelsNew = newEntity.getChemicalLevel();
		entity = em.merge(newEntity);
		if (chemicalLevels != null && !chemicalLevels.equals(chemicalLevelsNew)) {
			chemicalLevels.getChemicalEventsCollection().remove(entity);
		}
		if (chemicalLevelsNew != null && !chemicalLevelsNew.equals(chemicalLevels)) {
			chemicalLevelsNew.getChemicalEventsCollection().add(entity);
		}
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(ChemicalEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		ChemicalLevel chemicalLevels = entity.getChemicalLevel();
		if (chemicalLevels != null) {
			chemicalLevels.getChemicalEventsCollection().remove(entity);
		}
		em.remove(entity);
	}

	/**
	 * Returns a dynamic instance of ChemicalLevelsResource used for entity navigation.
	 *
	 * @param id identifier for the parent entity
	 * @return an instance of ChemicalLevelsResource
	 */
	@Path("chemicalLevels/")
	public ChemicalLevelsResource getChemicalLevelsResource() {
		ChemicalLevelsResourceSub chemicalLevelsResourceSub = resourceContext.getResource(ChemicalLevelsResourceSub.class);
		chemicalLevelsResourceSub.setParent(getEntity());
		return chemicalLevelsResourceSub;
	}

	public static class ChemicalLevelsResourceSub extends ChemicalLevelsResource {

		private ChemicalEvent parent;

		public void setParent(ChemicalEvent parent) {
			this.parent = parent;
		}

		protected ChemicalLevel getEntity() {
			ChemicalLevel entity = parent.getChemicalLevel();
			if (entity == null) {
				throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
			}
			return entity;
		}
	}
}
