/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.TillageLevel;
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
@XmlRootElement(name = "tillageLevels")
public class TillageLevelsConverter {
    private Collection<TillageLevel> entities;
    private Collection<TillageLevelConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TillageLevelsConverter */
    public TillageLevelsConverter() {
    }

    /**
     * Creates a new instance of TillageLevelsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TillageLevelsConverter(Collection<TillageLevel> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getTillageLevels();
    }

    /**
     * Returns a collection of TillageLevelConverter.
     *
     * @return a collection of TillageLevelConverter
     */
    @XmlElement
    public Collection<TillageLevelConverter> getTillageLevels() {
        if (items == null) {
            items = new ArrayList<TillageLevelConverter>();
        }
        if (entities != null) {
            items.clear();
            for (TillageLevel entity : entities) {
                items.add(new TillageLevelConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of TillageLevelConverter.
     *
     * @param a collection of TillageLevelConverter to set
     */
    public void setTillageLevels(Collection<TillageLevelConverter> items) {
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
     * Returns a collection TillageLevel entities.
     *
     * @return a collection of TillageLevel entities
     */
    @XmlTransient
    public Collection<TillageLevel> getEntities() {
        entities = new ArrayList<TillageLevel>();
        if (items != null) {
            for (TillageLevelConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TillageLevelsConverter)) {
            return false;
        }
        TillageLevelsConverter other = (TillageLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<TillageLevelConverter> itemSet = new HashSet<TillageLevelConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (TillageLevelConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
