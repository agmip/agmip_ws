/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wpavan
 */
@Entity
@Table(name = "mulch_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MulchLevels.findAll", query = "SELECT m FROM MulchLevels m"),
    @NamedQuery(name = "MulchLevels.findByExpId", query = "SELECT m FROM MulchLevels m WHERE m.mulchLevelsPK.expId = :expId"),
    @NamedQuery(name = "MulchLevels.findByMl", query = "SELECT m FROM MulchLevels m WHERE m.mulchLevelsPK.ml = :ml"),
    @NamedQuery(name = "MulchLevels.findByMlName", query = "SELECT m FROM MulchLevels m WHERE m.mlName = :mlName")})
public class MulchLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MulchLevelsPK mulchLevelsPK;
    @Size(max = 255)
    @Column(name = "ml_name")
    private String mlName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ml_notes")
    private String mlNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mulchLevels")
    private Collection<Treatments> treatmentsCollection;

    public MulchLevels() {
    }

    public MulchLevels(MulchLevelsPK mulchLevelsPK) {
        this.mulchLevelsPK = mulchLevelsPK;
    }

    public MulchLevels(int expId, int ml) {
        this.mulchLevelsPK = new MulchLevelsPK(expId, ml);
    }

    public MulchLevelsPK getMulchLevelsPK() {
        return mulchLevelsPK;
    }

    public void setMulchLevelsPK(MulchLevelsPK mulchLevelsPK) {
        this.mulchLevelsPK = mulchLevelsPK;
    }

    public String getMlName() {
        return mlName;
    }

    public void setMlName(String mlName) {
        this.mlName = mlName;
    }

    public String getMlNotes() {
        return mlNotes;
    }

    public void setMlNotes(String mlNotes) {
        this.mlNotes = mlNotes;
    }

    @XmlTransient
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mulchLevelsPK != null ? mulchLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MulchLevels)) {
            return false;
        }
        MulchLevels other = (MulchLevels) object;
        if ((this.mulchLevelsPK == null && other.mulchLevelsPK != null) || (this.mulchLevelsPK != null && !this.mulchLevelsPK.equals(other.mulchLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.MulchLevels[ mulchLevelsPK=" + mulchLevelsPK + " ]";
    }
    
}
