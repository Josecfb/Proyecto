package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import model.Articulo;
import model.PedidoCliente;
import model.Cliente;
import model.FilasPedidosCliente;
import model.FilasPedidosClientePK;

public class DaoPedidosClientes {
	private EntityManager em;
	
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
	
	@SuppressWarnings("unchecked")
	public List<PedidoCliente> listaEnviados(Cliente cli){
		List<PedidoCliente> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select ped from PedidoCliente ped where ped.clienteBean=:cli and ped.enviado=TRUE and ped.confirmado=FALSE order by ped.fecha desc").setParameter("cli", cli).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
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
	
	public int modificarPedido(PedidoCliente ped) {
		AbreCierra ab=new AbreCierra();
		
		PedidoCliente antiguo=existe(ped.getNum());
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNum(ped.getNum());
		antiguo.setFecha(ped.getFecha());
		antiguo.setConfirmado(ped.getConfirmado());
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
	 * 
	 * @param ped
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
