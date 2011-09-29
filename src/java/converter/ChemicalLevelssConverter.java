/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.ChemicalLevels;
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
@XmlRootElement(name = "chemicalLevelss")
public class ChemicalLevelssConverter {
    private Collection<ChemicalLevels> entities;
    private Collection<ChemicalLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ChemicalLevelssConverter */
    public ChemicalLevelssConverter() {
    }

    /**
     * Creates a new instance of ChemicalLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ChemicalLevelssConverter(Collection<ChemicalLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getChemicalLevels();
    }

    /**
     * Returns a collection of ChemicalLevelsConverter.
     *
     * @return a collection of ChemicalLevelsConverter
     */
    @XmlElement
    public Collection<ChemicalLevelsConverter> getChemicalLevels() {
        if (items == null) {
            items = new ArrayList<ChemicalLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (ChemicalLevels entity : entities) {
                items.add(new ChemicalLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ChemicalLevelsConverter.
     *
     * @param a collection of ChemicalLevelsConverter to set
     */
    public void setChemicalLevels(Collection<ChemicalLevelsConverter> items) {
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
     * Returns a collection ChemicalLevels entities.
     *
     * @return a collection of ChemicalLevels entities
     */
    @XmlTransient
    public Collection<ChemicalLevels> getEntities() {
        entities = new ArrayList<ChemicalLevels>();
        if (items != null) {
            for (ChemicalLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ChemicalLevelssConverter)) {
            return false;
        }
        ChemicalLevelssConverter other = (ChemicalLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<ChemicalLevelsConverter> itemSet = new HashSet<ChemicalLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (ChemicalLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
