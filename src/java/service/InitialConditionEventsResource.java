package service;

import beans.InitialConditionEvent;
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
import beans.InitialConditionLevel;
import converter.InitialConditionEventsConverter;
import converter.InitialConditionEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/initialConditionEvents/")
public class InitialConditionEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of InitialConditionEventsResource */
	public InitialConditionEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of InitialConditionEvents instance in XML format.
	 *
	 * @return an instance of InitialConditionEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public InitialConditionEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM InitialConditionEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new InitialConditionEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of InitialConditionEvents using XML as the input format.
	 *
	 * @param data an InitialConditionEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of InitialConditionEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(InitialConditionEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			InitialConditionEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getInitialConditionEventPK().getExpId() + "," + entity.getInitialConditionEventPK().getIc() + "," + entity.getInitialConditionEventPK().getIcLayer() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of InitialConditionEventResource used for entity navigation.
	 *
	 * @return an instance of InitialConditionEventResource
	 */
	@Path("{expId},{ic},{icLayer}/")
	public InitialConditionEventResource getInitialConditionEventsResource(@PathParam("expId") Integer id1, @PathParam("ic") Integer id2, @PathParam("icLayer") Integer id3) {
		InitialConditionEventResource initialConditionEventsResource = resourceContext.getResource(InitialConditionEventResource.class);
		initialConditionEventsResource.setId1(id1);
		initialConditionEventsResource.setId2(id2);
		initialConditionEventsResource.setId3(id3);
		return initialConditionEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of InitialConditionEvents instances
	 */
	protected Collection<InitialConditionEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(InitialConditionEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		InitialConditionLevel initialConditionLevels = entity.getInitialConditionLevel();
		if (initialConditionLevels != null) {
			initialConditionLevels.getInitialConditionEventsCollection().add(entity);
		}
	}
}
