package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FILAS_FACTURAS_PROVEEDOR database table.
 * 
 */
@Embeddable
public class FilasFacturasProveedorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FACTURA", insertable=false, updatable=false)
	private int factura;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	public FilasFacturasProveedorPK() {
	}
	public int getFactura() {
		return this.factura;
	}
	public void setFactura(int factura) {
		this.factura = factura;
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
		if (!(other instanceof FilasFacturasProveedorPK)) {
			return false;
		}
		FilasFacturasProveedorPK castOther = (FilasFacturasProveedorPK)other;
		return 
			(this.factura == castOther.factura)
			&& (this.articulo == castOther.articulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.factura;
		hash = hash * prime + this.articulo;

		return hash;
	}
}