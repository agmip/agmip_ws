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
@Table(name = "harvest_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HarvestLevels.findAll", query = "SELECT h FROM HarvestLevels h"),
    @NamedQuery(name = "HarvestLevels.findByExpId", query = "SELECT h FROM HarvestLevels h WHERE h.harvestLevelsPK.expId = :expId"),
    @NamedQuery(name = "HarvestLevels.findByHa", query = "SELECT h FROM HarvestLevels h WHERE h.harvestLevelsPK.ha = :ha"),
    @NamedQuery(name = "HarvestLevels.findByHaName", query = "SELECT h FROM HarvestLevels h WHERE h.haName = :haName")})
public class HarvestLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HarvestLevelsPK harvestLevelsPK;
    @Size(max = 255)
    @Column(name = "ha_name")
    private String haName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ha_notes")
    private String haNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "harvestLevels")
    private Collection<Treatment> treatmentsCollection;

    public HarvestLevels() {
    }

    public HarvestLevels(HarvestLevelsPK harvestLevelsPK) {
        this.harvestLevelsPK = harvestLevelsPK;
    }

    public HarvestLevels(int expId, int ha) {
        this.harvestLevelsPK = new HarvestLevelsPK(expId, ha);
    }

    public HarvestLevelsPK getHarvestLevelsPK() {
        return harvestLevelsPK;
    }

    public void setHarvestLevelsPK(HarvestLevelsPK harvestLevelsPK) {
        this.harvestLevelsPK = harvestLevelsPK;
    }

    public String getHaName() {
        return haName;
    }

    public void setHaName(String haName) {
        this.haName = haName;
    }

    public String getHaNotes() {
        return haNotes;
    }

    public void setHaNotes(String haNotes) {
        this.haNotes = haNotes;
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
        hash += (harvestLevelsPK != null ? harvestLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HarvestLevels)) {
            return false;
        }
        HarvestLevels other = (HarvestLevels) object;
        if ((this.harvestLevelsPK == null && other.harvestLevelsPK != null) || (this.harvestLevelsPK != null && !this.harvestLevelsPK.equals(other.harvestLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.HarvestLevels[ harvestLevelsPK=" + harvestLevelsPK + " ]";
    }

}
