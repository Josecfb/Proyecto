package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRECIOS_CLIENTE database table.
 * 
 */
@Entity
@Table(name="PRECIOS_CLIENTE")
@NamedQuery(name="PreciosCliente.findAll", query="SELECT p FROM PreciosCliente p")
public class PreciosCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PreciosClientePK id;

	@Column(name="PRECIO")
	private BigDecimal precio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente clienteBean;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public PreciosCliente() {
	}

	public PreciosClientePK getId() {
		return this.id;
	}

	public void setId(PreciosClientePK id) {
		this.id = id;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
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