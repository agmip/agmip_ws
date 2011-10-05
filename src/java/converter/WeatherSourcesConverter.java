package converter;

import beans.WeatherSources;
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
@XmlRootElement(name = "weatherSources")
public class WeatherSourcesConverter {
	private WeatherSources entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of WeatherSourcesConverter */
	public WeatherSourcesConverter() {
		entity = new WeatherSources();
	}

	/**
	 * Creates a new instance of WeatherSourcesConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public WeatherSourcesConverter(WeatherSources entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getWid() + "/").build() : uri;
		this.expandLevel = expandLevel;
	}

	/**
	 * Creates a new instance of WeatherSourcesConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public WeatherSourcesConverter(WeatherSources entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for wid.
	 *
	 * @return value for wid
	 */
	@XmlElement
	public Integer getWid() {
		return (expandLevel > 0) ? entity.getWid() : null;
	}

	/**
	 * Setter for wid.
	 *
	 * @param value the value to set
	 */
	public void setWid(Integer value) {
		entity.setWid(value);
	}

	/**
	 * Getter for wstaId.
	 *
	 * @return value for wstaId
	 */
	@XmlElement
	public String getWstaId() {
		return (expandLevel > 0) ? entity.getWstaId() : null;
	}

	/**
	 * Setter for wstaId.
	 *
	 * @param value the value to set
	 */
	public void setWstaId(String value) {
		entity.setWstaId(value);
	}

	/**
	 * Getter for fdName.
	 *
	 * @return value for fdName
	 */
	@XmlElement
	public String getFdName() {
		return (expandLevel > 0) ? entity.getFdName() : null;
	}

	/**
	 * Setter for fdName.
	 *
	 * @param value the value to set
	 */
	public void setFdName(String value) {
		entity.setFdName(value);
	}

	/**
	 * Getter for site.
	 *
	 * @return value for site
	 */
	@XmlElement
	public String getSite() {
		return (expandLevel > 0) ? entity.getSite() : null;
	}

	/**
	 * Setter for site.
	 *
	 * @param value the value to set
	 */
	public void setSite(String value) {
		entity.setSite(value);
	}

	/**
	 * Getter for source.
	 *
	 * @return value for source
	 */
	@XmlElement
	public String getSource() {
		return (expandLevel > 0) ? entity.getSource() : null;
	}

	/**
	 * Setter for source.
	 *
	 * @param value the value to set
	 */
	public void setSource(String value) {
		entity.setSource(value);
	}

	/**
	 * Getter for lat.
	 *
	 * @return value for lat
	 */
	@XmlElement
	public Float getLat() {
		return (expandLevel > 0) ? entity.getLat() : null;
	}

	/**
	 * Setter for lat.
	 *
	 * @param value the value to set
	 */
	public void setLat(Float value) {
		entity.setLat(value);
	}

	/**
	 * Getter for lon.
	 *
	 * @return value for lon
	 */
	@XmlElement
	public Float getLong1() {
		return (expandLevel > 0) ? entity.getLon() : null;
	}

	/**
	 * Setter for long1.
	 *
	 * @param value the value to set
	 */
	public void setLon(Float value) {
		entity.setLon(value);
	}

	/**
	 * Getter for elev.
	 *
	 * @return value for elev
	 */
	@XmlElement
	public Float getElev() {
		return (expandLevel > 0) ? entity.getElev() : null;
	}

	/**
	 * Setter for elev.
	 *
	 * @param value the value to set
	 */
	public void setElev(Float value) {
		entity.setElev(value);
	}

	/**
	 * Getter for tav.
	 *
	 * @return value for tav
	 */
	@XmlElement
	public Float getTav() {
		return (expandLevel > 0) ? entity.getTav() : null;
	}

	/**
	 * Setter for tav.
	 *
	 * @param value the value to set
	 */
	public void setTav(Float value) {
		entity.setTav(value);
	}

	/**
	 * Getter for tamp.
	 *
	 * @return value for tamp
	 */
	@XmlElement
	public Float getTamp() {
		return (expandLevel > 0) ? entity.getTamp() : null;
	}

	/**
	 * Setter for tamp.
	 *
	 * @param value the value to set
	 */
	public void setTamp(Float value) {
		entity.setTamp(value);
	}

	/**
	 * Getter for temht.
	 *
	 * @return value for temht
	 */
	@XmlElement
	public Float getTemht() {
		return (expandLevel > 0) ? entity.getTemht() : null;
	}

	/**
	 * Setter for temht.
	 *
	 * @param value the value to set
	 */
	public void setTemht(Float value) {
		entity.setTemht(value);
	}

	/**
	 * Getter for refht.
	 *
	 * @return value for refht
	 */
	@XmlElement
	public Float getRefht() {
		return (expandLevel > 0) ? entity.getRefht() : null;
	}

	/**
	 * Setter for refht.
	 *
	 * @param value the value to set
	 */
	public void setRefht(Float value) {
		entity.setRefht(value);
	}

	/**
	 * Getter for wndht.
	 *
	 * @return value for wndht
	 */
	@XmlElement
	public Float getWndht() {
		return (expandLevel > 0) ? entity.getWndht() : null;
	}

	/**
	 * Setter for wndht.
	 *
	 * @param value the value to set
	 */
	public void setWndht(Float value) {
		entity.setWndht(value);
	}

	/**
	 * Getter for co2y.
	 *
	 * @return value for co2y
	 */
	@XmlElement
	public Float getCo2y() {
		return (expandLevel > 0) ? entity.getCo2y() : null;
	}

