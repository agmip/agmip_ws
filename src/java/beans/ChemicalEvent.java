package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "chemical_events")
@XmlRootElement
public class ChemicalEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ChemicalEventPK chemicalEventPK;
	@Column(name = "cdate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date cdate;
	@Size(max = 255)
    @Column(name = "chcd")
	private String chcd;
	@Size(max = 255)
    @Column(name = "chacd")
	private String chacd;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "chdep")
	private Float chdep;
	@Column(name = "chamt")
	private Float chamt;
	@Size(max = 255)
    @Column(name = "ch_targets")
	private String chTargets;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", insertable = false, updatable = false),
    	@JoinColumn(name = "ch", referencedColumnName = "ch", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private ChemicalLevel chemicalLevel;

	public ChemicalEvent() {
	}

	public ChemicalEvent(ChemicalEventPK chemicalEventPK) {
		this.chemicalEventPK = chemicalEventPK;
	}

	public ChemicalEvent(int expId, int ch, int chApplNo) {
		this.chemicalEventPK = new ChemicalEventPK(expId, ch, chApplNo);
	}

	public ChemicalEventPK getChemicalEventPK() {
		return chemicalEventPK;
	}

	public void setChemicalEventPK(ChemicalEventPK chemicalEventPK) {
		this.chemicalEventPK = chemicalEventPK;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getChcd() {
		return chcd;
	}

	public void setChcd(String chcd) {
		this.chcd = chcd;
	}

	public String getChacd() {
		return chacd;
	}

	public void setChacd(String chacd) {
		this.chacd = chacd;
	}

	public Float getChdep() {
		return chdep;
	}

	public void setChdep(Float chdep) {
		this.chdep = chdep;
	}

	public Float getChamt() {
		return chamt;
	}

	public void setChamt(Float chamt) {
		this.chamt = chamt;
	}

	public String getChTargets() {
		return chTargets;
	}

	public void setChTargets(String chTargets) {
		this.chTargets = chTargets;
	}

	public ChemicalLevel getChemicalLevel() {
		return chemicalLevel;
	}

	public void setChemicalLevel(ChemicalLevel chemicalLevel) {
		this.chemicalLevel = chemicalLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (chemicalEventPK != null ? chemicalEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ChemicalEvent)) {
			return false;
		}
		ChemicalEvent other = (ChemicalEvent) object;
		if ((this.chemicalEventPK == null && other.chemicalEventPK != null) || (this.chemicalEventPK != null && !this.chemicalEventPK.equals(other.chemicalEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.ChemicalEvents[ chemicalEventPK=" + chemicalEventPK + " ]";
	}

}
