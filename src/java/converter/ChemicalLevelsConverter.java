package converter;

import beans.ChemicalLevel;
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
@XmlRootElement(name = "chemicalLevels")
public class ChemicalLevelsConverter {
    private Collection<ChemicalLevel> entities;
    private Collection<ChemicalLevelConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ChemicalLevelsConverter */
    public ChemicalLevelsConverter() {
    }

    /**
     * Creates a new instance of ChemicalLevelsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ChemicalLevelsConverter(Collection<ChemicalLevel> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getChemicalLevels();
    }

    /**
     * Returns a collection of ChemicalLevelConverter.
     *
     * @return a collection of ChemicalLevelConverter
     */
    @XmlElement
    public Collection<ChemicalLevelConverter> getChemicalLevels() {
        if (items == null) {
            items = new ArrayList<ChemicalLevelConverter>();
        }
        if (entities != null) {
            items.clear();
            for (ChemicalLevel entity : entities) {
                items.add(new ChemicalLevelConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ChemicalLevelConverter.
     *
     * @param a collection of ChemicalLevelConverter to set
     */
    public void setChemicalLevels(Collection<ChemicalLevelConverter> items) {
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
     * Returns a collection ChemicalLevel entities.
     *
     * @return a collection of ChemicalLevel entities
     */
    @XmlTransient
    public Collection<ChemicalLevel> getEntities() {
        entities = new ArrayList<ChemicalLevel>();
        if (items != null) {
            for (ChemicalLevelConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ChemicalLevelsConverter)) {
            return false;
        }
        ChemicalLevelsConverter other = (ChemicalLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<ChemicalLevelConverter> itemSet = new HashSet<ChemicalLevelConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (ChemicalLevelConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
