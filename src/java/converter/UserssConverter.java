/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.Users;
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
@XmlRootElement(name = "userss")
public class UserssConverter {
    private Collection<Users> entities;
    private Collection<UsersConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of UserssConverter */
    public UserssConverter() {
    }

    /**
     * Creates a new instance of UserssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UserssConverter(Collection<Users> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getUsers();
    }

    /**
     * Returns a collection of UsersConverter.
     *
     * @return a collection of UsersConverter
     */
    @XmlElement
    public Collection<UsersConverter> getUsers() {
        if (items == null) {
            items = new ArrayList<UsersConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Users entity : entities) {
                items.add(new UsersConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of UsersConverter.
     *
     * @param a collection of UsersConverter to set
     */
    public void setUsers(Collection<UsersConverter> items) {
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
     * Returns a collection Users entities.
     *
     * @return a collection of Users entities
     */
    @XmlTransient
    public Collection<Users> getEntities() {
        entities = new ArrayList<Users>();
        if (items != null) {
            for (UsersConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserssConverter)) {
            return false;
        }
        UserssConverter other = (UserssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<UsersConverter> itemSet = new HashSet<UsersConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (UsersConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
