package converter;

import beans.MulchLevel;
import beans.MulchLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import java.util.Collection;
import beans.MulchEvent;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "mulchLevel")
public class MulchLevelConverter {
	private MulchLevel entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of MulchLevelConverter */
	public MulchLevelConverter() {
		entity = new MulchLevel();
	}

	/**
	 * Creates a new instance of MulchLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public MulchLevelConverter(MulchLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getMulchLevelPK().getExpId() + "," + entity.getMulchLevelPK().getMl() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getMulchEventsCollection();
		getTreatmentsCollection();
	}

	/**
	 * Creates a new instance of MulchLevelConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public MulchLevelConverter(MulchLevel entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for mulchLevelsPK.
	 *
	 * @return value for mulchLevelsPK
	 */
	@XmlElement
	public MulchLevelPK getMulchLevelPK() {
		return (expandLevel > 0) ? entity.getMulchLevelPK() : null;
	}

	/**
	 * Setter for mulchLevelsPK.
	 *
	 * @param value the value to set
	 */
	public void setMulchLevelPK(MulchLevelPK value) {
		entity.setMulchLevelPK(value);
	}

	/**
	 * Getter for mlName.
	 *
	 * @return value for mlName
	 */
	@XmlElement
	public String getMlName() {
		return (expandLevel > 0) ? entity.getMlName() : null;
	}

	/**
	 * Setter for mlName.
	 *
	 * @param value the value to set
	 */
	public void setMlName(String value) {
		entity.setMlName(value);
	}

	/**
	 * Getter for mlNotes.
	 *
	 * @return value for mlNotes
	 */
	@XmlElement
	public String getMlNotes() {
		return (expandLevel > 0) ? entity.getMlNotes() : null;
	}

	/**
	 * Setter for mlNotes.
	 *
	 * @param value the value to set
	 */
	public void setMlNotes(String value) {
		entity.setMlNotes(value);
	}

	/**
	 * Getter for mulchEventsCollection.
	 *
	 * @return value for mulchEventsCollection
	 */
	@XmlElement
	public MulchEventsConverter getMulchEventsCollection() {
		if (expandLevel > 0) {
			if (entity.getMulchEventsCollection() != null) {
				return new MulchEventsConverter(entity.getMulchEventsCollection(), uri.resolve("mulchEventsCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for mulchEventsCollection.
	 *
	 * @param value the value to set
	 */
	public void setMulchEventsCollection(MulchEventsConverter value) {
		entity.setMulchEventsCollection((value != null) ? value.getEntities() : null);
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
	 * Returns the MulchLevels entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public MulchLevel getEntity() {
		if (entity.getMulchLevelPK() == null) {
			MulchLevelConverter converter = UriResolver.getInstance().resolve(MulchLevelConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved MulchLevels entity.
	 *
	 * @return an resolved entity
	 */
	public MulchLevel resolveEntity(EntityManager em) {
		Collection<MulchEvent> mulchEventsCollection = entity.getMulchEventsCollection();
		Collection<MulchEvent> newmulchEventsCollection = new java.util.ArrayList<MulchEvent>();
		if (mulchEventsCollection != null) {
			for (MulchEvent item : mulchEventsCollection) {
				newmulchEventsCollection.add(em.getReference(MulchEvent.class, item.getMulchEventPK()));
			}
		}
		entity.setMulchEventsCollection(newmulchEventsCollection);
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
		if (!(object instanceof MulchLevelConverter)) {
			return false;
		}
		MulchLevelConverter other = (MulchLevelConverter) object;
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
