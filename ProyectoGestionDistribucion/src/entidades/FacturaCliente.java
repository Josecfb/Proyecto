package entidades;

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
@NamedQuery(name="FacturaCliente.findAll", query="SELECT f FROM FacturaCliente f")
public class FacturaCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NUM")
	private int num;

	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="PAGADA")
	private boolean pagada;

	//bi-directional many-to-one association to AlbaranCliente
	@OneToMany(mappedBy="facturasCliente")
	private List<AlbaranCliente> albaranClientes;

	//bi-directional many-to-one association to FilasFacturasCliente
	@OneToMany(mappedBy="facturasCliente",cascade = CascadeType.ALL)
	private List<FilaFacturaCliente> filasFacturasClientes;

	public FacturaCliente() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean getPagada() {
		return this.pagada;
	}

	public void setPagada(boolean pagada) {
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

	public List<FilaFacturaCliente> getFilasFacturasClientes() {
		return this.filasFacturasClientes;
	}

	public void setFilasFacturasClientes(List<FilaFacturaCliente> filasFacturasClientes) {
		this.filasFacturasClientes = filasFacturasClientes;
	}

	public FilaFacturaCliente addFilasFacturasCliente(FilaFacturaCliente filasFacturasCliente) {
		getFilasFacturasClientes().add(filasFacturasCliente);
		filasFacturasCliente.setFacturasCliente(this);

		return filasFacturasCliente;
	}

	public FilaFacturaCliente removeFilasFacturasCliente(FilaFacturaCliente filasFacturasCliente) {
		getFilasFacturasClientes().remove(filasFacturasCliente);
		filasFacturasCliente.setFacturasCliente(null);

		return filasFacturasCliente;
	}

}