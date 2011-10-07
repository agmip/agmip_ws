package converter;

import beans.WeatherSource;
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
@XmlRootElement(name = "weatherSources")
public class WeatherSourcesConverter {
	private Collection<WeatherSource> entities;
	private Collection<WeatherSourceConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of WeatherSourcesConverter */
	public WeatherSourcesConverter() {
	}

	/**
	 * Creates a new instance of WeatherSourcesConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public WeatherSourcesConverter(Collection<WeatherSource> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getWeatherSource();
	}

	/**
	 * Returns a collection of WeatherSourceConverter.
	 *
	 * @return a collection of WeatherSourceConverter
	 */
	@XmlElement
	public Collection<WeatherSourceConverter> getWeatherSource() {
		if (items == null) {
			items = new ArrayList<WeatherSourceConverter>();
		}
		if (entities != null) {
			items.clear();
			for (WeatherSource entity : entities) {
				items.add(new WeatherSourceConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of WeatherSourceConverter.
	 *
	 * @param a collection of WeatherSourceConverter to set
	 */
	public void setWeatherSource(Collection<WeatherSourceConverter> items) {
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
	 * Returns a collection WeatherSource entities.
	 *
	 * @return a collection of WeatherSource entities
	 */
	@XmlTransient
	public Collection<WeatherSource> getEntities() {
		entities = new ArrayList<WeatherSource>();
		if (items != null) {
			for (WeatherSourceConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherSourcesConverter)) {
			return false;
		}
		WeatherSourcesConverter other = (WeatherSourcesConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<WeatherSourceConverter> itemSet = new HashSet<WeatherSourceConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (WeatherSourceConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
