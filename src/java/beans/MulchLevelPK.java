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
public class MulchLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ml")
    private int ml;

    public MulchLevelPK() {
    }

    public MulchLevelPK(int expId, int ml) {
        this.expId = expId;
        this.ml = ml;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ml;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MulchLevelPK)) {
            return false;
        }
        MulchLevelPK other = (MulchLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ml != other.ml) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.MulchLevelPK[ expId=" + expId + ", ml=" + ml + " ]";
    }
}