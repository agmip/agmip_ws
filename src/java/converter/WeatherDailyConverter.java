/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.WeatherDaily;
import beans.WeatherDailyPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "weatherDaily")
public class WeatherDailyConverter {
	private WeatherDaily entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of WeatherDailyConverter */
	public WeatherDailyConverter() {
		entity = new WeatherDaily();
	}

	/**
	 * Creates a new instance of WeatherDailyConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public WeatherDailyConverter(WeatherDaily entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getWeatherDailyPK().getWid() + "," + entity.getWeatherDailyPK().getWtyr() + "," + entity.getWeatherDailyPK().getWtday() + "/").build() : uri;
		this.expandLevel = expandLevel;
	}

	/**
	 * Creates a new instance of WeatherDailyConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public WeatherDailyConverter(WeatherDaily entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for weatherDailyPK.
	 *
	 * @return value for weatherDailyPK
	 */
	@XmlElement
	public WeatherDailyPK getWeatherDailyPK() {
		return (expandLevel > 0) ? entity.getWeatherDailyPK() : null;
	}

	/**
	 * Setter for weatherDailyPK.
	 *
	 * @param value the value to set
	 */
	public void setWeatherDailyPK(WeatherDailyPK value) {
		entity.setWeatherDailyPK(value);
	}

	/**
	 * Getter for srad.
	 *
	 * @return value for srad
	 */
	@XmlElement
	public Float getSrad() {
		return (expandLevel > 0) ? entity.getSrad() : null;
	}

	/**
	 * Setter for srad.
	 *
	 * @param value the value to set
	 */
	public void setSrad(Float value) {
		entity.setSrad(value);
	}

	/**
	 * Getter for tmax.
	 *
	 * @return value for tmax
	 */
	@XmlElement
	public Float getTmax() {
		return (expandLevel > 0) ? entity.getTmax() : null;
	}

	/**
	 * Setter for tmax.
	 *
	 * @param value the value to set
	 */
	public void setTmax(Float value) {
		entity.setTmax(value);
	}

	/**
	 * Getter for tmin.
	 *
	 * @return value for tmin
	 */
	@XmlElement
	public Float getTmin() {
		return (expandLevel > 0) ? entity.getTmin() : null;
	}

	/**
	 * Setter for tmin.
	 *
	 * @param value the value to set
	 */
	public void setTmin(Float value) {
		entity.setTmin(value);
	}

	/**
	 * Getter for rain.
	 *
	 * @return value for rain
	 */
	@XmlElement
	public Float getRain() {
		return (expandLevel > 0) ? entity.getRain() : null;
	}

	/**
	 * Setter for rain.
	 *
	 * @param value the value to set
	 */
	public void setRain(Float value) {
		entity.setRain(value);
	}

	/**
	 * Getter for wind.
	 *
	 * @return value for wind
	 */
	@XmlElement
	public Float getWind() {
		return (expandLevel > 0) ? entity.getWind() : null;
	}

	/**
	 * Setter for wind.
	 *
	 * @param value the value to set
	 */
	public void setWind(Float value) {
		entity.setWind(value);
	}

	/**
	 * Getter for tdew.
	 *
	 * @return value for tdew
	 */
	@XmlElement
	public Float getTdew() {
		return (expandLevel > 0) ? entity.getTdew() : null;
	}

	/**
	 * Setter for tdew.
	 *
	 * @param value the value to set
	 */
	public void setTdew(Float value) {
		entity.setTdew(value);
	}

	/**
	 * Getter for co2d.
	 *
	 * @return value for co2d
	 */
	@XmlElement
	public Float getCo2d() {
		return (expandLevel > 0) ? entity.getCo2d() : null;
	}

	/**
	 * Setter for co2d.
	 *
	 * @param value the value to set
	 */
	public void setCo2d(Float value) {
		entity.setCo2d(value);
	}

	/**
	 * Getter for cldd.
	 *
	 * @return value for cldd
	 */
	@XmlElement
	public Float getCldd() {
		return (expandLevel > 0) ? entity.getCldd() : null;
	}

	/**
	 * Setter for cldd.
	 *
	 * @param value the value to set
	 */
	public void setCldd(Float value) {
		entity.setCldd(value);
	}

