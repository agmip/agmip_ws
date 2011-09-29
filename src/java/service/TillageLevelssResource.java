/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.TillageLevels;
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
import converter.TillageLevelssConverter;
import converter.TillageLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/tillageLevelss/")
public class TillageLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of TillageLevelssResource */
    public TillageLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of TillageLevels instance in XML format.
     *
     * @return an instance of TillageLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TillageLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM TillageLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TillageLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of TillageLevels using XML as the input format.
     *
     * @param data an TillageLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of TillageLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(TillageLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            TillageLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getTillageLevelsPK().getExpId() + "," + entity.getTillageLevelsPK().getTi() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of TillageLevelsResource used for entity navigation.
     *
     * @return an instance of TillageLevelsResource
     */
    @Path("{expId},{ti}/")
    public TillageLevelsResource getTillageLevelsResource(@PathParam("expId") Integer id1, @PathParam("ti") Integer id2) {
        TillageLevelsResource tillageLevelsResource = resourceContext.getResource(TillageLevelsResource.class);
        tillageLevelsResource.setId1(id1);
        tillageLevelsResource.setId2(id2);
        return tillageLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of TillageLevels instances
     */
    protected Collection<TillageLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(TillageLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            TillageLevels oldEntity = value.getTillageLevels();
            value.setTillageLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
