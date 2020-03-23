package controlador.fichas;

import java.util.List;

import model.FilasPedidosProveedor;
import model.PedidosProveedor;
import modelo.negocio.GestorPedidosProve;
import vista.fichas.PedidoProveedor;

public class ControladorPedidoProveedor {
	//private PedidoProveedor pedidoProveedor;
	
	public ControladorPedidoProveedor(PedidoProveedor pedidoProveedor) {
		
	}
	
	public List<FilasPedidosProveedor> articulosPendientesPedido(PedidosProveedor pedido){
		GestorPedidosProve gpp=new GestorPedidosProve();
		return gpp.articulosPendientesPedido(pedido);
	}
}
