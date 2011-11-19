package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "experimental_descrips")
@XmlRootElement
public class Experiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private Integer expId;
    @Size(max = 255)
    @Column(name = "exname")
    private String exname;
    @Size(max = 255)
    @Column(name = "local_name")
    private String localName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "people")
    private String people;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "institutes")
    private String institutes;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "contacts")
    private String contacts;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "site")
    private String site;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "publications")
    private String publications;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "distribution")
    private String distribution;
    @Size(max = 255)
    @Column(name = "version")
    private String version;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "design")
    private String design;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "layout")
    private String layout;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "methods")
    private String methods;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objectives")
    private String objectives;
    @Size(max = 255)
    @Column(name = "factors")
    private String factors;
    @Size(max = 255)
    @Column(name = "main_factor")
    private String mainFactor;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "play")
    private String play;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "notes")
    private String notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiment", fetch = FetchType.EAGER)
    private Collection<Treatment> treatmentsCollection;

    public Experiment() {
    }

    public Experiment(Integer expId) {
        this.expId = expId;
    }

    public Integer getExpId() {
        return expId;
    }

    public void setExpId(Integer expId) {
        this.expId = expId;
    }

    public String getExname() {
        return exname;
    }

    public void setExname(String exname) {
        this.exname = exname;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getInstitutes() {
        return institutes;
    }

    public void setInstitutes(String institutes) {
        this.institutes = institutes;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getMainFactor() {
        return mainFactor;
    }

    public void setMainFactor(String mainFactor) {
        this.mainFactor = mainFactor;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expId != null ? expId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Experiment)) {
            return false;
        }
        Experiment other = (Experiment) object;
        if ((this.expId == null && other.expId != null) || (this.expId != null && !this.expId.equals(other.expId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Experiment[ expId=" + expId + " ]";
    }
}
