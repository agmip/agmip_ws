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
public class FertilizerLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fe")
    private int fe;

    public FertilizerLevelsPK() {
    }

    public FertilizerLevelsPK(int expId, int fe) {
        this.expId = expId;
        this.fe = fe;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) fe;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FertilizerLevelsPK)) {
            return false;
        }
        FertilizerLevelsPK other = (FertilizerLevelsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.fe != other.fe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.FertilizerLevelsPK[ expId=" + expId + ", fe=" + fe + " ]";
    }
    
}
