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
@Table(name = "chemical_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChemicalLevels.findAll", query = "SELECT c FROM ChemicalLevels c"),
    @NamedQuery(name = "ChemicalLevels.findByExpId", query = "SELECT c FROM ChemicalLevels c WHERE c.chemicalLevelsPK.expId = :expId"),
    @NamedQuery(name = "ChemicalLevels.findByCh", query = "SELECT c FROM ChemicalLevels c WHERE c.chemicalLevelsPK.ch = :ch"),
    @NamedQuery(name = "ChemicalLevels.findByChName", query = "SELECT c FROM ChemicalLevels c WHERE c.chName = :chName")})
public class ChemicalLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChemicalLevelsPK chemicalLevelsPK;
    @Size(max = 255)
    @Column(name = "ch_name")
    private String chName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ch_notes")
    private String chNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chemicalLevels")
    private Collection<Treatments> treatmentsCollection;

    public ChemicalLevels() {
    }

    public ChemicalLevels(ChemicalLevelsPK chemicalLevelsPK) {
        this.chemicalLevelsPK = chemicalLevelsPK;
    }

    public ChemicalLevels(int expId, int ch) {
        this.chemicalLevelsPK = new ChemicalLevelsPK(expId, ch);
    }

    public ChemicalLevelsPK getChemicalLevelsPK() {
        return chemicalLevelsPK;
    }

    public void setChemicalLevelsPK(ChemicalLevelsPK chemicalLevelsPK) {
        this.chemicalLevelsPK = chemicalLevelsPK;
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
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chemicalLevelsPK != null ? chemicalLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChemicalLevels)) {
            return false;
        }
        ChemicalLevels other = (ChemicalLevels) object;
        if ((this.chemicalLevelsPK == null && other.chemicalLevelsPK != null) || (this.chemicalLevelsPK != null && !this.chemicalLevelsPK.equals(other.chemicalLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ChemicalLevels[ chemicalLevelsPK=" + chemicalLevelsPK + " ]";
    }
    
}
