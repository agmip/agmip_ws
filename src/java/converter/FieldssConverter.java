/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Fields;
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
@XmlRootElement(name = "fieldss")
public class FieldssConverter {
    private Collection<Fields> entities;
    private Collection<FieldsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of FieldssConverter */
    public FieldssConverter() {
    }

    /**
     * Creates a new instance of FieldssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public FieldssConverter(Collection<Fields> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getFields();
    }

    /**
     * Returns a collection of FieldsConverter.
     *
     * @return a collection of FieldsConverter
     */
    @XmlElement
    public Collection<FieldsConverter> getFields() {
        if (items == null) {
            items = new ArrayList<FieldsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Fields entity : entities) {
                items.add(new FieldsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of FieldsConverter.
     *
     * @param a collection of FieldsConverter to set
     */
    public void setFields(Collection<FieldsConverter> items) {
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
     * Returns a collection Fields entities.
     *
     * @return a collection of Fields entities
     */
    @XmlTransient
    public Collection<Fields> getEntities() {
        entities = new ArrayList<Fields>();
        if (items != null) {
            for (FieldsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FieldssConverter)) {
            return false;
        }
        FieldssConverter other = (FieldssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<FieldsConverter> itemSet = new HashSet<FieldsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (FieldsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
