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
@Table(name = "fertilizer_events", catalog = "agmipapi", schema = "")
@XmlRootElement
public class FertilizerEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FertilizerEventPK fertilizerEventPK;
	@Column(name = "fdate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fdate;
	@Size(max = 255)
    @Column(name = "fecd", length = 255)
	private String fecd;
	@Size(max = 255)
    @Column(name = "feacd", length = 255)
	private String feacd;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "fedep", precision = 12)
	private Float fedep;
	@Column(name = "feamn", precision = 12)
	private Float feamn;
	@Column(name = "feamp", precision = 12)
	private Float feamp;
	@Column(name = "feamk", precision = 12)
	private Float feamk;
	@Column(name = "feamc", precision = 12)
	private Float feamc;
	@Column(name = "feamo", precision = 12)
	private Float feamo;
	@Size(max = 255)
    @Column(name = "feocd", length = 255)
	private String feocd;
	@Size(max = 255)
    @Column(name = "focd", length = 255)
	private String focd;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "fe", referencedColumnName = "fe", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private FertilizerLevel fertilizerLevel;

	public FertilizerEvent() {
	}

	public FertilizerEvent(FertilizerEventPK fertilizerEventPK) {
		this.fertilizerEventPK = fertilizerEventPK;
	}

	public FertilizerEvent(int expId, int fe, int feApplNo) {
		this.fertilizerEventPK = new FertilizerEventPK(expId, fe, feApplNo);
	}

	public FertilizerEventPK getFertilizerEventPK() {
		return fertilizerEventPK;
	}

	public void setFertilizerEventPK(FertilizerEventPK fertilizerEventPK) {
		this.fertilizerEventPK = fertilizerEventPK;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public String getFecd() {
		return fecd;
	}

	public void setFecd(String fecd) {
		this.fecd = fecd;
	}

	public String getFeacd() {
		return feacd;
	}

	public void setFeacd(String feacd) {
		this.feacd = feacd;
	}

	public Float getFedep() {
		return fedep;
	}

	public void setFedep(Float fedep) {
		this.fedep = fedep;
	}

	public Float getFeamn() {
		return feamn;
	}

	public void setFeamn(Float feamn) {
		this.feamn = feamn;
	}

	public Float getFeamp() {
		return feamp;
	}

	public void setFeamp(Float feamp) {
		this.feamp = feamp;
	}

	public Float getFeamk() {
		return feamk;
	}

	public void setFeamk(Float feamk) {
		this.feamk = feamk;
	}

	public Float getFeamc() {
		return feamc;
	}

	public void setFeamc(Float feamc) {
		this.feamc = feamc;
	}

	public Float getFeamo() {
		return feamo;
	}

	public void setFeamo(Float feamo) {
		this.feamo = feamo;
	}

	public String getFeocd() {
		return feocd;
	}

	public void setFeocd(String feocd) {
		this.feocd = feocd;
	}

	public String getFocd() {
		return focd;
	}

	public void setFocd(String focd) {
		this.focd = focd;
	}

	public FertilizerLevel getFertilizerLevel() {
		return fertilizerLevel;
	}

	public void setFertilizerLevel(FertilizerLevel fertilizerLevel) {
		this.fertilizerLevel = fertilizerLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (fertilizerEventPK != null ? fertilizerEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FertilizerEvent)) {
			return false;
		}
		FertilizerEvent other = (FertilizerEvent) object;
		if ((this.fertilizerEventPK == null && other.fertilizerEventPK != null) || (this.fertilizerEventPK != null && !this.fertilizerEventPK.equals(other.fertilizerEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.FertilizerEvent[ fertilizerEventPK=" + fertilizerEventPK + " ]";
	}

}
