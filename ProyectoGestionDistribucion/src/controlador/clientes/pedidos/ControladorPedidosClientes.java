package controlador.clientes.pedidos;

import java.util.List;
import entidades.PedidoCliente;
import modelo.negocio.GestorPedidosCliente;
import vista.clientes.pedidos.VPedidosClientes;
/**
 * Controla la ventana del listado de pedidos de clientes
 * @author Jose Carlos
 *
 */
public class ControladorPedidosClientes {
	/**
	 * El constructor ejecuta el método listar
	 * @param vPedidosCliente Vista de la ventana de listado de pedidos de cliente VPedidosClientes
	 */
	public ControladorPedidosClientes(VPedidosClientes vPedidosCliente) {
		listar(vPedidosCliente);
	}
	/**
	 * Genera el listado de pedidos de clientes
	 * @param pedidosCliente Vista de la ventana de listado de pedidos de cliente VPedidosClientes
	 */
	public void listar(VPedidosClientes pedidosCliente) {
		List<PedidoCliente> filas;
		GestorPedidosCliente gpc=new GestorPedidosCliente();
		filas=gpc.listar(null);
		pedidosCliente.muestraPendientes(filas);
	}
	
}
