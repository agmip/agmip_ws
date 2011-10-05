package converter;

import beans.IrrigationLevel;
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
@XmlRootElement(name = "irrigationLevels")
public class IrrigationLevelsConverter {
    private Collection<IrrigationLevel> entities;
    private Collection<IrrigationLevelConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of IrrigationLevelsConverter */
    public IrrigationLevelsConverter() {
    }

    /**
     * Creates a new instance of IrrigationLevelsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public IrrigationLevelsConverter(Collection<IrrigationLevel> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getIrrigationLevels();
    }

    /**
     * Returns a collection of IrrigationLevelConverter.
     *
     * @return a collection of IrrigationLevelConverter
     */
    @XmlElement
    public Collection<IrrigationLevelConverter> getIrrigationLevels() {
        if (items == null) {
            items = new ArrayList<IrrigationLevelConverter>();
        }
        if (entities != null) {
            items.clear();
            for (IrrigationLevel entity : entities) {
                items.add(new IrrigationLevelConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of IrrigationLevelConverter.
     *
     * @param a collection of IrrigationLevelConverter to set
     */
    public void setIrrigationLevels(Collection<IrrigationLevelConverter> items) {
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
     * Returns a collection IrrigationLevel entities.
     *
     * @return a collection of IrrigationLevel entities
     */
    @XmlTransient
    public Collection<IrrigationLevel> getEntities() {
        entities = new ArrayList<IrrigationLevel>();
        if (items != null) {
            for (IrrigationLevelConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IrrigationLevelsConverter)) {
            return false;
        }
        IrrigationLevelsConverter other = (IrrigationLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<IrrigationLevelConverter> itemSet = new HashSet<IrrigationLevelConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (IrrigationLevelConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
