package converter;

import beans.MulchEvent;
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
@XmlRootElement(name = "mulchEvents")
public class MulchEventsConverter {
	private Collection<MulchEvent> entities;
	private Collection<MulchEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of MulchEventsConverter */
	public MulchEventsConverter() {
	}

	/**
	 * Creates a new instance of MulchEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public MulchEventsConverter(Collection<MulchEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getMulchEvents();
	}

	/**
	 * Returns a collection of MulchEventConverter.
	 *
	 * @return a collection of MulchEventConverter
	 */
	@XmlElement
	public Collection<MulchEventConverter> getMulchEvents() {
		if (items == null) {
			items = new ArrayList<MulchEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (MulchEvent entity : entities) {
				items.add(new MulchEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of MulchEventConverter.
	 *
	 * @param a collection of MulchEventConverter to set
	 */
	public void setMulchEvents(Collection<MulchEventConverter> items) {
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
	 * Returns a collection MulchEvents entities.
	 *
	 * @return a collection of MulchEvents entities
	 */
	@XmlTransient
	public Collection<MulchEvent> getEntities() {
		entities = new ArrayList<MulchEvent>();
		if (items != null) {
			for (MulchEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MulchEventsConverter)) {
			return false;
		}
		MulchEventsConverter other = (MulchEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<MulchEventConverter> itemSet = new HashSet<MulchEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (MulchEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
