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
public class FertilizerEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fe", nullable = false)
	private int fe;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fe_appl_no", nullable = false)
	private int feApplNo;

	public FertilizerEventPK() {
	}

	public FertilizerEventPK(int expId, int fe, int feApplNo) {
		this.expId = expId;
		this.fe = fe;
		this.feApplNo = feApplNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getFe() {
		return fe;
	}

	public void setFe(int fe) {
		this.fe = fe;
	}

	public int getFeApplNo() {
		return feApplNo;
	}

	public void setFeApplNo(int feApplNo) {
		this.feApplNo = feApplNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) fe;
		hash += (int) feApplNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FertilizerEventPK)) {
			return false;
		}
		FertilizerEventPK other = (FertilizerEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.fe != other.fe) {
			return false;
		}
		if (this.feApplNo != other.feApplNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.FertilizerEventPK[ expId=" + expId + ", fe=" + fe + ", feApplNo=" + feApplNo + " ]";
	}
}