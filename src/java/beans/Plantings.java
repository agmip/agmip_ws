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
@Table(name = "plantings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantings.findAll", query = "SELECT p FROM Plantings p"),
    @NamedQuery(name = "Plantings.findByExpId", query = "SELECT p FROM Plantings p WHERE p.plantingsPK.expId = :expId"),
    @NamedQuery(name = "Plantings.findByPl", query = "SELECT p FROM Plantings p WHERE p.plantingsPK.pl = :pl"),
    @NamedQuery(name = "Plantings.findByPdate", query = "SELECT p FROM Plantings p WHERE p.pdate = :pdate"),
    @NamedQuery(name = "Plantings.findByPldae", query = "SELECT p FROM Plantings p WHERE p.pldae = :pldae"),
    @NamedQuery(name = "Plantings.findByPlpop", query = "SELECT p FROM Plantings p WHERE p.plpop = :plpop"),
    @NamedQuery(name = "Plantings.findByPlpoe", query = "SELECT p FROM Plantings p WHERE p.plpoe = :plpoe"),
    @NamedQuery(name = "Plantings.findByPlma", query = "SELECT p FROM Plantings p WHERE p.plma = :plma"),
    @NamedQuery(name = "Plantings.findByPlds", query = "SELECT p FROM Plantings p WHERE p.plds = :plds"),
    @NamedQuery(name = "Plantings.findByPlrs", query = "SELECT p FROM Plantings p WHERE p.plrs = :plrs"),
    @NamedQuery(name = "Plantings.findByPlrd", query = "SELECT p FROM Plantings p WHERE p.plrd = :plrd"),
    @NamedQuery(name = "Plantings.findByPlph", query = "SELECT p FROM Plantings p WHERE p.plph = :plph"),
    @NamedQuery(name = "Plantings.findByPldp", query = "SELECT p FROM Plantings p WHERE p.pldp = :pldp"),
    @NamedQuery(name = "Plantings.findByPlmwt", query = "SELECT p FROM Plantings p WHERE p.plmwt = :plmwt"),
    @NamedQuery(name = "Plantings.findByPlage", query = "SELECT p FROM Plantings p WHERE p.plage = :plage"),
    @NamedQuery(name = "Plantings.findByPlenv", query = "SELECT p FROM Plantings p WHERE p.plenv = :plenv"),
    @NamedQuery(name = "Plantings.findByPlspl", query = "SELECT p FROM Plantings p WHERE p.plspl = :plspl"),
    @NamedQuery(name = "Plantings.findByPlgpct", query = "SELECT p FROM Plantings p WHERE p.plgpct = :plgpct"),
    @NamedQuery(name = "Plantings.findByPlmsource", query = "SELECT p FROM Plantings p WHERE p.plmsource = :plmsource"),
    @NamedQuery(name = "Plantings.findByPage", query = "SELECT p FROM Plantings p WHERE p.page = :page"),
    @NamedQuery(name = "Plantings.findByPenv", query = "SELECT p FROM Plantings p WHERE p.penv = :penv"),
    @NamedQuery(name = "Plantings.findByPltln", query = "SELECT p FROM Plantings p WHERE p.pltln = :pltln"),
    @NamedQuery(name = "Plantings.findByPltod", query = "SELECT p FROM Plantings p WHERE p.pltod = :pltod"),
    @NamedQuery(name = "Plantings.findByPltor", query = "SELECT p FROM Plantings p WHERE p.pltor = :pltor"),
    @NamedQuery(name = "Plantings.findByPltrno", query = "SELECT p FROM Plantings p WHERE p.pltrno = :pltrno"),
    @NamedQuery(name = "Plantings.findByPltsp", query = "SELECT p FROM Plantings p WHERE p.pltsp = :pltsp"),
    @NamedQuery(name = "Plantings.findByPlName", query = "SELECT p FROM Plantings p WHERE p.plName = :plName"),
    @NamedQuery(name = "Plantings.findByPlNotes", query = "SELECT p FROM Plantings p WHERE p.plNotes = :plNotes"),
    @NamedQuery(name = "Plantings.findByPlme", query = "SELECT p FROM Plantings p WHERE p.plme = :plme")})
