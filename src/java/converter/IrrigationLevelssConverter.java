/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.IrrigationLevels;
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
@XmlRootElement(name = "irrigationLevelss")
public class IrrigationLevelssConverter {
    private Collection<IrrigationLevels> entities;
    private Collection<IrrigationLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of IrrigationLevelssConverter */
    public IrrigationLevelssConverter() {
    }

    /**
     * Creates a new instance of IrrigationLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public IrrigationLevelssConverter(Collection<IrrigationLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getIrrigationLevels();
    }

    /**
     * Returns a collection of IrrigationLevelsConverter.
     *
     * @return a collection of IrrigationLevelsConverter
     */
    @XmlElement
    public Collection<IrrigationLevelsConverter> getIrrigationLevels() {
        if (items == null) {
            items = new ArrayList<IrrigationLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (IrrigationLevels entity : entities) {
                items.add(new IrrigationLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of IrrigationLevelsConverter.
     *
     * @param a collection of IrrigationLevelsConverter to set
     */
    public void setIrrigationLevels(Collection<IrrigationLevelsConverter> items) {
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
     * Returns a collection IrrigationLevels entities.
     *
     * @return a collection of IrrigationLevels entities
     */
    @XmlTransient
    public Collection<IrrigationLevels> getEntities() {
        entities = new ArrayList<IrrigationLevels>();
        if (items != null) {
            for (IrrigationLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IrrigationLevelssConverter)) {
            return false;
        }
        IrrigationLevelssConverter other = (IrrigationLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<IrrigationLevelsConverter> itemSet = new HashSet<IrrigationLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (IrrigationLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
