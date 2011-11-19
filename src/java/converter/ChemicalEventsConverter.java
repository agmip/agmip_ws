package converter;

import beans.ChemicalEvent;
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
@XmlRootElement(name = "chemicalEvents")
public class ChemicalEventsConverter {
	private Collection<ChemicalEvent> entities;
	private Collection<ChemicalEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of ChemicalEventsConverter */
	public ChemicalEventsConverter() {
	}

	/**
	 * Creates a new instance of ChemicalEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public ChemicalEventsConverter(Collection<ChemicalEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getChemicalEvents();
	}

	/**
	 * Returns a collection of ChemicalEventConverter.
	 *
	 * @return a collection of ChemicalEventConverter
	 */
	@XmlElement
	public Collection<ChemicalEventConverter> getChemicalEvents() {
		if (items == null) {
			items = new ArrayList<ChemicalEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (ChemicalEvent entity : entities) {
				items.add(new ChemicalEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of ChemicalEventConverter.
	 *
	 * @param a collection of ChemicalEventConverter to set
	 */
	public void setChemicalEvents(Collection<ChemicalEventConverter> items) {
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
	 * Returns a collection ChemicalEvents entities.
	 *
	 * @return a collection of ChemicalEvents entities
	 */
	@XmlTransient
	public Collection<ChemicalEvent> getEntities() {
		entities = new ArrayList<ChemicalEvent>();
		if (items != null) {
			for (ChemicalEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ChemicalEventsConverter)) {
			return false;
		}
		ChemicalEventsConverter other = (ChemicalEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<ChemicalEventConverter> itemSet = new HashSet<ChemicalEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (ChemicalEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
