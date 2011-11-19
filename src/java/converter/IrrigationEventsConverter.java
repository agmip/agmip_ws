package converter;

import beans.IrrigationEvent;
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
@XmlRootElement(name = "irrigationEvents")
public class IrrigationEventsConverter {
	private Collection<IrrigationEvent> entities;
	private Collection<IrrigationEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of IrrigationEventsConverter */
	public IrrigationEventsConverter() {
	}

	/**
	 * Creates a new instance of IrrigationEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public IrrigationEventsConverter(Collection<IrrigationEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getIrrigationEvents();
	}

	/**
	 * Returns a collection of IrrigationEventConverter.
	 *
	 * @return a collection of IrrigationEventConverter
	 */
	@XmlElement
	public Collection<IrrigationEventConverter> getIrrigationEvents() {
		if (items == null) {
			items = new ArrayList<IrrigationEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (IrrigationEvent entity : entities) {
				items.add(new IrrigationEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of IrrigationEventConverter.
	 *
	 * @param a collection of IrrigationEventConverter to set
	 */
	public void setIrrigationEvents(Collection<IrrigationEventConverter> items) {
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
	 * Returns a collection IrrigationEvents entities.
	 *
	 * @return a collection of IrrigationEvents entities
	 */
	@XmlTransient
	public Collection<IrrigationEvent> getEntities() {
		entities = new ArrayList<IrrigationEvent>();
		if (items != null) {
			for (IrrigationEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof IrrigationEventsConverter)) {
			return false;
		}
		IrrigationEventsConverter other = (IrrigationEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<IrrigationEventConverter> itemSet = new HashSet<IrrigationEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (IrrigationEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
