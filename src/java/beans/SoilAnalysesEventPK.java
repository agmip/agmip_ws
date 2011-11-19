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
public class SoilAnalysesEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "sa", nullable = false)
	private int sa;
	@Basic(optional = false)
    @NotNull
    @Column(name = "sa_appl_no", nullable = false)
	private int saApplNo;

	public SoilAnalysesEventPK() {
	}

	public SoilAnalysesEventPK(int expId, int sa, int saApplNo) {
		this.expId = expId;
		this.sa = sa;
		this.saApplNo = saApplNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getSa() {
		return sa;
	}

	public void setSa(int sa) {
		this.sa = sa;
	}

	public int getSaApplNo() {
		return saApplNo;
	}

	public void setSaApplNo(int saApplNo) {
		this.saApplNo = saApplNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) sa;
		hash += (int) saApplNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SoilAnalysesEventPK)) {
			return false;
		}
		SoilAnalysesEventPK other = (SoilAnalysesEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.sa != other.sa) {
			return false;
		}
		if (this.saApplNo != other.saApplNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilAnalysesEventPK[ expId=" + expId + ", sa=" + sa + ", saApplNo=" + saApplNo + " ]";
	}

}
