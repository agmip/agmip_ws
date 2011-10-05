package converter;

import beans.Planting;
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
@XmlRootElement(name = "plantings")
public class PlantingsConverter {
    private Collection<Planting> entities;
    private Collection<PlantingConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of PlantingsConverter */
    public PlantingsConverter() {
    }

    /**
     * Creates a new instance of PlantingsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PlantingsConverter(Collection<Planting> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getPlantings();
    }

    /**
     * Returns a collection of PlantingConverter.
     *
     * @return a collection of PlantingConverter
     */
    @XmlElement
    public Collection<PlantingConverter> getPlantings() {
        if (items == null) {
            items = new ArrayList<PlantingConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Planting entity : entities) {
                items.add(new PlantingConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of PlantingConverter.
     *
     * @param a collection of PlantingConverter to set
     */
    public void setPlantings(Collection<PlantingConverter> items) {
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
     * Returns a collection Planting entities.
     *
     * @return a collection of Planting entities
     */
    @XmlTransient
    public Collection<Planting> getEntities() {
        entities = new ArrayList<Planting>();
        if (items != null) {
            for (PlantingConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlantingsConverter)) {
            return false;
        }
        PlantingsConverter other = (PlantingsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<PlantingConverter> itemSet = new HashSet<PlantingConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (PlantingConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
