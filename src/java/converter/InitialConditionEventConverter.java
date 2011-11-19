package converter;

import beans.InitialConditionEvent;
import beans.InitialConditionEventPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.InitialConditionLevelPK;
import beans.InitialConditionLevel;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "initialConditionEvent")
public class InitialConditionEventConverter {
	private InitialConditionEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of InitialConditionEventConverter */
	public InitialConditionEventConverter() {
		entity = new InitialConditionEvent();
	}

	/**
	 * Creates a new instance of InitialConditionEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public InitialConditionEventConverter(InitialConditionEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getInitialConditionEventPK().getExpId() + "," + entity.getInitialConditionEventPK().getIc() + "," + entity.getInitialConditionEventPK().getIcLayer() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getInitialConditionLevel();
	}

	/**
	 * Creates a new instance of InitialConditionEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public InitialConditionEventConverter(InitialConditionEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for initialConditionEventsPK.
	 *
	 * @return value for initialConditionEventsPK
	 */
	@XmlElement
	public InitialConditionEventPK getInitialConditionEventPK() {
		return (expandLevel > 0) ? entity.getInitialConditionEventPK() : null;
	}

	/**
	 * Setter for initialConditionEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setInitialConditionEventPK(InitialConditionEventPK value) {
		entity.setInitialConditionEventPK(value);
	}

	/**
	 * Getter for icbl.
	 *
	 * @return value for icbl
	 */
	@XmlElement
	public Integer getIcbl() {
		return (expandLevel > 0) ? entity.getIcbl() : null;
	}

	/**
	 * Setter for icbl.
	 *
	 * @param value the value to set
	 */
	public void setIcbl(Integer value) {
		entity.setIcbl(value);
	}

	/**
	 * Getter for ich2o.
	 *
	 * @return value for ich2o
	 */
	@XmlElement
	public Float getIch2o() {
		return (expandLevel > 0) ? entity.getIch2o() : null;
	}

	/**
	 * Setter for ich2o.
	 *
	 * @param value the value to set
	 */
	public void setIch2o(Float value) {
		entity.setIch2o(value);
	}

	/**
	 * Getter for icnh4.
	 *
	 * @return value for icnh4
	 */
	@XmlElement
	public Float getIcnh4() {
		return (expandLevel > 0) ? entity.getIcnh4() : null;
	}

	/**
	 * Setter for icnh4.
	 *
	 * @param value the value to set
	 */
	public void setIcnh4(Float value) {
		entity.setIcnh4(value);
	}

	/**
	 * Getter for icno3.
	 *
	 * @return value for icno3
	 */
	@XmlElement
	public Float getIcno3() {
		return (expandLevel > 0) ? entity.getIcno3() : null;
	}

	/**
	 * Setter for icno3.
	 *
	 * @param value the value to set
	 */
	public void setIcno3(Float value) {
		entity.setIcno3(value);
	}

	/**
	 * Getter for initialConditionLevels.
	 *
	 * @return value for initialConditionLevels
	 */
	@XmlElement
	public InitialConditionLevelConverter getInitialConditionLevel() {
		if (expandLevel > 0) {
			if (entity.getInitialConditionLevel() != null) {
				return new InitialConditionLevelConverter(entity.getInitialConditionLevel(), uri.resolve("initialConditionLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for initialConditionLevels.
	 *
	 * @param value the value to set
	 */
	public void setInitialConditionLevel(InitialConditionLevelConverter value) {
		entity.setInitialConditionLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the InitialConditionEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public InitialConditionEvent getEntity() {
		if (entity.getInitialConditionEventPK() == null) {
			InitialConditionEventConverter converter = UriResolver.getInstance().resolve(InitialConditionEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved InitialConditionEvents entity.
	 *
	 * @return an resolved entity
	 */
	public InitialConditionEvent resolveEntity(EntityManager em) {
		InitialConditionLevel initialConditionLevels = entity.getInitialConditionLevel();
		if (initialConditionLevels != null) {
			entity.setInitialConditionLevel(em.getReference(InitialConditionLevel.class, initialConditionLevels.getInitialConditionLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof InitialConditionEventConverter)) {
			return false;
		}
		InitialConditionEventConverter other = (InitialConditionEventConverter) object;
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
