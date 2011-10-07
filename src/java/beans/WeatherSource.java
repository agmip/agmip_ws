package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "weather_sources")
@XmlRootElement
public class WeatherSource implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "wid", nullable = false)
	private Integer wid;
	@Size(max = 255)
    @Column(name = "wsta_id", length = 255)
	private String wstaId;
	@Size(max = 255)
    @Column(name = "fd_name", length = 255)
	private String fdName;
	@Size(max = 255)
    @Column(name = "site", length = 255)
	private String site;
	@Size(max = 255)
    @Column(name = "source", length = 255)
	private String source;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "lat", precision = 12)
	private Float lat;
	@Column(name = "lon", precision = 12)
	private Float lon;
	@Column(name = "elev", precision = 12)
	private Float elev;
	@Column(name = "tav", precision = 12)
	private Float tav;
	@Column(name = "tamp", precision = 12)
	private Float tamp;
	@Column(name = "temht", precision = 12)
	private Float temht;
	@Column(name = "refht", precision = 12)
	private Float refht;
	@Column(name = "wndht", precision = 12)
	private Float wndht;
	@Column(name = "co2y", precision = 12)
	private Float co2y;
	@Column(name = "co2a", precision = 12)
	private Float co2a;
	@Column(name = "durn", precision = 12)
	private Float durn;
	@Column(name = "gsdu", precision = 12)
	private Float gsdu;
	@Column(name = "gsst")
	private Integer gsst;
	@Column(name = "anga", precision = 12)
	private Float anga;
	@Column(name = "bmth", precision = 12)
	private Float bmth;
	@Size(max = 255)
    @Column(name = "insi", length = 255)
	private String insi;
	@Column(name = "raiy", precision = 12)
	private Float raiy;
	@Column(name = "sray", precision = 12)
	private Float sray;
	@Column(name = "start_yr", precision = 12)
	private Float startYr;
	@Column(name = "tmny", precision = 12)
	private Float tmny;
	@Column(name = "tmxy", precision = 12)
	private Float tmxy;
	@Size(max = 255)
    @Column(name = "flaga", length = 255)
	private String flaga;

	public WeatherSource() {
	}

	public WeatherSource(Integer wid) {
		this.wid = wid;
	}

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWstaId() {
		return wstaId;
	}

	public void setWstaId(String wstaId) {
		this.wstaId = wstaId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public Float getElev() {
		return elev;
	}

	public void setElev(Float elev) {
		this.elev = elev;
	}

	public Float getTav() {
		return tav;
	}

	public void setTav(Float tav) {
		this.tav = tav;
	}

	public Float getTamp() {
		return tamp;
	}

	public void setTamp(Float tamp) {
		this.tamp = tamp;
	}

	public Float getTemht() {
		return temht;
	}

	public void setTemht(Float temht) {
		this.temht = temht;
	}

	public Float getRefht() {
		return refht;
	}

	public void setRefht(Float refht) {
		this.refht = refht;
	}

	public Float getWndht() {
		return wndht;
	}

	public void setWndht(Float wndht) {
		this.wndht = wndht;
	}

	public Float getCo2y() {
		return co2y;
	}

	public void setCo2y(Float co2y) {
		this.co2y = co2y;
	}

	public Float getCo2a() {
		return co2a;
	}

	public void setCo2a(Float co2a) {
		this.co2a = co2a;
	}

	public Float getDurn() {
		return durn;
	}

	public void setDurn(Float durn) {
		this.durn = durn;
	}

	public Float getGsdu() {
		return gsdu;
	}

	public void setGsdu(Float gsdu) {
		this.gsdu = gsdu;
	}

	public Integer getGsst() {
		return gsst;
	}

	public void setGsst(Integer gsst) {
		this.gsst = gsst;
	}

	public Float getAnga() {
		return anga;
	}

	public void setAnga(Float anga) {
		this.anga = anga;
	}

	public Float getBmth() {
		return bmth;
	}

	public void setBmth(Float bmth) {
		this.bmth = bmth;
	}

	public String getInsi() {
		return insi;
	}

	public void setInsi(String insi) {
		this.insi = insi;
	}

	public Float getRaiy() {
		return raiy;
	}

	public void setRaiy(Float raiy) {
		this.raiy = raiy;
	}

	public Float getSray() {
		return sray;
	}

	public void setSray(Float sray) {
		this.sray = sray;
	}

	public Float getStartYr() {
		return startYr;
	}

	public void setStartYr(Float startYr) {
		this.startYr = startYr;
	}

	public Float getTmny() {
		return tmny;
	}

	public void setTmny(Float tmny) {
		this.tmny = tmny;
	}

	public Float getTmxy() {
		return tmxy;
	}

	public void setTmxy(Float tmxy) {
		this.tmxy = tmxy;
	}

	public String getFlaga() {
		return flaga;
	}

	public void setFlaga(String flaga) {
		this.flaga = flaga;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (wid != null ? wid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WeatherSource)) {
			return false;
		}
		WeatherSource other = (WeatherSource) object;
		if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.WeatherSources[ wid=" + wid + " ]";
	}

}
