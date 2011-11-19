package converter;

import beans.SoilProfileLayer;
import beans.SoilProfileLayerPK;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import beans.SoilProfile;

/**
 *
 * @author fonini
 */
@XmlRootElement(name = "soilProfileLayer")
public class SoilProfileLayerConverter {
	private SoilProfileLayer entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of SoilProfileLayerConverter */
	public SoilProfileLayerConverter() {
		entity = new SoilProfileLayer();
	}

	/**
	 * Creates a new instance of SoilProfileLayerConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public SoilProfileLayerConverter(SoilProfileLayer entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getSoilProfileLayerPK().getSid() + "," + entity.getSoilProfileLayerPK().getSoilId() + "," + entity.getSoilProfileLayerPK().getSoilFileId() + "," + entity.getSoilProfileLayerPK().getSllb() + "/").build() : uri;
		this.expandLevel = expandLevel;
		getSoilProfile();
	}

	/**
	 * Creates a new instance of SoilProfileLayerConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public SoilProfileLayerConverter(SoilProfileLayer entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for soilProfileLayerPK.
	 *
	 * @return value for soilProfileLayerPK
	 */
	@XmlElement
	public SoilProfileLayerPK getSoilProfileLayerPK() {
		return (expandLevel > 0) ? entity.getSoilProfileLayerPK() : null;
	}

	/**
	 * Setter for soilProfileLayerPK.
	 *
	 * @param value the value to set
	 */
	public void setSoilProfileLayerPK(SoilProfileLayerPK value) {
		entity.setSoilProfileLayerPK(value);
	}

	/**
	 * Getter for slmh.
	 *
	 * @return value for slmh
	 */
	@XmlElement
	public String getSlmh() {
		return (expandLevel > 0) ? entity.getSlmh() : null;
	}

	/**
	 * Setter for slmh.
	 *
	 * @param value the value to set
	 */
	public void setSlmh(String value) {
		entity.setSlmh(value);
	}

	/**
	 * Getter for slll.
	 *
	 * @return value for slll
	 */
	@XmlElement
	public Float getSlll() {
		return (expandLevel > 0) ? entity.getSlll() : null;
	}

	/**
	 * Setter for slll.
	 *
	 * @param value the value to set
	 */
	public void setSlll(Float value) {
		entity.setSlll(value);
	}

	/**
	 * Getter for sldul.
	 *
	 * @return value for sldul
	 */
	@XmlElement
	public Float getSldul() {
		return (expandLevel > 0) ? entity.getSldul() : null;
	}

	/**
	 * Setter for sldul.
	 *
	 * @param value the value to set
	 */
	public void setSldul(Float value) {
		entity.setSldul(value);
	}

	/**
	 * Getter for slsat.
	 *
	 * @return value for slsat
	 */
	@XmlElement
	public Float getSlsat() {
		return (expandLevel > 0) ? entity.getSlsat() : null;
	}

	/**
	 * Setter for slsat.
	 *
	 * @param value the value to set
	 */
	public void setSlsat(Float value) {
		entity.setSlsat(value);
	}

	/**
	 * Getter for sksat.
	 *
	 * @return value for sksat
	 */
	@XmlElement
	public Float getSksat() {
		return (expandLevel > 0) ? entity.getSksat() : null;
	}

	/**
	 * Setter for sksat.
	 *
	 * @param value the value to set
	 */
	public void setSksat(Float value) {
		entity.setSksat(value);
	}

	/**
	 * Getter for slphb.
	 *
	 * @return value for slphb
	 */
	@XmlElement
	public Float getSlphb() {
		return (expandLevel > 0) ? entity.getSlphb() : null;
	}

	/**
	 * Setter for slphb.
	 *
	 * @param value the value to set
	 */
	public void setSlphb(Float value) {
		entity.setSlphb(value);
	}

	/**
	 * Getter for slphw.
	 *
	 * @return value for slphw
	 */
	@XmlElement
	public Float getSlphw() {
		return (expandLevel > 0) ? entity.getSlphw() : null;
	}

	/**
	 * Setter for slphw.
	 *
	 * @param value the value to set
	 */
	public void setSlphw(Float value) {
		entity.setSlphw(value);
	}

	/**
	 * Getter for slcec.
	 *
	 * @return value for slcec
	 */
	@XmlElement
	public Float getSlcec() {
		return (expandLevel > 0) ? entity.getSlcec() : null;
	}

	/**
	 * Setter for slcec.
	 *
	 * @param value the value to set
	 */
	public void setSlcec(Float value) {
		entity.setSlcec(value);
	}

