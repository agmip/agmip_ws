/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Treatments;
import beans.TreatmentsPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
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

/**
 *
 * @author wpavan
 */
@XmlRootElement(name = "treatments")
public class TreatmentsConverter {
    private Treatments entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TreatmentsConverter */
    public TreatmentsConverter() {
        entity = new Treatments();
    }

    /**
     * Creates a new instance of TreatmentsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public TreatmentsConverter(Treatments entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getTreatmentsPK().getExpId() + "," + entity.getTreatmentsPK().getTrno() + "," + entity.getTreatmentsPK().getRp() + "," + entity.getTreatmentsPK().getSq() + "," + entity.getTreatmentsPK().getOp() + "," + entity.getTreatmentsPK().getCo() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getUpdateUserId();
        getPlantings();
        getChemicalLevels();
        getEnvironModifLevels();
        getExperimentalDescrips();
        getFertilizerLevels();
        getFields();
        getGenotypes();
        getHarvestLevels();
        getInitialConditionLevels();
        getIrrigationLevels();
        getMulchLevels();
        getOrganicMaterialLevels();
        getSoilAnalysesLevels();
        getTillageLevels();
    }

    /**
     * Creates a new instance of TreatmentsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TreatmentsConverter(Treatments entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for treatmentsPK.
     *
     * @return value for treatmentsPK
     */
    @XmlElement
    public TreatmentsPK getTreatmentsPK() {
        return (expandLevel > 0) ? entity.getTreatmentsPK() : null;
    }

