package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILAS_ALBARAN_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FILAS_ALBARAN_PROVEEDOR")
@NamedQuery(name="FilasAlbaranProveedor.findAll", query="SELECT f FROM FilasAlbaranProveedor f")
public class FilasAlbaranProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasAlbaranProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to AlbaranesProveedor
	@ManyToOne
	@JoinColumn(name="ALBARAN")
	private AlbaranProveedor albaranesProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	//bi-directional many-to-one association to Lote
	@ManyToOne
	@JoinColumn(name="LOTE")
	private Lote loteBean;

	public FilasAlbaranProveedor() {
	}

	public FilasAlbaranProveedorPK getId() {
		return this.id;
	}

	public void setId(FilasAlbaranProveedorPK id) {
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

	public AlbaranProveedor getAlbaranesProveedor() {
		return this.albaranesProveedor;
	}

	public void setAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		this.albaranesProveedor = albaranesProveedor;
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