	/**
	 * Setter for co2y.
	 *
	 * @param value the value to set
	 */
	public void setCo2y(Float value) {
		entity.setCo2y(value);
	}

	/**
	 * Getter for co2a.
	 *
	 * @return value for co2a
	 */
	@XmlElement
	public Float getCo2a() {
		return (expandLevel > 0) ? entity.getCo2a() : null;
	}

	/**
	 * Setter for co2a.
	 *
	 * @param value the value to set
	 */
	public void setCo2a(Float value) {
		entity.setCo2a(value);
	}

	/**
	 * Getter for durn.
	 *
	 * @return value for durn
	 */
	@XmlElement
	public Float getDurn() {
		return (expandLevel > 0) ? entity.getDurn() : null;
	}

	/**
	 * Setter for durn.
	 *
	 * @param value the value to set
	 */
	public void setDurn(Float value) {
		entity.setDurn(value);
	}

	/**
	 * Getter for gsdu.
	 *
	 * @return value for gsdu
	 */
	@XmlElement
	public Float getGsdu() {
		return (expandLevel > 0) ? entity.getGsdu() : null;
	}

	/**
	 * Setter for gsdu.
	 *
	 * @param value the value to set
	 */
	public void setGsdu(Float value) {
		entity.setGsdu(value);
	}

	/**
	 * Getter for gsst.
	 *
	 * @return value for gsst
	 */
	@XmlElement
	public Integer getGsst() {
		return (expandLevel > 0) ? entity.getGsst() : null;
	}

	/**
	 * Setter for gsst.
	 *
	 * @param value the value to set
	 */
	public void setGsst(Integer value) {
		entity.setGsst(value);
	}

	/**
	 * Getter for anga.
	 *
	 * @return value for anga
	 */
	@XmlElement
	public Float getAnga() {
		return (expandLevel > 0) ? entity.getAnga() : null;
	}

	/**
	 * Setter for anga.
	 *
	 * @param value the value to set
	 */
	public void setAnga(Float value) {
		entity.setAnga(value);
	}

	/**
	 * Getter for bmth.
	 *
	 * @return value for bmth
	 */
	@XmlElement
	public Float getBmth() {
		return (expandLevel > 0) ? entity.getBmth() : null;
	}

	/**
	 * Setter for bmth.
	 *
	 * @param value the value to set
	 */
	public void setBmth(Float value) {
		entity.setBmth(value);
	}

	/**
	 * Getter for insi.
	 *
	 * @return value for insi
	 */
	@XmlElement
	public String getInsi() {
		return (expandLevel > 0) ? entity.getInsi() : null;
	}

	/**
	 * Setter for insi.
	 *
	 * @param value the value to set
	 */
	public void setInsi(String value) {
		entity.setInsi(value);
	}

	/**
	 * Getter for raiy.
	 *
	 * @return value for raiy
	 */
	@XmlElement
	public Float getRaiy() {
		return (expandLevel > 0) ? entity.getRaiy() : null;
	}

	/**
	 * Setter for raiy.
	 *
	 * @param value the value to set
	 */
	public void setRaiy(Float value) {
		entity.setRaiy(value);
	}

	/**
	 * Getter for sray.
	 *
	 * @return value for sray
	 */
	@XmlElement
	public Float getSray() {
		return (expandLevel > 0) ? entity.getSray() : null;
	}

	/**
	 * Setter for sray.
	 *
	 * @param value the value to set
	 */
	public void setSray(Float value) {
		entity.setSray(value);
	}

	/**
	 * Getter for startYr.
	 *
	 * @return value for startYr
	 */
	@XmlElement
	public Float getStartYr() {
		return (expandLevel > 0) ? entity.getStartYr() : null;
	}

	/**
	 * Setter for startYr.
	 *
	 * @param value the value to set
	 */
	public void setStartYr(Float value) {
		entity.setStartYr(value);
	}

	/**
	 * Getter for tmny.
	 *
	 * @return value for tmny
	 */
	@XmlElement
	public Float getTmny() {
		return (expandLevel > 0) ? entity.getTmny() : null;
	}

	/**
	 * Setter for tmny.
	 *
	 * @param value the value to set
	 */
	public void setTmny(Float value) {
		entity.setTmny(value);
	}

	/**
	 * Getter for tmxy.
	 *
	 * @return value for tmxy
	 */
	@XmlElement
	public Float getTmxy() {
		return (expandLevel > 0) ? entity.getTmxy() : null;
	}

	/**
	 * Setter for tmxy.
	 *
	 * @param value the value to set
	 */
	public void setTmxy(Float value) {
		entity.setTmxy(value);
	}

	/**
	 * Getter for flaga.
	 *
	 * @return value for flaga
	 */
	@XmlElement
	public String getFlaga() {
		return (expandLevel > 0) ? entity.getFlaga() : null;
	}

	/**
	 * Setter for flaga.
	 *
	 * @param value the value to set
	 */
	public void setFlaga(String value) {
		entity.setFlaga(value);
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
	 * Returns the WeatherSources entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public WeatherSources getEntity() {
		if (entity.getWid() == null) {
			WeatherSourcesConverter converter = UriResolver.getInstance().resolve(WeatherSourcesConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved WeatherSources entity.
	 *
	 * @return an resolved entity
	 */
	public WeatherSources resolveEntity(EntityManager em) {
		Integer max = (Integer) em.createQuery("select Max(w.wid) from WeatherSources w").getSingleResult();
		if (max == null){
			max = 0;
		}
		entity.setWid(++max);
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherSourcesConverter)) {
			return false;
		}
		WeatherSourcesConverter other = (WeatherSourcesConverter) object;
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
