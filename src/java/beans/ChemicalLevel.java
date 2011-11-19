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
@Table(name = "chemical_levels")
@XmlRootElement
public class ChemicalLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "chemicalLevel")
	private Collection<ChemicalEvent> chemicalEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChemicalLevelPK chemicalLevelPK;
    @Size(max = 255)
    @Column(name = "ch_name")
    private String chName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ch_notes")
    private String chNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chemicalLevels")
    private Collection<Treatment> treatmentsCollection;

    public ChemicalLevel() {
    }

    public ChemicalLevel(ChemicalLevelPK chemicalLevelPK) {
        this.chemicalLevelPK = chemicalLevelPK;
    }

    public ChemicalLevel(int expId, int ch) {
        this.chemicalLevelPK = new ChemicalLevelPK(expId, ch);
    }

    public ChemicalLevelPK getChemicalLevelPK() {
        return chemicalLevelPK;
    }

    public void setChemicalLevelPK(ChemicalLevelPK chemicalLevelPK) {
        this.chemicalLevelPK = chemicalLevelPK;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNotes() {
        return chNotes;
    }

    public void setChNotes(String chNotes) {
        this.chNotes = chNotes;
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
        hash += (chemicalLevelPK != null ? chemicalLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChemicalLevel)) {
            return false;
        }
        ChemicalLevel other = (ChemicalLevel) object;
        if ((this.chemicalLevelPK == null && other.chemicalLevelPK != null) || (this.chemicalLevelPK != null && !this.chemicalLevelPK.equals(other.chemicalLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ChemicalLevel[ chemicalLevelPK=" + chemicalLevelPK + " ]";
    }

	@XmlTransient
	public Collection<ChemicalEvent> getChemicalEventsCollection() {
		return chemicalEventsCollection;
	}

	public void setChemicalEventsCollection(Collection<ChemicalEvent> chemicalEventsCollection) {
		this.chemicalEventsCollection = chemicalEventsCollection;
	}

}
