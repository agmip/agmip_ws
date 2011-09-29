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
public class ChemicalLevelsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ch")
    private int ch;

    public ChemicalLevelsPK() {
    }

    public ChemicalLevelsPK(int expId, int ch) {
        this.expId = expId;
        this.ch = ch;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ch;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChemicalLevelsPK)) {
            return false;
        }
        ChemicalLevelsPK other = (ChemicalLevelsPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ch != other.ch) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ChemicalLevelsPK[ expId=" + expId + ", ch=" + ch + " ]";
    }
    
}
