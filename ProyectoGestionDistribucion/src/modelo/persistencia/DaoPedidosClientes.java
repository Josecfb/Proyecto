package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasPedidosCliente;
import entidades.FilasPedidosClientePK;
import entidades.PedidoCliente;
/**
 * Gestiona la persistencia de los pedidos de cliente
 * @author Jose Carlos
 *
 */
public class DaoPedidosClientes {
	private EntityManager em;
	/**
	 * Obtiene la lists de los pedidos de un cliente dado
	 * @param cli Objeto Cliente
	 * @return Liste de PedidoCliente
	 */
	@SuppressWarnings("unchecked")
	public List<PedidoCliente> listadoPendientes(Cliente cli){
		List<PedidoCliente> listaPedidos;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			if (cli==null)
				listaPedidos=em.createQuery("select ped from PedidoCliente ped order by ped.fecha desc").getResultList();
			else
				listaPedidos=em.createQuery("select ped from PedidoCliente ped where ped.clienteBean=:cli order by ped.fecha desc").setParameter("cli", cli).getResultList();
		ab.cerrarConexion();
		return listaPedidos;
	}
	/**
	 * Obtiene la lista de los pedidos de un cliente que no han sido enviados
	 * @param cli Objeto Cliente
	 * @return List de PedidoCliente
	 */
	@SuppressWarnings("unchecked")
	public List<PedidoCliente> listaNoEnviados(Cliente cli){
		List<PedidoCliente> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select ped from PedidoCliente ped where ped.clienteBean=:cli and ped.enviado=FALSE order by ped.fecha desc").setParameter("cli", cli).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	/**
	 * Obtiene las filas de un pedido de cliente
	 * @param pedido Objeto PedidoCliente
	 * @return List de FilasPedidosCliente
	 */
	@SuppressWarnings("unchecked")
	public List<FilasPedidosCliente> listaFilasPedido(PedidoCliente pedido){
		List<FilasPedidosCliente> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select fil from FilasPedidosCliente fil where fil.pedidosCliente=:pedido").setParameter("pedido", pedido).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	/**
	 * Obtiene la una fila de PedidoCliente si existe en la base de datos
	 * @param ped Objeto PedidoCliente
	 * @param art Objeto Articulo
	 * @return Objeto FilasPedidosCliente
	 */
	public FilasPedidosCliente existeFila(PedidoCliente ped, Articulo art) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		FilasPedidosClientePK clave= new FilasPedidosClientePK();
		clave.setArticulo(art.getCod());
		clave.setPedido(ped.getNum());
		FilasPedidosCliente fila=new FilasPedidosCliente();
		fila.setId(clave);
		if (em==null)
			return null;
		else 
			return em.find(FilasPedidosCliente.class,fila.getId());
	}
	/**
	 * Retorna un pedido cliente si existe en la base d edatos
	 * @param num número de pedido
	 * @return Objeto PedidoCliente
	 */
	public PedidoCliente existe(int num) {
		PedidoCliente ped;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			ped=em.find(PedidoCliente.class, num);
		ab.cerrarConexion();
		return ped;
	}
	/**
	 * Modifica un pedido de cliente existente
	 * @param ped Objeto PedidoCliente
	 * @return -1 error 0 correcto
	 */
	public int modificarPedido(PedidoCliente ped) {
		AbreCierra ab=new AbreCierra();
		PedidoCliente antiguo=existe(ped.getNum());
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNum(ped.getNum());
		antiguo.setFecha(ped.getFecha());
		antiguo.setEnviado(ped.getEnviado());
		antiguo.setAlbaranCliente(ped.getAlbaranCliente());
		antiguo.getFilasPedidosClientes().clear();
		em.getTransaction().commit();
		em.getTransaction().begin();
		antiguo.setFilasPedidosClientes(ped.getFilasPedidosClientes());
		em.merge(antiguo);
		em.getTransaction().commit();
		
		em.close();
		return 0;
	}
	/**
	 * Persiste un nuevo pedido de cliente
	 * @param ped Objeto PedidoCliente
	 * @return -1 error conexion 0 ok
	 */
	public int nuevoPedido(PedidoCliente ped) {
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
