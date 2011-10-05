/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wpavan
 */
@Embeddable
public class TreatmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_id")
    private int expId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trno")
    private int trno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rp")
    private int rp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sq")
    private int sq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "op")
    private int op;
    @Basic(optional = false)
    @NotNull
    @Column(name = "co")
    private int co;

    public TreatmentPK() {
    }

    public TreatmentPK(int expId, int trno, int rp, int sq, int op, int co) {
        this.expId = expId;
        this.trno = trno;
        this.rp = rp;
        this.sq = sq;
        this.op = op;
        this.co = co;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getTrno() {
        return trno;
    }

    public void setTrno(int trno) {
        this.trno = trno;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public int getSq() {
        return sq;
    }

    public void setSq(int sq) {
        this.sq = sq;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public int getCo() {
        return co;
    }

    public void setCo(int co) {
        this.co = co;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) expId;
        hash += (int) trno;
        hash += (int) rp;
        hash += (int) sq;
        hash += (int) op;
        hash += (int) co;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TreatmentPK)) {
            return false;
        }
        TreatmentPK other = (TreatmentPK) object;
        if (this.expId != other.expId) {
            return false;
        }
        if (this.trno != other.trno) {
            return false;
        }
        if (this.rp != other.rp) {
            return false;
        }
        if (this.sq != other.sq) {
            return false;
        }
        if (this.op != other.op) {
            return false;
        }
        if (this.co != other.co) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.TreatmentPK[ expId=" + expId + ", trno=" + trno + ", rp=" + rp + ", sq=" + sq + ", op=" + op + ", co=" + co + " ]";
    }

}
