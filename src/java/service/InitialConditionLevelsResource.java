package service;

import beans.InitialConditionLevel;
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
import converter.InitialConditionLevelsConverter;
import converter.InitialConditionLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/initialConditionLevels/")
public class InitialConditionLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of InitialConditionLevelsResource */
    public InitialConditionLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of InitialConditionLevel instance in XML format.
     *
     * @return an instance of InitialConditionLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public InitialConditionLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM InitialConditionLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new InitialConditionLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of InitialConditionLevel using XML as the input format.
     *
     * @param data an InitialConditionLevelConverter entity that is deserialized from an XML stream
     * @return an instance of InitialConditionLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(InitialConditionLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            InitialConditionLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getInitialConditionLevelPK().getExpId() + "," + entity.getInitialConditionLevelPK().getIc() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of InitialConditionLevelResource used for entity navigation.
     *
     * @return an instance of InitialConditionLevelResource
     */
    @Path("{expId},{ic}/")
    public InitialConditionLevelResource getInitialConditionLevelsResource(@PathParam("expId") Integer id1, @PathParam("ic") Integer id2) {
        InitialConditionLevelResource initialConditionLevelsResource = resourceContext.getResource(InitialConditionLevelResource.class);
        initialConditionLevelsResource.setId1(id1);
        initialConditionLevelsResource.setId2(id2);
        return initialConditionLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of InitialConditionLevel instances
     */
    protected Collection<InitialConditionLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(InitialConditionLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            InitialConditionLevel oldEntity = value.getInitialConditionLevel();
            value.setInitialConditionLevel(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
