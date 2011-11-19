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
public class HarvestEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ha", nullable = false)
	private int ha;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ha_ops_no", nullable = false)
	private int haOpsNo;

	public HarvestEventPK() {
	}

	public HarvestEventPK(int expId, int ha, int haOpsNo) {
		this.expId = expId;
		this.ha = ha;
		this.haOpsNo = haOpsNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getHa() {
		return ha;
	}

	public void setHa(int ha) {
		this.ha = ha;
	}

	public int getHaOpsNo() {
		return haOpsNo;
	}

	public void setHaOpsNo(int haOpsNo) {
		this.haOpsNo = haOpsNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ha;
		hash += (int) haOpsNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HarvestEventPK)) {
			return false;
		}
		HarvestEventPK other = (HarvestEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ha != other.ha) {
			return false;
		}
		if (this.haOpsNo != other.haOpsNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.HarvestEventPK[ expId=" + expId + ", ha=" + ha + ", haOpsNo=" + haOpsNo + " ]";
	}
}