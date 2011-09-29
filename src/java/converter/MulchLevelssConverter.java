/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.MulchLevels;
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
@XmlRootElement(name = "mulchLevelss")
public class MulchLevelssConverter {
    private Collection<MulchLevels> entities;
    private Collection<MulchLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of MulchLevelssConverter */
    public MulchLevelssConverter() {
    }

    /**
     * Creates a new instance of MulchLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MulchLevelssConverter(Collection<MulchLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getMulchLevels();
    }

    /**
     * Returns a collection of MulchLevelsConverter.
     *
     * @return a collection of MulchLevelsConverter
     */
    @XmlElement
    public Collection<MulchLevelsConverter> getMulchLevels() {
        if (items == null) {
            items = new ArrayList<MulchLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (MulchLevels entity : entities) {
                items.add(new MulchLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of MulchLevelsConverter.
     *
     * @param a collection of MulchLevelsConverter to set
     */
    public void setMulchLevels(Collection<MulchLevelsConverter> items) {
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
     * Returns a collection MulchLevels entities.
     *
     * @return a collection of MulchLevels entities
     */
    @XmlTransient
    public Collection<MulchLevels> getEntities() {
        entities = new ArrayList<MulchLevels>();
        if (items != null) {
            for (MulchLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MulchLevelssConverter)) {
            return false;
        }
        MulchLevelssConverter other = (MulchLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<MulchLevelsConverter> itemSet = new HashSet<MulchLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (MulchLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
