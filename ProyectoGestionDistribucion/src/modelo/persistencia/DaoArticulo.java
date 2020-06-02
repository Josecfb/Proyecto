package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.AlbaranCliente;
import entidades.AlbaranProveedor;
import entidades.Articulo;
import entidades.FilaAlbaranProveedor;
import entidades.FilasAlbaranCliente;
import entidades.Proveedor;
/**
 * Gestiona la persistencia de Articulo
 * @author Jose Carlos
 *
 */
public class DaoArticulo {
	private EntityManager em;
	
	/**
	 * Obtiene el listado de articulos 
	 * @param filtroNombre cadena que actua como filtro en el nombre de artículo
	 * @return List de Articulo
	 */
	@SuppressWarnings("unchecked")
	public List<Articulo> listado(String filtroNombre){
		List<Articulo> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			if (filtroNombre=="")
				lista=em.createQuery("SELECT ar FROM Articulo ar").getResultList();
			else
				lista=em.createQuery("SELECT ar FROM Articulo ar where ar.nombre LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			//ab.cerrarConexion();
			ab.cerrarConexion();
			return lista;
		}
	}
	/**
	 * Obtiene la lista de artículos de un proveedor dado
	 * @param pro Objeto Proveedor
	 * @return List de Articulo
	 */
	@SuppressWarnings("unchecked")
	public List<Articulo> deUnProveedor(Proveedor pro){
		List<Articulo> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("SELECT ar FROM Articulo ar where ar.proveedorBean=:pro order by ar.nombre").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
	/**
	 * Retorna el Objeto Articulo si existe en la base de datos
	 * @param num número de artículo
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num) {
		Articulo art;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			art=em.find(Articulo.class, num);
		return art;
	}
	/**
	 * Retorna el articulo si existe para un proveedor y numero de articulo
	 * @param num numero de articulo
	 * @param pro Objeto Proveedor
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num,Proveedor pro) {
		Articulo art;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else {
			art=em.createQuery("Select art from Articulo art where art.proveedorBean=:pro and art.cod=:num",Articulo.class).setParameter("num", num).setParameter("pro", pro).getSingleResult();
		}
		return art;
	}
	/**
	 * Modifica un artículo existente
	 * @param art Objeto Articulo
	 * @return -1 error 0 correcto
	 */
	public int modificar(Articulo art) {
		Articulo antiguo=existe(art.getCod());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo=art;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Persiste un nuevo Articulo
	 * @param art Objeto Articulo
	 * @return -1 error 0 correcto
	 */
	public int nuevo(Articulo art) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		em.persist(art);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Actualiza el stock de los articulos de un albaran de Proveedor
	 * @param albModif Objeto AlbaranProveedor
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranProve(AlbaranProveedor albModif,int masmenos) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		Articulo artAlb;
		int cantidad;
		for (FilaAlbaranProveedor fila:albModif.getFilasAlbaranProveedors()) {
			artAlb=fila.getArticuloBean();
			cantidad=fila.getCantidad();
			em.createQuery("Update Articulo art set art.stock = art.stock+ :cantidad*:masmenos where art=:artAlb").setParameter("cantidad", cantidad).setParameter("masmenos", masmenos).setParameter("artAlb", artAlb).executeUpdate();
		}
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * Actualiza el stock de los articulos de una albarán de cliente
	 * @param albModif Objeto AlbaranCliente
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranCliente(AlbaranCliente albModif,int masmenos) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		Articulo artAlb;
		int cantidad;
		for (FilasAlbaranCliente fila:albModif.getFilasAlbaranClientes()) {
			artAlb=fila.getArticuloBean();
			cantidad=fila.getCantidad();
			em.createQuery("Update Articulo art set art.stock = art.stock+ :cantidad*:masmenos, art.reservados=art.reservados+ :cantidad*:masmenos where art=:artAlb").setParameter("cantidad", cantidad).setParameter("masmenos", masmenos).setParameter("artAlb", artAlb).executeUpdate();
		}
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * Elimina un artículo de la base de datos
	 * @param art Objeto Articulo
	 */
	public void borrarArticulo(Articulo art) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		Articulo borrar=em.find(Articulo.class, art.getCod());
		em.getTransaction().begin();
		em.remove(borrar);
		em.getTransaction().commit();
		em.close();
	}
}
