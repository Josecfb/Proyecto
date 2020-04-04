package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILAS_FACTURAS_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FILAS_FACTURAS_PROVEEDOR")
@NamedQuery(name="FilasFacturasProveedor.findAll", query="SELECT f FROM FilasFacturasProveedor f")
public class FilasFacturasProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasFacturasProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to FacturasProveedor
	@ManyToOne
	@JoinColumn(name="FACTURA")
	private FacturasProveedor facturasProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilasFacturasProveedor() {
	}

	public FilasFacturasProveedorPK getId() {
		return this.id;
	}

	public void setId(FilasFacturasProveedorPK id) {
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

	public FacturasProveedor getFacturasProveedor() {
		return this.facturasProveedor;
	}

	public void setFacturasProveedor(FacturasProveedor facturasProveedor) {
		this.facturasProveedor = facturasProveedor;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

}