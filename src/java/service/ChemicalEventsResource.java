package service;

import beans.ChemicalEvent;
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
import beans.ChemicalLevel;
import converter.ChemicalEventsConverter;
import converter.ChemicalEventConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/chemicalEvents/")
public class ChemicalEventsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of ChemicalEventsResource */
	public ChemicalEventsResource() {
	}

	/**
	 * Get method for retrieving a collection of ChemicalEvents instance in XML format.
	 *
	 * @return an instance of ChemicalEventssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public ChemicalEventsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM ChemicalEvent e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new ChemicalEventsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of ChemicalEvents using XML as the input format.
	 *
	 * @param data an ChemicalEventsConverter entity that is deserialized from an XML stream
	 * @return an instance of ChemicalEventsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(ChemicalEventConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			ChemicalEvent entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getChemicalEventPK().getExpId() + "," + entity.getChemicalEventPK().getCh() + "," + entity.getChemicalEventPK().getChApplNo() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of ChemicalEventResource used for entity navigation.
	 *
	 * @return an instance of ChemicalEventResource
	 */
	@Path("{expId},{ch},{chApplNo}/")
	public ChemicalEventResource getChemicalEventsResource(@PathParam("expId") Integer id1, @PathParam("ch") Integer id2, @PathParam("chApplNo") Integer id3) {
		ChemicalEventResource chemicalEventsResource = resourceContext.getResource(ChemicalEventResource.class);
		chemicalEventsResource.setId1(id1);
		chemicalEventsResource.setId2(id2);
		chemicalEventsResource.setId3(id3);
		return chemicalEventsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of ChemicalEvents instances
	 */
	protected Collection<ChemicalEvent> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(ChemicalEvent entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		ChemicalLevel chemicalLevels = entity.getChemicalLevel();
		if (chemicalLevels != null) {
			chemicalLevels.getChemicalEventsCollection().add(entity);
		}
	}
}
