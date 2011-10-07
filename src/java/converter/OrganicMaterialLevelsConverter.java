package converter;

import beans.OrganicMaterialLevel;
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
@XmlRootElement(name = "organicMaterialLevels")
public class OrganicMaterialLevelsConverter {
    private Collection<OrganicMaterialLevel> entities;
    private Collection<OrganicMaterialLevelConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of OrganicMaterialLevelsConverter */
    public OrganicMaterialLevelsConverter() {
    }

    /**
     * Creates a new instance of OrganicMaterialLevelsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public OrganicMaterialLevelsConverter(Collection<OrganicMaterialLevel> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getOrganicMaterialLevels();
    }

    /**
     * Returns a collection of OrganicMaterialLevelConverter.
     *
     * @return a collection of OrganicMaterialLevelConverter
     */
    @XmlElement
    public Collection<OrganicMaterialLevelConverter> getOrganicMaterialLevels() {
        if (items == null) {
            items = new ArrayList<OrganicMaterialLevelConverter>();
        }
        if (entities != null) {
            items.clear();
            for (OrganicMaterialLevel entity : entities) {
                items.add(new OrganicMaterialLevelConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of OrganicMaterialLevelConverter.
     *
     * @param a collection of OrganicMaterialLevelConverter to set
     */
    public void setOrganicMaterialLevels(Collection<OrganicMaterialLevelConverter> items) {
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
     * Returns a collection OrganicMaterialLevel entities.
     *
     * @return a collection of OrganicMaterialLevel entities
     */
    @XmlTransient
    public Collection<OrganicMaterialLevel> getEntities() {
        entities = new ArrayList<OrganicMaterialLevel>();
        if (items != null) {
            for (OrganicMaterialLevelConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrganicMaterialLevelsConverter)) {
            return false;
        }
        OrganicMaterialLevelsConverter other = (OrganicMaterialLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<OrganicMaterialLevelConverter> itemSet = new HashSet<OrganicMaterialLevelConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (OrganicMaterialLevelConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
