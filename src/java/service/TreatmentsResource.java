package service;

import beans.Treatment;
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
import beans.User;
import beans.IrrigationLevel;
import beans.Field;
import beans.MulchLevel;
import beans.EnvironModifLevel;
import beans.OrganicMaterialLevel;
import beans.Genotype;
import beans.SoilAnalysesLevel;
import beans.InitialConditionLevel;
import beans.TillageLevel;
import beans.HarvestLevel;
import beans.ChemicalLevel;
import beans.FertilizerLevel;
import beans.Planting;
import beans.Experiment;
import converter.TreatmentsConverter;
import converter.TreatmentConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/treatments/")
public class TreatmentsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of TreatmentsResource */
    public TreatmentsResource() {
    }

    /**
     * Get method for retrieving a collection of Treatment instance in XML format.
     *
     * @return an instance of TreatmentsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TreatmentsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Treatment e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TreatmentsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Treatment using XML as the input format.
     *
     * @param data an TreatmentConverter entity that is deserialized from an XML stream
     * @return an instance of TreatmentConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(TreatmentConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Treatment entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getTreatmentPK().getExpId() + "," + entity.getTreatmentPK().getTrno() + "," + entity.getTreatmentPK().getRp() + "," + entity.getTreatmentPK().getSq() + "," + entity.getTreatmentPK().getOp() + "," + entity.getTreatmentPK().getCo() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of TreatmentResource used for entity navigation.
     *
     * @return an instance of TreatmentResource
     */
    @Path("{expId},{trno},{rp},{sq},{op},{co}/")
    public TreatmentResource getTreatmentsResource(@PathParam("expId") Integer id1, @PathParam("trno") Integer id2, @PathParam("rp") Integer id3, @PathParam("sq") Integer id4, @PathParam("op") Integer id5, @PathParam("co") Integer id6) {
        TreatmentResource treatmentsResource = resourceContext.getResource(TreatmentResource.class);
        treatmentsResource.setId1(id1);
        treatmentsResource.setId2(id2);
        treatmentsResource.setId3(id3);
        treatmentsResource.setId4(id4);
        treatmentsResource.setId5(id5);
        treatmentsResource.setId6(id6);
        return treatmentsResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Treatment instances
     */
    protected Collection<Treatment> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Treatment entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        User updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            updateUserId.getTreatmentsCollection().add(entity);
        }
        Planting planting = entity.getPlanting();
        if (planting != null) {
            planting.getTreatmentsCollection().add(entity);
        }
        ChemicalLevel chemicalLevels = entity.getChemicalLevel();
        if (chemicalLevels != null) {
            chemicalLevels.getTreatmentsCollection().add(entity);
        }
        EnvironModifLevel environModifLevels = entity.getEnvironModifLevel();
        if (environModifLevels != null) {
            environModifLevels.getTreatmentsCollection().add(entity);
        }
        Experiment experimentalDescrips = entity.getExperiment();
        if (experimentalDescrips != null) {
            experimentalDescrips.getTreatmentsCollection().add(entity);
        }
        FertilizerLevel fertilizerLevels = entity.getFertilizerLevel();
        if (fertilizerLevels != null) {
            fertilizerLevels.getTreatmentsCollection().add(entity);
        }
        Field fields = entity.getField();
        if (fields != null) {
            fields.getTreatmentsCollection().add(entity);
        }
        Genotype genotype = entity.getGenotype();
        if (genotype != null) {
            genotype.getTreatmentsCollection().add(entity);
        }
        HarvestLevel harvestLevels = entity.getHarvestLevel();
        if (harvestLevels != null) {
            harvestLevels.getTreatmentsCollection().add(entity);
        }
        InitialConditionLevel initialConditionLevel = entity.getInitialConditionLevel();
        if (initialConditionLevel != null) {
            initialConditionLevel.getTreatmentsCollection().add(entity);
        }
        IrrigationLevel irrigationLevel = entity.getIrrigationLevel();
        if (irrigationLevel != null) {
            irrigationLevel.getTreatmentsCollection().add(entity);
        }
        MulchLevel mulchLevels = entity.getMulchLevel();
        if (mulchLevels != null) {
            mulchLevels.getTreatmentsCollection().add(entity);
        }
        OrganicMaterialLevel organicMaterialLevel = entity.getOrganicMaterialLevel();
        if (organicMaterialLevel != null) {
            organicMaterialLevel.getTreatmentsCollection().add(entity);
        }
        SoilAnalysesLevel soilAnalysesLevel = entity.getSoilAnalysesLevel();
        if (soilAnalysesLevel != null) {
            soilAnalysesLevel.getTreatmentsCollection().add(entity);
        }
        TillageLevel tillageLevels = entity.getTillageLevel();
        if (tillageLevels != null) {
            tillageLevels.getTreatmentsCollection().add(entity);
        }
    }
}
