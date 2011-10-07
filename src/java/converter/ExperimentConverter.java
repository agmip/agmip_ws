package converter;

import beans.Experiment;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.Treatment;
import beans.TreatmentPK;
import java.util.Collection;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "experiment")
public class ExperimentConverter {
    private Experiment entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ExperimentConverter */
    public ExperimentConverter() {
        entity = new Experiment();
    }

    /**
     * Creates a new instance of ExperimentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ExperimentConverter(Experiment entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getExpId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();

		System.out.println(entity.getTreatmentsCollection().size());
    }

    /**
     * Creates a new instance of ExperimentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ExperimentConverter(Experiment entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for expId.
     *
     * @return value for expId
     */
    @XmlElement
    public Integer getExpId() {
        return (expandLevel > 0) ? entity.getExpId() : null;
    }

    /**
     * Setter for expId.
     *
     * @param value the value to set
     */
    public void setExpId(Integer value) {
        entity.setExpId(value);
    }

    /**
     * Getter for exname.
     *
     * @return value for exname
     */
    @XmlElement
    public String getExname() {
        return (expandLevel > 0) ? entity.getExname() : null;
    }

    /**
     * Setter for exname.
     *
     * @param value the value to set
     */
    public void setExname(String value) {
        entity.setExname(value);
    }

    /**
     * Getter for localName.
     *
     * @return value for localName
     */
    @XmlElement
    public String getLocalName() {
        return (expandLevel > 0) ? entity.getLocalName() : null;
    }

    /**
     * Setter for localName.
     *
     * @param value the value to set
     */
    public void setLocalName(String value) {
        entity.setLocalName(value);
    }

    /**
     * Getter for people.
     *
     * @return value for people
     */
    @XmlElement
    public String getPeople() {
        return (expandLevel > 0) ? entity.getPeople() : null;
    }

    /**
     * Setter for people.
     *
     * @param value the value to set
     */
    public void setPeople(String value) {
        entity.setPeople(value);
    }

    /**
     * Getter for institutes.
     *
     * @return value for institutes
     */
    @XmlElement
    public String getInstitutes() {
        return (expandLevel > 0) ? entity.getInstitutes() : null;
    }

    /**
     * Setter for institutes.
     *
     * @param value the value to set
     */
    public void setInstitutes(String value) {
        entity.setInstitutes(value);
    }

    /**
     * Getter for contacts.
     *
     * @return value for contacts
     */
    @XmlElement
    public String getContacts() {
        return (expandLevel > 0) ? entity.getContacts() : null;
    }

    /**
     * Setter for contacts.
     *
     * @param value the value to set
     */
    public void setContacts(String value) {
        entity.setContacts(value);
    }

    /**
     * Getter for address.
     *
     * @return value for address
     */
    @XmlElement
    public String getAddress() {
        return (expandLevel > 0) ? entity.getAddress() : null;
    }

    /**
     * Setter for address.
     *
     * @param value the value to set
     */
    public void setAddress(String value) {
        entity.setAddress(value);
    }

    /**
     * Getter for site.
     *
     * @return value for site
     */
    @XmlElement
    public String getSite() {
        return (expandLevel > 0) ? entity.getSite() : null;
    }

    /**
     * Setter for site.
     *
     * @param value the value to set
     */
    public void setSite(String value) {
        entity.setSite(value);
    }

    /**
     * Getter for publications.
     *
     * @return value for publications
     */
    @XmlElement
    public String getPublications() {
        return (expandLevel > 0) ? entity.getPublications() : null;
    }

    /**
     * Setter for publications.
     *
     * @param value the value to set
     */
    public void setPublications(String value) {
        entity.setPublications(value);
    }

    /**
     * Getter for distribution.
     *
     * @return value for distribution
     */
    @XmlElement
    public String getDistribution() {
        return (expandLevel > 0) ? entity.getDistribution() : null;
    }

    /**
     * Setter for distribution.
     *
     * @param value the value to set
     */
    public void setDistribution(String value) {
        entity.setDistribution(value);
    }

