package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ALBARANES_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="ALBARANES_PROVEEDOR")
@NamedQuery(name="AlbaranesProveedor.findAll", query="SELECT a FROM AlbaranesProveedor a")
public class AlbaranesProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Column(name="ACTUALIZADO_ALMACEN")
	private byte actualizadoAlmacen;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedore;

	//bi-directional many-to-one association to FacturasProveedor
	@ManyToOne
	@JoinColumn(name="NUM_FACTURA")
	private FacturasProveedor facturasProveedor;

	//bi-directional many-to-one association to FilasAlbaranProveedor
	@OneToMany(mappedBy="albaranesProveedor")
	private List<FilasAlbaranProveedor> filasAlbaranProveedors;

	//bi-directional many-to-one association to PedidosProveedor
	@OneToMany(mappedBy="albaranesProveedor")
	private List<PedidosProveedor> pedidosProveedors;

	public AlbaranesProveedor() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public byte getActualizadoAlmacen() {
		return this.actualizadoAlmacen;
	}

	public void setActualizadoAlmacen(byte actualizadoAlmacen) {
		this.actualizadoAlmacen = actualizadoAlmacen;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Proveedor getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedor proveedore) {
		this.proveedore = proveedore;
	}

	public FacturasProveedor getFacturasProveedor() {
		return this.facturasProveedor;
	}

	public void setFacturasProveedor(FacturasProveedor facturasProveedor) {
		this.facturasProveedor = facturasProveedor;
	}

	public List<FilasAlbaranProveedor> getFilasAlbaranProveedors() {
		return this.filasAlbaranProveedors;
	}

	public void setFilasAlbaranProveedors(List<FilasAlbaranProveedor> filasAlbaranProveedors) {
		this.filasAlbaranProveedors = filasAlbaranProveedors;
	}

	public FilasAlbaranProveedor addFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().add(filasAlbaranProveedor);
		filasAlbaranProveedor.setAlbaranesProveedor(this);

		return filasAlbaranProveedor;
	}

	public FilasAlbaranProveedor removeFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().remove(filasAlbaranProveedor);
		filasAlbaranProveedor.setAlbaranesProveedor(null);

		return filasAlbaranProveedor;
	}

	public List<PedidosProveedor> getPedidosProveedors() {
		return this.pedidosProveedors;
	}

	public void setPedidosProveedors(List<PedidosProveedor> pedidosProveedors) {
		this.pedidosProveedors = pedidosProveedors;
	}

	public PedidosProveedor addPedidosProveedor(PedidosProveedor pedidosProveedor) {
		getPedidosProveedors().add(pedidosProveedor);
		pedidosProveedor.setAlbaranesProveedor(this);

		return pedidosProveedor;
	}

	public PedidosProveedor removePedidosProveedor(PedidosProveedor pedidosProveedor) {
		getPedidosProveedors().remove(pedidosProveedor);
		pedidosProveedor.setAlbaranesProveedor(null);

		return pedidosProveedor;
	}

}