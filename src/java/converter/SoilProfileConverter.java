package converter;

import beans.SoilProfile;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.SoilProfileLayer;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "soilProfile")
public class SoilProfileConverter {
	private SoilProfile entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilProfileConverter */
	public SoilProfileConverter() {
		entity = new SoilProfile();
	}

	/**
	 * Creates a new instance of SoilProfileConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public SoilProfileConverter(SoilProfile entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getSid() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getSoilProfileLayerCollection();
	}

	/**
	 * Creates a new instance of SoilProfileConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilProfileConverter(SoilProfile entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for sid.
	 *
	 * @return value for sid
	 */
	@XmlElement
	public Integer getSid() {
		return (expandLevel > 0) ? entity.getSid() : null;
	}

	/**
	 * Setter for sid.
	 *
	 * @param value the value to set
	 */
	public void setSid(Integer value) {
		entity.setSid(value);
	}

	/**
	 * Getter for people.
	 *
	 * @return value for people
	 */
	@XmlElement
	public String getPeople() {
		return (expandLevel > 0) ? entity.getPeople() : null;
	}

	/**
	 * Setter for people.
	 *
	 * @param value the value to set
	 */
	public void setPeople(String value) {
		entity.setPeople(value);
	}

	/**
	 * Getter for soilId.
	 *
	 * @return value for soilId
	 */
	@XmlElement
	public String getSoilId() {
		return (expandLevel > 0) ? entity.getSoilId() : null;
	}

	/**
	 * Setter for soilId.
	 *
	 * @param value the value to set
	 */
	public void setSoilId(String value) {
		entity.setSoilId(value);
	}

	/**
	 * Getter for institutes.
	 *
	 * @return value for institutes
	 */
	@XmlElement
	public String getInstitutes() {
		return (expandLevel > 0) ? entity.getInstitutes() : null;
	}

	/**
	 * Setter for institutes.
	 *
	 * @param value the value to set
	 */
	public void setInstitutes(String value) {
		entity.setInstitutes(value);
	}

	/**
	 * Getter for soilFileId.
	 *
	 * @return value for soilFileId
	 */
	@XmlElement
	public String getSoilFileId() {
		return (expandLevel > 0) ? entity.getSoilFileId() : null;
	}

	/**
	 * Setter for soilFileId.
	 *
	 * @param value the value to set
	 */
	public void setSoilFileId(String value) {
		entity.setSoilFileId(value);
	}

	/**
	 * Getter for contacts.
	 *
	 * @return value for contacts
	 */
	@XmlElement
	public String getContacts() {
		return (expandLevel > 0) ? entity.getContacts() : null;
	}

	/**
	 * Setter for contacts.
	 *
	 * @param value the value to set
	 */
	public void setContacts(String value) {
		entity.setContacts(value);
	}

	/**
	 * Getter for fdName.
	 *
	 * @return value for fdName
	 */
	@XmlElement
	public String getFdName() {
		return (expandLevel > 0) ? entity.getFdName() : null;
	}

	/**
	 * Setter for fdName.
	 *
	 * @param value the value to set
	 */
	public void setFdName(String value) {
		entity.setFdName(value);
	}

	/**
	 * Getter for address.
	 *
	 * @return value for address
	 */
	@XmlElement
	public String getAddress() {
		return (expandLevel > 0) ? entity.getAddress() : null;
	}

	/**
	 * Setter for address.
	 *
	 * @param value the value to set
	 */
	public void setAddress(String value) {
		entity.setAddress(value);
	}

	/**
	 * Getter for site.
	 *
	 * @return value for site
	 */
	@XmlElement
	public String getSite() {
		return (expandLevel > 0) ? entity.getSite() : null;
	}

	/**
	 * Setter for site.
	 *
	 * @param value the value to set
	 */
	public void setSite(String value) {
		entity.setSite(value);
	}

	/**
	 * Getter for soillat.
	 *
	 * @return value for soillat
	 */
	@XmlElement
	public Float getSoillat() {
		return (expandLevel > 0) ? entity.getSoillat() : null;
	}

	/**
	 * Setter for soillat.
	 *
	 * @param value the value to set
	 */
	public void setSoillat(Float value) {
		entity.setSoillat(value);
	}

	/**
	 * Getter for publications.
	 *
	 * @return value for publications
	 */
	@XmlElement
	public String getPublications() {
		return (expandLevel > 0) ? entity.getPublications() : null;
	}

