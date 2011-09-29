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
@Table(name = "tillage_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TillageLevels.findAll", query = "SELECT t FROM TillageLevels t"),
    @NamedQuery(name = "TillageLevels.findByExpId", query = "SELECT t FROM TillageLevels t WHERE t.tillageLevelsPK.expId = :expId"),
    @NamedQuery(name = "TillageLevels.findByTi", query = "SELECT t FROM TillageLevels t WHERE t.tillageLevelsPK.ti = :ti"),
    @NamedQuery(name = "TillageLevels.findByTiName", query = "SELECT t FROM TillageLevels t WHERE t.tiName = :tiName")})
public class TillageLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TillageLevelsPK tillageLevelsPK;
    @Size(max = 255)
    @Column(name = "ti_name")
    private String tiName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ti_notes")
    private String tiNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tillageLevels")
    private Collection<Treatments> treatmentsCollection;

    public TillageLevels() {
    }

    public TillageLevels(TillageLevelsPK tillageLevelsPK) {
        this.tillageLevelsPK = tillageLevelsPK;
    }

    public TillageLevels(int expId, int ti) {
        this.tillageLevelsPK = new TillageLevelsPK(expId, ti);
    }

    public TillageLevelsPK getTillageLevelsPK() {
        return tillageLevelsPK;
    }

    public void setTillageLevelsPK(TillageLevelsPK tillageLevelsPK) {
        this.tillageLevelsPK = tillageLevelsPK;
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
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tillageLevelsPK != null ? tillageLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TillageLevels)) {
            return false;
        }
        TillageLevels other = (TillageLevels) object;
        if ((this.tillageLevelsPK == null && other.tillageLevelsPK != null) || (this.tillageLevelsPK != null && !this.tillageLevelsPK.equals(other.tillageLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.TillageLevels[ tillageLevelsPK=" + tillageLevelsPK + " ]";
    }
    
}
