package service;

import beans.WeatherDaily;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.persistence.EntityManager;
import converter.WeatherDailiesConverter;
import converter.WeatherDailyConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/weatherDailies/")
public class WeatherDailiesResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of WeatherDailiesResource */
	public WeatherDailiesResource() {
	}

	/**
	 * Get method for retrieving a collection of WeatherDaily instance in XML format.
	 *
	 * @return an instance of WeatherDailiesConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherDailiesConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM WeatherDaily e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherDailiesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of WeatherDaily using XML as the input format.
	 *
	 * @param data an WeatherDailyConverter entity that is deserialized from an XML stream
	 * @return an instance of WeatherDailyConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	public Response post(WeatherDailyConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			WeatherDaily entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getWeatherDailyPK().getWid() + "," + entity.getWeatherDailyPK().getWtyr() + "," + entity.getWeatherDailyPK().getWtday() + "/")).entity(entity).build();
		}
		finally {
			persistenceSvc.close();
		}
	}



	/**
	 * Returns a dynamic instance of WeatherDailyResource used for entity navigation.
	 *
	 * @return an instance of WeatherDailyResource
	 */
	@Path("{wid},{wtyr},{wtday}/")
	public WeatherDailyResource getWeatherDailyResource(@PathParam("wid") Integer id1, @PathParam("wtyr") Integer id2, @PathParam("wtday") Integer id3) {
		WeatherDailyResource weatherDailyResource = resourceContext.getResource(WeatherDailyResource.class);
		weatherDailyResource.setId1(id1);
		weatherDailyResource.setId2(id2);
		weatherDailyResource.setId3(id3);
		return weatherDailyResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of WeatherDaily instances
	 */
	protected Collection<WeatherDaily> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(WeatherDaily entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
	}
}
