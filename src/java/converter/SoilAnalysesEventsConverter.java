package converter;

import beans.SoilAnalysesEvent;
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
@XmlRootElement(name = "soilAnalysesEvents")
public class SoilAnalysesEventsConverter {
	private Collection<SoilAnalysesEvent> entities;
	private Collection<SoilAnalysesEventConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilAnalysesEventsConverter */
	public SoilAnalysesEventsConverter() {
	}

	/**
	 * Creates a new instance of SoilAnalysesEventsConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilAnalysesEventsConverter(Collection<SoilAnalysesEvent> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getSoilAnalysesEvent();
	}

	/**
	 * Returns a collection of SoilAnalysesEventConverter.
	 *
	 * @return a collection of SoilAnalysesEventConverter
	 */
	@XmlElement
	public Collection<SoilAnalysesEventConverter> getSoilAnalysesEvent() {
		if (items == null) {
			items = new ArrayList<SoilAnalysesEventConverter>();
		}
		if (entities != null) {
			items.clear();
			for (SoilAnalysesEvent entity : entities) {
				items.add(new SoilAnalysesEventConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of SoilAnalysesEventConverter.
	 *
	 * @param a collection of SoilAnalysesEventConverter to set
	 */
	public void setSoilAnalysesEvent(Collection<SoilAnalysesEventConverter> items) {
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
	 * Returns a collection SoilAnalysesEvents entities.
	 *
	 * @return a collection of SoilAnalysesEvents entities
	 */
	@XmlTransient
	public Collection<SoilAnalysesEvent> getEntities() {
		entities = new ArrayList<SoilAnalysesEvent>();
		if (items != null) {
			for (SoilAnalysesEventConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilAnalysesEventsConverter)) {
			return false;
		}
		SoilAnalysesEventsConverter other = (SoilAnalysesEventsConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<SoilAnalysesEventConverter> itemSet = new HashSet<SoilAnalysesEventConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (SoilAnalysesEventConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
