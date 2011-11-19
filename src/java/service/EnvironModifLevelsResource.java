package service;

import beans.EnvironModifLevel;
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
import converter.EnvironModifLevelConverter;
import converter.EnvironModifLevelConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.EnvironModifLevelsConverter;

/**
 *
 * @author fonini
 */
@Path("/environModifLevels/")
public class EnvironModifLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of EnvironModifLevelsResource */
    public EnvironModifLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of EnvironModifLevel instance in XML format.
     *
     * @return an instance of EnvironModifLevelConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public EnvironModifLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM EnvironModifLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new EnvironModifLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of EnvironModifLevel using XML as the input format.
     *
     * @param data an EnvironModifLevelConverter entity that is deserialized from an XML stream
     * @return an instance of EnvironModifLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(EnvironModifLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            EnvironModifLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getEnvironModifLevelPK().getExpId() + "," + entity.getEnvironModifLevelPK().getEm() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of EnvironModifLevelResource used for entity navigation.
     *
     * @return an instance of EnvironModifLevelResource
     */
    @Path("{expId},{em}/")
    public EnvironModifLevelResource getEnvironModifLevelsResource(@PathParam("expId") Integer id1, @PathParam("em") Integer id2) {
        EnvironModifLevelResource environModifLevelsResource = resourceContext.getResource(EnvironModifLevelResource.class);
        environModifLevelsResource.setId1(id1);
        environModifLevelsResource.setId2(id2);
        return environModifLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of EnvironModifLevel instances
     */
    protected Collection<EnvironModifLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(EnvironModifLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            EnvironModifLevel oldEntity = value.getEnvironModifLevels();
            value.setEnvironModifLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
