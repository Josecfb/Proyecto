package controlador.clientes.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.clientes.pedidos.VFilaPedidoPendienteCliente;
import vista.clientes.pedidos.VPedidoCliente;
/**
 * Controla la vista de las filas del listado de pedidos de clientes
 * @author Jose Carlos
 *
 */
public class ControladorFilaPedidoPendienteCliente implements ActionListener{
	private VFilaPedidoPendienteCliente fila;
	private VPedidoCliente pc;
	/**
	 * El constructor recibe como argumento la vista de la fila del listado de pedidos de clientes
	 * @param fila vista de la fila del listado de pedidos de clientes VFilaPedidoPendienteCliente
	 */
	public ControladorFilaPedidoPendienteCliente(VFilaPedidoPendienteCliente fila) {
		this.fila=fila;
	}
	/**
	 * Cuando se pulsa sobre el botón de editar pedido se abre una ventana para la edición de ese pedido
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			pc=new VPedidoCliente(fila.getPed(),fila.getVpedidos());
			ControladorPedidoCliente cpp=new ControladorPedidoCliente(pc);
			pc.establecerControlador(cpp);
			fila.getVpedidos().getV().getPanelInterior().add(pc);
			pc.setVisible(true);
		}
	}
}
