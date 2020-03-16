package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FILAS_ALBARAN_PROVEEDOR database table.
 * 
 */
@Embeddable
public class FilasAlbaranProveedorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ALBARAN", insertable=false, updatable=false)
	private int albaran;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	@Column(name="LOTE", insertable=false, updatable=false)
	private int lote;

	public FilasAlbaranProveedorPK() {
	}
	public int getAlbaran() {
		return this.albaran;
	}
	public void setAlbaran(int albaran) {
		this.albaran = albaran;
	}
	public int getArticulo() {
		return this.articulo;
	}
	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}
	public int getLote() {
		return this.lote;
	}
	public void setLote(int lote) {
		this.lote = lote;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilasAlbaranProveedorPK)) {
			return false;
		}
		FilasAlbaranProveedorPK castOther = (FilasAlbaranProveedorPK)other;
		return 
			(this.albaran == castOther.albaran)
			&& (this.articulo == castOther.articulo)
			&& (this.lote == castOther.lote);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.albaran;
		hash = hash * prime + this.articulo;
		hash = hash * prime + this.lote;
		
		return hash;
	}
}