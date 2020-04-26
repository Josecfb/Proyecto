package controlador.proveedores.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.pedidos.VFilaPedidoPendienteProveedor;
import vista.proveedores.pedidos.VPedidoProveedor;

public class ControladorFilaPedidoPendienteProveedor implements ActionListener{
	private VFilaPedidoPendienteProveedor fila;
	private VPedidoProveedor pp;
	
	public ControladorFilaPedidoPendienteProveedor(VFilaPedidoPendienteProveedor fila) {
		this.fila=fila;
	}

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
