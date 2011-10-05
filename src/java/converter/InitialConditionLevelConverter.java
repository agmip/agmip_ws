package converter;

import beans.InitialConditionLevel;
import beans.InitialConditionLevelPK;
import java.net.URI;
import java.util.Date;
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
@XmlRootElement(name = "initialConditionLevel")
public class InitialConditionLevelConverter {
    private InitialConditionLevel entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of InitialConditionLevelConverter */
    public InitialConditionLevelConverter() {
        entity = new InitialConditionLevel();
    }

    /**
     * Creates a new instance of InitialConditionLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public InitialConditionLevelConverter(InitialConditionLevel entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getInitialConditionLevelPK().getExpId() + "," + entity.getInitialConditionLevelPK().getIc() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of InitialConditionLevelConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public InitialConditionLevelConverter(InitialConditionLevel entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for initialConditionLevelPK.
     *
     * @return value for initialConditionLevelPK
     */
    @XmlElement
    public InitialConditionLevelPK getInitialConditionLevelPK() {
        return (expandLevel > 0) ? entity.getInitialConditionLevelPK() : null;
    }

    /**
     * Setter for initialConditionLevelPK.
     *
     * @param value the value to set
     */
    public void setInitialConditionLevelPK(InitialConditionLevelPK value) {
        entity.setInitialConditionLevelPK(value);
    }

    /**
     * Getter for icdat.
     *
     * @return value for icdat
     */
    @XmlElement
    public Date getIcdat() {
        return (expandLevel > 0) ? entity.getIcdat() : null;
    }

    /**
     * Setter for icdat.
     *
     * @param value the value to set
     */
    public void setIcdat(Date value) {
        entity.setIcdat(value);
    }

    /**
     * Getter for icyr.
     *
     * @return value for icyr
     */
    @XmlElement
    public Float getIcyr() {
        return (expandLevel > 0) ? entity.getIcyr() : null;
    }

    /**
     * Setter for icyr.
     *
     * @param value the value to set
     */
    public void setIcyr(Float value) {
        entity.setIcyr(value);
    }

    /**
     * Getter for icday.
     *
     * @return value for icday
     */
    @XmlElement
    public Integer getIcday() {
        return (expandLevel > 0) ? entity.getIcday() : null;
    }

    /**
     * Setter for icday.
     *
     * @param value the value to set
     */
    public void setIcday(Integer value) {
        entity.setIcday(value);
    }

    /**
     * Getter for icpcr.
     *
     * @return value for icpcr
     */
    @XmlElement
    public String getIcpcr() {
        return (expandLevel > 0) ? entity.getIcpcr() : null;
    }

    /**
     * Setter for icpcr.
     *
     * @param value the value to set
     */
    public void setIcpcr(String value) {
        entity.setIcpcr(value);
    }

    /**
     * Getter for icrdp.
     *
     * @return value for icrdp
     */
    @XmlElement
    public Float getIcrdp() {
        return (expandLevel > 0) ? entity.getIcrdp() : null;
    }

    /**
     * Setter for icrdp.
     *
     * @param value the value to set
     */
    public void setIcrdp(Float value) {
        entity.setIcrdp(value);
    }

    /**
     * Getter for icrip.
     *
     * @return value for icrip
     */
    @XmlElement
    public Float getIcrip() {
        return (expandLevel > 0) ? entity.getIcrip() : null;
    }

    /**
     * Setter for icrip.
     *
     * @param value the value to set
     */
    public void setIcrip(Float value) {
        entity.setIcrip(value);
    }

    /**
     * Getter for icrag.
     *
     * @return value for icrag
     */
    @XmlElement
    public Float getIcrag() {
        return (expandLevel > 0) ? entity.getIcrag() : null;
    }

    /**
     * Setter for icrag.
     *
     * @param value the value to set
     */
    public void setIcrag(Float value) {
        entity.setIcrag(value);
    }

    /**
     * Getter for icrn.
     *
     * @return value for icrn
     */
    @XmlElement
    public Float getIcrn() {
        return (expandLevel > 0) ? entity.getIcrn() : null;
    }

    /**
     * Setter for icrn.
     *
     * @param value the value to set
     */
    public void setIcrn(Float value) {
        entity.setIcrn(value);
    }

    /**
     * Getter for icrp.
     *
     * @return value for icrp
     */
    @XmlElement
    public Float getIcrp() {
        return (expandLevel > 0) ? entity.getIcrp() : null;
    }

    /**
     * Setter for icrp.
     *
     * @param value the value to set
     */
    public void setIcrp(Float value) {
        entity.setIcrp(value);
    }

    /**
     * Getter for icrk.
     *
     * @return value for icrk
     */
    @XmlElement
    public Float getIcrk() {
        return (expandLevel > 0) ? entity.getIcrk() : null;
    }

    /**
     * Setter for icrk.
     *
     * @param value the value to set
     */
    public void setIcrk(Float value) {
        entity.setIcrk(value);
    }

