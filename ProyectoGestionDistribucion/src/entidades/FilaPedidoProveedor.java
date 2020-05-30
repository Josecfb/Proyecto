package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FILAS_PEDIDOS_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FILAS_PEDIDOS_PROVEEDOR")
@NamedQuery(name="FilaPedidoProveedor.findAll", query="SELECT f FROM FilaPedidoProveedor f")
public class FilaPedidoProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasPedidosProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	//bi-directional many-to-one association to PedidosProveedor
	@ManyToOne
	@JoinColumn(name=("PEDIDO"))
	private PedidoProveedor pedidosProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilaPedidoProveedor() {
	}

	public FilasPedidosProveedorPK getId() {
		return this.id;
	}

	public void setId(FilasPedidosProveedorPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public PedidoProveedor getPedidosProveedor() {
		return this.pedidosProveedor;
	}

	public void setPedidosProveedor(PedidoProveedor pedidosProveedor) {
		this.pedidosProveedor = pedidosProveedor;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articuloBean == null) ? 0 : articuloBean.hashCode());
		result = prime * result + ((pedidosProveedor == null) ? 0 : pedidosProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilaPedidoProveedor other = (FilaPedidoProveedor) obj;
		if (articuloBean == null) {
			if (other.articuloBean != null)
				return false;
		} else if (!articuloBean.equals(other.articuloBean))
			return false;
		if (pedidosProveedor == null) {
			if (other.pedidosProveedor != null)
				return false;
		} else if (!pedidosProveedor.equals(other.pedidosProveedor))
			return false;
		return true;
	}



}