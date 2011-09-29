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
public class PlantingsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pl")
    private int pl;

    public PlantingsPK() {
    }

    public PlantingsPK(int expId, int pl) {
        this.expId = expId;
        this.pl = pl;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getPl() {
        return pl;
    }

    public void setPl(int pl) {
        this.pl = pl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) pl;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantingsPK)) {
            return false;
        }
        PlantingsPK other = (PlantingsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.pl != other.pl) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.PlantingsPK[ expId=" + expId + ", pl=" + pl + " ]";
    }
    
}
