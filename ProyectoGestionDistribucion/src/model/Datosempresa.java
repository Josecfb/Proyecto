package model;

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
	private String nif;

	private String codpos;

	private String direccion;

	private String email;

	private String fijo;

	private String movil;

	private String nombre;

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

}