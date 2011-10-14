package service;

import beans.Treatment;
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
import beans.MulchLevel;
import beans.EnvironModifLevel;
import beans.SoilAnalysesLevel;
import beans.InitialConditionLevel;
import beans.TillageLevel;
import beans.HarvestLevels;
import beans.ChemicalLevel;
import beans.Experiment;
import beans.IrrigationLevel;
import beans.Field;
import beans.OrganicMaterialLevel;
import beans.Genotype;
import beans.FertilizerLevel;
import beans.Planting;
import converter.TreatmentConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class TreatmentResource {
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

    /** Creates a new instance of TreatmentResource */
    public TreatmentResource() {
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
     * Get method for retrieving an instance of Treatment identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of TreatmentConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public TreatmentConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new TreatmentConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of Treatment identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an TreatmentConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(TreatmentConverter data) {
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
     * Delete method for deleting an instance of Treatment identified by id.
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
     * Returns an instance of Treatment identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Treatment
     */
    protected Treatment getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            beans.TreatmentPK id = new beans.TreatmentPK(id1, id2, id3, id4, id5, id6);
            return (Treatment) em.createQuery("SELECT e FROM Treatment e where e.treatmentPK = :treatmentPK").setParameter("treatmentPK", id).getSingleResult();
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
    private Treatment updateEntity(Treatment entity, Treatment newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Users updateUserId = entity.getUpdateUserId();
        Users updateUserIdNew = newEntity.getUpdateUserId();
        Planting planting = entity.getPlanting();
        Planting plantingNew = newEntity.getPlanting();
        ChemicalLevel chemicalLevels = entity.getChemicalLevels();
        ChemicalLevel chemicalLevelsNew = newEntity.getChemicalLevels();
        EnvironModifLevel environModifLevels = entity.getEnvironModifLevels();
        EnvironModifLevel environModifLevelsNew = newEntity.getEnvironModifLevels();
        Experiment experimentalDescrips = entity.getExperiment();
        Experiment experimentalDescripsNew = newEntity.getExperiment();
        FertilizerLevel fertilizerLevels = entity.getFertilizerLevels();
        FertilizerLevel fertilizerLevelsNew = newEntity.getFertilizerLevels();
        Field fields = entity.getField();
        Field fieldsNew = newEntity.getField();
        Genotype genotype = entity.getGenotype();
        Genotype genotypeNew = newEntity.getGenotype();
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        HarvestLevels harvestLevelsNew = newEntity.getHarvestLevels();
        InitialConditionLevel initialConditionLevel = entity.getInitialConditionLevel();
        InitialConditionLevel initialConditionLevelNew = newEntity.getInitialConditionLevel();
        IrrigationLevel irrigationLevel = entity.getIrrigationLevel();
        IrrigationLevel irrigationLevelNew = newEntity.getIrrigationLevel();
        MulchLevel mulchLevels = entity.getMulchLevels();
        MulchLevel mulchLevelsNew = newEntity.getMulchLevels();
        OrganicMaterialLevel organicMaterialLevel = entity.getOrganicMaterialLevel();
        OrganicMaterialLevel organicMaterialLevelNew = newEntity.getOrganicMaterialLevel();
        SoilAnalysesLevel soilAnalysesLevel = entity.getSoilAnalysesLevel();
        SoilAnalysesLevel soilAnalysesLevelNew = newEntity.getSoilAnalysesLevel();
        TillageLevel tillageLevels = entity.getTillageLevels();
        TillageLevel tillageLevelsNew = newEntity.getTillageLevels();
        entity = em.merge(newEntity);
        if (updateUserId != null && !updateUserId.equals(updateUserIdNew)) {
            updateUserId.getTreatmentsCollection().remove(entity);
        }
        if (updateUserIdNew != null && !updateUserIdNew.equals(updateUserId)) {
            updateUserIdNew.getTreatmentsCollection().add(entity);
        }
        if (planting != null && !planting.equals(plantingNew)) {
            planting.getTreatmentsCollection().remove(entity);
        }
        if (plantingNew != null && !plantingNew.equals(planting)) {
            plantingNew.getTreatmentsCollection().add(entity);
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
        if (genotype != null && !genotype.equals(genotypeNew)) {
            genotype.getTreatmentsCollection().remove(entity);
        }
        if (genotypeNew != null && !genotypeNew.equals(genotype)) {
            genotypeNew.getTreatmentsCollection().add(entity);
        }
        if (harvestLevels != null && !harvestLevels.equals(harvestLevelsNew)) {
            harvestLevels.getTreatmentsCollection().remove(entity);
        }
        if (harvestLevelsNew != null && !harvestLevelsNew.equals(harvestLevels)) {
            harvestLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (initialConditionLevel != null && !initialConditionLevel.equals(initialConditionLevelNew)) {
            initialConditionLevel.getTreatmentsCollection().remove(entity);
        }
        if (initialConditionLevelNew != null && !initialConditionLevelNew.equals(initialConditionLevel)) {
            initialConditionLevelNew.getTreatmentsCollection().add(entity);
        }
        if (irrigationLevel != null && !irrigationLevel.equals(irrigationLevelNew)) {
            irrigationLevel.getTreatmentsCollection().remove(entity);
        }
        if (irrigationLevelNew != null && !irrigationLevelNew.equals(irrigationLevel)) {
            irrigationLevelNew.getTreatmentsCollection().add(entity);
        }
        if (mulchLevels != null && !mulchLevels.equals(mulchLevelsNew)) {
            mulchLevels.getTreatmentsCollection().remove(entity);
        }
        if (mulchLevelsNew != null && !mulchLevelsNew.equals(mulchLevels)) {
            mulchLevelsNew.getTreatmentsCollection().add(entity);
        }
        if (organicMaterialLevel != null && !organicMaterialLevel.equals(organicMaterialLevelNew)) {
            organicMaterialLevel.getTreatmentsCollection().remove(entity);
        }
        if (organicMaterialLevelNew != null && !organicMaterialLevelNew.equals(organicMaterialLevel)) {
            organicMaterialLevelNew.getTreatmentsCollection().add(entity);
        }
        if (soilAnalysesLevel != null && !soilAnalysesLevel.equals(soilAnalysesLevelNew)) {
            soilAnalysesLevel.getTreatmentsCollection().remove(entity);
        }
        if (soilAnalysesLevelNew != null && !soilAnalysesLevelNew.equals(soilAnalysesLevel)) {
            soilAnalysesLevelNew.getTreatmentsCollection().add(entity);
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
    private void deleteEntity(Treatment entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Users updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            updateUserId.getTreatmentsCollection().remove(entity);
        }
        Planting planting = entity.getPlanting();
        if (planting != null) {
            planting.getTreatmentsCollection().remove(entity);
        }
        ChemicalLevel chemicalLevels = entity.getChemicalLevels();
        if (chemicalLevels != null) {
            chemicalLevels.getTreatmentsCollection().remove(entity);
        }
        EnvironModifLevel environModifLevels = entity.getEnvironModifLevels();
        if (environModifLevels != null) {
            environModifLevels.getTreatmentsCollection().remove(entity);
        }
        Experiment experimentalDescrips = entity.getExperiment();
        if (experimentalDescrips != null) {
            experimentalDescrips.getTreatmentsCollection().remove(entity);
        }
        FertilizerLevel fertilizerLevels = entity.getFertilizerLevels();
        if (fertilizerLevels != null) {
            fertilizerLevels.getTreatmentsCollection().remove(entity);
        }
        Field fields = entity.getField();
        if (fields != null) {
            fields.getTreatmentsCollection().remove(entity);
        }
        Genotype genotype = entity.getGenotype();
        if (genotype != null) {
            genotype.getTreatmentsCollection().remove(entity);
        }
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        if (harvestLevels != null) {
            harvestLevels.getTreatmentsCollection().remove(entity);
        }
        InitialConditionLevel initialConditionLevel = entity.getInitialConditionLevel();
        if (initialConditionLevel != null) {
            initialConditionLevel.getTreatmentsCollection().remove(entity);
        }
        IrrigationLevel irrigationLevel = entity.getIrrigationLevel();
        if (irrigationLevel != null) {
            irrigationLevel.getTreatmentsCollection().remove(entity);
        }
        MulchLevel mulchLevels = entity.getMulchLevels();
        if (mulchLevels != null) {
            mulchLevels.getTreatmentsCollection().remove(entity);
        }
        OrganicMaterialLevel organicMaterialLevel = entity.getOrganicMaterialLevel();
        if (organicMaterialLevel != null) {
            organicMaterialLevel.getTreatmentsCollection().remove(entity);
        }
        SoilAnalysesLevel soilAnalysesLevels = entity.getSoilAnalysesLevel();
        if (soilAnalysesLevels != null) {
            soilAnalysesLevels.getTreatmentsCollection().remove(entity);
        }
        TillageLevel tillageLevels = entity.getTillageLevels();
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
     * Returns a dynamic instance of PlantingResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of PlantingResource
     */
    @Path("plantings/")
    public PlantingResource getPlantingsResource() {
        PlantingsResourceSub plantingsResourceSub = resourceContext.getResource(PlantingsResourceSub.class);
        plantingsResourceSub.setParent(getEntity());
        return plantingsResourceSub;
    }

    /**
     * Returns a dynamic instance of ChemicalLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of ChemicalLevelResource
     */
    @Path("chemicalLevels/")
    public ChemicalLevelResource getChemicalLevelsResource() {
        ChemicalLevelsResourceSub chemicalLevelsResourceSub = resourceContext.getResource(ChemicalLevelsResourceSub.class);
        chemicalLevelsResourceSub.setParent(getEntity());
        return chemicalLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of EnvironModifLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of EnvironModifLevelResource
     */
    @Path("environModifLevels/")
    public EnvironModifLevelResource getEnvironModifLevelsResource() {
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
    @Path("experiments")
    public ExperimentResource getExperimentalDescripsResource() {
        ExperimentResourceSub experimentResourceSub = resourceContext.getResource(ExperimentResourceSub.class);
        experimentResourceSub.setParent(getEntity());
        return experimentResourceSub;
    }

    /**
     * Returns a dynamic instance of FertilizerLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of FertilizerLevelResource
     */
    @Path("fertilizerLevels/")
    public FertilizerLevelResource getFertilizerLevelsResource() {
        FertilizerLevelsResourceSub fertilizerLevelsResourceSub = resourceContext.getResource(FertilizerLevelsResourceSub.class);
        fertilizerLevelsResourceSub.setParent(getEntity());
        return fertilizerLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of FieldResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of FieldResource
     */
    @Path("fields/")
    public FieldResource getFieldsResource() {
        FieldsResourceSub fieldsResourceSub = resourceContext.getResource(FieldsResourceSub.class);
        fieldsResourceSub.setParent(getEntity());
        return fieldsResourceSub;
    }

    /**
     * Returns a dynamic instance of GenotypeResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of GenotypeResource
     */
    @Path("genotypes/")
    public GenotypeResource getGenotypesResource() {
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
     * Returns a dynamic instance of InitialConditionLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of InitialConditionLevelResource
     */
    @Path("initialConditionLevels/")
    public InitialConditionLevelResource getInitialConditionLevelsResource() {
        InitialConditionLevelsResourceSub initialConditionLevelsResourceSub = resourceContext.getResource(InitialConditionLevelsResourceSub.class);
        initialConditionLevelsResourceSub.setParent(getEntity());
        return initialConditionLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of IrrigationLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of IrrigationLevelResource
     */
    @Path("irrigationLevels/")
    public IrrigationLevelResource getIrrigationLevelsResource() {
        IrrigationLevelsResourceSub irrigationLevelsResourceSub = resourceContext.getResource(IrrigationLevelsResourceSub.class);
        irrigationLevelsResourceSub.setParent(getEntity());
        return irrigationLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of MulchLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of MulchLevelResource
     */
    @Path("mulchLevels/")
    public MulchLevelResource getMulchLevelsResource() {
        MulchLevelsResourceSub mulchLevelsResourceSub = resourceContext.getResource(MulchLevelsResourceSub.class);
        mulchLevelsResourceSub.setParent(getEntity());
        return mulchLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of OrganicMaterialLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of OrganicMaterialLevelResource
     */
    @Path("organicMaterialLevels/")
    public OrganicMaterialLevelResource getOrganicMaterialLevelsResource() {
        OrganicMaterialLevelsResourceSub organicMaterialLevelsResourceSub = resourceContext.getResource(OrganicMaterialLevelsResourceSub.class);
        organicMaterialLevelsResourceSub.setParent(getEntity());
        return organicMaterialLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of SoilAnalysesLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of SoilAnalysesLevelResource
     */
    @Path("soilAnalysesLevels/")
    public SoilAnalysesLevelResource getSoilAnalysesLevelsResource() {
        SoilAnalysesLevelsResourceSub soilAnalysesLevelsResourceSub = resourceContext.getResource(SoilAnalysesLevelsResourceSub.class);
        soilAnalysesLevelsResourceSub.setParent(getEntity());
        return soilAnalysesLevelsResourceSub;
    }

    /**
     * Returns a dynamic instance of TillageLevelResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of TillageLevelResource
     */
    @Path("tillageLevels/")
    public TillageLevelResource getTillageLevelsResource() {
        TillageLevelsResourceSub tillageLevelsResourceSub = resourceContext.getResource(TillageLevelsResourceSub.class);
        tillageLevelsResourceSub.setParent(getEntity());
        return tillageLevelsResourceSub;
    }

    public static class UpdateUserIdResourceSub extends UsersResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
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

    public static class PlantingsResourceSub extends PlantingResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected Planting getEntity() {
            Planting entity = parent.getPlanting();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class ChemicalLevelsResourceSub extends ChemicalLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected ChemicalLevel getEntity() {
            ChemicalLevel entity = parent.getChemicalLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class EnvironModifLevelsResourceSub extends EnvironModifLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected EnvironModifLevel getEntity() {
            EnvironModifLevel entity = parent.getEnvironModifLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class ExperimentResourceSub extends ExperimentResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected Experiment getEntity() {
            Experiment entity = parent.getExperiment();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class FertilizerLevelsResourceSub extends FertilizerLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected FertilizerLevel getEntity() {
            FertilizerLevel entity = parent.getFertilizerLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class FieldsResourceSub extends FieldResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected Field getEntity() {
            Field entity = parent.getField();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class GenotypesResourceSub extends GenotypeResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected Genotype getEntity() {
            Genotype entity = parent.getGenotype();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class HarvestLevelsResourceSub extends HarvestLevelsResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
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

    public static class InitialConditionLevelsResourceSub extends InitialConditionLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected InitialConditionLevel getEntity() {
            InitialConditionLevel entity = parent.getInitialConditionLevel();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class IrrigationLevelsResourceSub extends IrrigationLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected IrrigationLevel getEntity() {
            IrrigationLevel entity = parent.getIrrigationLevel();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class MulchLevelsResourceSub extends MulchLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected MulchLevel getEntity() {
            MulchLevel entity = parent.getMulchLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class OrganicMaterialLevelsResourceSub extends OrganicMaterialLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected OrganicMaterialLevel getEntity() {
            OrganicMaterialLevel entity = parent.getOrganicMaterialLevel();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class SoilAnalysesLevelsResourceSub extends SoilAnalysesLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected SoilAnalysesLevel getEntity() {
            SoilAnalysesLevel entity = parent.getSoilAnalysesLevel();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }

    public static class TillageLevelsResourceSub extends TillageLevelResource {

        private Treatment parent;

        public void setParent(Treatment parent) {
            this.parent = parent;
        }

        @Override
        protected TillageLevel getEntity() {
            TillageLevel entity = parent.getTillageLevels();
            if (entity == null) {
                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
            }
            return entity;
        }
    }
}
