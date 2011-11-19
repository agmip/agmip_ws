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
public class SoilAnalysesLevelPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "sa", nullable = false)
	private int sa;

	public SoilAnalysesLevelPK() {
	}

	public SoilAnalysesLevelPK(int expId, int sa) {
		this.expId = expId;
		this.sa = sa;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) sa;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilAnalysesLevelPK)) {
			return false;
		}
		SoilAnalysesLevelPK other = (SoilAnalysesLevelPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.sa != other.sa) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilAnalysesLevelPK[ expId=" + expId + ", sa=" + sa + " ]";
	}
}