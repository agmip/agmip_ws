package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fonini
 */
@Entity
@Table(name = "soil_profile_layers")
@XmlRootElement
public class SoilProfileLayer implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SoilProfileLayerPK soilProfileLayerPK;
	@Size(max = 255)
    @Column(name = "slmh", length = 255)
	private String slmh;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "slll", precision = 12)
	private Float slll;
	@Column(name = "sldul", precision = 12)
	private Float sldul;
	@Column(name = "slsat", precision = 12)
	private Float slsat;
	@Column(name = "sksat", precision = 12)
	private Float sksat;
	@Column(name = "slphb", precision = 12)
	private Float slphb;
	@Column(name = "slphw", precision = 12)
	private Float slphw;
	@Column(name = "slcec", precision = 12)
	private Float slcec;
	@Column(name = "slni", precision = 12)
	private Float slni;
	@Column(name = "sadc", precision = 12)
	private Float sadc;
	@Column(name = "sladc", precision = 12)
	private Float sladc;
	@Column(name = "scmpf", precision = 12)
	private Float scmpf;
	@Column(name = "slrgf", precision = 12)
	private Float slrgf;
	@Column(name = "slpx", precision = 12)
	private Float slpx;
	@Column(name = "slpt", precision = 12)
	private Float slpt;
	@Column(name = "slpo", precision = 12)
	private Float slpo;
	@Column(name = "slca", precision = 12)
	private Float slca;
	@Column(name = "slal", precision = 12)
	private Float slal;
	@Column(name = "slfe", precision = 12)
	private Float slfe;
	@Column(name = "slmn", precision = 12)
	private Float slmn;
	@Column(name = "slbs", precision = 12)
	private Float slbs;
	@Column(name = "slpa", precision = 12)
	private Float slpa;
	@Column(name = "slpb", precision = 12)
	private Float slpb;
	@Column(name = "slke", precision = 12)
	private Float slke;
	@Column(name = "slmg", precision = 12)
	private Float slmg;
	@Column(name = "slna", precision = 12)
	private Float slna;
	@Column(name = "slsu", precision = 12)
	private Float slsu;
	@Column(name = "caco3", precision = 12)
	private Float caco3;
	@Column(name = "sbdm", precision = 12)
	private Float sbdm;
	@Size(max = 255)
    @Column(name = "scom", length = 255)
	private String scom;
	@Column(name = "slbdm", precision = 12)
	private Float slbdm;
	@Column(name = "slcf", precision = 12)
	private Float slcf;
	@Column(name = "slcl", precision = 12)
	private Float slcl;
	@Column(name = "sldn", precision = 12)
	private Float sldn;
	@Column(name = "slec", precision = 12)
	private Float slec;
	@Column(name = "sloc", precision = 12)
	private Float sloc;
	@Column(name = "slsi", precision = 12)
	private Float slsi;
	@Column(name = "snh4", precision = 12)
	private Float snh4;
	@Column(name = "sno3", precision = 12)
	private Float sno3;
	@Column(name = "penv", precision = 12)
	private Float penv;
	@JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private SoilProfile soilProfile;

	public SoilProfileLayer() {
	}

	public SoilProfileLayer(SoilProfileLayerPK soilProfileLayerPK) {
		this.soilProfileLayerPK = soilProfileLayerPK;
	}

	public SoilProfileLayer(int sid, String soilId, String soilFileId, int sllb) {
		this.soilProfileLayerPK = new SoilProfileLayerPK(sid, soilId, soilFileId, sllb);
	}

	public SoilProfileLayerPK getSoilProfileLayerPK() {
		return soilProfileLayerPK;
	}

	public void setSoilProfileLayerPK(SoilProfileLayerPK soilProfileLayerPK) {
		this.soilProfileLayerPK = soilProfileLayerPK;
	}

	public String getSlmh() {
		return slmh;
	}

	public void setSlmh(String slmh) {
		this.slmh = slmh;
	}

	public Float getSlll() {
		return slll;
	}

	public void setSlll(Float slll) {
		this.slll = slll;
	}

	public Float getSldul() {
		return sldul;
	}

	public void setSldul(Float sldul) {
		this.sldul = sldul;
	}

	public Float getSlsat() {
		return slsat;
	}

	public void setSlsat(Float slsat) {
		this.slsat = slsat;
	}

	public Float getSksat() {
		return sksat;
	}

	public void setSksat(Float sksat) {
		this.sksat = sksat;
	}

	public Float getSlphb() {
		return slphb;
	}

	public void setSlphb(Float slphb) {
		this.slphb = slphb;
	}

	public Float getSlphw() {
		return slphw;
	}

	public void setSlphw(Float slphw) {
		this.slphw = slphw;
	}

	public Float getSlcec() {
		return slcec;
	}

	public void setSlcec(Float slcec) {
		this.slcec = slcec;
	}

	public Float getSlni() {
		return slni;
	}

	public void setSlni(Float slni) {
		this.slni = slni;
	}

	public Float getSadc() {
		return sadc;
	}

	public void setSadc(Float sadc) {
		this.sadc = sadc;
	}

	public Float getSladc() {
		return sladc;
	}

	public void setSladc(Float sladc) {
		this.sladc = sladc;
	}

	public Float getScmpf() {
		return scmpf;
	}

	public void setScmpf(Float scmpf) {
		this.scmpf = scmpf;
	}

	public Float getSlrgf() {
		return slrgf;
	}

	public void setSlrgf(Float slrgf) {
		this.slrgf = slrgf;
	}

	public Float getSlpx() {
		return slpx;
	}

	public void setSlpx(Float slpx) {
		this.slpx = slpx;
	}

	public Float getSlpt() {
		return slpt;
	}

	public void setSlpt(Float slpt) {
		this.slpt = slpt;
	}

	public Float getSlpo() {
		return slpo;
	}

	public void setSlpo(Float slpo) {
		this.slpo = slpo;
	}

	public Float getSlca() {
		return slca;
	}

	public void setSlca(Float slca) {
		this.slca = slca;
	}

	public Float getSlal() {
		return slal;
	}

	public void setSlal(Float slal) {
		this.slal = slal;
	}

	public Float getSlfe() {
		return slfe;
	}

	public void setSlfe(Float slfe) {
		this.slfe = slfe;
	}

	public Float getSlmn() {
		return slmn;
	}

	public void setSlmn(Float slmn) {
		this.slmn = slmn;
	}

	public Float getSlbs() {
		return slbs;
	}

	public void setSlbs(Float slbs) {
		this.slbs = slbs;
	}

	public Float getSlpa() {
		return slpa;
	}

	public void setSlpa(Float slpa) {
		this.slpa = slpa;
	}

	public Float getSlpb() {
		return slpb;
	}

	public void setSlpb(Float slpb) {
		this.slpb = slpb;
	}

	public Float getSlke() {
		return slke;
	}

	public void setSlke(Float slke) {
		this.slke = slke;
	}

	public Float getSlmg() {
		return slmg;
	}

	public void setSlmg(Float slmg) {
		this.slmg = slmg;
	}

	public Float getSlna() {
		return slna;
	}

	public void setSlna(Float slna) {
		this.slna = slna;
	}

	public Float getSlsu() {
		return slsu;
	}

	public void setSlsu(Float slsu) {
		this.slsu = slsu;
	}

	public Float getCaco3() {
		return caco3;
	}

	public void setCaco3(Float caco3) {
		this.caco3 = caco3;
	}

	public Float getSbdm() {
		return sbdm;
	}

	public void setSbdm(Float sbdm) {
		this.sbdm = sbdm;
	}

	public String getScom() {
		return scom;
	}

	public void setScom(String scom) {
		this.scom = scom;
	}

	public Float getSlbdm() {
		return slbdm;
	}

	public void setSlbdm(Float slbdm) {
		this.slbdm = slbdm;
	}

	public Float getSlcf() {
		return slcf;
	}

	public void setSlcf(Float slcf) {
		this.slcf = slcf;
	}

	public Float getSlcl() {
		return slcl;
	}

	public void setSlcl(Float slcl) {
		this.slcl = slcl;
	}

	public Float getSldn() {
		return sldn;
	}

	public void setSldn(Float sldn) {
		this.sldn = sldn;
	}

	public Float getSlec() {
		return slec;
	}

	public void setSlec(Float slec) {
		this.slec = slec;
	}

	public Float getSloc() {
		return sloc;
	}

	public void setSloc(Float sloc) {
		this.sloc = sloc;
	}

	public Float getSlsi() {
		return slsi;
	}

	public void setSlsi(Float slsi) {
		this.slsi = slsi;
	}

	public Float getSnh4() {
		return snh4;
	}

	public void setSnh4(Float snh4) {
		this.snh4 = snh4;
	}

	public Float getSno3() {
		return sno3;
	}

	public void setSno3(Float sno3) {
		this.sno3 = sno3;
	}

	public Float getPenv() {
		return penv;
	}

	public void setPenv(Float penv) {
		this.penv = penv;
	}

	public SoilProfile getSoilProfile() {
		return soilProfile;
	}

	public void setSoilProfile(SoilProfile soilProfile) {
		this.soilProfile = soilProfile;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (soilProfileLayerPK != null ? soilProfileLayerPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SoilProfileLayer)) {
			return false;
		}
		SoilProfileLayer other = (SoilProfileLayer) object;
		if ((this.soilProfileLayerPK == null && other.soilProfileLayerPK != null) || (this.soilProfileLayerPK != null && !this.soilProfileLayerPK.equals(other.soilProfileLayerPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilProfileLayer[ soilProfileLayerPK=" + soilProfileLayerPK + " ]";
	}

}
