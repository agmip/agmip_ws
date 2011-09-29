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
 * @author wpavan
 */
@Embeddable
public class InitialConditionLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ic")
    private int ic;

    public InitialConditionLevelsPK() {
    }

    public InitialConditionLevelsPK(int expId, int ic) {
        this.expId = expId;
        this.ic = ic;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InitialConditionLevelsPK)) {
            return false;
        }
        InitialConditionLevelsPK other = (InitialConditionLevelsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ic != other.ic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.InitialConditionLevelsPK[ expId=" + expId + ", ic=" + ic + " ]";
    }
    
}
