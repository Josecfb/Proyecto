package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CODIGOSPOSTALES database table.
 * 
 */
@Embeddable
public class CodigospostalePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;

	@Column(name="NUM", insertable=false, updatable=false)
	private int num;

	public CodigospostalePK() {
	}
	public String getCodigoPostal() {
		return this.codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getNum() {
		return this.num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CodigospostalePK)) {
			return false;
		}
		CodigospostalePK castOther = (CodigospostalePK)other;
		return 
			this.codigoPostal.equals(castOther.codigoPostal)
			&& (this.num == castOther.num);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoPostal.hashCode();
		hash = hash * prime + this.num;
		
		return hash;
	}
}