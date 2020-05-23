package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.Articulo;
import entidades.FilaPedidoProveedor;
import entidades.FilasPedidosProveedorPK;
import entidades.PedidoProveedor;
import entidades.Proveedor;

public class DaoPedidosProveedores {
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> listadoPendientes(Proveedor pro){
		List<PedidoProveedor> listaPedidos;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			if (pro==null)
				listaPedidos=em.createQuery("select ped from PedidoProveedor ped order by ped.fecha desc").getResultList();
			else
				listaPedidos=em.createQuery("select ped from PedidoProveedor ped where ped.proveedore=:pro order by ped.fecha desc").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return listaPedidos;
	}
	
	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> listaEnviados(Proveedor pro){
		List<PedidoProveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select ped from PedidoProveedor ped where ped.proveedore=:pro and ped.enviado=TRUE and ped.confirmado=FALSE order by ped.fecha desc").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<FilaPedidoProveedor> listaFilasPedido(PedidoProveedor pedido){
		List<FilaPedidoProveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select fil from FilaPedidoProveedor fil where fil.pedidosProveedor=:pedido").setParameter("pedido", pedido).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
	public FilaPedidoProveedor existeFila(PedidoProveedor ped, Articulo art) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		FilasPedidosProveedorPK clave= new FilasPedidosProveedorPK();
		clave.setArticulo(art.getCod());
		clave.setPedido(ped.getNum());
		FilaPedidoProveedor fila=new FilaPedidoProveedor();
		fila.setId(clave);
		if (em==null)
			return null;
		else 
			return em.find(FilaPedidoProveedor.class,fila.getId());
	}
	
	public PedidoProveedor existe(int num) {
		PedidoProveedor ped;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			ped=em.find(PedidoProveedor.class, num);
		ab.cerrarConexion();
		return ped;
	}
	
	public int modificarPedido(PedidoProveedor ped) {
		AbreCierra ab=new AbreCierra();
		
		PedidoProveedor antiguo=existe(ped.getNum());
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNum(ped.getNum());
		antiguo.setFecha(ped.getFecha());
		antiguo.setConfirmado(ped.getConfirmado());
		antiguo.setEnviado(ped.getEnviado());
		antiguo.setAlbaranesProveedor(ped.getAlbaranesProveedor());
		for (FilaPedidoProveedor fi:antiguo.getFilaPedidoProveedor()) {
			FilaPedidoProveedor fil=em.find(FilaPedidoProveedor.class, fi.getId());
			em.remove(fil);
		}
		em.getTransaction().commit();
		em.getTransaction().begin();
		for (FilaPedidoProveedor fil:ped.getFilaPedidoProveedor())
			em.persist(fil);
		antiguo.setFilaPedidoProveedor(ped.getFilaPedidoProveedor());
		em.merge(antiguo);
		em.getTransaction().commit();
		
		em.close();
		return 0;
	}
	/**
	 * 
	 * @param ped
	 * @return -1 error conexion 0 ok
	 */
	public int nuevoPedido(PedidoProveedor ped) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		em.persist(ped);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
}
