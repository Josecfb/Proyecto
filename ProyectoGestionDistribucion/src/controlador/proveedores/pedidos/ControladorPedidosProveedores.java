package controlador.proveedores.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.PedidoProveedor;
import modelo.negocio.GestorPedidosProve;
import vista.proveedores.pedidos.VPedidoProveedor;
import vista.proveedores.pedidos.VPedidosProveedores;
/**
 * Controla la ventana del listado de pedidos de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorPedidosProveedores implements ActionListener{
	private VPedidosProveedores vPedidosProve;
	/**
	 * Constructor, ejecuta el método listar
	 * @param vPedidosProve Vista de la ventana de listado de pedidos VPedidosProveedores
	 */
	public ControladorPedidosProveedores(VPedidosProveedores vPedidosProve) {
		listar(vPedidosProve);
	}
	/**
	 * Obtiene la lista de pedidos de proveedor
	 * @param pedidosProve Vista de la ventana de listado de pedidos VPedidosProveedores
	 */
	public void listar(VPedidosProveedores pedidosProve) {
		this.vPedidosProve=pedidosProve;
		List<PedidoProveedor> filas;
		GestorPedidosProve gpp=new GestorPedidosProve();
		filas=gpp.listar(null);
		pedidosProve.muestraPendientes(filas);
	}
	/**
	 * Abre la ventana del asistente para generar pedidos 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vPedidosProve.getbNuevoPendiente()) {
			VPedidoProveedor vPedidoPro;
			vPedidoPro=new VPedidoProveedor(null, vPedidosProve);
			ControladorPedidoProveedor cpp=new ControladorPedidoProveedor(vPedidoPro);
			vPedidoPro.establecerControlador(cpp);
			vPedidosProve.getV().getPanelInterior().add(vPedidoPro);
			vPedidoPro.setVisible(true);
		}
	}
}
