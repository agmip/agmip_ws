/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "SoilAnalysesLevels.findAll", query = "SELECT s FROM SoilAnalysesLevels s"),
    @NamedQuery(name = "SoilAnalysesLevels.findByExpId", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.soilAnalysesLevelsPK.expId = :expId"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySa", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.soilAnalysesLevelsPK.sa = :sa"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySadat", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.sadat = :sadat"),
    @NamedQuery(name = "SoilAnalysesLevels.findByNrcsfam", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.nrcsfam = :nrcsfam"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySamhb", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.samhb = :samhb"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySampx", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.sampx = :sampx"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySamke", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.samke = :samke"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySadr", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.sadr = :sadr"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySaro", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.saro = :saro"),
    @NamedQuery(name = "SoilAnalysesLevels.findBySaName", query = "SELECT s FROM SoilAnalysesLevels s WHERE s.saName = :saName")})
public class SoilAnalysesLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoilAnalysesLevelsPK soilAnalysesLevelsPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soilAnalysesLevels")
    private Collection<Treatments> treatmentsCollection;

    public SoilAnalysesLevels() {
    }

    public SoilAnalysesLevels(SoilAnalysesLevelsPK soilAnalysesLevelsPK) {
        this.soilAnalysesLevelsPK = soilAnalysesLevelsPK;
    }

    public SoilAnalysesLevels(int expId, int sa) {
        this.soilAnalysesLevelsPK = new SoilAnalysesLevelsPK(expId, sa);
    }

    public SoilAnalysesLevelsPK getSoilAnalysesLevelsPK() {
        return soilAnalysesLevelsPK;
    }

    public void setSoilAnalysesLevelsPK(SoilAnalysesLevelsPK soilAnalysesLevelsPK) {
        this.soilAnalysesLevelsPK = soilAnalysesLevelsPK;
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
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soilAnalysesLevelsPK != null ? soilAnalysesLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoilAnalysesLevels)) {
            return false;
        }
        SoilAnalysesLevels other = (SoilAnalysesLevels) object;
        if ((this.soilAnalysesLevelsPK == null && other.soilAnalysesLevelsPK != null) || (this.soilAnalysesLevelsPK != null && !this.soilAnalysesLevelsPK.equals(other.soilAnalysesLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.SoilAnalysesLevels[ soilAnalysesLevelsPK=" + soilAnalysesLevelsPK + " ]";
    }
    
}
