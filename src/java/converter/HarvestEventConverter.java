package converter;

import beans.HarvestEvent;
import beans.HarvestEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.HarvestLevelPK;
import beans.HarvestLevel;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "harvestEvent")
public class HarvestEventConverter {
	private HarvestEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of HarvestEventConverter */
	public HarvestEventConverter() {
		entity = new HarvestEvent();
	}

	/**
	 * Creates a new instance of HarvestEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public HarvestEventConverter(HarvestEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getHarvestEventPK().getExpId() + "," + entity.getHarvestEventPK().getHa() + "," + entity.getHarvestEventPK().getHaOpsNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getHarvestLevel();
	}

	/**
	 * Creates a new instance of HarvestEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public HarvestEventConverter(HarvestEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for harvestEventsPK.
	 *
	 * @return value for harvestEventsPK
	 */
	@XmlElement
	public HarvestEventPK getHarvestEventPK() {
		return (expandLevel > 0) ? entity.getHarvestEventPK() : null;
	}

	/**
	 * Setter for harvestEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setHarvestEventPK(HarvestEventPK value) {
		entity.setHarvestEventPK(value);
	}

	/**
	 * Getter for haday.
	 *
	 * @return value for haday
	 */
	@XmlElement
	public Date getHaday() {
		return (expandLevel > 0) ? entity.getHaday() : null;
	}

	/**
	 * Setter for haday.
	 *
	 * @param value the value to set
	 */
	public void setHaday(Date value) {
		entity.setHaday(value);
	}

	/**
	 * Getter for hastg.
	 *
	 * @return value for hastg
	 */
	@XmlElement
	public String getHastg() {
		return (expandLevel > 0) ? entity.getHastg() : null;
	}

	/**
	 * Setter for hastg.
	 *
	 * @param value the value to set
	 */
	public void setHastg(String value) {
		entity.setHastg(value);
	}

	/**
	 * Getter for hacom.
	 *
	 * @return value for hacom
	 */
	@XmlElement
	public String getHacom() {
		return (expandLevel > 0) ? entity.getHacom() : null;
	}

	/**
	 * Setter for hacom.
	 *
	 * @param value the value to set
	 */
	public void setHacom(String value) {
		entity.setHacom(value);
	}

	/**
	 * Getter for harm.
	 *
	 * @return value for harm
	 */
	@XmlElement
	public String getHarm() {
		return (expandLevel > 0) ? entity.getHarm() : null;
	}

	/**
	 * Setter for harm.
	 *
	 * @param value the value to set
	 */
	public void setHarm(String value) {
		entity.setHarm(value);
	}

	/**
	 * Getter for harea.
	 *
	 * @return value for harea
	 */
	@XmlElement
	public Float getHarea() {
		return (expandLevel > 0) ? entity.getHarea() : null;
	}

	/**
	 * Setter for harea.
	 *
	 * @param value the value to set
	 */
	public void setHarea(Float value) {
		entity.setHarea(value);
	}

	/**
	 * Getter for hasiz.
	 *
	 * @return value for hasiz
	 */
	@XmlElement
	public String getHasiz() {
		return (expandLevel > 0) ? entity.getHasiz() : null;
	}

	/**
	 * Setter for hasiz.
	 *
	 * @param value the value to set
	 */
	public void setHasiz(String value) {
		entity.setHasiz(value);
	}

	/**
	 * Getter for hapc.
	 *
	 * @return value for hapc
	 */
	@XmlElement
	public Float getHapc() {
		return (expandLevel > 0) ? entity.getHapc() : null;
	}

	/**
	 * Setter for hapc.
	 *
	 * @param value the value to set
	 */
	public void setHapc(Float value) {
		entity.setHapc(value);
	}

	/**
	 * Getter for habpc.
	 *
	 * @return value for habpc
	 */
	@XmlElement
	public Float getHabpc() {
		return (expandLevel > 0) ? entity.getHabpc() : null;
	}

	/**
	 * Setter for habpc.
	 *
	 * @param value the value to set
	 */
	public void setHabpc(Float value) {
		entity.setHabpc(value);
	}

	/**
	 * Getter for harvestLevels.
	 *
	 * @return value for harvestLevels
	 */
	@XmlElement
	public HarvestLevelConverter getHarvestLevel() {
		if (expandLevel > 0) {
			if (entity.getHarvestLevel() != null) {
				return new HarvestLevelConverter(entity.getHarvestLevel(), uri.resolve("harvestLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for harvestLevels.
	 *
	 * @param value the value to set
	 */
	public void setHarvestLevel(HarvestLevelConverter value) {
		entity.setHarvestLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the HarvestEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public HarvestEvent getEntity() {
		if (entity.getHarvestEventPK() == null) {
			HarvestEventConverter converter = UriResolver.getInstance().resolve(HarvestEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved HarvestEvents entity.
	 *
	 * @return an resolved entity
	 */
	public HarvestEvent resolveEntity(EntityManager em) {
		HarvestLevel harvestLevels = entity.getHarvestLevel();
		if (harvestLevels != null) {
			entity.setHarvestLevel(em.getReference(HarvestLevel.class, harvestLevels.getHarvestLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HarvestEventConverter)) {
			return false;
		}
		HarvestEventConverter other = (HarvestEventConverter) object;
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