	/**
	 * Getter for slni.
	 *
	 * @return value for slni
	 */
	@XmlElement
	public Float getSlni() {
		return (expandLevel > 0) ? entity.getSlni() : null;
	}

	/**
	 * Setter for slni.
	 *
	 * @param value the value to set
	 */
	public void setSlni(Float value) {
		entity.setSlni(value);
	}

	/**
	 * Getter for sadc.
	 *
	 * @return value for sadc
	 */
	@XmlElement
	public Float getSadc() {
		return (expandLevel > 0) ? entity.getSadc() : null;
	}

	/**
	 * Setter for sadc.
	 *
	 * @param value the value to set
	 */
	public void setSadc(Float value) {
		entity.setSadc(value);
	}

	/**
	 * Getter for sladc.
	 *
	 * @return value for sladc
	 */
	@XmlElement
	public Float getSladc() {
		return (expandLevel > 0) ? entity.getSladc() : null;
	}

	/**
	 * Setter for sladc.
	 *
	 * @param value the value to set
	 */
	public void setSladc(Float value) {
		entity.setSladc(value);
	}

	/**
	 * Getter for scmpf.
	 *
	 * @return value for scmpf
	 */
	@XmlElement
	public Float getScmpf() {
		return (expandLevel > 0) ? entity.getScmpf() : null;
	}

	/**
	 * Setter for scmpf.
	 *
	 * @param value the value to set
	 */
	public void setScmpf(Float value) {
		entity.setScmpf(value);
	}

	/**
	 * Getter for slrgf.
	 *
	 * @return value for slrgf
	 */
	@XmlElement
	public Float getSlrgf() {
		return (expandLevel > 0) ? entity.getSlrgf() : null;
	}

	/**
	 * Setter for slrgf.
	 *
	 * @param value the value to set
	 */
	public void setSlrgf(Float value) {
		entity.setSlrgf(value);
	}

	/**
	 * Getter for slpx.
	 *
	 * @return value for slpx
	 */
	@XmlElement
	public Float getSlpx() {
		return (expandLevel > 0) ? entity.getSlpx() : null;
	}

	/**
	 * Setter for slpx.
	 *
	 * @param value the value to set
	 */
	public void setSlpx(Float value) {
		entity.setSlpx(value);
	}

	/**
	 * Getter for slpt.
	 *
	 * @return value for slpt
	 */
	@XmlElement
	public Float getSlpt() {
		return (expandLevel > 0) ? entity.getSlpt() : null;
	}

	/**
	 * Setter for slpt.
	 *
	 * @param value the value to set
	 */
	public void setSlpt(Float value) {
		entity.setSlpt(value);
	}

	/**
	 * Getter for slpo.
	 *
	 * @return value for slpo
	 */
	@XmlElement
	public Float getSlpo() {
		return (expandLevel > 0) ? entity.getSlpo() : null;
	}

	/**
	 * Setter for slpo.
	 *
	 * @param value the value to set
	 */
	public void setSlpo(Float value) {
		entity.setSlpo(value);
	}

	/**
	 * Getter for slca.
	 *
	 * @return value for slca
	 */
	@XmlElement
	public Float getSlca() {
		return (expandLevel > 0) ? entity.getSlca() : null;
	}

	/**
	 * Setter for slca.
	 *
	 * @param value the value to set
	 */
	public void setSlca(Float value) {
		entity.setSlca(value);
	}

	/**
	 * Getter for slal.
	 *
	 * @return value for slal
	 */
	@XmlElement
	public Float getSlal() {
		return (expandLevel > 0) ? entity.getSlal() : null;
	}

	/**
	 * Setter for slal.
	 *
	 * @param value the value to set
	 */
	public void setSlal(Float value) {
		entity.setSlal(value);
	}

	/**
	 * Getter for slfe.
	 *
	 * @return value for slfe
	 */
	@XmlElement
	public Float getSlfe() {
		return (expandLevel > 0) ? entity.getSlfe() : null;
	}

	/**
	 * Setter for slfe.
	 *
	 * @param value the value to set
	 */
	public void setSlfe(Float value) {
		entity.setSlfe(value);
	}

	/**
	 * Getter for slmn.
	 *
	 * @return value for slmn
	 */
	@XmlElement
	public Float getSlmn() {
		return (expandLevel > 0) ? entity.getSlmn() : null;
	}

	/**
	 * Setter for slmn.
	 *
	 * @param value the value to set
	 */
	public void setSlmn(Float value) {
		entity.setSlmn(value);
	}

