package converter;

import beans.FertilizerEvent;
import beans.FertilizerEventPK;
import beans.FertilizerLevel;
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
@XmlRootElement(name = "fertilizerEvent")
public class FertilizerEventConverter {
	private FertilizerEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of FertilizerEventConverter */
	public FertilizerEventConverter() {
		entity = new FertilizerEvent();
	}

	/**
	 * Creates a new instance of FertilizerEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public FertilizerEventConverter(FertilizerEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getFertilizerEventPK().getExpId() + "," + entity.getFertilizerEventPK().getFe() + "," + entity.getFertilizerEventPK().getFeApplNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getFertilizerLevel();
	}

	/**
	 * Creates a new instance of FertilizerEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public FertilizerEventConverter(FertilizerEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for fertilizerEventsPK.
	 *
	 * @return value for fertilizerEventsPK
	 */
	@XmlElement
	public FertilizerEventPK getFertilizerEventPK() {
		return (expandLevel > 0) ? entity.getFertilizerEventPK() : null;
	}

	/**
	 * Setter for fertilizerEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setFertilizerEventPK(FertilizerEventPK value) {
		entity.setFertilizerEventPK(value);
	}

	/**
	 * Getter for fdate.
	 *
	 * @return value for fdate
	 */
	@XmlElement
	public Date getFdate() {
		return (expandLevel > 0) ? entity.getFdate() : null;
	}

	/**
	 * Setter for fdate.
	 *
	 * @param value the value to set
	 */
	public void setFdate(Date value) {
		entity.setFdate(value);
	}

	/**
	 * Getter for fecd.
	 *
	 * @return value for fecd
	 */
	@XmlElement
	public String getFecd() {
		return (expandLevel > 0) ? entity.getFecd() : null;
	}

	/**
	 * Setter for fecd.
	 *
	 * @param value the value to set
	 */
	public void setFecd(String value) {
		entity.setFecd(value);
	}

	/**
	 * Getter for feacd.
	 *
	 * @return value for feacd
	 */
	@XmlElement
	public String getFeacd() {
		return (expandLevel > 0) ? entity.getFeacd() : null;
	}

	/**
	 * Setter for feacd.
	 *
	 * @param value the value to set
	 */
	public void setFeacd(String value) {
		entity.setFeacd(value);
	}

	/**
	 * Getter for fedep.
	 *
	 * @return value for fedep
	 */
	@XmlElement
	public Float getFedep() {
		return (expandLevel > 0) ? entity.getFedep() : null;
	}

	/**
	 * Setter for fedep.
	 *
	 * @param value the value to set
	 */
	public void setFedep(Float value) {
		entity.setFedep(value);
	}

	/**
	 * Getter for feamn.
	 *
	 * @return value for feamn
	 */
	@XmlElement
	public Float getFeamn() {
		return (expandLevel > 0) ? entity.getFeamn() : null;
	}

	/**
	 * Setter for feamn.
	 *
	 * @param value the value to set
	 */
	public void setFeamn(Float value) {
		entity.setFeamn(value);
	}

	/**
	 * Getter for feamp.
	 *
	 * @return value for feamp
	 */
	@XmlElement
	public Float getFeamp() {
		return (expandLevel > 0) ? entity.getFeamp() : null;
	}

	/**
	 * Setter for feamp.
	 *
	 * @param value the value to set
	 */
	public void setFeamp(Float value) {
		entity.setFeamp(value);
	}

	/**
	 * Getter for feamk.
	 *
	 * @return value for feamk
	 */
	@XmlElement
	public Float getFeamk() {
		return (expandLevel > 0) ? entity.getFeamk() : null;
	}

	/**
	 * Setter for feamk.
	 *
	 * @param value the value to set
	 */
	public void setFeamk(Float value) {
		entity.setFeamk(value);
	}

	/**
	 * Getter for feamc.
	 *
	 * @return value for feamc
	 */
	@XmlElement
	public Float getFeamc() {
		return (expandLevel > 0) ? entity.getFeamc() : null;
	}

	/**
	 * Setter for feamc.
	 *
	 * @param value the value to set
	 */
	public void setFeamc(Float value) {
		entity.setFeamc(value);
	}

	/**
	 * Getter for feamo.
	 *
	 * @return value for feamo
	 */
	@XmlElement
	public Float getFeamo() {
		return (expandLevel > 0) ? entity.getFeamo() : null;
	}

	/**
	 * Setter for feamo.
	 *
	 * @param value the value to set
	 */
	public void setFeamo(Float value) {
		entity.setFeamo(value);
	}

	/**
	 * Getter for feocd.
	 *
	 * @return value for feocd
	 */
	@XmlElement
	public String getFeocd() {
		return (expandLevel > 0) ? entity.getFeocd() : null;
	}

	/**
	 * Setter for feocd.
	 *
	 * @param value the value to set
	 */
	public void setFeocd(String value) {
		entity.setFeocd(value);
	}

	/**
	 * Getter for focd.
	 *
	 * @return value for focd
	 */
	@XmlElement
	public String getFocd() {
		return (expandLevel > 0) ? entity.getFocd() : null;
	}

	/**
	 * Setter for focd.
	 *
	 * @param value the value to set
	 */
	public void setFocd(String value) {
		entity.setFocd(value);
	}

	/**
	 * Getter for fertilizerLevels.
	 *
	 * @return value for fertilizerLevels
	 */
	@XmlElement
	public FertilizerLevelConverter getFertilizerLevel() {
		if (expandLevel > 0) {
			if (entity.getFertilizerLevel() != null) {
				return new FertilizerLevelConverter(entity.getFertilizerLevel(), uri.resolve("fertilizerLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for fertilizerLevels.
	 *
	 * @param value the value to set
	 */
	public void setFertilizerLevel(FertilizerLevelConverter value) {
		entity.setFertilizerLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the FertilizerEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public FertilizerEvent getEntity() {
		if (entity.getFertilizerEventPK() == null) {
			FertilizerEventConverter converter = UriResolver.getInstance().resolve(FertilizerEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved FertilizerEvents entity.
	 *
	 * @return an resolved entity
	 */
	public FertilizerEvent resolveEntity(EntityManager em) {
		FertilizerLevel fertilizerLevels = entity.getFertilizerLevel();
		if (fertilizerLevels != null) {
			entity.setFertilizerLevel(em.getReference(FertilizerLevel.class, fertilizerLevels.getFertilizerLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FertilizerEventConverter)) {
			return false;
		}
		FertilizerEventConverter other = (FertilizerEventConverter) object;
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
