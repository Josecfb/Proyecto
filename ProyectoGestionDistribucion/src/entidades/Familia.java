package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FAMILIAS database table.
 * 
 */
@Entity
@Table(name="FAMILIAS")
@NamedQuery(name="Familia.findAll", query="SELECT f FROM Familia f")
public class Familia implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articulos == null) ? 0 : articulos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + num;
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
		Familia other = (Familia) obj;
		if (articulos == null) {
			if (other.articulos != null)
				return false;
		} else if (!articulos.equals(other.articulos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Articulo
	@OneToMany(mappedBy="familiaBean")
	private List<Articulo> articulos;

	public Familia() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Articulo addArticulo(Articulo articulo) {
		getArticulos().add(articulo);
		articulo.setFamiliaBean(this);

		return articulo;
	}

	public Articulo removeArticulo(Articulo articulo) {
		getArticulos().remove(articulo);
		articulo.setFamiliaBean(null);

		return articulo;
	}

	@Override
	public String toString() {
		return nombre;
	}

}