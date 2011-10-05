package converter;

import beans.WeatherSources;
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
@XmlRootElement(name = "weatherSourcess")
public class WeatherSourcessConverter {
	private Collection<WeatherSources> entities;
	private Collection<WeatherSourcesConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of WeatherSourcessConverter */
	public WeatherSourcessConverter() {
	}

	/**
	 * Creates a new instance of WeatherSourcessConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public WeatherSourcessConverter(Collection<WeatherSources> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getWeatherSources();
	}

	/**
	 * Returns a collection of WeatherSourcesConverter.
	 *
	 * @return a collection of WeatherSourcesConverter
	 */
	@XmlElement
	public Collection<WeatherSourcesConverter> getWeatherSources() {
		if (items == null) {
			items = new ArrayList<WeatherSourcesConverter>();
		}
		if (entities != null) {
			items.clear();
			for (WeatherSources entity : entities) {
				items.add(new WeatherSourcesConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of WeatherSourcesConverter.
	 *
	 * @param a collection of WeatherSourcesConverter to set
	 */
	public void setWeatherSources(Collection<WeatherSourcesConverter> items) {
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
	 * Returns a collection WeatherSources entities.
	 *
	 * @return a collection of WeatherSources entities
	 */
	@XmlTransient
	public Collection<WeatherSources> getEntities() {
		entities = new ArrayList<WeatherSources>();
		if (items != null) {
			for (WeatherSourcesConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherSourcessConverter)) {
			return false;
		}
		WeatherSourcessConverter other = (WeatherSourcessConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<WeatherSourcesConverter> itemSet = new HashSet<WeatherSourcesConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (WeatherSourcesConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
