/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.ChemicalLevels;
import beans.ChemicalLevelsPK;
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
@XmlRootElement(name = "chemicalLevels")
public class ChemicalLevelsConverter {
    private ChemicalLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ChemicalLevelsConverter */
    public ChemicalLevelsConverter() {
        entity = new ChemicalLevels();
    }

    /**
     * Creates a new instance of ChemicalLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ChemicalLevelsConverter(ChemicalLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getChemicalLevelsPK().getExpId() + "," + entity.getChemicalLevelsPK().getCh() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of ChemicalLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ChemicalLevelsConverter(ChemicalLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for chemicalLevelsPK.
     *
     * @return value for chemicalLevelsPK
     */
    @XmlElement
    public ChemicalLevelsPK getChemicalLevelsPK() {
        return (expandLevel > 0) ? entity.getChemicalLevelsPK() : null;
    }

    /**
     * Setter for chemicalLevelsPK.
     *
     * @param value the value to set
     */
    public void setChemicalLevelsPK(ChemicalLevelsPK value) {
        entity.setChemicalLevelsPK(value);
    }

    /**
     * Getter for chName.
     *
     * @return value for chName
     */
    @XmlElement
    public String getChName() {
        return (expandLevel > 0) ? entity.getChName() : null;
    }

    /**
     * Setter for chName.
     *
     * @param value the value to set
     */
    public void setChName(String value) {
        entity.setChName(value);
    }

    /**
     * Getter for chNotes.
     *
     * @return value for chNotes
     */
    @XmlElement
    public String getChNotes() {
        return (expandLevel > 0) ? entity.getChNotes() : null;
    }

    /**
     * Setter for chNotes.
     *
     * @param value the value to set
     */
    public void setChNotes(String value) {
        entity.setChNotes(value);
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
     * Returns the ChemicalLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public ChemicalLevels getEntity() {
        if (entity.getChemicalLevelsPK() == null) {
            ChemicalLevelsConverter converter = UriResolver.getInstance().resolve(ChemicalLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved ChemicalLevels entity.
     *
     * @return an resolved entity
     */
    public ChemicalLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof ChemicalLevelsConverter)) {
            return false;
        }
        ChemicalLevelsConverter other = (ChemicalLevelsConverter) object;
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
