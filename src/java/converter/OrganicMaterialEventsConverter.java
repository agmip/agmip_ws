package converter;

import beans.OrganicMaterialEvent;
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
@XmlRootElement(name = "organicMaterialEvents")
public class OrganicMaterialEventsConverter {
	private Collection<OrganicMaterialEvent> entities;
	private Collection<OrganicMaterialEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of OrganicMaterialEventsConverter */
	public OrganicMaterialEventsConverter() {
	}

	/**
	 * Creates a new instance of OrganicMaterialEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public OrganicMaterialEventsConverter(Collection<OrganicMaterialEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getOrganicMaterialEvent();
	}

	/**
	 * Returns a collection of OrganicMaterialEventConverter.
	 *
	 * @return a collection of OrganicMaterialEventConverter
	 */
	@XmlElement
	public Collection<OrganicMaterialEventConverter> getOrganicMaterialEvent() {
		if (items == null) {
			items = new ArrayList<OrganicMaterialEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (OrganicMaterialEvent entity : entities) {
				items.add(new OrganicMaterialEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of OrganicMaterialEventConverter.
	 *
	 * @param a collection of OrganicMaterialEventConverter to set
	 */
	public void setOrganicMaterialEvent(Collection<OrganicMaterialEventConverter> items) {
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
	 * Returns a collection OrganicMaterialEvents entities.
	 *
	 * @return a collection of OrganicMaterialEvents entities
	 */
	@XmlTransient
	public Collection<OrganicMaterialEvent> getEntities() {
		entities = new ArrayList<OrganicMaterialEvent>();
		if (items != null) {
			for (OrganicMaterialEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OrganicMaterialEventsConverter)) {
			return false;
		}
		OrganicMaterialEventsConverter other = (OrganicMaterialEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<OrganicMaterialEventConverter> itemSet = new HashSet<OrganicMaterialEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (OrganicMaterialEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