	/**
	 * Getter for dewp.
	 *
	 * @return value for dewp
	 */
	@XmlElement
	public Float getDewp() {
		return (expandLevel > 0) ? entity.getDewp() : null;
	}

	/**
	 * Setter for dewp.
	 *
	 * @param value the value to set
	 */
	public void setDewp(Float value) {
		entity.setDewp(value);
	}

	/**
	 * Getter for pard.
	 *
	 * @return value for pard
	 */
	@XmlElement
	public Float getPard() {
		return (expandLevel > 0) ? entity.getPard() : null;
	}

	/**
	 * Setter for pard.
	 *
	 * @param value the value to set
	 */
	public void setPard(Float value) {
		entity.setPard(value);
	}

	/**
	 * Getter for dtr.
	 *
	 * @return value for dtr
	 */
	@XmlElement
	public Float getDtr() {
		return (expandLevel > 0) ? entity.getDtr() : null;
	}

	/**
	 * Setter for dtr.
	 *
	 * @param value the value to set
	 */
	public void setDtr(Float value) {
		entity.setDtr(value);
	}

	/**
	 * Getter for sunh.
	 *
	 * @return value for sunh
	 */
	@XmlElement
	public Float getSunh() {
		return (expandLevel > 0) ? entity.getSunh() : null;
	}

	/**
	 * Setter for sunh.
	 *
	 * @param value the value to set
	 */
	public void setSunh(Float value) {
		entity.setSunh(value);
	}

	/**
	 * Getter for tdry.
	 *
	 * @return value for tdry
	 */
	@XmlElement
	public Float getTdry() {
		return (expandLevel > 0) ? entity.getTdry() : null;
	}

	/**
	 * Setter for tdry.
	 *
	 * @param value the value to set
	 */
	public void setTdry(Float value) {
		entity.setTdry(value);
	}

	/**
	 * Getter for evap.
	 *
	 * @return value for evap
	 */
	@XmlElement
	public Float getEvap() {
		return (expandLevel > 0) ? entity.getEvap() : null;
	}

	/**
	 * Setter for evap.
	 *
	 * @param value the value to set
	 */
	public void setEvap(Float value) {
		entity.setEvap(value);
	}

	/**
	 * Getter for twet.
	 *
	 * @return value for twet
	 */
	@XmlElement
	public Float getTwet() {
		return (expandLevel > 0) ? entity.getTwet() : null;
	}

	/**
	 * Setter for twet.
	 *
	 * @param value the value to set
	 */
	public void setTwet(Float value) {
		entity.setTwet(value);
	}

	/**
	 * Getter for dayd.
	 *
	 * @return value for dayd
	 */
	@XmlElement
	public Float getDayd() {
		return (expandLevel > 0) ? entity.getDayd() : null;
	}

	/**
	 * Setter for dayd.
	 *
	 * @param value the value to set
	 */
	public void setDayd(Float value) {
		entity.setDayd(value);
	}

	/**
	 * Getter for da3d.
	 *
	 * @return value for da3d
	 */
	@XmlElement
	public Float getDa3d() {
		return (expandLevel > 0) ? entity.getDa3d() : null;
	}

	/**
	 * Setter for da3d.
	 *
	 * @param value the value to set
	 */
	public void setDa3d(Float value) {
		entity.setDa3d(value);
	}

	/**
	 * Getter for twld.
	 *
	 * @return value for twld
	 */
	@XmlElement
	public Float getTwld() {
		return (expandLevel > 0) ? entity.getTwld() : null;
	}

	/**
	 * Setter for twld.
	 *
	 * @param value the value to set
	 */
	public void setTwld(Float value) {
		entity.setTwld(value);
	}

	/**
	 * Getter for aco2.
	 *
	 * @return value for aco2
	 */
	@XmlElement
	public Float getAco2() {
		return (expandLevel > 0) ? entity.getAco2() : null;
	}

	/**
	 * Setter for aco2.
	 *
	 * @param value the value to set
	 */
	public void setAco2(Float value) {
		entity.setAco2(value);
	}

	/**
	 * Getter for dyld.
	 *
	 * @return value for dyld
	 */
	@XmlElement
	public Float getDyld() {
		return (expandLevel > 0) ? entity.getDyld() : null;
	}

