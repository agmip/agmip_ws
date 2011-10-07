/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.HarvestLevels;
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
@XmlRootElement(name = "harvestLevelss")
public class HarvestLevelssConverter {
    private Collection<HarvestLevels> entities;
    private Collection<HarvestLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of HarvestLevelssConverter */
    public HarvestLevelssConverter() {
    }

    /**
     * Creates a new instance of HarvestLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public HarvestLevelssConverter(Collection<HarvestLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getHarvestLevels();
    }

    /**
     * Returns a collection of HarvestLevelsConverter.
     *
     * @return a collection of HarvestLevelsConverter
     */
    @XmlElement
    public Collection<HarvestLevelsConverter> getHarvestLevels() {
        if (items == null) {
            items = new ArrayList<HarvestLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (HarvestLevels entity : entities) {
                items.add(new HarvestLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of HarvestLevelsConverter.
     *
     * @param a collection of HarvestLevelsConverter to set
     */
    public void setHarvestLevels(Collection<HarvestLevelsConverter> items) {
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
     * Returns a collection HarvestLevels entities.
     *
     * @return a collection of HarvestLevels entities
     */
    @XmlTransient
    public Collection<HarvestLevels> getEntities() {
        entities = new ArrayList<HarvestLevels>();
        if (items != null) {
            for (HarvestLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HarvestLevelssConverter)) {
            return false;
        }
        HarvestLevelssConverter other = (HarvestLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<HarvestLevelsConverter> itemSet = new HashSet<HarvestLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (HarvestLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
