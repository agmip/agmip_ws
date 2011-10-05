package converter;

import beans.Treatment;
import beans.TreatmentPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
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

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "treatment")
public class TreatmentConverter {
    private Treatment entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TreatmentConverter */
    public TreatmentConverter() {
        entity = new Treatment();
    }

    /**
     * Creates a new instance of TreatmentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public TreatmentConverter(Treatment entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getTreatmentPK().getExpId() + "," + entity.getTreatmentPK().getTrno() + "," + entity.getTreatmentPK().getRp() + "," + entity.getTreatmentPK().getSq() + "," + entity.getTreatmentPK().getOp() + "," + entity.getTreatmentPK().getCo() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getUpdateUserId();
        getPlanting();
        getChemicalLevels();
        getEnvironModifLevel();
        getExperimentalDescrips();
        getFertilizerLevel();
        getField();
        getGenotype();
        getHarvestLevels();
        getInitialConditionLevel();
        getIrrigationLevel();
        getMulchLevels();
        getOrganicMaterialLevel();
        getSoilAnalysesLevel();
        getTillageLevel();
    }

    /**
     * Creates a new instance of TreatmentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TreatmentConverter(Treatment entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for treatmentsPK.
     *
     * @return value for treatmentsPK
     */
    @XmlElement
    public TreatmentPK getTreatmentPK() {
        return (expandLevel > 0) ? entity.getTreatmentPK() : null;
    }

    /**
     * Setter for treatmentsPK.
     *
     * @param value the value to set
     */
    public void setTreatmentPK(TreatmentPK value) {
        entity.setTreatmentPK(value);
    }

    /**
     * Getter for trName.
     *
     * @return value for trName
     */
    @XmlElement
    public String getTrName() {
        return (expandLevel > 0) ? entity.getTrName() : null;
    }

    /**
     * Setter for trName.
     *
     * @param value the value to set
     */
    public void setTrName(String value) {
        entity.setTrName(value);
    }

    /**
     * Getter for sm.
     *
     * @return value for sm
     */
    @XmlElement
    public Integer getSm() {
        return (expandLevel > 0) ? entity.getSm() : null;
    }

    /**
     * Setter for sm.
     *
     * @param value the value to set
     */
    public void setSm(Integer value) {
        entity.setSm(value);
    }

    /**
     * Getter for trNotes.
     *
     * @return value for trNotes
     */
    @XmlElement
    public String getTrNotes() {
        return (expandLevel > 0) ? entity.getTrNotes() : null;
    }

    /**
     * Setter for trNotes.
     *
     * @param value the value to set
     */
    public void setTrNotes(String value) {
        entity.setTrNotes(value);
    }

    /**
     * Getter for updateDate.
     *
     * @return value for updateDate
     */
    @XmlElement
    public Date getUpdateDate() {
        return (expandLevel > 0) ? entity.getUpdateDate() : null;
    }

    /**
     * Setter for updateDate.
     *
     * @param value the value to set
     */
    public void setUpdateDate(Date value) {
        entity.setUpdateDate(value);
    }

    /**
     * Getter for updateComment.
     *
     * @return value for updateComment
     */
    @XmlElement
    public String getUpdateComment() {
        return (expandLevel > 0) ? entity.getUpdateComment() : null;
    }

    /**
     * Setter for updateComment.
     *
     * @param value the value to set
     */
    public void setUpdateComment(String value) {
        entity.setUpdateComment(value);
    }

    /**
     * Getter for updateStatus.
     *
     * @return value for updateStatus
     */
    @XmlElement
    public Integer getUpdateStatus() {
        return (expandLevel > 0) ? entity.getUpdateStatus() : null;
    }

    /**
     * Setter for updateStatus.
     *
     * @param value the value to set
     */
    public void setUpdateStatus(Integer value) {
        entity.setUpdateStatus(value);
    }

