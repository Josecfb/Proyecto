package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LOTES database table.
 * 
 */
@Entity
@Table(name="LOTES")
@NamedQuery(name="Lote.findAll", query="SELECT l FROM Lote l")
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM_LOTE")
	private int numLote;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CADUCIDAD")
	private Date fechaCaducidad;

	@Column(name="LOTE")
	private String lote;

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

	public List<FilasAlbaranCliente> getFilasAlbaranClientes() {
		return this.filasAlbaranClientes;
	}

	public void setFilasAlbaranClientes(List<FilasAlbaranCliente> filasAlbaranClientes) {
		this.filasAlbaranClientes = filasAlbaranClientes;
	}

	public FilasAlbaranCliente addFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().add(filasAlbaranCliente);
		filasAlbaranCliente.setLoteBean(this);

		return filasAlbaranCliente;
	}

	public FilasAlbaranCliente removeFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().remove(filasAlbaranCliente);
		filasAlbaranCliente.setLoteBean(null);

		return filasAlbaranCliente;
	}

	public List<FilasAlbaranProveedor> getFilasAlbaranProveedors() {
		return this.filasAlbaranProveedors;
	}

	public void setFilasAlbaranProveedors(List<FilasAlbaranProveedor> filasAlbaranProveedors) {
		this.filasAlbaranProveedors = filasAlbaranProveedors;
	}

	public FilasAlbaranProveedor addFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().add(filasAlbaranProveedor);
		filasAlbaranProveedor.setLoteBean(this);

		return filasAlbaranProveedor;
	}

	public FilasAlbaranProveedor removeFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().remove(filasAlbaranProveedor);
		filasAlbaranProveedor.setLoteBean(null);

		return filasAlbaranProveedor;
	}

	public List<FilasFacturasCliente> getFilasFacturasClientes() {
		return this.filasFacturasClientes;
	}

	public void setFilasFacturasClientes(List<FilasFacturasCliente> filasFacturasClientes) {
		this.filasFacturasClientes = filasFacturasClientes;
	}

	public FilasFacturasCliente addFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().add(filasFacturasCliente);
		filasFacturasCliente.setLoteBean(this);

		return filasFacturasCliente;
	}

	public FilasFacturasCliente removeFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().remove(filasFacturasCliente);
		filasFacturasCliente.setLoteBean(null);

		return filasFacturasCliente;
	}

	public List<FilasFacturasProveedor> getFilasFacturasProveedors() {
		return this.filasFacturasProveedors;
	}

	public void setFilasFacturasProveedors(List<FilasFacturasProveedor> filasFacturasProveedors) {
		this.filasFacturasProveedors = filasFacturasProveedors;
	}

	public FilasFacturasProveedor addFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().add(filasFacturasProveedor);
		filasFacturasProveedor.setLoteBean(this);

		return filasFacturasProveedor;
	}

	public FilasFacturasProveedor removeFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().remove(filasFacturasProveedor);
		filasFacturasProveedor.setLoteBean(null);

		return filasFacturasProveedor;
	}

}