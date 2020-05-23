package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PROVINCIAS database table.
 * 
 */
@Entity
@Table(name="PROVINCIAS")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD")
	private String cod;

	@Column(name="PROVINCIA")
	private String provincia;

	public Provincia() {
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}