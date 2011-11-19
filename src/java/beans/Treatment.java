package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "treatments")
@XmlRootElement
public class Treatment implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TreatmentPK treatmentPK;
	@Size(max = 255)
    @Column(name = "tr_name", length = 255)
	private String trName;
	@Column(name = "sm")
	private Integer sm;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "tr_notes", length = 2147483647)
	private String trNotes;
	@Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "update_comment", length = 2147483647)
	private String updateComment;
	@Column(name = "update_status")
	private Integer updateStatus;
	@JoinColumn(name = "update_user_id", referencedColumnName = "user_id")
    @ManyToOne
	private User updateUserId;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ti", referencedColumnName = "ti", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private TillageLevel tillageLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ch", referencedColumnName = "ch", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private ChemicalLevel chemicalLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "em", referencedColumnName = "em", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private EnvironModifLevel environModifLevel;
	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Experiment experiment;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "fe", referencedColumnName = "fe", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private FertilizerLevel fertilizerLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "fl", referencedColumnName = "fl", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
	private Field field;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ge", referencedColumnName = "ge", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private Genotype genotype;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ha", referencedColumnName = "ha", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private HarvestLevel harvestLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ic", referencedColumnName = "ic", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private InitialConditionLevel initialConditionLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ir", referencedColumnName = "ir", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private IrrigationLevel irrigationLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ml", referencedColumnName = "ml", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private MulchLevel mulchLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "om", referencedColumnName = "om", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private OrganicMaterialLevel organicMaterialLevel;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "pl", referencedColumnName = "pl", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private Planting planting;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "sa", referencedColumnName = "sa", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private SoilAnalysesLevel soilAnalysesLevel;

	public Treatment() {
	}

	public Treatment(TreatmentPK treatmentPK) {
		this.treatmentPK = treatmentPK;
	}

	public Treatment(int expId, int trno, int rp, int sq, int op, int co) {
		this.treatmentPK = new TreatmentPK(expId, trno, rp, sq, op, co);
	}

	public TreatmentPK getTreatmentPK() {
		return treatmentPK;
	}

	public void setTreatmentPK(TreatmentPK treatmentPK) {
		this.treatmentPK = treatmentPK;
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

	public User getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(User updateUserId) {
		this.updateUserId = updateUserId;
	}

	public TillageLevel getTillageLevel() {
		return tillageLevel;
	}

	public void setTillageLevel(TillageLevel tillageLevels) {
		this.tillageLevel = tillageLevels;
	}

	public ChemicalLevel getChemicalLevel() {
		return chemicalLevel;
	}

	public void setChemicalLevel(ChemicalLevel chemicalLevel) {
		this.chemicalLevel = chemicalLevel;
	}

	public EnvironModifLevel getEnvironModifLevel() {
		return environModifLevel;
	}

	public void setEnvironModifLevel(EnvironModifLevel environModifLevel) {
		this.environModifLevel = environModifLevel;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experimentalDescrips) {
		this.experiment = experimentalDescrips;
	}

	public FertilizerLevel getFertilizerLevel() {
		return fertilizerLevel;
	}

	public void setFertilizerLevel(FertilizerLevel fertilizerLevel) {
		this.fertilizerLevel = fertilizerLevel;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Genotype getGenotype() {
		return genotype;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public HarvestLevel getHarvestLevel() {
		return harvestLevel;
	}

	public void setHarvestLevel(HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel;
	}

	public InitialConditionLevel getInitialConditionLevel() {
		return initialConditionLevel;
	}

	public void setInitialConditionLevel(InitialConditionLevel initialConditionLevel) {
		this.initialConditionLevel = initialConditionLevel;
	}

	public IrrigationLevel getIrrigationLevel() {
		return irrigationLevel;
	}

	public void setIrrigationLevel(IrrigationLevel irrigationLevel) {
		this.irrigationLevel = irrigationLevel;
	}

	public MulchLevel getMulchLevel() {
		return mulchLevel;
	}

	public void setMulchLevel(MulchLevel mulchLevel) {
		this.mulchLevel = mulchLevel;
	}

	public OrganicMaterialLevel getOrganicMaterialLevel() {
		return organicMaterialLevel;
	}

	public void setOrganicMaterialLevel(OrganicMaterialLevel organicMaterialLevel) {
		this.organicMaterialLevel = organicMaterialLevel;
	}

	public Planting getPlanting() {
		return planting;
	}

	public void setPlanting(Planting planting) {
		this.planting = planting;
	}

	public SoilAnalysesLevel getSoilAnalysesLevel() {
		return soilAnalysesLevel;
	}

	public void setSoilAnalysesLevel(SoilAnalysesLevel soilAnalysesLevel) {
		this.soilAnalysesLevel = soilAnalysesLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (treatmentPK != null ? treatmentPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Treatment)) {
			return false;
		}
		Treatment other = (Treatment) object;
		if ((this.treatmentPK == null && other.treatmentPK != null) || (this.treatmentPK != null && !this.treatmentPK.equals(other.treatmentPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Treatment[ treatmentPK=" + treatmentPK + " ]";
	}
}