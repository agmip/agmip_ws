/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Plantings;
import beans.PlantingsPK;
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
@XmlRootElement(name = "plantings")
public class PlantingsConverter {
    private Plantings entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of PlantingsConverter */
    public PlantingsConverter() {
        entity = new Plantings();
    }

    /**
     * Creates a new instance of PlantingsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public PlantingsConverter(Plantings entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getPlantingsPK().getExpId() + "," + entity.getPlantingsPK().getPl() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of PlantingsConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PlantingsConverter(Plantings entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for plantingsPK.
     *
     * @return value for plantingsPK
     */
    @XmlElement
    public PlantingsPK getPlantingsPK() {
        return (expandLevel > 0) ? entity.getPlantingsPK() : null;
    }

    /**
     * Setter for plantingsPK.
     *
     * @param value the value to set
     */
    public void setPlantingsPK(PlantingsPK value) {
        entity.setPlantingsPK(value);
    }

    /**
     * Getter for pdate.
     *
     * @return value for pdate
     */
    @XmlElement
    public Date getPdate() {
        return (expandLevel > 0) ? entity.getPdate() : null;
    }

    /**
     * Setter for pdate.
     *
     * @param value the value to set
     */
    public void setPdate(Date value) {
        entity.setPdate(value);
    }

    /**
     * Getter for pldae.
     *
     * @return value for pldae
     */
    @XmlElement
    public Date getPldae() {
        return (expandLevel > 0) ? entity.getPldae() : null;
    }

    /**
     * Setter for pldae.
     *
     * @param value the value to set
     */
    public void setPldae(Date value) {
        entity.setPldae(value);
    }

    /**
     * Getter for plpop.
     *
     * @return value for plpop
     */
    @XmlElement
    public Float getPlpop() {
        return (expandLevel > 0) ? entity.getPlpop() : null;
    }

    /**
     * Setter for plpop.
     *
     * @param value the value to set
     */
    public void setPlpop(Float value) {
        entity.setPlpop(value);
    }

    /**
     * Getter for plpoe.
     *
     * @return value for plpoe
     */
    @XmlElement
    public Float getPlpoe() {
        return (expandLevel > 0) ? entity.getPlpoe() : null;
    }

    /**
     * Setter for plpoe.
     *
     * @param value the value to set
     */
    public void setPlpoe(Float value) {
        entity.setPlpoe(value);
    }

    /**
     * Getter for plma.
     *
     * @return value for plma
     */
    @XmlElement
    public String getPlma() {
        return (expandLevel > 0) ? entity.getPlma() : null;
    }

    /**
     * Setter for plma.
     *
     * @param value the value to set
     */
    public void setPlma(String value) {
        entity.setPlma(value);
    }

    /**
     * Getter for plds.
     *
     * @return value for plds
     */
    @XmlElement
    public String getPlds() {
        return (expandLevel > 0) ? entity.getPlds() : null;
    }

    /**
     * Setter for plds.
     *
     * @param value the value to set
     */
    public void setPlds(String value) {
        entity.setPlds(value);
    }

    /**
     * Getter for plrs.
     *
     * @return value for plrs
     */
    @XmlElement
    public Float getPlrs() {
        return (expandLevel > 0) ? entity.getPlrs() : null;
    }

    /**
     * Setter for plrs.
     *
     * @param value the value to set
     */
    public void setPlrs(Float value) {
        entity.setPlrs(value);
    }

    /**
     * Getter for plrd.
     *
     * @return value for plrd
     */
    @XmlElement
    public Float getPlrd() {
        return (expandLevel > 0) ? entity.getPlrd() : null;
    }

    /**
     * Setter for plrd.
     *
     * @param value the value to set
     */
    public void setPlrd(Float value) {
        entity.setPlrd(value);
    }

    /**
     * Getter for plph.
     *
     * @return value for plph
     */
    @XmlElement
    public Integer getPlph() {
        return (expandLevel > 0) ? entity.getPlph() : null;
    }

    /**
     * Setter for plph.
     *
     * @param value the value to set
     */
    public void setPlph(Integer value) {
        entity.setPlph(value);
    }

    /**
     * Getter for pldp.
     *
     * @return value for pldp
     */
    @XmlElement
    public Float getPldp() {
        return (expandLevel > 0) ? entity.getPldp() : null;
    }

    /**
     * Setter for pldp.
     *
     * @param value the value to set
     */
    public void setPldp(Float value) {
        entity.setPldp(value);
    }

    /**
     * Getter for plmwt.
     *
     * @return value for plmwt
     */
    @XmlElement
    public Float getPlmwt() {
        return (expandLevel > 0) ? entity.getPlmwt() : null;
    }

    /**
     * Setter for plmwt.
     *
     * @param value the value to set
     */
    public void setPlmwt(Float value) {
        entity.setPlmwt(value);
    }

    /**
     * Getter for plage.
     *
     * @return value for plage
     */
    @XmlElement
    public Float getPlage() {
        return (expandLevel > 0) ? entity.getPlage() : null;
    }

    /**
     * Setter for plage.
     *
     * @param value the value to set
     */
    public void setPlage(Float value) {
        entity.setPlage(value);
    }

    /**
     * Getter for plenv.
     *
     * @return value for plenv
     */
    @XmlElement
    public Float getPlenv() {
        return (expandLevel > 0) ? entity.getPlenv() : null;
    }

