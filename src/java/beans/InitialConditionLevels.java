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
@Table(name = "initial_condition_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InitialConditionLevels.findAll", query = "SELECT i FROM InitialConditionLevels i"),
    @NamedQuery(name = "InitialConditionLevels.findByExpId", query = "SELECT i FROM InitialConditionLevels i WHERE i.initialConditionLevelsPK.expId = :expId"),
    @NamedQuery(name = "InitialConditionLevels.findByIc", query = "SELECT i FROM InitialConditionLevels i WHERE i.initialConditionLevelsPK.ic = :ic"),
    @NamedQuery(name = "InitialConditionLevels.findByIcdat", query = "SELECT i FROM InitialConditionLevels i WHERE i.icdat = :icdat"),
    @NamedQuery(name = "InitialConditionLevels.findByIcyr", query = "SELECT i FROM InitialConditionLevels i WHERE i.icyr = :icyr"),
    @NamedQuery(name = "InitialConditionLevels.findByIcday", query = "SELECT i FROM InitialConditionLevels i WHERE i.icday = :icday"),
    @NamedQuery(name = "InitialConditionLevels.findByIcpcr", query = "SELECT i FROM InitialConditionLevels i WHERE i.icpcr = :icpcr"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrdp", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrdp = :icrdp"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrip", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrip = :icrip"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrag", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrag = :icrag"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrn", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrn = :icrn"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrp", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrp = :icrp"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrk", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrk = :icrk"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrli", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrli = :icrli"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrt", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrt = :icrt"),
    @NamedQuery(name = "InitialConditionLevels.findByIcnd", query = "SELECT i FROM InitialConditionLevels i WHERE i.icnd = :icnd"),
    @NamedQuery(name = "InitialConditionLevels.findByIcryr", query = "SELECT i FROM InitialConditionLevels i WHERE i.icryr = :icryr"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrdy", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrdy = :icrdy"),
    @NamedQuery(name = "InitialConditionLevels.findByIcwt", query = "SELECT i FROM InitialConditionLevels i WHERE i.icwt = :icwt"),
    @NamedQuery(name = "InitialConditionLevels.findByIcin", query = "SELECT i FROM InitialConditionLevels i WHERE i.icin = :icin"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrzno", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrzno = :icrzno"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrze", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrze = :icrze"),
    @NamedQuery(name = "InitialConditionLevels.findByIcrzn", query = "SELECT i FROM InitialConditionLevels i WHERE i.icrzn = :icrzn"),
    @NamedQuery(name = "InitialConditionLevels.findByIcName", query = "SELECT i FROM InitialConditionLevels i WHERE i.icName = :icName"),
    @NamedQuery(name = "InitialConditionLevels.findByIcNotes", query = "SELECT i FROM InitialConditionLevels i WHERE i.icNotes = :icNotes")})
