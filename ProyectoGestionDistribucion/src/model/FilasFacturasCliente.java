package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILAS_FACTURAS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="FILAS_FACTURAS_CLIENTE")
@NamedQuery(name="FilasFacturasCliente.findAll", query="SELECT f FROM FilasFacturasCliente f")
public class FilasFacturasCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasFacturasClientePK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to FacturasCliente
	@ManyToOne
	@JoinColumn(name="FACTURA")
	private FacturasCliente facturasCliente;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	//bi-directional many-to-one association to Lote
	@ManyToOne
	@JoinColumn(name="LOTE")
	private Lote loteBean;

	public FilasFacturasCliente() {
	}

	public FilasFacturasClientePK getId() {
		return this.id;
	}

	public void setId(FilasFacturasClientePK id) {
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

	public FacturasCliente getFacturasCliente() {
		return this.facturasCliente;
	}

	public void setFacturasCliente(FacturasCliente facturasCliente) {
		this.facturasCliente = facturasCliente;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

	public Lote getLoteBean() {
		return this.loteBean;
	}

	public void setLoteBean(Lote loteBean) {
		this.loteBean = loteBean;
	}

}