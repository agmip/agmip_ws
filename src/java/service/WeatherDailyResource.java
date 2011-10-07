package service;

import beans.WeatherDaily;
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
import converter.WeatherDailyConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class WeatherDailyResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;
	protected Integer id3;
	protected Integer id2;
	protected Integer id1;

	/** Creates a new instance of WeatherDailyResource */
	public WeatherDailyResource() {
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
	 * Get method for retrieving an instance of WeatherDaily identified by id in XML format.
	 *
	 * @param id identifier for the entity
	 * @return an instance of WeatherDailyConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherDailyConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherDailyConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			PersistenceService.getInstance().close();
		}
	}

	/**
	 * Put method for updating an instance of WeatherDaily identified by id using XML as the input format.
	 *
	 * @param id identifier for the entity
	 * @param data an WeatherDailyConverter entity that is deserialized from a XML stream
	 */
	@PUT
    @Consumes({"application/xml", "application/json"})
	public void put(WeatherDailyConverter data) {
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
	 * Delete method for deleting an instance of WeatherDaily identified by id.
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
	 * Returns an instance of WeatherDaily identified by id.
	 *
	 * @param id identifier for the entity
	 * @return an instance of WeatherDaily
	 */
	protected WeatherDaily getEntity() {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		try {
			beans.WeatherDailyPK id = new beans.WeatherDailyPK(id1, id2, id3);
			return (WeatherDaily) em.createQuery("SELECT e FROM WeatherDaily e where e.weatherDailyPK = :weatherDailyPK").setParameter("weatherDailyPK", id).getSingleResult();
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
	private WeatherDaily updateEntity(WeatherDaily entity, WeatherDaily newEntity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		entity = em.merge(newEntity);
		return entity;
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity the entity to deletle
	 */
	private void deleteEntity(WeatherDaily entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.remove(entity);
	}
}
