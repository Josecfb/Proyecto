package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the datosempresa database table.
 * 
 */
@Entity
@NamedQuery(name="Datosempresa.findAll", query="SELECT d FROM Datosempresa d")
public class Datosempresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NIF")
	private String nif;
	
	@Column(name="CODPOS")
	private String codpos;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FIJO")
	private String fijo;
	
	@Column(name="MOVIL")
	private String movil;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="POBLACION")
	private String poblacion;
	
	@Column(name="PROVINCIA")
	private String provincia;
	
	public Datosempresa() {
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getCodpos() {
		return this.codpos;
	}

	public void setCodpos(String codpos) {
		this.codpos = codpos;
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

	public String getFijo() {
		return this.fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getMovil() {
		return this.movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}