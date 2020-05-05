package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CLIENTES database table.
 * 
 */
@Entity
@Table(name="CLIENTES")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NUMERO")
	private int numero;

	@Column(name="APELLIDOS")
	private String apellidos;

	@Column(name="COD_POST")
	private String codPost;

	@Column(name="CONTRASENA")
	private int contrasena;

	@Column(name="DIRECCION")
	private String direccion;

	@Column(name="EMAIL")
	private String email;

	@Column(name="NIF_CIF")
	private String nifCif;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="NUM_CUENTA_CONTABLE")
	private int numCuentaContable;
	
	@Column(name="PROVINCIA")
	private String provincia;
	
	@Column(name="POBLACION")
	private String poblacion;

	@Column(name="TELEFONO_FIJO")
	private String telefonoFijo;

	@Column(name="TELEFONO_MOVIL")
	private String telefonoMovil;

	@Column(name="TIPO")
	private int tipo;
	
	@Column(name="CONFIRMADO")
	private boolean confirmado;

	//bi-directional many-to-one association to AlbaranCliente
	@OneToMany(mappedBy="clienteBean")
	private List<AlbaranCliente> albaranClientes;

	//bi-directional many-to-one association to PedidosCliente
	@OneToMany(mappedBy="clienteBean")
	private List<PedidoCliente> pedidosClientes;

	//bi-directional many-to-one association to PreciosCliente
	@OneToMany(mappedBy="clienteBean")
	private List<PreciosCliente> preciosClientes;

	public Cliente() {
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCodPost() {
		return this.codPost;
	}

	public void setCodPost(String codPost) {
		this.codPost = codPost;
	}

	public int getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(int contrasena) {
		this.contrasena = contrasena;
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

	public String getNifCif() {
		return this.nifCif;
	}

	public void setNifCif(String nifCif) {
		this.nifCif = nifCif;
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

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public List<AlbaranCliente> getAlbaranClientes() {
		return this.albaranClientes;
	}

	public void setAlbaranClientes(List<AlbaranCliente> albaranClientes) {
		this.albaranClientes = albaranClientes;
	}

	public AlbaranCliente addAlbaranCliente(AlbaranCliente albaranCliente) {
		getAlbaranClientes().add(albaranCliente);
		albaranCliente.setClienteBean(this);

		return albaranCliente;
	}

	public AlbaranCliente removeAlbaranCliente(AlbaranCliente albaranCliente) {
		getAlbaranClientes().remove(albaranCliente);
		albaranCliente.setClienteBean(null);

		return albaranCliente;
	}

	public List<PedidoCliente> getPedidosClientes() {
		return this.pedidosClientes;
	}

	public void setPedidosClientes(List<PedidoCliente> pedidosClientes) {
		this.pedidosClientes = pedidosClientes;
	}

	public PedidoCliente addPedidosCliente(PedidoCliente pedidosCliente) {
		getPedidosClientes().add(pedidosCliente);
		pedidosCliente.setClienteBean(this);

		return pedidosCliente;
	}

	public PedidoCliente removePedidosCliente(PedidoCliente pedidosCliente) {
		getPedidosClientes().remove(pedidosCliente);
		pedidosCliente.setClienteBean(null);

		return pedidosCliente;
	}

	public List<PreciosCliente> getPreciosClientes() {
		return this.preciosClientes;
	}

	public void setPreciosClientes(List<PreciosCliente> preciosClientes) {
		this.preciosClientes = preciosClientes;
	}

	public PreciosCliente addPreciosCliente(PreciosCliente preciosCliente) {
		getPreciosClientes().add(preciosCliente);
		preciosCliente.setClienteBean(this);

		return preciosCliente;
	}

	public PreciosCliente removePreciosCliente(PreciosCliente preciosCliente) {
		getPreciosClientes().remove(preciosCliente);
		preciosCliente.setClienteBean(null);

		return preciosCliente;
	}

}