	/**
	 * Getter for slbs.
	 *
	 * @return value for slbs
	 */
	@XmlElement
	public Float getSlbs() {
		return (expandLevel > 0) ? entity.getSlbs() : null;
	}

	/**
	 * Setter for slbs.
	 *
	 * @param value the value to set
	 */
	public void setSlbs(Float value) {
		entity.setSlbs(value);
	}

	/**
	 * Getter for slpa.
	 *
	 * @return value for slpa
	 */
	@XmlElement
	public Float getSlpa() {
		return (expandLevel > 0) ? entity.getSlpa() : null;
	}

	/**
	 * Setter for slpa.
	 *
	 * @param value the value to set
	 */
	public void setSlpa(Float value) {
		entity.setSlpa(value);
	}

	/**
	 * Getter for slpb.
	 *
	 * @return value for slpb
	 */
	@XmlElement
	public Float getSlpb() {
		return (expandLevel > 0) ? entity.getSlpb() : null;
	}

	/**
	 * Setter for slpb.
	 *
	 * @param value the value to set
	 */
	public void setSlpb(Float value) {
		entity.setSlpb(value);
	}

	/**
	 * Getter for slke.
	 *
	 * @return value for slke
	 */
	@XmlElement
	public Float getSlke() {
		return (expandLevel > 0) ? entity.getSlke() : null;
	}

	/**
	 * Setter for slke.
	 *
	 * @param value the value to set
	 */
	public void setSlke(Float value) {
		entity.setSlke(value);
	}

	/**
	 * Getter for slmg.
	 *
	 * @return value for slmg
	 */
	@XmlElement
	public Float getSlmg() {
		return (expandLevel > 0) ? entity.getSlmg() : null;
	}

	/**
	 * Setter for slmg.
	 *
	 * @param value the value to set
	 */
	public void setSlmg(Float value) {
		entity.setSlmg(value);
	}

	/**
	 * Getter for slna.
	 *
	 * @return value for slna
	 */
	@XmlElement
	public Float getSlna() {
		return (expandLevel > 0) ? entity.getSlna() : null;
	}

	/**
	 * Setter for slna.
	 *
	 * @param value the value to set
	 */
	public void setSlna(Float value) {
		entity.setSlna(value);
	}

	/**
	 * Getter for slsu.
	 *
	 * @return value for slsu
	 */
	@XmlElement
	public Float getSlsu() {
		return (expandLevel > 0) ? entity.getSlsu() : null;
	}

	/**
	 * Setter for slsu.
	 *
	 * @param value the value to set
	 */
	public void setSlsu(Float value) {
		entity.setSlsu(value);
	}

	/**
	 * Getter for caco3.
	 *
	 * @return value for caco3
	 */
	@XmlElement
	public Float getCaco3() {
		return (expandLevel > 0) ? entity.getCaco3() : null;
	}

	/**
	 * Setter for caco3.
	 *
	 * @param value the value to set
	 */
	public void setCaco3(Float value) {
		entity.setCaco3(value);
	}

	/**
	 * Getter for sbdm.
	 *
	 * @return value for sbdm
	 */
	@XmlElement
	public Float getSbdm() {
		return (expandLevel > 0) ? entity.getSbdm() : null;
	}

	/**
	 * Setter for sbdm.
	 *
	 * @param value the value to set
	 */
	public void setSbdm(Float value) {
		entity.setSbdm(value);
	}

	/**
	 * Getter for scom.
	 *
	 * @return value for scom
	 */
	@XmlElement
	public String getScom() {
		return (expandLevel > 0) ? entity.getScom() : null;
	}

	/**
	 * Setter for scom.
	 *
	 * @param value the value to set
	 */
	public void setScom(String value) {
		entity.setScom(value);
	}

	/**
	 * Getter for slbdm.
	 *
	 * @return value for slbdm
	 */
	@XmlElement
	public Float getSlbdm() {
		return (expandLevel > 0) ? entity.getSlbdm() : null;
	}

	/**
	 * Setter for slbdm.
	 *
	 * @param value the value to set
	 */
	public void setSlbdm(Float value) {
		entity.setSlbdm(value);
	}

	/**
	 * Getter for slcf.
	 *
	 * @return value for slcf
	 */
	@XmlElement
	public Float getSlcf() {
		return (expandLevel > 0) ? entity.getSlcf() : null;
	}

	/**
	 * Setter for slcf.
	 *
	 * @param value the value to set
	 */
	public void setSlcf(Float value) {
		entity.setSlcf(value);
	}

	/**
	 * Getter for slcl.
	 *
	 * @return value for slcl
	 */
	@XmlElement
	public Float getSlcl() {
		return (expandLevel > 0) ? entity.getSlcl() : null;
	}

