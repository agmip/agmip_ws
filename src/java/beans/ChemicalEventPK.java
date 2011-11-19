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
public class ChemicalEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ch")
	private int ch;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ch_appl_no")
	private int chApplNo;

	public ChemicalEventPK() {
	}

	public ChemicalEventPK(int expId, int ch, int chApplNo) {
		this.expId = expId;
		this.ch = ch;
		this.chApplNo = chApplNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public int getChApplNo() {
		return chApplNo;
	}

	public void setChApplNo(int chApplNo) {
		this.chApplNo = chApplNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ch;
		hash += (int) chApplNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ChemicalEventPK)) {
			return false;
		}
		ChemicalEventPK other = (ChemicalEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ch != other.ch) {
			return false;
		}
		if (this.chApplNo != other.chApplNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.ChemicalEventPK[ expId=" + expId + ", ch=" + ch + ", chApplNo=" + chApplNo + " ]";
	}
}