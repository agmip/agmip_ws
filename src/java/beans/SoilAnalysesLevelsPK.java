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
public class SoilAnalysesLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sa")
    private int sa;

    public SoilAnalysesLevelsPK() {
    }

    public SoilAnalysesLevelsPK(int expId, int sa) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoilAnalysesLevelsPK)) {
            return false;
        }
        SoilAnalysesLevelsPK other = (SoilAnalysesLevelsPK) object;
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
        return "beans.SoilAnalysesLevelsPK[ expId=" + expId + ", sa=" + sa + " ]";
    }
    
}
