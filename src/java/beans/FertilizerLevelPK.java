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
public class FertilizerLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fe")
    private int fe;

    public FertilizerLevelPK() {
    }

    public FertilizerLevelPK(int expId, int fe) {
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
        if (!(object instanceof FertilizerLevelPK)) {
            return false;
        }
        FertilizerLevelPK other = (FertilizerLevelPK) object;
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
        return "beans.FertilizerLevelPK[ expId=" + expId + ", fe=" + fe + " ]";
    }
}
