/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wpavan
 */
@Entity
@Table(name = "fields")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fields.findAll", query = "SELECT f FROM Fields f"),
    @NamedQuery(name = "Fields.findByExpId", query = "SELECT f FROM Fields f WHERE f.fieldsPK.expId = :expId"),
    @NamedQuery(name = "Fields.findByFl", query = "SELECT f FROM Fields f WHERE f.fieldsPK.fl = :fl"),
    @NamedQuery(name = "Fields.findBySid", query = "SELECT f FROM Fields f WHERE f.sid = :sid"),
    @NamedQuery(name = "Fields.findByFlLoc", query = "SELECT f FROM Fields f WHERE f.flLoc = :flLoc"),
    @NamedQuery(name = "Fields.findByFlLat", query = "SELECT f FROM Fields f WHERE f.flLat = :flLat"),
    @NamedQuery(name = "Fields.findByFlLong", query = "SELECT f FROM Fields f WHERE f.flLong = :flLong"),
    @NamedQuery(name = "Fields.findByFlele", query = "SELECT f FROM Fields f WHERE f.flele = :flele"),
    @NamedQuery(name = "Fields.findByFlsl", query = "SELECT f FROM Fields f WHERE f.flsl = :flsl"),
    @NamedQuery(name = "Fields.findByFlsll", query = "SELECT f FROM Fields f WHERE f.flsll = :flsll"),
    @NamedQuery(name = "Fields.findByFlsla", query = "SELECT f FROM Fields f WHERE f.flsla = :flsla"),
    @NamedQuery(name = "Fields.findByFarea", query = "SELECT f FROM Fields f WHERE f.farea = :farea"),
    @NamedQuery(name = "Fields.findByFlob", query = "SELECT f FROM Fields f WHERE f.flob = :flob"),
    @NamedQuery(name = "Fields.findByFllwr", query = "SELECT f FROM Fields f WHERE f.fllwr = :fllwr"),
    @NamedQuery(name = "Fields.findByFlPlowd", query = "SELECT f FROM Fields f WHERE f.flPlowd = :flPlowd"),
    @NamedQuery(name = "Fields.findByFlPlowdc", query = "SELECT f FROM Fields f WHERE f.flPlowdc = :flPlowdc"),
    @NamedQuery(name = "Fields.findByFlDrntype", query = "SELECT f FROM Fields f WHERE f.flDrntype = :flDrntype"),
    @NamedQuery(name = "Fields.findByFldrd", query = "SELECT f FROM Fields f WHERE f.fldrd = :fldrd"),
    @NamedQuery(name = "Fields.findByFldrs", query = "SELECT f FROM Fields f WHERE f.fldrs = :fldrs"),
    @NamedQuery(name = "Fields.findByWid", query = "SELECT f FROM Fields f WHERE f.wid = :wid"),
    @NamedQuery(name = "Fields.findByWthFile", query = "SELECT f FROM Fields f WHERE f.wthFile = :wthFile"),
    @NamedQuery(name = "Fields.findBySoilId", query = "SELECT f FROM Fields f WHERE f.soilId = :soilId"),
    @NamedQuery(name = "Fields.findBySoilFile", query = "SELECT f FROM Fields f WHERE f.soilFile = :soilFile"),
    @NamedQuery(name = "Fields.findBySltx", query = "SELECT f FROM Fields f WHERE f.sltx = :sltx"),
    @NamedQuery(name = "Fields.findBySldp", query = "SELECT f FROM Fields f WHERE f.sldp = :sldp"),
    @NamedQuery(name = "Fields.findByDtwt", query = "SELECT f FROM Fields f WHERE f.dtwt = :dtwt"),
    @NamedQuery(name = "Fields.findByIdField", query = "SELECT f FROM Fields f WHERE f.idField = :idField"),
    @NamedQuery(name = "Fields.findByWstaId", query = "SELECT f FROM Fields f WHERE f.wstaId = :wstaId"),
    @NamedQuery(name = "Fields.findByFlName", query = "SELECT f FROM Fields f WHERE f.flName = :flName"),
    @NamedQuery(name = "Fields.findByFlNotes", query = "SELECT f FROM Fields f WHERE f.flNotes = :flNotes"),
    @NamedQuery(name = "Fields.findByFlst", query = "SELECT f FROM Fields f WHERE f.flst = :flst"),
    @NamedQuery(name = "Fields.findByFlhst", query = "SELECT f FROM Fields f WHERE f.flhst = :flhst"),
    @NamedQuery(name = "Fields.findByFhdur", query = "SELECT f FROM Fields f WHERE f.fhdur = :fhdur")})
