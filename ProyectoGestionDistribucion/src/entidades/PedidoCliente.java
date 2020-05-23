package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PEDIDOS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="PEDIDOS_CLIENTE")
@NamedQuery(name="PedidoCliente.findAll", query="SELECT p FROM PedidoCliente p")
public class PedidoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Column(name="CONFIRMADO")
	private boolean confirmado;
	
	@Column(name="ENVIADO")
	private boolean enviado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	//bi-directional many-to-one association to FilasPedidosCliente
	@OneToMany(mappedBy="pedidosCliente")
	private List<FilasPedidosCliente> filasPedidosClientes;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente clienteBean;

	//bi-directional many-to-one association to AlbaranCliente
	@ManyToOne
	@JoinColumn(name="NUM_ALBARAN")
	private AlbaranCliente albaranCliente;

	public PedidoCliente() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean getConfirmado() {
		return this.confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<FilasPedidosCliente> getFilasPedidosClientes() {
		return this.filasPedidosClientes;
	}

	public void setFilasPedidosClientes(List<FilasPedidosCliente> filasPedidosClientes) {
		this.filasPedidosClientes = filasPedidosClientes;
	}

	public FilasPedidosCliente addFilasPedidosCliente(FilasPedidosCliente filasPedidosCliente) {
		getFilasPedidosClientes().add(filasPedidosCliente);
		filasPedidosCliente.setPedidosCliente(this);

		return filasPedidosCliente;
	}

	public FilasPedidosCliente removeFilasPedidosCliente(FilasPedidosCliente filasPedidosCliente) {
		getFilasPedidosClientes().remove(filasPedidosCliente);
		filasPedidosCliente.setPedidosCliente(null);

		return filasPedidosCliente;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public AlbaranCliente getAlbaranCliente() {
		return this.albaranCliente;
	}

	public void setAlbaranCliente(AlbaranCliente albaranCliente) {
		this.albaranCliente = albaranCliente;
	}

	public boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	

}