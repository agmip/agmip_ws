package service;

import beans.FertilizerLevel;
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
import converter.FertilizerLevelsConverter;
import converter.FertilizerLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/fertilizerLevels/")
public class FertilizerLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of FertilizerLevelsResource */
    public FertilizerLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of FertilizerLevel instance in XML format.
     *
     * @return an instance of FertilizerLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public FertilizerLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM FertilizerLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new FertilizerLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of FertilizerLevel using XML as the input format.
     *
     * @param data an FertilizerLevelConverter entity that is deserialized from an XML stream
     * @return an instance of FertilizerLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(FertilizerLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            FertilizerLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getFertilizerLevelPK().getExpId() + "," + entity.getFertilizerLevelPK().getFe() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of FertilizerLevelResource used for entity navigation.
     *
     * @return an instance of FertilizerLevelResource
     */
    @Path("{expId},{fe}/")
    public FertilizerLevelResource getFertilizerLevelsResource(@PathParam("expId") Integer id1, @PathParam("fe") Integer id2) {
        FertilizerLevelResource fertilizerLevelsResource = resourceContext.getResource(FertilizerLevelResource.class);
        fertilizerLevelsResource.setId1(id1);
        fertilizerLevelsResource.setId2(id2);
        return fertilizerLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of FertilizerLevel instances
     */
    protected Collection<FertilizerLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(FertilizerLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            FertilizerLevel oldEntity = value.getFertilizerLevels();
            value.setFertilizerLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
