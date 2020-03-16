package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILAS_PEDIDOS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="FILAS_PEDIDOS_CLIENTE")
@NamedQuery(name="FilasPedidosCliente.findAll", query="SELECT f FROM FilasPedidosCliente f")
public class FilasPedidosCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasPedidosClientePK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to PedidosCliente
	@ManyToOne
	@JoinColumn(name="PEDIDO")
	private PedidosCliente pedidosCliente;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilasPedidosCliente() {
	}

	public FilasPedidosClientePK getId() {
		return this.id;
	}

	public void setId(FilasPedidosClientePK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public PedidosCliente getPedidosCliente() {
		return this.pedidosCliente;
	}

	public void setPedidosCliente(PedidosCliente pedidosCliente) {
		this.pedidosCliente = pedidosCliente;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

}