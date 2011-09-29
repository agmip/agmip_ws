/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.FertilizerLevels;
import beans.FertilizerLevelsPK;
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
@XmlRootElement(name = "fertilizerLevels")
public class FertilizerLevelsConverter {
    private FertilizerLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of FertilizerLevelsConverter */
    public FertilizerLevelsConverter() {
        entity = new FertilizerLevels();
    }

    /**
     * Creates a new instance of FertilizerLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public FertilizerLevelsConverter(FertilizerLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getFertilizerLevelsPK().getExpId() + "," + entity.getFertilizerLevelsPK().getFe() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of FertilizerLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public FertilizerLevelsConverter(FertilizerLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for fertilizerLevelsPK.
     *
     * @return value for fertilizerLevelsPK
     */
    @XmlElement
    public FertilizerLevelsPK getFertilizerLevelsPK() {
        return (expandLevel > 0) ? entity.getFertilizerLevelsPK() : null;
    }

    /**
     * Setter for fertilizerLevelsPK.
     *
     * @param value the value to set
     */
    public void setFertilizerLevelsPK(FertilizerLevelsPK value) {
        entity.setFertilizerLevelsPK(value);
    }

    /**
     * Getter for feName.
     *
     * @return value for feName
     */
    @XmlElement
    public String getFeName() {
        return (expandLevel > 0) ? entity.getFeName() : null;
    }

    /**
     * Setter for feName.
     *
     * @param value the value to set
     */
    public void setFeName(String value) {
        entity.setFeName(value);
    }

    /**
     * Getter for feComments.
     *
     * @return value for feComments
     */
    @XmlElement
    public String getFeComments() {
        return (expandLevel > 0) ? entity.getFeComments() : null;
    }

    /**
     * Setter for feComments.
     *
     * @param value the value to set
     */
    public void setFeComments(String value) {
        entity.setFeComments(value);
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
     * Returns the FertilizerLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public FertilizerLevels getEntity() {
        if (entity.getFertilizerLevelsPK() == null) {
            FertilizerLevelsConverter converter = UriResolver.getInstance().resolve(FertilizerLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved FertilizerLevels entity.
     *
     * @return an resolved entity
     */
    public FertilizerLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof FertilizerLevelsConverter)) {
            return false;
        }
        FertilizerLevelsConverter other = (FertilizerLevelsConverter) object;
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
