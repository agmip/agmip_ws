/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.IrrigationLevels;
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
import converter.IrrigationLevelssConverter;
import converter.IrrigationLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/irrigationLevelss/")
public class IrrigationLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of IrrigationLevelssResource */
    public IrrigationLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of IrrigationLevels instance in XML format.
     *
     * @return an instance of IrrigationLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public IrrigationLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM IrrigationLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new IrrigationLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of IrrigationLevels using XML as the input format.
     *
     * @param data an IrrigationLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of IrrigationLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(IrrigationLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            IrrigationLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getIrrigationLevelsPK().getExpId() + "," + entity.getIrrigationLevelsPK().getIr() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of IrrigationLevelsResource used for entity navigation.
     *
     * @return an instance of IrrigationLevelsResource
     */
    @Path("{expId},{ir}/")
    public IrrigationLevelsResource getIrrigationLevelsResource(@PathParam("expId") Integer id1, @PathParam("ir") Integer id2) {
        IrrigationLevelsResource irrigationLevelsResource = resourceContext.getResource(IrrigationLevelsResource.class);
        irrigationLevelsResource.setId1(id1);
        irrigationLevelsResource.setId2(id2);
        return irrigationLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of IrrigationLevels instances
     */
    protected Collection<IrrigationLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(IrrigationLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            IrrigationLevels oldEntity = value.getIrrigationLevels();
            value.setIrrigationLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
