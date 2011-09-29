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
@Table(name = "organic_material_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganicMaterialLevels.findAll", query = "SELECT o FROM OrganicMaterialLevels o"),
    @NamedQuery(name = "OrganicMaterialLevels.findByExpId", query = "SELECT o FROM OrganicMaterialLevels o WHERE o.organicMaterialLevelsPK.expId = :expId"),
    @NamedQuery(name = "OrganicMaterialLevels.findByOm", query = "SELECT o FROM OrganicMaterialLevels o WHERE o.organicMaterialLevelsPK.om = :om"),
    @NamedQuery(name = "OrganicMaterialLevels.findByOmName", query = "SELECT o FROM OrganicMaterialLevels o WHERE o.omName = :omName")})
public class OrganicMaterialLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrganicMaterialLevelsPK organicMaterialLevelsPK;
    @Size(max = 255)
    @Column(name = "om_name")
    private String omName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "om_notes")
    private String omNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organicMaterialLevels")
    private Collection<Treatments> treatmentsCollection;

    public OrganicMaterialLevels() {
    }

    public OrganicMaterialLevels(OrganicMaterialLevelsPK organicMaterialLevelsPK) {
        this.organicMaterialLevelsPK = organicMaterialLevelsPK;
    }

    public OrganicMaterialLevels(int expId, int om) {
        this.organicMaterialLevelsPK = new OrganicMaterialLevelsPK(expId, om);
    }

    public OrganicMaterialLevelsPK getOrganicMaterialLevelsPK() {
        return organicMaterialLevelsPK;
    }

    public void setOrganicMaterialLevelsPK(OrganicMaterialLevelsPK organicMaterialLevelsPK) {
        this.organicMaterialLevelsPK = organicMaterialLevelsPK;
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
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organicMaterialLevelsPK != null ? organicMaterialLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganicMaterialLevels)) {
            return false;
        }
        OrganicMaterialLevels other = (OrganicMaterialLevels) object;
        if ((this.organicMaterialLevelsPK == null && other.organicMaterialLevelsPK != null) || (this.organicMaterialLevelsPK != null && !this.organicMaterialLevelsPK.equals(other.organicMaterialLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.OrganicMaterialLevels[ organicMaterialLevelsPK=" + organicMaterialLevelsPK + " ]";
    }
    
}
