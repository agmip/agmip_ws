package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fonini
 */
@Embeddable
public class WeatherDailyPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "wid", nullable = false)
	private int wid;
	@Basic(optional = false)
    @NotNull
    @Column(name = "wtyr", nullable = false)
	private int wtyr;
	@Basic(optional = false)
    @NotNull
    @Column(name = "wtday", nullable = false)
	private int wtday;

	public WeatherDailyPK() {
	}

	public WeatherDailyPK(int wid, int wtyr, int wtday) {
		this.wid = wid;
		this.wtyr = wtyr;
		this.wtday = wtday;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public int getWtyr() {
		return wtyr;
	}

	public void setWtyr(int wtyr) {
		this.wtyr = wtyr;
	}

	public int getWtday() {
		return wtday;
	}

	public void setWtday(int wtday) {
		this.wtday = wtday;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) wid;
		hash += (int) wtyr;
		hash += (int) wtday;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WeatherDailyPK)) {
			return false;
		}
		WeatherDailyPK other = (WeatherDailyPK) object;
		if (this.wid != other.wid) {
			return false;
		}
		if (this.wtyr != other.wtyr) {
			return false;
		}
		if (this.wtday != other.wtday) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.WeatherDailyPK[ wid=" + wid + ", wtyr=" + wtyr + ", wtday=" + wtday + " ]";
	}

}
