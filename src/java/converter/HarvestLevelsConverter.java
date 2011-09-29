/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.HarvestLevels;
import beans.HarvestLevelsPK;
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
@XmlRootElement(name = "harvestLevels")
public class HarvestLevelsConverter {
    private HarvestLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of HarvestLevelsConverter */
    public HarvestLevelsConverter() {
        entity = new HarvestLevels();
    }

    /**
     * Creates a new instance of HarvestLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public HarvestLevelsConverter(HarvestLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getHarvestLevelsPK().getExpId() + "," + entity.getHarvestLevelsPK().getHa() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of HarvestLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public HarvestLevelsConverter(HarvestLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for harvestLevelsPK.
     *
     * @return value for harvestLevelsPK
     */
    @XmlElement
    public HarvestLevelsPK getHarvestLevelsPK() {
        return (expandLevel > 0) ? entity.getHarvestLevelsPK() : null;
    }

    /**
     * Setter for harvestLevelsPK.
     *
     * @param value the value to set
     */
    public void setHarvestLevelsPK(HarvestLevelsPK value) {
        entity.setHarvestLevelsPK(value);
    }

    /**
     * Getter for haName.
     *
     * @return value for haName
     */
    @XmlElement
    public String getHaName() {
        return (expandLevel > 0) ? entity.getHaName() : null;
    }

    /**
     * Setter for haName.
     *
     * @param value the value to set
     */
    public void setHaName(String value) {
        entity.setHaName(value);
    }

    /**
     * Getter for haNotes.
     *
     * @return value for haNotes
     */
    @XmlElement
    public String getHaNotes() {
        return (expandLevel > 0) ? entity.getHaNotes() : null;
    }

    /**
     * Setter for haNotes.
     *
     * @param value the value to set
     */
    public void setHaNotes(String value) {
        entity.setHaNotes(value);
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
     * Returns the HarvestLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public HarvestLevels getEntity() {
        if (entity.getHarvestLevelsPK() == null) {
            HarvestLevelsConverter converter = UriResolver.getInstance().resolve(HarvestLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved HarvestLevels entity.
     *
     * @return an resolved entity
     */
    public HarvestLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof HarvestLevelsConverter)) {
            return false;
        }
        HarvestLevelsConverter other = (HarvestLevelsConverter) object;
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