public class InitialConditionLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InitialConditionLevelsPK initialConditionLevelsPK;
    @Column(name = "icdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date icdat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "icyr")
    private Float icyr;
    @Column(name = "icday")
    private Integer icday;
    @Size(max = 255)
    @Column(name = "icpcr")
    private String icpcr;
    @Column(name = "icrdp")
    private Float icrdp;
    @Column(name = "icrip")
    private Float icrip;
    @Column(name = "icrag")
    private Float icrag;
    @Column(name = "icrn")
    private Float icrn;
    @Column(name = "icrp")
    private Float icrp;
    @Column(name = "icrk")
    private Float icrk;
    @Column(name = "icrli")
    private Float icrli;
    @Column(name = "icrt")
    private Float icrt;
    @Column(name = "icnd")
    private Float icnd;
    @Column(name = "icryr")
    private Float icryr;
    @Column(name = "icrdy")
    private Integer icrdy;
    @Column(name = "icwt")
    private Float icwt;
    @Column(name = "icin")
    private Float icin;
    @Column(name = "icrzno")
    private Float icrzno;
    @Column(name = "icrze")
    private Integer icrze;
    @Column(name = "icrzn")
    private Float icrzn;
    @Size(max = 255)
    @Column(name = "ic_name")
    private String icName;
    @Size(max = 255)
    @Column(name = "ic_notes")
    private String icNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "initialConditionLevels")
    private Collection<Treatments> treatmentsCollection;

    public InitialConditionLevels() {
    }

    public InitialConditionLevels(InitialConditionLevelsPK initialConditionLevelsPK) {
        this.initialConditionLevelsPK = initialConditionLevelsPK;
    }

    public InitialConditionLevels(int expId, int ic) {
        this.initialConditionLevelsPK = new InitialConditionLevelsPK(expId, ic);
    }

    public InitialConditionLevelsPK getInitialConditionLevelsPK() {
        return initialConditionLevelsPK;
    }

    public void setInitialConditionLevelsPK(InitialConditionLevelsPK initialConditionLevelsPK) {
        this.initialConditionLevelsPK = initialConditionLevelsPK;
    }

    public Date getIcdat() {
        return icdat;
    }

    public void setIcdat(Date icdat) {
        this.icdat = icdat;
    }

    public Float getIcyr() {
        return icyr;
    }

    public void setIcyr(Float icyr) {
        this.icyr = icyr;
    }

    public Integer getIcday() {
        return icday;
    }

    public void setIcday(Integer icday) {
        this.icday = icday;
    }

    public String getIcpcr() {
        return icpcr;
    }

    public void setIcpcr(String icpcr) {
        this.icpcr = icpcr;
    }

    public Float getIcrdp() {
        return icrdp;
    }

    public void setIcrdp(Float icrdp) {
        this.icrdp = icrdp;
    }

    public Float getIcrip() {
        return icrip;
    }

    public void setIcrip(Float icrip) {
        this.icrip = icrip;
    }

    public Float getIcrag() {
        return icrag;
    }

    public void setIcrag(Float icrag) {
        this.icrag = icrag;
    }

    public Float getIcrn() {
        return icrn;
    }

    public void setIcrn(Float icrn) {
        this.icrn = icrn;
    }

    public Float getIcrp() {
        return icrp;
    }

    public void setIcrp(Float icrp) {
        this.icrp = icrp;
    }

    public Float getIcrk() {
        return icrk;
    }

    public void setIcrk(Float icrk) {
        this.icrk = icrk;
    }

    public Float getIcrli() {
        return icrli;
    }

    public void setIcrli(Float icrli) {
        this.icrli = icrli;
    }

    public Float getIcrt() {
        return icrt;
    }

    public void setIcrt(Float icrt) {
        this.icrt = icrt;
    }

    public Float getIcnd() {
        return icnd;
    }

    public void setIcnd(Float icnd) {
        this.icnd = icnd;
    }

    public Float getIcryr() {
        return icryr;
    }

    public void setIcryr(Float icryr) {
        this.icryr = icryr;
    }

    public Integer getIcrdy() {
        return icrdy;
    }

    public void setIcrdy(Integer icrdy) {
        this.icrdy = icrdy;
    }

    public Float getIcwt() {
        return icwt;
    }

    public void setIcwt(Float icwt) {
        this.icwt = icwt;
    }

    public Float getIcin() {
        return icin;
    }

    public void setIcin(Float icin) {
        this.icin = icin;
    }

    public Float getIcrzno() {
        return icrzno;
    }

    public void setIcrzno(Float icrzno) {
        this.icrzno = icrzno;
    }

    public Integer getIcrze() {
        return icrze;
    }

    public void setIcrze(Integer icrze) {
        this.icrze = icrze;
    }

    public Float getIcrzn() {
        return icrzn;
    }

    public void setIcrzn(Float icrzn) {
        this.icrzn = icrzn;
    }

    public String getIcName() {
        return icName;
    }

    public void setIcName(String icName) {
        this.icName = icName;
    }

    public String getIcNotes() {
        return icNotes;
    }

    public void setIcNotes(String icNotes) {
        this.icNotes = icNotes;
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
        hash += (initialConditionLevelsPK != null ? initialConditionLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InitialConditionLevels)) {
            return false;
        }
        InitialConditionLevels other = (InitialConditionLevels) object;
        if ((this.initialConditionLevelsPK == null && other.initialConditionLevelsPK != null) || (this.initialConditionLevelsPK != null && !this.initialConditionLevelsPK.equals(other.initialConditionLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.InitialConditionLevels[ initialConditionLevelsPK=" + initialConditionLevelsPK + " ]";
    }
    
}
