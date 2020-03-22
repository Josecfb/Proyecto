package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.FilaPedidoPendienteProveedor;
import vista.fichas.PedidoProveedor;

public class ControladorFilaPedidoPendienteProveedor implements ActionListener{
	private FilaPedidoPendienteProveedor fila;
	private PedidoProveedor pp;
	
	public ControladorFilaPedidoPendienteProveedor(FilaPedidoPendienteProveedor fila) {
		this.fila=fila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			System.out.println("editar pedido pendiente");
			pp=new PedidoProveedor(fila.getPro());
			System.out.println(fila.getPro().getNombre());
			if (fila.getV()==null) System.out.println("nulo");
			fila.getV().getPanelInterior().add(pp);
			pp.setVisible(true);
		}
	}
}
