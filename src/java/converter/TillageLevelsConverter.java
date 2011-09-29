/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.TillageLevels;
import beans.TillageLevelsPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatments;
import java.util.Collection;

/**
 *
 * @author wpavan
 */
@XmlRootElement(name = "tillageLevels")
public class TillageLevelsConverter {
    private TillageLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TillageLevelsConverter */
    public TillageLevelsConverter() {
        entity = new TillageLevels();
    }

    /**
     * Creates a new instance of TillageLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public TillageLevelsConverter(TillageLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getTillageLevelsPK().getExpId() + "," + entity.getTillageLevelsPK().getTi() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of TillageLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TillageLevelsConverter(TillageLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for tillageLevelsPK.
     *
     * @return value for tillageLevelsPK
     */
    @XmlElement
    public TillageLevelsPK getTillageLevelsPK() {
        return (expandLevel > 0) ? entity.getTillageLevelsPK() : null;
    }

    /**
     * Setter for tillageLevelsPK.
     *
     * @param value the value to set
     */
    public void setTillageLevelsPK(TillageLevelsPK value) {
        entity.setTillageLevelsPK(value);
    }

    /**
     * Getter for tiName.
     *
     * @return value for tiName
     */
    @XmlElement
    public String getTiName() {
        return (expandLevel > 0) ? entity.getTiName() : null;
    }

    /**
     * Setter for tiName.
     *
     * @param value the value to set
     */
    public void setTiName(String value) {
        entity.setTiName(value);
    }

    /**
     * Getter for tiNotes.
     *
     * @return value for tiNotes
     */
    @XmlElement
    public String getTiNotes() {
        return (expandLevel > 0) ? entity.getTiNotes() : null;
    }

    /**
     * Setter for tiNotes.
     *
     * @param value the value to set
     */
    public void setTiNotes(String value) {
        entity.setTiNotes(value);
    }

    /**
     * Getter for treatmentsCollection.
     *
     * @return value for treatmentsCollection
     */
    @XmlElement
    public TreatmentssConverter getTreatmentsCollection() {
        if (expandLevel > 0) {
            if (entity.getTreatmentsCollection() != null) {
                return new TreatmentssConverter(entity.getTreatmentsCollection(), uri.resolve("treatmentsCollection/"), expandLevel - 1);
            }
        }
        return null;
    }

    /**
     * Setter for treatmentsCollection.
     *
     * @param value the value to set
     */
    public void setTreatmentsCollection(TreatmentssConverter value) {
        entity.setTreatmentsCollection((value != null) ? value.getEntities() : null);
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
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the TillageLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public TillageLevels getEntity() {
        if (entity.getTillageLevelsPK() == null) {
            TillageLevelsConverter converter = UriResolver.getInstance().resolve(TillageLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved TillageLevels entity.
     *
     * @return an resolved entity
     */
    public TillageLevels resolveEntity(EntityManager em) {
        Collection<Treatments> treatmentsCollection = entity.getTreatmentsCollection();
        Collection<Treatments> newtreatmentsCollection = new java.util.ArrayList<Treatments>();
        if (treatmentsCollection != null) {
            for (Treatments item : treatmentsCollection) {
                newtreatmentsCollection.add(em.getReference(Treatments.class, item.getTreatmentsPK()));
            }
        }
        entity.setTreatmentsCollection(newtreatmentsCollection);
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TillageLevelsConverter)) {
            return false;
        }
        TillageLevelsConverter other = (TillageLevelsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (expandLevel <= 0) {
            return true;
        }
        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        if (expandLevel <= 0) {
            return hash + 37 * expandLevel;
        }
        return hash + 37 * (expandLevel + 37 * (entity == null ? 0 : entity.hashCode()));
    }
}