    /**
     * Getter for version.
     *
     * @return value for version
     */
    @XmlElement
    public String getVersion() {
        return (expandLevel > 0) ? entity.getVersion() : null;
    }

    /**
     * Setter for version.
     *
     * @param value the value to set
     */
    public void setVersion(String value) {
        entity.setVersion(value);
    }

    /**
     * Getter for design.
     *
     * @return value for design
     */
    @XmlElement
    public String getDesign() {
        return (expandLevel > 0) ? entity.getDesign() : null;
    }

    /**
     * Setter for design.
     *
     * @param value the value to set
     */
    public void setDesign(String value) {
        entity.setDesign(value);
    }

    /**
     * Getter for layout.
     *
     * @return value for layout
     */
    @XmlElement
    public String getLayout() {
        return (expandLevel > 0) ? entity.getLayout() : null;
    }

    /**
     * Setter for layout.
     *
     * @param value the value to set
     */
    public void setLayout(String value) {
        entity.setLayout(value);
    }

    /**
     * Getter for methods.
     *
     * @return value for methods
     */
    @XmlElement
    public String getMethods() {
        return (expandLevel > 0) ? entity.getMethods() : null;
    }

    /**
     * Setter for methods.
     *
     * @param value the value to set
     */
    public void setMethods(String value) {
        entity.setMethods(value);
    }

    /**
     * Getter for objectives.
     *
     * @return value for objectives
     */
    @XmlElement
    public String getObjectives() {
        return (expandLevel > 0) ? entity.getObjectives() : null;
    }

    /**
     * Setter for objectives.
     *
     * @param value the value to set
     */
    public void setObjectives(String value) {
        entity.setObjectives(value);
    }

    /**
     * Getter for factors.
     *
     * @return value for factors
     */
    @XmlElement
    public String getFactors() {
        return (expandLevel > 0) ? entity.getFactors() : null;
    }

    /**
     * Setter for factors.
     *
     * @param value the value to set
     */
    public void setFactors(String value) {
        entity.setFactors(value);
    }

    /**
     * Getter for mainFactor.
     *
     * @return value for mainFactor
     */
    @XmlElement
    public String getMainFactor() {
        return (expandLevel > 0) ? entity.getMainFactor() : null;
    }

    /**
     * Setter for mainFactor.
     *
     * @param value the value to set
     */
    public void setMainFactor(String value) {
        entity.setMainFactor(value);
    }

    /**
     * Getter for play.
     *
     * @return value for play
     */
    @XmlElement
    public String getPlay() {
        return (expandLevel > 0) ? entity.getPlay() : null;
    }

    /**
     * Setter for play.
     *
     * @param value the value to set
     */
    public void setPlay(String value) {
        entity.setPlay(value);
    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
    @XmlElement
    public String getNotes() {
        return (expandLevel > 0) ? entity.getNotes() : null;
    }

    /**
     * Setter for notes.
     *
     * @param value the value to set
     */
    public void setNotes(String value) {
        entity.setNotes(value);
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
     * Returns the Experiment entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Experiment getEntity() {
        if (entity.getExpId() == null) {
            ExperimentConverter converter = UriResolver.getInstance().resolve(ExperimentConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Experiment entity.
     *
     * @return an resolved entity
     */
    public Experiment resolveEntity(EntityManager em) {
        return entity;
    }

	public Experiment resolvePersistedEntity(EntityManager em, Integer id) {
        Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
        Collection<Treatment> newtreatmentsCollection = new java.util.ArrayList<Treatment>();


        if (treatmentsCollection != null) {
            for (Treatment item : treatmentsCollection) {
                //newtreatmentsCollection.add(em.getReference(Treatment.class, item.getTreatmentsPK()));
				TreatmentPK key = item.getTreatmentPK();
				key.setExpId(id);

				newtreatmentsCollection.add(em.getReference(Treatment.class, key));

				item.setTreatmentPK(key);

				em.persist(item);
            }
        }
        entity.setTreatmentsCollection(newtreatmentsCollection);
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExperimentConverter)) {
            return false;
        }
        ExperimentConverter other = (ExperimentConverter) object;
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
