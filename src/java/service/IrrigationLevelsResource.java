package service;

import beans.IrrigationLevel;
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
import beans.Treatment;
import beans.IrrigationEvent;
import converter.IrrigationLevelsConverter;
import converter.IrrigationLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/irrigationLevels/")
public class IrrigationLevelsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of IrrigationLevelsResource */
	public IrrigationLevelsResource() {
	}

	/**
	 * Get method for retrieving a collection of IrrigationLevels instance in XML format.
	 *
	 * @return an instance of IrrigationLevelssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public IrrigationLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM IrrigationLevel e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new IrrigationLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of IrrigationLevels using XML as the input format.
	 *
	 * @param data an IrrigationLevelsConverter entity that is deserialized from an XML stream
	 * @return an instance of IrrigationLevelsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(IrrigationLevelConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			IrrigationLevel entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getIrrigationLevelPK().getExpId() + "," + entity.getIrrigationLevelPK().getIr() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of IrrigationLevelResource used for entity navigation.
	 *
	 * @return an instance of IrrigationLevelResource
	 */
	@Path("{expId},{ir}/")
	public IrrigationLevelResource getIrrigationLevelsResource(@PathParam("expId") Integer id1, @PathParam("ir") Integer id2) {
		IrrigationLevelResource irrigationLevelsResource = resourceContext.getResource(IrrigationLevelResource.class);
		irrigationLevelsResource.setId1(id1);
		irrigationLevelsResource.setId2(id2);
		return irrigationLevelsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of IrrigationLevels instances
	 */
	protected Collection<IrrigationLevel> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(IrrigationLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		for (IrrigationEvent value : entity.getIrrigationEventsCollection()) {
			IrrigationLevel oldEntity = value.getIrrigationLevel();
			value.setIrrigationLevel(entity);
			if (oldEntity != null) {
				oldEntity.getIrrigationEventsCollection().remove(value);
			}
		}
		for (Treatment value : entity.getTreatmentsCollection()) {
			IrrigationLevel oldEntity = value.getIrrigationLevel();
			value.setIrrigationLevel(entity);
			if (oldEntity != null) {
				oldEntity.getTreatmentsCollection().remove(value);
			}
		}
	}
}
