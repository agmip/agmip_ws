package converter;

import beans.SoilAnalysesEvent;
import beans.SoilAnalysesEventPK;
import beans.SoilAnalysesLevel;
import java.net.URI;
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
@XmlRootElement(name = "soilAnalysesEvent")
public class SoilAnalysesEventConverter {
	private SoilAnalysesEvent entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilAnalysesEventConverter */
	public SoilAnalysesEventConverter() {
		entity = new SoilAnalysesEvent();
	}

	/**
	 * Creates a new instance of SoilAnalysesEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public SoilAnalysesEventConverter(SoilAnalysesEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getSoilAnalysesEventPK().getExpId() + "," + entity.getSoilAnalysesEventPK().getSa() + "," + entity.getSoilAnalysesEventPK().getSaApplNo() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getSoilAnalysesLevel();
	}

	/**
	 * Creates a new instance of SoilAnalysesEventConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilAnalysesEventConverter(SoilAnalysesEvent entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for soilAnalysesEventsPK.
	 *
	 * @return value for soilAnalysesEventsPK
	 */
	@XmlElement
	public SoilAnalysesEventPK getSoilAnalysesEventPK() {
		return (expandLevel > 0) ? entity.getSoilAnalysesEventPK() : null;
	}

	/**
	 * Setter for soilAnalysesEventsPK.
	 *
	 * @param value the value to set
	 */
	public void setSoilAnalysesEventPK(SoilAnalysesEventPK value) {
		entity.setSoilAnalysesEventPK(value);
	}

	/**
	 * Getter for satl.
	 *
	 * @return value for satl
	 */
	@XmlElement
	public Float getSatl() {
		return (expandLevel > 0) ? entity.getSatl() : null;
	}

	/**
	 * Setter for satl.
	 *
	 * @param value the value to set
	 */
	public void setSatl(Float value) {
		entity.setSatl(value);
	}

	/**
	 * Getter for sabl.
	 *
	 * @return value for sabl
	 */
	@XmlElement
	public Integer getSabl() {
		return (expandLevel > 0) ? entity.getSabl() : null;
	}

	/**
	 * Setter for sabl.
	 *
	 * @param value the value to set
	 */
	public void setSabl(Integer value) {
		entity.setSabl(value);
	}

	/**
	 * Getter for sabdm.
	 *
	 * @return value for sabdm
	 */
	@XmlElement
	public Float getSabdm() {
		return (expandLevel > 0) ? entity.getSabdm() : null;
	}

	/**
	 * Setter for sabdm.
	 *
	 * @param value the value to set
	 */
	public void setSabdm(Float value) {
		entity.setSabdm(value);
	}

	/**
	 * Getter for saoc.
	 *
	 * @return value for saoc
	 */
	@XmlElement
	public Float getSaoc() {
		return (expandLevel > 0) ? entity.getSaoc() : null;
	}

	/**
	 * Setter for saoc.
	 *
	 * @param value the value to set
	 */
	public void setSaoc(Float value) {
		entity.setSaoc(value);
	}

	/**
	 * Getter for sani.
	 *
	 * @return value for sani
	 */
	@XmlElement
	public Float getSani() {
		return (expandLevel > 0) ? entity.getSani() : null;
	}

	/**
	 * Setter for sani.
	 *
	 * @param value the value to set
	 */
	public void setSani(Float value) {
		entity.setSani(value);
	}

	/**
	 * Getter for saphw.
	 *
	 * @return value for saphw
	 */
	@XmlElement
	public Float getSaphw() {
		return (expandLevel > 0) ? entity.getSaphw() : null;
	}

	/**
	 * Setter for saphw.
	 *
	 * @param value the value to set
	 */
	public void setSaphw(Float value) {
		entity.setSaphw(value);
	}

	/**
	 * Getter for saphb.
	 *
	 * @return value for saphb
	 */
	@XmlElement
	public Float getSaphb() {
		return (expandLevel > 0) ? entity.getSaphb() : null;
	}

	/**
	 * Setter for saphb.
	 *
	 * @param value the value to set
	 */
	public void setSaphb(Float value) {
		entity.setSaphb(value);
	}

	/**
	 * Getter for sapx.
	 *
	 * @return value for sapx
	 */
	@XmlElement
	public Float getSapx() {
		return (expandLevel > 0) ? entity.getSapx() : null;
	}

	/**
	 * Setter for sapx.
	 *
	 * @param value the value to set
	 */
	public void setSapx(Float value) {
		entity.setSapx(value);
	}

	/**
	 * Getter for sake.
	 *
	 * @return value for sake
	 */
	@XmlElement
	public Float getSake() {
		return (expandLevel > 0) ? entity.getSake() : null;
	}

	/**
	 * Setter for sake.
	 *
	 * @param value the value to set
	 */
	public void setSake(Float value) {
		entity.setSake(value);
	}

	/**
	 * Getter for sacmf.
	 *
	 * @return value for sacmf
	 */
	@XmlElement
	public Float getSacmf() {
		return (expandLevel > 0) ? entity.getSacmf() : null;
	}

	/**
	 * Setter for sacmf.
	 *
	 * @param value the value to set
	 */
	public void setSacmf(Float value) {
		entity.setSacmf(value);
	}

	/**
	 * Getter for slphb.
	 *
	 * @return value for slphb
	 */
	@XmlElement
	public Float getSlphb() {
		return (expandLevel > 0) ? entity.getSlphb() : null;
	}

	/**
	 * Setter for slphb.
	 *
	 * @param value the value to set
	 */
	public void setSlphb(Float value) {
		entity.setSlphb(value);
	}

	/**
	 * Getter for sasc.
	 *
	 * @return value for sasc
	 */
	@XmlElement
	public Float getSasc() {
		return (expandLevel > 0) ? entity.getSasc() : null;
	}

	/**
	 * Setter for sasc.
	 *
	 * @param value the value to set
	 */
	public void setSasc(Float value) {
		entity.setSasc(value);
	}

	/**
	 * Getter for soilAnalysesLevels.
	 *
	 * @return value for soilAnalysesLevels
	 */
	@XmlElement
	public SoilAnalysesLevelConverter getSoilAnalysesLevel() {
		if (expandLevel > 0) {
			if (entity.getSoilAnalysesLevel() != null) {
				return new SoilAnalysesLevelConverter(entity.getSoilAnalysesLevel(), uri.resolve("soilAnalysesLevel/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for soilAnalysesLevels.
	 *
	 * @param value the value to set
	 */
	public void setSoilAnalysesLevel(SoilAnalysesLevelConverter value) {
		entity.setSoilAnalysesLevel((value != null) ? value.getEntity() : null);
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
	 * Returns the SoilAnalysesEvents entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public SoilAnalysesEvent getEntity() {
		if (entity.getSoilAnalysesEventPK() == null) {
			SoilAnalysesEventConverter converter = UriResolver.getInstance().resolve(SoilAnalysesEventConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved SoilAnalysesEvents entity.
	 *
	 * @return an resolved entity
	 */
	public SoilAnalysesEvent resolveEntity(EntityManager em) {
		SoilAnalysesLevel soilAnalysesLevel = entity.getSoilAnalysesLevel();
		if (soilAnalysesLevel != null) {
			entity.setSoilAnalysesLevel(em.getReference(SoilAnalysesLevel.class, soilAnalysesLevel.getSoilAnalysesLevelPK()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilAnalysesEventConverter)) {
			return false;
		}
		SoilAnalysesEventConverter other = (SoilAnalysesEventConverter) object;
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
