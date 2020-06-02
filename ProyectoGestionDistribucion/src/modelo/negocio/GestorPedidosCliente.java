package modelo.negocio;

import java.util.List;

import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasPedidosCliente;
import entidades.PedidoCliente;
import modelo.persistencia.DaoPedidosClientes;
/**
 * Gestor de pedidos de cliente
 * @author Jose Carlos
 *
 */
public class GestorPedidosCliente {
	private DaoPedidosClientes dpc;
	/**
	 * El constructor crea un objeto DaoPedidosClientes
	 */
	public GestorPedidosCliente() {
		dpc=new DaoPedidosClientes();
	}
	/**
	 * Llama al método existe de DaoPedidosClientes
	 * @param num número de pedido de cliente
	 * @return Objeto PedidoCliente
	 */
	public PedidoCliente existe(int num) {
		return dpc.existe(num);
	}
	
	/**
	 * Llama al método listarFilasPedido de DaoPedidosClientes
	 * @param pedido Objeto PedidoCliente
	 * @return List de FilasPedidosCliente
	 */
	public List<FilasPedidosCliente> listaFilasPedido(PedidoCliente pedido){
		return dpc.listaFilasPedido(pedido);
	}
	/**
	 * Llama al método existeFila de DaoPedidosClientes
	 * @param ped Objeto PedidoCliente
	 * @param art Objeto Articulo
	 * @return Objeto FilasPedidosCliente
	 */
	public FilasPedidosCliente existeFila(PedidoCliente ped, Articulo art) {
		return dpc.existeFila(ped,art);
	}
	/**
	 * Llama al método modificarPedido de DaoPedidosClientes
	 * @param ped Objeto PedidoCliente
	 * @return -1 error 0 correcto
	 */
	public int modificarPedido(PedidoCliente ped) {
		return dpc.modificarPedido(ped);
	}
	/**
	 * Llama al método nuevoPedido de DaoPedidosClientes
	 * @param ped Objeto PedidoCliente
	 * @return 0 sin cliente 1 sin fecha 2 error base datos 3 correcto
	 */
	public boolean[] nuevoPedido(PedidoCliente ped) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=ped.getClienteBean()!=null;
		ok[1]=ped.getFecha()!=null;
		int res=0;
		if (ok[0] && ok[1]) {
			res=dpc.nuevoPedido(ped);
			ok[2]=res==-1;
			ok[3]=res==0;
		}
		return ok;
	}
	/**
	 * Llama al método listadoPendientes de DaoPedidosClientes
	 * @param cli Objeto Cliente
	 * @return List de PedidoCliente
	 */
	public List<PedidoCliente> listar(Cliente cli){
		return dpc.listadoPendientes(cli);
	}
	/**
	 * Llama al método listaNoEnviados de DaoPedidosClientes
	 * @param cli Objeto Cliente
	 * @return Lis de PedidoCliente
	 */
	public List<PedidoCliente> listaNoEnviados(Cliente cli){
		return dpc.listaNoEnviados(cli);
	}
}
