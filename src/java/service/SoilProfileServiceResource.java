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
public class SoilProfileServiceResource {

	private String id;

	/** Creates a new instance of SoilProfileServiceResource */
	private SoilProfileServiceResource(String id) {
		this.id = id;
	}

	/** Get instance of the SoilProfileServiceResource */
	public static SoilProfileServiceResource getInstance(String id) {
		// The user may use some kind of persistence mechanism
		// to store and restore instances of SoilProfileServiceResource class.
		return new SoilProfileServiceResource(id);
	}

	/**
	 * Retrieves representation of an instance of service.SoilProfileServiceResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces("application/xml")
	public String getXml() {
		//TODO return proper representation object
		throw new UnsupportedOperationException();
	}

	/**
	 * PUT method for updating or creating an instance of SoilProfileServiceResource
	 * @param content representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
    @Consumes("application/xml")
	public void putXml(String content) {
	}

	/**
	 * DELETE method for resource SoilProfileServiceResource
	 */
	@DELETE
	public void delete() {
	}
}
