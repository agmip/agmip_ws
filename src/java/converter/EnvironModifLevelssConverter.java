/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.EnvironModifLevels;
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
@XmlRootElement(name = "environModifLevelss")
public class EnvironModifLevelssConverter {
    private Collection<EnvironModifLevels> entities;
    private Collection<EnvironModifLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of EnvironModifLevelssConverter */
    public EnvironModifLevelssConverter() {
    }

    /**
     * Creates a new instance of EnvironModifLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public EnvironModifLevelssConverter(Collection<EnvironModifLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getEnvironModifLevels();
    }

    /**
     * Returns a collection of EnvironModifLevelsConverter.
     *
     * @return a collection of EnvironModifLevelsConverter
     */
    @XmlElement
    public Collection<EnvironModifLevelsConverter> getEnvironModifLevels() {
        if (items == null) {
            items = new ArrayList<EnvironModifLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (EnvironModifLevels entity : entities) {
                items.add(new EnvironModifLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of EnvironModifLevelsConverter.
     *
     * @param a collection of EnvironModifLevelsConverter to set
     */
    public void setEnvironModifLevels(Collection<EnvironModifLevelsConverter> items) {
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
     * Returns a collection EnvironModifLevels entities.
     *
     * @return a collection of EnvironModifLevels entities
     */
    @XmlTransient
    public Collection<EnvironModifLevels> getEntities() {
        entities = new ArrayList<EnvironModifLevels>();
        if (items != null) {
            for (EnvironModifLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EnvironModifLevelssConverter)) {
            return false;
        }
        EnvironModifLevelssConverter other = (EnvironModifLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<EnvironModifLevelsConverter> itemSet = new HashSet<EnvironModifLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (EnvironModifLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
