package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.Familia;
/**
 * Gestina la persistencia de las familias de artículos
 * @author Jose Carlos
 *
 */
public class DaoFamilia {
private EntityManager em;
	
	/**
	 * Obtiene la lista de familias de articulos
	 * @param filtroNombre Cadena de filto para nombre de familia
	 * @return List de Familia
	 */
	@SuppressWarnings("unchecked")
	public List<Familia> listado(String filtroNombre){
		List<Familia> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			if (filtroNombre=="")
				lista=em.createQuery("SELECT fam FROM Familia fam").getResultList();
			else
				lista=em.createQuery("SELECT fam FROM Familia fam where fam.nombre LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			//ab.cerrarConexion();
			ab.cerrarConexion();
			return lista;
		}
	}
}
