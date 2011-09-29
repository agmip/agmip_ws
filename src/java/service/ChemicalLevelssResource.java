/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.ChemicalLevels;
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
import converter.ChemicalLevelssConverter;
import converter.ChemicalLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/chemicalLevelss/")
public class ChemicalLevelssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of ChemicalLevelssResource */
    public ChemicalLevelssResource() {
    }

    /**
     * Get method for retrieving a collection of ChemicalLevels instance in XML format.
     *
     * @return an instance of ChemicalLevelssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public ChemicalLevelssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM ChemicalLevels e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ChemicalLevelssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of ChemicalLevels using XML as the input format.
     *
     * @param data an ChemicalLevelsConverter entity that is deserialized from an XML stream
     * @return an instance of ChemicalLevelsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(ChemicalLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            ChemicalLevels entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getChemicalLevelsPK().getExpId() + "," + entity.getChemicalLevelsPK().getCh() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of ChemicalLevelsResource used for entity navigation.
     *
     * @return an instance of ChemicalLevelsResource
     */
    @Path("{expId},{ch}/")
    public ChemicalLevelsResource getChemicalLevelsResource(@PathParam("expId") Integer id1, @PathParam("ch") Integer id2) {
        ChemicalLevelsResource chemicalLevelsResource = resourceContext.getResource(ChemicalLevelsResource.class);
        chemicalLevelsResource.setId1(id1);
        chemicalLevelsResource.setId2(id2);
        return chemicalLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of ChemicalLevels instances
     */
    protected Collection<ChemicalLevels> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(ChemicalLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatments value : entity.getTreatmentsCollection()) {
            ChemicalLevels oldEntity = value.getChemicalLevels();
            value.setChemicalLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
