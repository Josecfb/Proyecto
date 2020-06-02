package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.Proveedor;
/**
 * Gestiona la persistencia de los proveedores
 * @author Jose Carlos
 *
 */
public class DaoProveedor {
	private EntityManager em;
	/**
	 * Obtiene la lista de los proveedores
	 * @param filtroNombre Actua como filtro para el nombre 
	 * @return List de Proveedor
	 */
	@SuppressWarnings("unchecked")
	public List<Proveedor> listar(String filtroNombre){
		List<Proveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			if (filtroNombre=="")
				lista=em.createQuery("SELECT pr FROM Proveedor pr").getResultList();
			else
				lista=em.createQuery("SELECT pr FROM Proveedor pr where pr.nombre LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			//ab.cerrarConexion();
			ab.cerrarConexion();
			return lista;
		}
	}
	/**
	 * Retorna un proveedor si existe en la base de datos
	 * @param num Número de proveedor
	 * @return Objeto Proveedor
	 */
	public Proveedor existe(int num) {
		Proveedor pro;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			pro=em.find(Proveedor.class, num);
		return pro;
	}
	/**
	 * Modifica un proveedor existente
	 * @param pro Objeto Proveedor
	 * @return -1 error 0 correcto
	 */
	public int modificar(Proveedor pro) {
		Proveedor antiguo=existe(pro.getNumero());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNumero(pro.getNumero());
		antiguo.setNombre(pro.getNombre());
		antiguo.setDireccion(pro.getDireccion());
		antiguo.setCodPost(pro.getCodPost());
		antiguo.setNif(pro.getNif());
		antiguo.setEmail(pro.getEmail());
		antiguo.setNumCuentaContable(pro.getNumCuentaContable());
		antiguo.setTelefonoFijo(pro.getTelefonoFijo());
		antiguo.setTelefonoMovil(pro.getTelefonoMovil());
		antiguo.setPoblacion(pro.getPoblacion());
		antiguo.setProvincia(pro.getProvincia());
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Persiste un nuevo proveedor
	 * @param pro Objeto Proveedor
	 * @return -1 error 0 correcto
	 */
	public int nuevo(Proveedor pro) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		em.persist(pro);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Elimina un proveedor de la base de datos
	 * @param pro Objeto Proveedor
	 */
	public void borrarProveedor(Proveedor pro) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		Proveedor borrar=em.find(Proveedor.class, pro.getNumero());
		em.getTransaction().begin();
		em.remove(borrar);
		em.getTransaction().commit();
		em.close();
	}
}
