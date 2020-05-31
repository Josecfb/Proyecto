package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the FILAS_FACTURAS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="FILAS_FACTURAS_CLIENTE")
@NamedQuery(name="FilaFacturaCliente.findAll", query="SELECT f FROM FilaFacturaCliente f")
public class FilaFacturaCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasFacturasClientePK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private double precio;

	//bi-directional many-to-one association to FacturasCliente
	@ManyToOne
	@JoinColumn(name="FACTURA")
	private FacturaCliente facturasCliente;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilaFacturaCliente() {
	}

	public FilasFacturasClientePK getId() {
		return this.id;
	}

	public void setId(FilasFacturasClientePK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public FacturaCliente getFacturasCliente() {
		return this.facturasCliente;
	}

	public void setFacturasCliente(FacturaCliente facturasCliente) {
		this.facturasCliente = facturasCliente;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

}