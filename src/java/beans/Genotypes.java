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
@Table(name = "genotypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genotypes.findAll", query = "SELECT g FROM Genotypes g"),
    @NamedQuery(name = "Genotypes.findByExpId", query = "SELECT g FROM Genotypes g WHERE g.genotypesPK.expId = :expId"),
    @NamedQuery(name = "Genotypes.findByGe", query = "SELECT g FROM Genotypes g WHERE g.genotypesPK.ge = :ge"),
    @NamedQuery(name = "Genotypes.findByCrid", query = "SELECT g FROM Genotypes g WHERE g.crid = :crid"),
    @NamedQuery(name = "Genotypes.findByCr", query = "SELECT g FROM Genotypes g WHERE g.cr = :cr"),
    @NamedQuery(name = "Genotypes.findByCulId", query = "SELECT g FROM Genotypes g WHERE g.culId = :culId"),
    @NamedQuery(name = "Genotypes.findByCulName", query = "SELECT g FROM Genotypes g WHERE g.culName = :culName")})
public class Genotypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GenotypesPK genotypesPK;
    @Column(name = "crid")
    private Integer crid;
    @Size(max = 255)
    @Column(name = "cr")
    private String cr;
    @Size(max = 255)
    @Column(name = "cul_id")
    private String culId;
    @Size(max = 255)
    @Column(name = "cul_name")
    private String culName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "cul_notes")
    private String culNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genotypes")
    private Collection<Treatments> treatmentsCollection;

    public Genotypes() {
    }

    public Genotypes(GenotypesPK genotypesPK) {
        this.genotypesPK = genotypesPK;
    }

    public Genotypes(int expId, int ge) {
        this.genotypesPK = new GenotypesPK(expId, ge);
    }

    public GenotypesPK getGenotypesPK() {
        return genotypesPK;
    }

    public void setGenotypesPK(GenotypesPK genotypesPK) {
        this.genotypesPK = genotypesPK;
    }

    public Integer getCrid() {
        return crid;
    }

    public void setCrid(Integer crid) {
        this.crid = crid;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getCulId() {
        return culId;
    }

    public void setCulId(String culId) {
        this.culId = culId;
    }

    public String getCulName() {
        return culName;
    }

    public void setCulName(String culName) {
        this.culName = culName;
    }

    public String getCulNotes() {
        return culNotes;
    }

    public void setCulNotes(String culNotes) {
        this.culNotes = culNotes;
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
        hash += (genotypesPK != null ? genotypesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genotypes)) {
            return false;
        }
        Genotypes other = (Genotypes) object;
        if ((this.genotypesPK == null && other.genotypesPK != null) || (this.genotypesPK != null && !this.genotypesPK.equals(other.genotypesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Genotypes[ genotypesPK=" + genotypesPK + " ]";
    }
    
}
