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
public class TillageLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ti")
    private int ti;

    public TillageLevelPK() {
    }

    public TillageLevelPK(int expId, int ti) {
        this.expId = expId;
        this.ti = ti;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ti;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TillageLevelPK)) {
            return false;
        }
        TillageLevelPK other = (TillageLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ti != other.ti) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.TillageLevelsPK[ expId=" + expId + ", ti=" + ti + " ]";
    }

}
