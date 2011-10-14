package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fonini
 */
@Embeddable
public class SoilProfileLayerPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
	private int sid;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "soil_id", nullable = false, length = 255)
	private String soilId;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "soil_file_id", nullable = false, length = 255)
	private String soilFileId;
	@Basic(optional = false)
    @NotNull
    @Column(name = "sllb", nullable = false)
	private int sllb;

	public SoilProfileLayerPK() {
	}

	public SoilProfileLayerPK(int sid, String soilId, String soilFileId, int sllb) {
		this.sid = sid;
		this.soilId = soilId;
		this.soilFileId = soilFileId;
		this.sllb = sllb;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSoilId() {
		return soilId;
	}

	public void setSoilId(String soilId) {
		this.soilId = soilId;
	}

	public String getSoilFileId() {
		return soilFileId;
	}

	public void setSoilFileId(String soilFileId) {
		this.soilFileId = soilFileId;
	}

	public int getSllb() {
		return sllb;
	}

	public void setSllb(int sllb) {
		this.sllb = sllb;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) sid;
		hash += (soilId != null ? soilId.hashCode() : 0);
		hash += (soilFileId != null ? soilFileId.hashCode() : 0);
		hash += (int) sllb;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SoilProfileLayerPK)) {
			return false;
		}
		SoilProfileLayerPK other = (SoilProfileLayerPK) object;
		if (this.sid != other.sid) {
			return false;
		}
		if ((this.soilId == null && other.soilId != null) || (this.soilId != null && !this.soilId.equals(other.soilId))) {
			return false;
		}
		if ((this.soilFileId == null && other.soilFileId != null) || (this.soilFileId != null && !this.soilFileId.equals(other.soilFileId))) {
			return false;
		}
		if (this.sllb != other.sllb) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.SoilProfileLayerPK[ sid=" + sid + ", soilId=" + soilId + ", soilFileId=" + soilFileId + ", sllb=" + sllb + " ]";
	}

}
