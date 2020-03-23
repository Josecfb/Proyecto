package modelo.negocio;

import java.util.List;

import model.Articulo;
import model.FilasPedidosProveedor;
import model.PedidosProveedor;
import model.Proveedor;
import modelo.persistencia.DaoPedidosProveedores;

public class GestorPedidosProve {
	public List<PedidosProveedor> listar(){
		DaoPedidosProveedores dpp=new DaoPedidosProveedores();
		System.out.println(dpp.listadoPendientes().size());
		return dpp.listadoPendientes();
	}
	
	public List<FilasPedidosProveedor> articulosPendientesPedido(PedidosProveedor pedido){
		DaoPedidosProveedores dpp=new DaoPedidosProveedores();
		return dpp.articulosPendientesPedido(pedido);
	}
}
