package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "genotypes")
@XmlRootElement
public class Genotype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GenotypePK genotypePK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genotype", fetch= FetchType.EAGER)
    private Collection<Treatment> treatmentsCollection;

    public Genotype() {
    }

    public Genotype(GenotypePK genotypePK) {
        this.genotypePK = genotypePK;
    }

    public Genotype(int expId, int ge) {
        this.genotypePK = new GenotypePK(expId, ge);
    }

    public GenotypePK getGenotypePK() {
        return genotypePK;
    }

    public void setGenotypePK(GenotypePK genotypePK) {
        this.genotypePK = genotypePK;
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
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genotypePK != null ? genotypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genotype)) {
            return false;
        }
        Genotype other = (Genotype) object;
        if ((this.genotypePK == null && other.genotypePK != null) || (this.genotypePK != null && !this.genotypePK.equals(other.genotypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Genotype[ genotypePK=" + genotypePK + " ]";
    }

}
