/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.ExperimentalDescrips;
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
import converter.ExperimentalDescripssConverter;
import converter.ExperimentalDescripsConverter;
import com.sun.jersey.api.core.ResourceContext;
import converter.TreatmentsConverter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author wpavan
 */
@Path("/experimentalDescripss/")
public class ExperimentalDescripssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of ExperimentalDescripssResource */
    public ExperimentalDescripssResource() {
    }

    /**
     * Get method for retrieving a collection of ExperimentalDescrips instance in XML format.
     *
     * @return an instance of ExperimentalDescripssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public ExperimentalDescripssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM ExperimentalDescrips e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ExperimentalDescripssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of ExperimentalDescrips using XML as the input format.
     *
     * @param data an ExperimentalDescripsConverter entity that is deserialized from an XML stream
     * @return an instance of ExperimentalDescripsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(ExperimentalDescripsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
			EntityManager em = persistenceSvc.getEntityManager();
			persistenceSvc.beginTx();




			ExperimentalDescrips entity = data.resolveEntity(em);

			createEntity(data.resolveEntity(em));




			persistenceSvc.commitTx();



            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getExpId() + "/")).build();
        }
		catch(ConstraintViolationException e){
			System.out.println(e.getConstraintViolations());
			return null;
		}
		finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of ExperimentalDescripsResource used for entity navigation.
     *
     * @return an instance of ExperimentalDescripsResource
     */
    @Path("{expId}/")
    public ExperimentalDescripsResource getExperimentalDescripsResource(@PathParam("expId") Integer id) {
        ExperimentalDescripsResource experimentalDescripsResource = resourceContext.getResource(ExperimentalDescripsResource.class);
        experimentalDescripsResource.setId(id);
        return experimentalDescripsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of ExperimentalDescrips instances
     */
    protected Collection<ExperimentalDescrips> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(ExperimentalDescrips entity) {
        entity.setExpId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);

        for (Treatments value : entity.getTreatmentsCollection()) {
            ExperimentalDescrips oldEntity = value.getExperimentalDescrips();
            value.setExperimentalDescrips(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
