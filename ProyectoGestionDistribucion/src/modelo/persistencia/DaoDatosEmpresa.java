package modelo.persistencia;

import javax.persistence.EntityManager;

import model.Datosempresa;

public class DaoDatosEmpresa {
	private EntityManager em;
	public Datosempresa empresa() {
		Datosempresa empresa;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			return empresa=(Datosempresa) em.createQuery("SELECT emp FROM Datosempresa emp").getSingleResult();
		}
	}
}
