package converter;

import beans.HarvestLevel;
import beans.HarvestLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import beans.HarvestEvent;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "harvestLevel")
public class HarvestLevelConverter {
	private HarvestLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of HarvestLevelConverter */
	public HarvestLevelConverter() {
		entity = new HarvestLevel();
	}

	/**
	 * Creates a new instance of HarvestLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public HarvestLevelConverter(HarvestLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getHarvestLevelPK().getExpId() + "," + entity.getHarvestLevelPK().getHa() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getHarvestEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of HarvestLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public HarvestLevelConverter(HarvestLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for harvestLevelsPK.
	 *
	 * @return value for harvestLevelsPK
	 */
	@XmlElement
	public HarvestLevelPK getHarvestLevelPK() {
		return (expandLevel > 0) ? entity.getHarvestLevelPK() : null;
	}

	/**
	 * Setter for harvestLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setHarvestLevelPK(HarvestLevelPK value) {
		entity.setHarvestLevelPK(value);
	}

	/**
	 * Getter for haName.
	 *
	 * @return value for haName
	 */
	@XmlElement
	public String getHaName() {
		return (expandLevel > 0) ? entity.getHaName() : null;
	}

	/**
	 * Setter for haName.
	 *
	 * @param value the value to set
	 */
	public void setHaName(String value) {
		entity.setHaName(value);
	}

	/**
	 * Getter for haNotes.
	 *
	 * @return value for haNotes
	 */
	@XmlElement
	public String getHaNotes() {
		return (expandLevel > 0) ? entity.getHaNotes() : null;
	}

	/**
	 * Setter for haNotes.
	 *
	 * @param value the value to set
	 */
	public void setHaNotes(String value) {
		entity.setHaNotes(value);
	}

	/**
	 * Getter for harvestEventsCollection.
	 *
	 * @return value for harvestEventsCollection
	 */
	@XmlElement
	public HarvestEventsConverter getHarvestEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getHarvestEventsCollection() != null) {
				return new HarvestEventsConverter(entity.getHarvestEventsCollection(), uri.resolve("harvestEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for harvestEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setHarvestEventsCollection(HarvestEventsConverter value) {
		entity.setHarvestEventsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the HarvestLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public HarvestLevel getEntity() {
		if (entity.getHarvestLevelPK() == null) {
			HarvestLevelConverter converter = UriResolver.getInstance().resolve(HarvestLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved HarvestLevels entity.
	 *
	 * @return an resolved entity
	 */
	public HarvestLevel resolveEntity(EntityManager em) {
		Collection<HarvestEvent> harvestEventsCollection = entity.getHarvestEventsCollection();
		Collection<HarvestEvent> newharvestEventsCollection = new java.util.ArrayList<HarvestEvent>();
		if (harvestEventsCollection != null) {
			for (HarvestEvent item : harvestEventsCollection) {
				newharvestEventsCollection.add(em.getReference(HarvestEvent.class, item.getHarvestEventPK()));
			}
		}
		entity.setHarvestEventsCollection(newharvestEventsCollection);
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
		if (!(object instanceof HarvestLevelConverter)) {
			return false;
		}
		HarvestLevelConverter other = (HarvestLevelConverter) object;
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
