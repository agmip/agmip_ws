package converter;

import beans.Treatment;
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
@XmlRootElement(name = "treatments")
public class TreatmentsConverter {
    private Collection<Treatment> entities;
    private Collection<TreatmentConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TreatmentsConverter */
    public TreatmentsConverter() {
    }

    /**
     * Creates a new instance of TreatmentsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TreatmentsConverter(Collection<Treatment> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getTreatment();
    }

    /**
     * Returns a collection of TreatmentConverter.
     *
     * @return a collection of TreatmentConverter
     */
    @XmlElement
    public Collection<TreatmentConverter> getTreatment() {
        if (items == null) {
            items = new ArrayList<TreatmentConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Treatment entity : entities) {
                items.add(new TreatmentConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of TreatmentConverter.
     *
     * @param a collection of TreatmentConverter to set
     */
    public void setTreatments(Collection<TreatmentConverter> items) {
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
     * Returns a collection Treatment entities.
     *
     * @return a collection of Treatment entities
     */
    @XmlTransient
    public Collection<Treatment> getEntities() {
        entities = new ArrayList<Treatment>();
        if (items != null) {
            for (TreatmentConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TreatmentsConverter)) {
            return false;
        }
        TreatmentsConverter other = (TreatmentsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<TreatmentConverter> itemSet = new HashSet<TreatmentConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (TreatmentConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
