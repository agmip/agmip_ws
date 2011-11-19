package converter;

import beans.EnvironModifLevel;
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
@XmlRootElement(name = "environModifLevels")
public class EnvironModifLevelsConverter {
	private Collection<EnvironModifLevel> entities;
	private Collection<EnvironModifLevelConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of EnvironModifLevelsConverter */
	public EnvironModifLevelsConverter() {
	}

	/**
	 * Creates a new instance of EnvironModifLevelsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public EnvironModifLevelsConverter(Collection<EnvironModifLevel> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getEnvironModifLevels();
	}

	/**
	 * Returns a collection of EnvironModifLevelConverter.
	 *
	 * @return a collection of EnvironModifLevelConverter
	 */
	@XmlElement
	public Collection<EnvironModifLevelConverter> getEnvironModifLevels() {
		if (items == null) {
			items = new ArrayList<EnvironModifLevelConverter>();
		}
		if (entities != null) {
			items.clear();
			for (EnvironModifLevel entity : entities) {
				items.add(new EnvironModifLevelConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of EnvironModifLevelConverter.
	 *
	 * @param a collection of EnvironModifLevelConverter to set
	 */
	public void setEnvironModifLevels(Collection<EnvironModifLevelConverter> items) {
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
	 * Returns a collection EnvironModifLevels entities.
	 *
	 * @return a collection of EnvironModifLevels entities
	 */
	@XmlTransient
	public Collection<EnvironModifLevel> getEntities() {
		entities = new ArrayList<EnvironModifLevel>();
		if (items != null) {
			for (EnvironModifLevelConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EnvironModifLevelsConverter)) {
			return false;
		}
		EnvironModifLevelsConverter other = (EnvironModifLevelsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<EnvironModifLevelConverter> itemSet = new HashSet<EnvironModifLevelConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (EnvironModifLevelConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
