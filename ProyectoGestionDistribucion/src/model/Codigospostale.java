package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CODIGOSPOSTALES database table.
 * 
 */
@Entity
@Table(name="CODIGOSPOSTALES")
@NamedQuery(name="Codigospostale.findAll", query="SELECT c FROM Codigospostale c")
public class Codigospostale implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CodigospostalePK id;

	//bi-directional many-to-one association to Poblacione
	@ManyToOne
	@JoinColumn(name="NUM")
	private Poblacione poblacione;

	public Codigospostale() {
	}

	public CodigospostalePK getId() {
		return this.id;
	}

	public void setId(CodigospostalePK id) {
		this.id = id;
	}

	public Poblacione getPoblacione() {
		return this.poblacione;
	}

	public void setPoblacione(Poblacione poblacione) {
		this.poblacione = poblacione;
	}

}