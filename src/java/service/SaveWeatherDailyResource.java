/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SaveWeatherDailyResource {

	private String id;

	/** Creates a new instance of SaveWeatherDailyResource */
	private SaveWeatherDailyResource(String id) {
		this.id = id;
	}

	/** Get instance of the SaveWeatherDailyResource */
	public static SaveWeatherDailyResource getInstance(String id) {
		// The user may use some kind of persistence mechanism
		// to store and restore instances of SaveWeatherDailyResource class.
		return new SaveWeatherDailyResource(id);
	}

	/**
	 * Retrieves representation of an instance of service.SaveWeatherDailyResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces("application/json")
	public String getJson() {
		//TODO return proper representation object
		//throw new UnsupportedOperationException();
		return "Teste";
	}

	/**
	 * PUT method for updating or creating an instance of SaveWeatherDailyResource
	 * @param content representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
    @Consumes("application/json")
	public void putJson(String content) {
	}

	/**
	 * DELETE method for resource SaveWeatherDailyResource
	 */
	@DELETE
	public void delete() {
	}
}
