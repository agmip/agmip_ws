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
public class IrrigationLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ir")
    private int ir;

    public IrrigationLevelsPK() {
    }

    public IrrigationLevelsPK(int expId, int ir) {
        this.expId = expId;
        this.ir = ir;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ir;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IrrigationLevelsPK)) {
            return false;
        }
        IrrigationLevelsPK other = (IrrigationLevelsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ir != other.ir) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.IrrigationLevelsPK[ expId=" + expId + ", ir=" + ir + " ]";
    }
    
}
