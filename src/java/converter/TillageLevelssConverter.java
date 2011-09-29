/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.TillageLevels;
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
@XmlRootElement(name = "tillageLevelss")
public class TillageLevelssConverter {
    private Collection<TillageLevels> entities;
    private Collection<TillageLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TillageLevelssConverter */
    public TillageLevelssConverter() {
    }

    /**
     * Creates a new instance of TillageLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TillageLevelssConverter(Collection<TillageLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getTillageLevels();
    }

    /**
     * Returns a collection of TillageLevelsConverter.
     *
     * @return a collection of TillageLevelsConverter
     */
    @XmlElement
    public Collection<TillageLevelsConverter> getTillageLevels() {
        if (items == null) {
            items = new ArrayList<TillageLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (TillageLevels entity : entities) {
                items.add(new TillageLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of TillageLevelsConverter.
     *
     * @param a collection of TillageLevelsConverter to set
     */
    public void setTillageLevels(Collection<TillageLevelsConverter> items) {
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
     * Returns a collection TillageLevels entities.
     *
     * @return a collection of TillageLevels entities
     */
    @XmlTransient
    public Collection<TillageLevels> getEntities() {
        entities = new ArrayList<TillageLevels>();
        if (items != null) {
            for (TillageLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TillageLevelssConverter)) {
            return false;
        }
        TillageLevelssConverter other = (TillageLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<TillageLevelsConverter> itemSet = new HashSet<TillageLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (TillageLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
