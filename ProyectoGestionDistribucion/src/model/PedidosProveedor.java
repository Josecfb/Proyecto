package model;

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
@NamedQuery(name="PedidosProveedor.findAll", query="SELECT p FROM PedidosProveedor p")
public class PedidosProveedor implements Serializable {
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
	@OneToMany(mappedBy="pedidosProveedor")
	private List<FilasPedidosProveedor> filasPedidosProveedors;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedore;

	//bi-directional many-to-one association to AlbaranesProveedor
	@ManyToOne
	@JoinColumn(name="NUM_ALBARAN")
	private AlbaranesProveedor albaranesProveedor;

	public PedidosProveedor() {
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

	public List<FilasPedidosProveedor> getFilasPedidosProveedors() {
		return this.filasPedidosProveedors;
	}

	public void setFilasPedidosProveedors(List<FilasPedidosProveedor> filasPedidosProveedors) {
		this.filasPedidosProveedors = filasPedidosProveedors;
	}

	public FilasPedidosProveedor addFilasPedidosProveedor(FilasPedidosProveedor filasPedidosProveedor) {
		getFilasPedidosProveedors().add(filasPedidosProveedor);
		filasPedidosProveedor.setPedidosProveedor(this);

		return filasPedidosProveedor;
	}

	public FilasPedidosProveedor removeFilasPedidosProveedor(FilasPedidosProveedor filasPedidosProveedor) {
		getFilasPedidosProveedors().remove(filasPedidosProveedor);
		filasPedidosProveedor.setPedidosProveedor(null);

		return filasPedidosProveedor;
	}

	public Proveedor getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedor proveedore) {
		this.proveedore = proveedore;
	}

	public AlbaranesProveedor getAlbaranesProveedor() {
		return this.albaranesProveedor;
	}

	public void setAlbaranesProveedor(AlbaranesProveedor albaranesProveedor) {
		this.albaranesProveedor = albaranesProveedor;
	}

}