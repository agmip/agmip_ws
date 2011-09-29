/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Fields;
import beans.FieldsPK;
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
@XmlRootElement(name = "fields")
public class FieldsConverter {
    private Fields entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of FieldsConverter */
    public FieldsConverter() {
        entity = new Fields();
    }

    /**
     * Creates a new instance of FieldsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public FieldsConverter(Fields entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getFieldsPK().getExpId() + "," + entity.getFieldsPK().getFl() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of FieldsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public FieldsConverter(Fields entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for fieldsPK.
     *
     * @return value for fieldsPK
     */
    @XmlElement
    public FieldsPK getFieldsPK() {
        return (expandLevel > 0) ? entity.getFieldsPK() : null;
    }

    /**
     * Setter for fieldsPK.
     *
     * @param value the value to set
     */
    public void setFieldsPK(FieldsPK value) {
        entity.setFieldsPK(value);
    }

    /**
     * Getter for sid.
     *
     * @return value for sid
     */
    @XmlElement
    public Integer getSid() {
        return (expandLevel > 0) ? entity.getSid() : null;
    }

    /**
     * Setter for sid.
     *
     * @param value the value to set
     */
    public void setSid(Integer value) {
        entity.setSid(value);
    }

    /**
     * Getter for flLoc.
     *
     * @return value for flLoc
     */
    @XmlElement
    public Float getFlLoc() {
        return (expandLevel > 0) ? entity.getFlLoc() : null;
    }

    /**
     * Setter for flLoc.
     *
     * @param value the value to set
     */
    public void setFlLoc(Float value) {
        entity.setFlLoc(value);
    }

    /**
     * Getter for flLat.
     *
     * @return value for flLat
     */
    @XmlElement
    public Float getFlLat() {
        return (expandLevel > 0) ? entity.getFlLat() : null;
    }

    /**
     * Setter for flLat.
     *
     * @param value the value to set
     */
    public void setFlLat(Float value) {
        entity.setFlLat(value);
    }

    /**
     * Getter for flLong.
     *
     * @return value for flLong
     */
    @XmlElement
    public Float getFlLong() {
        return (expandLevel > 0) ? entity.getFlLong() : null;
    }

    /**
     * Setter for flLong.
     *
     * @param value the value to set
     */
    public void setFlLong(Float value) {
        entity.setFlLong(value);
    }

    /**
     * Getter for flele.
     *
     * @return value for flele
     */
    @XmlElement
    public Float getFlele() {
        return (expandLevel > 0) ? entity.getFlele() : null;
    }

    /**
     * Setter for flele.
     *
     * @param value the value to set
     */
    public void setFlele(Float value) {
        entity.setFlele(value);
    }

    /**
     * Getter for flsl.
     *
     * @return value for flsl
     */
    @XmlElement
    public Float getFlsl() {
        return (expandLevel > 0) ? entity.getFlsl() : null;
    }

    /**
     * Setter for flsl.
     *
     * @param value the value to set
     */
    public void setFlsl(Float value) {
        entity.setFlsl(value);
    }

    /**
     * Getter for flsll.
     *
     * @return value for flsll
     */
    @XmlElement
    public Float getFlsll() {
        return (expandLevel > 0) ? entity.getFlsll() : null;
    }

    /**
     * Setter for flsll.
     *
     * @param value the value to set
     */
    public void setFlsll(Float value) {
        entity.setFlsll(value);
    }

    /**
     * Getter for flsla.
     *
     * @return value for flsla
     */
    @XmlElement
    public String getFlsla() {
        return (expandLevel > 0) ? entity.getFlsla() : null;
    }

    /**
     * Setter for flsla.
     *
     * @param value the value to set
     */
    public void setFlsla(String value) {
        entity.setFlsla(value);
    }

    /**
     * Getter for farea.
     *
     * @return value for farea
     */
    @XmlElement
    public Float getFarea() {
        return (expandLevel > 0) ? entity.getFarea() : null;
    }

    /**
     * Setter for farea.
     *
     * @param value the value to set
     */
    public void setFarea(Float value) {
        entity.setFarea(value);
    }