    /**
     * Setter for plenv.
     *
     * @param value the value to set
     */
    public void setPlenv(Float value) {
        entity.setPlenv(value);
    }

    /**
     * Getter for plspl.
     *
     * @return value for plspl
     */
    @XmlElement
    public Float getPlspl() {
        return (expandLevel > 0) ? entity.getPlspl() : null;
    }

    /**
     * Setter for plspl.
     *
     * @param value the value to set
     */
    public void setPlspl(Float value) {
        entity.setPlspl(value);
    }

    /**
     * Getter for plgpct.
     *
     * @return value for plgpct
     */
    @XmlElement
    public Float getPlgpct() {
        return (expandLevel > 0) ? entity.getPlgpct() : null;
    }

    /**
     * Setter for plgpct.
     *
     * @param value the value to set
     */
    public void setPlgpct(Float value) {
        entity.setPlgpct(value);
    }

    /**
     * Getter for plmsource.
     *
     * @return value for plmsource
     */
    @XmlElement
    public String getPlmsource() {
        return (expandLevel > 0) ? entity.getPlmsource() : null;
    }

    /**
     * Setter for plmsource.
     *
     * @param value the value to set
     */
    public void setPlmsource(String value) {
        entity.setPlmsource(value);
    }

    /**
     * Getter for page.
     *
     * @return value for page
     */
    @XmlElement
    public Float getPage() {
        return (expandLevel > 0) ? entity.getPage() : null;
    }

    /**
     * Setter for page.
     *
     * @param value the value to set
     */
    public void setPage(Float value) {
        entity.setPage(value);
    }

    /**
     * Getter for penv.
     *
     * @return value for penv
     */
    @XmlElement
    public Float getPenv() {
        return (expandLevel > 0) ? entity.getPenv() : null;
    }

    /**
     * Setter for penv.
     *
     * @param value the value to set
     */
    public void setPenv(Float value) {
        entity.setPenv(value);
    }

    /**
     * Getter for pltln.
     *
     * @return value for pltln
     */
    @XmlElement
    public Float getPltln() {
        return (expandLevel > 0) ? entity.getPltln() : null;
    }

    /**
     * Setter for pltln.
     *
     * @param value the value to set
     */
    public void setPltln(Float value) {
        entity.setPltln(value);
    }

    /**
     * Getter for pltod.
     *
     * @return value for pltod
     */
    @XmlElement
    public Float getPltod() {
        return (expandLevel > 0) ? entity.getPltod() : null;
    }

    /**
     * Setter for pltod.
     *
     * @param value the value to set
     */
    public void setPltod(Float value) {
        entity.setPltod(value);
    }

    /**
     * Getter for pltor.
     *
     * @return value for pltor
     */
    @XmlElement
    public Float getPltor() {
        return (expandLevel > 0) ? entity.getPltor() : null;
    }

    /**
     * Setter for pltor.
     *
     * @param value the value to set
     */
    public void setPltor(Float value) {
        entity.setPltor(value);
    }

    /**
     * Getter for pltrno.
     *
     * @return value for pltrno
     */
    @XmlElement
    public Integer getPltrno() {
        return (expandLevel > 0) ? entity.getPltrno() : null;
    }

    /**
     * Setter for pltrno.
     *
     * @param value the value to set
     */
    public void setPltrno(Integer value) {
        entity.setPltrno(value);
    }

    /**
     * Getter for pltsp.
     *
     * @return value for pltsp
     */
    @XmlElement
    public Float getPltsp() {
        return (expandLevel > 0) ? entity.getPltsp() : null;
    }

    /**
     * Setter for pltsp.
     *
     * @param value the value to set
     */
    public void setPltsp(Float value) {
        entity.setPltsp(value);
    }

    /**
     * Getter for plName.
     *
     * @return value for plName
     */
    @XmlElement
    public String getPlName() {
        return (expandLevel > 0) ? entity.getPlName() : null;
    }

    /**
     * Setter for plName.
     *
     * @param value the value to set
     */
    public void setPlName(String value) {
        entity.setPlName(value);
    }

    /**
     * Getter for plNotes.
     *
     * @return value for plNotes
     */
    @XmlElement
    public String getPlNotes() {
        return (expandLevel > 0) ? entity.getPlNotes() : null;
    }

    /**
     * Setter for plNotes.
     *
     * @param value the value to set
     */
    public void setPlNotes(String value) {
        entity.setPlNotes(value);
    }

    /**
     * Getter for plme.
     *
     * @return value for plme
     */
    @XmlElement
    public String getPlme() {
        return (expandLevel > 0) ? entity.getPlme() : null;
    }

    /**
     * Setter for plme.
     *
     * @param value the value to set
     */
    public void setPlme(String value) {
        entity.setPlme(value);
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
     * Returns the Plantings entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Plantings getEntity() {
        if (entity.getPlantingsPK() == null) {
            PlantingsConverter converter = UriResolver.getInstance().resolve(PlantingsConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Plantings entity.
     *
     * @return an resolved entity
     */
    public Plantings resolveEntity(EntityManager em) {
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
        if (!(object instanceof PlantingsConverter)) {
            return false;
        }
        PlantingsConverter other = (PlantingsConverter) object;
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
