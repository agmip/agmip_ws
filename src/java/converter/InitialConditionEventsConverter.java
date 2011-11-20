package converter;

import beans.InitialConditionEvent;
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
@XmlRootElement(name = "initialConditionEvents")
public class InitialConditionEventsConverter {
	private Collection<InitialConditionEvent> entities;
	private Collection<InitialConditionEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of InitialConditionEventsConverter */
	public InitialConditionEventsConverter() {
	}

	/**
	 * Creates a new instance of InitialConditionEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public InitialConditionEventsConverter(Collection<InitialConditionEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getInitialConditionEvent();
	}

	/**
	 * Returns a collection of InitialConditionEventConverter.
	 *
	 * @return a collection of InitialConditionEventConverter
	 */
	@XmlElement
	public Collection<InitialConditionEventConverter> getInitialConditionEvent() {
		if (items == null) {
			items = new ArrayList<InitialConditionEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (InitialConditionEvent entity : entities) {
				items.add(new InitialConditionEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of InitialConditionEventConverter.
	 *
	 * @param a collection of InitialConditionEventConverter to set
	 */
	public void setInitialConditionEvent(Collection<InitialConditionEventConverter> items) {
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
	 * Returns a collection InitialConditionEvents entities.
	 *
	 * @return a collection of InitialConditionEvents entities
	 */
	@XmlTransient
	public Collection<InitialConditionEvent> getEntities() {
		entities = new ArrayList<InitialConditionEvent>();
		if (items != null) {
			for (InitialConditionEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof InitialConditionEventsConverter)) {
			return false;
		}
		InitialConditionEventsConverter other = (InitialConditionEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<InitialConditionEventConverter> itemSet = new HashSet<InitialConditionEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (InitialConditionEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
