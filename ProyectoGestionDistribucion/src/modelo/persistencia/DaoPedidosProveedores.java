package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.Articulo;
import entidades.FilaPedidoProveedor;
import entidades.FilasPedidosProveedorPK;
import entidades.PedidoProveedor;
import entidades.Proveedor;
/**
 * Gestina la persistencia de los pedidos de proveedor
 * @author Jose Carlos
 *
 */
public class DaoPedidosProveedores {
	private EntityManager em;
	/**
	 * Obtiene la lista de los pedidos de un proveedor dado
	 * @param pro Objeto Proveedor
	 * @return List de PedidoProveedor
	 */
	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> listadoPendientes(Proveedor pro){
		List<PedidoProveedor> listaPedidos;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			if (pro==null)
				listaPedidos=em.createQuery("select ped from PedidoProveedor ped order by ped.fecha desc, ped.num desc").getResultList();
			else
				listaPedidos=em.createQuery("select ped from PedidoProveedor ped where ped.proveedore=:pro order by ped.fecha desc, ped.num desc").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return listaPedidos;
	}
	/**
	 * Obtiene la lista de los pedidos de un proveedor que no se han enviado
	 * @param pro Objeto Proveedor
	 * @return List de PedidoProveedor
	 */
	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> listaEnviados(Proveedor pro){
		List<PedidoProveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select ped from PedidoProveedor ped where ped.proveedore=:pro and ped.enviado=TRUE and ped.confirmado=FALSE order by ped.fecha desc, ped.num desc").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	/**
	 * Obtiene la lista de las filas de un pedido de proveedor
	 * @param pedido  Objeto PedidoProveedor
	 * @return List de FilaPedidoProveedor
	 */
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
	/**
	 * Retorna la fila de un pedido de proveedor si existe en la base de datos
	 * @param ped Objeto PedidoProveedor
	 * @param art Objeto Articulo
	 * @return Objeto FilaPedidoProveedor
	 */
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
	/**
	 * Retorna un pedido de proveedor si existe en la base de datos
	 * @param num Número de pedido de proveedor
	 * @return Objeto PedidoProveedor
	 */
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
	/**
	 * Modifica un pedido de proveedor existente
	 * @param ped Objeto PedidoProveedor
	 * @return -1 error 0 correcto
	 */
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
	 * Persiste un nuevo Pedido de proveedor
	 * @param ped Objeto PedidoProveedor
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
