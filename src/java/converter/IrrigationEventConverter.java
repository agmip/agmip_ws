package converter;

import beans.IrrigationEvent;
import beans.IrrigationEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.IrrigationLevel;
import beans.IrrigationLevelPK;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "irrigationEvent")
public class IrrigationEventConverter {
	private IrrigationEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of IrrigationEventConverter */
	public IrrigationEventConverter() {
		entity = new IrrigationEvent();
	}

	/**
	 * Creates a new instance of IrrigationEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public IrrigationEventConverter(IrrigationEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getIrrigationEventPK().getExpId() + "," + entity.getIrrigationEventPK().getIr() + "," + entity.getIrrigationEventPK().getIrAppNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getIrrigationLevel();
	}

	/**
	 * Creates a new instance of IrrigationEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public IrrigationEventConverter(IrrigationEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for irrigationEventsPK.
	 *
	 * @return value for irrigationEventsPK
	 */
	@XmlElement
	public IrrigationEventPK getIrrigationEventPK() {
		return (expandLevel > 0) ? entity.getIrrigationEventPK() : null;
	}

	/**
	 * Setter for irrigationEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setIrrigationEventPK(IrrigationEventPK value) {
		entity.setIrrigationEventPK(value);
	}

	/**
	 * Getter for idate.
	 *
	 * @return value for idate
	 */
	@XmlElement
	public Date getIdate() {
		return (expandLevel > 0) ? entity.getIdate() : null;
	}

	/**
	 * Setter for idate.
	 *
	 * @param value the value to set
	 */
	public void setIdate(Date value) {
		entity.setIdate(value);
	}

	/**
	 * Getter for iradp.
	 *
	 * @return value for iradp
	 */
	@XmlElement
	public Float getIradp() {
		return (expandLevel > 0) ? entity.getIradp() : null;
	}

	/**
	 * Setter for iradp.
	 *
	 * @param value the value to set
	 */
	public void setIradp(Float value) {
		entity.setIradp(value);
	}

	/**
	 * Getter for irop.
	 *
	 * @return value for irop
	 */
	@XmlElement
	public String getIrop() {
		return (expandLevel > 0) ? entity.getIrop() : null;
	}

	/**
	 * Setter for irop.
	 *
	 * @param value the value to set
	 */
	public void setIrop(String value) {
		entity.setIrop(value);
	}

	/**
	 * Getter for irval.
	 *
	 * @return value for irval
	 */
	@XmlElement
	public Float getIrval() {
		return (expandLevel > 0) ? entity.getIrval() : null;
	}

	/**
	 * Setter for irval.
	 *
	 * @param value the value to set
	 */
	public void setIrval(Float value) {
		entity.setIrval(value);
	}

	/**
	 * Getter for irnpct.
	 *
	 * @return value for irnpct
	 */
	@XmlElement
	public Float getIrnpct() {
		return (expandLevel > 0) ? entity.getIrnpct() : null;
	}

	/**
	 * Setter for irnpct.
	 *
	 * @param value the value to set
	 */
	public void setIrnpct(Float value) {
		entity.setIrnpct(value);
	}

	/**
	 * Getter for abund.
	 *
	 * @return value for abund
	 */
	@XmlElement
	public Float getAbund() {
		return (expandLevel > 0) ? entity.getAbund() : null;
	}

	/**
	 * Setter for abund.
	 *
	 * @param value the value to set
	 */
	public void setAbund(Float value) {
		entity.setAbund(value);
	}

	/**
	 * Getter for irrigationLevels.
	 *
	 * @return value for irrigationLevels
	 */
	@XmlElement
	public IrrigationLevelConverter getIrrigationLevel() {
		if (expandLevel > 0) {
			if (entity.getIrrigationLevel() != null) {
				return new IrrigationLevelConverter(entity.getIrrigationLevel(), uri.resolve("irrigationLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for irrigationLevels.
	 *
	 * @param value the value to set
	 */
	public void setIrrigationLevel(IrrigationLevelConverter value) {
		entity.setIrrigationLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the IrrigationEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public IrrigationEvent getEntity() {
		if (entity.getIrrigationEventPK() == null) {
			IrrigationEventConverter converter = UriResolver.getInstance().resolve(IrrigationEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved IrrigationEvents entity.
	 *
	 * @return an resolved entity
	 */
	public IrrigationEvent resolveEntity(EntityManager em) {
		IrrigationLevel irrigationLevels = entity.getIrrigationLevel();
		if (irrigationLevels != null) {
			entity.setIrrigationLevel(em.getReference(IrrigationLevel.class, irrigationLevels.getIrrigationLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof IrrigationEventConverter)) {
			return false;
		}
		IrrigationEventConverter other = (IrrigationEventConverter) object;
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
