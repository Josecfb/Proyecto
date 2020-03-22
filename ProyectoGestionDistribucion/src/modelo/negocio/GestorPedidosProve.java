package modelo.negocio;

import java.util.List;

import model.Articulo;
import model.Proveedor;
import modelo.persistencia.DaoPedidosProveedores;

public class GestorPedidosProve {
	public List<Object[]> listar(){
		DaoPedidosProveedores dpp=new DaoPedidosProveedores();
		System.out.println(dpp.listadoPendientes().size());
		return dpp.listadoPendientes();
	}
	
	public List<Articulo> articulosPendientesPedido(Proveedor pro){
		DaoPedidosProveedores dpp=new DaoPedidosProveedores();
		return dpp.articulosPendientesPedido(pro);
	}
}
