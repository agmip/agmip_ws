package converter;

import beans.EnvironModifEvent;
import beans.EnvironModifEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.EnvironModifLevel;
import beans.EnvironModifLevelPK;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "environModifEvent")
public class EnvironModifEventConverter {
	private EnvironModifEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of EnvironModifEventConverter */
	public EnvironModifEventConverter() {
		entity = new EnvironModifEvent();
	}

	/**
	 * Creates a new instance of EnvironModifEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public EnvironModifEventConverter(EnvironModifEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getEnvironModifEventPK().getExpId() + "," + entity.getEnvironModifEventPK().getEm() + "," + entity.getEnvironModifEventPK().getEnvLevNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getEnvironModifLevel();
	}

	/**
	 * Creates a new instance of EnvironModifEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public EnvironModifEventConverter(EnvironModifEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for environModifEventsPK.
	 *
	 * @return value for environModifEventsPK
	 */
	@XmlElement
	public EnvironModifEventPK getEnvironModifEventPK() {
		return (expandLevel > 0) ? entity.getEnvironModifEventPK() : null;
	}

	/**
	 * Setter for environModifEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setEnvironModifEventPK(EnvironModifEventPK value) {
		entity.setEnvironModifEventPK(value);
	}

	/**
	 * Getter for emday.
	 *
	 * @return value for emday
	 */
	@XmlElement
	public Date getEmday() {
		return (expandLevel > 0) ? entity.getEmday() : null;
	}

	/**
	 * Setter for emday.
	 *
	 * @param value the value to set
	 */
	public void setEmday(Date value) {
		entity.setEmday(value);
	}

	/**
	 * Getter for ecdyl.
	 *
	 * @return value for ecdyl
	 */
	@XmlElement
	public String getEcdyl() {
		return (expandLevel > 0) ? entity.getEcdyl() : null;
	}

	/**
	 * Setter for ecdyl.
	 *
	 * @param value the value to set
	 */
	public void setEcdyl(String value) {
		entity.setEcdyl(value);
	}

	/**
	 * Getter for emdyl.
	 *
	 * @return value for emdyl
	 */
	@XmlElement
	public Float getEmdyl() {
		return (expandLevel > 0) ? entity.getEmdyl() : null;
	}

	/**
	 * Setter for emdyl.
	 *
	 * @param value the value to set
	 */
	public void setEmdyl(Float value) {
		entity.setEmdyl(value);
	}

	/**
	 * Getter for ecrad.
	 *
	 * @return value for ecrad
	 */
	@XmlElement
	public String getEcrad() {
		return (expandLevel > 0) ? entity.getEcrad() : null;
	}

	/**
	 * Setter for ecrad.
	 *
	 * @param value the value to set
	 */
	public void setEcrad(String value) {
		entity.setEcrad(value);
	}

	/**
	 * Getter for emrad.
	 *
	 * @return value for emrad
	 */
	@XmlElement
	public Float getEmrad() {
		return (expandLevel > 0) ? entity.getEmrad() : null;
	}

	/**
	 * Setter for emrad.
	 *
	 * @param value the value to set
	 */
	public void setEmrad(Float value) {
		entity.setEmrad(value);
	}

	/**
	 * Getter for ecmax.
	 *
	 * @return value for ecmax
	 */
	@XmlElement
	public String getEcmax() {
		return (expandLevel > 0) ? entity.getEcmax() : null;
	}

	/**
	 * Setter for ecmax.
	 *
	 * @param value the value to set
	 */
	public void setEcmax(String value) {
		entity.setEcmax(value);
	}

	/**
	 * Getter for emmax.
	 *
	 * @return value for emmax
	 */
	@XmlElement
	public Float getEmmax() {
		return (expandLevel > 0) ? entity.getEmmax() : null;
	}

	/**
	 * Setter for emmax.
	 *
	 * @param value the value to set
	 */
	public void setEmmax(Float value) {
		entity.setEmmax(value);
	}

	/**
	 * Getter for ecmin.
	 *
	 * @return value for ecmin
	 */
	@XmlElement
	public String getEcmin() {
		return (expandLevel > 0) ? entity.getEcmin() : null;
	}

	/**
	 * Setter for ecmin.
	 *
	 * @param value the value to set
	 */
	public void setEcmin(String value) {
		entity.setEcmin(value);
	}

	/**
	 * Getter for emmin.
	 *
	 * @return value for emmin
	 */
	@XmlElement
	public Float getEmmin() {
		return (expandLevel > 0) ? entity.getEmmin() : null;
	}

