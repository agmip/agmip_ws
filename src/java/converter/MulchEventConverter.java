package converter;

import beans.MulchEvent;
import beans.MulchEventPK;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.MulchLevel;
import beans.MulchLevelPK;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "mulchEvent")
public class MulchEventConverter {
	private MulchEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of MulchEventConverter */
	public MulchEventConverter() {
		entity = new MulchEvent();
	}

	/**
	 * Creates a new instance of MulchEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public MulchEventConverter(MulchEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getMulchEventPK().getExpId() + "," + entity.getMulchEventPK().getMl() + "," + entity.getMulchEventPK().getMlApplNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getMulchLevel();
	}

	/**
	 * Creates a new instance of MulchEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public MulchEventConverter(MulchEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for mulchEventsPK.
	 *
	 * @return value for mulchEventsPK
	 */
	@XmlElement
	public MulchEventPK getMulchEventPK() {
		return (expandLevel > 0) ? entity.getMulchEventPK() : null;
	}

	/**
	 * Setter for mulchEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setMulchEventPK(MulchEventPK value) {
		entity.setMulchEventPK(value);
	}

	/**
	 * Getter for mldat.
	 *
	 * @return value for mldat
	 */
	@XmlElement
	public Date getMldat() {
		return (expandLevel > 0) ? entity.getMldat() : null;
	}

	/**
	 * Setter for mldat.
	 *
	 * @param value the value to set
	 */
	public void setMldat(Date value) {
		entity.setMldat(value);
	}

	/**
	 * Getter for mlrdat.
	 *
	 * @return value for mlrdat
	 */
	@XmlElement
	public Date getMlrdat() {
		return (expandLevel > 0) ? entity.getMlrdat() : null;
	}

	/**
	 * Setter for mlrdat.
	 *
	 * @param value the value to set
	 */
	public void setMlrdat(Date value) {
		entity.setMlrdat(value);
	}

	/**
	 * Getter for mltp.
	 *
	 * @return value for mltp
	 */
	@XmlElement
	public String getMltp() {
		return (expandLevel > 0) ? entity.getMltp() : null;
	}

	/**
	 * Setter for mltp.
	 *
	 * @param value the value to set
	 */
	public void setMltp(String value) {
		entity.setMltp(value);
	}

	/**
	 * Getter for mlthk.
	 *
	 * @return value for mlthk
	 */
	@XmlElement
	public Float getMlthk() {
		return (expandLevel > 0) ? entity.getMlthk() : null;
	}

	/**
	 * Setter for mlthk.
	 *
	 * @param value the value to set
	 */
	public void setMlthk(Float value) {
		entity.setMlthk(value);
	}

	/**
	 * Getter for mlcol.
	 *
	 * @return value for mlcol
	 */
	@XmlElement
	public String getMlcol() {
		return (expandLevel > 0) ? entity.getMlcol() : null;
	}

	/**
	 * Setter for mlcol.
	 *
	 * @param value the value to set
	 */
	public void setMlcol(String value) {
		entity.setMlcol(value);
	}

	/**
	 * Getter for mulchLevels.
	 *
	 * @return value for mulchLevels
	 */
	@XmlElement
	public MulchLevelConverter getMulchLevel() {
		if (expandLevel > 0) {
			if (entity.getMulchLevel() != null) {
				return new MulchLevelConverter(entity.getMulchLevel(), uri.resolve("mulchLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for mulchLevels.
	 *
	 * @param value the value to set
	 */
	public void setMulchLevel(MulchLevelConverter value) {
		entity.setMulchLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the MulchEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public MulchEvent getEntity() {
		if (entity.getMulchEventPK() == null) {
			MulchEventConverter converter = UriResolver.getInstance().resolve(MulchEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved MulchEvents entity.
	 *
	 * @return an resolved entity
	 */
	public MulchEvent resolveEntity(EntityManager em) {
		MulchLevel mulchLevels = entity.getMulchLevel();
		if (mulchLevels != null) {
			entity.setMulchLevel(em.getReference(MulchLevel.class, mulchLevels.getMulchLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MulchEventConverter)) {
			return false;
		}
		MulchEventConverter other = (MulchEventConverter) object;
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
