package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import model.Articulo;
import model.PedidoProveedor;
import model.FilaPedidoProveedor;
import model.FilasPedidosProveedorPK;

public class DaoPedidosProveedores {
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> listadoPendientes(){
		List<PedidoProveedor> listaPedidos;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			listaPedidos=em.createQuery("select ped from PedidoProveedor ped").getResultList();
		ab.cerrarConexion();
		return listaPedidos;
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
		return ped;
	}
	
	public int modificarPedido(PedidoProveedor ped) {
		PedidoProveedor antiguo=existe(ped.getNum());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNum(ped.getNum());
		antiguo.setFecha(ped.getFecha());
		antiguo.setConfirmado(ped.getConfirmado());
		antiguo.setEnviado(ped.getEnviado());
		antiguo.setProveedore(ped.getProveedore());
		System.out.println("ah� van");
		for (FilaPedidoProveedor fil:antiguo.getFilaPedidoProveedor()) {
			em.find(FilaPedidoProveedor.class, fil.getId());
			em.remove(fil);
		}
		em.getTransaction().commit();
		em.getTransaction().begin();
		for (FilaPedidoProveedor fil:ped.getFilaPedidoProveedor())
			em.persist(fil);
		System.out.println(antiguo.getFilaPedidoProveedor().size()+"filas");
		for (FilaPedidoProveedor fil:antiguo.getFilaPedidoProveedor())
			System.out.println("antiguoooo"+fil.getCantidad());
		antiguo.setFilaPedidoProveedor(ped.getFilaPedidoProveedor());
		em.merge(antiguo);
		em.getTransaction().commit();
		
		em.close();
		return 0;
	}
	
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
