package converter;

import beans.HarvestEvent;
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
@XmlRootElement(name = "harvestEvents")
public class HarvestEventsConverter {
	private Collection<HarvestEvent> entities;
	private Collection<HarvestEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of HarvestEventsConverter */
	public HarvestEventsConverter() {
	}

	/**
	 * Creates a new instance of HarvestEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public HarvestEventsConverter(Collection<HarvestEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getHarvestEvents();
	}

	/**
	 * Returns a collection of HarvestEventConverter.
	 *
	 * @return a collection of HarvestEventConverter
	 */
	@XmlElement
	public Collection<HarvestEventConverter> getHarvestEvents() {
		if (items == null) {
			items = new ArrayList<HarvestEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (HarvestEvent entity : entities) {
				items.add(new HarvestEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of HarvestEventConverter.
	 *
	 * @param a collection of HarvestEventConverter to set
	 */
	public void setHarvestEvents(Collection<HarvestEventConverter> items) {
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
	 * Returns a collection HarvestEvents entities.
	 *
	 * @return a collection of HarvestEvents entities
	 */
	@XmlTransient
	public Collection<HarvestEvent> getEntities() {
		entities = new ArrayList<HarvestEvent>();
		if (items != null) {
			for (HarvestEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HarvestEventsConverter)) {
			return false;
		}
		HarvestEventsConverter other = (HarvestEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<HarvestEventConverter> itemSet = new HashSet<HarvestEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (HarvestEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
