/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.OrganicMaterialLevels;
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
import converter.OrganicMaterialLevelssConverter;
import converter.OrganicMaterialLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/organicMaterialLevelss/")
public class OrganicMaterialLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of OrganicMaterialLevelssResource */
    public OrganicMaterialLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of OrganicMaterialLevels instance in XML format.
     *
     * @return an instance of OrganicMaterialLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public OrganicMaterialLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM OrganicMaterialLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new OrganicMaterialLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of OrganicMaterialLevels using XML as the input format.
     *
     * @param data an OrganicMaterialLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of OrganicMaterialLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(OrganicMaterialLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            OrganicMaterialLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getOrganicMaterialLevelsPK().getExpId() + "," + entity.getOrganicMaterialLevelsPK().getOm() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of OrganicMaterialLevelsResource used for entity navigation.
     *
     * @return an instance of OrganicMaterialLevelsResource
     */
    @Path("{expId},{om}/")
    public OrganicMaterialLevelsResource getOrganicMaterialLevelsResource(@PathParam("expId") Integer id1, @PathParam("om") Integer id2) {
        OrganicMaterialLevelsResource organicMaterialLevelsResource = resourceContext.getResource(OrganicMaterialLevelsResource.class);
        organicMaterialLevelsResource.setId1(id1);
        organicMaterialLevelsResource.setId2(id2);
        return organicMaterialLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of OrganicMaterialLevels instances
     */
    protected Collection<OrganicMaterialLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(OrganicMaterialLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            OrganicMaterialLevels oldEntity = value.getOrganicMaterialLevels();
            value.setOrganicMaterialLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