public class Fields implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FieldsPK fieldsPK;
    @Column(name = "sid")
    private Integer sid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fl_loc")
    private Float flLoc;
    @Column(name = "fl_lat")
    private Float flLat;
    @Column(name = "fl_long")
    private Float flLong;
    @Column(name = "flele")
    private Float flele;
    @Column(name = "flsl")
    private Float flsl;
    @Column(name = "flsll")
    private Float flsll;
    @Size(max = 255)
    @Column(name = "flsla")
    private String flsla;
    @Column(name = "farea")
    private Float farea;
    @Column(name = "flob")
    private Float flob;
    @Column(name = "fllwr")
    private Integer fllwr;
    @Column(name = "fl_plowd")
    private Float flPlowd;
    @Column(name = "fl_plowdc")
    private Integer flPlowdc;
    @Size(max = 255)
    @Column(name = "fl_drntype")
    private String flDrntype;
    @Column(name = "fldrd")
    private Float fldrd;
    @Column(name = "fldrs")
    private Float fldrs;
    @Column(name = "wid")
    private Integer wid;
    @Size(max = 255)
    @Column(name = "wth_file")
    private String wthFile;
    @Size(max = 255)
    @Column(name = "soil_id")
    private String soilId;
    @Size(max = 255)
    @Column(name = "soil_file")
    private String soilFile;
    @Size(max = 255)
    @Column(name = "sltx")
    private String sltx;
    @Column(name = "sldp")
    private Float sldp;
    @Column(name = "dtwt")
    private Float dtwt;
    @Size(max = 255)
    @Column(name = "id_field")
    private String idField;
    @Size(max = 255)
    @Column(name = "wsta_id")
    private String wstaId;
    @Size(max = 255)
    @Column(name = "fl_name")
    private String flName;
    @Size(max = 255)
    @Column(name = "fl_notes")
    private String flNotes;
    @Size(max = 5)
    @Column(name = "flst")
    private String flst;
    @Size(max = 5)
    @Column(name = "flhst")
    private String flhst;
    @Size(max = 5)
    @Column(name = "fhdur")
    private String fhdur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fields")
    private Collection<Treatments> treatmentsCollection;

    public Fields() {
    }

    public Fields(FieldsPK fieldsPK) {
        this.fieldsPK = fieldsPK;
    }

    public Fields(int expId, int fl) {
        this.fieldsPK = new FieldsPK(expId, fl);
    }

    public FieldsPK getFieldsPK() {
        return fieldsPK;
    }

    public void setFieldsPK(FieldsPK fieldsPK) {
        this.fieldsPK = fieldsPK;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Float getFlLoc() {
        return flLoc;
    }

    public void setFlLoc(Float flLoc) {
        this.flLoc = flLoc;
    }

    public Float getFlLat() {
        return flLat;
    }

    public void setFlLat(Float flLat) {
        this.flLat = flLat;
    }

    public Float getFlLong() {
        return flLong;
    }

    public void setFlLong(Float flLong) {
        this.flLong = flLong;
    }

    public Float getFlele() {
        return flele;
    }

    public void setFlele(Float flele) {
        this.flele = flele;
    }

    public Float getFlsl() {
        return flsl;
    }

    public void setFlsl(Float flsl) {
        this.flsl = flsl;
    }

    public Float getFlsll() {
        return flsll;
    }

    public void setFlsll(Float flsll) {
        this.flsll = flsll;
    }

    public String getFlsla() {
        return flsla;
    }

    public void setFlsla(String flsla) {
        this.flsla = flsla;
    }

    public Float getFarea() {
        return farea;
    }

    public void setFarea(Float farea) {
        this.farea = farea;
    }

    public Float getFlob() {
        return flob;
    }

    public void setFlob(Float flob) {
        this.flob = flob;
    }

    public Integer getFllwr() {
        return fllwr;
    }

    public void setFllwr(Integer fllwr) {
        this.fllwr = fllwr;
    }

    public Float getFlPlowd() {
        return flPlowd;
    }

    public void setFlPlowd(Float flPlowd) {
        this.flPlowd = flPlowd;
    }

    public Integer getFlPlowdc() {
        return flPlowdc;
    }

    public void setFlPlowdc(Integer flPlowdc) {
        this.flPlowdc = flPlowdc;
    }

    public String getFlDrntype() {
        return flDrntype;
    }

    public void setFlDrntype(String flDrntype) {
        this.flDrntype = flDrntype;
    }

    public Float getFldrd() {
        return fldrd;
    }

    public void setFldrd(Float fldrd) {
        this.fldrd = fldrd;
    }

    public Float getFldrs() {
        return fldrs;
    }

    public void setFldrs(Float fldrs) {
        this.fldrs = fldrs;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWthFile() {
        return wthFile;
    }

    public void setWthFile(String wthFile) {
        this.wthFile = wthFile;
    }

    public String getSoilId() {
        return soilId;
    }

    public void setSoilId(String soilId) {
        this.soilId = soilId;
    }

    public String getSoilFile() {
        return soilFile;
    }

    public void setSoilFile(String soilFile) {
        this.soilFile = soilFile;
    }

    public String getSltx() {
        return sltx;
    }

    public void setSltx(String sltx) {
        this.sltx = sltx;
    }

    public Float getSldp() {
        return sldp;
    }

    public void setSldp(Float sldp) {
        this.sldp = sldp;
    }

    public Float getDtwt() {
        return dtwt;
    }

    public void setDtwt(Float dtwt) {
        this.dtwt = dtwt;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getWstaId() {
        return wstaId;
    }

    public void setWstaId(String wstaId) {
        this.wstaId = wstaId;
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public String getFlNotes() {
        return flNotes;
    }

    public void setFlNotes(String flNotes) {
        this.flNotes = flNotes;
    }

    public String getFlst() {
        return flst;
    }

    public void setFlst(String flst) {
        this.flst = flst;
    }

    public String getFlhst() {
        return flhst;
    }

    public void setFlhst(String flhst) {
        this.flhst = flhst;
    }

    public String getFhdur() {
        return fhdur;
    }

    public void setFhdur(String fhdur) {
        this.fhdur = fhdur;
    }

    @XmlTransient
    public Collection<Treatments> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatments> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fieldsPK != null ? fieldsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fields)) {
            return false;
        }
        Fields other = (Fields) object;
        if ((this.fieldsPK == null && other.fieldsPK != null) || (this.fieldsPK != null && !this.fieldsPK.equals(other.fieldsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Fields[ fieldsPK=" + fieldsPK + " ]";
    }
    
}
