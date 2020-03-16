package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FILAS_FACTURAS_CLIENTE database table.
 * 
 */
@Embeddable
public class FilasFacturasClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FACTURA", insertable=false, updatable=false)
	private int factura;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	@Column(name="LOTE", insertable=false, updatable=false)
	private int lote;

	public FilasFacturasClientePK() {
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
		if (!(other instanceof FilasFacturasClientePK)) {
			return false;
		}
		FilasFacturasClientePK castOther = (FilasFacturasClientePK)other;
		return 
			(this.factura == castOther.factura)
			&& (this.articulo == castOther.articulo)
			&& (this.lote == castOther.lote);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.factura;
		hash = hash * prime + this.articulo;
		hash = hash * prime + this.lote;
		
		return hash;
	}
}