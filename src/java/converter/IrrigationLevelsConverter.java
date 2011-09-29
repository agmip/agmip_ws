/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.IrrigationLevels;
import beans.IrrigationLevelsPK;
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
@XmlRootElement(name = "irrigationLevels")
public class IrrigationLevelsConverter {
    private IrrigationLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of IrrigationLevelsConverter */
    public IrrigationLevelsConverter() {
        entity = new IrrigationLevels();
    }

    /**
     * Creates a new instance of IrrigationLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public IrrigationLevelsConverter(IrrigationLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getIrrigationLevelsPK().getExpId() + "," + entity.getIrrigationLevelsPK().getIr() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of IrrigationLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public IrrigationLevelsConverter(IrrigationLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for irrigationLevelsPK.
     *
     * @return value for irrigationLevelsPK
     */
    @XmlElement
    public IrrigationLevelsPK getIrrigationLevelsPK() {
        return (expandLevel > 0) ? entity.getIrrigationLevelsPK() : null;
    }

    /**
     * Setter for irrigationLevelsPK.
     *
     * @param value the value to set
     */
    public void setIrrigationLevelsPK(IrrigationLevelsPK value) {
        entity.setIrrigationLevelsPK(value);
    }

    /**
     * Getter for iame.
     *
     * @return value for iame
     */
    @XmlElement
    public String getIame() {
        return (expandLevel > 0) ? entity.getIame() : null;
    }

    /**
     * Setter for iame.
     *
     * @param value the value to set
     */
    public void setIame(String value) {
        entity.setIame(value);
    }

    /**
     * Getter for iamt.
     *
     * @return value for iamt
     */
    @XmlElement
    public Float getIamt() {
        return (expandLevel > 0) ? entity.getIamt() : null;
    }

    /**
     * Setter for iamt.
     *
     * @param value the value to set
     */
    public void setIamt(Float value) {
        entity.setIamt(value);
    }

    /**
     * Getter for ireff.
     *
     * @return value for ireff
     */
    @XmlElement
    public Float getIreff() {
        return (expandLevel > 0) ? entity.getIreff() : null;
    }

    /**
     * Setter for ireff.
     *
     * @param value the value to set
     */
    public void setIreff(Float value) {
        entity.setIreff(value);
    }

    /**
     * Getter for irstg.
     *
     * @return value for irstg
     */
    @XmlElement
    public String getIrstg() {
        return (expandLevel > 0) ? entity.getIrstg() : null;
    }

    /**
     * Setter for irstg.
     *
     * @param value the value to set
     */
    public void setIrstg(String value) {
        entity.setIrstg(value);
    }

    /**
     * Getter for irmdp.
     *
     * @return value for irmdp
     */
    @XmlElement
    public Float getIrmdp() {
        return (expandLevel > 0) ? entity.getIrmdp() : null;
    }

    /**
     * Setter for irmdp.
     *
     * @param value the value to set
     */
    public void setIrmdp(Float value) {
        entity.setIrmdp(value);
    }

    /**
     * Getter for irthr.
     *
     * @return value for irthr
     */
    @XmlElement
    public Float getIrthr() {
        return (expandLevel > 0) ? entity.getIrthr() : null;
    }

    /**
     * Setter for irthr.
     *
     * @param value the value to set
     */
    public void setIrthr(Float value) {
        entity.setIrthr(value);
    }

    /**
     * Getter for irept.
     *
     * @return value for irept
     */
    @XmlElement
    public Float getIrept() {
        return (expandLevel > 0) ? entity.getIrept() : null;
    }

    /**
     * Setter for irept.
     *
     * @param value the value to set
     */
    public void setIrept(Float value) {
        entity.setIrept(value);
    }

    /**
     * Getter for irName.
     *
     * @return value for irName
     */
    @XmlElement
    public String getIrName() {
        return (expandLevel > 0) ? entity.getIrName() : null;
    }

    /**
     * Setter for irName.
     *
     * @param value the value to set
     */
    public void setIrName(String value) {
        entity.setIrName(value);
    }

    /**
     * Getter for irNotes.
     *
     * @return value for irNotes
     */
    @XmlElement
    public String getIrNotes() {
        return (expandLevel > 0) ? entity.getIrNotes() : null;
    }

    /**
     * Setter for irNotes.
     *
     * @param value the value to set
     */
    public void setIrNotes(String value) {
        entity.setIrNotes(value);
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
     * Returns the IrrigationLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public IrrigationLevels getEntity() {
        if (entity.getIrrigationLevelsPK() == null) {
            IrrigationLevelsConverter converter = UriResolver.getInstance().resolve(IrrigationLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved IrrigationLevels entity.
     *
     * @return an resolved entity
     */
    public IrrigationLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof IrrigationLevelsConverter)) {
            return false;
        }
        IrrigationLevelsConverter other = (IrrigationLevelsConverter) object;
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
