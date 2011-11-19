package converter;

import beans.OrganicMaterialLevel;
import beans.OrganicMaterialLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import beans.OrganicMaterialEvent;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "organicMaterialLevel")
public class OrganicMaterialLevelConverter {
	private OrganicMaterialLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of OrganicMaterialLevelConverter */
	public OrganicMaterialLevelConverter() {
		entity = new OrganicMaterialLevel();
	}

	/**
	 * Creates a new instance of OrganicMaterialLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public OrganicMaterialLevelConverter(OrganicMaterialLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getOrganicMaterialLevelPK().getExpId() + "," + entity.getOrganicMaterialLevelPK().getOm() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getOrganicMaterialEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of OrganicMaterialLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public OrganicMaterialLevelConverter(OrganicMaterialLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for organicMaterialLevelsPK.
	 *
	 * @return value for organicMaterialLevelsPK
	 */
	@XmlElement
	public OrganicMaterialLevelPK getOrganicMaterialLevelsPK() {
		return (expandLevel > 0) ? entity.getOrganicMaterialLevelPK() : null;
	}

	/**
	 * Setter for organicMaterialLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setOrganicMaterialLevelPK(OrganicMaterialLevelPK value) {
		entity.setOrganicMaterialLevelPK(value);
	}

	/**
	 * Getter for omName.
	 *
	 * @return value for omName
	 */
	@XmlElement
	public String getOmName() {
		return (expandLevel > 0) ? entity.getOmName() : null;
	}

	/**
	 * Setter for omName.
	 *
	 * @param value the value to set
	 */
	public void setOmName(String value) {
		entity.setOmName(value);
	}

	/**
	 * Getter for omNotes.
	 *
	 * @return value for omNotes
	 */
	@XmlElement
	public String getOmNotes() {
		return (expandLevel > 0) ? entity.getOmNotes() : null;
	}

	/**
	 * Setter for omNotes.
	 *
	 * @param value the value to set
	 */
	public void setOmNotes(String value) {
		entity.setOmNotes(value);
	}

	/**
	 * Getter for organicMaterialEventsCollection.
	 *
	 * @return value for organicMaterialEventsCollection
	 */
	@XmlElement
	public OrganicMaterialEventsConverter getOrganicMaterialEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getOrganicMaterialEventsCollection() != null) {
				return new OrganicMaterialEventsConverter(entity.getOrganicMaterialEventsCollection(), uri.resolve("organicMaterialEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for organicMaterialEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setOrganicMaterialEventsCollection(OrganicMaterialEventsConverter value) {
		entity.setOrganicMaterialEventsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the OrganicMaterialLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public OrganicMaterialLevel getEntity() {
		if (entity.getOrganicMaterialLevelPK() == null) {
			OrganicMaterialLevelConverter converter = UriResolver.getInstance().resolve(OrganicMaterialLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved OrganicMaterialLevels entity.
	 *
	 * @return an resolved entity
	 */
	public OrganicMaterialLevel resolveEntity(EntityManager em) {
		Collection<OrganicMaterialEvent> organicMaterialEventsCollection = entity.getOrganicMaterialEventsCollection();
		Collection<OrganicMaterialEvent> neworganicMaterialEventsCollection = new java.util.ArrayList<OrganicMaterialEvent>();
		if (organicMaterialEventsCollection != null) {
			for (OrganicMaterialEvent item : organicMaterialEventsCollection) {
				neworganicMaterialEventsCollection.add(em.getReference(OrganicMaterialEvent.class, item.getOrganicMaterialEventPK()));
			}
		}
		entity.setOrganicMaterialEventsCollection(neworganicMaterialEventsCollection);
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
		if (!(object instanceof OrganicMaterialLevelConverter)) {
			return false;
		}
		OrganicMaterialLevelConverter other = (OrganicMaterialLevelConverter) object;
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