	/**
	 * Setter for publications.
	 *
	 * @param value the value to set
	 */
	public void setPublications(String value) {
		entity.setPublications(value);
	}

	/**
	 * Getter for soillong.
	 *
	 * @return value for soillong
	 */
	@XmlElement
	public Float getSoillong() {
		return (expandLevel > 0) ? entity.getSoillong() : null;
	}

	/**
	 * Setter for soillong.
	 *
	 * @param value the value to set
	 */
	public void setSoillong(Float value) {
		entity.setSoillong(value);
	}

	/**
	 * Getter for distribution.
	 *
	 * @return value for distribution
	 */
	@XmlElement
	public String getDistribution() {
		return (expandLevel > 0) ? entity.getDistribution() : null;
	}

	/**
	 * Setter for distribution.
	 *
	 * @param value the value to set
	 */
	public void setDistribution(String value) {
		entity.setDistribution(value);
	}

	/**
	 * Getter for sand.
	 *
	 * @return value for sand
	 */
	@XmlElement
	public Float getSand() {
		return (expandLevel > 0) ? entity.getSand() : null;
	}

	/**
	 * Setter for sand.
	 *
	 * @param value the value to set
	 */
	public void setSand(Float value) {
		entity.setSand(value);
	}

	/**
	 * Getter for soilelev.
	 *
	 * @return value for soilelev
	 */
	@XmlElement
	public Float getSoilelev() {
		return (expandLevel > 0) ? entity.getSoilelev() : null;
	}

	/**
	 * Setter for soilelev.
	 *
	 * @param value the value to set
	 */
	public void setSoilelev(Float value) {
		entity.setSoilelev(value);
	}

	/**
	 * Getter for slope.
	 *
	 * @return value for slope
	 */
	@XmlElement
	public Float getSlope() {
		return (expandLevel > 0) ? entity.getSlope() : null;
	}

	/**
	 * Setter for slope.
	 *
	 * @param value the value to set
	 */
	public void setSlope(Float value) {
		entity.setSlope(value);
	}

	/**
	 * Getter for version.
	 *
	 * @return value for version
	 */
	@XmlElement
	public String getVersion() {
		return (expandLevel > 0) ? entity.getVersion() : null;
	}

	/**
	 * Setter for version.
	 *
	 * @param value the value to set
	 */
	public void setVersion(String value) {
		entity.setVersion(value);
	}

	/**
	 * Getter for classifcation.
	 *
	 * @return value for classifcation
	 */
	@XmlElement
	public String getClassifcation() {
		return (expandLevel > 0) ? entity.getClassifcation() : null;
	}

	/**
	 * Setter for classifcation.
	 *
	 * @param value the value to set
	 */
	public void setClassifcation(String value) {
		entity.setClassifcation(value);
	}

	/**
	 * Getter for slSystem.
	 *
	 * @return value for slSystem
	 */
	@XmlElement
	public String getSlSystem() {
		return (expandLevel > 0) ? entity.getSlSystem() : null;
	}

	/**
	 * Setter for slSystem.
	 *
	 * @param value the value to set
	 */
	public void setSlSystem(String value) {
		entity.setSlSystem(value);
	}

	/**
	 * Getter for salb.
	 *
	 * @return value for salb
	 */
	@XmlElement
	public Float getSalb() {
		return (expandLevel > 0) ? entity.getSalb() : null;
	}

	/**
	 * Setter for salb.
	 *
	 * @param value the value to set
	 */
	public void setSalb(Float value) {
		entity.setSalb(value);
	}

	/**
	 * Getter for slu1.
	 *
	 * @return value for slu1
	 */
	@XmlElement
	public Float getSlu1() {
		return (expandLevel > 0) ? entity.getSlu1() : null;
	}

	/**
	 * Setter for slu1.
	 *
	 * @param value the value to set
	 */
	public void setSlu1(Float value) {
		entity.setSlu1(value);
	}

	/**
	 * Getter for sldr.
	 *
	 * @return value for sldr
	 */
	@XmlElement
	public Float getSldr() {
		return (expandLevel > 0) ? entity.getSldr() : null;
	}

	/**
	 * Setter for sldr.
	 *
	 * @param value the value to set
	 */
	public void setSldr(Float value) {
		entity.setSldr(value);
	}

	/**
	 * Getter for slro.
	 *
	 * @return value for slro
	 */
	@XmlElement
	public Float getSlro() {
		return (expandLevel > 0) ? entity.getSlro() : null;
	}

	/**
	 * Setter for slro.
	 *
	 * @param value the value to set
	 */
	public void setSlro(Float value) {
		entity.setSlro(value);
	}

