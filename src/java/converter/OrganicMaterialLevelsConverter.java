/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.OrganicMaterialLevels;
import beans.OrganicMaterialLevelsPK;
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
@XmlRootElement(name = "organicMaterialLevels")
public class OrganicMaterialLevelsConverter {
    private OrganicMaterialLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of OrganicMaterialLevelsConverter */
    public OrganicMaterialLevelsConverter() {
        entity = new OrganicMaterialLevels();
    }

    /**
     * Creates a new instance of OrganicMaterialLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public OrganicMaterialLevelsConverter(OrganicMaterialLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getOrganicMaterialLevelsPK().getExpId() + "," + entity.getOrganicMaterialLevelsPK().getOm() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of OrganicMaterialLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public OrganicMaterialLevelsConverter(OrganicMaterialLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for organicMaterialLevelsPK.
     *
     * @return value for organicMaterialLevelsPK
     */
    @XmlElement
    public OrganicMaterialLevelsPK getOrganicMaterialLevelsPK() {
        return (expandLevel > 0) ? entity.getOrganicMaterialLevelsPK() : null;
    }

    /**
     * Setter for organicMaterialLevelsPK.
     *
     * @param value the value to set
     */
    public void setOrganicMaterialLevelsPK(OrganicMaterialLevelsPK value) {
        entity.setOrganicMaterialLevelsPK(value);
    }

    /**
     * Getter for omName.
     *
     * @return value for omName
     */
    @XmlElement
    public String getOmName() {
        return (expandLevel > 0) ? entity.getOmName() : null;
    }

    /**
     * Setter for omName.
     *
     * @param value the value to set
     */
    public void setOmName(String value) {
        entity.setOmName(value);
    }

    /**
     * Getter for omNotes.
     *
     * @return value for omNotes
     */
    @XmlElement
    public String getOmNotes() {
        return (expandLevel > 0) ? entity.getOmNotes() : null;
    }

    /**
     * Setter for omNotes.
     *
     * @param value the value to set
     */
    public void setOmNotes(String value) {
        entity.setOmNotes(value);
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
     * Returns the OrganicMaterialLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public OrganicMaterialLevels getEntity() {
        if (entity.getOrganicMaterialLevelsPK() == null) {
            OrganicMaterialLevelsConverter converter = UriResolver.getInstance().resolve(OrganicMaterialLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved OrganicMaterialLevels entity.
     *
     * @return an resolved entity
     */
    public OrganicMaterialLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof OrganicMaterialLevelsConverter)) {
            return false;
        }
        OrganicMaterialLevelsConverter other = (OrganicMaterialLevelsConverter) object;
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
