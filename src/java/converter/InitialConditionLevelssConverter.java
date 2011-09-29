/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.InitialConditionLevels;
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
@XmlRootElement(name = "initialConditionLevelss")
public class InitialConditionLevelssConverter {
    private Collection<InitialConditionLevels> entities;
    private Collection<InitialConditionLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of InitialConditionLevelssConverter */
    public InitialConditionLevelssConverter() {
    }

    /**
     * Creates a new instance of InitialConditionLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public InitialConditionLevelssConverter(Collection<InitialConditionLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getInitialConditionLevels();
    }

    /**
     * Returns a collection of InitialConditionLevelsConverter.
     *
     * @return a collection of InitialConditionLevelsConverter
     */
    @XmlElement
    public Collection<InitialConditionLevelsConverter> getInitialConditionLevels() {
        if (items == null) {
            items = new ArrayList<InitialConditionLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (InitialConditionLevels entity : entities) {
                items.add(new InitialConditionLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of InitialConditionLevelsConverter.
     *
     * @param a collection of InitialConditionLevelsConverter to set
     */
    public void setInitialConditionLevels(Collection<InitialConditionLevelsConverter> items) {
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
     * Returns a collection InitialConditionLevels entities.
     *
     * @return a collection of InitialConditionLevels entities
     */
    @XmlTransient
    public Collection<InitialConditionLevels> getEntities() {
        entities = new ArrayList<InitialConditionLevels>();
        if (items != null) {
            for (InitialConditionLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InitialConditionLevelssConverter)) {
            return false;
        }
        InitialConditionLevelssConverter other = (InitialConditionLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<InitialConditionLevelsConverter> itemSet = new HashSet<InitialConditionLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (InitialConditionLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
