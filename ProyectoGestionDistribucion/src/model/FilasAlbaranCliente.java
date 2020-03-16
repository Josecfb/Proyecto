package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILAS_ALBARAN_CLIENTE database table.
 * 
 */
@Entity
@Table(name="FILAS_ALBARAN_CLIENTE")
@NamedQuery(name="FilasAlbaranCliente.findAll", query="SELECT f FROM FilasAlbaranCliente f")
public class FilasAlbaranCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasAlbaranClientePK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to AlbaranCliente
	@ManyToOne
	@JoinColumn(name="ALBARAN")
	private AlbaranCliente albaranCliente;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	//bi-directional many-to-one association to Lote
	@ManyToOne
	@JoinColumn(name="LOTE")
	private Lote loteBean;

	public FilasAlbaranCliente() {
	}

	public FilasAlbaranClientePK getId() {
		return this.id;
	}

	public void setId(FilasAlbaranClientePK id) {
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

	public AlbaranCliente getAlbaranCliente() {
		return this.albaranCliente;
	}

	public void setAlbaranCliente(AlbaranCliente albaranCliente) {
		this.albaranCliente = albaranCliente;
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