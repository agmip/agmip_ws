package converter;

import beans.FertilizerEvent;
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
@XmlRootElement(name = "fertilizerEvents")
public class FertilizerEventsConverter {
	private Collection<FertilizerEvent> entities;
	private Collection<FertilizerEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of FertilizerEventsConverter */
	public FertilizerEventsConverter() {
	}

	/**
	 * Creates a new instance of FertilizerEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public FertilizerEventsConverter(Collection<FertilizerEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getFertilizerEvent();
	}

	/**
	 * Returns a collection of FertilizerEventConverter.
	 *
	 * @return a collection of FertilizerEventConverter
	 */
	@XmlElement
	public Collection<FertilizerEventConverter> getFertilizerEvent() {
		if (items == null) {
			items = new ArrayList<FertilizerEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (FertilizerEvent entity : entities) {
				items.add(new FertilizerEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of FertilizerEventConverter.
	 *
	 * @param a collection of FertilizerEventConverter to set
	 */
	public void setFertilizerEvent(Collection<FertilizerEventConverter> items) {
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
	 * Returns a collection FertilizerEvents entities.
	 *
	 * @return a collection of FertilizerEvents entities
	 */
	@XmlTransient
	public Collection<FertilizerEvent> getEntities() {
		entities = new ArrayList<FertilizerEvent>();
		if (items != null) {
			for (FertilizerEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FertilizerEventsConverter)) {
			return false;
		}
		FertilizerEventsConverter other = (FertilizerEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<FertilizerEventConverter> itemSet = new HashSet<FertilizerEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (FertilizerEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
