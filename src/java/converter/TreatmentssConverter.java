/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Treatments;
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
@XmlRootElement(name = "treatmentss")
public class TreatmentssConverter {
    private Collection<Treatments> entities;
    private Collection<TreatmentsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TreatmentssConverter */
    public TreatmentssConverter() {
    }

    /**
     * Creates a new instance of TreatmentssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TreatmentssConverter(Collection<Treatments> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getTreatments();
    }

    /**
     * Returns a collection of TreatmentsConverter.
     *
     * @return a collection of TreatmentsConverter
     */
    @XmlElement
    public Collection<TreatmentsConverter> getTreatments() {
        if (items == null) {
            items = new ArrayList<TreatmentsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Treatments entity : entities) {
                items.add(new TreatmentsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of TreatmentsConverter.
     *
     * @param a collection of TreatmentsConverter to set
     */
    public void setTreatments(Collection<TreatmentsConverter> items) {
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
     * Returns a collection Treatments entities.
     *
     * @return a collection of Treatments entities
     */
    @XmlTransient
    public Collection<Treatments> getEntities() {
        entities = new ArrayList<Treatments>();
        if (items != null) {
            for (TreatmentsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TreatmentssConverter)) {
            return false;
        }
        TreatmentssConverter other = (TreatmentssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<TreatmentsConverter> itemSet = new HashSet<TreatmentsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (TreatmentsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
