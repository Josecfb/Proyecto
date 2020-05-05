package controlador.clientes.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.clientes.pedidos.VFilaPedidoPendienteCliente;
import vista.clientes.pedidos.VPedidoCliente;

public class ControladorFilaPedidoPendienteCliente implements ActionListener{
	private VFilaPedidoPendienteCliente fila;
	private VPedidoCliente pc;
	
	public ControladorFilaPedidoPendienteCliente(VFilaPedidoPendienteCliente fila) {
		this.fila=fila;
	}

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
