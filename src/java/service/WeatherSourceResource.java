package service;

import beans.WeatherSource;
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
import converter.WeatherSourceConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class WeatherSourceResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id;

	/** Creates a new instance of WeatherSourceResource */
	public WeatherSourceResource() {
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method for retrieving an instance of WeatherSource identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of WeatherSourceConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherSourceConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherSourceConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of WeatherSource identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an WeatherSourceConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(WeatherSourceConverter data) {
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
	 * Delete method for deleting an instance of WeatherSource identified by id.
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
	 * Returns an instance of WeatherSource identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of WeatherSource
	 */
	protected WeatherSource getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			return (WeatherSource) em.createQuery("SELECT e FROM WeatherSource e where e.wid = :wid").setParameter("wid", id).getSingleResult();
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
	private WeatherSource updateEntity(WeatherSource entity, WeatherSource newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		entity = em.merge(newEntity);
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(WeatherSource entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.remove(entity);
	}
}
