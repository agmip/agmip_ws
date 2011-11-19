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
public class InitialConditionEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id", nullable = false)
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ic", nullable = false)
	private int ic;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ic_layer", nullable = false)
	private int icLayer;

	public InitialConditionEventPK() {
	}

	public InitialConditionEventPK(int expId, int ic, int icLayer) {
		this.expId = expId;
		this.ic = ic;
		this.icLayer = icLayer;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getIc() {
		return ic;
	}

	public void setIc(int ic) {
		this.ic = ic;
	}

	public int getIcLayer() {
		return icLayer;
	}

	public void setIcLayer(int icLayer) {
		this.icLayer = icLayer;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) ic;
		hash += (int) icLayer;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof InitialConditionEventPK)) {
			return false;
		}
		InitialConditionEventPK other = (InitialConditionEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.ic != other.ic) {
			return false;
		}
		if (this.icLayer != other.icLayer) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.InitialConditionEventsPK[ expId=" + expId + ", ic=" + ic + ", icLayer=" + icLayer + " ]";
	}

}
