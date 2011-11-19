package service;

import beans.MulchEvent;
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
import beans.MulchLevel;
import converter.MulchEventsConverter;
import converter.MulchEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/mulchEvents/")
public class MulchEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of MulchEventsResource */
	public MulchEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of MulchEvents instance in XML format.
	 *
	 * @return an instance of MulchEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public MulchEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM MulchEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new MulchEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of MulchEvents using XML as the input format.
	 *
	 * @param data an MulchEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of MulchEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(MulchEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			MulchEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getMulchEventPK().getExpId() + "," + entity.getMulchEventPK().getMl() + "," + entity.getMulchEventPK().getMlApplNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of MulchEventResource used for entity navigation.
	 *
	 * @return an instance of MulchEventResource
	 */
	@Path("{expId},{ml},{mlApplNo}/")
	public MulchEventResource getMulchEventsResource(@PathParam("expId") Integer id1, @PathParam("ml") Integer id2, @PathParam("mlApplNo") Integer id3) {
		MulchEventResource mulchEventsResource = resourceContext.getResource(MulchEventResource.class);
		mulchEventsResource.setId1(id1);
		mulchEventsResource.setId2(id2);
		mulchEventsResource.setId3(id3);
		return mulchEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of MulchEvents instances
	 */
	protected Collection<MulchEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(MulchEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		MulchLevel mulchLevels = entity.getMulchLevel();
		if (mulchLevels != null) {
			mulchLevels.getMulchEventsCollection().add(entity);
		}
	}
}
