/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.FertilizerLevels;
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
@XmlRootElement(name = "fertilizerLevelss")
public class FertilizerLevelssConverter {
    private Collection<FertilizerLevels> entities;
    private Collection<FertilizerLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of FertilizerLevelssConverter */
    public FertilizerLevelssConverter() {
    }

    /**
     * Creates a new instance of FertilizerLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public FertilizerLevelssConverter(Collection<FertilizerLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getFertilizerLevels();
    }

    /**
     * Returns a collection of FertilizerLevelsConverter.
     *
     * @return a collection of FertilizerLevelsConverter
     */
    @XmlElement
    public Collection<FertilizerLevelsConverter> getFertilizerLevels() {
        if (items == null) {
            items = new ArrayList<FertilizerLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (FertilizerLevels entity : entities) {
                items.add(new FertilizerLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of FertilizerLevelsConverter.
     *
     * @param a collection of FertilizerLevelsConverter to set
     */
    public void setFertilizerLevels(Collection<FertilizerLevelsConverter> items) {
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
     * Returns a collection FertilizerLevels entities.
     *
     * @return a collection of FertilizerLevels entities
     */
    @XmlTransient
    public Collection<FertilizerLevels> getEntities() {
        entities = new ArrayList<FertilizerLevels>();
        if (items != null) {
            for (FertilizerLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FertilizerLevelssConverter)) {
            return false;
        }
        FertilizerLevelssConverter other = (FertilizerLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<FertilizerLevelsConverter> itemSet = new HashSet<FertilizerLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (FertilizerLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
