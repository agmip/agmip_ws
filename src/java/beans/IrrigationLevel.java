package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "irrigation_levels")
@XmlRootElement
public class IrrigationLevel implements Serializable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "irrigationLevel")
	private Collection<IrrigationEvent> irrigationEventsCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IrrigationLevelPK irrigationLevelPK;
    @Size(max = 255)
    @Column(name = "iame")
    private String iame;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "irrigationLevel")
    private Collection<Treatment> treatmentsCollection;

    public IrrigationLevel() {
    }

    public IrrigationLevel(IrrigationLevelPK irrigationLevelPK) {
        this.irrigationLevelPK = irrigationLevelPK;
    }

    public IrrigationLevel(int expId, int ir) {
        this.irrigationLevelPK = new IrrigationLevelPK(expId, ir);
    }

    public IrrigationLevelPK getIrrigationLevelPK() {
        return irrigationLevelPK;
    }

    public void setIrrigationLevelPK(IrrigationLevelPK irrigationLevelPK) {
        this.irrigationLevelPK = irrigationLevelPK;
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
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (irrigationLevelPK != null ? irrigationLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IrrigationLevel)) {
            return false;
        }
        IrrigationLevel other = (IrrigationLevel) object;
        if ((this.irrigationLevelPK == null && other.irrigationLevelPK != null) || (this.irrigationLevelPK != null && !this.irrigationLevelPK.equals(other.irrigationLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.IrrigationLevel[ irrigationLevelPK=" + irrigationLevelPK + " ]";
    }

	@XmlTransient
	public Collection<IrrigationEvent> getIrrigationEventsCollection() {
		return irrigationEventsCollection;
	}

	public void setIrrigationEventsCollection(Collection<IrrigationEvent> irrigationEventsCollection) {
		this.irrigationEventsCollection = irrigationEventsCollection;
	}
}