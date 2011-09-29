/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.EnvironModifLevels;
import beans.EnvironModifLevelsPK;
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
@XmlRootElement(name = "environModifLevels")
public class EnvironModifLevelsConverter {
    private EnvironModifLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of EnvironModifLevelsConverter */
    public EnvironModifLevelsConverter() {
        entity = new EnvironModifLevels();
    }

    /**
     * Creates a new instance of EnvironModifLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public EnvironModifLevelsConverter(EnvironModifLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getEnvironModifLevelsPK().getExpId() + "," + entity.getEnvironModifLevelsPK().getEm() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of EnvironModifLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public EnvironModifLevelsConverter(EnvironModifLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for environModifLevelsPK.
     *
     * @return value for environModifLevelsPK
     */
    @XmlElement
    public EnvironModifLevelsPK getEnvironModifLevelsPK() {
        return (expandLevel > 0) ? entity.getEnvironModifLevelsPK() : null;
    }

    /**
     * Setter for environModifLevelsPK.
     *
     * @param value the value to set
     */
    public void setEnvironModifLevelsPK(EnvironModifLevelsPK value) {
        entity.setEnvironModifLevelsPK(value);
    }

    /**
     * Getter for emName.
     *
     * @return value for emName
     */
    @XmlElement
    public String getEmName() {
        return (expandLevel > 0) ? entity.getEmName() : null;
    }

    /**
     * Setter for emName.
     *
     * @param value the value to set
     */
    public void setEmName(String value) {
        entity.setEmName(value);
    }

    /**
     * Getter for emNotes.
     *
     * @return value for emNotes
     */
    @XmlElement
    public String getEmNotes() {
        return (expandLevel > 0) ? entity.getEmNotes() : null;
    }

    /**
     * Setter for emNotes.
     *
     * @param value the value to set
     */
    public void setEmNotes(String value) {
        entity.setEmNotes(value);
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
     * Returns the EnvironModifLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public EnvironModifLevels getEntity() {
        if (entity.getEnvironModifLevelsPK() == null) {
            EnvironModifLevelsConverter converter = UriResolver.getInstance().resolve(EnvironModifLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved EnvironModifLevels entity.
     *
     * @return an resolved entity
     */
    public EnvironModifLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof EnvironModifLevelsConverter)) {
            return false;
        }
        EnvironModifLevelsConverter other = (EnvironModifLevelsConverter) object;
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
