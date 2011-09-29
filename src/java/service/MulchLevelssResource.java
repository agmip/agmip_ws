/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.MulchLevels;
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
import converter.MulchLevelssConverter;
import converter.MulchLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/mulchLevelss/")
public class MulchLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of MulchLevelssResource */
    public MulchLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of MulchLevels instance in XML format.
     *
     * @return an instance of MulchLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public MulchLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM MulchLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new MulchLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of MulchLevels using XML as the input format.
     *
     * @param data an MulchLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of MulchLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(MulchLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            MulchLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getMulchLevelsPK().getExpId() + "," + entity.getMulchLevelsPK().getMl() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of MulchLevelsResource used for entity navigation.
     *
     * @return an instance of MulchLevelsResource
     */
    @Path("{expId},{ml}/")
    public MulchLevelsResource getMulchLevelsResource(@PathParam("expId") Integer id1, @PathParam("ml") Integer id2) {
        MulchLevelsResource mulchLevelsResource = resourceContext.getResource(MulchLevelsResource.class);
        mulchLevelsResource.setId1(id1);
        mulchLevelsResource.setId2(id2);
        return mulchLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of MulchLevels instances
     */
    protected Collection<MulchLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(MulchLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            MulchLevels oldEntity = value.getMulchLevels();
            value.setMulchLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
