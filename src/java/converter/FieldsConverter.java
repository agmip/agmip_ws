package converter;

import beans.Field;
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
@XmlRootElement(name = "fields")
public class FieldsConverter {
    private Collection<Field> entities;
    private Collection<FieldConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of FieldsConverter */
    public FieldsConverter() {
    }

    /**
     * Creates a new instance of FieldsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public FieldsConverter(Collection<Field> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getField();
    }

    /**
     * Returns a collection of FieldConverter.
     *
     * @return a collection of FieldConverter
     */
    @XmlElement
    public Collection<FieldConverter> getField() {
        if (items == null) {
            items = new ArrayList<FieldConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Field entity : entities) {
                items.add(new FieldConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of FieldConverter.
     *
     * @param a collection of FieldConverter to set
     */
    public void setField(Collection<FieldConverter> items) {
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
     * Returns a collection Field entities.
     *
     * @return a collection of Field entities
     */
    @XmlTransient
    public Collection<Field> getEntities() {
        entities = new ArrayList<Field>();
        if (items != null) {
            for (FieldConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FieldsConverter)) {
            return false;
        }
        FieldsConverter other = (FieldsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<FieldConverter> itemSet = new HashSet<FieldConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (FieldConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
