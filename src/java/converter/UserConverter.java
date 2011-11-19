package converter;

import beans.User;
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
@XmlRootElement(name = "user")
public class UserConverter {
    private User entity;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of UserConverter */
    public UserConverter() {
        entity = new User();
    }

    /**
     * Creates a new instance of UserConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public UserConverter(User entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getUserId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getTreatmentsCollection();
    }

    /**
     * Creates a new instance of UserConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UserConverter(User entity, URI uri, int expandLevel) {
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
     * Returns the User entity.
     *
     * @return an entity
     */
    @XmlTransient
    public User getEntity() {
        if (entity.getUserId() == null) {
            UserConverter converter = UriResolver.getInstance().resolve(UserConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved User entity.
     *
     * @return an resolved entity
     */
    public User resolveEntity(EntityManager em) {
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
        if (!(object instanceof UserConverter)) {
            return false;
        }
        UserConverter other = (UserConverter) object;
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