    /**
     * Getter for updateUserId.
     *
     * @return value for updateUserId
     */
    @XmlElement
    public UsersConverter getUpdateUserId() {
        if (expandLevel > 0) {
            if (entity.getUpdateUserId() != null) {
                return new UsersConverter(entity.getUpdateUserId(), uri.resolve("updateUserId/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for updateUserId.
     *
     * @param value the value to set
     */
    public void setUpdateUserId(UsersConverter value) {
        entity.setUpdateUserId((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for planting.
     *
     * @return value for planting
     */
    @XmlElement
    public PlantingConverter getPlanting() {
        if (expandLevel > 0) {
            if (entity.getPlanting() != null) {
                return new PlantingConverter(entity.getPlanting(), uri.resolve("plantings/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for planting.
     *
     * @param value the value to set
     */
    public void setPlanting(PlantingConverter value) {
        entity.setPlanting((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for chemicalLevels.
     *
     * @return value for chemicalLevels
     */
    @XmlElement
    public ChemicalLevelConverter getChemicalLevels() {
        if (expandLevel > 0) {
            if (entity.getChemicalLevels() != null) {
                return new ChemicalLevelConverter(entity.getChemicalLevels(), uri.resolve("chemicalLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for chemicalLevels.
     *
     * @param value the value to set
     */
    public void setChemicalLevels(ChemicalLevelConverter value) {
        entity.setChemicalLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for environModifLevel.
     *
     * @return value for environModifLevel
     */
    @XmlElement
    public EnvironModifLevelConverter getEnvironModifLevel() {
        if (expandLevel > 0) {
            if (entity.getEnvironModifLevels() != null) {
                return new EnvironModifLevelConverter(entity.getEnvironModifLevels(), uri.resolve("environModifLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for environModifLevel.
     *
     * @param value the value to set
     */
    public void setEnvironModifLevel(EnvironModifLevelConverter value) {
        entity.setEnvironModifLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for experimentalDescrips.
     *
     * @return value for experimentalDescrips
     */
    @XmlElement
    public ExperimentConverter getExperimentalDescrips() {
        if (expandLevel > 0) {
            if (entity.getExperiment() != null) {
                return new ExperimentConverter(entity.getExperiment(), uri.resolve("experimentalDescrips/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for experimentalDescrips.
     *
     * @param value the value to set
     */
    public void setExperimentalDescrips(ExperimentConverter value) {
        entity.setExperiment((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for fertilizerLevel.
     *
     * @return value for fertilizerLevel
     */
    @XmlElement
    public FertilizerLevelConverter getFertilizerLevel() {
        if (expandLevel > 0) {
            if (entity.getFertilizerLevels() != null) {
                return new FertilizerLevelConverter(entity.getFertilizerLevels(), uri.resolve("fertilizerLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for fertilizerLevel.
     *
     * @param value the value to set
     */
    public void setFertilizerLevel(FertilizerLevelConverter value) {
        entity.setFertilizerLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for field.
     *
     * @return value for field
     */
    @XmlElement
    public FieldConverter getField() {
        if (expandLevel > 0) {
            if (entity.getField() != null) {
                return new FieldConverter(entity.getField(), uri.resolve("fields/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for field.
     *
     * @param value the value to set
     */
    public void setField(FieldConverter value) {
        entity.setField((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for genotypes.
     *
     * @return value for genotypes
     */
    @XmlElement
    public GenotypeConverter getGenotype() {
		System.out.println("entrou");
        if (expandLevel > 0) {
            if (entity.getGenotype() != null) {
                return new GenotypeConverter(entity.getGenotype(), uri.resolve("genotypes/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for genotypes.
     *
     * @param value the value to set
     */
    public void setGenotype(GenotypeConverter value) {
        entity.setGenotype((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for harvestLevels.
     *
     * @return value for harvestLevels
     */
    @XmlElement
    public HarvestLevelsConverter getHarvestLevels() {
        if (expandLevel > 0) {
            if (entity.getHarvestLevels() != null) {
                return new HarvestLevelsConverter(entity.getHarvestLevels(), uri.resolve("harvestLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for harvestLevels.
     *
     * @param value the value to set
     */
    public void setHarvestLevels(HarvestLevelsConverter value) {
        entity.setHarvestLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for initialConditionLevel.
     *
     * @return value for initialConditionLevel
     */
    @XmlElement
    public InitialConditionLevelConverter getInitialConditionLevel() {
        if (expandLevel > 0) {
            if (entity.getInitialConditionLevel() != null) {
                return new InitialConditionLevelConverter(entity.getInitialConditionLevel(), uri.resolve("initialConditionLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for initialConditionLevel.
     *
     * @param value the value to set
     */
    public void setInitialConditionLevel(InitialConditionLevelConverter value) {
        entity.setInitialConditionLevel((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for irrigationLevel.
     *
     * @return value for irrigationLevel
     */
    @XmlElement
    public IrrigationLevelConverter getIrrigationLevel() {
        if (expandLevel > 0) {
            if (entity.getIrrigationLevel() != null) {
                return new IrrigationLevelConverter(entity.getIrrigationLevel(), uri.resolve("irrigationLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for irrigationLevel.
     *
     * @param value the value to set
     */
    public void setIrrigationLevel(IrrigationLevelConverter value) {
        entity.setIrrigationLevel((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for mulchLevels.
     *
     * @return value for mulchLevels
     */
    @XmlElement
    public MulchLevelConverter getMulchLevels() {
        if (expandLevel > 0) {
            if (entity.getMulchLevels() != null) {
                return new MulchLevelConverter(entity.getMulchLevels(), uri.resolve("mulchLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for mulchLevels.
     *
     * @param value the value to set
     */
    public void setMulchLevels(MulchLevelConverter value) {
        entity.setMulchLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for organicMaterialLevel.
     *
     * @return value for organicMaterialLevel
     */
    @XmlElement
    public OrganicMaterialLevelConverter getOrganicMaterialLevel() {
        if (expandLevel > 0) {
            if (entity.getOrganicMaterialLevel() != null) {
                return new OrganicMaterialLevelConverter(entity.getOrganicMaterialLevel(), uri.resolve("organicMaterialLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for organicMaterialLevel.
     *
     * @param value the value to set
     */
    public void setOrganicMaterialLevel(OrganicMaterialLevelConverter value) {
        entity.setOrganicMaterialLevel((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for soilAnalysesLevel.
     *
     * @return value for soilAnalysesLevel
     */
    @XmlElement
    public SoilAnalysesLevelConverter getSoilAnalysesLevel() {
        if (expandLevel > 0) {
            if (entity.getSoilAnalysesLevel() != null) {
                return new SoilAnalysesLevelConverter(entity.getSoilAnalysesLevel(), uri.resolve("soilAnalysesLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for soilAnalysesLevel.
     *
     * @param value the value to set
     */
    public void setSoilAnalysesLevel(SoilAnalysesLevelConverter value) {
        entity.setSoilAnalysesLevel((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for tillageLevel.
     *
     * @return value for tillageLevel
     */
    @XmlElement
    public TillageLevelConverter getTillageLevel() {
        if (expandLevel > 0) {
            if (entity.getTillageLevels() != null) {
                return new TillageLevelConverter(entity.getTillageLevels(), uri.resolve("tillageLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for tillageLevel.
     *
     * @param value the value to set
     */
    public void setTillageLevel(TillageLevelConverter value) {
        entity.setTillageLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the Treatment entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Treatment getEntity() {
        if (entity.getTreatmentPK() == null) {
            TreatmentConverter converter = UriResolver.getInstance().resolve(TreatmentConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Treatment entity.
     *
     * @return an resolved entity
     */
    public Treatment resolveEntity(EntityManager em) {
        Users updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            entity.setUpdateUserId(em.getReference(Users.class, updateUserId.getUserId()));
        }
        Planting planting = entity.getPlanting();
        if (planting != null) {
            entity.setPlanting(em.getReference(Planting.class, planting.getPlantingPK()));
        }
        ChemicalLevel chemicalLevels = entity.getChemicalLevels();
        if (chemicalLevels != null) {
            entity.setChemicalLevels(em.getReference(ChemicalLevel.class, chemicalLevels.getChemicalLevelPK()));
        }
        EnvironModifLevel environModifLevels = entity.getEnvironModifLevels();
        if (environModifLevels != null) {
            entity.setEnvironModifLevels(em.getReference(EnvironModifLevel.class, environModifLevels.getEnvironModifLevelPK()));
        }
        Experiment experimentalDescrips = entity.getExperiment();
        if (experimentalDescrips != null) {
            entity.setExperiment(em.getReference(Experiment.class, experimentalDescrips.getExpId()));
        }
        FertilizerLevel fertilizerLevels = entity.getFertilizerLevels();
        if (fertilizerLevels != null) {
            entity.setFertilizerLevels(em.getReference(FertilizerLevel.class, fertilizerLevels.getFertilizerLevelPK()));
        }
        Field fields = entity.getField();
        if (fields != null) {
            entity.setField(em.getReference(Field.class, fields.getFieldPK()));
        }
        Genotype genotype = entity.getGenotype();
        if (genotype != null) {
            entity.setGenotype(em.getReference(Genotype.class, genotype.getGenotypePK()));
        }
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        if (harvestLevels != null) {
            entity.setHarvestLevels(em.getReference(HarvestLevels.class, harvestLevels.getHarvestLevelsPK()));
        }
        InitialConditionLevel initialConditionLevels = entity.getInitialConditionLevel();
        if (initialConditionLevels != null) {
            entity.setInitialConditionLevel(em.getReference(InitialConditionLevel.class, initialConditionLevels.getInitialConditionLevelPK()));
        }
        IrrigationLevel irrigationLevel = entity.getIrrigationLevel();
        if (irrigationLevel != null) {
            entity.setIrrigationLevel(em.getReference(IrrigationLevel.class, irrigationLevel.getIrrigationLevelPK()));
        }
        MulchLevel mulchLevels = entity.getMulchLevels();
        if (mulchLevels != null) {
            entity.setMulchLevels(em.getReference(MulchLevel.class, mulchLevels.getMulchLevelsPK()));
        }
        OrganicMaterialLevel organicMaterialLevel = entity.getOrganicMaterialLevel();
        if (organicMaterialLevel != null) {
            entity.setOrganicMaterialLevel(em.getReference(OrganicMaterialLevel.class, organicMaterialLevel.getOrganicMaterialLevelPK()));
        }
        SoilAnalysesLevel soilAnalysesLevel = entity.getSoilAnalysesLevel();
        if (soilAnalysesLevel != null) {
            entity.setSoilAnalysesLevel(em.getReference(SoilAnalysesLevel.class, soilAnalysesLevel.getSoilAnalysesLevelPK()));
        }
        TillageLevel tillageLevels = entity.getTillageLevels();
        if (tillageLevels != null) {
            entity.setTillageLevels(em.getReference(TillageLevel.class, tillageLevels.getTillageLevelPK()));
        }
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TreatmentConverter)) {
            return false;
        }
        TreatmentConverter other = (TreatmentConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (expandLevel <= 0) {
            return true;
        }
        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        if (expandLevel <= 0) {
            return hash + 37 * expandLevel;
        }
        return hash + 37 * (expandLevel + 37 * (entity == null ? 0 : entity.hashCode()));
    }
}
