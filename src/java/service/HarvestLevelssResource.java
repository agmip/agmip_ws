/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.HarvestLevels;
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
import converter.HarvestLevelssConverter;
import converter.HarvestLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/harvestLevelss/")
public class HarvestLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of HarvestLevelssResource */
    public HarvestLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of HarvestLevels instance in XML format.
     *
     * @return an instance of HarvestLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public HarvestLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM HarvestLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new HarvestLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of HarvestLevels using XML as the input format.
     *
     * @param data an HarvestLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of HarvestLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(HarvestLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            HarvestLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getHarvestLevelsPK().getExpId() + "," + entity.getHarvestLevelsPK().getHa() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of HarvestLevelsResource used for entity navigation.
     *
     * @return an instance of HarvestLevelsResource
     */
    @Path("{expId},{ha}/")
    public HarvestLevelsResource getHarvestLevelsResource(@PathParam("expId") Integer id1, @PathParam("ha") Integer id2) {
        HarvestLevelsResource harvestLevelsResource = resourceContext.getResource(HarvestLevelsResource.class);
        harvestLevelsResource.setId1(id1);
        harvestLevelsResource.setId2(id2);
        return harvestLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of HarvestLevels instances
     */
    protected Collection<HarvestLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(HarvestLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            HarvestLevels oldEntity = value.getHarvestLevels();
            value.setHarvestLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
