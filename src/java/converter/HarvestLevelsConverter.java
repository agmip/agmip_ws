package converter;

import beans.HarvestLevel;
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
@XmlRootElement(name = "harvestLevels")
public class HarvestLevelsConverter {
	private Collection<HarvestLevel> entities;
	private Collection<HarvestLevelConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of HarvestLevelsConverter */
	public HarvestLevelsConverter() {
	}

	/**
	 * Creates a new instance of HarvestLevelsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public HarvestLevelsConverter(Collection<HarvestLevel> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getHarvestLevels();
	}

	/**
	 * Returns a collection of HarvestLevelConverter.
	 *
	 * @return a collection of HarvestLevelConverter
	 */
	@XmlElement
	public Collection<HarvestLevelConverter> getHarvestLevels() {
		if (items == null) {
			items = new ArrayList<HarvestLevelConverter>();
		}
		if (entities != null) {
			items.clear();
			for (HarvestLevel entity : entities) {
				items.add(new HarvestLevelConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of HarvestLevelConverter.
	 *
	 * @param a collection of HarvestLevelConverter to set
	 */
	public void setHarvestLevels(Collection<HarvestLevelConverter> items) {
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
	 * Returns a collection HarvestLevels entities.
	 *
	 * @return a collection of HarvestLevels entities
	 */
	@XmlTransient
	public Collection<HarvestLevel> getEntities() {
		entities = new ArrayList<HarvestLevel>();
		if (items != null) {
			for (HarvestLevelConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HarvestLevelsConverter)) {
			return false;
		}
		HarvestLevelsConverter other = (HarvestLevelsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<HarvestLevelConverter> itemSet = new HashSet<HarvestLevelConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (HarvestLevelConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
