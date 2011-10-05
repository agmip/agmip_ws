package converter;

import beans.WeatherDaily;
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
@XmlRootElement(name = "weatherDailies")
public class WeatherDailiesConverter {
	private Collection<WeatherDaily> entities;
	private Collection<WeatherDailyConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of WeatherDailiesConverter */
	public WeatherDailiesConverter() {
	}

	/**
	 * Creates a new instance of WeatherDailiesConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public WeatherDailiesConverter(Collection<WeatherDaily> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getWeatherDaily();
	}

	/**
	 * Returns a collection of WeatherDailyConverter.
	 *
	 * @return a collection of WeatherDailyConverter
	 */
	@XmlElement
	public Collection<WeatherDailyConverter> getWeatherDaily() {
		if (items == null) {
			items = new ArrayList<WeatherDailyConverter>();
		}
		if (entities != null) {
			items.clear();
			for (WeatherDaily entity : entities) {
				items.add(new WeatherDailyConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of WeatherDailyConverter.
	 *
	 * @param a collection of WeatherDailyConverter to set
	 */
	public void setWeatherDaily(Collection<WeatherDailyConverter> items) {
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
	 * Returns a collection WeatherDaily entities.
	 *
	 * @return a collection of WeatherDaily entities
	 */
	@XmlTransient
	public Collection<WeatherDaily> getEntities() {
		entities = new ArrayList<WeatherDaily>();
		if (items != null) {
			for (WeatherDailyConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeatherDailiesConverter)) {
			return false;
		}
		WeatherDailiesConverter other = (WeatherDailiesConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<WeatherDailyConverter> itemSet = new HashSet<WeatherDailyConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (WeatherDailyConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
