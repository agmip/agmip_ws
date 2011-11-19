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
public class GenotypePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ge")
    private int ge;

    public GenotypePK() {
    }

    public GenotypePK(int expId, int ge) {
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
        if (!(object instanceof GenotypePK)) {
            return false;
        }
        GenotypePK other = (GenotypePK) object;
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
        return "beans.GenotypePK[ expId=" + expId + ", ge=" + ge + " ]";
    }
}