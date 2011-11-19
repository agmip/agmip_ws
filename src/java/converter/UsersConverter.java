package converter;

import beans.User;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author wpavan
 */
@XmlRootElement(name = "users")
public class UsersConverter {
    private Collection<User> entities;
    private Collection<UserConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of UsersConverter */
    public UsersConverter() {
    }

    /**
     * Creates a new instance of UsersConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UsersConverter(Collection<User> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getUsers();
    }

    /**
     * Returns a collection of UserConverter.
     *
     * @return a collection of UserConverter
     */
    @XmlElement
    public Collection<UserConverter> getUsers() {
        if (items == null) {
            items = new ArrayList<UserConverter>();
        }
        if (entities != null) {
            items.clear();
            for (User entity : entities) {
                items.add(new UserConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of UserConverter.
     *
     * @param a collection of UserConverter to set
     */
    public void setUsers(Collection<UserConverter> items) {
        this.items = items;
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
     * Returns a collection User entities.
     *
     * @return a collection of User entities
     */
    @XmlTransient
    public Collection<User> getEntities() {
        entities = new ArrayList<User>();
        if (items != null) {
            for (UserConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
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
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<UserConverter> itemSet = new HashSet<UserConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (UserConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
