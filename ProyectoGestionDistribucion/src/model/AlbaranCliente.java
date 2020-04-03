package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ALBARAN_CLIENTE database table.
 * 
 */
@Entity
@Table(name="ALBARAN_CLIENTE")
@NamedQuery(name="AlbaranCliente.findAll", query="SELECT a FROM AlbaranCliente a")
public class AlbaranCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NUM")
	private int num;

	@Column(name="ENTREGADO")
	private byte entregado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente clienteBean;

	//bi-directional many-to-one association to FacturasCliente
	@ManyToOne
	@JoinColumn(name="NUM_FACTURA")
	private FacturasCliente facturasCliente;

	//bi-directional many-to-one association to FilasAlbaranCliente
	@OneToMany(mappedBy="albaranCliente")
	private List<FilasAlbaranCliente> filasAlbaranClientes;

	//bi-directional many-to-one association to PedidosCliente
	@OneToMany(mappedBy="albaranCliente")
	private List<PedidosCliente> pedidosClientes;

	public AlbaranCliente() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public byte getEntregado() {
		return this.entregado;
	}

	public void setEntregado(byte entregado) {
		this.entregado = entregado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public FacturasCliente getFacturasCliente() {
		return this.facturasCliente;
	}

	public void setFacturasCliente(FacturasCliente facturasCliente) {
		this.facturasCliente = facturasCliente;
	}

	public List<FilasAlbaranCliente> getFilasAlbaranClientes() {
		return this.filasAlbaranClientes;
	}

	public void setFilasAlbaranClientes(List<FilasAlbaranCliente> filasAlbaranClientes) {
		this.filasAlbaranClientes = filasAlbaranClientes;
	}

	public FilasAlbaranCliente addFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().add(filasAlbaranCliente);
		filasAlbaranCliente.setAlbaranCliente(this);

		return filasAlbaranCliente;
	}

	public FilasAlbaranCliente removeFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().remove(filasAlbaranCliente);
		filasAlbaranCliente.setAlbaranCliente(null);

		return filasAlbaranCliente;
	}

	public List<PedidosCliente> getPedidosClientes() {
		return this.pedidosClientes;
	}

	public void setPedidosClientes(List<PedidosCliente> pedidosClientes) {
		this.pedidosClientes = pedidosClientes;
	}

	public PedidosCliente addPedidosCliente(PedidosCliente pedidosCliente) {
		getPedidosClientes().add(pedidosCliente);
		pedidosCliente.setAlbaranCliente(this);

		return pedidosCliente;
	}

	public PedidosCliente removePedidosCliente(PedidosCliente pedidosCliente) {
		getPedidosClientes().remove(pedidosCliente);
		pedidosCliente.setAlbaranCliente(null);

		return pedidosCliente;
	}

}