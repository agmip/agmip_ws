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
public class OrganicMaterialEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "om", nullable = false)
	private int om;
	@Basic(optional = false)
    @NotNull
    @Column(name = "om_ops_no", nullable = false)
	private int omOpsNo;

	public OrganicMaterialEventPK() {
	}

	public OrganicMaterialEventPK(int expId, int om, int omOpsNo) {
		this.expId = expId;
		this.om = om;
		this.omOpsNo = omOpsNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getOm() {
		return om;
	}

	public void setOm(int om) {
		this.om = om;
	}

	public int getOmOpsNo() {
		return omOpsNo;
	}

	public void setOmOpsNo(int omOpsNo) {
		this.omOpsNo = omOpsNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) om;
		hash += (int) omOpsNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OrganicMaterialEventPK)) {
			return false;
		}
		OrganicMaterialEventPK other = (OrganicMaterialEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.om != other.om) {
			return false;
		}
		if (this.omOpsNo != other.omOpsNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.OrganicMaterialEventPK[ expId=" + expId + ", om=" + om + ", omOpsNo=" + omOpsNo + " ]";
	}
}