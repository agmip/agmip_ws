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
 * @author fonini
 */
@Entity
@Table(name = "organic_material_levels")
@XmlRootElement
public class OrganicMaterialLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrganicMaterialLevelPK organicMaterialLevelPK;
    @Size(max = 255)
    @Column(name = "om_name")
    private String omName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "om_notes")
    private String omNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organicMaterialLevel")
    private Collection<Treatment> treatmentsCollection;

    public OrganicMaterialLevel() {
    }

    public OrganicMaterialLevel(OrganicMaterialLevelPK organicMaterialLevelPK) {
        this.organicMaterialLevelPK = organicMaterialLevelPK;
    }

    public OrganicMaterialLevel(int expId, int om) {
        this.organicMaterialLevelPK = new OrganicMaterialLevelPK(expId, om);
    }

    public OrganicMaterialLevelPK getOrganicMaterialLevelPK() {
        return organicMaterialLevelPK;
    }

    public void setOrganicMaterialLevelPK(OrganicMaterialLevelPK organicMaterialLevelPK) {
        this.organicMaterialLevelPK = organicMaterialLevelPK;
    }

    public String getOmName() {
        return omName;
    }

    public void setOmName(String omName) {
        this.omName = omName;
    }

    public String getOmNotes() {
        return omNotes;
    }

    public void setOmNotes(String omNotes) {
        this.omNotes = omNotes;
    }

    @XmlTransient
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organicMaterialLevelPK != null ? organicMaterialLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganicMaterialLevel)) {
            return false;
        }
        OrganicMaterialLevel other = (OrganicMaterialLevel) object;
        if ((this.organicMaterialLevelPK == null && other.organicMaterialLevelPK != null) || (this.organicMaterialLevelPK != null && !this.organicMaterialLevelPK.equals(other.organicMaterialLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.OrganicMaterialLevel[ organicMaterialLevelPK=" + organicMaterialLevelPK + " ]";
    }

}
