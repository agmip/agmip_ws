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
@Table(name = "irrigation_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IrrigationLevels.findAll", query = "SELECT i FROM IrrigationLevels i"),
    @NamedQuery(name = "IrrigationLevels.findByExpId", query = "SELECT i FROM IrrigationLevels i WHERE i.irrigationLevelsPK.expId = :expId"),
    @NamedQuery(name = "IrrigationLevels.findByIr", query = "SELECT i FROM IrrigationLevels i WHERE i.irrigationLevelsPK.ir = :ir"),
    @NamedQuery(name = "IrrigationLevels.findByIame", query = "SELECT i FROM IrrigationLevels i WHERE i.iame = :iame"),
    @NamedQuery(name = "IrrigationLevels.findByIamt", query = "SELECT i FROM IrrigationLevels i WHERE i.iamt = :iamt"),
    @NamedQuery(name = "IrrigationLevels.findByIreff", query = "SELECT i FROM IrrigationLevels i WHERE i.ireff = :ireff"),
    @NamedQuery(name = "IrrigationLevels.findByIrstg", query = "SELECT i FROM IrrigationLevels i WHERE i.irstg = :irstg"),
    @NamedQuery(name = "IrrigationLevels.findByIrmdp", query = "SELECT i FROM IrrigationLevels i WHERE i.irmdp = :irmdp"),
    @NamedQuery(name = "IrrigationLevels.findByIrthr", query = "SELECT i FROM IrrigationLevels i WHERE i.irthr = :irthr"),
    @NamedQuery(name = "IrrigationLevels.findByIrept", query = "SELECT i FROM IrrigationLevels i WHERE i.irept = :irept"),
    @NamedQuery(name = "IrrigationLevels.findByIrName", query = "SELECT i FROM IrrigationLevels i WHERE i.irName = :irName")})
public class IrrigationLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IrrigationLevelsPK irrigationLevelsPK;
    @Size(max = 255)
    @Column(name = "iame")
    private String iame;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iamt")
    private Float iamt;
    @Column(name = "ireff")
    private Float ireff;
    @Size(max = 255)
    @Column(name = "irstg")
    private String irstg;
    @Column(name = "irmdp")
    private Float irmdp;
    @Column(name = "irthr")
    private Float irthr;
    @Column(name = "irept")
    private Float irept;
    @Size(max = 255)
    @Column(name = "ir_name")
    private String irName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ir_notes")
    private String irNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "irrigationLevels")
    private Collection<Treatments> treatmentsCollection;

    public IrrigationLevels() {
    }

    public IrrigationLevels(IrrigationLevelsPK irrigationLevelsPK) {
        this.irrigationLevelsPK = irrigationLevelsPK;
    }

    public IrrigationLevels(int expId, int ir) {
        this.irrigationLevelsPK = new IrrigationLevelsPK(expId, ir);
    }

    public IrrigationLevelsPK getIrrigationLevelsPK() {
        return irrigationLevelsPK;
    }

    public void setIrrigationLevelsPK(IrrigationLevelsPK irrigationLevelsPK) {
        this.irrigationLevelsPK = irrigationLevelsPK;
    }

    public String getIame() {
        return iame;
    }

    public void setIame(String iame) {
        this.iame = iame;
    }

    public Float getIamt() {
        return iamt;
    }

    public void setIamt(Float iamt) {
        this.iamt = iamt;
    }

    public Float getIreff() {
        return ireff;
    }

    public void setIreff(Float ireff) {
        this.ireff = ireff;
    }

    public String getIrstg() {
        return irstg;
    }

    public void setIrstg(String irstg) {
        this.irstg = irstg;
    }

    public Float getIrmdp() {
        return irmdp;
    }

    public void setIrmdp(Float irmdp) {
        this.irmdp = irmdp;
    }

    public Float getIrthr() {
        return irthr;
    }

    public void setIrthr(Float irthr) {
        this.irthr = irthr;
    }

    public Float getIrept() {
        return irept;
    }

    public void setIrept(Float irept) {
        this.irept = irept;
    }

    public String getIrName() {
        return irName;
    }

    public void setIrName(String irName) {
        this.irName = irName;
    }

    public String getIrNotes() {
        return irNotes;
    }

    public void setIrNotes(String irNotes) {
        this.irNotes = irNotes;
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
        hash += (irrigationLevelsPK != null ? irrigationLevelsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IrrigationLevels)) {
            return false;
        }
        IrrigationLevels other = (IrrigationLevels) object;
        if ((this.irrigationLevelsPK == null && other.irrigationLevelsPK != null) || (this.irrigationLevelsPK != null && !this.irrigationLevelsPK.equals(other.irrigationLevelsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.IrrigationLevels[ irrigationLevelsPK=" + irrigationLevelsPK + " ]";
    }
    
}