	/**
	 * Setter for slcl.
	 *
	 * @param value the value to set
	 */
	public void setSlcl(Float value) {
		entity.setSlcl(value);
	}

	/**
	 * Getter for sldn.
	 *
	 * @return value for sldn
	 */
	@XmlElement
	public Float getSldn() {
		return (expandLevel > 0) ? entity.getSldn() : null;
	}

	/**
	 * Setter for sldn.
	 *
	 * @param value the value to set
	 */
	public void setSldn(Float value) {
		entity.setSldn(value);
	}

	/**
	 * Getter for slec.
	 *
	 * @return value for slec
	 */
	@XmlElement
	public Float getSlec() {
		return (expandLevel > 0) ? entity.getSlec() : null;
	}

	/**
	 * Setter for slec.
	 *
	 * @param value the value to set
	 */
	public void setSlec(Float value) {
		entity.setSlec(value);
	}

	/**
	 * Getter for sloc.
	 *
	 * @return value for sloc
	 */
	@XmlElement
	public Float getSloc() {
		return (expandLevel > 0) ? entity.getSloc() : null;
	}

	/**
	 * Setter for sloc.
	 *
	 * @param value the value to set
	 */
	public void setSloc(Float value) {
		entity.setSloc(value);
	}

	/**
	 * Getter for slsi.
	 *
	 * @return value for slsi
	 */
	@XmlElement
	public Float getSlsi() {
		return (expandLevel > 0) ? entity.getSlsi() : null;
	}

	/**
	 * Setter for slsi.
	 *
	 * @param value the value to set
	 */
	public void setSlsi(Float value) {
		entity.setSlsi(value);
	}

	/**
	 * Getter for snh4.
	 *
	 * @return value for snh4
	 */
	@XmlElement
	public Float getSnh4() {
		return (expandLevel > 0) ? entity.getSnh4() : null;
	}

	/**
	 * Setter for snh4.
	 *
	 * @param value the value to set
	 */
	public void setSnh4(Float value) {
		entity.setSnh4(value);
	}

	/**
	 * Getter for sno3.
	 *
	 * @return value for sno3
	 */
	@XmlElement
	public Float getSno3() {
		return (expandLevel > 0) ? entity.getSno3() : null;
	}

	/**
	 * Setter for sno3.
	 *
	 * @param value the value to set
	 */
	public void setSno3(Float value) {
		entity.setSno3(value);
	}

	/**
	 * Getter for penv.
	 *
	 * @return value for penv
	 */
	@XmlElement
	public Float getPenv() {
		return (expandLevel > 0) ? entity.getPenv() : null;
	}

	/**
	 * Setter for penv.
	 *
	 * @param value the value to set
	 */
	public void setPenv(Float value) {
		entity.setPenv(value);
	}

	/**
	 * Getter for soilProfile.
	 *
	 * @return value for soilProfile
	 */
	@XmlElement
	public SoilProfileConverter getSoilProfile() {
		if (expandLevel > 0) {
			if (entity.getSoilProfile() != null) {
				return new SoilProfileConverter(entity.getSoilProfile(), uri.resolve("soilProfile/"), expandLevel - 1, false);
			}
		}
		return null;
	}

	/**
	 * Setter for soilProfile.
	 *
	 * @param value the value to set
	 */
	public void setSoilProfile(SoilProfileConverter value) {
		entity.setSoilProfile((value != null) ? value.getEntity() : null);
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
	 * Sets the URI for this reference converter.
	 *
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

	/**
	 * Returns the SoilProfileLayer entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public SoilProfileLayer getEntity() {
		if (entity.getSoilProfileLayerPK() == null) {
			SoilProfileLayerConverter converter = UriResolver.getInstance().resolve(SoilProfileLayerConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved SoilProfileLayer entity.
	 *
	 * @return an resolved entity
	 */
	public SoilProfileLayer resolveEntity(EntityManager em) {
		SoilProfile soilProfile = entity.getSoilProfile();
		if (soilProfile != null) {
			entity.setSoilProfile(em.getReference(SoilProfile.class, soilProfile.getSid()));
		}
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoilProfileLayerConverter)) {
			return false;
		}
		SoilProfileLayerConverter other = (SoilProfileLayerConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (expandLevel <= 0) {
			return true;
		}
		if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		if (expandLevel <= 0) {
			return hash + 37 * expandLevel;
		}
		return hash + 37 * (expandLevel + 37 * (entity == null ? 0 : entity.hashCode()));
	}
}
