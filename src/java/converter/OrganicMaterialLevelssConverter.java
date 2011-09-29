/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.OrganicMaterialLevels;
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
@XmlRootElement(name = "organicMaterialLevelss")
public class OrganicMaterialLevelssConverter {
    private Collection<OrganicMaterialLevels> entities;
    private Collection<OrganicMaterialLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of OrganicMaterialLevelssConverter */
    public OrganicMaterialLevelssConverter() {
    }

    /**
     * Creates a new instance of OrganicMaterialLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public OrganicMaterialLevelssConverter(Collection<OrganicMaterialLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getOrganicMaterialLevels();
    }

    /**
     * Returns a collection of OrganicMaterialLevelsConverter.
     *
     * @return a collection of OrganicMaterialLevelsConverter
     */
    @XmlElement
    public Collection<OrganicMaterialLevelsConverter> getOrganicMaterialLevels() {
        if (items == null) {
            items = new ArrayList<OrganicMaterialLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (OrganicMaterialLevels entity : entities) {
                items.add(new OrganicMaterialLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of OrganicMaterialLevelsConverter.
     *
     * @param a collection of OrganicMaterialLevelsConverter to set
     */
    public void setOrganicMaterialLevels(Collection<OrganicMaterialLevelsConverter> items) {
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
     * Returns a collection OrganicMaterialLevels entities.
     *
     * @return a collection of OrganicMaterialLevels entities
     */
    @XmlTransient
    public Collection<OrganicMaterialLevels> getEntities() {
        entities = new ArrayList<OrganicMaterialLevels>();
        if (items != null) {
            for (OrganicMaterialLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrganicMaterialLevelssConverter)) {
            return false;
        }
        OrganicMaterialLevelssConverter other = (OrganicMaterialLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<OrganicMaterialLevelsConverter> itemSet = new HashSet<OrganicMaterialLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (OrganicMaterialLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
