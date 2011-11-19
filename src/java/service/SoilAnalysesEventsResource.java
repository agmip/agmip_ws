package service;

import beans.SoilAnalysesEvent;
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
import beans.SoilAnalysesLevel;
import converter.SoilAnalysesEventsConverter;
import converter.SoilAnalysesEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/soilAnalysesEvents/")
public class SoilAnalysesEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of SoilAnalysesEventsResource */
	public SoilAnalysesEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of SoilAnalysesEvent instance in XML format.
	 *
	 * @return an instance of SoilAnalysesEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilAnalysesEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM SoilAnalysesEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilAnalysesEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of SoilAnalysesEvent using XML as the input format.
	 *
	 * @param data an SoilAnalysesEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of SoilAnalysesEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(SoilAnalysesEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			SoilAnalysesEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getSoilAnalysesEventPK().getExpId() + "," + entity.getSoilAnalysesEventPK().getSa() + "," + entity.getSoilAnalysesEventPK().getSaApplNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of SoilAnalysesEventResource used for entity navigation.
	 *
	 * @return an instance of SoilAnalysesEventResource
	 */
	@Path("{expId},{sa},{saApplNo}/")
	public SoilAnalysesEventResource getSoilAnalysesEventsResource(@PathParam("expId") Integer id1, @PathParam("sa") Integer id2, @PathParam("saApplNo") Integer id3) {
		SoilAnalysesEventResource soilAnalysesEventsResource = resourceContext.getResource(SoilAnalysesEventResource.class);
		soilAnalysesEventsResource.setId1(id1);
		soilAnalysesEventsResource.setId2(id2);
		soilAnalysesEventsResource.setId3(id3);
		return soilAnalysesEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of SoilAnalysesEvent instances
	 */
	protected Collection<SoilAnalysesEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(SoilAnalysesEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		SoilAnalysesLevel soilAnalysesLevels = entity.getSoilAnalysesLevel();
		if (soilAnalysesLevels != null) {
			soilAnalysesLevels.getSoilAnalysesEventsCollection().add(entity);
		}
	}
}
