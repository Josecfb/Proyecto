package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PEDIDOS_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="PEDIDOS_PROVEEDOR")
@NamedQuery(name="PedidoProveedor.findAll", query="SELECT p FROM PedidoProveedor p")
public class PedidoProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM")
	private int num;

	@Column(name="CONFIRMADO")
	private boolean confirmado;

	@Column(name="ENVIADO")
	private boolean enviado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	//bi-directional many-to-one association to FilasPedidosProveedor
	@OneToMany(mappedBy="pedidosProveedor",cascade = CascadeType.ALL)
	private List<FilaPedidoProveedor> filaPedidoProveedors;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedore;

	//bi-directional many-to-one association to AlbaranesProveedor
	@ManyToOne
	@JoinColumn(name="NUM_ALBARAN")
	private AlbaranProveedor albaranesProveedor;

	public PedidoProveedor() {
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

	public boolean getEnviado() {
		return this.enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<FilaPedidoProveedor> getFilaPedidoProveedor() {
		return this.filaPedidoProveedors;
	}

	public void setFilaPedidoProveedor(List<FilaPedidoProveedor> filasPedidosProveedors) {
		this.filaPedidoProveedors = filasPedidosProveedors;
	}

	public FilaPedidoProveedor addFilasPedidosProveedor(FilaPedidoProveedor filasPedidosProveedor) {
		getFilaPedidoProveedor().add(filasPedidosProveedor);
		filasPedidosProveedor.setPedidosProveedor(this);

		return filasPedidosProveedor;
	}

	public FilaPedidoProveedor removeFilasPedidosProveedor(FilaPedidoProveedor filasPedidosProveedor) {
		getFilaPedidoProveedor().remove(filasPedidosProveedor);
		filasPedidosProveedor.setPedidosProveedor(null);

		return filasPedidosProveedor;
	}

	public Proveedor getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedor proveedore) {
		this.proveedore = proveedore;
	}

	public AlbaranProveedor getAlbaranesProveedor() {
		return this.albaranesProveedor;
	}

	public void setAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		this.albaranesProveedor = albaranesProveedor;
	}

}