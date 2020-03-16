package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the POBLACIONES database table.
 * 
 */
@Entity
@Table(name="POBLACIONES")
@NamedQuery(name="Poblacione.findAll", query="SELECT p FROM Poblacione p")
public class Poblacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM")
	private int num;

	@Column(name="POBLACION")
	private String poblacion;

	//bi-directional many-to-one association to Codigospostale
	@OneToMany(mappedBy="poblacione")
	private List<Codigospostale> codigospostales;

	public Poblacione() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public List<Codigospostale> getCodigospostales() {
		return this.codigospostales;
	}

	public void setCodigospostales(List<Codigospostale> codigospostales) {
		this.codigospostales = codigospostales;
	}

	public Codigospostale addCodigospostale(Codigospostale codigospostale) {
		getCodigospostales().add(codigospostale);
		codigospostale.setPoblacione(this);

		return codigospostale;
	}

	public Codigospostale removeCodigospostale(Codigospostale codigospostale) {
		getCodigospostales().remove(codigospostale);
		codigospostale.setPoblacione(null);

		return codigospostale;
	}

}