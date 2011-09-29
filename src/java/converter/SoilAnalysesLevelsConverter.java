/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.SoilAnalysesLevels;
import beans.SoilAnalysesLevelsPK;
import java.net.URI;
import java.util.Date;
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
@XmlRootElement(name = "soilAnalysesLevels")
public class SoilAnalysesLevelsConverter {
    private SoilAnalysesLevels entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of SoilAnalysesLevelsConverter */
    public SoilAnalysesLevelsConverter() {
        entity = new SoilAnalysesLevels();
    }

    /**
     * Creates a new instance of SoilAnalysesLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public SoilAnalysesLevelsConverter(SoilAnalysesLevels entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getSoilAnalysesLevelsPK().getExpId() + "," + entity.getSoilAnalysesLevelsPK().getSa() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of SoilAnalysesLevelsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public SoilAnalysesLevelsConverter(SoilAnalysesLevels entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for soilAnalysesLevelsPK.
     *
     * @return value for soilAnalysesLevelsPK
     */
    @XmlElement
    public SoilAnalysesLevelsPK getSoilAnalysesLevelsPK() {
        return (expandLevel > 0) ? entity.getSoilAnalysesLevelsPK() : null;
    }

    /**
     * Setter for soilAnalysesLevelsPK.
     *
     * @param value the value to set
     */
    public void setSoilAnalysesLevelsPK(SoilAnalysesLevelsPK value) {
        entity.setSoilAnalysesLevelsPK(value);
    }

    /**
     * Getter for sadat.
     *
     * @return value for sadat
     */
    @XmlElement
    public Date getSadat() {
        return (expandLevel > 0) ? entity.getSadat() : null;
    }

    /**
     * Setter for sadat.
     *
     * @param value the value to set
     */
    public void setSadat(Date value) {
        entity.setSadat(value);
    }

    /**
     * Getter for nrcsfam.
     *
     * @return value for nrcsfam
     */
    @XmlElement
    public String getNrcsfam() {
        return (expandLevel > 0) ? entity.getNrcsfam() : null;
    }

    /**
     * Setter for nrcsfam.
     *
     * @param value the value to set
     */
    public void setNrcsfam(String value) {
        entity.setNrcsfam(value);
    }

    /**
     * Getter for samhb.
     *
     * @return value for samhb
     */
    @XmlElement
    public String getSamhb() {
        return (expandLevel > 0) ? entity.getSamhb() : null;
    }

    /**
     * Setter for samhb.
     *
     * @param value the value to set
     */
    public void setSamhb(String value) {
        entity.setSamhb(value);
    }

    /**
     * Getter for sampx.
     *
     * @return value for sampx
     */
    @XmlElement
    public String getSampx() {
        return (expandLevel > 0) ? entity.getSampx() : null;
    }

    /**
     * Setter for sampx.
     *
     * @param value the value to set
     */
    public void setSampx(String value) {
        entity.setSampx(value);
    }

    /**
     * Getter for samke.
     *
     * @return value for samke
     */
    @XmlElement
    public String getSamke() {
        return (expandLevel > 0) ? entity.getSamke() : null;
    }

    /**
     * Setter for samke.
     *
     * @param value the value to set
     */
    public void setSamke(String value) {
        entity.setSamke(value);
    }

    /**
     * Getter for sadr.
     *
     * @return value for sadr
     */
    @XmlElement
    public Float getSadr() {
        return (expandLevel > 0) ? entity.getSadr() : null;
    }

    /**
     * Setter for sadr.
     *
     * @param value the value to set
     */
    public void setSadr(Float value) {
        entity.setSadr(value);
    }

    /**
     * Getter for saro.
     *
     * @return value for saro
     */
    @XmlElement
    public Float getSaro() {
        return (expandLevel > 0) ? entity.getSaro() : null;
    }

    /**
     * Setter for saro.
     *
     * @param value the value to set
     */
    public void setSaro(Float value) {
        entity.setSaro(value);
    }

    /**
     * Getter for saName.
     *
     * @return value for saName
     */
    @XmlElement
    public String getSaName() {
        return (expandLevel > 0) ? entity.getSaName() : null;
    }

    /**
     * Setter for saName.
     *
     * @param value the value to set
     */
    public void setSaName(String value) {
        entity.setSaName(value);
    }

    /**
     * Getter for saNotes.
     *
     * @return value for saNotes
     */
    @XmlElement
    public String getSaNotes() {
        return (expandLevel > 0) ? entity.getSaNotes() : null;
    }

    /**
     * Setter for saNotes.
     *
     * @param value the value to set
     */
    public void setSaNotes(String value) {
        entity.setSaNotes(value);
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
     * Returns the SoilAnalysesLevels entity.
     *
     * @return an entity
     */
    @XmlTransient
    public SoilAnalysesLevels getEntity() {
        if (entity.getSoilAnalysesLevelsPK() == null) {
            SoilAnalysesLevelsConverter converter = UriResolver.getInstance().resolve(SoilAnalysesLevelsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved SoilAnalysesLevels entity.
     *
     * @return an resolved entity
     */
    public SoilAnalysesLevels resolveEntity(EntityManager em) {
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
        if (!(object instanceof SoilAnalysesLevelsConverter)) {
            return false;
        }
        SoilAnalysesLevelsConverter other = (SoilAnalysesLevelsConverter) object;
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
