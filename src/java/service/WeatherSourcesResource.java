package service;

import beans.WeatherSource;
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
import converter.WeatherSourcesConverter;
import converter.WeatherSourceConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/weatherSources/")
public class WeatherSourcesResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of WeatherSourcesResource */
	public WeatherSourcesResource() {
	}

	/**
	 * Get method for retrieving a collection of WeatherSource instance in XML format.
	 *
	 * @return an instance of WeatherSourcesConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherSourcesConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM WeatherSource e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherSourcesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of WeatherSource using XML as the input format.
	 *
	 * @param data an WeatherSourceConverter entity that is deserialized from an XML stream
	 * @return an instance of WeatherSourceConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(WeatherSourceConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			WeatherSource entity = data.resolveEntity(em);

			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getWid() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of WeatherSourceResource used for entity navigation.
	 *
	 * @return an instance of WeatherSourceResource
	 */
	@Path("{wid}/")
	public WeatherSourceResource getWeatherSourcesResource(@PathParam("wid") Integer id) {
		WeatherSourceResource weatherSourcesResource = resourceContext.getResource(WeatherSourceResource.class);
		weatherSourcesResource.setId(id);
		return weatherSourcesResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of WeatherSource instances
	 */
	protected Collection<WeatherSource> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(WeatherSource entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
	}
}
