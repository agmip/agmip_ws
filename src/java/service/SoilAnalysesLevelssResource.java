/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.SoilAnalysesLevels;
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
import converter.SoilAnalysesLevelssConverter;
import converter.SoilAnalysesLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/soilAnalysesLevelss/")
public class SoilAnalysesLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of SoilAnalysesLevelssResource */
    public SoilAnalysesLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of SoilAnalysesLevels instance in XML format.
     *
     * @return an instance of SoilAnalysesLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public SoilAnalysesLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM SoilAnalysesLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new SoilAnalysesLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
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
    public Response post(SoilAnalysesLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            SoilAnalysesLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getSoilAnalysesLevelsPK().getExpId() + "," + entity.getSoilAnalysesLevelsPK().getSa() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of SoilAnalysesLevelsResource used for entity navigation.
     *
     * @return an instance of SoilAnalysesLevelsResource
     */
    @Path("{expId},{sa}/")
    public SoilAnalysesLevelsResource getSoilAnalysesLevelsResource(@PathParam("expId") Integer id1, @PathParam("sa") Integer id2) {
        SoilAnalysesLevelsResource soilAnalysesLevelsResource = resourceContext.getResource(SoilAnalysesLevelsResource.class);
        soilAnalysesLevelsResource.setId1(id1);
        soilAnalysesLevelsResource.setId2(id2);
        return soilAnalysesLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of SoilAnalysesLevels instances
     */
    protected Collection<SoilAnalysesLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(SoilAnalysesLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            SoilAnalysesLevels oldEntity = value.getSoilAnalysesLevels();
            value.setSoilAnalysesLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
