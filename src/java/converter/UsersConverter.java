/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Users;
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
@XmlRootElement(name = "users")
public class UsersConverter {
    private Users entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of UsersConverter */
    public UsersConverter() {
        entity = new Users();
    }

    /**
     * Creates a new instance of UsersConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public UsersConverter(Users entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getUserId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of UsersConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UsersConverter(Users entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for userId.
     *
     * @return value for userId
     */
    @XmlElement
    public Integer getUserId() {
        return (expandLevel > 0) ? entity.getUserId() : null;
    }

    /**
     * Setter for userId.
     *
     * @param value the value to set
     */
    public void setUserId(Integer value) {
        entity.setUserId(value);
    }

    /**
     * Getter for firstName.
     *
     * @return value for firstName
     */
    @XmlElement
    public String getFirstName() {
        return (expandLevel > 0) ? entity.getFirstName() : null;
    }

    /**
     * Setter for firstName.
     *
     * @param value the value to set
     */
    public void setFirstName(String value) {
        entity.setFirstName(value);
    }

    /**
     * Getter for lastName.
     *
     * @return value for lastName
     */
    @XmlElement
    public String getLastName() {
        return (expandLevel > 0) ? entity.getLastName() : null;
    }

    /**
     * Setter for lastName.
     *
     * @param value the value to set
     */
    public void setLastName(String value) {
        entity.setLastName(value);
    }

    /**
     * Getter for email.
     *
     * @return value for email
     */
    @XmlElement
    public String getEmail() {
        return (expandLevel > 0) ? entity.getEmail() : null;
    }

    /**
     * Setter for email.
     *
     * @param value the value to set
     */
    public void setEmail(String value) {
        entity.setEmail(value);
    }

    /**
     * Getter for password.
     *
     * @return value for password
     */
    @XmlElement
    public String getPassword() {
        return (expandLevel > 0) ? entity.getPassword() : null;
    }

    /**
     * Setter for password.
     *
     * @param value the value to set
     */
    public void setPassword(String value) {
        entity.setPassword(value);
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
     * Returns the Users entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Users getEntity() {
        if (entity.getUserId() == null) {
            UsersConverter converter = UriResolver.getInstance().resolve(UsersConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Users entity.
     *
     * @return an resolved entity
     */
    public Users resolveEntity(EntityManager em) {
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
        if (!(object instanceof UsersConverter)) {
            return false;
        }
        UsersConverter other = (UsersConverter) object;
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