	/**
	 * Setter for dyld.
	 *
	 * @param value the value to set
	 */
	public void setDyld(Float value) {
		entity.setDyld(value);
	}

	/**
	 * Getter for pred.
	 *
	 * @return value for pred
	 */
	@XmlElement
	public Float getPred() {
		return (expandLevel > 0) ? entity.getPred() : null;
	}

	/**
	 * Setter for pred.
	 *
	 * @param value the value to set
	 */
	public void setPred(Float value) {
		entity.setPred(value);
	}

	/**
	 * Getter for tavd.
	 *
	 * @return value for tavd
	 */
	@XmlElement
	public Float getTavd() {
		return (expandLevel > 0) ? entity.getTavd() : null;
	}

	/**
	 * Setter for tavd.
	 *
	 * @param value the value to set
	 */
	public void setTavd(Float value) {
		entity.setTavd(value);
	}

	/**
	 * Getter for tdyd.
	 *
	 * @return value for tdyd
	 */
	@XmlElement
	public Float getTdyd() {
		return (expandLevel > 0) ? entity.getTdyd() : null;
	}

	/**
	 * Setter for tdyd.
	 *
	 * @param value the value to set
	 */
	public void setTdyd(Float value) {
		entity.setTdyd(value);
	}

	/**
	 * Getter for tgad.
	 *
	 * @return value for tgad
	 */
	@XmlElement
	public Float getTgad() {
		return (expandLevel > 0) ? entity.getTgad() : null;
	}

	/**
	 * Setter for tgad.
	 *
	 * @param value the value to set
	 */
	public void setTgad(Float value) {
		entity.setTgad(value);
	}

	/**
	 * Getter for tgrd.
	 *
	 * @return value for tgrd
	 */
	@XmlElement
	public Float getTgrd() {
		return (expandLevel > 0) ? entity.getTgrd() : null;
	}

	/**
	 * Setter for tgrd.
	 *
	 * @param value the value to set
	 */
	public void setTgrd(Float value) {
		entity.setTgrd(value);
	}

	/**
	 * Getter for tmen.
	 *
	 * @return value for tmen
	 */
	@XmlElement
	public Float getTmen() {
		return (expandLevel > 0) ? entity.getTmen() : null;
	}

	/**
	 * Setter for tmen.
	 *
	 * @param value the value to set
	 */
	public void setTmen(Float value) {
		entity.setTmen(value);
	}

	/**
	 * Getter for tmna.
	 *
	 * @return value for tmna
	 */
	@XmlElement
	public Float getTmna() {
		return (expandLevel > 0) ? entity.getTmna() : null;
	}

	/**
	 * Setter for tmna.
	 *
	 * @param value the value to set
	 */
	public void setTmna(Float value) {
		entity.setTmna(value);
	}

	/**
	 * Getter for tmxa.
	 *
	 * @return value for tmxa
	 */
	@XmlElement
	public Float getTmxa() {
		return (expandLevel > 0) ? entity.getTmxa() : null;
	}

	/**
	 * Setter for tmxa.
	 *
	 * @param value the value to set
	 */
	public void setTmxa(Float value) {
		entity.setTmxa(value);
	}

	/**
	 * Getter for ts1d.
	 *
	 * @return value for ts1d
	 */
	@XmlElement
	public Float getTs1d() {
		return (expandLevel > 0) ? entity.getTs1d() : null;
	}

	/**
	 * Setter for ts1d.
	 *
	 * @param value the value to set
	 */
	public void setTs1d(Float value) {
		entity.setTs1d(value);
	}

	/**
	 * Getter for ts2d.
	 *
	 * @return value for ts2d
	 */
	@XmlElement
	public Float getTs2d() {
		return (expandLevel > 0) ? entity.getTs2d() : null;
	}

	/**
	 * Setter for ts2d.
	 *
	 * @param value the value to set
	 */
	public void setTs2d(Float value) {
		entity.setTs2d(value);
	}

	/**
	 * Getter for ts3d.
	 *
	 * @return value for ts3d
	 */
	@XmlElement
	public Float getTs3d() {
		return (expandLevel > 0) ? entity.getTs3d() : null;
	}

