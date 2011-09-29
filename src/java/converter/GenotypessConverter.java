/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Genotypes;
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
@XmlRootElement(name = "genotypess")
public class GenotypessConverter {
    private Collection<Genotypes> entities;
    private Collection<GenotypesConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of GenotypessConverter */
    public GenotypessConverter() {
    }

    /**
     * Creates a new instance of GenotypessConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public GenotypessConverter(Collection<Genotypes> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getGenotypes();
    }

    /**
     * Returns a collection of GenotypesConverter.
     *
     * @return a collection of GenotypesConverter
     */
    @XmlElement
    public Collection<GenotypesConverter> getGenotypes() {
        if (items == null) {
            items = new ArrayList<GenotypesConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Genotypes entity : entities) {
                items.add(new GenotypesConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of GenotypesConverter.
     *
     * @param a collection of GenotypesConverter to set
     */
    public void setGenotypes(Collection<GenotypesConverter> items) {
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
     * Returns a collection Genotypes entities.
     *
     * @return a collection of Genotypes entities
     */
    @XmlTransient
    public Collection<Genotypes> getEntities() {
        entities = new ArrayList<Genotypes>();
        if (items != null) {
            for (GenotypesConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GenotypessConverter)) {
            return false;
        }
        GenotypessConverter other = (GenotypessConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<GenotypesConverter> itemSet = new HashSet<GenotypesConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (GenotypesConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