public class Plantings implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlantingsPK plantingsPK;
    @Column(name = "pdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdate;
    @Column(name = "pldae")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pldae;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "plpop")
    private Float plpop;
    @Column(name = "plpoe")
    private Float plpoe;
    @Size(max = 255)
    @Column(name = "plma")
    private String plma;
    @Size(max = 255)
    @Column(name = "plds")
    private String plds;
    @Column(name = "plrs")
    private Float plrs;
    @Column(name = "plrd")
    private Float plrd;
    @Column(name = "plph")
    private Integer plph;
    @Column(name = "pldp")
    private Float pldp;
    @Column(name = "plmwt")
    private Float plmwt;
    @Column(name = "plage")
    private Float plage;
    @Column(name = "plenv")
    private Float plenv;
    @Column(name = "plspl")
    private Float plspl;
    @Column(name = "plgpct")
    private Float plgpct;
    @Size(max = 255)
    @Column(name = "plmsource")
    private String plmsource;
    @Column(name = "page")
    private Float page;
    @Column(name = "penv")
    private Float penv;
    @Column(name = "pltln")
    private Float pltln;
    @Column(name = "pltod")
    private Float pltod;
    @Column(name = "pltor")
    private Float pltor;
    @Column(name = "pltrno")
    private Integer pltrno;
    @Column(name = "pltsp")
    private Float pltsp;
    @Size(max = 255)
    @Column(name = "pl_name")
    private String plName;
    @Size(max = 255)
    @Column(name = "pl_notes")
    private String plNotes;
    @Size(max = 1)
    @Column(name = "plme")
    private String plme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantings")
    private Collection<Treatments> treatmentsCollection;

    public Plantings() {
    }

    public Plantings(PlantingsPK plantingsPK) {
        this.plantingsPK = plantingsPK;
    }

    public Plantings(int expId, int pl) {
        this.plantingsPK = new PlantingsPK(expId, pl);
    }

    public PlantingsPK getPlantingsPK() {
        return plantingsPK;
    }

    public void setPlantingsPK(PlantingsPK plantingsPK) {
        this.plantingsPK = plantingsPK;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Date getPldae() {
        return pldae;
    }

    public void setPldae(Date pldae) {
        this.pldae = pldae;
    }

    public Float getPlpop() {
        return plpop;
    }

    public void setPlpop(Float plpop) {
        this.plpop = plpop;
    }

    public Float getPlpoe() {
        return plpoe;
    }

    public void setPlpoe(Float plpoe) {
        this.plpoe = plpoe;
    }

    public String getPlma() {
        return plma;
    }

    public void setPlma(String plma) {
        this.plma = plma;
    }

    public String getPlds() {
        return plds;
    }

    public void setPlds(String plds) {
        this.plds = plds;
    }

    public Float getPlrs() {
        return plrs;
    }

    public void setPlrs(Float plrs) {
        this.plrs = plrs;
    }

    public Float getPlrd() {
        return plrd;
    }

    public void setPlrd(Float plrd) {
        this.plrd = plrd;
    }

    public Integer getPlph() {
        return plph;
    }

    public void setPlph(Integer plph) {
        this.plph = plph;
    }

    public Float getPldp() {
        return pldp;
    }

    public void setPldp(Float pldp) {
        this.pldp = pldp;
    }

    public Float getPlmwt() {
        return plmwt;
    }

    public void setPlmwt(Float plmwt) {
        this.plmwt = plmwt;
    }

    public Float getPlage() {
        return plage;
    }

    public void setPlage(Float plage) {
        this.plage = plage;
    }

    public Float getPlenv() {
        return plenv;
    }

    public void setPlenv(Float plenv) {
        this.plenv = plenv;
    }

    public Float getPlspl() {
        return plspl;
    }

    public void setPlspl(Float plspl) {
        this.plspl = plspl;
    }

    public Float getPlgpct() {
        return plgpct;
    }

    public void setPlgpct(Float plgpct) {
        this.plgpct = plgpct;
    }

    public String getPlmsource() {
        return plmsource;
    }

    public void setPlmsource(String plmsource) {
        this.plmsource = plmsource;
    }

    public Float getPage() {
        return page;
    }

    public void setPage(Float page) {
        this.page = page;
    }

    public Float getPenv() {
        return penv;
    }

    public void setPenv(Float penv) {
        this.penv = penv;
    }

    public Float getPltln() {
        return pltln;
    }

    public void setPltln(Float pltln) {
        this.pltln = pltln;
    }

    public Float getPltod() {
        return pltod;
    }

    public void setPltod(Float pltod) {
        this.pltod = pltod;
    }

    public Float getPltor() {
        return pltor;
    }

    public void setPltor(Float pltor) {
        this.pltor = pltor;
    }

    public Integer getPltrno() {
        return pltrno;
    }

    public void setPltrno(Integer pltrno) {
        this.pltrno = pltrno;
    }

    public Float getPltsp() {
        return pltsp;
    }

    public void setPltsp(Float pltsp) {
        this.pltsp = pltsp;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public String getPlNotes() {
        return plNotes;
    }

    public void setPlNotes(String plNotes) {
        this.plNotes = plNotes;
    }

    public String getPlme() {
        return plme;
    }

    public void setPlme(String plme) {
        this.plme = plme;
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
        hash += (plantingsPK != null ? plantingsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantings)) {
            return false;
        }
        Plantings other = (Plantings) object;
        if ((this.plantingsPK == null && other.plantingsPK != null) || (this.plantingsPK != null && !this.plantingsPK.equals(other.plantingsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Plantings[ plantingsPK=" + plantingsPK + " ]";
    }
    
}
