package converter;

import beans.Experiment;
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
 * @author fonini
 */
@XmlRootElement(name = "experiments")
public class ExperimentsConverter {
    private Collection<Experiment> entities;
    private Collection<ExperimentConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of ExperimentsConverter */
    public ExperimentsConverter() {
    }

    /**
     * Creates a new instance of ExperimentsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ExperimentsConverter(Collection<Experiment> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getExperiment();
    }

    /**
     * Returns a collection of ExperimentConverter.
     *
     * @return a collection of ExperimentConverter
     */
    @XmlElement
    public Collection<ExperimentConverter> getExperiment() {
        if (items == null) {
            items = new ArrayList<ExperimentConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Experiment entity : entities) {
                items.add(new ExperimentConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ExperimentConverter.
     *
     * @param a collection of ExperimentConverter to set
     */
    public void setExperimentalDescrips(Collection<ExperimentConverter> items) {
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
     * Returns a collection Experiment entities.
     *
     * @return a collection of Experiment entities
     */
    @XmlTransient
    public Collection<Experiment> getEntities() {
        entities = new ArrayList<Experiment>();
        if (items != null) {
            for (ExperimentConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExperimentsConverter)) {
            return false;
        }
        ExperimentsConverter other = (ExperimentsConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<ExperimentConverter> itemSet = new HashSet<ExperimentConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (ExperimentConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