    /**
     * Getter for flob.
     *
     * @return value for flob
     */
    @XmlElement
    public Float getFlob() {
        return (expandLevel > 0) ? entity.getFlob() : null;
    }

    /**
     * Setter for flob.
     *
     * @param value the value to set
     */
    public void setFlob(Float value) {
        entity.setFlob(value);
    }

    /**
     * Getter for fllwr.
     *
     * @return value for fllwr
     */
    @XmlElement
    public Integer getFllwr() {
        return (expandLevel > 0) ? entity.getFllwr() : null;
    }

    /**
     * Setter for fllwr.
     *
     * @param value the value to set
     */
    public void setFllwr(Integer value) {
        entity.setFllwr(value);
    }

    /**
     * Getter for flPlowd.
     *
     * @return value for flPlowd
     */
    @XmlElement
    public Float getFlPlowd() {
        return (expandLevel > 0) ? entity.getFlPlowd() : null;
    }

    /**
     * Setter for flPlowd.
     *
     * @param value the value to set
     */
    public void setFlPlowd(Float value) {
        entity.setFlPlowd(value);
    }

    /**
     * Getter for flPlowdc.
     *
     * @return value for flPlowdc
     */
    @XmlElement
    public Integer getFlPlowdc() {
        return (expandLevel > 0) ? entity.getFlPlowdc() : null;
    }

    /**
     * Setter for flPlowdc.
     *
     * @param value the value to set
     */
    public void setFlPlowdc(Integer value) {
        entity.setFlPlowdc(value);
    }

    /**
     * Getter for flDrntype.
     *
     * @return value for flDrntype
     */
    @XmlElement
    public String getFlDrntype() {
        return (expandLevel > 0) ? entity.getFlDrntype() : null;
    }

    /**
     * Setter for flDrntype.
     *
     * @param value the value to set
     */
    public void setFlDrntype(String value) {
        entity.setFlDrntype(value);
    }

    /**
     * Getter for fldrd.
     *
     * @return value for fldrd
     */
    @XmlElement
    public Float getFldrd() {
        return (expandLevel > 0) ? entity.getFldrd() : null;
    }

    /**
     * Setter for fldrd.
     *
     * @param value the value to set
     */
    public void setFldrd(Float value) {
        entity.setFldrd(value);
    }

    /**
     * Getter for fldrs.
     *
     * @return value for fldrs
     */
    @XmlElement
    public Float getFldrs() {
        return (expandLevel > 0) ? entity.getFldrs() : null;
    }

    /**
     * Setter for fldrs.
     *
     * @param value the value to set
     */
    public void setFldrs(Float value) {
        entity.setFldrs(value);
    }

    /**
     * Getter for wid.
     *
     * @return value for wid
     */
    @XmlElement
    public Integer getWid() {
        return (expandLevel > 0) ? entity.getWid() : null;
    }

    /**
     * Setter for wid.
     *
     * @param value the value to set
     */
    public void setWid(Integer value) {
        entity.setWid(value);
    }

    /**
     * Getter for wthFile.
     *
     * @return value for wthFile
     */
    @XmlElement
    public String getWthFile() {
        return (expandLevel > 0) ? entity.getWthFile() : null;
    }

    /**
     * Setter for wthFile.
     *
     * @param value the value to set
     */
    public void setWthFile(String value) {
        entity.setWthFile(value);
    }

    /**
     * Getter for soilId.
     *
     * @return value for soilId
     */
    @XmlElement
    public String getSoilId() {
        return (expandLevel > 0) ? entity.getSoilId() : null;
    }

    /**
     * Setter for soilId.
     *
     * @param value the value to set
     */
    public void setSoilId(String value) {
        entity.setSoilId(value);
    }

    /**
     * Getter for soilFile.
     *
     * @return value for soilFile
     */
    @XmlElement
    public String getSoilFile() {
        return (expandLevel > 0) ? entity.getSoilFile() : null;
    }

