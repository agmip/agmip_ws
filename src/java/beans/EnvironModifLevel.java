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
@Table(name = "environ_modif_levels")
@XmlRootElement
public class EnvironModifLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "environModifLevel")
	private Collection<EnvironModifEvent> environModifEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvironModifLevelPK environModifLevelPK;
    @Size(max = 255)
    @Column(name = "em_name")
    private String emName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "em_notes")
    private String emNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "environModifLevels")
    private Collection<Treatment> treatmentsCollection;

    public EnvironModifLevel() {
    }

    public EnvironModifLevel(EnvironModifLevelPK environModifLevelPK) {
        this.environModifLevelPK = environModifLevelPK;
    }

    public EnvironModifLevel(int expId, int em) {
        this.environModifLevelPK = new EnvironModifLevelPK(expId, em);
    }

    public EnvironModifLevelPK getEnvironModifLevelPK() {
        return environModifLevelPK;
    }

    public void setEnvironModifLevelPK(EnvironModifLevelPK environModifLevelPK) {
        this.environModifLevelPK = environModifLevelPK;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmNotes() {
        return emNotes;
    }

    public void setEmNotes(String emNotes) {
        this.emNotes = emNotes;
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
        hash += (environModifLevelPK != null ? environModifLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvironModifLevel)) {
            return false;
        }
        EnvironModifLevel other = (EnvironModifLevel) object;
        if ((this.environModifLevelPK == null && other.environModifLevelPK != null) || (this.environModifLevelPK != null && !this.environModifLevelPK.equals(other.environModifLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.EnvironModifLevel[ environModifLevelPK=" + environModifLevelPK + " ]";
    }

	@XmlTransient
	public Collection<EnvironModifEvent> getEnvironModifEventsCollection() {
		return environModifEventsCollection;
	}

	public void setEnvironModifEventsCollection(Collection<EnvironModifEvent> environModifEventsCollection) {
		this.environModifEventsCollection = environModifEventsCollection;
	}

}
