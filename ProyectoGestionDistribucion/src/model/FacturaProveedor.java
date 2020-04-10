package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FACTURAS_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FACTURAS_PROVEEDOR")
@NamedQuery(name="FacturaProveedor.findAll", query="SELECT f FROM FacturaProveedor f")
public class FacturaProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NUM")
	private int num;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="PAGADA")
	private boolean pagada;

	//bi-directional many-to-one association to AlbaranesProveedor
	@OneToMany(mappedBy="facturasProveedor")
	private List<AlbaranProveedor> albaranesProveedors;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedore;

	//bi-directional many-to-one association to FilasFacturasProveedor
	@OneToMany(mappedBy="facturasProveedor",cascade = CascadeType.ALL)
	private List<FilaFacturaProveedor> filasFacturasProveedors;

	public FacturaProveedor() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public List<AlbaranProveedor> getAlbaranesProveedors() {
		return this.albaranesProveedors;
	}

	public void setAlbaranesProveedors(List<AlbaranProveedor> albaranesProveedors) {
		this.albaranesProveedors = albaranesProveedors;
	}

	public AlbaranProveedor addAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		getAlbaranesProveedors().add(albaranesProveedor);
		albaranesProveedor.setFacturasProveedor(this);

		return albaranesProveedor;
	}

	public AlbaranProveedor removeAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		getAlbaranesProveedors().remove(albaranesProveedor);
		albaranesProveedor.setFacturasProveedor(null);

		return albaranesProveedor;
	}

	public Proveedor getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedor proveedore) {
		this.proveedore = proveedore;
	}

	public List<FilaFacturaProveedor> getFilasFacturasProveedors() {
		return this.filasFacturasProveedors;
	}

	public void setFilasFacturasProveedors(List<FilaFacturaProveedor> filasFacturasProveedors) {
		this.filasFacturasProveedors = filasFacturasProveedors;
	}

	public FilaFacturaProveedor addFilasFacturasProveedor(FilaFacturaProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().add(filasFacturasProveedor);
		filasFacturasProveedor.setFacturasProveedor(this);

		return filasFacturasProveedor;
	}

	public FilaFacturaProveedor removeFilasFacturasProveedor(FilaFacturaProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().remove(filasFacturasProveedor);
		filasFacturasProveedor.setFacturasProveedor(null);

		return filasFacturasProveedor;
	}

}