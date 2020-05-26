package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entidades.AlbaranProveedor;
import entidades.Articulo;
import entidades.FilaAlbaranProveedor;
import entidades.Proveedor;



public class DaoAlbaranProve {
	private EntityManager em;

	public int nuevoAlbaran(AlbaranProveedor alb) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(alb);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
	public int modificaAlbaranGenerado(AlbaranProveedor alb) {
		abrir();
		if (em==null) return -1;
		AlbaranProveedor antiguo=em.find(AlbaranProveedor.class, alb.getNum());
		antiguo.getFilasAlbaranProveedors().clear();
		for (FilaAlbaranProveedor fila:alb.getFilasAlbaranProveedors())
			antiguo.getFilasAlbaranProveedors().add(fila);
		em.getTransaction().begin();
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
	public int modificaAlbaran(AlbaranProveedor alb) {
		abrir();
		if (em==null) return -1;
		AlbaranProveedor antiguo=em.find(AlbaranProveedor.class, alb.getNum());
		em.getTransaction().begin();
		for (FilaAlbaranProveedor fi:antiguo.getFilasAlbaranProveedors()) 
			em.remove(fi);
		em.getTransaction().commit();
		em.getTransaction().begin();
		antiguo=alb;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		System.gc();
		return 0;
	}
	
	public int facturaAlbaran(AlbaranProveedor alb) {
		abrir();
		if (em==null) return -1;
		AlbaranProveedor antiguo=em.find(AlbaranProveedor.class, alb.getNum());
		em.getTransaction().begin();
		antiguo=alb;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		System.gc();
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlbaranProveedor> listarAlbaranes(){
		abrir();
		if (em==null) return null;
		List<AlbaranProveedor> lista=em.createQuery("select alb from AlbaranProveedor alb order by alb.fecha desc").getResultList();
		em.close();
		return lista;
	}

	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	
	@SuppressWarnings("unchecked")
	public List<FilaAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		List<FilaAlbaranProveedor> filasAlb=new ArrayList<FilaAlbaranProveedor>();
		abrir();
		if (em==null) return null;
		List<Object[]> filas;
		filas=em.createQuery("select fil.articuloBean,sum(fil.cantidad),fil.articuloBean.coste from FilaPedidoProveedor fil where fil.pedidosProveedor.albaranesProveedor=:alb group by fil.articuloBean").setParameter("alb", alb).getResultList();
		for (Object[] fila:filas) {
			Articulo art=(Articulo) fila[0];
			FilaAlbaranProveedor filaAlb=new FilaAlbaranProveedor();
			filaAlb.setAlbaranesProveedor(alb);
			filaAlb.setArticuloBean(art);
			int cantidad=((Long) fila[1]).intValue();
			filaAlb.setCantidad(cantidad);
			filaAlb.setPrecio((Double) fila[2]);
			filasAlb.add(filaAlb);
		}
		return filasAlb;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlbaranProveedor> listaAlbaranesAlmacen(Proveedor pro){
		abrir();
		if (em==null) return null;
		List<AlbaranProveedor> lista=em.createQuery("select alb from AlbaranProveedor alb where alb.proveedore=:pro and alb.actualizadoAlmacen=true and alb.facturado=false").setParameter("pro", pro).getResultList();
		em.close();
		return lista;
	}
	
	public AlbaranProveedor existe(int num) {
		abrir();
		if (em==null) return null;
		AlbaranProveedor alb=em.find(AlbaranProveedor.class, num);
		em.close();
		return alb;
	}
	
}
