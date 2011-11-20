package converter;

import beans.EnvironModifEvent;
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
@XmlRootElement(name = "environModifEvents")
public class EnvironModifEventsConverter {
	private Collection<EnvironModifEvent> entities;
	private Collection<EnvironModifEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of EnvironModifEventsConverter */
	public EnvironModifEventsConverter() {
	}

	/**
	 * Creates a new instance of EnvironModifEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public EnvironModifEventsConverter(Collection<EnvironModifEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getEnvironModifEvent();
	}

	/**
	 * Returns a collection of EnvironModifEventConverter.
	 *
	 * @return a collection of EnvironModifEventConverter
	 */
	@XmlElement
	public Collection<EnvironModifEventConverter> getEnvironModifEvent() {
		if (items == null) {
			items = new ArrayList<EnvironModifEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (EnvironModifEvent entity : entities) {
				items.add(new EnvironModifEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of EnvironModifEventConverter.
	 *
	 * @param a collection of EnvironModifEventConverter to set
	 */
	public void setEnvironModifEvent(Collection<EnvironModifEventConverter> items) {
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
	 * Returns a collection EnvironModifEvents entities.
	 *
	 * @return a collection of EnvironModifEvents entities
	 */
	@XmlTransient
	public Collection<EnvironModifEvent> getEntities() {
		entities = new ArrayList<EnvironModifEvent>();
		if (items != null) {
			for (EnvironModifEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EnvironModifEventsConverter)) {
			return false;
		}
		EnvironModifEventsConverter other = (EnvironModifEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<EnvironModifEventConverter> itemSet = new HashSet<EnvironModifEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (EnvironModifEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
