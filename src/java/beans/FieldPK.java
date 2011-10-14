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
public class FieldPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fl")
    private int fl;

    public FieldPK() {
    }

    public FieldPK(int expId, int fl) {
        this.expId = expId;
        this.fl = fl;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) fl;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FieldPK)) {
            return false;
        }
        FieldPK other = (FieldPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.fl != other.fl) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.FieldsPK[ expId=" + expId + ", fl=" + fl + " ]";
    }

}
