/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.SoilAnalysesLevels;
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
@XmlRootElement(name = "soilAnalysesLevelss")
public class SoilAnalysesLevelssConverter {
    private Collection<SoilAnalysesLevels> entities;
    private Collection<SoilAnalysesLevelsConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of SoilAnalysesLevelssConverter */
    public SoilAnalysesLevelssConverter() {
    }

    /**
     * Creates a new instance of SoilAnalysesLevelssConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public SoilAnalysesLevelssConverter(Collection<SoilAnalysesLevels> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getSoilAnalysesLevels();
    }

    /**
     * Returns a collection of SoilAnalysesLevelsConverter.
     *
     * @return a collection of SoilAnalysesLevelsConverter
     */
    @XmlElement
    public Collection<SoilAnalysesLevelsConverter> getSoilAnalysesLevels() {
        if (items == null) {
            items = new ArrayList<SoilAnalysesLevelsConverter>();
        }
        if (entities != null) {
            items.clear();
            for (SoilAnalysesLevels entity : entities) {
                items.add(new SoilAnalysesLevelsConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of SoilAnalysesLevelsConverter.
     *
     * @param a collection of SoilAnalysesLevelsConverter to set
     */
    public void setSoilAnalysesLevels(Collection<SoilAnalysesLevelsConverter> items) {
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
     * Returns a collection SoilAnalysesLevels entities.
     *
     * @return a collection of SoilAnalysesLevels entities
     */
    @XmlTransient
    public Collection<SoilAnalysesLevels> getEntities() {
        entities = new ArrayList<SoilAnalysesLevels>();
        if (items != null) {
            for (SoilAnalysesLevelsConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SoilAnalysesLevelssConverter)) {
            return false;
        }
        SoilAnalysesLevelssConverter other = (SoilAnalysesLevelssConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<SoilAnalysesLevelsConverter> itemSet = new HashSet<SoilAnalysesLevelsConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (SoilAnalysesLevelsConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
