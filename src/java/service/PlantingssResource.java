/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Plantings;
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
import converter.PlantingssConverter;
import converter.PlantingsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/plantingss/")
public class PlantingssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of PlantingssResource */
    public PlantingssResource() {
    }

    /**
     * Get method for retrieving a collection of Plantings instance in XML format.
     *
     * @return an instance of PlantingssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public PlantingssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Plantings e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new PlantingssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Plantings using XML as the input format.
     *
     * @param data an PlantingsConverter entity that is deserialized from an XML stream
     * @return an instance of PlantingsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(PlantingsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Plantings entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getPlantingsPK().getExpId() + "," + entity.getPlantingsPK().getPl() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of PlantingsResource used for entity navigation.
     *
     * @return an instance of PlantingsResource
     */
    @Path("{expId},{pl}/")
    public PlantingsResource getPlantingsResource(@PathParam("expId") Integer id1, @PathParam("pl") Integer id2) {
        PlantingsResource plantingsResource = resourceContext.getResource(PlantingsResource.class);
        plantingsResource.setId1(id1);
        plantingsResource.setId2(id2);
        return plantingsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Plantings instances
     */
    protected Collection<Plantings> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Plantings entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            Plantings oldEntity = value.getPlantings();
            value.setPlantings(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
