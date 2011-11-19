package converter;

import beans.TillageEvent;
import beans.TillageEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.TillageLevelPK;
import beans.TillageLevel;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "tillageEvent")
public class TillageEventConverter {
	private TillageEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of TillageEventConverter */
	public TillageEventConverter() {
		entity = new TillageEvent();
	}

	/**
	 * Creates a new instance of TillageEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public TillageEventConverter(TillageEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getTillageEventPK().getExpId() + "," + entity.getTillageEventPK().getTi() + "," + entity.getTillageEventPK().getTiOpsNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getTillageLevel();
	}

	/**
	 * Creates a new instance of TillageEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public TillageEventConverter(TillageEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for tillageEventsPK.
	 *
	 * @return value for tillageEventsPK
	 */
	@XmlElement
	public TillageEventPK getTillageEventPK() {
		return (expandLevel > 0) ? entity.getTillageEventPK() : null;
	}

	/**
	 * Setter for tillageEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setTillageEventPK(TillageEventPK value) {
		entity.setTillageEventPK(value);
	}

	/**
	 * Getter for tdate.
	 *
	 * @return value for tdate
	 */
	@XmlElement
	public Date getTdate() {
		return (expandLevel > 0) ? entity.getTdate() : null;
	}

	/**
	 * Setter for tdate.
	 *
	 * @param value the value to set
	 */
	public void setTdate(Date value) {
		entity.setTdate(value);
	}

	/**
	 * Getter for tiord.
	 *
	 * @return value for tiord
	 */
	@XmlElement
	public Integer getTiord() {
		return (expandLevel > 0) ? entity.getTiord() : null;
	}

	/**
	 * Setter for tiord.
	 *
	 * @param value the value to set
	 */
	public void setTiord(Integer value) {
		entity.setTiord(value);
	}

	/**
	 * Getter for tiimp.
	 *
	 * @return value for tiimp
	 */
	@XmlElement
	public String getTiimp() {
		return (expandLevel > 0) ? entity.getTiimp() : null;
	}

	/**
	 * Setter for tiimp.
	 *
	 * @param value the value to set
	 */
	public void setTiimp(String value) {
		entity.setTiimp(value);
	}

	/**
	 * Getter for tidep.
	 *
	 * @return value for tidep
	 */
	@XmlElement
	public Float getTidep() {
		return (expandLevel > 0) ? entity.getTidep() : null;
	}

	/**
	 * Setter for tidep.
	 *
	 * @param value the value to set
	 */
	public void setTidep(Float value) {
		entity.setTidep(value);
	}

	/**
	 * Getter for timix.
	 *
	 * @return value for timix
	 */
	@XmlElement
	public Float getTimix() {
		return (expandLevel > 0) ? entity.getTimix() : null;
	}

	/**
	 * Setter for timix.
	 *
	 * @param value the value to set
	 */
	public void setTimix(Float value) {
		entity.setTimix(value);
	}

	/**
	 * Getter for tillageLevels.
	 *
	 * @return value for tillageLevels
	 */
	@XmlElement
	public TillageLevelConverter getTillageLevel() {
		if (expandLevel > 0) {
			if (entity.getTillageLevel() != null) {
				return new TillageLevelConverter(entity.getTillageLevel(), uri.resolve("tillageLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for tillageLevels.
	 *
	 * @param value the value to set
	 */
	public void setTillageLevel(TillageLevelConverter value) {
		entity.setTillageLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the TillageEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public TillageEvent getEntity() {
		if (entity.getTillageEventPK() == null) {
			TillageEventConverter converter = UriResolver.getInstance().resolve(TillageEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved TillageEvents entity.
	 *
	 * @return an resolved entity
	 */
	public TillageEvent resolveEntity(EntityManager em) {
		TillageLevel tillageLevels = entity.getTillageLevel();
		if (tillageLevels != null) {
			entity.setTillageLevel(em.getReference(TillageLevel.class, tillageLevels.getTillageLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TillageEventConverter)) {
			return false;
		}
		TillageEventConverter other = (TillageEventConverter) object;
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
