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
public class IrrigationLevelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ir")
    private int ir;

    public IrrigationLevelPK() {
    }

    public IrrigationLevelPK(int expId, int ir) {
        this.expId = expId;
        this.ir = ir;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) ir;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IrrigationLevelPK)) {
            return false;
        }
        IrrigationLevelPK other = (IrrigationLevelPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.ir != other.ir) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.IrrigationLevelPK[ expId=" + expId + ", ir=" + ir + " ]";
    }
}