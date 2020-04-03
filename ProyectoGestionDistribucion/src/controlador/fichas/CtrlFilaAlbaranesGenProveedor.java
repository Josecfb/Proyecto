package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VAlbaranesProveedores;
import vista.VFilaAlbaranGeneradoProveedor;


public class CtrlFilaAlbaranesGenProveedor implements ActionListener{
	private VFilaAlbaranGeneradoProveedor fila;
//	private VAlbaranesProveedores vAlba;
	
	public CtrlFilaAlbaranesGenProveedor(VFilaAlbaranGeneradoProveedor fila) {
		this.fila=fila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			System.out.println("editar pedido pendiente");
			// ficha albaran generado vAlba=new VAlbaranesProveedores(fila.getA(),fila.getVpedidos());
//			ControladorPedidoProveedor cpp=new ControladorPedidoProveedor(vAlba);
//			vAlba.establecerControlador(cpp);
//			if (fila.getVpedidos().getV()==null) System.out.println("nulo");
//			fila.getVpedidos().getV().getPanelInterior().add(vAlba);
//			vAlba.setVisible(true);
		}
	}
}
