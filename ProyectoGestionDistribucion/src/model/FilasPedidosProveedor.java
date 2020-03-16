package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FILAS_PEDIDOS_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FILAS_PEDIDOS_PROVEEDOR")
@NamedQuery(name="FilasPedidosProveedor.findAll", query="SELECT f FROM FilasPedidosProveedor f")
public class FilasPedidosProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasPedidosProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	//bi-directional many-to-one association to PedidosProveedor
	@ManyToOne
	@JoinColumn(name="PEDIDO")
	private PedidosProveedor pedidosProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilasPedidosProveedor() {
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

	public PedidosProveedor getPedidosProveedor() {
		return this.pedidosProveedor;
	}

	public void setPedidosProveedor(PedidosProveedor pedidosProveedor) {
		this.pedidosProveedor = pedidosProveedor;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

}