package converter;

import beans.Genotype;
import beans.GenotypePK;
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
 * @author fonini
 */
@XmlRootElement(name = "genotype")
public class GenotypeConverter {
    private Genotype entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of GenotypeConverter */
    public GenotypeConverter() {
        entity = new Genotype();
    }

    /**
     * Creates a new instance of GenotypeConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public GenotypeConverter(Genotype entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getGenotypePK().getExpId() + "," + entity.getGenotypePK().getGe() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of GenotypeConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public GenotypeConverter(Genotype entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for genotypesPK.
     *
     * @return value for genotypePK
     */
    @XmlElement
    public GenotypePK getGenotypePK() {
        return (expandLevel > 0) ? entity.getGenotypePK() : null;
    }

    /**
     * Setter for genotypePK.
     *
     * @param value the value to set
     */
    public void setGenotypePK(GenotypePK value) {
        entity.setGenotypePK(value);
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
     * Returns the Genotype entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Genotype getEntity() {
        if (entity.getGenotypePK() == null) {
            GenotypeConverter converter = UriResolver.getInstance().resolve(GenotypeConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Genotype entity.
     *
     * @return an resolved entity
     */
    public Genotype resolveEntity(EntityManager em) {
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
        if (!(object instanceof GenotypeConverter)) {
            return false;
        }
        GenotypeConverter other = (GenotypeConverter) object;
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
