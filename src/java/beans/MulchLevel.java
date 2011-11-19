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
@Table(name = "mulch_levels")
@XmlRootElement
public class MulchLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mulchLevel")
	private Collection<MulchEvent> mulchEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MulchLevelPK mulchLevelPK;
    @Size(max = 255)
    @Column(name = "ml_name")
    private String mlName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ml_notes")
    private String mlNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mulchLevel")
    private Collection<Treatment> treatmentsCollection;

    public MulchLevel() {
    }

    public MulchLevel(MulchLevelPK mulchLevelPK) {
        this.mulchLevelPK = mulchLevelPK;
    }

    public MulchLevel(int expId, int ml) {
        this.mulchLevelPK = new MulchLevelPK(expId, ml);
    }

    public MulchLevelPK getMulchLevelPK() {
        return mulchLevelPK;
    }

    public void setMulchLevelPK(MulchLevelPK mulchLevelPK) {
        this.mulchLevelPK = mulchLevelPK;
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
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mulchLevelPK != null ? mulchLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MulchLevel)) {
            return false;
        }
        MulchLevel other = (MulchLevel) object;
        if ((this.mulchLevelPK == null && other.mulchLevelPK != null) || (this.mulchLevelPK != null && !this.mulchLevelPK.equals(other.mulchLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.MulchLevel[ mulchLevelPK=" + mulchLevelPK + " ]";
    }

	@XmlTransient
	public Collection<MulchEvent> getMulchEventsCollection() {
		return mulchEventsCollection;
	}

	public void setMulchEventsCollection(Collection<MulchEvent> mulchEventsCollection) {
		this.mulchEventsCollection = mulchEventsCollection;
	}

}
