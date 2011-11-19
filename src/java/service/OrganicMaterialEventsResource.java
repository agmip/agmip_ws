package service;

import beans.OrganicMaterialEvent;
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
import beans.OrganicMaterialLevel;
import com.sun.jersey.api.core.ResourceContext;
import converter.OrganicMaterialEventConverter;
import converter.OrganicMaterialEventsConverter;

/**
 *
 * @author fonini
 */
@Path("/organicMaterialEvents/")
public class OrganicMaterialEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of OrganicMaterialEventsResource */
	public OrganicMaterialEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of OrganicMaterialEvents instance in XML format.
	 *
	 * @return an instance of OrganicMaterialEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public OrganicMaterialEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM OrganicMaterialEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new OrganicMaterialEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of OrganicMaterialEvents using XML as the input format.
	 *
	 * @param data an OrganicMaterialEventConverter entity that is deserialized from an XML stream
	 * @return an instance of OrganicMaterialEventConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(OrganicMaterialEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			OrganicMaterialEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getOrganicMaterialEventPK().getExpId() + "," + entity.getOrganicMaterialEventPK().getOm() + "," + entity.getOrganicMaterialEventPK().getOmOpsNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of OrganicMaterialEventResource used for entity navigation.
	 *
	 * @return an instance of OrganicMaterialEventResource
	 */
	@Path("{expId},{om},{omOpsNo}/")
	public OrganicMaterialEventResource getOrganicMaterialEventsResource(@PathParam("expId") Integer id1, @PathParam("om") Integer id2, @PathParam("omOpsNo") Integer id3) {
		OrganicMaterialEventResource organicMaterialEventsResource = resourceContext.getResource(OrganicMaterialEventResource.class);
		organicMaterialEventsResource.setId1(id1);
		organicMaterialEventsResource.setId2(id2);
		organicMaterialEventsResource.setId3(id3);
		return organicMaterialEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of OrganicMaterialEvents instances
	 */
	protected Collection<OrganicMaterialEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(OrganicMaterialEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		OrganicMaterialLevel organicMaterialLevels = entity.getOrganicMaterialLevel();
		if (organicMaterialLevels != null) {
			organicMaterialLevels.getOrganicMaterialEventsCollection().add(entity);
		}
	}
}
