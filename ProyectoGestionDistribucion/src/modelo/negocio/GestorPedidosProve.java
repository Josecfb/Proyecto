package modelo.negocio;

import java.util.List;

import model.Articulo;
import model.FilaPedidoProveedor;
import model.PedidoProveedor;
import modelo.persistencia.DaoPedidosProveedores;

public class GestorPedidosProve {
	private DaoPedidosProveedores dpp;
	
	public PedidoProveedor existe(int num) {
		return dpp.existe(num);
	}
	
	public GestorPedidosProve() {
		dpp=new DaoPedidosProveedores();
	}
	public List<PedidoProveedor> listar(){
		System.out.println(dpp.listadoPendientes().size());
		return dpp.listadoPendientes();
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
}
