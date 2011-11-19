package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "fields")
@XmlRootElement
public class Field implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FieldPK fieldPK;
    @Column(name = "sid")
    private Integer sid;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "field", fetch= FetchType.EAGER)
    private Collection<Treatment> treatmentsCollection;

    public Field() {
    }

    public Field(FieldPK fieldPK) {
        this.fieldPK = fieldPK;
    }

    public Field(int expId, int fl) {
        this.fieldPK = new FieldPK(expId, fl);
    }

    public FieldPK getFieldPK() {
        return fieldPK;
    }

    public void setFieldPK(FieldPK fieldPK) {
        this.fieldPK = fieldPK;
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
    public Collection<Treatment> getTreatmentsCollection() {
        return treatmentsCollection;
    }

    public void setTreatmentsCollection(Collection<Treatment> treatmentsCollection) {
        this.treatmentsCollection = treatmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fieldPK != null ? fieldPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        if ((this.fieldPK == null && other.fieldPK != null) || (this.fieldPK != null && !this.fieldPK.equals(other.fieldPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Field[ fieldPK=" + fieldPK + " ]";
    }
}