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
public class ChemicalLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ch")
    private int ch;

    public ChemicalLevelPK() {
    }

    public ChemicalLevelPK(int expId, int ch) {
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
        if (!(object instanceof ChemicalLevelPK)) {
            return false;
        }
        ChemicalLevelPK other = (ChemicalLevelPK) object;
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
        return "beans.ChemicalLevelPK[ expId=" + expId + ", ch=" + ch + " ]";
    }
}