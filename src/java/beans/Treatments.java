/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wpavan
 */
@Entity
@Table(name = "treatments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treatments.findAll", query = "SELECT t FROM Treatments t"),
    @NamedQuery(name = "Treatments.findByExpId", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.expId = :expId"),
    @NamedQuery(name = "Treatments.findByTrno", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.trno = :trno"),
    @NamedQuery(name = "Treatments.findByRp", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.rp = :rp"),
    @NamedQuery(name = "Treatments.findBySq", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.sq = :sq"),
    @NamedQuery(name = "Treatments.findByOp", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.op = :op"),
    @NamedQuery(name = "Treatments.findByCo", query = "SELECT t FROM Treatments t WHERE t.treatmentsPK.co = :co"),
    @NamedQuery(name = "Treatments.findByTrName", query = "SELECT t FROM Treatments t WHERE t.trName = :trName"),
    @NamedQuery(name = "Treatments.findBySm", query = "SELECT t FROM Treatments t WHERE t.sm = :sm"),
    @NamedQuery(name = "Treatments.findByUpdateDate", query = "SELECT t FROM Treatments t WHERE t.updateDate = :updateDate"),
    @NamedQuery(name = "Treatments.findByUpdateStatus", query = "SELECT t FROM Treatments t WHERE t.updateStatus = :updateStatus")})
public class Treatments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TreatmentsPK treatmentsPK;
    @Size(max = 255)
    @Column(name = "tr_name")
    private String trName;
    @Column(name = "sm")
    private Integer sm;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "tr_notes")
    private String trNotes;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "update_comment")
    private String updateComment;
    @Column(name = "update_status")
    private Integer updateStatus;
    @JoinColumn(name = "update_user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users updateUserId;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "pl", referencedColumnName = "pl")})
    @ManyToOne(optional = false)
    private Plantings plantings;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ch", referencedColumnName = "ch")})
    @ManyToOne(optional = false)
    private ChemicalLevels chemicalLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "em", referencedColumnName = "em")})
    @ManyToOne(optional = false)
    private EnvironModifLevels environModifLevels;
    @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExperimentalDescrips experimentalDescrips;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "fe", referencedColumnName = "fe")})
    @ManyToOne(optional = false)
    private FertilizerLevels fertilizerLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "fl", referencedColumnName = "fl")})
    @ManyToOne(optional = false)
    private Fields fields;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ge", referencedColumnName = "ge")})
    @ManyToOne(optional = false)
    private Genotypes genotypes;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ha", referencedColumnName = "ha")})
    @ManyToOne(optional = false)
    private HarvestLevels harvestLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ic", referencedColumnName = "ic")})
    @ManyToOne(optional = false)
    private InitialConditionLevels initialConditionLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ir", referencedColumnName = "ir")})
    @ManyToOne(optional = false)
    private IrrigationLevels irrigationLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ml", referencedColumnName = "ml")})
    @ManyToOne(optional = false)
    private MulchLevels mulchLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "om", referencedColumnName = "om")})
    @ManyToOne(optional = false)
    private OrganicMaterialLevels organicMaterialLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "sa", referencedColumnName = "sa")})
    @ManyToOne(optional = false)
    private SoilAnalysesLevels soilAnalysesLevels;
    @JoinColumns({
        @JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
        @JoinColumn(name = "ti", referencedColumnName = "ti")})
    @ManyToOne(optional = false)
    private TillageLevels tillageLevels;

    public Treatments() {
    }

    public Treatments(TreatmentsPK treatmentsPK) {
        this.treatmentsPK = treatmentsPK;
    }

    public Treatments(int expId, int trno, int rp, int sq, int op, int co) {
        this.treatmentsPK = new TreatmentsPK(expId, trno, rp, sq, op, co);
    }

    public TreatmentsPK getTreatmentsPK() {
        return treatmentsPK;
    }

    public void setTreatmentsPK(TreatmentsPK treatmentsPK) {
        this.treatmentsPK = treatmentsPK;
    }

    public String getTrName() {
        return trName;
    }

    public void setTrName(String trName) {
        this.trName = trName;
    }

    public Integer getSm() {
        return sm;
    }

    public void setSm(Integer sm) {
        this.sm = sm;
    }

    public String getTrNotes() {
        return trNotes;
    }

    public void setTrNotes(String trNotes) {
        this.trNotes = trNotes;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateComment() {
        return updateComment;
    }

    public void setUpdateComment(String updateComment) {
        this.updateComment = updateComment;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Users getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Users updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Plantings getPlantings() {
        return plantings;
    }

    public void setPlantings(Plantings plantings) {
        this.plantings = plantings;
    }

    public ChemicalLevels getChemicalLevels() {
        return chemicalLevels;
    }

    public void setChemicalLevels(ChemicalLevels chemicalLevels) {
        this.chemicalLevels = chemicalLevels;
    }

    public EnvironModifLevels getEnvironModifLevels() {
        return environModifLevels;
    }

    public void setEnvironModifLevels(EnvironModifLevels environModifLevels) {
        this.environModifLevels = environModifLevels;
    }

    public ExperimentalDescrips getExperimentalDescrips() {
        return experimentalDescrips;
    }

    public void setExperimentalDescrips(ExperimentalDescrips experimentalDescrips) {
        this.experimentalDescrips = experimentalDescrips;
    }

    public FertilizerLevels getFertilizerLevels() {
        return fertilizerLevels;
    }

    public void setFertilizerLevels(FertilizerLevels fertilizerLevels) {
        this.fertilizerLevels = fertilizerLevels;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Genotypes getGenotypes() {
        return genotypes;
    }

    public void setGenotypes(Genotypes genotypes) {
        this.genotypes = genotypes;
    }

    public HarvestLevels getHarvestLevels() {
        return harvestLevels;
    }

    public void setHarvestLevels(HarvestLevels harvestLevels) {
        this.harvestLevels = harvestLevels;
    }

    public InitialConditionLevels getInitialConditionLevels() {
        return initialConditionLevels;
    }

    public void setInitialConditionLevels(InitialConditionLevels initialConditionLevels) {
        this.initialConditionLevels = initialConditionLevels;
    }

    public IrrigationLevels getIrrigationLevels() {
        return irrigationLevels;
    }

    public void setIrrigationLevels(IrrigationLevels irrigationLevels) {
        this.irrigationLevels = irrigationLevels;
    }

    public MulchLevels getMulchLevels() {
        return mulchLevels;
    }

    public void setMulchLevels(MulchLevels mulchLevels) {
        this.mulchLevels = mulchLevels;
    }

    public OrganicMaterialLevels getOrganicMaterialLevels() {
        return organicMaterialLevels;
    }

    public void setOrganicMaterialLevels(OrganicMaterialLevels organicMaterialLevels) {
        this.organicMaterialLevels = organicMaterialLevels;
    }

    public SoilAnalysesLevels getSoilAnalysesLevels() {
        return soilAnalysesLevels;
    }

    public void setSoilAnalysesLevels(SoilAnalysesLevels soilAnalysesLevels) {
        this.soilAnalysesLevels = soilAnalysesLevels;
    }

    public TillageLevels getTillageLevels() {
        return tillageLevels;
    }

    public void setTillageLevels(TillageLevels tillageLevels) {
        this.tillageLevels = tillageLevels;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatmentsPK != null ? treatmentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatments)) {
            return false;
        }
        Treatments other = (Treatments) object;
        if ((this.treatmentsPK == null && other.treatmentsPK != null) || (this.treatmentsPK != null && !this.treatmentsPK.equals(other.treatmentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Treatments[ treatmentsPK=" + treatmentsPK + " ]";
    }
    
}
