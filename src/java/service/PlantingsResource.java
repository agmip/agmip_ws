package service;

import beans.Planting;
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
import converter.PlantingsConverter;
import converter.PlantingConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/plantings/")
public class PlantingsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of PlantingsResource */
    public PlantingsResource() {
    }

    /**
     * Get method for retrieving a collection of Planting instance in XML format.
     *
     * @return an instance of PlantingsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public PlantingsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Planting e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new PlantingsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Planting using XML as the input format.
     *
     * @param data an PlantingConverter entity that is deserialized from an XML stream
     * @return an instance of PlantingConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(PlantingConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Planting entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getPlantingPK().getExpId() + "," + entity.getPlantingPK().getPl() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of PlantingResource used for entity navigation.
     *
     * @return an instance of PlantingResource
     */
    @Path("{expId},{pl}/")
    public PlantingResource getPlantingsResource(@PathParam("expId") Integer id1, @PathParam("pl") Integer id2) {
        PlantingResource plantingsResource = resourceContext.getResource(PlantingResource.class);
        plantingsResource.setId1(id1);
        plantingsResource.setId2(id2);
        return plantingsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Planting instances
     */
    protected Collection<Planting> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Planting entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            Planting oldEntity = value.getPlanting();
            value.setPlanting(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
