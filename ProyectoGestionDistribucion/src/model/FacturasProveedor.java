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
@NamedQuery(name="FacturasProveedor.findAll", query="SELECT f FROM FacturasProveedor f")
public class FacturasProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="PAGADA")
	private byte pagada;

	//bi-directional many-to-one association to AlbaranesProveedor
	@OneToMany(mappedBy="facturasProveedor")
	private List<AlbaranesProveedor> albaranesProveedors;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedore;

	//bi-directional many-to-one association to FilasFacturasProveedor
	@OneToMany(mappedBy="facturasProveedor")
	private List<FilasFacturasProveedor> filasFacturasProveedors;

	public FacturasProveedor() {
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

	public byte getPagada() {
		return this.pagada;
	}

	public void setPagada(byte pagada) {
		this.pagada = pagada;
	}

	public List<AlbaranesProveedor> getAlbaranesProveedors() {
		return this.albaranesProveedors;
	}

	public void setAlbaranesProveedors(List<AlbaranesProveedor> albaranesProveedors) {
		this.albaranesProveedors = albaranesProveedors;
	}

	public AlbaranesProveedor addAlbaranesProveedor(AlbaranesProveedor albaranesProveedor) {
		getAlbaranesProveedors().add(albaranesProveedor);
		albaranesProveedor.setFacturasProveedor(this);

		return albaranesProveedor;
	}

	public AlbaranesProveedor removeAlbaranesProveedor(AlbaranesProveedor albaranesProveedor) {
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

	public List<FilasFacturasProveedor> getFilasFacturasProveedors() {
		return this.filasFacturasProveedors;
	}

	public void setFilasFacturasProveedors(List<FilasFacturasProveedor> filasFacturasProveedors) {
		this.filasFacturasProveedors = filasFacturasProveedors;
	}

	public FilasFacturasProveedor addFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().add(filasFacturasProveedor);
		filasFacturasProveedor.setFacturasProveedor(this);

		return filasFacturasProveedor;
	}

	public FilasFacturasProveedor removeFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().remove(filasFacturasProveedor);
		filasFacturasProveedor.setFacturasProveedor(null);

		return filasFacturasProveedor;
	}

}