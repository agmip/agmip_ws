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
public class HarvestLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ha")
    private int ha;

    public HarvestLevelsPK() {
    }

    public HarvestLevelsPK(int expId, int ha) {
        this.expId = expId;
        this.ha = ha;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getHa() {
        return ha;
    }

    public void setHa(int ha) {
        this.ha = ha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HarvestLevelsPK)) {
            return false;
        }
        HarvestLevelsPK other = (HarvestLevelsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ha != other.ha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.HarvestLevelsPK[ expId=" + expId + ", ha=" + ha + " ]";
    }
    
}
