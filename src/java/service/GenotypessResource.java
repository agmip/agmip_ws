/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Genotypes;
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
import converter.GenotypessConverter;
import converter.GenotypesConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/genotypess/")
public class GenotypessResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of GenotypessResource */
    public GenotypessResource() {
    }

    /**
     * Get method for retrieving a collection of Genotypes instance in XML format.
     *
     * @return an instance of GenotypessConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public GenotypessConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Genotypes e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new GenotypessConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Genotypes using XML as the input format.
     *
     * @param data an GenotypesConverter entity that is deserialized from an XML stream
     * @return an instance of GenotypesConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(GenotypesConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Genotypes entity = data.resolveEntity(em);
			System.out.println(entity.getCulName());
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getGenotypesPK().getExpId() + "," + entity.getGenotypesPK().getGe() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of GenotypesResource used for entity navigation.
     *
     * @return an instance of GenotypesResource
     */
    @Path("{expId},{ge}/")
    public GenotypesResource getGenotypesResource(@PathParam("expId") Integer id1, @PathParam("ge") Integer id2) {
        GenotypesResource genotypesResource = resourceContext.getResource(GenotypesResource.class);
        genotypesResource.setId1(id1);
        genotypesResource.setId2(id2);
        return genotypesResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Genotypes instances
     */
    protected Collection<Genotypes> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Genotypes entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            Genotypes oldEntity = value.getGenotypes();
            value.setGenotypes(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
