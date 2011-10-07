/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.WeatherDaily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author fonini
 */
@Path("/saveWeatherDailies")
public class SaveWeatherDailiesResource {

	@Context
	private UriInfo context;

	/** Creates a new instance of SaveWeatherDailiesResource */
	public SaveWeatherDailiesResource() {
	}

	/**
	 * Retrieves representation of an instance of service.SaveWeatherDailiesResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces("application/json")
	public String getJson() {
		//TODO return proper representation object
		return "Teste";
		//throw new UnsupportedOperationException();
	}

	/**
	 * POST method for creating an instance of SaveWeatherDailyResource
	 * @param content representation for the new resource
	 * @return an HTTP response with content of the created resource
	 */
	@POST
    @Consumes("application/json")
    @Produces("application/json")
	public Response postJson(String content) {
		Gson json = new Gson();
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();

			Type collectionType = new TypeToken<Collection<WeatherDaily>>(){}.getType();
			Collection <WeatherDaily> collection = json.fromJson(content, collectionType);

			for (WeatherDaily daily : collection){
				em.persist(daily);
			}

			persistenceSvc.commitTx();

			return Response.created(context.getAbsolutePath()).build();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	/**
	 * Sub-resource locator method for {id}
	 */
	@Path("{id}")
	public SaveWeatherDailyResource getSaveWeatherDailyResource(@PathParam("id") String id) {
		return SaveWeatherDailyResource.getInstance(id);
	}
}
