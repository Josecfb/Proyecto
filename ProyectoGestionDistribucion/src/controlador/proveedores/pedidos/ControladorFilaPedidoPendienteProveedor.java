package controlador.proveedores.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.pedidos.VFilaPedidosProveedor;
import vista.proveedores.pedidos.VPedidoProveedor;
/**
 * Controla las filas del listado de pedidos de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorFilaPedidoPendienteProveedor implements ActionListener{
	private VFilaPedidosProveedor fila;
	private VPedidoProveedor pp;
	/**
	 * Constructor
	 * @param fila Vista de la fila del listado de pedidos de proveedor VFilaPedidoPendienteProveedor
	 */
	public ControladorFilaPedidoPendienteProveedor(VFilaPedidosProveedor fila) {
		this.fila=fila;
	}
/**
 * Al pulsar el botón abre la ventana del pedido de proveedor para editarlo
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			pp=new VPedidoProveedor(fila.getPed(),fila.getVpedidos());
			ControladorPedidoProveedor cpp=new ControladorPedidoProveedor(pp);
			pp.establecerControlador(cpp);
			fila.getVpedidos().getV().getPanelInterior().add(pp);
			pp.setVisible(true);
		}
	}
}
