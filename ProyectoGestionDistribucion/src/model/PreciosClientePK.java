package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRECIOS_CLIENTE database table.
 * 
 */
@Embeddable
public class PreciosClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	@Column(name="CLIENTE", insertable=false, updatable=false)
	private int cliente;

	public PreciosClientePK() {
	}
	public int getArticulo() {
		return this.articulo;
	}
	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}
	public int getCliente() {
		return this.cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PreciosClientePK)) {
			return false;
		}
		PreciosClientePK castOther = (PreciosClientePK)other;
		return 
			(this.articulo == castOther.articulo)
			&& (this.cliente == castOther.cliente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.articulo;
		hash = hash * prime + this.cliente;
		
		return hash;
	}
}