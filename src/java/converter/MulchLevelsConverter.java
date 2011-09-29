/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.MulchLevels;
import beans.MulchLevelsPK;
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
@XmlRootElement(name = "mulchLevels")
public class MulchLevelsConverter {
    private MulchLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of MulchLevelsConverter */
    public MulchLevelsConverter() {
        entity = new MulchLevels();
    }

    /**
     * Creates a new instance of MulchLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public MulchLevelsConverter(MulchLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getMulchLevelsPK().getExpId() + "," + entity.getMulchLevelsPK().getMl() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of MulchLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MulchLevelsConverter(MulchLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for mulchLevelsPK.
     *
     * @return value for mulchLevelsPK
     */
    @XmlElement
    public MulchLevelsPK getMulchLevelsPK() {
        return (expandLevel > 0) ? entity.getMulchLevelsPK() : null;
    }

    /**
     * Setter for mulchLevelsPK.
     *
     * @param value the value to set
     */
    public void setMulchLevelsPK(MulchLevelsPK value) {
        entity.setMulchLevelsPK(value);
    }

    /**
     * Getter for mlName.
     *
     * @return value for mlName
     */
    @XmlElement
    public String getMlName() {
        return (expandLevel > 0) ? entity.getMlName() : null;
    }

    /**
     * Setter for mlName.
     *
     * @param value the value to set
     */
    public void setMlName(String value) {
        entity.setMlName(value);
    }

    /**
     * Getter for mlNotes.
     *
     * @return value for mlNotes
     */
    @XmlElement
    public String getMlNotes() {
        return (expandLevel > 0) ? entity.getMlNotes() : null;
    }

    /**
     * Setter for mlNotes.
     *
     * @param value the value to set
     */
    public void setMlNotes(String value) {
        entity.setMlNotes(value);
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
     * Returns the MulchLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public MulchLevels getEntity() {
        if (entity.getMulchLevelsPK() == null) {
            MulchLevelsConverter converter = UriResolver.getInstance().resolve(MulchLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved MulchLevels entity.
     *
     * @return an resolved entity
     */
    public MulchLevels resolveEntity(EntityManager em) {
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
