package converter;

import beans.FertilizerLevel;
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
@XmlRootElement(name = "fertilizerLevels")
public class FertilizerLevelsConverter {
	private Collection<FertilizerLevel> entities;
	private Collection<FertilizerLevelConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of FertilizerLevelsConverter */
	public FertilizerLevelsConverter() {
	}

	/**
	 * Creates a new instance of FertilizerLevelsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public FertilizerLevelsConverter(Collection<FertilizerLevel> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getFertilizerLevels();
	}

	/**
	 * Returns a collection of FertilizerLevelConverter.
	 *
	 * @return a collection of FertilizerLevelConverter
	 */
	@XmlElement
	public Collection<FertilizerLevelConverter> getFertilizerLevels() {
		if (items == null) {
			items = new ArrayList<FertilizerLevelConverter>();
		}
		if (entities != null) {
			items.clear();
			for (FertilizerLevel entity : entities) {
				items.add(new FertilizerLevelConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of FertilizerLevelConverter.
	 *
	 * @param a collection of FertilizerLevelConverter to set
	 */
	public void setFertilizerLevels(Collection<FertilizerLevelConverter> items) {
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
	 * Returns a collection FertilizerLevels entities.
	 *
	 * @return a collection of FertilizerLevels entities
	 */
	@XmlTransient
	public Collection<FertilizerLevel> getEntities() {
		entities = new ArrayList<FertilizerLevel>();
		if (items != null) {
			for (FertilizerLevelConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FertilizerLevelsConverter)) {
			return false;
		}
		FertilizerLevelsConverter other = (FertilizerLevelsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<FertilizerLevelConverter> itemSet = new HashSet<FertilizerLevelConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (FertilizerLevelConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
