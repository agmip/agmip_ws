package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wpavan
 */
@Entity
@Table(name = "soil_analyses_levels")
@XmlRootElement
public class SoilAnalysesLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoilAnalysesLevelPK soilAnalysesLevelPK;
    @Column(name = "sadat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sadat;
    @Size(max = 255)
    @Column(name = "nrcsfam")
    private String nrcsfam;
    @Size(max = 255)
    @Column(name = "samhb")
    private String samhb;
    @Size(max = 255)
    @Column(name = "sampx")
    private String sampx;
    @Size(max = 255)
    @Column(name = "samke")
    private String samke;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sadr")
    private Float sadr;
    @Column(name = "saro")
    private Float saro;
    @Size(max = 255)
    @Column(name = "sa_name")
    private String saName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "sa_notes")
    private String saNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soilAnalysesLevel", fetch= FetchType.EAGER)
    private Collection<Treatment> treatmentsCollection;

    public SoilAnalysesLevel() {
    }

    public SoilAnalysesLevel(SoilAnalysesLevelPK soilAnalysesLevelPK) {
        this.soilAnalysesLevelPK = soilAnalysesLevelPK;
    }

    public SoilAnalysesLevel(int expId, int sa) {
        this.soilAnalysesLevelPK = new SoilAnalysesLevelPK(expId, sa);
    }

    public SoilAnalysesLevelPK getSoilAnalysesLevelPK() {
        return soilAnalysesLevelPK;
    }

    public void setSoilAnalysesLevelPK(SoilAnalysesLevelPK soilAnalysesLevelPK) {
        this.soilAnalysesLevelPK = soilAnalysesLevelPK;
    }

    public Date getSadat() {
        return sadat;
    }

    public void setSadat(Date sadat) {
        this.sadat = sadat;
    }

    public String getNrcsfam() {
        return nrcsfam;
    }

    public void setNrcsfam(String nrcsfam) {
        this.nrcsfam = nrcsfam;
    }

    public String getSamhb() {
        return samhb;
    }

    public void setSamhb(String samhb) {
        this.samhb = samhb;
    }

    public String getSampx() {
        return sampx;
    }

    public void setSampx(String sampx) {
        this.sampx = sampx;
    }

    public String getSamke() {
        return samke;
    }

    public void setSamke(String samke) {
        this.samke = samke;
    }

    public Float getSadr() {
        return sadr;
    }

    public void setSadr(Float sadr) {
        this.sadr = sadr;
    }

    public Float getSaro() {
        return saro;
    }

    public void setSaro(Float saro) {
        this.saro = saro;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSaNotes() {
        return saNotes;
    }

    public void setSaNotes(String saNotes) {
        this.saNotes = saNotes;
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
        hash += (soilAnalysesLevelPK != null ? soilAnalysesLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoilAnalysesLevel)) {
            return false;
        }
        SoilAnalysesLevel other = (SoilAnalysesLevel) object;
        if ((this.soilAnalysesLevelPK == null && other.soilAnalysesLevelPK != null) || (this.soilAnalysesLevelPK != null && !this.soilAnalysesLevelPK.equals(other.soilAnalysesLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.SoilAnalysesLevel[ soilAnalysesLevelPK=" + soilAnalysesLevelPK + " ]";
    }

}
