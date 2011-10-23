package service;

import beans.TillageLevel;
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
import converter.TillageLevelsConverter;
import converter.TillageLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/tillageLevels/")
public class TillageLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of TillageLevelsResource */
    public TillageLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of TillageLevel instance in XML format.
     *
     * @return an instance of TillageLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TillageLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM TillageLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TillageLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of TillageLevel using XML as the input format.
     *
     * @param data an TillageLevelConverter entity that is deserialized from an XML stream
     * @return an instance of TillageLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(TillageLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            TillageLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getTillageLevelPK().getExpId() + "," + entity.getTillageLevelPK().getTi() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of TillageLevelResource used for entity navigation.
     *
     * @return an instance of TillageLevelResource
     */
    @Path("{expId},{ti}/")
    public TillageLevelResource getTillageLevelsResource(@PathParam("expId") Integer id1, @PathParam("ti") Integer id2) {
        TillageLevelResource tillageLevelsResource = resourceContext.getResource(TillageLevelResource.class);
        tillageLevelsResource.setId1(id1);
        tillageLevelsResource.setId2(id2);
        return tillageLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of TillageLevel instances
     */
    protected Collection<TillageLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(TillageLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            TillageLevel oldEntity = value.getTillageLevels();
            value.setTillageLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
