package entidades;

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

	@Column(name="FACTURADO")
	private boolean facturado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente clienteBean;
	
	@Column(name="ACTUALIZADO_ALMACEN")
	private boolean actualizadoAlmacen;

	//bi-directional many-to-one association to FacturasCliente
	@ManyToOne
	@JoinColumn(name="NUM_FACTURA")
	private FacturasCliente facturasCliente;

	//bi-directional many-to-one association to FilasAlbaranCliente
	@OneToMany(mappedBy="albaranCliente")
	private List<FilasAlbaranCliente> filasAlbaranClientes;

	//bi-directional many-to-one association to PedidosCliente
	@OneToMany(mappedBy="albaranCliente")
	private List<PedidoCliente> pedidosClientes;

	public AlbaranCliente() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean getFacturado() {
		return this.facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
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

	public List<PedidoCliente> getPedidosClientes() {
		return this.pedidosClientes;
	}

	public void setPedidosClientes(List<PedidoCliente> pedidosClientes) {
		this.pedidosClientes = pedidosClientes;
	}

	public PedidoCliente addPedidosCliente(PedidoCliente pedidosCliente) {
		getPedidosClientes().add(pedidosCliente);
		pedidosCliente.setAlbaranCliente(this);

		return pedidosCliente;
	}

	public PedidoCliente removePedidosCliente(PedidoCliente pedidosCliente) {
		getPedidosClientes().remove(pedidosCliente);
		pedidosCliente.setAlbaranCliente(null);

		return pedidosCliente;
	}

	public boolean isActualizadoAlmacen() {
		return actualizadoAlmacen;
	}

	public void setActualizadoAlmacen(boolean actualizadoAlmacen) {
		this.actualizadoAlmacen = actualizadoAlmacen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbaranCliente other = (AlbaranCliente) obj;
		if (num != other.num)
			return false;
		return true;
	}
	
	

}