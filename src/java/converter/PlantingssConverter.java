/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Plantings;
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
 * @author wpavan
 */
@XmlRootElement(name = "plantingss")
public class PlantingssConverter {
    private Collection<Plantings> entities;
    private Collection<PlantingsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of PlantingssConverter */
    public PlantingssConverter() {
    }

    /**
     * Creates a new instance of PlantingssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PlantingssConverter(Collection<Plantings> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getPlantings();
    }

    /**
     * Returns a collection of PlantingsConverter.
     *
     * @return a collection of PlantingsConverter
     */
    @XmlElement
    public Collection<PlantingsConverter> getPlantings() {
        if (items == null) {
            items = new ArrayList<PlantingsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Plantings entity : entities) {
                items.add(new PlantingsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of PlantingsConverter.
     *
     * @param a collection of PlantingsConverter to set
     */
    public void setPlantings(Collection<PlantingsConverter> items) {
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
     * Returns a collection Plantings entities.
     *
     * @return a collection of Plantings entities
     */
    @XmlTransient
    public Collection<Plantings> getEntities() {
        entities = new ArrayList<Plantings>();
        if (items != null) {
            for (PlantingsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlantingssConverter)) {
            return false;
        }
        PlantingssConverter other = (PlantingssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<PlantingsConverter> itemSet = new HashSet<PlantingsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (PlantingsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
