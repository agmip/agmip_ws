package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author fonini
 */
@Entity
@Table(name = "organic_material_events", catalog = "agmipapi", schema = "")
@XmlRootElement
public class OrganicMaterialEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected OrganicMaterialEventPK organicMaterialEventPK;
	@Column(name = "omdat")
    @Temporal(TemporalType.TIMESTAMP)
	private Date omdat;
	@Size(max = 255)
    @Column(name = "omcd", length = 255)
	private String omcd;
	@Size(max = 255)
    @Column(name = "omacd", length = 255)
	private String omacd;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "omdep", precision = 12)
	private Float omdep;
	@Column(name = "ominp", precision = 12)
	private Float ominp;
	@Column(name = "omamt", precision = 12)
	private Float omamt;
	@Column(name = "omh2o", precision = 12)
	private Float omh2o;
	@Column(name = "omcpct", precision = 12)
	private Float omcpct;
	@Column(name = "omnpct", precision = 12)
	private Float omnpct;
	@Column(name = "omppct", precision = 12)
	private Float omppct;
	@Column(name = "omkpct", precision = 12)
	private Float omkpct;
	@Column(name = "omlipct", precision = 12)
	private Float omlipct;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "om", referencedColumnName = "om", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private OrganicMaterialLevel organicMaterialLevel;

	public OrganicMaterialEvent() {
	}

	public OrganicMaterialEvent(OrganicMaterialEventPK organicMaterialEventPK) {
		this.organicMaterialEventPK = organicMaterialEventPK;
	}

	public OrganicMaterialEvent(int expId, int om, int omOpsNo) {
		this.organicMaterialEventPK = new OrganicMaterialEventPK(expId, om, omOpsNo);
	}

	public OrganicMaterialEventPK getOrganicMaterialEventPK() {
		return organicMaterialEventPK;
	}

	public void setOrganicMaterialEventPK(OrganicMaterialEventPK organicMaterialEventPK) {
		this.organicMaterialEventPK = organicMaterialEventPK;
	}

	public Date getOmdat() {
		return omdat;
	}

	public void setOmdat(Date omdat) {
		this.omdat = omdat;
	}

	public String getOmcd() {
		return omcd;
	}

	public void setOmcd(String omcd) {
		this.omcd = omcd;
	}

	public String getOmacd() {
		return omacd;
	}

	public void setOmacd(String omacd) {
		this.omacd = omacd;
	}

	public Float getOmdep() {
		return omdep;
	}

	public void setOmdep(Float omdep) {
		this.omdep = omdep;
	}

	public Float getOminp() {
		return ominp;
	}

	public void setOminp(Float ominp) {
		this.ominp = ominp;
	}

	public Float getOmamt() {
		return omamt;
	}

	public void setOmamt(Float omamt) {
		this.omamt = omamt;
	}

	public Float getOmh2o() {
		return omh2o;
	}

	public void setOmh2o(Float omh2o) {
		this.omh2o = omh2o;
	}

	public Float getOmcpct() {
		return omcpct;
	}

	public void setOmcpct(Float omcpct) {
		this.omcpct = omcpct;
	}

	public Float getOmnpct() {
		return omnpct;
	}

	public void setOmnpct(Float omnpct) {
		this.omnpct = omnpct;
	}

	public Float getOmppct() {
		return omppct;
	}

	public void setOmppct(Float omppct) {
		this.omppct = omppct;
	}

	public Float getOmkpct() {
		return omkpct;
	}

	public void setOmkpct(Float omkpct) {
		this.omkpct = omkpct;
	}

	public Float getOmlipct() {
		return omlipct;
	}

	public void setOmlipct(Float omlipct) {
		this.omlipct = omlipct;
	}

	public OrganicMaterialLevel getOrganicMaterialLevel() {
		return organicMaterialLevel;
	}

	public void setOrganicMaterialLevel(OrganicMaterialLevel organicMaterialLevel) {
		this.organicMaterialLevel = organicMaterialLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (organicMaterialEventPK != null ? organicMaterialEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof OrganicMaterialEvent)) {
			return false;
		}
		OrganicMaterialEvent other = (OrganicMaterialEvent) object;
		if ((this.organicMaterialEventPK == null && other.organicMaterialEventPK != null) || (this.organicMaterialEventPK != null && !this.organicMaterialEventPK.equals(other.organicMaterialEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.OrganicMaterialEvent[ organicMaterialEventPK=" + organicMaterialEventPK + " ]";
	}

}
