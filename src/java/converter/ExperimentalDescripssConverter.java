/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.ExperimentalDescrips;
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
@XmlRootElement(name = "experimentalDescripss")
public class ExperimentalDescripssConverter {
    private Collection<ExperimentalDescrips> entities;
    private Collection<ExperimentalDescripsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ExperimentalDescripssConverter */
    public ExperimentalDescripssConverter() {
    }

    /**
     * Creates a new instance of ExperimentalDescripssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ExperimentalDescripssConverter(Collection<ExperimentalDescrips> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getExperimentalDescrips();
    }

    /**
     * Returns a collection of ExperimentalDescripsConverter.
     *
     * @return a collection of ExperimentalDescripsConverter
     */
    @XmlElement
    public Collection<ExperimentalDescripsConverter> getExperimentalDescrips() {
        if (items == null) {
            items = new ArrayList<ExperimentalDescripsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (ExperimentalDescrips entity : entities) {
                items.add(new ExperimentalDescripsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ExperimentalDescripsConverter.
     *
     * @param a collection of ExperimentalDescripsConverter to set
     */
    public void setExperimentalDescrips(Collection<ExperimentalDescripsConverter> items) {
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
     * Returns a collection ExperimentalDescrips entities.
     *
     * @return a collection of ExperimentalDescrips entities
     */
    @XmlTransient
    public Collection<ExperimentalDescrips> getEntities() {
        entities = new ArrayList<ExperimentalDescrips>();
        if (items != null) {
            for (ExperimentalDescripsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExperimentalDescripssConverter)) {
            return false;
        }
        ExperimentalDescripssConverter other = (ExperimentalDescripssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<ExperimentalDescripsConverter> itemSet = new HashSet<ExperimentalDescripsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (ExperimentalDescripsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
