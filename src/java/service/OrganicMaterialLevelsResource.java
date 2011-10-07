package service;

import beans.OrganicMaterialLevel;
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
import converter.OrganicMaterialLevelsConverter;
import converter.OrganicMaterialLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/organicMaterialLevels/")
public class OrganicMaterialLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of OrganicMaterialLevelsResource */
    public OrganicMaterialLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of OrganicMaterialLevel instance in XML format.
     *
     * @return an instance of OrganicMaterialLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public OrganicMaterialLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM OrganicMaterialLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new OrganicMaterialLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of OrganicMaterialLevel using XML as the input format.
     *
     * @param data an OrganicMaterialLevelConverter entity that is deserialized from an XML stream
     * @return an instance of OrganicMaterialLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(OrganicMaterialLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            OrganicMaterialLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getOrganicMaterialLevelPK().getExpId() + "," + entity.getOrganicMaterialLevelPK().getOm() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of OrganicMaterialLevelResource used for entity navigation.
     *
     * @return an instance of OrganicMaterialLevelResource
     */
    @Path("{expId},{om}/")
    public OrganicMaterialLevelResource getOrganicMaterialLevelsResource(@PathParam("expId") Integer id1, @PathParam("om") Integer id2) {
        OrganicMaterialLevelResource organicMaterialLevelsResource = resourceContext.getResource(OrganicMaterialLevelResource.class);
        organicMaterialLevelsResource.setId1(id1);
        organicMaterialLevelsResource.setId2(id2);
        return organicMaterialLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of OrganicMaterialLevel instances
     */
    protected Collection<OrganicMaterialLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(OrganicMaterialLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            OrganicMaterialLevel oldEntity = value.getOrganicMaterialLevel();
            value.setOrganicMaterialLevel(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
