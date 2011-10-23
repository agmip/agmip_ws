package service;

import beans.ChemicalLevel;
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
import converter.ChemicalLevelsConverter;
import converter.ChemicalLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/chemicalLevels/")
public class ChemicalLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of ChemicalLevelsResource */
    public ChemicalLevelsResource() {
    }

    /**
     * Get method for retrieving a collection of ChemicalLevel instance in XML format.
     *
     * @return an instance of ChemicalLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public ChemicalLevelsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM ChemicalLevel e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ChemicalLevelsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of ChemicalLevel using XML as the input format.
     *
     * @param data an ChemicalLevelConverter entity that is deserialized from an XML stream
     * @return an instance of ChemicalLevelConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(ChemicalLevelConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            ChemicalLevel entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getChemicalLevelPK().getExpId() + "," + entity.getChemicalLevelPK().getCh() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of ChemicalLevelResource used for entity navigation.
     *
     * @return an instance of ChemicalLevelResource
     */
    @Path("{expId},{ch}/")
    public ChemicalLevelResource getChemicalLevelsResource(@PathParam("expId") Integer id1, @PathParam("ch") Integer id2) {
        ChemicalLevelResource chemicalLevelsResource = resourceContext.getResource(ChemicalLevelResource.class);
        chemicalLevelsResource.setId1(id1);
        chemicalLevelsResource.setId2(id2);
        return chemicalLevelsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of ChemicalLevel instances
     */
    protected Collection<ChemicalLevel> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(ChemicalLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            ChemicalLevel oldEntity = value.getChemicalLevels();
            value.setChemicalLevels(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
