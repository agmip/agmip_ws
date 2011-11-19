package converter;

import beans.OrganicMaterialEvent;
import beans.OrganicMaterialEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.OrganicMaterialLevelPK;
import beans.OrganicMaterialLevel;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "organicMaterialEvent")
public class OrganicMaterialEventConverter {
	private OrganicMaterialEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of OrganicMaterialEventConverter */
	public OrganicMaterialEventConverter() {
		entity = new OrganicMaterialEvent();
	}

	/**
	 * Creates a new instance of OrganicMaterialEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public OrganicMaterialEventConverter(OrganicMaterialEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getOrganicMaterialEventPK().getExpId() + "," + entity.getOrganicMaterialEventPK().getOm() + "," + entity.getOrganicMaterialEventPK().getOmOpsNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getOrganicMaterialLevel();
	}

	/**
	 * Creates a new instance of OrganicMaterialEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public OrganicMaterialEventConverter(OrganicMaterialEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for organicMaterialEventsPK.
	 *
	 * @return value for organicMaterialEventsPK
	 */
	@XmlElement
	public OrganicMaterialEventPK getOrganicMaterialEventPK() {
		return (expandLevel > 0) ? entity.getOrganicMaterialEventPK() : null;
	}

	/**
	 * Setter for organicMaterialEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setOrganicMaterialEventPK(OrganicMaterialEventPK value) {
		entity.setOrganicMaterialEventPK(value);
	}

	/**
	 * Getter for omdat.
	 *
	 * @return value for omdat
	 */
	@XmlElement
	public Date getOmdat() {
		return (expandLevel > 0) ? entity.getOmdat() : null;
	}

	/**
	 * Setter for omdat.
	 *
	 * @param value the value to set
	 */
	public void setOmdat(Date value) {
		entity.setOmdat(value);
	}

	/**
	 * Getter for omcd.
	 *
	 * @return value for omcd
	 */
	@XmlElement
	public String getOmcd() {
		return (expandLevel > 0) ? entity.getOmcd() : null;
	}

	/**
	 * Setter for omcd.
	 *
	 * @param value the value to set
	 */
	public void setOmcd(String value) {
		entity.setOmcd(value);
	}

	/**
	 * Getter for omacd.
	 *
	 * @return value for omacd
	 */
	@XmlElement
	public String getOmacd() {
		return (expandLevel > 0) ? entity.getOmacd() : null;
	}

	/**
	 * Setter for omacd.
	 *
	 * @param value the value to set
	 */
	public void setOmacd(String value) {
		entity.setOmacd(value);
	}

	/**
	 * Getter for omdep.
	 *
	 * @return value for omdep
	 */
	@XmlElement
	public Float getOmdep() {
		return (expandLevel > 0) ? entity.getOmdep() : null;
	}

	/**
	 * Setter for omdep.
	 *
	 * @param value the value to set
	 */
	public void setOmdep(Float value) {
		entity.setOmdep(value);
	}

	/**
	 * Getter for ominp.
	 *
	 * @return value for ominp
	 */
	@XmlElement
	public Float getOminp() {
		return (expandLevel > 0) ? entity.getOminp() : null;
	}

	/**
	 * Setter for ominp.
	 *
	 * @param value the value to set
	 */
	public void setOminp(Float value) {
		entity.setOminp(value);
	}

	/**
	 * Getter for omamt.
	 *
	 * @return value for omamt
	 */
	@XmlElement
	public Float getOmamt() {
		return (expandLevel > 0) ? entity.getOmamt() : null;
	}

	/**
	 * Setter for omamt.
	 *
	 * @param value the value to set
	 */
	public void setOmamt(Float value) {
		entity.setOmamt(value);
	}

	/**
	 * Getter for omh2o.
	 *
	 * @return value for omh2o
	 */
	@XmlElement
	public Float getOmh2o() {
		return (expandLevel > 0) ? entity.getOmh2o() : null;
	}

	/**
	 * Setter for omh2o.
	 *
	 * @param value the value to set
	 */
	public void setOmh2o(Float value) {
		entity.setOmh2o(value);
	}

	/**
	 * Getter for omcpct.
	 *
	 * @return value for omcpct
	 */
	@XmlElement
	public Float getOmcpct() {
		return (expandLevel > 0) ? entity.getOmcpct() : null;
	}

	/**
	 * Setter for omcpct.
	 *
	 * @param value the value to set
	 */
	public void setOmcpct(Float value) {
		entity.setOmcpct(value);
	}

	/**
	 * Getter for omnpct.
	 *
	 * @return value for omnpct
	 */
	@XmlElement
	public Float getOmnpct() {
		return (expandLevel > 0) ? entity.getOmnpct() : null;
	}

	/**
	 * Setter for omnpct.
	 *
	 * @param value the value to set
	 */
	public void setOmnpct(Float value) {
		entity.setOmnpct(value);
	}

	/**
	 * Getter for omppct.
	 *
	 * @return value for omppct
	 */
	@XmlElement
	public Float getOmppct() {
		return (expandLevel > 0) ? entity.getOmppct() : null;
	}

	/**
	 * Setter for omppct.
	 *
	 * @param value the value to set
	 */
	public void setOmppct(Float value) {
		entity.setOmppct(value);
	}

	/**
	 * Getter for omkpct.
	 *
	 * @return value for omkpct
	 */
	@XmlElement
	public Float getOmkpct() {
		return (expandLevel > 0) ? entity.getOmkpct() : null;
	}

	/**
	 * Setter for omkpct.
	 *
	 * @param value the value to set
	 */
	public void setOmkpct(Float value) {
		entity.setOmkpct(value);
	}

	/**
	 * Getter for omlipct.
	 *
	 * @return value for omlipct
	 */
	@XmlElement
	public Float getOmlipct() {
		return (expandLevel > 0) ? entity.getOmlipct() : null;
	}

	/**
	 * Setter for omlipct.
	 *
	 * @param value the value to set
	 */
	public void setOmlipct(Float value) {
		entity.setOmlipct(value);
	}

	/**
	 * Getter for organicMaterialLevels.
	 *
	 * @return value for organicMaterialLevels
	 */
	@XmlElement
	public OrganicMaterialLevelConverter getOrganicMaterialLevel() {
		if (expandLevel > 0) {
			if (entity.getOrganicMaterialLevel() != null) {
				return new OrganicMaterialLevelConverter(entity.getOrganicMaterialLevel(), uri.resolve("organicMaterialLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for organicMaterialLevels.
	 *
	 * @param value the value to set
	 */
	public void setOrganicMaterialLevel(OrganicMaterialLevelConverter value) {
		entity.setOrganicMaterialLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the OrganicMaterialEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public OrganicMaterialEvent getEntity() {
		if (entity.getOrganicMaterialEventPK() == null) {
			OrganicMaterialEventConverter converter = UriResolver.getInstance().resolve(OrganicMaterialEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved OrganicMaterialEvents entity.
	 *
	 * @return an resolved entity
	 */
	public OrganicMaterialEvent resolveEntity(EntityManager em) {
		OrganicMaterialLevel organicMaterialLevels = entity.getOrganicMaterialLevel();
		if (organicMaterialLevels != null) {
			entity.setOrganicMaterialLevel(em.getReference(OrganicMaterialLevel.class, organicMaterialLevels.getOrganicMaterialLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OrganicMaterialEventConverter)) {
			return false;
		}
		OrganicMaterialEventConverter other = (OrganicMaterialEventConverter) object;
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
