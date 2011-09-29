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
@Table(name = "environ_modif_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnvironModifLevels.findAll", query = "SELECT e FROM EnvironModifLevels e"),
    @NamedQuery(name = "EnvironModifLevels.findByExpId", query = "SELECT e FROM EnvironModifLevels e WHERE e.environModifLevelsPK.expId = :expId"),
    @NamedQuery(name = "EnvironModifLevels.findByEm", query = "SELECT e FROM EnvironModifLevels e WHERE e.environModifLevelsPK.em = :em"),
    @NamedQuery(name = "EnvironModifLevels.findByEmName", query = "SELECT e FROM EnvironModifLevels e WHERE e.emName = :emName")})
public class EnvironModifLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvironModifLevelsPK environModifLevelsPK;
    @Size(max = 255)
    @Column(name = "em_name")
    private String emName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "em_notes")
    private String emNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "environModifLevels")
    private Collection<Treatments> treatmentsCollection;

    public EnvironModifLevels() {
    }

    public EnvironModifLevels(EnvironModifLevelsPK environModifLevelsPK) {
        this.environModifLevelsPK = environModifLevelsPK;
    }

    public EnvironModifLevels(int expId, int em) {
        this.environModifLevelsPK = new EnvironModifLevelsPK(expId, em);
    }

    public EnvironModifLevelsPK getEnvironModifLevelsPK() {
        return environModifLevelsPK;
    }

    public void setEnvironModifLevelsPK(EnvironModifLevelsPK environModifLevelsPK) {
        this.environModifLevelsPK = environModifLevelsPK;
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
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (environModifLevelsPK != null ? environModifLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvironModifLevels)) {
            return false;
        }
        EnvironModifLevels other = (EnvironModifLevels) object;
        if ((this.environModifLevelsPK == null && other.environModifLevelsPK != null) || (this.environModifLevelsPK != null && !this.environModifLevelsPK.equals(other.environModifLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.EnvironModifLevels[ environModifLevelsPK=" + environModifLevelsPK + " ]";
    }
    
}
