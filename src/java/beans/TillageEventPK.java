/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TillageEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ti", nullable = false)
	private int ti;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ti_ops_no", nullable = false)
	private int tiOpsNo;

	public TillageEventPK() {
	}

	public TillageEventPK(int expId, int ti, int tiOpsNo) {
		this.expId = expId;
		this.ti = ti;
		this.tiOpsNo = tiOpsNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getTi() {
		return ti;
	}

	public void setTi(int ti) {
		this.ti = ti;
	}

	public int getTiOpsNo() {
		return tiOpsNo;
	}

	public void setTiOpsNo(int tiOpsNo) {
		this.tiOpsNo = tiOpsNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ti;
		hash += (int) tiOpsNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TillageEventPK)) {
			return false;
		}
		TillageEventPK other = (TillageEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ti != other.ti) {
			return false;
		}
		if (this.tiOpsNo != other.tiOpsNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.TillageEventsPK[ expId=" + expId + ", ti=" + ti + ", tiOpsNo=" + tiOpsNo + " ]";
	}

}
