/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Treatments;
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
import beans.Users;
import beans.IrrigationLevels;
import beans.Fields;
import beans.MulchLevels;
import beans.EnvironModifLevels;
import beans.OrganicMaterialLevels;
import beans.Genotypes;
import beans.SoilAnalysesLevels;
import beans.InitialConditionLevels;
import beans.TillageLevels;
import beans.HarvestLevels;
import beans.ChemicalLevels;
import beans.FertilizerLevels;
import beans.Plantings;
import beans.ExperimentalDescrips;
import converter.TreatmentssConverter;
import converter.TreatmentsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
@Path("/treatmentss/")
public class TreatmentssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of TreatmentssResource */
    public TreatmentssResource() {
    }

    /**
     * Get method for retrieving a collection of Treatments instance in XML format.
     *
     * @return an instance of TreatmentssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TreatmentssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Treatments e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TreatmentssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Treatments using XML as the input format.
     *
     * @param data an TreatmentsConverter entity that is deserialized from an XML stream
     * @return an instance of TreatmentsConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(TreatmentsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Treatments entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getTreatmentsPK().getExpId() + "," + entity.getTreatmentsPK().getTrno() + "," + entity.getTreatmentsPK().getRp() + "," + entity.getTreatmentsPK().getSq() + "," + entity.getTreatmentsPK().getOp() + "," + entity.getTreatmentsPK().getCo() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of TreatmentsResource used for entity navigation.
     *
     * @return an instance of TreatmentsResource
     */
    @Path("{expId},{trno},{rp},{sq},{op},{co}/")
    public TreatmentsResource getTreatmentsResource(@PathParam("expId") Integer id1, @PathParam("trno") Integer id2, @PathParam("rp") Integer id3, @PathParam("sq") Integer id4, @PathParam("op") Integer id5, @PathParam("co") Integer id6) {
        TreatmentsResource treatmentsResource = resourceContext.getResource(TreatmentsResource.class);
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
     * @return a collection of Treatments instances
     */
    protected Collection<Treatments> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Treatments entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        Users updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            updateUserId.getTreatmentsCollection().add(entity);
        }
        Plantings plantings = entity.getPlantings();
        if (plantings != null) {
            plantings.getTreatmentsCollection().add(entity);
        }
        ChemicalLevels chemicalLevels = entity.getChemicalLevels();
        if (chemicalLevels != null) {
            chemicalLevels.getTreatmentsCollection().add(entity);
        }
        EnvironModifLevels environModifLevels = entity.getEnvironModifLevels();
        if (environModifLevels != null) {
            environModifLevels.getTreatmentsCollection().add(entity);
        }
        ExperimentalDescrips experimentalDescrips = entity.getExperimentalDescrips();
        if (experimentalDescrips != null) {
            experimentalDescrips.getTreatmentsCollection().add(entity);
        }
        FertilizerLevels fertilizerLevels = entity.getFertilizerLevels();
        if (fertilizerLevels != null) {
            fertilizerLevels.getTreatmentsCollection().add(entity);
        }
        Fields fields = entity.getFields();
        if (fields != null) {
            fields.getTreatmentsCollection().add(entity);
        }
        Genotypes genotypes = entity.getGenotypes();
        if (genotypes != null) {
            genotypes.getTreatmentsCollection().add(entity);
        }
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        if (harvestLevels != null) {
            harvestLevels.getTreatmentsCollection().add(entity);
        }
        InitialConditionLevels initialConditionLevels = entity.getInitialConditionLevels();
        if (initialConditionLevels != null) {
            initialConditionLevels.getTreatmentsCollection().add(entity);
        }
        IrrigationLevels irrigationLevels = entity.getIrrigationLevels();
        if (irrigationLevels != null) {
            irrigationLevels.getTreatmentsCollection().add(entity);
        }
        MulchLevels mulchLevels = entity.getMulchLevels();
        if (mulchLevels != null) {
            mulchLevels.getTreatmentsCollection().add(entity);
        }
        OrganicMaterialLevels organicMaterialLevels = entity.getOrganicMaterialLevels();
        if (organicMaterialLevels != null) {
            organicMaterialLevels.getTreatmentsCollection().add(entity);
        }
        SoilAnalysesLevels soilAnalysesLevels = entity.getSoilAnalysesLevels();
        if (soilAnalysesLevels != null) {
            soilAnalysesLevels.getTreatmentsCollection().add(entity);
        }
        TillageLevels tillageLevels = entity.getTillageLevels();
        if (tillageLevels != null) {
            tillageLevels.getTreatmentsCollection().add(entity);
        }
    }
}
