package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROVEEDORES database table.
 * 
 */
@Entity
@Table(name="PROVEEDORES")
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NUMERO")
	private int numero;

	@Column(name="COD_POST")
	private String codPost;

	@Column(name="DIRECCION")
	private String direccion;

	@Column(name="EMAIL")
	private String email;

	@Column(name="NIF")
	private String nif;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="NUM_CUENTA_CONTABLE")
	private int numCuentaContable;
	
	@Column(name="TELEFONO_FIJO")
	private String telefonoFijo;
	
	@Column(name="TELEFONO_MOVIL")
	private String telefonoMovil;
	
	@Column(name="PROVINCIA")
	private String provincia;
	
	@Column(name="POBLACION")
	private String poblacion;

	//bi-directional many-to-one association to AlbaranesProveedor
	@OneToMany(mappedBy="proveedore")
	private List<AlbaranProveedor> albaranesProveedors;

	//bi-directional many-to-one association to FacturasProveedor
	@OneToMany(mappedBy="proveedore")
	private List<FacturaProveedor> facturasProveedors;

	//bi-directional many-to-one association to PedidosProveedor
	@OneToMany(mappedBy="proveedore")
	private List<PedidoProveedor> pedidosProveedors;
	
	@OneToMany(mappedBy="proveedorBean")
	private List<Articulo> articulosPro;

	public Proveedor() {
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCodPost() {
		return this.codPost;
	}

	public void setCodPost(String codPost) {
		this.codPost = codPost;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumCuentaContable() {
		return this.numCuentaContable;
	}

	public void setNumCuentaContable(int numCuentaContable) {
		this.numCuentaContable = numCuentaContable;
	}
	
	

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public List<AlbaranProveedor> getAlbaranesProveedors() {
		return this.albaranesProveedors;
	}

	public void setAlbaranesProveedors(List<AlbaranProveedor> albaranesProveedors) {
		this.albaranesProveedors = albaranesProveedors;
	}

	public AlbaranProveedor addAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		getAlbaranesProveedors().add(albaranesProveedor);
		albaranesProveedor.setProveedore(this);

		return albaranesProveedor;
	}

	public AlbaranProveedor removeAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		getAlbaranesProveedors().remove(albaranesProveedor);
		albaranesProveedor.setProveedore(null);

		return albaranesProveedor;
	}

	public List<FacturaProveedor> getFacturasProveedors() {
		return this.facturasProveedors;
	}

	public void setFacturasProveedors(List<FacturaProveedor> facturasProveedors) {
		this.facturasProveedors = facturasProveedors;
	}

	public FacturaProveedor addFacturasProveedor(FacturaProveedor facturasProveedor) {
		getFacturasProveedors().add(facturasProveedor);
		facturasProveedor.setProveedore(this);

		return facturasProveedor;
	}

	public FacturaProveedor removeFacturasProveedor(FacturaProveedor facturasProveedor) {
		getFacturasProveedors().remove(facturasProveedor);
		facturasProveedor.setProveedore(null);

		return facturasProveedor;
	}

	public List<PedidoProveedor> getPedidosProveedors() {
		return this.pedidosProveedors;
	}

	public void setPedidosProveedors(List<PedidoProveedor> pedidosProveedors) {
		this.pedidosProveedors = pedidosProveedors;
	}

	public PedidoProveedor addPedidosProveedor(PedidoProveedor pedidosProveedor) {
		getPedidosProveedors().add(pedidosProveedor);
		pedidosProveedor.setProveedore(this);

		return pedidosProveedor;
	}

	public PedidoProveedor removePedidosProveedor(PedidoProveedor pedidosProveedor) {
		getPedidosProveedors().remove(pedidosProveedor);
		pedidosProveedor.setProveedore(null);

		return pedidosProveedor;
	}

	@Override
	public String toString() {
		return nombre+"..";
	}

}