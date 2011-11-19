package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fonini
 */
@Embeddable
public class IrrigationEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ir", nullable = false)
	private int ir;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ir_app_no", nullable = false)
	private int irAppNo;

	public IrrigationEventPK() {
	}

	public IrrigationEventPK(int expId, int ir, int irAppNo) {
		this.expId = expId;
		this.ir = ir;
		this.irAppNo = irAppNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getIr() {
		return ir;
	}

	public void setIr(int ir) {
		this.ir = ir;
	}

	public int getIrAppNo() {
		return irAppNo;
	}

	public void setIrAppNo(int irAppNo) {
		this.irAppNo = irAppNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ir;
		hash += (int) irAppNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof IrrigationEventPK)) {
			return false;
		}
		IrrigationEventPK other = (IrrigationEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ir != other.ir) {
			return false;
		}
		if (this.irAppNo != other.irAppNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.IrrigationEventsPK[ expId=" + expId + ", ir=" + ir + ", irAppNo=" + irAppNo + " ]";
	}

}
