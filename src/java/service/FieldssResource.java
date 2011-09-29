/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Fields;
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
import converter.FieldssConverter;
import converter.FieldsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/fieldss/")
public class FieldssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of FieldssResource */
    public FieldssResource() {
    }

    /**
     * Get method for retrieving a collection of Fields instance in XML format.
     *
     * @return an instance of FieldssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public FieldssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Fields e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new FieldssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Fields using XML as the input format.
     *
     * @param data an FieldsConverter entity that is deserialized from an XML stream
     * @return an instance of FieldsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(FieldsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Fields entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getFieldsPK().getExpId() + "," + entity.getFieldsPK().getFl() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of FieldsResource used for entity navigation.
     *
     * @return an instance of FieldsResource
     */
    @Path("{expId},{fl}/")
    public FieldsResource getFieldsResource(@PathParam("expId") Integer id1, @PathParam("fl") Integer id2) {
        FieldsResource fieldsResource = resourceContext.getResource(FieldsResource.class);
        fieldsResource.setId1(id1);
        fieldsResource.setId2(id2);
        return fieldsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Fields instances
     */
    protected Collection<Fields> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Fields entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            Fields oldEntity = value.getFields();
            value.setFields(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
