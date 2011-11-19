package converter;

import beans.EnvironModifLevel;
import beans.EnvironModifLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import beans.EnvironModifEvent;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "environModifLevel")
public class EnvironModifLevelConverter {
	private EnvironModifLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of EnvironModifLevelConverter */
	public EnvironModifLevelConverter() {
		entity = new EnvironModifLevel();
	}

	/**
	 * Creates a new instance of EnvironModifLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public EnvironModifLevelConverter(EnvironModifLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getEnvironModifLevelPK().getExpId() + "," + entity.getEnvironModifLevelPK().getEm() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getEnvironModifEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of EnvironModifLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public EnvironModifLevelConverter(EnvironModifLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for environModifLevelsPK.
	 *
	 * @return value for environModifLevelsPK
	 */
	@XmlElement
	public EnvironModifLevelPK getEnvironModifLevelPK() {
		return (expandLevel > 0) ? entity.getEnvironModifLevelPK() : null;
	}

	/**
	 * Setter for environModifLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setEnvironModifLevelPK(EnvironModifLevelPK value) {
		entity.setEnvironModifLevelPK(value);
	}

	/**
	 * Getter for emName.
	 *
	 * @return value for emName
	 */
	@XmlElement
	public String getEmName() {
		return (expandLevel > 0) ? entity.getEmName() : null;
	}

	/**
	 * Setter for emName.
	 *
	 * @param value the value to set
	 */
	public void setEmName(String value) {
		entity.setEmName(value);
	}

	/**
	 * Getter for emNotes.
	 *
	 * @return value for emNotes
	 */
	@XmlElement
	public String getEmNotes() {
		return (expandLevel > 0) ? entity.getEmNotes() : null;
	}

	/**
	 * Setter for emNotes.
	 *
	 * @param value the value to set
	 */
	public void setEmNotes(String value) {
		entity.setEmNotes(value);
	}

	/**
	 * Getter for environModifEventsCollection.
	 *
	 * @return value for environModifEventsCollection
	 */
	@XmlElement
	public EnvironModifEventsConverter getEnvironModifEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getEnvironModifEventsCollection() != null) {
				return new EnvironModifEventsConverter(entity.getEnvironModifEventsCollection(), uri.resolve("environModifEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for environModifEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setEnvironModifEventsCollection(EnvironModifEventsConverter value) {
		entity.setEnvironModifEventsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the EnvironModifLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public EnvironModifLevel getEntity() {
		if (entity.getEnvironModifLevelPK() == null) {
			EnvironModifLevelConverter converter = UriResolver.getInstance().resolve(EnvironModifLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved EnvironModifLevels entity.
	 *
	 * @return an resolved entity
	 */
	public EnvironModifLevel resolveEntity(EntityManager em) {
		Collection<EnvironModifEvent> environModifEventsCollection = entity.getEnvironModifEventsCollection();
		Collection<EnvironModifEvent> newenvironModifEventsCollection = new java.util.ArrayList<EnvironModifEvent>();
		if (environModifEventsCollection != null) {
			for (EnvironModifEvent item : environModifEventsCollection) {
				newenvironModifEventsCollection.add(em.getReference(EnvironModifEvent.class, item.getEnvironModifEventPK()));
			}
		}
		entity.setEnvironModifEventsCollection(newenvironModifEventsCollection);
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
		if (!(object instanceof EnvironModifLevelConverter)) {
			return false;
		}
		EnvironModifLevelConverter other = (EnvironModifLevelConverter) object;
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
