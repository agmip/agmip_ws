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
	private Users updateUserId;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ti", referencedColumnName = "ti", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private TillageLevel tillageLevels;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ch", referencedColumnName = "ch", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private ChemicalLevel chemicalLevels;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "em", referencedColumnName = "em", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private EnvironModifLevel environModifLevels;
	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Experiment experiment;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "fe", referencedColumnName = "fe", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private FertilizerLevel fertilizerLevels;
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
	private HarvestLevels harvestLevels;
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
	private MulchLevel mulchLevels;
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

	public Users getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Users updateUserId) {
		this.updateUserId = updateUserId;
	}

	public TillageLevel getTillageLevels() {
		return tillageLevels;
	}

	public void setTillageLevels(TillageLevel tillageLevels) {
		this.tillageLevels = tillageLevels;
	}

	public ChemicalLevel getChemicalLevels() {
		return chemicalLevels;
	}

	public void setChemicalLevels(ChemicalLevel chemicalLevels) {
		this.chemicalLevels = chemicalLevels;
	}

	public EnvironModifLevel getEnvironModifLevels() {
		return environModifLevels;
	}

	public void setEnvironModifLevels(EnvironModifLevel environModifLevels) {
		this.environModifLevels = environModifLevels;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experimentalDescrips) {
		this.experiment = experimentalDescrips;
	}

	public FertilizerLevel getFertilizerLevels() {
		return fertilizerLevels;
	}

	public void setFertilizerLevels(FertilizerLevel fertilizerLevels) {
		this.fertilizerLevels = fertilizerLevels;
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

	public HarvestLevels getHarvestLevels() {
		return harvestLevels;
	}

	public void setHarvestLevels(HarvestLevels harvestLevels) {
		this.harvestLevels = harvestLevels;
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

	public MulchLevel getMulchLevels() {
		return mulchLevels;
	}

	public void setMulchLevels(MulchLevel mulchLevels) {
		this.mulchLevels = mulchLevels;
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
		// TODO: Warning - this method won't work in the case the id fields are not set
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
