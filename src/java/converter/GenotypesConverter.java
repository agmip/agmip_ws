package converter;

import beans.Genotype;
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
@XmlRootElement(name = "genotypes")
public class GenotypesConverter {
    private Collection<Genotype> entities;
    private Collection<GenotypeConverter> items;
    private URI uri;
    private int expandLevel;

    /** Creates a new instance of GenotypesConverter */
    public GenotypesConverter() {
    }

    /**
     * Creates a new instance of GenotypesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public GenotypesConverter(Collection<Genotype> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getGenotype();
    }

    /**
     * Returns a collection of GenotypeConverter.
     *
     * @return a collection of GenotypeConverter
     */
    @XmlElement
    public Collection<GenotypeConverter> getGenotype() {
        if (items == null) {
            items = new ArrayList<GenotypeConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Genotype entity : entities) {
                items.add(new GenotypeConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of GenotypeConverter.
     *
     * @param a collection of GenotypeConverter to set
     */
    public void setGenotype(Collection<GenotypeConverter> items) {
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
     * Returns a collection Genotype entities.
     *
     * @return a collection of Genotype entities
     */
    @XmlTransient
    public Collection<Genotype> getEntities() {
        entities = new ArrayList<Genotype>();
        if (items != null) {
            for (GenotypeConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GenotypesConverter)) {
            return false;
        }
        GenotypesConverter other = (GenotypesConverter) object;
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        if (this.expandLevel != other.expandLevel) {
            return false;
        }
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<GenotypeConverter> itemSet = new HashSet<GenotypeConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = uri == null ? 0 : uri.hashCode();
        hash = 37 * hash + expandLevel;
        for (GenotypeConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
