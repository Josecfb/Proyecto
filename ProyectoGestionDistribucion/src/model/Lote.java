package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lotes database table.
 * 
 */
@Entity
@Table(name="lotes")
@NamedQuery(name="Lote.findAll", query="SELECT l FROM Lote l")
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM_LOTE")
	private int numLote;

	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CADUCIDAD")
	private Date fechaCaducidad;

	private String lote;

	private int stock;
	
	//bi-directional many-to-one association to FilasAlbaranCliente
	@OneToMany(mappedBy="loteBean")
	private List<FilasAlbaranCliente> filasAlbaranClientes;

	//bi-directional many-to-one association to FilasAlbaranProveedor
	@OneToMany(mappedBy="loteBean")
	private List<FilasAlbaranProveedor> filasAlbaranProveedors;

	//bi-directional many-to-one association to FilasFacturasCliente
	@OneToMany(mappedBy="loteBean")
	private List<FilasFacturasCliente> filasFacturasClientes;

	//bi-directional many-to-one association to FilasFacturasProveedor
	@OneToMany(mappedBy="loteBean")
	private List<FilasFacturasProveedor> filasFacturasProveedors;

	public Lote() {
	}

	public int getNumLote() {
		return this.numLote;
	}

	public void setNumLote(int numLote) {
		this.numLote = numLote;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}