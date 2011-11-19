package service;

import beans.SoilAnalysesLevel;
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
import beans.SoilAnalysesEvent;
import converter.SoilAnalysesLevelsConverter;
import converter.SoilAnalysesLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/soilAnalysesLevels/")
public class SoilAnalysesLevelsResource {
	@Context
	protected ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of SoilAnalysesLevelsResource */
	public SoilAnalysesLevelsResource() {
	}

	/**
	 * Get method for retrieving a collection of SoilAnalysesLevels instance in XML format.
	 *
	 * @return an instance of SoilAnalysesLevelssConverter
	 */
	@GET
    @Produces({"application/xml", "application/json"})
	public SoilAnalysesLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM SoilAnalysesLevel e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new SoilAnalysesLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/**
	 * Post method for creating an instance of SoilAnalysesLevels using XML as the input format.
	 *
	 * @param data an SoilAnalysesLevelsConverter entity that is deserialized from an XML stream
	 * @return an instance of SoilAnalysesLevelsConverter
	 */
	@POST
    @Consumes({"application/xml", "application/json"})
	public Response post(SoilAnalysesLevelConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();
			SoilAnalysesLevel entity = data.resolveEntity(em);
			createEntity(data.resolveEntity(em));
			persistenceSvc.commitTx();
			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getSoilAnalysesLevelPK().getExpId() + "," + entity.getSoilAnalysesLevelPK().getSa() + "/")).build();
		}
		finally {
			persistenceSvc.close();
		}
	}

	/**
	 * Returns a dynamic instance of SoilAnalysesLevelResource used for entity navigation.
	 *
	 * @return an instance of SoilAnalysesLevelResource
	 */
	@Path("{expId},{sa}/")
	public SoilAnalysesLevelResource getSoilAnalysesLevelsResource(@PathParam("expId") Integer id1, @PathParam("sa") Integer id2) {
		SoilAnalysesLevelResource soilAnalysesLevelsResource = resourceContext.getResource(SoilAnalysesLevelResource.class);
		soilAnalysesLevelsResource.setId1(id1);
		soilAnalysesLevelsResource.setId2(id2);
		return soilAnalysesLevelsResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of SoilAnalysesLevels instances
	 */
	protected Collection<SoilAnalysesLevel> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(SoilAnalysesLevel entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
		for (SoilAnalysesEvent value : entity.getSoilAnalysesEventsCollection()) {
			SoilAnalysesLevel oldEntity = value.getSoilAnalysesLevel();
			value.setSoilAnalysesLevel(entity);
			if (oldEntity != null) {
				oldEntity.getSoilAnalysesEventsCollection().remove(value);
			}
		}
		for (Treatment value : entity.getTreatmentsCollection()) {
			SoilAnalysesLevel oldEntity = value.getSoilAnalysesLevel();
			value.setSoilAnalysesLevel(entity);
			if (oldEntity != null) {
				oldEntity.getTreatmentsCollection().remove(value);
			}
		}
	}
}
