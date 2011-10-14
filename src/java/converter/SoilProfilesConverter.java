package converter;

import beans.SoilProfile;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "soilProfiles")
public class SoilProfilesConverter {
	private Collection<SoilProfile> entities;
	private Collection<SoilProfileConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilProfilesConverter */
	public SoilProfilesConverter() {
	}

	/**
	 * Creates a new instance of SoilProfilesConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilProfilesConverter(Collection<SoilProfile> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getSoilProfile();
	}

	/**
	 * Returns a collection of SoilProfileConverter.
	 *
	 * @return a collection of SoilProfileConverter
	 */
	@XmlElement
	public Collection<SoilProfileConverter> getSoilProfile() {
		if (items == null) {
			items = new ArrayList<SoilProfileConverter>();
		}
		if (entities != null) {
			items.clear();
			for (SoilProfile entity : entities) {
				items.add(new SoilProfileConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of SoilProfileConverter.
	 *
	 * @param a collection of SoilProfileConverter to set
	 */
	public void setSoilProfile(Collection<SoilProfileConverter> items) {
		this.items = items;
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
	 * Returns a collection SoilProfile entities.
	 *
	 * @return a collection of SoilProfile entities
	 */
	@XmlTransient
	public Collection<SoilProfile> getEntities() {
		entities = new ArrayList<SoilProfile>();
		if (items != null) {
			for (SoilProfileConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilProfilesConverter)) {
			return false;
		}
		SoilProfilesConverter other = (SoilProfilesConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<SoilProfileConverter> itemSet = new HashSet<SoilProfileConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (SoilProfileConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
