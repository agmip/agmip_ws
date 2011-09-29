/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.InitialConditionLevels;
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
import converter.InitialConditionLevelssConverter;
import converter.InitialConditionLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/initialConditionLevelss/")
public class InitialConditionLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of InitialConditionLevelssResource */
    public InitialConditionLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of InitialConditionLevels instance in XML format.
     *
     * @return an instance of InitialConditionLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public InitialConditionLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM InitialConditionLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new InitialConditionLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of InitialConditionLevels using XML as the input format.
     *
     * @param data an InitialConditionLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of InitialConditionLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(InitialConditionLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            InitialConditionLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getInitialConditionLevelsPK().getExpId() + "," + entity.getInitialConditionLevelsPK().getIc() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of InitialConditionLevelsResource used for entity navigation.
     *
     * @return an instance of InitialConditionLevelsResource
     */
    @Path("{expId},{ic}/")
    public InitialConditionLevelsResource getInitialConditionLevelsResource(@PathParam("expId") Integer id1, @PathParam("ic") Integer id2) {
        InitialConditionLevelsResource initialConditionLevelsResource = resourceContext.getResource(InitialConditionLevelsResource.class);
        initialConditionLevelsResource.setId1(id1);
        initialConditionLevelsResource.setId2(id2);
        return initialConditionLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of InitialConditionLevels instances
     */
    protected Collection<InitialConditionLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(InitialConditionLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            InitialConditionLevels oldEntity = value.getInitialConditionLevels();
            value.setInitialConditionLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
