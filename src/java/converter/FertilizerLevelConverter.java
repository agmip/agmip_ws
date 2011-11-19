package converter;

import beans.FertilizerLevel;
import beans.FertilizerLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.FertilizerEvent;
import beans.Treatment;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "fertilizerLevel")
public class FertilizerLevelConverter {
	private FertilizerLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of FertilizerLevelConverter */
	public FertilizerLevelConverter() {
		entity = new FertilizerLevel();
	}

	/**
	 * Creates a new instance of FertilizerLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public FertilizerLevelConverter(FertilizerLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getFertilizerLevelPK().getExpId() + "," + entity.getFertilizerLevelPK().getFe() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getFertilizerEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of FertilizerLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public FertilizerLevelConverter(FertilizerLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for fertilizerLevelsPK.
	 *
	 * @return value for fertilizerLevelsPK
	 */
	@XmlElement
	public FertilizerLevelPK getFertilizerLevelPK() {
		return (expandLevel > 0) ? entity.getFertilizerLevelPK() : null;
	}

	/**
	 * Setter for fertilizerLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setFertilizerLevelPK(FertilizerLevelPK value) {
		entity.setFertilizerLevelPK(value);
	}

	/**
	 * Getter for feName.
	 *
	 * @return value for feName
	 */
	@XmlElement
	public String getFeName() {
		return (expandLevel > 0) ? entity.getFeName() : null;
	}

	/**
	 * Setter for feName.
	 *
	 * @param value the value to set
	 */
	public void setFeName(String value) {
		entity.setFeName(value);
	}

	/**
	 * Getter for feComments.
	 *
	 * @return value for feComments
	 */
	@XmlElement
	public String getFeComments() {
		return (expandLevel > 0) ? entity.getFeComments() : null;
	}

	/**
	 * Setter for feComments.
	 *
	 * @param value the value to set
	 */
	public void setFeComments(String value) {
		entity.setFeComments(value);
	}

	/**
	 * Getter for fertilizerEventsCollection.
	 *
	 * @return value for fertilizerEventsCollection
	 */
	@XmlElement
	public FertilizerEventsConverter getFertilizerEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getFertilizerEventsCollection() != null) {
				return new FertilizerEventsConverter(entity.getFertilizerEventsCollection(), uri.resolve("fertilizerEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for fertilizerEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setFertilizerEventsCollection(FertilizerEventsConverter value) {
		entity.setFertilizerEventsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the FertilizerLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public FertilizerLevel getEntity() {
		if (entity.getFertilizerLevelPK() == null) {
			FertilizerLevelConverter converter = UriResolver.getInstance().resolve(FertilizerLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved FertilizerLevels entity.
	 *
	 * @return an resolved entity
	 */
	public FertilizerLevel resolveEntity(EntityManager em) {
		Collection<FertilizerEvent> fertilizerEventsCollection = entity.getFertilizerEventsCollection();
		Collection<FertilizerEvent> newfertilizerEventsCollection = new java.util.ArrayList<FertilizerEvent>();
		if (fertilizerEventsCollection != null) {
			for (FertilizerEvent item : fertilizerEventsCollection) {
				newfertilizerEventsCollection.add(em.getReference(FertilizerEvent.class, item.getFertilizerEventPK()));
			}
		}
		entity.setFertilizerEventsCollection(newfertilizerEventsCollection);
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
		if (!(object instanceof FertilizerLevelConverter)) {
			return false;
		}
		FertilizerLevelConverter other = (FertilizerLevelConverter) object;
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
