package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "initial_condition_events", catalog = "agmipapi", schema = "")
@XmlRootElement
public class InitialConditionEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected InitialConditionEventPK initialConditionEventPK;
	@Column(name = "icbl")
	private Integer icbl;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "ich2o", precision = 12)
	private Float ich2o;
	@Column(name = "icnh4", precision = 12)
	private Float icnh4;
	@Column(name = "icno3", precision = 12)
	private Float icno3;
	@JoinColumns({
    	@JoinColumn(name = "exp_id", referencedColumnName = "exp_id", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "ic", referencedColumnName = "ic", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private InitialConditionLevel initialConditionLevel;

	public InitialConditionEvent() {
	}

	public InitialConditionEvent(InitialConditionEventPK initialConditionEventPK) {
		this.initialConditionEventPK = initialConditionEventPK;
	}

	public InitialConditionEvent(int expId, int ic, int icLayer) {
		this.initialConditionEventPK = new InitialConditionEventPK(expId, ic, icLayer);
	}

	public InitialConditionEventPK getInitialConditionEventPK() {
		return initialConditionEventPK;
	}

	public void setInitialConditionEventPK(InitialConditionEventPK initialConditionEventPK) {
		this.initialConditionEventPK = initialConditionEventPK;
	}

	public Integer getIcbl() {
		return icbl;
	}

	public void setIcbl(Integer icbl) {
		this.icbl = icbl;
	}

	public Float getIch2o() {
		return ich2o;
	}

	public void setIch2o(Float ich2o) {
		this.ich2o = ich2o;
	}

	public Float getIcnh4() {
		return icnh4;
	}

	public void setIcnh4(Float icnh4) {
		this.icnh4 = icnh4;
	}

	public Float getIcno3() {
		return icno3;
	}

	public void setIcno3(Float icno3) {
		this.icno3 = icno3;
	}

	public InitialConditionLevel getInitialConditionLevel() {
		return initialConditionLevel;
	}

	public void setInitialConditionLevel(InitialConditionLevel initialConditionLevel) {
		this.initialConditionLevel = initialConditionLevel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (initialConditionEventPK != null ? initialConditionEventPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof InitialConditionEvent)) {
			return false;
		}
		InitialConditionEvent other = (InitialConditionEvent) object;
		if ((this.initialConditionEventPK == null && other.initialConditionEventPK != null) || (this.initialConditionEventPK != null && !this.initialConditionEventPK.equals(other.initialConditionEventPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.InitialConditionEvent[ initialConditionEventPK=" + initialConditionEventPK + " ]";
	}

}
