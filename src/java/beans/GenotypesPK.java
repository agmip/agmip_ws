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
public class GenotypesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ge")
    private int ge;

    public GenotypesPK() {
    }

    public GenotypesPK(int expId, int ge) {
        this.expId = expId;
        this.ge = ge;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getGe() {
        return ge;
    }

    public void setGe(int ge) {
        this.ge = ge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ge;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenotypesPK)) {
            return false;
        }
        GenotypesPK other = (GenotypesPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ge != other.ge) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.GenotypesPK[ expId=" + expId + ", ge=" + ge + " ]";
    }
    
}
