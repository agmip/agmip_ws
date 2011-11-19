package service;

import beans.TillageEvent;
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
import beans.TillageLevel;
import converter.TillageEventsConverter;
import converter.TillageEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/tillageEvents/")
public class TillageEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of TillageEventsResource */
	public TillageEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of TillageEvents instance in XML format.
	 *
	 * @return an instance of TillageEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public TillageEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM TillageEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new TillageEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of TillageEvents using XML as the input format.
	 *
	 * @param data an TillageEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of TillageEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(TillageEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			TillageEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getTillageEventPK().getExpId() + "," + entity.getTillageEventPK().getTi() + "," + entity.getTillageEventPK().getTiOpsNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of TillageEventResource used for entity navigation.
	 *
	 * @return an instance of TillageEventResource
	 */
	@Path("{expId},{ti},{tiOpsNo}/")
	public TillageEventResource getTillageEventsResource(@PathParam("expId") Integer id1, @PathParam("ti") Integer id2, @PathParam("tiOpsNo") Integer id3) {
		TillageEventResource tillageEventsResource = resourceContext.getResource(TillageEventResource.class);
		tillageEventsResource.setId1(id1);
		tillageEventsResource.setId2(id2);
		tillageEventsResource.setId3(id3);
		return tillageEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of TillageEvents instances
	 */
	protected Collection<TillageEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(TillageEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		TillageLevel tillageLevels = entity.getTillageLevel();
		if (tillageLevels != null) {
			tillageLevels.getTillageEventsCollection().add(entity);
		}
	}
}
