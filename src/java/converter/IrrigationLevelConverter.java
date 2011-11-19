package converter;

import beans.IrrigationLevel;
import beans.IrrigationLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import beans.IrrigationEvent;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "irrigationLevel")
public class IrrigationLevelConverter {
	private IrrigationLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of IrrigationLevelConverter */
	public IrrigationLevelConverter() {
		entity = new IrrigationLevel();
	}

	/**
	 * Creates a new instance of IrrigationLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public IrrigationLevelConverter(IrrigationLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getIrrigationLevelPK().getExpId() + "," + entity.getIrrigationLevelPK().getIr() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getIrrigationEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of IrrigationLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public IrrigationLevelConverter(IrrigationLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for irrigationLevelsPK.
	 *
	 * @return value for irrigationLevelsPK
	 */
	@XmlElement
	public IrrigationLevelPK getIrrigationLevelPK() {
		return (expandLevel > 0) ? entity.getIrrigationLevelPK() : null;
	}

	/**
	 * Setter for irrigationLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setIrrigationLevelPK(IrrigationLevelPK value) {
		entity.setIrrigationLevelPK(value);
	}

	/**
	 * Getter for iame.
	 *
	 * @return value for iame
	 */
	@XmlElement
	public String getIame() {
		return (expandLevel > 0) ? entity.getIame() : null;
	}

	/**
	 * Setter for iame.
	 *
	 * @param value the value to set
	 */
	public void setIame(String value) {
		entity.setIame(value);
	}

	/**
	 * Getter for iamt.
	 *
	 * @return value for iamt
	 */
	@XmlElement
	public Float getIamt() {
		return (expandLevel > 0) ? entity.getIamt() : null;
	}

	/**
	 * Setter for iamt.
	 *
	 * @param value the value to set
	 */
	public void setIamt(Float value) {
		entity.setIamt(value);
	}

	/**
	 * Getter for ireff.
	 *
	 * @return value for ireff
	 */
	@XmlElement
	public Float getIreff() {
		return (expandLevel > 0) ? entity.getIreff() : null;
	}

	/**
	 * Setter for ireff.
	 *
	 * @param value the value to set
	 */
	public void setIreff(Float value) {
		entity.setIreff(value);
	}

	/**
	 * Getter for irstg.
	 *
	 * @return value for irstg
	 */
	@XmlElement
	public String getIrstg() {
		return (expandLevel > 0) ? entity.getIrstg() : null;
	}

	/**
	 * Setter for irstg.
	 *
	 * @param value the value to set
	 */
	public void setIrstg(String value) {
		entity.setIrstg(value);
	}

	/**
	 * Getter for irmdp.
	 *
	 * @return value for irmdp
	 */
	@XmlElement
	public Float getIrmdp() {
		return (expandLevel > 0) ? entity.getIrmdp() : null;
	}

	/**
	 * Setter for irmdp.
	 *
	 * @param value the value to set
	 */
	public void setIrmdp(Float value) {
		entity.setIrmdp(value);
	}

	/**
	 * Getter for irthr.
	 *
	 * @return value for irthr
	 */
	@XmlElement
	public Float getIrthr() {
		return (expandLevel > 0) ? entity.getIrthr() : null;
	}

	/**
	 * Setter for irthr.
	 *
	 * @param value the value to set
	 */
	public void setIrthr(Float value) {
		entity.setIrthr(value);
	}

	/**
	 * Getter for irept.
	 *
	 * @return value for irept
	 */
	@XmlElement
	public Float getIrept() {
		return (expandLevel > 0) ? entity.getIrept() : null;
	}

	/**
	 * Setter for irept.
	 *
	 * @param value the value to set
	 */
	public void setIrept(Float value) {
		entity.setIrept(value);
	}

	/**
	 * Getter for irName.
	 *
	 * @return value for irName
	 */
	@XmlElement
	public String getIrName() {
		return (expandLevel > 0) ? entity.getIrName() : null;
	}

	/**
	 * Setter for irName.
	 *
	 * @param value the value to set
	 */
	public void setIrName(String value) {
		entity.setIrName(value);
	}

	/**
	 * Getter for irNotes.
	 *
	 * @return value for irNotes
	 */
	@XmlElement
	public String getIrNotes() {
		return (expandLevel > 0) ? entity.getIrNotes() : null;
	}

	/**
	 * Setter for irNotes.
	 *
	 * @param value the value to set
	 */
	public void setIrNotes(String value) {
		entity.setIrNotes(value);
	}

	/**
	 * Getter for irrigationEventsCollection.
	 *
	 * @return value for irrigationEventsCollection
	 */
	@XmlElement
	public IrrigationEventsConverter getIrrigationEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getIrrigationEventsCollection() != null) {
				return new IrrigationEventsConverter(entity.getIrrigationEventsCollection(), uri.resolve("irrigationEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for irrigationEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setIrrigationEventsCollection(IrrigationEventsConverter value) {
		entity.setIrrigationEventsCollection((value != null) ? value.getEntities() : null);
	}

	/**
	 * Getter for treatmentsCollection.
	 *
	 * @return value for treatmentsCollection
	 */
	@XmlElement
	public TreatmentsConverter getTreatmentsCollection() {
		if (expandLevel > 0) {
			if (entity.getTreatmentsCollection() != null) {
				return new TreatmentsConverter(entity.getTreatmentsCollection(), uri.resolve("treatmentsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for treatmentsCollection.
	 *
	 * @param value the value to set
	 */
	public void setTreatmentsCollection(TreatmentsConverter value) {
		entity.setTreatmentsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the IrrigationLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public IrrigationLevel getEntity() {
		if (entity.getIrrigationLevelPK() == null) {
			IrrigationLevelConverter converter = UriResolver.getInstance().resolve(IrrigationLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved IrrigationLevels entity.
	 *
	 * @return an resolved entity
	 */
	public IrrigationLevel resolveEntity(EntityManager em) {
		Collection<IrrigationEvent> irrigationEventsCollection = entity.getIrrigationEventsCollection();
		Collection<IrrigationEvent> newirrigationEventsCollection = new java.util.ArrayList<IrrigationEvent>();
		if (irrigationEventsCollection != null) {
			for (IrrigationEvent item : irrigationEventsCollection) {
				newirrigationEventsCollection.add(em.getReference(IrrigationEvent.class, item.getIrrigationEventPK()));
			}
		}
		entity.setIrrigationEventsCollection(newirrigationEventsCollection);
		Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
		Collection<Treatment> newtreatmentsCollection = new java.util.ArrayList<Treatment>();
		if (treatmentsCollection != null) {
			for (Treatment item : treatmentsCollection) {
				newtreatmentsCollection.add(em.getReference(Treatment.class, item.getTreatmentPK()));
			}
		}
		entity.setTreatmentsCollection(newtreatmentsCollection);
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof IrrigationLevelConverter)) {
			return false;
		}
		IrrigationLevelConverter other = (IrrigationLevelConverter) object;
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
