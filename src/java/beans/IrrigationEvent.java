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
@Table(name = "irrigation_events", catalog = "agmipapi", schema = "")

public class IrrigationEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected IrrigationEventPK irrigationEventPK;
	@Column(name = "idate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date idate;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "iradp", precision = 12)
	private Float iradp;
	@Size(max = 255)
    @Column(name = "irop", length = 255)
	private String irop;
	@Column(name = "irval", precision = 12)
	private Float irval;
	@Column(name = "irnpct", precision = 12)
	private Float irnpct;
	@Column(name = "abund", precision = 12)
	private Float abund;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ir", referencedColumnName = "ir", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private IrrigationLevel irrigationLevel;

	public IrrigationEvent() {
	}

	public IrrigationEvent(IrrigationEventPK irrigationEventPK) {
		this.irrigationEventPK = irrigationEventPK;
	}

	public IrrigationEvent(int expId, int ir, int irAppNo) {
		this.irrigationEventPK = new IrrigationEventPK(expId, ir, irAppNo);
	}

	public IrrigationEventPK getIrrigationEventPK() {
		return irrigationEventPK;
	}

	public void setIrrigationEventPK(IrrigationEventPK irrigationEventPK) {
		this.irrigationEventPK = irrigationEventPK;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public Float getIradp() {
		return iradp;
	}

	public void setIradp(Float iradp) {
		this.iradp = iradp;
	}

	public String getIrop() {
		return irop;
	}

	public void setIrop(String irop) {
		this.irop = irop;
	}

	public Float getIrval() {
		return irval;
	}

	public void setIrval(Float irval) {
		this.irval = irval;
	}

	public Float getIrnpct() {
		return irnpct;
	}

	public void setIrnpct(Float irnpct) {
		this.irnpct = irnpct;
	}

	public Float getAbund() {
		return abund;
	}

	public void setAbund(Float abund) {
		this.abund = abund;
	}

	public IrrigationLevel getIrrigationLevel() {
		return irrigationLevel;
	}

	public void setIrrigationLevel(IrrigationLevel irrigationLevel) {
		this.irrigationLevel = irrigationLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (irrigationEventPK != null ? irrigationEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof IrrigationEvent)) {
			return false;
		}
		IrrigationEvent other = (IrrigationEvent) object;
		if ((this.irrigationEventPK == null && other.irrigationEventPK != null) || (this.irrigationEventPK != null && !this.irrigationEventPK.equals(other.irrigationEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.IrrigationEvents[ irrigationEventsPK=" + irrigationEventPK + " ]";
	}

}
