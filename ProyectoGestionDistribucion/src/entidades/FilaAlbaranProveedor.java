package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FILAS_ALBARAN_PROVEEDOR database table.
 * 
 */
@Entity
@Table(name="FILAS_ALBARAN_PROVEEDOR")
@NamedQuery(name="FilaAlbaranProveedor.findAll", query="SELECT f FROM FilaAlbaranProveedor f")
public class FilaAlbaranProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilasAlbaranProveedorPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="PRECIO")
	private Double precio;

	//bi-directional many-to-one association to AlbaranesProveedor
	@ManyToOne
	@JoinColumn(name="ALBARAN")
	private AlbaranProveedor albaranesProveedor;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO")
	private Articulo articuloBean;

	public FilaAlbaranProveedor() {
	}

	public FilasAlbaranProveedorPK getId() {
		return this.id;
	}

	public void setId(FilasAlbaranProveedorPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public AlbaranProveedor getAlbaranesProveedor() {
		return this.albaranesProveedor;
	}

	public void setAlbaranesProveedor(AlbaranProveedor albaranesProveedor) {
		this.albaranesProveedor = albaranesProveedor;
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
		result = prime * result + ((albaranesProveedor == null) ? 0 : albaranesProveedor.hashCode());
		result = prime * result + ((articuloBean == null) ? 0 : articuloBean.hashCode());
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
		FilaAlbaranProveedor other = (FilaAlbaranProveedor) obj;
		if (albaranesProveedor == null) {
			if (other.albaranesProveedor != null)
				return false;
		} else if (!albaranesProveedor.equals(other.albaranesProveedor))
			return false;
		if (articuloBean == null) {
			if (other.articuloBean != null)
				return false;
		} else if (!articuloBean.equals(other.articuloBean))
			return false;
		return true;
	}

}