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
@Table(name = "mulch_events", catalog = "agmipapi", schema = "")
@XmlRootElement
public class MulchEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MulchEventPK mulchEventPK;
	@Column(name = "mldat")
    @Temporal(TemporalType.TIMESTAMP)
	private Date mldat;
	@Column(name = "mlrdat")
    @Temporal(TemporalType.TIMESTAMP)
	private Date mlrdat;
	@Size(max = 255)
    @Column(name = "mltp", length = 255)
	private String mltp;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "mlthk", precision = 12)
	private Float mlthk;
	@Size(max = 255)
    @Column(name = "mlcol", length = 255)
	private String mlcol;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ml", referencedColumnName = "ml", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private MulchLevel mulchLevel;

	public MulchEvent() {
	}

	public MulchEvent(MulchEventPK mulchEventPK) {
		this.mulchEventPK = mulchEventPK;
	}

	public MulchEvent(int expId, int ml, int mlApplNo) {
		this.mulchEventPK = new MulchEventPK(expId, ml, mlApplNo);
	}

	public MulchEventPK getMulchEventPK() {
		return mulchEventPK;
	}

	public void setMulchEventPK(MulchEventPK mulchEventPK) {
		this.mulchEventPK = mulchEventPK;
	}

	public Date getMldat() {
		return mldat;
	}

	public void setMldat(Date mldat) {
		this.mldat = mldat;
	}

	public Date getMlrdat() {
		return mlrdat;
	}

	public void setMlrdat(Date mlrdat) {
		this.mlrdat = mlrdat;
	}

	public String getMltp() {
		return mltp;
	}

	public void setMltp(String mltp) {
		this.mltp = mltp;
	}

	public Float getMlthk() {
		return mlthk;
	}

	public void setMlthk(Float mlthk) {
		this.mlthk = mlthk;
	}

	public String getMlcol() {
		return mlcol;
	}

	public void setMlcol(String mlcol) {
		this.mlcol = mlcol;
	}

	public MulchLevel getMulchLevel() {
		return mulchLevel;
	}

	public void setMulchLevel(MulchLevel mulchLevel) {
		this.mulchLevel = mulchLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mulchEventPK != null ? mulchEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MulchEvent)) {
			return false;
		}
		MulchEvent other = (MulchEvent) object;
		if ((this.mulchEventPK == null && other.mulchEventPK != null) || (this.mulchEventPK != null && !this.mulchEventPK.equals(other.mulchEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.MulchEvent[ mulchEventPK=" + mulchEventPK + " ]";
	}

}
