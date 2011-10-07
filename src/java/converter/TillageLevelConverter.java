package converter;

import beans.TillageLevel;
import beans.TillageLevelPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import java.util.Collection;

/**
 *
 * @author wpavan
 */
@XmlRootElement(name = "tillageLevel")
public class TillageLevelConverter {
    private TillageLevel entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of TillageLevelConverter */
    public TillageLevelConverter() {
        entity = new TillageLevel();
    }

    /**
     * Creates a new instance of TillageLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public TillageLevelConverter(TillageLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getTillageLevelPK().getExpId() + "," + entity.getTillageLevelPK().getTi() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of TillageLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public TillageLevelConverter(TillageLevel entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for tillageLevelsPK.
     *
     * @return value for tillageLevelsPK
     */
    @XmlElement
    public TillageLevelPK getTillageLevelPK() {
        return (expandLevel > 0) ? entity.getTillageLevelPK() : null;
    }

    /**
     * Setter for tillageLevelsPK.
     *
     * @param value the value to set
     */
    public void setTillageLevelPK(TillageLevelPK value) {
        entity.setTillageLevelPK(value);
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
    public TreatmentsConverter getTreatmentsCollection() {
        if (expandLevel > 0) {
            if (entity.getTreatmentsCollection() != null) {
                return new TreatmentsConverter(entity.getTreatmentsCollection(), uri.resolve("treatmentsCollection/"), expandLevel - 1);
            }
        }
        return null;
    }

    /**
     * Setter for treatmentsCollection.
     *
     * @param value the value to set
     */
    public void setTreatmentsCollection(TreatmentsConverter value) {
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
     * Returns the TillageLevel entity.
     *
     * @return an entity
     */
    @XmlTransient
    public TillageLevel getEntity() {
        if (entity.getTillageLevelPK() == null) {
            TillageLevelConverter converter = UriResolver.getInstance().resolve(TillageLevelConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved TillageLevel entity.
     *
     * @return an resolved entity
     */
    public TillageLevel resolveEntity(EntityManager em) {
        Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
        Collection<Treatment> newtreatmentsCollection = new java.util.ArrayList<Treatment>();
        if (treatmentsCollection != null) {
            for (Treatment item : treatmentsCollection) {
                newtreatmentsCollection.add(em.getReference(Treatment.class, item.getTreatmentPK()));
            }
        }
        entity.setTreatmentsCollection(newtreatmentsCollection);
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TillageLevelConverter)) {
            return false;
        }
        TillageLevelConverter other = (TillageLevelConverter) object;
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
