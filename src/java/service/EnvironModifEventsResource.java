package service;

import beans.EnvironModifEvent;
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
import beans.EnvironModifLevel;
import converter.EnvironModifEventsConverter;
import converter.EnvironModifEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/environModifEvents/")
public class EnvironModifEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of EnvironModifEventsResource */
	public EnvironModifEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of EnvironModifEvents instance in XML format.
	 *
	 * @return an instance of EnvironModifEventsConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public EnvironModifEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM EnvironModifEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new EnvironModifEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of EnvironModifEvents using XML as the input format.
	 *
	 * @param data an EnvironModifEventConverter entity that is deserialized from an XML stream
	 * @return an instance of EnvironModifEventConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(EnvironModifEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			EnvironModifEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getEnvironModifEventPK().getExpId() + "," + entity.getEnvironModifEventPK().getEm() + "," + entity.getEnvironModifEventPK().getEnvLevNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of EnvironModifEventResource used for entity navigation.
	 *
	 * @return an instance of EnvironModifEventResource
	 */
	@Path("{expId},{em},{envLevNo}/")
	public EnvironModifEventResource getEnvironModifEventsResource(@PathParam("expId") Integer id1, @PathParam("em") Integer id2, @PathParam("envLevNo") Integer id3) {
		EnvironModifEventResource environModifEventsResource = resourceContext.getResource(EnvironModifEventResource.class);
		environModifEventsResource.setId1(id1);
		environModifEventsResource.setId2(id2);
		environModifEventsResource.setId3(id3);
		return environModifEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of EnvironModifEvents instances
	 */
	protected Collection<EnvironModifEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(EnvironModifEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		EnvironModifLevel environModifLevels = entity.getEnvironModifLevel();
		if (environModifLevels != null) {
			environModifLevels.getEnvironModifEventsCollection().add(entity);
		}
	}
}
