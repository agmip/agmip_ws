package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "tillage_events")
@XmlRootElement
public class TillageEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TillageEventPK tillageEventPK;
	@Column(name = "tdate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date tdate;
	@Column(name = "tiord")
	private Integer tiord;
	@Size(max = 255)
    @Column(name = "tiimp", length = 255)
	private String tiimp;
	@Column(name = "tidep", precision = 12)
	private Float tidep;
	@Column(name = "timix", precision = 12)
	private Float timix;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ti", referencedColumnName = "ti", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private TillageLevel tillageLevel;

	public TillageEvent() {
	}

	public TillageEvent(TillageEventPK tillageEventPK) {
		this.tillageEventPK = tillageEventPK;
	}

	public TillageEvent(int expId, int ti, int tiOpsNo) {
		this.tillageEventPK = new TillageEventPK(expId, ti, tiOpsNo);
	}

	public TillageEventPK getTillageEventPK() {
		return tillageEventPK;
	}

	public void setTillageEventPK(TillageEventPK tillageEventPK) {
		this.tillageEventPK = tillageEventPK;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	public Integer getTiord() {
		return tiord;
	}

	public void setTiord(Integer tiord) {
		this.tiord = tiord;
	}

	public String getTiimp() {
		return tiimp;
	}

	public void setTiimp(String tiimp) {
		this.tiimp = tiimp;
	}

	public Float getTidep() {
		return tidep;
	}

	public void setTidep(Float tidep) {
		this.tidep = tidep;
	}

	public Float getTimix() {
		return timix;
	}

	public void setTimix(Float timix) {
		this.timix = timix;
	}

	public TillageLevel getTillageLevel() {
		return tillageLevel;
	}

	public void setTillageLevel(TillageLevel tillageLevel) {
		this.tillageLevel = tillageLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tillageEventPK != null ? tillageEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TillageEvent)) {
			return false;
		}
		TillageEvent other = (TillageEvent) object;
		if ((this.tillageEventPK == null && other.tillageEventPK != null) || (this.tillageEventPK != null && !this.tillageEventPK.equals(other.tillageEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.TillageEvent[ tillageEventPK=" + tillageEventPK + " ]";
	}
}