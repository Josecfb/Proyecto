package entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="PRECIOS_CLIENTE")
@NamedQuery(name="PrecioCliente.findAll", query="SELECT p FROM PrecioCliente p")
public class PrecioCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PreciosClientePK id;

	@Column(name="PRECIO")
	private double precio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente clienteBean;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public PrecioCliente() {
	}

	public PreciosClientePK getId() {
		return this.id;
	}

	public void setId(PreciosClientePK id) {
		this.id = id;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Articulo getArticuloBean() {
		return this.articuloBean;
	}

	public void setArticuloBean(Articulo articuloBean) {
		this.articuloBean = articuloBean;
	}

}