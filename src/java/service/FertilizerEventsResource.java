package service;

import beans.FertilizerEvent;
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
import beans.FertilizerLevel;
import converter.FertilizerEventsConverter;
import converter.FertilizerEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/fertilizerEvents/")
public class FertilizerEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of FertilizerEventsResource */
	public FertilizerEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of FertilizerEvents instance in XML format.
	 *
	 * @return an instance of FertilizerEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public FertilizerEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM FertilizerEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new FertilizerEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of FertilizerEvents using XML as the input format.
	 *
	 * @param data an FertilizerEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of FertilizerEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(FertilizerEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			FertilizerEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getFertilizerEventPK().getExpId() + "," + entity.getFertilizerEventPK().getFe() + "," + entity.getFertilizerEventPK().getFeApplNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of FertilizerEventResource used for entity navigation.
	 *
	 * @return an instance of FertilizerEventResource
	 */
	@Path("{expId},{fe},{feApplNo}/")
	public FertilizerEventResource getFertilizerEventsResource(@PathParam("expId") Integer id1, @PathParam("fe") Integer id2, @PathParam("feApplNo") Integer id3) {
		FertilizerEventResource fertilizerEventsResource = resourceContext.getResource(FertilizerEventResource.class);
		fertilizerEventsResource.setId1(id1);
		fertilizerEventsResource.setId2(id2);
		fertilizerEventsResource.setId3(id3);
		return fertilizerEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of FertilizerEvents instances
	 */
	protected Collection<FertilizerEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(FertilizerEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		FertilizerLevel fertilizerLevels = entity.getFertilizerLevel();
		if (fertilizerLevels != null) {
			fertilizerLevels.getFertilizerEventsCollection().add(entity);
		}
	}
}
