package converter;

import beans.TillageEvent;
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
@XmlRootElement(name = "tillageEvents")
public class TillageEventsConverter {
	private Collection<TillageEvent> entities;
	private Collection<TillageEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of TillageEventsConverter */
	public TillageEventsConverter() {
	}

	/**
	 * Creates a new instance of TillageEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public TillageEventsConverter(Collection<TillageEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getTillageEvents();
	}

	/**
	 * Returns a collection of TillageEventConverter.
	 *
	 * @return a collection of TillageEventConverter
	 */
	@XmlElement
	public Collection<TillageEventConverter> getTillageEvents() {
		if (items == null) {
			items = new ArrayList<TillageEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (TillageEvent entity : entities) {
				items.add(new TillageEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of TillageEventConverter.
	 *
	 * @param a collection of TillageEventConverter to set
	 */
	public void setTillageEvents(Collection<TillageEventConverter> items) {
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
	 * Returns a collection TillageEvents entities.
	 *
	 * @return a collection of TillageEvents entities
	 */
	@XmlTransient
	public Collection<TillageEvent> getEntities() {
		entities = new ArrayList<TillageEvent>();
		if (items != null) {
			for (TillageEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TillageEventsConverter)) {
			return false;
		}
		TillageEventsConverter other = (TillageEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<TillageEventConverter> itemSet = new HashSet<TillageEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (TillageEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