    /**
     * Getter for icrli.
     *
     * @return value for icrli
     */
    @XmlElement
    public Float getIcrli() {
        return (expandLevel > 0) ? entity.getIcrli() : null;
    }

    /**
     * Setter for icrli.
     *
     * @param value the value to set
     */
    public void setIcrli(Float value) {
        entity.setIcrli(value);
    }

    /**
     * Getter for icrt.
     *
     * @return value for icrt
     */
    @XmlElement
    public Float getIcrt() {
        return (expandLevel > 0) ? entity.getIcrt() : null;
    }

    /**
     * Setter for icrt.
     *
     * @param value the value to set
     */
    public void setIcrt(Float value) {
        entity.setIcrt(value);
    }

    /**
     * Getter for icnd.
     *
     * @return value for icnd
     */
    @XmlElement
    public Float getIcnd() {
        return (expandLevel > 0) ? entity.getIcnd() : null;
    }

    /**
     * Setter for icnd.
     *
     * @param value the value to set
     */
    public void setIcnd(Float value) {
        entity.setIcnd(value);
    }

    /**
     * Getter for icryr.
     *
     * @return value for icryr
     */
    @XmlElement
    public Float getIcryr() {
        return (expandLevel > 0) ? entity.getIcryr() : null;
    }

    /**
     * Setter for icryr.
     *
     * @param value the value to set
     */
    public void setIcryr(Float value) {
        entity.setIcryr(value);
    }

    /**
     * Getter for icrdy.
     *
     * @return value for icrdy
     */
    @XmlElement
    public Integer getIcrdy() {
        return (expandLevel > 0) ? entity.getIcrdy() : null;
    }

    /**
     * Setter for icrdy.
     *
     * @param value the value to set
     */
    public void setIcrdy(Integer value) {
        entity.setIcrdy(value);
    }

    /**
     * Getter for icwt.
     *
     * @return value for icwt
     */
    @XmlElement
    public Float getIcwt() {
        return (expandLevel > 0) ? entity.getIcwt() : null;
    }

    /**
     * Setter for icwt.
     *
     * @param value the value to set
     */
    public void setIcwt(Float value) {
        entity.setIcwt(value);
    }

    /**
     * Getter for icin.
     *
     * @return value for icin
     */
    @XmlElement
    public Float getIcin() {
        return (expandLevel > 0) ? entity.getIcin() : null;
    }

    /**
     * Setter for icin.
     *
     * @param value the value to set
     */
    public void setIcin(Float value) {
        entity.setIcin(value);
    }

    /**
     * Getter for icrzno.
     *
     * @return value for icrzno
     */
    @XmlElement
    public Float getIcrzno() {
        return (expandLevel > 0) ? entity.getIcrzno() : null;
    }

    /**
     * Setter for icrzno.
     *
     * @param value the value to set
     */
    public void setIcrzno(Float value) {
        entity.setIcrzno(value);
    }

    /**
     * Getter for icrze.
     *
     * @return value for icrze
     */
    @XmlElement
    public Integer getIcrze() {
        return (expandLevel > 0) ? entity.getIcrze() : null;
    }

    /**
     * Setter for icrze.
     *
     * @param value the value to set
     */
    public void setIcrze(Integer value) {
        entity.setIcrze(value);
    }

    /**
     * Getter for icrzn.
     *
     * @return value for icrzn
     */
    @XmlElement
    public Float getIcrzn() {
        return (expandLevel > 0) ? entity.getIcrzn() : null;
    }

    /**
     * Setter for icrzn.
     *
     * @param value the value to set
     */
    public void setIcrzn(Float value) {
        entity.setIcrzn(value);
    }

    /**
     * Getter for icName.
     *
     * @return value for icName
     */
    @XmlElement
    public String getIcName() {
        return (expandLevel > 0) ? entity.getIcName() : null;
    }

    /**
     * Setter for icName.
     *
     * @param value the value to set
     */
    public void setIcName(String value) {
        entity.setIcName(value);
    }

    /**
     * Getter for icNotes.
     *
     * @return value for icNotes
     */
    @XmlElement
    public String getIcNotes() {
        return (expandLevel > 0) ? entity.getIcNotes() : null;
    }

    /**
     * Setter for icNotes.
     *
     * @param value the value to set
     */
    public void setIcNotes(String value) {
        entity.setIcNotes(value);
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
     * Returns the InitialConditionLevel entity.
     *
     * @return an entity
     */
    @XmlTransient
    public InitialConditionLevel getEntity() {
        if (entity.getInitialConditionLevelPK() == null) {
            InitialConditionLevelConverter converter = UriResolver.getInstance().resolve(InitialConditionLevelConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved InitialConditionLevel entity.
     *
     * @return an resolved entity
     */
    public InitialConditionLevel resolveEntity(EntityManager em) {
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
        if (!(object instanceof InitialConditionLevelConverter)) {
            return false;
        }
        InitialConditionLevelConverter other = (InitialConditionLevelConverter) object;
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
