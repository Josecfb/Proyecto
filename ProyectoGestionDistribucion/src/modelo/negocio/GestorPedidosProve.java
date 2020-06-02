package modelo.negocio;

import java.util.List;

import entidades.Articulo;
import entidades.FilaPedidoProveedor;
import entidades.PedidoProveedor;
import entidades.Proveedor;
import modelo.persistencia.DaoPedidosProveedores;
/**
 * Gestor de Pedidos a Proveedor
 * @author Jose Carlos
 *
 */
public class GestorPedidosProve {
	private DaoPedidosProveedores dpp;
	/**
	 * El constructor crea un objeto DaoPedidosProveedores
	 */
	public GestorPedidosProve() {
		dpp=new DaoPedidosProveedores();
	}
	/**
	 * Llama al método existe de DaoPedidosProveedores
	 * @param num número de pedido
	 * @return Objeto PedidoProveedor
	 */
	public PedidoProveedor existe(int num) {
		return dpp.existe(num);
	}
	/**
	 * Llama al método listaFilasPedido de DaoPedidosProveedores
	 * @param pedido Objeto PedidoProveedor
	 * @return List de FilaPedidoProveedor
	 */
	public List<FilaPedidoProveedor> listaFilasPedido(PedidoProveedor pedido){
		return dpp.listaFilasPedido(pedido);
	}
	/**
	 * Llama al método existeFila de DaoPedidosProveedores
	 * @param ped Objeto PedidoProveedor
	 * @param art Objeto Articulo
	 * @return Objeto FilaPedidoProveedor
	 */
	public FilaPedidoProveedor existeFila(PedidoProveedor ped, Articulo art) {
		return dpp.existeFila(ped,art);
	}
	/**
	 * Llama al método modificarPedido de DaoPedidosProveedores
	 * @param ped Objeto PedidoProveedor
	 * @return -1 error 0 correcto
	 */
	public int modificarPedido(PedidoProveedor ped) {
		return dpp.modificarPedido(ped);
	}
	/**
	 * Llama al método nuevoPedido de DaoPedidosProveedores
	 * @param ped Objeto PedidoProveedor
	 * @return 0 sin proveedor 1 sin fecha 2 error base datos 3 correcto
	 */
	public boolean[] nuevoPedido(PedidoProveedor ped) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=ped.getProveedore()!=null;
		ok[1]=ped.getFecha()!=null;
		int res=0;
		if (ok[0] && ok[1]) {
			res=dpp.nuevoPedido(ped);
			ok[2]=res==-1;
			ok[3]=res==0;
		}
		return ok;
	}
	/**
	 * Llama al método listadoPendientes de DaoPedidosProveedores
	 * @param pro Objeto Proveedor
	 * @return List de PedidoProveedor
	 */
	public List<PedidoProveedor> listar(Proveedor pro){
		return dpp.listadoPendientes(pro);
	}
	
	public List<PedidoProveedor> listaEnviados(Proveedor pro){
		return dpp.listaEnviados(pro);
	}
}
