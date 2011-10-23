package service;

import beans.MulchLevel;
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
import converter.MulchLevelsConverter;
import converter.MulchLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/mulchLevels/")
public class MulchLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of MulchLevelsResource */
    public MulchLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of MulchLevel instance in XML format.
     *
     * @return an instance of MulchLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public MulchLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM MulchLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new MulchLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of MulchLevel using XML as the input format.
     *
     * @param data an MulchLevelConverter entity that is deserialized from an XML stream
     * @return an instance of MulchLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(MulchLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            MulchLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getMulchLevelsPK().getExpId() + "," + entity.getMulchLevelsPK().getMl() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of MulchLevelResource used for entity navigation.
     *
     * @return an instance of MulchLevelResource
     */
    @Path("{expId},{ml}/")
    public MulchLevelResource getMulchLevelsResource(@PathParam("expId") Integer id1, @PathParam("ml") Integer id2) {
        MulchLevelResource mulchLevelsResource = resourceContext.getResource(MulchLevelResource.class);
        mulchLevelsResource.setId1(id1);
        mulchLevelsResource.setId2(id2);
        return mulchLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of MulchLevel instances
     */
    protected Collection<MulchLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(MulchLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            MulchLevel oldEntity = value.getMulchLevels();
            value.setMulchLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
