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
@Table(name = "fertilizer_levels")
@XmlRootElement
public class FertilizerLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fertilizerLevel")
	private Collection<FertilizerEvent> fertilizerEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FertilizerLevelPK fertilizerLevelPK;
    @Size(max = 255)
    @Column(name = "fe_name")
    private String feName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "fe_comments")
    private String feComments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fertilizerLevels")
    private Collection<Treatment> treatmentsCollection;

    public FertilizerLevel() {
    }

    public FertilizerLevel(FertilizerLevelPK fertilizerLevelPK) {
        this.fertilizerLevelPK = fertilizerLevelPK;
    }

    public FertilizerLevel(int expId, int fe) {
        this.fertilizerLevelPK = new FertilizerLevelPK(expId, fe);
    }

    public FertilizerLevelPK getFertilizerLevelPK() {
        return fertilizerLevelPK;
    }

    public void setFertilizerLevelPK(FertilizerLevelPK fertilizerLevelPK) {
        this.fertilizerLevelPK = fertilizerLevelPK;
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
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fertilizerLevelPK != null ? fertilizerLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FertilizerLevel)) {
            return false;
        }
        FertilizerLevel other = (FertilizerLevel) object;
        if ((this.fertilizerLevelPK == null && other.fertilizerLevelPK != null) || (this.fertilizerLevelPK != null && !this.fertilizerLevelPK.equals(other.fertilizerLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.FertilizerLevels[ fertilizerLevelPK=" + fertilizerLevelPK + " ]";
    }

	@XmlTransient
	public Collection<FertilizerEvent> getFertilizerEventsCollection() {
		return fertilizerEventsCollection;
	}

	public void setFertilizerEventsCollection(Collection<FertilizerEvent> fertilizerEventsCollection) {
		this.fertilizerEventsCollection = fertilizerEventsCollection;
	}

}