	/**
	 * Setter for emmin.
	 *
	 * @param value the value to set
	 */
	public void setEmmin(Float value) {
		entity.setEmmin(value);
	}

	/**
	 * Getter for ecrai.
	 *
	 * @return value for ecrai
	 */
	@XmlElement
	public String getEcrai() {
		return (expandLevel > 0) ? entity.getEcrai() : null;
	}

	/**
	 * Setter for ecrai.
	 *
	 * @param value the value to set
	 */
	public void setEcrai(String value) {
		entity.setEcrai(value);
	}

	/**
	 * Getter for emrai.
	 *
	 * @return value for emrai
	 */
	@XmlElement
	public Float getEmrai() {
		return (expandLevel > 0) ? entity.getEmrai() : null;
	}

	/**
	 * Setter for emrai.
	 *
	 * @param value the value to set
	 */
	public void setEmrai(Float value) {
		entity.setEmrai(value);
	}

	/**
	 * Getter for ecco2.
	 *
	 * @return value for ecco2
	 */
	@XmlElement
	public String getEcco2() {
		return (expandLevel > 0) ? entity.getEcco2() : null;
	}

	/**
	 * Setter for ecco2.
	 *
	 * @param value the value to set
	 */
	public void setEcco2(String value) {
		entity.setEcco2(value);
	}

	/**
	 * Getter for emco2.
	 *
	 * @return value for emco2
	 */
	@XmlElement
	public Float getEmco2() {
		return (expandLevel > 0) ? entity.getEmco2() : null;
	}

	/**
	 * Setter for emco2.
	 *
	 * @param value the value to set
	 */
	public void setEmco2(Float value) {
		entity.setEmco2(value);
	}

	/**
	 * Getter for emdew.
	 *
	 * @return value for emdew
	 */
	@XmlElement
	public Float getEmdew() {
		return (expandLevel > 0) ? entity.getEmdew() : null;
	}

	/**
	 * Setter for emdew.
	 *
	 * @param value the value to set
	 */
	public void setEmdew(Float value) {
		entity.setEmdew(value);
	}

	/**
	 * Getter for ecdew.
	 *
	 * @return value for ecdew
	 */
	@XmlElement
	public String getEcdew() {
		return (expandLevel > 0) ? entity.getEcdew() : null;
	}

	/**
	 * Setter for ecdew.
	 *
	 * @param value the value to set
	 */
	public void setEcdew(String value) {
		entity.setEcdew(value);
	}

	/**
	 * Getter for ecwnd.
	 *
	 * @return value for ecwnd
	 */
	@XmlElement
	public String getEcwnd() {
		return (expandLevel > 0) ? entity.getEcwnd() : null;
	}

	/**
	 * Setter for ecwnd.
	 *
	 * @param value the value to set
	 */
	public void setEcwnd(String value) {
		entity.setEcwnd(value);
	}

	/**
	 * Getter for emwnd.
	 *
	 * @return value for emwnd
	 */
	@XmlElement
	public Float getEmwnd() {
		return (expandLevel > 0) ? entity.getEmwnd() : null;
	}

	/**
	 * Setter for emwnd.
	 *
	 * @param value the value to set
	 */
	public void setEmwnd(Float value) {
		entity.setEmwnd(value);
	}

	/**
	 * Getter for environModifLevels.
	 *
	 * @return value for environModifLevels
	 */
	@XmlElement
	public EnvironModifLevelConverter getEnvironModifLevel() {
		if (expandLevel > 0) {
			if (entity.getEnvironModifLevel() != null) {
				return new EnvironModifLevelConverter(entity.getEnvironModifLevel(), uri.resolve("environModifLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for environModifLevels.
	 *
	 * @param value the value to set
	 */
	public void setEnvironModifLevel(EnvironModifLevelConverter value) {
		entity.setEnvironModifLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the EnvironModifEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public EnvironModifEvent getEntity() {
		if (entity.getEnvironModifEventPK() == null) {
			EnvironModifEventConverter converter = UriResolver.getInstance().resolve(EnvironModifEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved EnvironModifEvents entity.
	 *
	 * @return an resolved entity
	 */
	public EnvironModifEvent resolveEntity(EntityManager em) {
		EnvironModifLevel environModifLevels = entity.getEnvironModifLevel();
		if (environModifLevels != null) {
			entity.setEnvironModifLevel(em.getReference(EnvironModifLevel.class, environModifLevels.getEnvironModifLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EnvironModifEventConverter)) {
			return false;
		}
		EnvironModifEventConverter other = (EnvironModifEventConverter) object;
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
