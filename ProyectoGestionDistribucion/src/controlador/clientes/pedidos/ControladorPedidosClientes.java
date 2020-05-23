package controlador.clientes.pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.PedidoCliente;
import modelo.negocio.GestorPedidosCliente;
import vista.clientes.pedidos.VPedidosClientes;

public class ControladorPedidosClientes implements ActionListener{
	
	public ControladorPedidosClientes(VPedidosClientes vPedidosCliente) {
		listar(vPedidosCliente);
	}
	public void listar(VPedidosClientes pedidosCliente) {
		List<PedidoCliente> filas;
		GestorPedidosCliente gpc=new GestorPedidosCliente();
		filas=gpc.listar(null);
		pedidosCliente.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
}
