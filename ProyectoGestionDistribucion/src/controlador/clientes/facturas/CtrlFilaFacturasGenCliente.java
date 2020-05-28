package controlador.clientes.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.clientes.facturas.VFacturaCliente;
import vista.clientes.facturas.VFilaFacturasCliente;

public class CtrlFilaFacturasGenCliente implements ActionListener{
	private VFilaFacturasCliente vFila;
	private VFacturaCliente vFact;
	
	public CtrlFilaFacturasGenCliente(VFilaFacturasCliente vFila) {
		this.vFila=vFila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFila.getbEditar()) {
			vFact=new VFacturaCliente(vFila.getFact(),vFila.getVFacturas());
			ControladorFacturaCliente cfc=new ControladorFacturaCliente(vFact);
			vFact.establecerControlador(cfc);
			vFila.getVFacturas().getV().getPanelInterior().add(vFact);
			vFact.setVisible(true);
		}
	}
}
