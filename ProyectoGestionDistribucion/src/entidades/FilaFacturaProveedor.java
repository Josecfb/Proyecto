package entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="FILAS_FACTURAS_PROVEEDOR")
@NamedQuery(name="FilaFacturasroveedor.findAll", query="SELECT f FROM FilaFacturaProveedor f")
public class FilaFacturaProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasFacturasProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private double precio;

	//bi-directional many-to-one association to FacturasProveedor
	@ManyToOne
	@JoinColumn(name="FACTURA")
	private FacturaProveedor facturasProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilaFacturaProveedor() {
	}

	public FilasFacturasProveedorPK getId() {
		return this.id;
	}

	public void setId(FilasFacturasProveedorPK id) {
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

	public FacturaProveedor getFacturasProveedor() {
		return this.facturasProveedor;
	}

	public void setFacturasProveedor(FacturaProveedor facturasProveedor) {
		this.facturasProveedor = facturasProveedor;
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
		result = prime * result + ((facturasProveedor == null) ? 0 : facturasProveedor.hashCode());
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
		FilaFacturaProveedor other = (FilaFacturaProveedor) obj;
		if (articuloBean == null) {
			if (other.articuloBean != null)
				return false;
		} else if (!articuloBean.equals(other.articuloBean))
			return false;
		if (facturasProveedor == null) {
			if (other.facturasProveedor != null)
				return false;
		} else if (!facturasProveedor.equals(other.facturasProveedor))
			return false;
		return true;
	}



}