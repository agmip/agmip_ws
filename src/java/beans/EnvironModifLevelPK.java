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
public class EnvironModifLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "em")
    private int em;

    public EnvironModifLevelPK() {
    }

    public EnvironModifLevelPK(int expId, int em) {
        this.expId = expId;
        this.em = em;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) em;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvironModifLevelPK)) {
            return false;
        }
        EnvironModifLevelPK other = (EnvironModifLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.em != other.em) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.EnvironModifLevelPK[ expId=" + expId + ", em=" + em + " ]";
    }

}
