package service;

import beans.IrrigationEvent;
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
import beans.IrrigationLevel;
import converter.IrrigationEventsConverter;
import converter.IrrigationEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/irrigationEvents/")
public class IrrigationEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of IrrigationEventsResource */
	public IrrigationEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of IrrigationEvents instance in XML format.
	 *
	 * @return an instance of IrrigationEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public IrrigationEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM IrrigationEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new IrrigationEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of IrrigationEvents using XML as the input format.
	 *
	 * @param data an IrrigationEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of IrrigationEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(IrrigationEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			IrrigationEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getIrrigationEventPK().getExpId() + "," + entity.getIrrigationEventPK().getIr() + "," + entity.getIrrigationEventPK().getIrAppNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of IrrigationEventResource used for entity navigation.
	 *
	 * @return an instance of IrrigationEventResource
	 */
	@Path("{expId},{ir},{irAppNo}/")
	public IrrigationEventResource getIrrigationEventsResource(@PathParam("expId") Integer id1, @PathParam("ir") Integer id2, @PathParam("irAppNo") Integer id3) {
		IrrigationEventResource irrigationEventsResource = resourceContext.getResource(IrrigationEventResource.class);
		irrigationEventsResource.setId1(id1);
		irrigationEventsResource.setId2(id2);
		irrigationEventsResource.setId3(id3);
		return irrigationEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of IrrigationEvents instances
	 */
	protected Collection<IrrigationEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(IrrigationEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		IrrigationLevel irrigationLevels = entity.getIrrigationLevel();
		if (irrigationLevels != null) {
			irrigationLevels.getIrrigationEventsCollection().add(entity);
		}
	}
}
