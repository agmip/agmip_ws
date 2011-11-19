package converter;

import beans.InitialConditionLevel;
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
@XmlRootElement(name = "initialConditionLevels")
public class InitialConditionLevelsConverter {
	private Collection<InitialConditionLevel> entities;
	private Collection<InitialConditionLevelConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of InitialConditionLevelsConverter */
	public InitialConditionLevelsConverter() {
	}

	/**
	 * Creates a new instance of InitialConditionLevelsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public InitialConditionLevelsConverter(Collection<InitialConditionLevel> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getInitialConditionLevels();
	}

	/**
	 * Returns a collection of InitialConditionLevelConverter.
	 *
	 * @return a collection of InitialConditionLevelConverter
	 */
	@XmlElement
	public Collection<InitialConditionLevelConverter> getInitialConditionLevels() {
		if (items == null) {
			items = new ArrayList<InitialConditionLevelConverter>();
		}
		if (entities != null) {
			items.clear();
			for (InitialConditionLevel entity : entities) {
				items.add(new InitialConditionLevelConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of InitialConditionLevelConverter.
	 *
	 * @param a collection of InitialConditionLevelConverter to set
	 */
	public void setInitialConditionLevels(Collection<InitialConditionLevelConverter> items) {
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
	 * Returns a collection InitialConditionLevels entities.
	 *
	 * @return a collection of InitialConditionLevels entities
	 */
	@XmlTransient
	public Collection<InitialConditionLevel> getEntities() {
		entities = new ArrayList<InitialConditionLevel>();
		if (items != null) {
			for (InitialConditionLevelConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof InitialConditionLevelsConverter)) {
			return false;
		}
		InitialConditionLevelsConverter other = (InitialConditionLevelsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<InitialConditionLevelConverter> itemSet = new HashSet<InitialConditionLevelConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (InitialConditionLevelConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