    /**
     * Setter for soilFile.
     *
     * @param value the value to set
     */
    public void setSoilFile(String value) {
        entity.setSoilFile(value);
    }

    /**
     * Getter for sltx.
     *
     * @return value for sltx
     */
    @XmlElement
    public String getSltx() {
        return (expandLevel > 0) ? entity.getSltx() : null;
    }

    /**
     * Setter for sltx.
     *
     * @param value the value to set
     */
    public void setSltx(String value) {
        entity.setSltx(value);
    }

    /**
     * Getter for sldp.
     *
     * @return value for sldp
     */
    @XmlElement
    public Float getSldp() {
        return (expandLevel > 0) ? entity.getSldp() : null;
    }

    /**
     * Setter for sldp.
     *
     * @param value the value to set
     */
    public void setSldp(Float value) {
        entity.setSldp(value);
    }

    /**
     * Getter for dtwt.
     *
     * @return value for dtwt
     */
    @XmlElement
    public Float getDtwt() {
        return (expandLevel > 0) ? entity.getDtwt() : null;
    }

    /**
     * Setter for dtwt.
     *
     * @param value the value to set
     */
    public void setDtwt(Float value) {
        entity.setDtwt(value);
    }

    /**
     * Getter for idField.
     *
     * @return value for idField
     */
    @XmlElement
    public String getIdField() {
        return (expandLevel > 0) ? entity.getIdField() : null;
    }

    /**
     * Setter for idField.
     *
     * @param value the value to set
     */
    public void setIdField(String value) {
        entity.setIdField(value);
    }

    /**
     * Getter for wstaId.
     *
     * @return value for wstaId
     */
    @XmlElement
    public String getWstaId() {
        return (expandLevel > 0) ? entity.getWstaId() : null;
    }

    /**
     * Setter for wstaId.
     *
     * @param value the value to set
     */
    public void setWstaId(String value) {
        entity.setWstaId(value);
    }

    /**
     * Getter for flName.
     *
     * @return value for flName
     */
    @XmlElement
    public String getFlName() {
        return (expandLevel > 0) ? entity.getFlName() : null;
    }

    /**
     * Setter for flName.
     *
     * @param value the value to set
     */
    public void setFlName(String value) {
        entity.setFlName(value);
    }

    /**
     * Getter for flNotes.
     *
     * @return value for flNotes
     */
    @XmlElement
    public String getFlNotes() {
        return (expandLevel > 0) ? entity.getFlNotes() : null;
    }

    /**
     * Setter for flNotes.
     *
     * @param value the value to set
     */
    public void setFlNotes(String value) {
        entity.setFlNotes(value);
    }

    /**
     * Getter for flst.
     *
     * @return value for flst
     */
    @XmlElement
    public String getFlst() {
        return (expandLevel > 0) ? entity.getFlst() : null;
    }

    /**
     * Setter for flst.
     *
     * @param value the value to set
     */
    public void setFlst(String value) {
        entity.setFlst(value);
    }

    /**
     * Getter for flhst.
     *
     * @return value for flhst
     */
    @XmlElement
    public String getFlhst() {
        return (expandLevel > 0) ? entity.getFlhst() : null;
    }

    /**
     * Setter for flhst.
     *
     * @param value the value to set
     */
    public void setFlhst(String value) {
        entity.setFlhst(value);
    }

    /**
     * Getter for fhdur.
     *
     * @return value for fhdur
     */
    @XmlElement
    public String getFhdur() {
        return (expandLevel > 0) ? entity.getFhdur() : null;
    }

    /**
     * Setter for fhdur.
     *
     * @param value the value to set
     */
    public void setFhdur(String value) {
        entity.setFhdur(value);
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
     * Returns the Fields entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Fields getEntity() {
        if (entity.getFieldsPK() == null) {
            FieldsConverter converter = UriResolver.getInstance().resolve(FieldsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Fields entity.
     *
     * @return an resolved entity
     */
    public Fields resolveEntity(EntityManager em) {
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
        if (!(object instanceof FieldsConverter)) {
            return false;
        }
        FieldsConverter other = (FieldsConverter) object;
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
