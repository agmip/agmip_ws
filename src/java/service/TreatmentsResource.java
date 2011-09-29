/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Treatments;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import beans.Users;
import beans.MulchLevels;
import beans.EnvironModifLevels;
import beans.FertilizerLevelsPK;
import beans.GenotypesPK;
import beans.SoilAnalysesLevels;
import beans.HarvestLevelsPK;
import beans.IrrigationLevelsPK;
import beans.InitialConditionLevels;
import beans.TillageLevelsPK;
import beans.TillageLevels;
import beans.HarvestLevels;
import beans.ChemicalLevels;
import beans.OrganicMaterialLevelsPK;
import beans.ExperimentalDescrips;
import beans.SoilAnalysesLevelsPK;
import beans.IrrigationLevels;
import beans.Fields;
import beans.FieldsPK;
import beans.EnvironModifLevelsPK;
import beans.PlantingsPK;
import beans.OrganicMaterialLevels;
import beans.Genotypes;
import beans.InitialConditionLevelsPK;
import beans.FertilizerLevels;
import beans.Plantings;
import beans.ChemicalLevelsPK;
import beans.MulchLevelsPK;
import converter.TreatmentsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
public class TreatmentsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    protected Integer id6;
    protected Integer id5;
    protected Integer id4;
    protected Integer id3;
    protected Integer id2;
    protected Integer id1;

    /** Creates a new instance of TreatmentsResource */
    public TreatmentsResource() {
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }

    public void setId4(Integer id4) {
        this.id4 = id4;
    }

    public void setId5(Integer id5) {
        this.id5 = id5;
    }

    public void setId6(Integer id6) {
        this.id6 = id6;
    }

    /**
     * Get method for retrieving an instance of Treatments identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of TreatmentsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TreatmentsConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TreatmentsConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of Treatments identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an TreatmentsConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(TreatmentsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            updateEntity(getEntity(), data.resolveEntity(em));
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Delete method for deleting an instance of Treatments identified by id.
     *
     * @param id identifier for the entity
     */
    @DELETE
    public void delete() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            deleteEntity(getEntity());
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns an instance of Treatments identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Treatments
     */
    protected Treatments getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            beans.TreatmentsPK id = new beans.TreatmentsPK(id1, id2, id3, id4, id5, id6);
            return (Treatments) em.createQuery("SELECT e FROM Treatments e where e.treatmentsPK = :treatmentsPK").setParameter("treatmentsPK", id).getSingleResult();
        } catch (NoResultException ex) {
            throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
        }
    }

    /**
     * Updates entity using data from newEntity.
     *
     * @param entity the entity to update
     * @param newEntity the entity containing the new data
     * @return the updated entity
     */
    private Treatments updateEntity(Treatments entity, Treatments newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Users updateUserId = entity.getUpdateUserId();
        Users updateUserIdNew = newEntity.getUpdateUserId();
        Plantings plantings = entity.getPlantings();
        Plantings plantingsNew = newEntity.getPlantings();
        ChemicalLevels chemicalLevels = entity.getChemicalLevels();
        ChemicalLevels chemicalLevelsNew = newEntity.getChemicalLevels();
        EnvironModifLevels environModifLevels = entity.getEnvironModifLevels();
        EnvironModifLevels environModifLevelsNew = newEntity.getEnvironModifLevels();
        ExperimentalDescrips experimentalDescrips = entity.getExperimentalDescrips();
        ExperimentalDescrips experimentalDescripsNew = newEntity.getExperimentalDescrips();
        FertilizerLevels fertilizerLevels = entity.getFertilizerLevels();
        FertilizerLevels fertilizerLevelsNew = newEntity.getFertilizerLevels();
        Fields fields = entity.getFields();
        Fields fieldsNew = newEntity.getFields();
        Genotypes genotypes = entity.getGenotypes();
        Genotypes genotypesNew = newEntity.getGenotypes();
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        HarvestLevels harvestLevelsNew = newEntity.getHarvestLevels();
        InitialConditionLevels initialConditionLevels = entity.getInitialConditionLevels();
        InitialConditionLevels initialConditionLevelsNew = newEntity.getInitialConditionLevels();
        IrrigationLevels irrigationLevels = entity.getIrrigationLevels();
        IrrigationLevels irrigationLevelsNew = newEntity.getIrrigationLevels();
        MulchLevels mulchLevels = entity.getMulchLevels();
        MulchLevels mulchLevelsNew = newEntity.getMulchLevels();
        OrganicMaterialLevels organicMaterialLevels = entity.getOrganicMaterialLevels();
        OrganicMaterialLevels organicMaterialLevelsNew = newEntity.getOrganicMaterialLevels();
        SoilAnalysesLevels soilAnalysesLevels = entity.getSoilAnalysesLevels();
        SoilAnalysesLevels soilAnalysesLevelsNew = newEntity.getSoilAnalysesLevels();
        TillageLevels tillageLevels = entity.getTillageLevels();
        TillageLevels tillageLevelsNew = newEntity.getTillageLevels();
        entity = em.merge(newEntity);
        if (updateUserId != null && !updateUserId.equals(updateUserIdNew)) {
            updateUserId.getTreatmentsCollection().remove(entity);
        }
        if (updateUserIdNew != null && !updateUserIdNew.equals(updateUserId)) {
            updateUserIdNew.getTreatmentsCollection().add(entity);
        }
        if (plantings != null && !plantings.equals(plantingsNew)) {
            plantings.getTreatmentsCollection().remove(entity);
        }
        if (plantingsNew != null && !plantingsNew.equals(plantings)) {
            plantingsNew.getTreatmentsCollection().add(entity);
        }
        if (chemicalLevels != null && !chemicalLevels.equals(chemicalLevelsNew)) {
            chemicalLevels.getTreatmentsCollection().remove(entity);
        }
        if (chemicalLevelsNew != null && !chemicalLevelsNew.equals(chemicalLevels)) {
            chemicalLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (environModifLevels != null && !environModifLevels.equals(environModifLevelsNew)) {
            environModifLevels.getTreatmentsCollection().remove(entity);
        }
        if (environModifLevelsNew != null && !environModifLevelsNew.equals(environModifLevels)) {
            environModifLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (experimentalDescrips != null && !experimentalDescrips.equals(experimentalDescripsNew)) {
            experimentalDescrips.getTreatmentsCollection().remove(entity);
        }
        if (experimentalDescripsNew != null && !experimentalDescripsNew.equals(experimentalDescrips)) {
            experimentalDescripsNew.getTreatmentsCollection().add(entity);
        }
        if (fertilizerLevels != null && !fertilizerLevels.equals(fertilizerLevelsNew)) {
            fertilizerLevels.getTreatmentsCollection().remove(entity);
        }
        if (fertilizerLevelsNew != null && !fertilizerLevelsNew.equals(fertilizerLevels)) {
            fertilizerLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (fields != null && !fields.equals(fieldsNew)) {
            fields.getTreatmentsCollection().remove(entity);
        }
        if (fieldsNew != null && !fieldsNew.equals(fields)) {
            fieldsNew.getTreatmentsCollection().add(entity);
        }
        if (genotypes != null && !genotypes.equals(genotypesNew)) {
            genotypes.getTreatmentsCollection().remove(entity);
        }
        if (genotypesNew != null && !genotypesNew.equals(genotypes)) {
            genotypesNew.getTreatmentsCollection().add(entity);
        }
        if (harvestLevels != null && !harvestLevels.equals(harvestLevelsNew)) {
            harvestLevels.getTreatmentsCollection().remove(entity);
        }
        if (harvestLevelsNew != null && !harvestLevelsNew.equals(harvestLevels)) {
            harvestLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (initialConditionLevels != null && !initialConditionLevels.equals(initialConditionLevelsNew)) {
            initialConditionLevels.getTreatmentsCollection().remove(entity);
        }
        if (initialConditionLevelsNew != null && !initialConditionLevelsNew.equals(initialConditionLevels)) {
            initialConditionLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (irrigationLevels != null && !irrigationLevels.equals(irrigationLevelsNew)) {
            irrigationLevels.getTreatmentsCollection().remove(entity);
        }
        if (irrigationLevelsNew != null && !irrigationLevelsNew.equals(irrigationLevels)) {
            irrigationLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (mulchLevels != null && !mulchLevels.equals(mulchLevelsNew)) {
            mulchLevels.getTreatmentsCollection().remove(entity);
        }
        if (mulchLevelsNew != null && !mulchLevelsNew.equals(mulchLevels)) {
            mulchLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (organicMaterialLevels != null && !organicMaterialLevels.equals(organicMaterialLevelsNew)) {
            organicMaterialLevels.getTreatmentsCollection().remove(entity);
        }
        if (organicMaterialLevelsNew != null && !organicMaterialLevelsNew.equals(organicMaterialLevels)) {
            organicMaterialLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (soilAnalysesLevels != null && !soilAnalysesLevels.equals(soilAnalysesLevelsNew)) {
            soilAnalysesLevels.getTreatmentsCollection().remove(entity);
        }
        if (soilAnalysesLevelsNew != null && !soilAnalysesLevelsNew.equals(soilAnalysesLevels)) {
            soilAnalysesLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (tillageLevels != null && !tillageLevels.equals(tillageLevelsNew)) {
            tillageLevels.getTreatmentsCollection().remove(entity);
        }
        if (tillageLevelsNew != null && !tillageLevelsNew.equals(tillageLevels)) {
            tillageLevelsNew.getTreatmentsCollection().add(entity);
        }
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    private void deleteEntity(Treatments entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Users updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            updateUserId.getTreatmentsCollection().remove(entity);
        }
        Plantings plantings = entity.getPlantings();
        if (plantings != null) {
            plantings.getTreatmentsCollection().remove(entity);
        }
        ChemicalLevels chemicalLevels = entity.getChemicalLevels();
        if (chemicalLevels != null) {
            chemicalLevels.getTreatmentsCollection().remove(entity);
        }
        EnvironModifLevels environModifLevels = entity.getEnvironModifLevels();
        if (environModifLevels != null) {
            environModifLevels.getTreatmentsCollection().remove(entity);
        }
        ExperimentalDescrips experimentalDescrips = entity.getExperimentalDescrips();
        if (experimentalDescrips != null) {
            experimentalDescrips.getTreatmentsCollection().remove(entity);
        }
        FertilizerLevels fertilizerLevels = entity.getFertilizerLevels();
        if (fertilizerLevels != null) {
            fertilizerLevels.getTreatmentsCollection().remove(entity);
        }
        Fields fields = entity.getFields();
        if (fields != null) {
            fields.getTreatmentsCollection().remove(entity);
        }
        Genotypes genotypes = entity.getGenotypes();
        if (genotypes != null) {
            genotypes.getTreatmentsCollection().remove(entity);
        }
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        if (harvestLevels != null) {
            harvestLevels.getTreatmentsCollection().remove(entity);
        }
        InitialConditionLevels initialConditionLevels = entity.getInitialConditionLevels();
        if (initialConditionLevels != null) {
            initialConditionLevels.getTreatmentsCollection().remove(entity);
        }
        IrrigationLevels irrigationLevels = entity.getIrrigationLevels();
        if (irrigationLevels != null) {
            irrigationLevels.getTreatmentsCollection().remove(entity);
        }
        MulchLevels mulchLevels = entity.getMulchLevels();
        if (mulchLevels != null) {
            mulchLevels.getTreatmentsCollection().remove(entity);
        }
        OrganicMaterialLevels organicMaterialLevels = entity.getOrganicMaterialLevels();
        if (organicMaterialLevels != null) {
            organicMaterialLevels.getTreatmentsCollection().remove(entity);
        }
        SoilAnalysesLevels soilAnalysesLevels = entity.getSoilAnalysesLevels();
        if (soilAnalysesLevels != null) {
            soilAnalysesLevels.getTreatmentsCollection().remove(entity);
        }
        TillageLevels tillageLevels = entity.getTillageLevels();
        if (tillageLevels != null) {
            tillageLevels.getTreatmentsCollection().remove(entity);
        }
        em.remove(entity);
    }

    /**
     * Returns a dynamic instance of UsersResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of UsersResource
     */
    @Path("updateUserId/")
    public UsersResource getUpdateUserIdResource() {
        UpdateUserIdResourceSub updateUserIdResourceSub = resourceContext.getResource(UpdateUserIdResourceSub.class);
        updateUserIdResourceSub.setParent(getEntity());
        return updateUserIdResourceSub;
    }

    /**
     * Returns a dynamic instance of PlantingsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of PlantingsResource
     */
    @Path("plantings/")
    public PlantingsResource getPlantingsResource() {
        PlantingsResourceSub plantingsResourceSub = resourceContext.getResource(PlantingsResourceSub.class);
        plantingsResourceSub.setParent(getEntity());
        return plantingsResourceSub;
    }

    /**
     * Returns a dynamic instance of ChemicalLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of ChemicalLevelsResource
     */
    @Path("chemicalLevels/")
    public ChemicalLevelsResource getChemicalLevelsResource() {
        ChemicalLevelsResourceSub chemicalLevelsResourceSub = resourceContext.getResource(ChemicalLevelsResourceSub.class);
        chemicalLevelsResourceSub.setParent(getEntity());
        return chemicalLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of EnvironModifLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of EnvironModifLevelsResource
     */
    @Path("environModifLevels/")
    public EnvironModifLevelsResource getEnvironModifLevelsResource() {
        EnvironModifLevelsResourceSub environModifLevelsResourceSub = resourceContext.getResource(EnvironModifLevelsResourceSub.class);
        environModifLevelsResourceSub.setParent(getEntity());
        return environModifLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of ExperimentalDescripsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of ExperimentalDescripsResource
     */
    @Path("experimentalDescrips/")
    public ExperimentalDescripsResource getExperimentalDescripsResource() {
        ExperimentalDescripsResourceSub experimentalDescripsResourceSub = resourceContext.getResource(ExperimentalDescripsResourceSub.class);
        experimentalDescripsResourceSub.setParent(getEntity());
        return experimentalDescripsResourceSub;
    }

    /**
     * Returns a dynamic instance of FertilizerLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of FertilizerLevelsResource
     */
    @Path("fertilizerLevels/")
    public FertilizerLevelsResource getFertilizerLevelsResource() {
        FertilizerLevelsResourceSub fertilizerLevelsResourceSub = resourceContext.getResource(FertilizerLevelsResourceSub.class);
        fertilizerLevelsResourceSub.setParent(getEntity());
        return fertilizerLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of FieldsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of FieldsResource
     */
    @Path("fields/")
    public FieldsResource getFieldsResource() {
        FieldsResourceSub fieldsResourceSub = resourceContext.getResource(FieldsResourceSub.class);
        fieldsResourceSub.setParent(getEntity());
        return fieldsResourceSub;
    }

    /**
     * Returns a dynamic instance of GenotypesResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of GenotypesResource
     */
    @Path("genotypes/")
    public GenotypesResource getGenotypesResource() {
        GenotypesResourceSub genotypesResourceSub = resourceContext.getResource(GenotypesResourceSub.class);
        genotypesResourceSub.setParent(getEntity());
        return genotypesResourceSub;
    }

    /**
     * Returns a dynamic instance of HarvestLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of HarvestLevelsResource
     */
    @Path("harvestLevels/")
    public HarvestLevelsResource getHarvestLevelsResource() {
        HarvestLevelsResourceSub harvestLevelsResourceSub = resourceContext.getResource(HarvestLevelsResourceSub.class);
        harvestLevelsResourceSub.setParent(getEntity());
        return harvestLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of InitialConditionLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of InitialConditionLevelsResource
     */
    @Path("initialConditionLevels/")
    public InitialConditionLevelsResource getInitialConditionLevelsResource() {
        InitialConditionLevelsResourceSub initialConditionLevelsResourceSub = resourceContext.getResource(InitialConditionLevelsResourceSub.class);
        initialConditionLevelsResourceSub.setParent(getEntity());
        return initialConditionLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of IrrigationLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of IrrigationLevelsResource
     */
    @Path("irrigationLevels/")
    public IrrigationLevelsResource getIrrigationLevelsResource() {
        IrrigationLevelsResourceSub irrigationLevelsResourceSub = resourceContext.getResource(IrrigationLevelsResourceSub.class);
        irrigationLevelsResourceSub.setParent(getEntity());
        return irrigationLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of MulchLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of MulchLevelsResource
     */
    @Path("mulchLevels/")
    public MulchLevelsResource getMulchLevelsResource() {
        MulchLevelsResourceSub mulchLevelsResourceSub = resourceContext.getResource(MulchLevelsResourceSub.class);
        mulchLevelsResourceSub.setParent(getEntity());
        return mulchLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of OrganicMaterialLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of OrganicMaterialLevelsResource
     */
    @Path("organicMaterialLevels/")
    public OrganicMaterialLevelsResource getOrganicMaterialLevelsResource() {
        OrganicMaterialLevelsResourceSub organicMaterialLevelsResourceSub = resourceContext.getResource(OrganicMaterialLevelsResourceSub.class);
        organicMaterialLevelsResourceSub.setParent(getEntity());
        return organicMaterialLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of SoilAnalysesLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of SoilAnalysesLevelsResource
     */
    @Path("soilAnalysesLevels/")
    public SoilAnalysesLevelsResource getSoilAnalysesLevelsResource() {
        SoilAnalysesLevelsResourceSub soilAnalysesLevelsResourceSub = resourceContext.getResource(SoilAnalysesLevelsResourceSub.class);
        soilAnalysesLevelsResourceSub.setParent(getEntity());
        return soilAnalysesLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of TillageLevelsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of TillageLevelsResource
     */
    @Path("tillageLevels/")
    public TillageLevelsResource getTillageLevelsResource() {
        TillageLevelsResourceSub tillageLevelsResourceSub = resourceContext.getResource(TillageLevelsResourceSub.class);
        tillageLevelsResourceSub.setParent(getEntity());
        return tillageLevelsResourceSub;
    }

    public static class UpdateUserIdResourceSub extends UsersResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected Users getEntity() {
            Users entity = parent.getUpdateUserId();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class PlantingsResourceSub extends PlantingsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected Plantings getEntity() {
            Plantings entity = parent.getPlantings();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class ChemicalLevelsResourceSub extends ChemicalLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected ChemicalLevels getEntity() {
            ChemicalLevels entity = parent.getChemicalLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class EnvironModifLevelsResourceSub extends EnvironModifLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected EnvironModifLevels getEntity() {
            EnvironModifLevels entity = parent.getEnvironModifLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class ExperimentalDescripsResourceSub extends ExperimentalDescripsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected ExperimentalDescrips getEntity() {
            ExperimentalDescrips entity = parent.getExperimentalDescrips();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class FertilizerLevelsResourceSub extends FertilizerLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected FertilizerLevels getEntity() {
            FertilizerLevels entity = parent.getFertilizerLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class FieldsResourceSub extends FieldsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected Fields getEntity() {
            Fields entity = parent.getFields();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class GenotypesResourceSub extends GenotypesResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected Genotypes getEntity() {
            Genotypes entity = parent.getGenotypes();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class HarvestLevelsResourceSub extends HarvestLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected HarvestLevels getEntity() {
            HarvestLevels entity = parent.getHarvestLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class InitialConditionLevelsResourceSub extends InitialConditionLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected InitialConditionLevels getEntity() {
            InitialConditionLevels entity = parent.getInitialConditionLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class IrrigationLevelsResourceSub extends IrrigationLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected IrrigationLevels getEntity() {
            IrrigationLevels entity = parent.getIrrigationLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class MulchLevelsResourceSub extends MulchLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected MulchLevels getEntity() {
            MulchLevels entity = parent.getMulchLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class OrganicMaterialLevelsResourceSub extends OrganicMaterialLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected OrganicMaterialLevels getEntity() {
            OrganicMaterialLevels entity = parent.getOrganicMaterialLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class SoilAnalysesLevelsResourceSub extends SoilAnalysesLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected SoilAnalysesLevels getEntity() {
            SoilAnalysesLevels entity = parent.getSoilAnalysesLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class TillageLevelsResourceSub extends TillageLevelsResource {

        private Treatments parent;

        public void setParent(Treatments parent) {
            this.parent = parent;
        }

        @Override
        protected TillageLevels getEntity() {
            TillageLevels entity = parent.getTillageLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }
}
