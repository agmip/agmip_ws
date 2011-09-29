/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Genotypes;
import beans.GenotypesPK;
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
@XmlRootElement(name = "genotypes")
public class GenotypesConverter {
    private Genotypes entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of GenotypesConverter */
    public GenotypesConverter() {
        entity = new Genotypes();
    }

    /**
     * Creates a new instance of GenotypesConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public GenotypesConverter(Genotypes entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getGenotypesPK().getExpId() + "," + entity.getGenotypesPK().getGe() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of GenotypesConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public GenotypesConverter(Genotypes entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for genotypesPK.
     *
     * @return value for genotypesPK
     */
    @XmlElement
    public GenotypesPK getGenotypesPK() {
        return (expandLevel > 0) ? entity.getGenotypesPK() : null;
    }

    /**
     * Setter for genotypesPK.
     *
     * @param value the value to set
     */
    public void setGenotypesPK(GenotypesPK value) {
        entity.setGenotypesPK(value);
    }

    /**
     * Getter for crid.
     *
     * @return value for crid
     */
    @XmlElement
    public Integer getCrid() {
        return (expandLevel > 0) ? entity.getCrid() : null;
    }

    /**
     * Setter for crid.
     *
     * @param value the value to set
     */
    public void setCrid(Integer value) {
        entity.setCrid(value);
    }

    /**
     * Getter for cr.
     *
     * @return value for cr
     */
    @XmlElement
    public String getCr() {
        return (expandLevel > 0) ? entity.getCr() : null;
    }

    /**
     * Setter for cr.
     *
     * @param value the value to set
     */
    public void setCr(String value) {
        entity.setCr(value);
    }

    /**
     * Getter for culId.
     *
     * @return value for culId
     */
    @XmlElement
    public String getCulId() {
        return (expandLevel > 0) ? entity.getCulId() : null;
    }

    /**
     * Setter for culId.
     *
     * @param value the value to set
     */
    public void setCulId(String value) {
        entity.setCulId(value);
    }

    /**
     * Getter for culName.
     *
     * @return value for culName
     */
    @XmlElement
    public String getCulName() {
        return (expandLevel > 0) ? entity.getCulName() : null;
    }

    /**
     * Setter for culName.
     *
     * @param value the value to set
     */
    public void setCulName(String value) {
        entity.setCulName(value);
    }

    /**
     * Getter for culNotes.
     *
     * @return value for culNotes
     */
    @XmlElement
    public String getCulNotes() {
        return (expandLevel > 0) ? entity.getCulNotes() : null;
    }

    /**
     * Setter for culNotes.
     *
     * @param value the value to set
     */
    public void setCulNotes(String value) {
        entity.setCulNotes(value);
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
     * Returns the Genotypes entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Genotypes getEntity() {
        if (entity.getGenotypesPK() == null) {
            GenotypesConverter converter = UriResolver.getInstance().resolve(GenotypesConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Genotypes entity.
     *
     * @return an resolved entity
     */
    public Genotypes resolveEntity(EntityManager em) {
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
        if (!(object instanceof GenotypesConverter)) {
            return false;
        }
        GenotypesConverter other = (GenotypesConverter) object;
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
