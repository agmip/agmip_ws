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
public class EnvironModifEventPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
	private int expId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "em")
	private int em;
	@Basic(optional = false)
    @NotNull
    @Column(name = "env_lev_no")
	private int envLevNo;

	public EnvironModifEventPK() {
	}

	public EnvironModifEventPK(int expId, int em, int envLevNo) {
		this.expId = expId;
		this.em = em;
		this.envLevNo = envLevNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getEm() {
		return em;
	}

	public void setEm(int em) {
		this.em = em;
	}

	public int getEnvLevNo() {
		return envLevNo;
	}

	public void setEnvLevNo(int envLevNo) {
		this.envLevNo = envLevNo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) expId;
		hash += (int) em;
		hash += (int) envLevNo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof EnvironModifEventPK)) {
			return false;
		}
		EnvironModifEventPK other = (EnvironModifEventPK) object;
		if (this.expId != other.expId) {
			return false;
		}
		if (this.em != other.em) {
			return false;
		}
		if (this.envLevNo != other.envLevNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.EnvironModifEventsPK[ expId=" + expId + ", em=" + em + ", envLevNo=" + envLevNo + " ]";
	}

}
