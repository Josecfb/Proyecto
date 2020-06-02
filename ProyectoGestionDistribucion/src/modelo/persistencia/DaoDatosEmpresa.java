package modelo.persistencia;

import javax.persistence.EntityManager;


import entidades.Datosempresa;
/**
 * Gestiona la persistencia de los datos de empresa
 * @author Jose Carlos
 *
 */
public class DaoDatosEmpresa {
	private EntityManager em;
	/**
	 * Retorna el objeto DatosEmpresa
	 * @return objeto DatosEmpresa
	 */
	public Datosempresa empresa() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			return (Datosempresa) em.createQuery("SELECT emp FROM Datosempresa emp").getSingleResult();
		}
	}
	/**
	 * Modifica los datos de la empresa
	 * @param empresa Objeto Empresa
	 */
	public  void modificaEmpresa(Datosempresa empresa) {
		Datosempresa antigua=existe(empresa.getNif());
		antigua=empresa;
		em.getTransaction().begin();
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * Retorna los datos de empresa si existe
	 * @param nif NIF de la empresa
	 * @return Objeto DatosEmpresa
	 */
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
