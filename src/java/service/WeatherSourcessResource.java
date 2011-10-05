package service;

import beans.WeatherSources;
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
import converter.WeatherSourcessConverter;
import converter.WeatherSourcesConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/weatherSourcess/")
public class WeatherSourcessResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of WeatherSourcessResource */
	public WeatherSourcessResource() {
	}

	/**
	 * Get method for retrieving a collection of WeatherSources instance in XML format.
	 *
	 * @return an instance of WeatherSourcessConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherSourcessConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM WeatherSources e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherSourcessConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of WeatherSources using XML as the input format.
	 *
	 * @param data an WeatherSourcesConverter entity that is deserialized from an XML stream
	 * @return an instance of WeatherSourcesConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(WeatherSourcesConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			WeatherSources entity = data.resolveEntity(em);
			
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getWid() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of WeatherSourcesResource used for entity navigation.
	 *
	 * @return an instance of WeatherSourcesResource
	 */
	@Path("{wid}/")
	public WeatherSourcesResource getWeatherSourcesResource(@PathParam("wid") Integer id) {
		WeatherSourcesResource weatherSourcesResource = resourceContext.getResource(WeatherSourcesResource.class);
		weatherSourcesResource.setId(id);
		return weatherSourcesResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of WeatherSources instances
	 */
	protected Collection<WeatherSources> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(WeatherSources entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
	}
}
