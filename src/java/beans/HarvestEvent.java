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
@Table(name = "harvest_events")
@XmlRootElement
public class HarvestEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected HarvestEventPK harvestEventPK;
	@Column(name = "haday")
    @Temporal(TemporalType.TIMESTAMP)
	private Date haday;
	@Size(max = 255)
    @Column(name = "hastg", length = 255)
	private String hastg;
	@Size(max = 255)
    @Column(name = "hacom", length = 255)
	private String hacom;
	@Size(max = 255)
    @Column(name = "harm", length = 255)
	private String harm;
	@Column(name = "harea", precision = 12)
	private Float harea;
	@Size(max = 255)
    @Column(name = "hasiz", length = 255)
	private String hasiz;
	@Column(name = "hapc", precision = 12)
	private Float hapc;
	@Column(name = "habpc", precision = 12)
	private Float habpc;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ha", referencedColumnName = "ha", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private HarvestLevel harvestLevel;

	public HarvestEvent() {
	}

	public HarvestEvent(HarvestEventPK harvestEventPK) {
		this.harvestEventPK = harvestEventPK;
	}

	public HarvestEvent(int expId, int ha, int haOpsNo) {
		this.harvestEventPK = new HarvestEventPK(expId, ha, haOpsNo);
	}

	public HarvestEventPK getHarvestEventPK() {
		return harvestEventPK;
	}

	public void setHarvestEventPK(HarvestEventPK harvestEventPK) {
		this.harvestEventPK = harvestEventPK;
	}

	public Date getHaday() {
		return haday;
	}

	public void setHaday(Date haday) {
		this.haday = haday;
	}

	public String getHastg() {
		return hastg;
	}

	public void setHastg(String hastg) {
		this.hastg = hastg;
	}

	public String getHacom() {
		return hacom;
	}

	public void setHacom(String hacom) {
		this.hacom = hacom;
	}

	public String getHarm() {
		return harm;
	}

	public void setHarm(String harm) {
		this.harm = harm;
	}

	public Float getHarea() {
		return harea;
	}

	public void setHarea(Float harea) {
		this.harea = harea;
	}

	public String getHasiz() {
		return hasiz;
	}

	public void setHasiz(String hasiz) {
		this.hasiz = hasiz;
	}

	public Float getHapc() {
		return hapc;
	}

	public void setHapc(Float hapc) {
		this.hapc = hapc;
	}

	public Float getHabpc() {
		return habpc;
	}

	public void setHabpc(Float habpc) {
		this.habpc = habpc;
	}

	public HarvestLevel getHarvestLevel() {
		return harvestLevel;
	}

	public void setHarvestLevel(HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (harvestEventPK != null ? harvestEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HarvestEvent)) {
			return false;
		}
		HarvestEvent other = (HarvestEvent) object;
		if ((this.harvestEventPK == null && other.harvestEventPK != null) || (this.harvestEventPK != null && !this.harvestEventPK.equals(other.harvestEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.HarvestEvent[ harvestEventPK=" + harvestEventPK + " ]";
	}
}