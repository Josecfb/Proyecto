package controlador.clientes.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.PedidoCliente;
import modelo.negocio.GestorPedidosCliente;
import vista.clientes.pedidos.VPedidoCliente;
import vista.clientes.pedidos.VPedidosClientes;

public class ControladorPedidosClientes implements ActionListener{
	private VPedidosClientes vPedidosCliente;
	
	public ControladorPedidosClientes(VPedidosClientes vPedidosCliente) {
		listar(vPedidosCliente);
	}
	public void listar(VPedidosClientes pedidosCliente) {
		this.vPedidosCliente=pedidosCliente;
		List<PedidoCliente> filas;
		GestorPedidosCliente gpc=new GestorPedidosCliente();
		filas=gpc.listar(null);
		pedidosCliente.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vPedidosCliente.getbNuevoPendiente()) {
			VPedidoCliente vPedidoCli;
			vPedidoCli=new VPedidoCliente(null, vPedidosCliente);
			ControladorPedidoCliente cpp=new ControladorPedidoCliente(vPedidoCli);
			vPedidoCli.establecerControlador(cpp);
			vPedidosCliente.getV().getPanelInterior().add(vPedidoCli);
			vPedidoCli.setVisible(true);
		}
	}
}
