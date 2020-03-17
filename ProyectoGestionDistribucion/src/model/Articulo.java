package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ARTICULOS database table.
 * 
 */
@Entity
@Table(name="ARTICULOS")
@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD")
	private int cod;

	@Column(name="CODPRO")
	private String codpro;

	@Column(name="COSTE")
	private Double coste;

	@Column(name="IVA")
	private Double iva;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PRECIO_MAYORISTA")
	private Double precioMayorista;

	@Column(name="PRECIO_MINORISTA")
	private Double precioMinorista;

	@Column(name="STOCK")
	private int stock;

	@Column(name="STOCK_MINIMO")
	private int stockMinimo;

	@Column(name="UNIDADES_CAJA")
	private int unidadesCaja;

	//bi-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name="FAMILIA")
	private Familia familiaBean;
	
	@ManyToOne
	@JoinColumn(name="PROVEEDOR")
	private Proveedor proveedorBean;

	//bi-directional many-to-one association to FilasAlbaranCliente
	@OneToMany(mappedBy="articuloBean")
	private List<FilasAlbaranCliente> filasAlbaranClientes;

	//bi-directional many-to-one association to FilasAlbaranProveedor
	@OneToMany(mappedBy="articuloBean")
	private List<FilasAlbaranProveedor> filasAlbaranProveedors;

	//bi-directional many-to-one association to FilasFacturasCliente
	@OneToMany(mappedBy="articuloBean")
	private List<FilasFacturasCliente> filasFacturasClientes;

	//bi-directional many-to-one association to FilasFacturasProveedor
	@OneToMany(mappedBy="articuloBean")
	private List<FilasFacturasProveedor> filasFacturasProveedors;

	//bi-directional many-to-one association to FilasPedidosCliente
	@OneToMany(mappedBy="articuloBean")
	private List<FilasPedidosCliente> filasPedidosClientes;

	//bi-directional many-to-one association to FilasPedidosProveedor
	@OneToMany(mappedBy="articuloBean")
	private List<FilasPedidosProveedor> filasPedidosProveedors;

	//bi-directional many-to-one association to PreciosCliente
	@OneToMany(mappedBy="articuloBean")
	private List<PreciosCliente> preciosClientes;

	public Articulo() {
	}

	public int getCod() {
		return this.cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getCodpro() {
		return this.codpro;
	}

	public void setCodpro(String codpro) {
		this.codpro = codpro;
	}

	public Double getCoste() {
		return this.coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public Double getIva() {
		return this.iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioMayorista() {
		return this.precioMayorista;
	}

	public void setPrecioMayorista(Double precioMayorista) {
		this.precioMayorista = precioMayorista;
	}

	public Double getPrecioMinorista() {
		return this.precioMinorista;
	}

	public void setPrecioMinorista(Double precioMinorista) {
		this.precioMinorista = precioMinorista;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getUnidadesCaja() {
		return this.unidadesCaja;
	}

	public void setUnidadesCaja(int unidadesCaja) {
		this.unidadesCaja = unidadesCaja;
	}

	public Familia getFamiliaBean() {
		return this.familiaBean;
	}

	public void setFamiliaBean(Familia familiaBean) {
		this.familiaBean = familiaBean;
	}

	public Proveedor getProveedorBean() {
		return proveedorBean;
	}

	public void setProveedorBean(Proveedor proveedorBean) {
		this.proveedorBean = proveedorBean;
	}

	public List<FilasAlbaranCliente> getFilasAlbaranClientes() {
		return this.filasAlbaranClientes;
	}

	public void setFilasAlbaranClientes(List<FilasAlbaranCliente> filasAlbaranClientes) {
		this.filasAlbaranClientes = filasAlbaranClientes;
	}

	public FilasAlbaranCliente addFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().add(filasAlbaranCliente);
		filasAlbaranCliente.setArticuloBean(this);

		return filasAlbaranCliente;
	}

	public FilasAlbaranCliente removeFilasAlbaranCliente(FilasAlbaranCliente filasAlbaranCliente) {
		getFilasAlbaranClientes().remove(filasAlbaranCliente);
		filasAlbaranCliente.setArticuloBean(null);

		return filasAlbaranCliente;
	}

	public List<FilasAlbaranProveedor> getFilasAlbaranProveedors() {
		return this.filasAlbaranProveedors;
	}

	public void setFilasAlbaranProveedors(List<FilasAlbaranProveedor> filasAlbaranProveedors) {
		this.filasAlbaranProveedors = filasAlbaranProveedors;
	}

	public FilasAlbaranProveedor addFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().add(filasAlbaranProveedor);
		filasAlbaranProveedor.setArticuloBean(this);

		return filasAlbaranProveedor;
	}

	public FilasAlbaranProveedor removeFilasAlbaranProveedor(FilasAlbaranProveedor filasAlbaranProveedor) {
		getFilasAlbaranProveedors().remove(filasAlbaranProveedor);
		filasAlbaranProveedor.setArticuloBean(null);

		return filasAlbaranProveedor;
	}

	public List<FilasFacturasCliente> getFilasFacturasClientes() {
		return this.filasFacturasClientes;
	}

	public void setFilasFacturasClientes(List<FilasFacturasCliente> filasFacturasClientes) {
		this.filasFacturasClientes = filasFacturasClientes;
	}

	public FilasFacturasCliente addFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().add(filasFacturasCliente);
		filasFacturasCliente.setArticuloBean(this);

		return filasFacturasCliente;
	}

	public FilasFacturasCliente removeFilasFacturasCliente(FilasFacturasCliente filasFacturasCliente) {
		getFilasFacturasClientes().remove(filasFacturasCliente);
		filasFacturasCliente.setArticuloBean(null);

		return filasFacturasCliente;
	}

	public List<FilasFacturasProveedor> getFilasFacturasProveedors() {
		return this.filasFacturasProveedors;
	}

	public void setFilasFacturasProveedors(List<FilasFacturasProveedor> filasFacturasProveedors) {
		this.filasFacturasProveedors = filasFacturasProveedors;
	}

	public FilasFacturasProveedor addFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().add(filasFacturasProveedor);
		filasFacturasProveedor.setArticuloBean(this);

		return filasFacturasProveedor;
	}

	public FilasFacturasProveedor removeFilasFacturasProveedor(FilasFacturasProveedor filasFacturasProveedor) {
		getFilasFacturasProveedors().remove(filasFacturasProveedor);
		filasFacturasProveedor.setArticuloBean(null);

		return filasFacturasProveedor;
	}

	public List<FilasPedidosCliente> getFilasPedidosClientes() {
		return this.filasPedidosClientes;
	}

	public void setFilasPedidosClientes(List<FilasPedidosCliente> filasPedidosClientes) {
		this.filasPedidosClientes = filasPedidosClientes;
	}

	public FilasPedidosCliente addFilasPedidosCliente(FilasPedidosCliente filasPedidosCliente) {
		getFilasPedidosClientes().add(filasPedidosCliente);
		filasPedidosCliente.setArticuloBean(this);

		return filasPedidosCliente;
	}

	public FilasPedidosCliente removeFilasPedidosCliente(FilasPedidosCliente filasPedidosCliente) {
		getFilasPedidosClientes().remove(filasPedidosCliente);
		filasPedidosCliente.setArticuloBean(null);

		return filasPedidosCliente;
	}

	public List<FilasPedidosProveedor> getFilasPedidosProveedors() {
		return this.filasPedidosProveedors;
	}

	public void setFilasPedidosProveedors(List<FilasPedidosProveedor> filasPedidosProveedors) {
		this.filasPedidosProveedors = filasPedidosProveedors;
	}

	public FilasPedidosProveedor addFilasPedidosProveedor(FilasPedidosProveedor filasPedidosProveedor) {
		getFilasPedidosProveedors().add(filasPedidosProveedor);
		filasPedidosProveedor.setArticuloBean(this);

		return filasPedidosProveedor;
	}

	public FilasPedidosProveedor removeFilasPedidosProveedor(FilasPedidosProveedor filasPedidosProveedor) {
		getFilasPedidosProveedors().remove(filasPedidosProveedor);
		filasPedidosProveedor.setArticuloBean(null);

		return filasPedidosProveedor;
	}

	public List<PreciosCliente> getPreciosClientes() {
		return this.preciosClientes;
	}

	public void setPreciosClientes(List<PreciosCliente> preciosClientes) {
		this.preciosClientes = preciosClientes;
	}

	public PreciosCliente addPreciosCliente(PreciosCliente preciosCliente) {
		getPreciosClientes().add(preciosCliente);
		preciosCliente.setArticuloBean(this);

		return preciosCliente;
	}

	public PreciosCliente removePreciosCliente(PreciosCliente preciosCliente) {
		getPreciosClientes().remove(preciosCliente);
		preciosCliente.setArticuloBean(null);

		return preciosCliente;
	}

}