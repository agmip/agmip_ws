package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author fonini
 */
public class WeatherDailyServiceResource {

	private String id;

	/** Creates a new instance of WeatherDailyServiceResource */
	private WeatherDailyServiceResource(String id) {
		this.id = id;
	}

	/** Get instance of the WeatherDailyServiceResource */
	public static WeatherDailyServiceResource getInstance(String id) {
		// The user may use some kind of persistence mechanism
		// to store and restore instances of WeatherDailyServiceResource class.
		return new WeatherDailyServiceResource(id);
	}

	/**
	 * Retrieves representation of an instance of service.WeatherDailyServiceResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces("application/json")
	public String getJson() {
		//TODO return proper representation object
		return "Use the weatherDaily web service to get data";
	}

	/**
	 * PUT method for updating or creating an instance of WeatherDailyServiceResource
	 * @param content representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
    @Consumes("application/json")
	public void putJson(String content) {
	}

	/**
	 * DELETE method for resource WeatherDailyServiceResource
	 */
	@DELETE
	public void delete() {
	}
}
