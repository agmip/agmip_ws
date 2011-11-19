package converter;

import beans.SoilAnalysesLevel;
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
@XmlRootElement(name = "soilAnalysesLevels")
public class SoilAnalysesLevelsConverter {
	private Collection<SoilAnalysesLevel> entities;
	private Collection<SoilAnalysesLevelConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilAnalysesLevelsConverter */
	public SoilAnalysesLevelsConverter() {
	}

	/**
	 * Creates a new instance of SoilAnalysesLevelsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilAnalysesLevelsConverter(Collection<SoilAnalysesLevel> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getSoilAnalysesLevels();
	}

	/**
	 * Returns a collection of SoilAnalysesLevelConverter.
	 *
	 * @return a collection of SoilAnalysesLevelConverter
	 */
	@XmlElement
	public Collection<SoilAnalysesLevelConverter> getSoilAnalysesLevels() {
		if (items == null) {
			items = new ArrayList<SoilAnalysesLevelConverter>();
		}
		if (entities != null) {
			items.clear();
			for (SoilAnalysesLevel entity : entities) {
				items.add(new SoilAnalysesLevelConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of SoilAnalysesLevelConverter.
	 *
	 * @param a collection of SoilAnalysesLevelConverter to set
	 */
	public void setSoilAnalysesLevels(Collection<SoilAnalysesLevelConverter> items) {
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
	 * Returns a collection SoilAnalysesLevels entities.
	 *
	 * @return a collection of SoilAnalysesLevels entities
	 */
	@XmlTransient
	public Collection<SoilAnalysesLevel> getEntities() {
		entities = new ArrayList<SoilAnalysesLevel>();
		if (items != null) {
			for (SoilAnalysesLevelConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilAnalysesLevelsConverter)) {
			return false;
		}
		SoilAnalysesLevelsConverter other = (SoilAnalysesLevelsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<SoilAnalysesLevelConverter> itemSet = new HashSet<SoilAnalysesLevelConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (SoilAnalysesLevelConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
