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
public class OrganicMaterialLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "om")
    private int om;

    public OrganicMaterialLevelPK() {
    }

    public OrganicMaterialLevelPK(int expId, int om) {
        this.expId = expId;
        this.om = om;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getOm() {
        return om;
    }

    public void setOm(int om) {
        this.om = om;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) om;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrganicMaterialLevelPK)) {
            return false;
        }
        OrganicMaterialLevelPK other = (OrganicMaterialLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.om != other.om) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.OrganicMaterialLevelPK[ expId=" + expId + ", om=" + om + " ]";
    }
}