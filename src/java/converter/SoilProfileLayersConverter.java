/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.SoilProfileLayer;
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
@XmlRootElement(name = "soilProfileLayers")
public class SoilProfileLayersConverter {
	private Collection<SoilProfileLayer> entities;
	private Collection<SoilProfileLayerConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilProfileLayersConverter */
	public SoilProfileLayersConverter() {
	}

	/**
	 * Creates a new instance of SoilProfileLayersConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilProfileLayersConverter(Collection<SoilProfileLayer> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getSoilProfileLayer();
	}

	/**
	 * Returns a collection of SoilProfileLayerConverter.
	 *
	 * @return a collection of SoilProfileLayerConverter
	 */
	@XmlElement
	public Collection<SoilProfileLayerConverter> getSoilProfileLayer() {
		if (items == null) {
			items = new ArrayList<SoilProfileLayerConverter>();
		}
		if (entities != null) {
			items.clear();
			for (SoilProfileLayer entity : entities) {
				items.add(new SoilProfileLayerConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of SoilProfileLayerConverter.
	 *
	 * @param a collection of SoilProfileLayerConverter to set
	 */
	public void setSoilProfileLayer(Collection<SoilProfileLayerConverter> items) {
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
	 * Returns a collection SoilProfileLayer entities.
	 *
	 * @return a collection of SoilProfileLayer entities
	 */
	@XmlTransient
	public Collection<SoilProfileLayer> getEntities() {
		entities = new ArrayList<SoilProfileLayer>();
		if (items != null) {
			for (SoilProfileLayerConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilProfileLayersConverter)) {
			return false;
		}
		SoilProfileLayersConverter other = (SoilProfileLayersConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<SoilProfileLayerConverter> itemSet = new HashSet<SoilProfileLayerConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (SoilProfileLayerConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
