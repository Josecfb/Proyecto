package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import model.Proveedor;

public class DaoProveedor {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Proveedor> Listado(String filtroNombre){
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
