package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FILAS_PEDIDOS_CLIENTE database table.
 * 
 */
@Embeddable
public class FilasPedidosClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PEDIDO", insertable=false, updatable=false)
	private int pedido;

	@Column(name="ARTICULO", insertable=false, updatable=false)
	private int articulo;

	public FilasPedidosClientePK() {
	}
	public int getPedido() {
		return this.pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
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
		if (!(other instanceof FilasPedidosClientePK)) {
			return false;
		}
		FilasPedidosClientePK castOther = (FilasPedidosClientePK)other;
		return 
			(this.pedido == castOther.pedido)
			&& (this.articulo == castOther.articulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pedido;
		hash = hash * prime + this.articulo;
		
		return hash;
	}
}