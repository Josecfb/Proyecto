package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import model.AlbaranProveedor;
import model.Articulo;
import model.FilaAlbaranProveedor;
import model.Proveedor;

public class DaoArticulo {
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	public List<Articulo> Listado(String filtroNombre){
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
	
	public Articulo existe(int num) {
		Articulo art;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			art=em.find(Articulo.class, num);
		System.out.println("en existe"+art.getCod());
		return art;
	}
	
	public int modificar(Articulo art) {
		Articulo antiguo=existe(art.getCod());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setCod(art.getCod());
		antiguo.setCodpro(art.getCodpro());
		antiguo.setNombre(art.getNombre());
		antiguo.setCoste(art.getCoste());
		antiguo.setPrecioMayorista(art.getPrecioMayorista());
		antiguo.setPrecioMinorista(art.getPrecioMinorista());
		antiguo.setIva(art.getIva());
		antiguo.setUnidadesCaja(art.getUnidadesCaja());
		antiguo.setStock(art.getStock());
		antiguo.setStockMinimo(art.getStockMinimo());
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
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
}
