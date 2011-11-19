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
public class HarvestLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ha")
    private int ha;

    public HarvestLevelPK() {
    }

    public HarvestLevelPK(int expId, int ha) {
        this.expId = expId;
        this.ha = ha;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getHa() {
        return ha;
    }

    public void setHa(int ha) {
        this.ha = ha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HarvestLevelPK)) {
            return false;
        }
        HarvestLevelPK other = (HarvestLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ha != other.ha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.HarvestLevelPK[ expId=" + expId + ", ha=" + ha + " ]";
    }
}