package controlador.proveedores.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.PedidoProveedor;
import modelo.negocio.GestorPedidosProve;
import vista.proveedores.pedidos.VPedidoProveedor;
import vista.proveedores.pedidos.VPedidosProveedores;

public class ControladorPedidosProveedores implements ActionListener{
	private VPedidosProveedores vPedidosProve;
	
	public ControladorPedidosProveedores(VPedidosProveedores vPedidosProve) {
		listar(vPedidosProve);
	}
	public void listar(VPedidosProveedores pedidosProve) {
		this.vPedidosProve=pedidosProve;
		List<PedidoProveedor> filas;
		GestorPedidosProve gpp=new GestorPedidosProve();
		filas=gpp.listar(null);
		pedidosProve.muestraPendientes(filas);
	}
	
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