    /**
     * Setter for treatmentsPK.
     *
     * @param value the value to set
     */
    public void setTreatmentsPK(TreatmentsPK value) {
        entity.setTreatmentsPK(value);
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
     * Getter for plantings.
     *
     * @return value for plantings
     */
    @XmlElement
    public PlantingsConverter getPlantings() {
        if (expandLevel > 0) {
            if (entity.getPlantings() != null) {
                return new PlantingsConverter(entity.getPlantings(), uri.resolve("plantings/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for plantings.
     *
     * @param value the value to set
     */
    public void setPlantings(PlantingsConverter value) {
        entity.setPlantings((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for chemicalLevels.
     *
     * @return value for chemicalLevels
     */
    @XmlElement
    public ChemicalLevelsConverter getChemicalLevels() {
        if (expandLevel > 0) {
            if (entity.getChemicalLevels() != null) {
                return new ChemicalLevelsConverter(entity.getChemicalLevels(), uri.resolve("chemicalLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for chemicalLevels.
     *
     * @param value the value to set
     */
    public void setChemicalLevels(ChemicalLevelsConverter value) {
        entity.setChemicalLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for environModifLevels.
     *
     * @return value for environModifLevels
     */
    @XmlElement
    public EnvironModifLevelsConverter getEnvironModifLevels() {
        if (expandLevel > 0) {
            if (entity.getEnvironModifLevels() != null) {
                return new EnvironModifLevelsConverter(entity.getEnvironModifLevels(), uri.resolve("environModifLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for environModifLevels.
     *
     * @param value the value to set
     */
    public void setEnvironModifLevels(EnvironModifLevelsConverter value) {
        entity.setEnvironModifLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for experimentalDescrips.
     *
     * @return value for experimentalDescrips
     */
    @XmlElement
    public ExperimentalDescripsConverter getExperimentalDescrips() {
        if (expandLevel > 0) {
            if (entity.getExperimentalDescrips() != null) {
                return new ExperimentalDescripsConverter(entity.getExperimentalDescrips(), uri.resolve("experimentalDescrips/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for experimentalDescrips.
     *
     * @param value the value to set
     */
    public void setExperimentalDescrips(ExperimentalDescripsConverter value) {
        entity.setExperimentalDescrips((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for fertilizerLevels.
     *
     * @return value for fertilizerLevels
     */
    @XmlElement
    public FertilizerLevelsConverter getFertilizerLevels() {
        if (expandLevel > 0) {
            if (entity.getFertilizerLevels() != null) {
                return new FertilizerLevelsConverter(entity.getFertilizerLevels(), uri.resolve("fertilizerLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for fertilizerLevels.
     *
     * @param value the value to set
     */
    public void setFertilizerLevels(FertilizerLevelsConverter value) {
        entity.setFertilizerLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for fields.
     *
     * @return value for fields
     */
    @XmlElement
    public FieldsConverter getFields() {
        if (expandLevel > 0) {
            if (entity.getFields() != null) {
                return new FieldsConverter(entity.getFields(), uri.resolve("fields/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for fields.
     *
     * @param value the value to set
     */
    public void setFields(FieldsConverter value) {
        entity.setFields((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for genotypes.
     *
     * @return value for genotypes
     */
    @XmlElement
    public GenotypesConverter getGenotypes() {
        if (expandLevel > 0) {
            if (entity.getGenotypes() != null) {
                return new GenotypesConverter(entity.getGenotypes(), uri.resolve("genotypes/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for genotypes.
     *
     * @param value the value to set
     */
    public void setGenotypes(GenotypesConverter value) {
        entity.setGenotypes((value != null) ? value.getEntity() : null);
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
     * Getter for initialConditionLevels.
     *
     * @return value for initialConditionLevels
     */
    @XmlElement
    public InitialConditionLevelsConverter getInitialConditionLevels() {
        if (expandLevel > 0) {
            if (entity.getInitialConditionLevels() != null) {
                return new InitialConditionLevelsConverter(entity.getInitialConditionLevels(), uri.resolve("initialConditionLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for initialConditionLevels.
     *
     * @param value the value to set
     */
    public void setInitialConditionLevels(InitialConditionLevelsConverter value) {
        entity.setInitialConditionLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for irrigationLevels.
     *
     * @return value for irrigationLevels
     */
    @XmlElement
    public IrrigationLevelsConverter getIrrigationLevels() {
        if (expandLevel > 0) {
            if (entity.getIrrigationLevels() != null) {
                return new IrrigationLevelsConverter(entity.getIrrigationLevels(), uri.resolve("irrigationLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for irrigationLevels.
     *
     * @param value the value to set
     */
    public void setIrrigationLevels(IrrigationLevelsConverter value) {
        entity.setIrrigationLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for mulchLevels.
     *
     * @return value for mulchLevels
     */
    @XmlElement
    public MulchLevelsConverter getMulchLevels() {
        if (expandLevel > 0) {
            if (entity.getMulchLevels() != null) {
                return new MulchLevelsConverter(entity.getMulchLevels(), uri.resolve("mulchLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for mulchLevels.
     *
     * @param value the value to set
     */
    public void setMulchLevels(MulchLevelsConverter value) {
        entity.setMulchLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for organicMaterialLevels.
     *
     * @return value for organicMaterialLevels
     */
    @XmlElement
    public OrganicMaterialLevelsConverter getOrganicMaterialLevels() {
        if (expandLevel > 0) {
            if (entity.getOrganicMaterialLevels() != null) {
                return new OrganicMaterialLevelsConverter(entity.getOrganicMaterialLevels(), uri.resolve("organicMaterialLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for organicMaterialLevels.
     *
     * @param value the value to set
     */
    public void setOrganicMaterialLevels(OrganicMaterialLevelsConverter value) {
        entity.setOrganicMaterialLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for soilAnalysesLevels.
     *
     * @return value for soilAnalysesLevels
     */
    @XmlElement
    public SoilAnalysesLevelsConverter getSoilAnalysesLevels() {
        if (expandLevel > 0) {
            if (entity.getSoilAnalysesLevels() != null) {
                return new SoilAnalysesLevelsConverter(entity.getSoilAnalysesLevels(), uri.resolve("soilAnalysesLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for soilAnalysesLevels.
     *
     * @param value the value to set
     */
    public void setSoilAnalysesLevels(SoilAnalysesLevelsConverter value) {
        entity.setSoilAnalysesLevels((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for tillageLevels.
     *
     * @return value for tillageLevels
     */
    @XmlElement
    public TillageLevelsConverter getTillageLevels() {
        if (expandLevel > 0) {
            if (entity.getTillageLevels() != null) {
                return new TillageLevelsConverter(entity.getTillageLevels(), uri.resolve("tillageLevels/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for tillageLevels.
     *
     * @param value the value to set
     */
    public void setTillageLevels(TillageLevelsConverter value) {
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
     * Returns the Treatments entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Treatments getEntity() {
        if (entity.getTreatmentsPK() == null) {
            TreatmentsConverter converter = UriResolver.getInstance().resolve(TreatmentsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Treatments entity.
     *
     * @return an resolved entity
     */
    public Treatments resolveEntity(EntityManager em) {
        Users updateUserId = entity.getUpdateUserId();
        if (updateUserId != null) {
            entity.setUpdateUserId(em.getReference(Users.class, updateUserId.getUserId()));
        }
        Plantings plantings = entity.getPlantings();
        if (plantings != null) {
            entity.setPlantings(em.getReference(Plantings.class, plantings.getPlantingsPK()));
        }
        ChemicalLevels chemicalLevels = entity.getChemicalLevels();
        if (chemicalLevels != null) {
            entity.setChemicalLevels(em.getReference(ChemicalLevels.class, chemicalLevels.getChemicalLevelsPK()));
        }
        EnvironModifLevels environModifLevels = entity.getEnvironModifLevels();
        if (environModifLevels != null) {
            entity.setEnvironModifLevels(em.getReference(EnvironModifLevels.class, environModifLevels.getEnvironModifLevelsPK()));
        }
        ExperimentalDescrips experimentalDescrips = entity.getExperimentalDescrips();
        if (experimentalDescrips != null) {
            entity.setExperimentalDescrips(em.getReference(ExperimentalDescrips.class, experimentalDescrips.getExpId()));
        }
        FertilizerLevels fertilizerLevels = entity.getFertilizerLevels();
        if (fertilizerLevels != null) {
            entity.setFertilizerLevels(em.getReference(FertilizerLevels.class, fertilizerLevels.getFertilizerLevelsPK()));
        }
        Fields fields = entity.getFields();
        if (fields != null) {
            entity.setFields(em.getReference(Fields.class, fields.getFieldsPK()));
        }
        Genotypes genotypes = entity.getGenotypes();
        if (genotypes != null) {
            entity.setGenotypes(em.getReference(Genotypes.class, genotypes.getGenotypesPK()));
        }
        HarvestLevels harvestLevels = entity.getHarvestLevels();
        if (harvestLevels != null) {
            entity.setHarvestLevels(em.getReference(HarvestLevels.class, harvestLevels.getHarvestLevelsPK()));
        }
        InitialConditionLevels initialConditionLevels = entity.getInitialConditionLevels();
        if (initialConditionLevels != null) {
            entity.setInitialConditionLevels(em.getReference(InitialConditionLevels.class, initialConditionLevels.getInitialConditionLevelsPK()));
        }
        IrrigationLevels irrigationLevels = entity.getIrrigationLevels();
        if (irrigationLevels != null) {
            entity.setIrrigationLevels(em.getReference(IrrigationLevels.class, irrigationLevels.getIrrigationLevelsPK()));
        }
        MulchLevels mulchLevels = entity.getMulchLevels();
        if (mulchLevels != null) {
            entity.setMulchLevels(em.getReference(MulchLevels.class, mulchLevels.getMulchLevelsPK()));
        }
        OrganicMaterialLevels organicMaterialLevels = entity.getOrganicMaterialLevels();
        if (organicMaterialLevels != null) {
            entity.setOrganicMaterialLevels(em.getReference(OrganicMaterialLevels.class, organicMaterialLevels.getOrganicMaterialLevelsPK()));
        }
        SoilAnalysesLevels soilAnalysesLevels = entity.getSoilAnalysesLevels();
        if (soilAnalysesLevels != null) {
            entity.setSoilAnalysesLevels(em.getReference(SoilAnalysesLevels.class, soilAnalysesLevels.getSoilAnalysesLevelsPK()));
        }
        TillageLevels tillageLevels = entity.getTillageLevels();
        if (tillageLevels != null) {
            entity.setTillageLevels(em.getReference(TillageLevels.class, tillageLevels.getTillageLevelsPK()));
        }
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TreatmentsConverter)) {
            return false;
        }
        TreatmentsConverter other = (TreatmentsConverter) object;
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
