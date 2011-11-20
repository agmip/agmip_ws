package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "tillage_levels")
@XmlRootElement
public class TillageLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tillageLevel")
	private Collection<TillageEvent> tillageEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TillageLevelPK tillageLevelPK;
    @Size(max = 255)
    @Column(name = "ti_name")
    private String tiName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ti_notes")
    private String tiNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tillageLevel")
    private Collection<Treatment> treatmentsCollection;

    public TillageLevel() {
    }

    public TillageLevel(TillageLevelPK tillageLevelPK) {
        this.tillageLevelPK = tillageLevelPK;
    }

    public TillageLevel(int expId, int ti) {
        this.tillageLevelPK = new TillageLevelPK(expId, ti);
    }

    public TillageLevelPK getTillageLevelPK() {
        return tillageLevelPK;
    }

    public void setTillageLevelPK(TillageLevelPK tillageLevelPK) {
        this.tillageLevelPK = tillageLevelPK;
    }

    public String getTiName() {
        return tiName;
    }

    public void setTiName(String tiName) {
        this.tiName = tiName;
    }

    public String getTiNotes() {
        return tiNotes;
    }

    public void setTiNotes(String tiNotes) {
        this.tiNotes = tiNotes;
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
        hash += (tillageLevelPK != null ? tillageLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TillageLevel)) {
            return false;
        }
        TillageLevel other = (TillageLevel) object;
        if ((this.tillageLevelPK == null && other.tillageLevelPK != null) || (this.tillageLevelPK != null && !this.tillageLevelPK.equals(other.tillageLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.TillageLevel[ tillageLevelPK=" + tillageLevelPK + " ]";
    }

	@XmlTransient
	public Collection<TillageEvent> getTillageEventsCollection() {
		return tillageEventsCollection;
	}

	public void setTillageEventsCollection(Collection<TillageEvent> tillageEventsCollection) {
		this.tillageEventsCollection = tillageEventsCollection;
	}
}