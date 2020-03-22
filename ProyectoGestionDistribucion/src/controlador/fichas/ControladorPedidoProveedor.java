package controlador.fichas;

import java.util.List;

import model.Articulo;
import modelo.negocio.GestorPedidosProve;
import vista.fichas.PedidoProveedor;

public class ControladorPedidoProveedor {
	//private PedidoProveedor pedidoProveedor;
	
	public ControladorPedidoProveedor(PedidoProveedor pedidoProveedor) {
		
	}
	
	public List<Articulo> articulosPendientesPedido(PedidoProveedor pedido){
		GestorPedidosProve gpp=new GestorPedidosProve();
		return gpp.articulosPendientesPedido(pedido.getPro());
	}
}
