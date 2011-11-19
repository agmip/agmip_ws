package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "soil_profiles")
@XmlRootElement
public class SoilProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
	private Integer sid;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "people", length = 2147483647)
	private String people;
	@Size(max = 255)
    @Column(name = "soil_id", length = 255)
	private String soilId;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "institutes", length = 2147483647)
	private String institutes;
	@Size(max = 255)
    @Column(name = "soil_file_id", length = 255)
	private String soilFileId;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "contacts", length = 2147483647)
	private String contacts;
	@Size(max = 255)
    @Column(name = "fd_name", length = 255)
	private String fdName;
	@Size(max = 255)
    @Column(name = "address", length = 255)
	private String address;
	@Size(max = 255)
    @Column(name = "site", length = 255)
	private String site;
	@Column(name = "soillat", precision = 12)
	private Float soillat;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "publications", length = 2147483647)
	private String publications;
	@Column(name = "soillong", precision = 12)
	private Float soillong;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "distribution", length = 2147483647)
	private String distribution;
	@Column(name = "sand", precision = 12)
	private Float sand;
	@Column(name = "soilelev", precision = 12)
	private Float soilelev;
	@Column(name = "slope", precision = 12)
	private Float slope;
	@Size(max = 255)
    @Column(name = "version", length = 255)
	private String version;
	@Size(max = 255)
    @Column(name = "classifcation", length = 255)
	private String classifcation;
	@Size(max = 255)
    @Column(name = "sl_system", length = 255)
	private String slSystem;
	@Column(name = "salb", precision = 12)
	private Float salb;
	@Column(name = "slu1", precision = 12)
	private Float slu1;
	@Column(name = "sldr", precision = 12)
	private Float sldr;
	@Column(name = "slro", precision = 12)
	private Float slro;
	@Size(max = 255)
    @Column(name = "sscol", length = 255)
	private String sscol;
	@Column(name = "flst", precision = 12)
	private Float flst;
	@Column(name = "slnf", precision = 12)
	private Float slnf;
	@Size(max = 255)
    @Column(name = "smhb", length = 255)
	private String smhb;
	@Size(max = 255)
    @Column(name = "smke", length = 255)
	private String smke;
	@Size(max = 255)
    @Column(name = "smpx", length = 255)
	private String smpx;
	@Lob
    @Size(max = 2147483647)
    @Column(name = "notes", length = 2147483647)
	private String notes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "soilProfile", fetch= FetchType.EAGER)
	private Collection<SoilProfileLayer> soilProfileLayerCollection;

	public SoilProfile() {
	}

	public SoilProfile(Integer sid) {
		this.sid = sid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getSoilId() {
		return soilId;
	}

	public void setSoilId(String soilId) {
		this.soilId = soilId;
	}

	public String getInstitutes() {
		return institutes;
	}

	public void setInstitutes(String institutes) {
		this.institutes = institutes;
	}

	public String getSoilFileId() {
		return soilFileId;
	}

	public void setSoilFileId(String soilFileId) {
		this.soilFileId = soilFileId;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
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

	public Float getSoillat() {
		return soillat;
	}

	public void setSoillat(Float soillat) {
		this.soillat = soillat;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	public Float getSoillong() {
		return soillong;
	}

	public void setSoillong(Float soillong) {
		this.soillong = soillong;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public Float getSand() {
		return sand;
	}

	public void setSand(Float sand) {
		this.sand = sand;
	}

	public Float getSoilelev() {
		return soilelev;
	}

	public void setSoilelev(Float soilelev) {
		this.soilelev = soilelev;
	}

	public Float getSlope() {
		return slope;
	}

	public void setSlope(Float slope) {
		this.slope = slope;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClassifcation() {
		return classifcation;
	}

	public void setClassifcation(String classifcation) {
		this.classifcation = classifcation;
	}

	public String getSlSystem() {
		return slSystem;
	}

	public void setSlSystem(String slSystem) {
		this.slSystem = slSystem;
	}

	public Float getSalb() {
		return salb;
	}

	public void setSalb(Float salb) {
		this.salb = salb;
	}

	public Float getSlu1() {
		return slu1;
	}

	public void setSlu1(Float slu1) {
		this.slu1 = slu1;
	}

	public Float getSldr() {
		return sldr;
	}

	public void setSldr(Float sldr) {
		this.sldr = sldr;
	}

	public Float getSlro() {
		return slro;
	}

	public void setSlro(Float slro) {
		this.slro = slro;
	}

	public String getSscol() {
		return sscol;
	}

	public void setSscol(String sscol) {
		this.sscol = sscol;
	}

	public Float getFlst() {
		return flst;
	}

	public void setFlst(Float flst) {
		this.flst = flst;
	}

	public Float getSlnf() {
		return slnf;
	}

	public void setSlnf(Float slnf) {
		this.slnf = slnf;
	}

	public String getSmhb() {
		return smhb;
	}

	public void setSmhb(String smhb) {
		this.smhb = smhb;
	}

	public String getSmke() {
		return smke;
	}

	public void setSmke(String smke) {
		this.smke = smke;
	}

	public String getSmpx() {
		return smpx;
	}

	public void setSmpx(String smpx) {
		this.smpx = smpx;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sid != null ? sid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilProfile)) {
			return false;
		}
		SoilProfile other = (SoilProfile) object;
		if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilProfile[ sid=" + sid + " ]";
	}

	@XmlTransient
	public Collection<SoilProfileLayer> getSoilProfileLayerCollection() {
		return soilProfileLayerCollection;
	}

	public void setSoilProfileLayerCollection(Collection<SoilProfileLayer> soilProfileLayerCollection) {
		this.soilProfileLayerCollection = soilProfileLayerCollection;
	}
}