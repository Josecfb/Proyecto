package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FILAS_ALBARAN_CLIENTE database table.
 * 
 */
@Embeddable
public class FilasAlbaranClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ALBARAN", insertable=false, updatable=false)
	private int albaran;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	public FilasAlbaranClientePK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilasAlbaranClientePK)) {
			return false;
		}
		FilasAlbaranClientePK castOther = (FilasAlbaranClientePK)other;
		return 
			(this.albaran == castOther.albaran)
			&& (this.articulo == castOther.articulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.albaran;
		hash = hash * prime + this.articulo;

		
		return hash;
	}
}