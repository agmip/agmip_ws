package converter;

import beans.MulchLevel;
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
@XmlRootElement(name = "mulchLevels")
public class MulchLevelsConverter {
    private Collection<MulchLevel> entities;
    private Collection<MulchLevelConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of MulchLevelsConverter */
    public MulchLevelsConverter() {
    }

    /**
     * Creates a new instance of MulchLevelsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MulchLevelsConverter(Collection<MulchLevel> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getMulchLevels();
    }

    /**
     * Returns a collection of MulchLevelConverter.
     *
     * @return a collection of MulchLevelConverter
     */
    @XmlElement
    public Collection<MulchLevelConverter> getMulchLevels() {
        if (items == null) {
            items = new ArrayList<MulchLevelConverter>();
        }
        if (entities != null) {
            items.clear();
            for (MulchLevel entity : entities) {
                items.add(new MulchLevelConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of MulchLevelConverter.
     *
     * @param a collection of MulchLevelConverter to set
     */
    public void setMulchLevels(Collection<MulchLevelConverter> items) {
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
     * Returns a collection MulchLevel entities.
     *
     * @return a collection of MulchLevel entities
     */
    @XmlTransient
    public Collection<MulchLevel> getEntities() {
        entities = new ArrayList<MulchLevel>();
        if (items != null) {
            for (MulchLevelConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MulchLevelsConverter)) {
            return false;
        }
        MulchLevelsConverter other = (MulchLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<MulchLevelConverter> itemSet = new HashSet<MulchLevelConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (MulchLevelConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
