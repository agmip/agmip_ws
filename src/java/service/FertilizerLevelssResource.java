/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.FertilizerLevels;
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
import beans.Treatments;
import converter.FertilizerLevelssConverter;
import converter.FertilizerLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/fertilizerLevelss/")
public class FertilizerLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of FertilizerLevelssResource */
    public FertilizerLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of FertilizerLevels instance in XML format.
     *
     * @return an instance of FertilizerLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public FertilizerLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM FertilizerLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new FertilizerLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of FertilizerLevels using XML as the input format.
     *
     * @param data an FertilizerLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of FertilizerLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(FertilizerLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            FertilizerLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getFertilizerLevelsPK().getExpId() + "," + entity.getFertilizerLevelsPK().getFe() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of FertilizerLevelsResource used for entity navigation.
     *
     * @return an instance of FertilizerLevelsResource
     */
    @Path("{expId},{fe}/")
    public FertilizerLevelsResource getFertilizerLevelsResource(@PathParam("expId") Integer id1, @PathParam("fe") Integer id2) {
        FertilizerLevelsResource fertilizerLevelsResource = resourceContext.getResource(FertilizerLevelsResource.class);
        fertilizerLevelsResource.setId1(id1);
        fertilizerLevelsResource.setId2(id2);
        return fertilizerLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of FertilizerLevels instances
     */
    protected Collection<FertilizerLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(FertilizerLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            FertilizerLevels oldEntity = value.getFertilizerLevels();
            value.setFertilizerLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