	/**
	 * Getter for sscol.
	 *
	 * @return value for sscol
	 */
	@XmlElement
	public String getSscol() {
		return (expandLevel > 0) ? entity.getSscol() : null;
	}

	/**
	 * Setter for sscol.
	 *
	 * @param value the value to set
	 */
	public void setSscol(String value) {
		entity.setSscol(value);
	}

	/**
	 * Getter for flst.
	 *
	 * @return value for flst
	 */
	@XmlElement
	public Float getFlst() {
		return (expandLevel > 0) ? entity.getFlst() : null;
	}

	/**
	 * Setter for flst.
	 *
	 * @param value the value to set
	 */
	public void setFlst(Float value) {
		entity.setFlst(value);
	}

	/**
	 * Getter for slnf.
	 *
	 * @return value for slnf
	 */
	@XmlElement
	public Float getSlnf() {
		return (expandLevel > 0) ? entity.getSlnf() : null;
	}

	/**
	 * Setter for slnf.
	 *
	 * @param value the value to set
	 */
	public void setSlnf(Float value) {
		entity.setSlnf(value);
	}

	/**
	 * Getter for smhb.
	 *
	 * @return value for smhb
	 */
	@XmlElement
	public String getSmhb() {
		return (expandLevel > 0) ? entity.getSmhb() : null;
	}

	/**
	 * Setter for smhb.
	 *
	 * @param value the value to set
	 */
	public void setSmhb(String value) {
		entity.setSmhb(value);
	}

	/**
	 * Getter for smke.
	 *
	 * @return value for smke
	 */
	@XmlElement
	public String getSmke() {
		return (expandLevel > 0) ? entity.getSmke() : null;
	}

	/**
	 * Setter for smke.
	 *
	 * @param value the value to set
	 */
	public void setSmke(String value) {
		entity.setSmke(value);
	}

	/**
	 * Getter for smpx.
	 *
	 * @return value for smpx
	 */
	@XmlElement
	public String getSmpx() {
		return (expandLevel > 0) ? entity.getSmpx() : null;
	}

	/**
	 * Setter for smpx.
	 *
	 * @param value the value to set
	 */
	public void setSmpx(String value) {
		entity.setSmpx(value);
	}

	/**
	 * Getter for notes.
	 *
	 * @return value for notes
	 */
	@XmlElement
	public String getNotes() {
		return (expandLevel > 0) ? entity.getNotes() : null;
	}

	/**
	 * Setter for notes.
	 *
	 * @param value the value to set
	 */
	public void setNotes(String value) {
		entity.setNotes(value);
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
	 * Returns the SoilProfile entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public SoilProfile getEntity() {
		if (entity.getSid() == null) {
			SoilProfileConverter converter = UriResolver.getInstance().resolve(SoilProfileConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved SoilProfile entity.
	 *
	 * @return an resolved entity
	 */
	public SoilProfile resolveEntity(EntityManager em) {
		Collection<SoilProfileLayer> soilProfileLayerCollection = entity.getSoilProfileLayerCollection();
		Collection<SoilProfileLayer> newsoilProfileLayerCollection = new java.util.ArrayList<SoilProfileLayer>();
		if (soilProfileLayerCollection != null) {
			for (SoilProfileLayer item : soilProfileLayerCollection) {
				newsoilProfileLayerCollection.add(em.getReference(SoilProfileLayer.class, item.getSoilProfileLayerPK()));
			}
		}
		entity.setSoilProfileLayerCollection(newsoilProfileLayerCollection);
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilProfileConverter)) {
			return false;
		}
		SoilProfileConverter other = (SoilProfileConverter) object;
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

	/**
	 * Getter for soilProfileLayerCollection.
	 *
	 * @return value for soilProfileLayerCollection
	 */
	@XmlElement
	public SoilProfileLayersConverter getSoilProfileLayerCollection() {
		if (expandLevel > 0) {
			if (entity.getSoilProfileLayerCollection() != null) {
				return new SoilProfileLayersConverter(entity.getSoilProfileLayerCollection(), uri.resolve("soilProfileLayerCollection/"), expandLevel - 1);
			}
		}
		return null;
	}

	/**
	 * Setter for soilProfileLayerCollection.
	 *
	 * @param value the value to set
	 */
	public void setSoilProfileLayerCollection(SoilProfileLayersConverter value) {
		entity.setSoilProfileLayerCollection((value != null) ? value.getEntities() : null);
	}
}
