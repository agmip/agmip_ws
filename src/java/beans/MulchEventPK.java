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
public class MulchEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ml", nullable = false)
	private int ml;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ml_appl_no", nullable = false)
	private int mlApplNo;

	public MulchEventPK() {
	}

	public MulchEventPK(int expId, int ml, int mlApplNo) {
		this.expId = expId;
		this.ml = ml;
		this.mlApplNo = mlApplNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getMl() {
		return ml;
	}

	public void setMl(int ml) {
		this.ml = ml;
	}

	public int getMlApplNo() {
		return mlApplNo;
	}

	public void setMlApplNo(int mlApplNo) {
		this.mlApplNo = mlApplNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ml;
		hash += (int) mlApplNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MulchEventPK)) {
			return false;
		}
		MulchEventPK other = (MulchEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ml != other.ml) {
			return false;
		}
		if (this.mlApplNo != other.mlApplNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.MulchEventPK[ expId=" + expId + ", ml=" + ml + ", mlApplNo=" + mlApplNo + " ]";
	}
}