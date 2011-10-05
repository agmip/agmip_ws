package converter;

import beans.ChemicalLevel;
import beans.ChemicalLevelPK;
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
@XmlRootElement(name = "chemicalLevel")
public class ChemicalLevelConverter {
    private ChemicalLevel entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ChemicalLevelConverter */
    public ChemicalLevelConverter() {
        entity = new ChemicalLevel();
    }

    /**
     * Creates a new instance of ChemicalLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ChemicalLevelConverter(ChemicalLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getChemicalLevelPK().getExpId() + "," + entity.getChemicalLevelPK().getCh() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of ChemicalLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ChemicalLevelConverter(ChemicalLevel entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for chemicalLevelsPK.
     *
     * @return value for chemicalLevelsPK
     */
    @XmlElement
    public ChemicalLevelPK getChemicalLevelsPK() {
        return (expandLevel > 0) ? entity.getChemicalLevelPK() : null;
    }

    /**
     * Setter for chemicalLevelsPK.
     *
     * @param value the value to set
     */
    public void setChemicalLevelsPK(ChemicalLevelPK value) {
        entity.setChemicalLevelPK(value);
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
     * Returns the ChemicalLevel entity.
     *
     * @return an entity
     */
    @XmlTransient
    public ChemicalLevel getEntity() {
        if (entity.getChemicalLevelPK() == null) {
            ChemicalLevelConverter converter = UriResolver.getInstance().resolve(ChemicalLevelConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved ChemicalLevel entity.
     *
     * @return an resolved entity
     */
    public ChemicalLevel resolveEntity(EntityManager em) {
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
        if (!(object instanceof ChemicalLevelConverter)) {
            return false;
        }
        ChemicalLevelConverter other = (ChemicalLevelConverter) object;
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
