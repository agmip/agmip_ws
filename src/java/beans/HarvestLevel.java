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
public class HarvestLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "harvestLevel")
	private Collection<HarvestEvent> harvestEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HarvestLevelPK harvestLevelPK;
    @Size(max = 255)
    @Column(name = "ha_name")
    private String haName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ha_notes")
    private String haNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "harvestLevels")
    private Collection<Treatment> treatmentsCollection;

    public HarvestLevel() {
    }

    public HarvestLevel(HarvestLevelPK harvestLevelPK) {
        this.harvestLevelPK = harvestLevelPK;
    }

    public HarvestLevel(int expId, int ha) {
        this.harvestLevelPK = new HarvestLevelPK(expId, ha);
    }

    public HarvestLevelPK getHarvestLevelPK() {
        return harvestLevelPK;
    }

    public void setHarvestLevelPK(HarvestLevelPK harvestLevelPK) {
        this.harvestLevelPK = harvestLevelPK;
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
        hash += (harvestLevelPK != null ? harvestLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HarvestLevel)) {
            return false;
        }
        HarvestLevel other = (HarvestLevel) object;
        if ((this.harvestLevelPK == null && other.harvestLevelPK != null) || (this.harvestLevelPK != null && !this.harvestLevelPK.equals(other.harvestLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.HarvestLevel[ harvestLevelPK=" + harvestLevelPK + " ]";
    }

	@XmlTransient
	public Collection<HarvestEvent> getHarvestEventsCollection() {
		return harvestEventsCollection;
	}

	public void setHarvestEventsCollection(Collection<HarvestEvent> harvestEventsCollection) {
		this.harvestEventsCollection = harvestEventsCollection;
	}

}
