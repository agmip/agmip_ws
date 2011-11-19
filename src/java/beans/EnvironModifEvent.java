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
@Table(name = "environ_modif_events")
@XmlRootElement
public class EnvironModifEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected EnvironModifEventPK environModifEventPK;
	@Column(name = "emday")
    @Temporal(TemporalType.TIMESTAMP)
	private Date emday;
	@Size(max = 255)
    @Column(name = "ecdyl")
	private String ecdyl;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "emdyl")
	private Float emdyl;
	@Size(max = 255)
    @Column(name = "ecrad")
	private String ecrad;
	@Column(name = "emrad")
	private Float emrad;
	@Size(max = 255)
    @Column(name = "ecmax")
	private String ecmax;
	@Column(name = "emmax")
	private Float emmax;
	@Size(max = 255)
    @Column(name = "ecmin")
	private String ecmin;
	@Column(name = "emmin")
	private Float emmin;
	@Size(max = 255)
    @Column(name = "ecrai")
	private String ecrai;
	@Column(name = "emrai")
	private Float emrai;
	@Size(max = 255)
    @Column(name = "ecco2")
	private String ecco2;
	@Column(name = "emco2")
	private Float emco2;
	@Column(name = "emdew")
	private Float emdew;
	@Size(max = 255)
    @Column(name = "ecdew")
	private String ecdew;
	@Size(max = 255)
    @Column(name = "ecwnd")
	private String ecwnd;
	@Column(name = "emwnd")
	private Float emwnd;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
    	@JoinColumn(name = "em", referencedColumnName = "em", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private EnvironModifLevel environModifLevel;

	public EnvironModifEvent() {
	}

	public EnvironModifEvent(EnvironModifEventPK environModifEventPK) {
		this.environModifEventPK = environModifEventPK;
	}

	public EnvironModifEvent(int expId, int em, int envLevNo) {
		this.environModifEventPK = new EnvironModifEventPK(expId, em, envLevNo);
	}

	public EnvironModifEventPK getEnvironModifEventPK() {
		return environModifEventPK;
	}

	public void setEnvironModifEventPK(EnvironModifEventPK environModifEventPK) {
		this.environModifEventPK = environModifEventPK;
	}

	public Date getEmday() {
		return emday;
	}

	public void setEmday(Date emday) {
		this.emday = emday;
	}

	public String getEcdyl() {
		return ecdyl;
	}

	public void setEcdyl(String ecdyl) {
		this.ecdyl = ecdyl;
	}

	public Float getEmdyl() {
		return emdyl;
	}

	public void setEmdyl(Float emdyl) {
		this.emdyl = emdyl;
	}

	public String getEcrad() {
		return ecrad;
	}

	public void setEcrad(String ecrad) {
		this.ecrad = ecrad;
	}

	public Float getEmrad() {
		return emrad;
	}

	public void setEmrad(Float emrad) {
		this.emrad = emrad;
	}

	public String getEcmax() {
		return ecmax;
	}

	public void setEcmax(String ecmax) {
		this.ecmax = ecmax;
	}

	public Float getEmmax() {
		return emmax;
	}

	public void setEmmax(Float emmax) {
		this.emmax = emmax;
	}

	public String getEcmin() {
		return ecmin;
	}

	public void setEcmin(String ecmin) {
		this.ecmin = ecmin;
	}

	public Float getEmmin() {
		return emmin;
	}

	public void setEmmin(Float emmin) {
		this.emmin = emmin;
	}

	public String getEcrai() {
		return ecrai;
	}

	public void setEcrai(String ecrai) {
		this.ecrai = ecrai;
	}

	public Float getEmrai() {
		return emrai;
	}

	public void setEmrai(Float emrai) {
		this.emrai = emrai;
	}

	public String getEcco2() {
		return ecco2;
	}

	public void setEcco2(String ecco2) {
		this.ecco2 = ecco2;
	}

	public Float getEmco2() {
		return emco2;
	}

	public void setEmco2(Float emco2) {
		this.emco2 = emco2;
	}

	public Float getEmdew() {
		return emdew;
	}

	public void setEmdew(Float emdew) {
		this.emdew = emdew;
	}

	public String getEcdew() {
		return ecdew;
	}

	public void setEcdew(String ecdew) {
		this.ecdew = ecdew;
	}

	public String getEcwnd() {
		return ecwnd;
	}

	public void setEcwnd(String ecwnd) {
		this.ecwnd = ecwnd;
	}

	public Float getEmwnd() {
		return emwnd;
	}

	public void setEmwnd(Float emwnd) {
		this.emwnd = emwnd;
	}

	public EnvironModifLevel getEnvironModifLevel() {
		return environModifLevel;
	}

	public void setEnvironModifLevel(EnvironModifLevel environModifLevel) {
		this.environModifLevel = environModifLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (environModifEventPK != null ? environModifEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof EnvironModifEvent)) {
			return false;
		}
		EnvironModifEvent other = (EnvironModifEvent) object;
		if ((this.environModifEventPK == null && other.environModifEventPK != null) || (this.environModifEventPK != null && !this.environModifEventPK.equals(other.environModifEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.EnvironModifEvents[ environModifEventPK=" + environModifEventPK + " ]";
	}

}
