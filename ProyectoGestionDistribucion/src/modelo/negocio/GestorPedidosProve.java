package modelo.negocio;

import java.util.List;

import model.Articulo;
import model.FilaPedidoProveedor;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.persistencia.DaoPedidosProveedores;

public class GestorPedidosProve {
	private DaoPedidosProveedores dpp;
	
	public PedidoProveedor existe(int num) {
		return dpp.existe(num);
	}
	
	public GestorPedidosProve() {
		dpp=new DaoPedidosProveedores();
	}

	
	public List<FilaPedidoProveedor> listaFilasPedido(PedidoProveedor pedido){
		return dpp.listaFilasPedido(pedido);
	}
	
	public FilaPedidoProveedor existeFila(PedidoProveedor ped, Articulo art) {
		return dpp.existeFila(ped,art);
	}
	
	public int modificarPedido(PedidoProveedor ped) {
		return dpp.modificarPedido(ped);
	}
	
	public int nuevoPedido(PedidoProveedor ped) {
		return dpp.nuevoPedido(ped);
	}
	
	public List<PedidoProveedor> listar(Proveedor pro){
		return dpp.listadoPendientes(pro);
	}
	
	public List<PedidoProveedor> listaEnviados(Proveedor pro){
		return dpp.listaEnviados(pro);
	}
}
