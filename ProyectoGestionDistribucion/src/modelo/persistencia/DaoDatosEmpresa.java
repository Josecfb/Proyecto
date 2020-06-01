package modelo.persistencia;

import javax.persistence.EntityManager;


import entidades.Datosempresa;

public class DaoDatosEmpresa {
	private EntityManager em;
	
	public Datosempresa empresa() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			return (Datosempresa) em.createQuery("SELECT emp FROM Datosempresa emp").getSingleResult();
		}
	}
	
	public  void modificaEmpresa(Datosempresa empresa) {
		Datosempresa antigua=existe(empresa.getNif());
		antigua=empresa;
		em.getTransaction().begin();
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
	}
	
	public Datosempresa existe(String nif ) {
		Datosempresa empresa;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			empresa=em.find(Datosempresa.class, nif);
		return empresa;
	}
}
