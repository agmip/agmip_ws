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
@Table(name = "fertilizer_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FertilizerLevels.findAll", query = "SELECT f FROM FertilizerLevels f"),
    @NamedQuery(name = "FertilizerLevels.findByExpId", query = "SELECT f FROM FertilizerLevels f WHERE f.fertilizerLevelsPK.expId = :expId"),
    @NamedQuery(name = "FertilizerLevels.findByFe", query = "SELECT f FROM FertilizerLevels f WHERE f.fertilizerLevelsPK.fe = :fe"),
    @NamedQuery(name = "FertilizerLevels.findByFeName", query = "SELECT f FROM FertilizerLevels f WHERE f.feName = :feName")})
public class FertilizerLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FertilizerLevelsPK fertilizerLevelsPK;
    @Size(max = 255)
    @Column(name = "fe_name")
    private String feName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "fe_comments")
    private String feComments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fertilizerLevels")
    private Collection<Treatments> treatmentsCollection;

    public FertilizerLevels() {
    }

    public FertilizerLevels(FertilizerLevelsPK fertilizerLevelsPK) {
        this.fertilizerLevelsPK = fertilizerLevelsPK;
    }

    public FertilizerLevels(int expId, int fe) {
        this.fertilizerLevelsPK = new FertilizerLevelsPK(expId, fe);
    }

    public FertilizerLevelsPK getFertilizerLevelsPK() {
        return fertilizerLevelsPK;
    }

    public void setFertilizerLevelsPK(FertilizerLevelsPK fertilizerLevelsPK) {
        this.fertilizerLevelsPK = fertilizerLevelsPK;
    }

    public String getFeName() {
        return feName;
    }

    public void setFeName(String feName) {
        this.feName = feName;
    }

    public String getFeComments() {
        return feComments;
    }

    public void setFeComments(String feComments) {
        this.feComments = feComments;
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
        hash += (fertilizerLevelsPK != null ? fertilizerLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FertilizerLevels)) {
            return false;
        }
        FertilizerLevels other = (FertilizerLevels) object;
        if ((this.fertilizerLevelsPK == null && other.fertilizerLevelsPK != null) || (this.fertilizerLevelsPK != null && !this.fertilizerLevelsPK.equals(other.fertilizerLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.FertilizerLevels[ fertilizerLevelsPK=" + fertilizerLevelsPK + " ]";
    }
    
}
