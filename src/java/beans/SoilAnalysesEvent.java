package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "soil_analyses_events")
@XmlRootElement
public class SoilAnalysesEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SoilAnalysesEventPK soilAnalysesEventPK;
	@Column(name = "satl", precision = 12)
	private Float satl;
	@Column(name = "sabl")
	private Integer sabl;
	@Column(name = "sabdm", precision = 12)
	private Float sabdm;
	@Column(name = "saoc", precision = 12)
	private Float saoc;
	@Column(name = "sani", precision = 12)
	private Float sani;
	@Column(name = "saphw", precision = 12)
	private Float saphw;
	@Column(name = "saphb", precision = 12)
	private Float saphb;
	@Column(name = "sapx", precision = 12)
	private Float sapx;
	@Column(name = "sake", precision = 12)
	private Float sake;
	@Column(name = "sacmf", precision = 12)
	private Float sacmf;
	@Column(name = "slphb", precision = 12)
	private Float slphb;
	@Column(name = "sasc", precision = 12)
	private Float sasc;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "sa", referencedColumnName = "sa", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private SoilAnalysesLevel soilAnalysesLevel;

	public SoilAnalysesEvent() {
	}

	public SoilAnalysesEvent(SoilAnalysesEventPK soilAnalysesEventPK) {
		this.soilAnalysesEventPK = soilAnalysesEventPK;
	}

	public SoilAnalysesEvent(int expId, int sa, int saApplNo) {
		this.soilAnalysesEventPK = new SoilAnalysesEventPK(expId, sa, saApplNo);
	}

	public SoilAnalysesEventPK getSoilAnalysesEventPK() {
		return soilAnalysesEventPK;
	}

	public void setSoilAnalysesEventPK(SoilAnalysesEventPK soilAnalysesEventPK) {
		this.soilAnalysesEventPK = soilAnalysesEventPK;
	}

	public Float getSatl() {
		return satl;
	}

	public void setSatl(Float satl) {
		this.satl = satl;
	}

	public Integer getSabl() {
		return sabl;
	}

	public void setSabl(Integer sabl) {
		this.sabl = sabl;
	}

	public Float getSabdm() {
		return sabdm;
	}

	public void setSabdm(Float sabdm) {
		this.sabdm = sabdm;
	}

	public Float getSaoc() {
		return saoc;
	}

	public void setSaoc(Float saoc) {
		this.saoc = saoc;
	}

	public Float getSani() {
		return sani;
	}

	public void setSani(Float sani) {
		this.sani = sani;
	}

	public Float getSaphw() {
		return saphw;
	}

	public void setSaphw(Float saphw) {
		this.saphw = saphw;
	}

	public Float getSaphb() {
		return saphb;
	}

	public void setSaphb(Float saphb) {
		this.saphb = saphb;
	}

	public Float getSapx() {
		return sapx;
	}

	public void setSapx(Float sapx) {
		this.sapx = sapx;
	}

	public Float getSake() {
		return sake;
	}

	public void setSake(Float sake) {
		this.sake = sake;
	}

	public Float getSacmf() {
		return sacmf;
	}

	public void setSacmf(Float sacmf) {
		this.sacmf = sacmf;
	}

	public Float getSlphb() {
		return slphb;
	}

	public void setSlphb(Float slphb) {
		this.slphb = slphb;
	}

	public Float getSasc() {
		return sasc;
	}

	public void setSasc(Float sasc) {
		this.sasc = sasc;
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
		hash += (soilAnalysesEventPK != null ? soilAnalysesEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilAnalysesEvent)) {
			return false;
		}
		SoilAnalysesEvent other = (SoilAnalysesEvent) object;
		if ((this.soilAnalysesEventPK == null && other.soilAnalysesEventPK != null) || (this.soilAnalysesEventPK != null && !this.soilAnalysesEventPK.equals(other.soilAnalysesEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilAnalysesEvent[ soilAnalysesEventPK=" + soilAnalysesEventPK + " ]";
	}
}