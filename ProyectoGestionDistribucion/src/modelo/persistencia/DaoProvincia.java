package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.Provincia;
/**
 * Gestiona persistencia de provincias y poblaciones
 * @author Jose Carlos
 *
 */
public class DaoProvincia {
	private EntityManager em;
	/**
	 * Abre la conexion
	 */
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	/**
	 * Obtiene el nombre de la provincia a partir del código postal
	 * @param codPost cadene del código postal
	 * @return Cadena con el nombre de la provincia
	 */
	public String nomProvincia(String codPost) {
		String codigo=codPost.substring(0, 2);
		abrir();
		String provincia=em.find(Provincia.class, codigo).getProvincia();
		em.close();
		return provincia;
	}
	/**
	 * Obtiene la lista de poblacionde de un código postal dado
	 * @param codPost cadena del código postal
	 * @return List de cadenas con el nombre de poblacion
	 */
	@SuppressWarnings("unchecked")
	public List<String> nomPoblaciones(String codPost) {
		abrir();
		System.out.println(codPost);
		List<String> poblaciones=em.createQuery("select cod.poblacione.poblacion from Codigospostale cod where cod.id.codigoPostal=:codPost").setParameter("codPost", codPost).getResultList();
		for (String poblacion:poblaciones) {
			System.out.println(poblacion);
		}
		em.close();
		return poblaciones;
	}
}
