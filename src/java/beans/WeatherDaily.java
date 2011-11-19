package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "weather_daily")
@XmlRootElement
public class WeatherDaily implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected WeatherDailyPK weatherDailyPK;
	@Column(name = "srad", precision = 12)
	private Float srad;
	@Column(name = "tmax", precision = 12)
	private Float tmax;
	@Column(name = "tmin", precision = 12)
	private Float tmin;
	@Column(name = "rain", precision = 12)
	private Float rain;
	@Column(name = "wind", precision = 12)
	private Float wind;
	@Column(name = "tdew", precision = 12)
	private Float tdew;
	@Column(name = "co2d", precision = 12)
	private Float co2d;
	@Column(name = "cldd", precision = 12)
	private Float cldd;
	@Column(name = "dewp", precision = 12)
	private Float dewp;
	@Column(name = "pard", precision = 12)
	private Float pard;
	@Column(name = "dtr", precision = 12)
	private Float dtr;
	@Column(name = "sunh", precision = 12)
	private Float sunh;
	@Column(name = "tdry", precision = 12)
	private Float tdry;
	@Column(name = "evap", precision = 12)
	private Float evap;
	@Column(name = "twet", precision = 12)
	private Float twet;
	@Column(name = "dayd", precision = 12)
	private Float dayd;
	@Column(name = "da3d", precision = 12)
	private Float da3d;
	@Column(name = "twld", precision = 12)
	private Float twld;
	@Column(name = "aco2", precision = 12)
	private Float aco2;
	@Column(name = "dyld", precision = 12)
	private Float dyld;
	@Column(name = "pred", precision = 12)
	private Float pred;
	@Column(name = "tavd", precision = 12)
	private Float tavd;
	@Column(name = "tdyd", precision = 12)
	private Float tdyd;
	@Column(name = "tgad", precision = 12)
	private Float tgad;
	@Column(name = "tgrd", precision = 12)
	private Float tgrd;
	@Column(name = "tmen", precision = 12)
	private Float tmen;
	@Column(name = "tmna", precision = 12)
	private Float tmna;
	@Column(name = "tmxa", precision = 12)
	private Float tmxa;
	@Column(name = "ts1d", precision = 12)
	private Float ts1d;
	@Column(name = "ts2d", precision = 12)
	private Float ts2d;
	@Column(name = "ts3d", precision = 12)
	private Float ts3d;
	@Column(name = "ts4d", precision = 12)
	private Float ts4d;
	@Column(name = "ts5d", precision = 12)
	private Float ts5d;
	@Column(name = "ts6d", precision = 12)
	private Float ts6d;
	@Column(name = "ts7d", precision = 12)
	private Float ts7d;
	@Column(name = "ts8d", precision = 12)
	private Float ts8d;
	@Column(name = "ts9d", precision = 12)
	private Float ts9d;
	@Column(name = "ts10d", precision = 12)
	private Float ts10d;
	@Size(max = 255)
    @Column(name = "flagw", length = 255)
	private String flagw;

	public WeatherDaily() {
	}

	public WeatherDaily(WeatherDailyPK weatherDailyPK) {
		this.weatherDailyPK = weatherDailyPK;
	}

	public WeatherDaily(int wid, int wtyr, int wtday) {
		this.weatherDailyPK = new WeatherDailyPK(wid, wtyr, wtday);
	}

	public WeatherDailyPK getWeatherDailyPK() {
		return weatherDailyPK;
	}

	public void setWeatherDailyPK(WeatherDailyPK weatherDailyPK) {
		this.weatherDailyPK = weatherDailyPK;
	}

	public Float getSrad() {
		return srad;
	}

	public void setSrad(Float srad) {
		this.srad = srad;
	}

	public Float getTmax() {
		return tmax;
	}

	public void setTmax(Float tmax) {
		this.tmax = tmax;
	}

	public Float getTmin() {
		return tmin;
	}

	public void setTmin(Float tmin) {
		this.tmin = tmin;
	}

	public Float getRain() {
		return rain;
	}

	public void setRain(Float rain) {
		this.rain = rain;
	}

	public Float getWind() {
		return wind;
	}

	public void setWind(Float wind) {
		this.wind = wind;
	}

	public Float getTdew() {
		return tdew;
	}

	public void setTdew(Float tdew) {
		this.tdew = tdew;
	}

	public Float getCo2d() {
		return co2d;
	}

	public void setCo2d(Float co2d) {
		this.co2d = co2d;
	}

	public Float getCldd() {
		return cldd;
	}

	public void setCldd(Float cldd) {
		this.cldd = cldd;
	}

	public Float getDewp() {
		return dewp;
	}

	public void setDewp(Float dewp) {
		this.dewp = dewp;
	}

	public Float getPard() {
		return pard;
	}

	public void setPard(Float pard) {
		this.pard = pard;
	}

	public Float getDtr() {
		return dtr;
	}

	public void setDtr(Float dtr) {
		this.dtr = dtr;
	}

	public Float getSunh() {
		return sunh;
	}

	public void setSunh(Float sunh) {
		this.sunh = sunh;
	}

	public Float getTdry() {
		return tdry;
	}

	public void setTdry(Float tdry) {
		this.tdry = tdry;
	}

	public Float getEvap() {
		return evap;
	}

	public void setEvap(Float evap) {
		this.evap = evap;
	}

	public Float getTwet() {
		return twet;
	}

	public void setTwet(Float twet) {
		this.twet = twet;
	}

	public Float getDayd() {
		return dayd;
	}

	public void setDayd(Float dayd) {
		this.dayd = dayd;
	}

	public Float getDa3d() {
		return da3d;
	}

	public void setDa3d(Float da3d) {
		this.da3d = da3d;
	}

	public Float getTwld() {
		return twld;
	}

	public void setTwld(Float twld) {
		this.twld = twld;
	}

	public Float getAco2() {
		return aco2;
	}

	public void setAco2(Float aco2) {
		this.aco2 = aco2;
	}

	public Float getDyld() {
		return dyld;
	}

	public void setDyld(Float dyld) {
		this.dyld = dyld;
	}

	public Float getPred() {
		return pred;
	}

	public void setPred(Float pred) {
		this.pred = pred;
	}

	public Float getTavd() {
		return tavd;
	}

	public void setTavd(Float tavd) {
		this.tavd = tavd;
	}

	public Float getTdyd() {
		return tdyd;
	}

	public void setTdyd(Float tdyd) {
		this.tdyd = tdyd;
	}

	public Float getTgad() {
		return tgad;
	}

	public void setTgad(Float tgad) {
		this.tgad = tgad;
	}

	public Float getTgrd() {
		return tgrd;
	}

	public void setTgrd(Float tgrd) {
		this.tgrd = tgrd;
	}

	public Float getTmen() {
		return tmen;
	}

	public void setTmen(Float tmen) {
		this.tmen = tmen;
	}

	public Float getTmna() {
		return tmna;
	}

	public void setTmna(Float tmna) {
		this.tmna = tmna;
	}

	public Float getTmxa() {
		return tmxa;
	}

	public void setTmxa(Float tmxa) {
		this.tmxa = tmxa;
	}

	public Float getTs1d() {
		return ts1d;
	}

	public void setTs1d(Float ts1d) {
		this.ts1d = ts1d;
	}

	public Float getTs2d() {
		return ts2d;
	}

	public void setTs2d(Float ts2d) {
		this.ts2d = ts2d;
	}

	public Float getTs3d() {
		return ts3d;
	}

	public void setTs3d(Float ts3d) {
		this.ts3d = ts3d;
	}

	public Float getTs4d() {
		return ts4d;
	}

	public void setTs4d(Float ts4d) {
		this.ts4d = ts4d;
	}

	public Float getTs5d() {
		return ts5d;
	}

	public void setTs5d(Float ts5d) {
		this.ts5d = ts5d;
	}

	public Float getTs6d() {
		return ts6d;
	}

	public void setTs6d(Float ts6d) {
		this.ts6d = ts6d;
	}

	public Float getTs7d() {
		return ts7d;
	}

	public void setTs7d(Float ts7d) {
		this.ts7d = ts7d;
	}

	public Float getTs8d() {
		return ts8d;
	}

	public void setTs8d(Float ts8d) {
		this.ts8d = ts8d;
	}

	public Float getTs9d() {
		return ts9d;
	}

	public void setTs9d(Float ts9d) {
		this.ts9d = ts9d;
	}

	public Float getTs10d() {
		return ts10d;
	}

	public void setTs10d(Float ts10d) {
		this.ts10d = ts10d;
	}

	public String getFlagw() {
		return flagw;
	}

	public void setFlagw(String flagw) {
		this.flagw = flagw;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (weatherDailyPK != null ? weatherDailyPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherDaily)) {
			return false;
		}
		WeatherDaily other = (WeatherDaily) object;
		if ((this.weatherDailyPK == null && other.weatherDailyPK != null) || (this.weatherDailyPK != null && !this.weatherDailyPK.equals(other.weatherDailyPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.WeatherDaily[ weatherDailyPK=" + weatherDailyPK + " ]";
	}
}