	/**
	 * Setter for ts3d.
	 *
	 * @param value the value to set
	 */
	public void setTs3d(Float value) {
		entity.setTs3d(value);
	}

	/**
	 * Getter for ts4d.
	 *
	 * @return value for ts4d
	 */
	@XmlElement
	public Float getTs4d() {
		return (expandLevel > 0) ? entity.getTs4d() : null;
	}

	/**
	 * Setter for ts4d.
	 *
	 * @param value the value to set
	 */
	public void setTs4d(Float value) {
		entity.setTs4d(value);
	}

	/**
	 * Getter for ts5d.
	 *
	 * @return value for ts5d
	 */
	@XmlElement
	public Float getTs5d() {
		return (expandLevel > 0) ? entity.getTs5d() : null;
	}

	/**
	 * Setter for ts5d.
	 *
	 * @param value the value to set
	 */
	public void setTs5d(Float value) {
		entity.setTs5d(value);
	}

	/**
	 * Getter for ts6d.
	 *
	 * @return value for ts6d
	 */
	@XmlElement
	public Float getTs6d() {
		return (expandLevel > 0) ? entity.getTs6d() : null;
	}

	/**
	 * Setter for ts6d.
	 *
	 * @param value the value to set
	 */
	public void setTs6d(Float value) {
		entity.setTs6d(value);
	}

	/**
	 * Getter for ts7d.
	 *
	 * @return value for ts7d
	 */
	@XmlElement
	public Float getTs7d() {
		return (expandLevel > 0) ? entity.getTs7d() : null;
	}

	/**
	 * Setter for ts7d.
	 *
	 * @param value the value to set
	 */
	public void setTs7d(Float value) {
		entity.setTs7d(value);
	}

	/**
	 * Getter for ts8d.
	 *
	 * @return value for ts8d
	 */
	@XmlElement
	public Float getTs8d() {
		return (expandLevel > 0) ? entity.getTs8d() : null;
	}

	/**
	 * Setter for ts8d.
	 *
	 * @param value the value to set
	 */
	public void setTs8d(Float value) {
		entity.setTs8d(value);
	}

	/**
	 * Getter for ts9d.
	 *
	 * @return value for ts9d
	 */
	@XmlElement
	public Float getTs9d() {
		return (expandLevel > 0) ? entity.getTs9d() : null;
	}

	/**
	 * Setter for ts9d.
	 *
	 * @param value the value to set
	 */
	public void setTs9d(Float value) {
		entity.setTs9d(value);
	}

	/**
	 * Getter for ts10d.
	 *
	 * @return value for ts10d
	 */
	@XmlElement
	public Float getTs10d() {
		return (expandLevel > 0) ? entity.getTs10d() : null;
	}

	/**
	 * Setter for ts10d.
	 *
	 * @param value the value to set
	 */
	public void setTs10d(Float value) {
		entity.setTs10d(value);
	}

	/**
	 * Getter for flagw.
	 *
	 * @return value for flagw
	 */
	@XmlElement
	public String getFlagw() {
		return (expandLevel > 0) ? entity.getFlagw() : null;
	}

	/**
	 * Setter for flagw.
	 *
	 * @param value the value to set
	 */
	public void setFlagw(String value) {
		entity.setFlagw(value);
	}

	/**
	 * Returns the URI associated with this converter.
	 *
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	/**
	 * Sets the URI for this reference converter.
	 *
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

	/**
	 * Returns the WeatherDaily entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public WeatherDaily getEntity() {
		if (entity.getWeatherDailyPK() == null) {
			WeatherDailyConverter converter = UriResolver.getInstance().resolve(WeatherDailyConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved WeatherDaily entity.
	 *
	 * @return an resolved entity
	 */
	public WeatherDaily resolveEntity(EntityManager em) {
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherDailyConverter)) {
			return false;
		}
		WeatherDailyConverter other = (WeatherDailyConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (expandLevel <= 0) {
			return true;
		}
		if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		if (expandLevel <= 0) {
			return hash + 37 * expandLevel;
		}
		return hash + 37 * (expandLevel + 37 * (entity == null ? 0 : entity.hashCode()));
	}
}
