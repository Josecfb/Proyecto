package entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="PRECIOS_CLIENTE")
@NamedQuery(name="PrecioCliente.findAll", query="SELECT p FROM PrecioCliente p")
public class PrecioCliente implements Serializable {
	private static final long serialVersionUID = 1L;



	public PrecioCliente(Cliente clienteBean, Articulo articuloBean) {
		super();
		this.clienteBean = clienteBean;
		this.articuloBean = articuloBean;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articuloBean == null) ? 0 : articuloBean.hashCode());
		result = prime * result + ((clienteBean == null) ? 0 : clienteBean.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrecioCliente other = (PrecioCliente) obj;
		if (articuloBean == null) {
			if (other.articuloBean != null)
				return false;
		} else if (!articuloBean.equals(other.articuloBean))
			return false;
		if (clienteBean == null) {
			if (other.clienteBean != null)
				return false;
		} else if (!clienteBean.equals(other.clienteBean))
			return false;
		return true;
	}

}