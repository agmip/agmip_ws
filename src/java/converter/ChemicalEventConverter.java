package converter;

import beans.ChemicalEvent;
import beans.ChemicalEventPK;
import beans.ChemicalLevel;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "chemicalEvent")
public class ChemicalEventConverter {
	private ChemicalEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of ChemicalEventConverter */
	public ChemicalEventConverter() {
		entity = new ChemicalEvent();
	}

	/**
	 * Creates a new instance of ChemicalEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public ChemicalEventConverter(ChemicalEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getChemicalEventPK().getExpId() + "," + entity.getChemicalEventPK().getCh() + "," + entity.getChemicalEventPK().getChApplNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getChemicalLevel();
	}

	/**
	 * Creates a new instance of ChemicalEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public ChemicalEventConverter(ChemicalEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for chemicalEventsPK.
	 *
	 * @return value for chemicalEventsPK
	 */
	@XmlElement
	public ChemicalEventPK getChemicalEventPK() {
		return (expandLevel > 0) ? entity.getChemicalEventPK() : null;
	}

	/**
	 * Setter for chemicalEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setChemicalEventPK(ChemicalEventPK value) {
		entity.setChemicalEventPK(value);
	}

	/**
	 * Getter for cdate.
	 *
	 * @return value for cdate
	 */
	@XmlElement
	public Date getCdate() {
		return (expandLevel > 0) ? entity.getCdate() : null;
	}

	/**
	 * Setter for cdate.
	 *
	 * @param value the value to set
	 */
	public void setCdate(Date value) {
		entity.setCdate(value);
	}

	/**
	 * Getter for chcd.
	 *
	 * @return value for chcd
	 */
	@XmlElement
	public String getChcd() {
		return (expandLevel > 0) ? entity.getChcd() : null;
	}

	/**
	 * Setter for chcd.
	 *
	 * @param value the value to set
	 */
	public void setChcd(String value) {
		entity.setChcd(value);
	}

	/**
	 * Getter for chacd.
	 *
	 * @return value for chacd
	 */
	@XmlElement
	public String getChacd() {
		return (expandLevel > 0) ? entity.getChacd() : null;
	}

	/**
	 * Setter for chacd.
	 *
	 * @param value the value to set
	 */
	public void setChacd(String value) {
		entity.setChacd(value);
	}

	/**
	 * Getter for chdep.
	 *
	 * @return value for chdep
	 */
	@XmlElement
	public Float getChdep() {
		return (expandLevel > 0) ? entity.getChdep() : null;
	}

	/**
	 * Setter for chdep.
	 *
	 * @param value the value to set
	 */
	public void setChdep(Float value) {
		entity.setChdep(value);
	}

	/**
	 * Getter for chamt.
	 *
	 * @return value for chamt
	 */
	@XmlElement
	public Float getChamt() {
		return (expandLevel > 0) ? entity.getChamt() : null;
	}

	/**
	 * Setter for chamt.
	 *
	 * @param value the value to set
	 */
	public void setChamt(Float value) {
		entity.setChamt(value);
	}

	/**
	 * Getter for chTargets.
	 *
	 * @return value for chTargets
	 */
	@XmlElement
	public String getChTargets() {
		return (expandLevel > 0) ? entity.getChTargets() : null;
	}

	/**
	 * Setter for chTargets.
	 *
	 * @param value the value to set
	 */
	public void setChTargets(String value) {
		entity.setChTargets(value);
	}

	/**
	 * Getter for chemicalLevels.
	 *
	 * @return value for chemicalLevels
	 */
	@XmlElement
	public ChemicalLevelConverter getChemicalLevel() {
		if (expandLevel > 0) {
			if (entity.getChemicalLevel() != null) {
				return new ChemicalLevelConverter(entity.getChemicalLevel(), uri.resolve("chemicalLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for chemicalLevels.
	 *
	 * @param value the value to set
	 */
	public void setChemicalLevel(ChemicalLevelConverter value) {
		entity.setChemicalLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the ChemicalEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public ChemicalEvent getEntity() {
		if (entity.getChemicalEventPK() == null) {
			ChemicalEventConverter converter = UriResolver.getInstance().resolve(ChemicalEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved ChemicalEvents entity.
	 *
	 * @return an resolved entity
	 */
	public ChemicalEvent resolveEntity(EntityManager em) {
		ChemicalLevel chemicalLevels = entity.getChemicalLevel();
		if (chemicalLevels != null) {
			entity.setChemicalLevel(em.getReference(ChemicalLevel.class, chemicalLevels.getChemicalLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ChemicalEventConverter)) {
			return false;
		}
		ChemicalEventConverter other = (ChemicalEventConverter) object;
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
