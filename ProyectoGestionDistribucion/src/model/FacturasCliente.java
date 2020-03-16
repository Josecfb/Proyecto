package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FACTURAS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="FACTURAS_CLIENTE")
@NamedQuery(name="FacturasCliente.findAll", query="SELECT f FROM FacturasCliente f")
public class FacturasCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Column(name="CLIENTE")
	private int cliente;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="PAGADA")
	private byte pagada;

	//bi-directional many-to-one association to AlbaranCliente
	@OneToMany(mappedBy="facturasCliente")
	private List<AlbaranCliente> albaranClientes;

	//bi-directional many-to-one association to FilasFacturasCliente
	@OneToMany(mappedBy="facturasCliente")
	private List<FilasFacturasCliente> filasFacturasClientes;

	public FacturasCliente() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCliente() {
		return this.cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public byte getPagada() {
		return this.pagada;
	}

	public void setPagada(byte pagada) {
		this.pagada = pagada;
	}

	public List<AlbaranCliente> getAlbaranClientes() {
		return this.albaranClientes;
	}

	public void setAlbaranClientes(List<AlbaranCliente> albaranClientes) {
		this.albaranClientes = albaranClientes;
	}

	public AlbaranCliente addAlbaranCliente(AlbaranCliente albaranCliente) {
		getAlbaranClientes().add(albaranCliente);
		albaranCliente.setFacturasCliente(this);

		return albaranCliente;
	}

	public AlbaranCliente removeAlbaranCliente(AlbaranCliente albaranCliente) {
		getAlbaranClientes().remove(albaranCliente);
		albaranCliente.setFacturasCliente(null);

		return albaranCliente;
	}

	public List<FilasFacturasCliente> getFilasFacturasClientes() {
		return this.filasFacturasClientes;
	}

	public void setFilasFacturasClientes(List<FilasFacturasCliente> filasFacturasClientes) {
		this.filasFacturasClientes = filasFacturasClientes;
	}

	public FilasFacturasCliente addFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().add(filasFacturasCliente);
		filasFacturasCliente.setFacturasCliente(this);

		return filasFacturasCliente;
	}

	public FilasFacturasCliente removeFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().remove(filasFacturasCliente);
		filasFacturasCliente.setFacturasCliente(null);

		return filasFacturasCliente;
	}

}