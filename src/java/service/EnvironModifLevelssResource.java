/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.EnvironModifLevels;
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
import converter.EnvironModifLevelssConverter;
import converter.EnvironModifLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/environModifLevelss/")
public class EnvironModifLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of EnvironModifLevelssResource */
    public EnvironModifLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of EnvironModifLevels instance in XML format.
     *
     * @return an instance of EnvironModifLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public EnvironModifLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM EnvironModifLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new EnvironModifLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of EnvironModifLevels using XML as the input format.
     *
     * @param data an EnvironModifLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of EnvironModifLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(EnvironModifLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            EnvironModifLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getEnvironModifLevelsPK().getExpId() + "," + entity.getEnvironModifLevelsPK().getEm() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of EnvironModifLevelsResource used for entity navigation.
     *
     * @return an instance of EnvironModifLevelsResource
     */
    @Path("{expId},{em}/")
    public EnvironModifLevelsResource getEnvironModifLevelsResource(@PathParam("expId") Integer id1, @PathParam("em") Integer id2) {
        EnvironModifLevelsResource environModifLevelsResource = resourceContext.getResource(EnvironModifLevelsResource.class);
        environModifLevelsResource.setId1(id1);
        environModifLevelsResource.setId2(id2);
        return environModifLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of EnvironModifLevels instances
     */
    protected Collection<EnvironModifLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(EnvironModifLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            EnvironModifLevels oldEntity = value.getEnvironModifLevels();
            value.setEnvironModifLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
