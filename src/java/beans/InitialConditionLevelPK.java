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
public class InitialConditionLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ic")
    private int ic;

    public InitialConditionLevelPK() {
    }

    public InitialConditionLevelPK(int expId, int ic) {
        this.expId = expId;
        this.ic = ic;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getIc() {
        return ic;
    }

    public void setIc(int ic) {
        this.ic = ic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InitialConditionLevelPK)) {
            return false;
        }
        InitialConditionLevelPK other = (InitialConditionLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ic != other.ic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.InitialConditionLevelPK[ expId=" + expId + ", ic=" + ic + " ]";
    }
}