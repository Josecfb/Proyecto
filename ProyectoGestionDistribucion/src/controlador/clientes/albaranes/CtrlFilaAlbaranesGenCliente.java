package controlador.clientes.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.clientes.albaranes.VAlbaranCliente;
import vista.clientes.albaranes.VFilaAlbaranGeneradoCliente;

public class CtrlFilaAlbaranesGenCliente implements ActionListener{
	private VFilaAlbaranGeneradoCliente fila;
	private VAlbaranCliente vAlba;
	
	public CtrlFilaAlbaranesGenCliente(VFilaAlbaranGeneradoCliente fila) {
		this.fila=fila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			vAlba=new VAlbaranCliente(fila.getAlb(),fila.getVAlbaranes());
			ControladorAlbaranCliente cac=new ControladorAlbaranCliente(vAlba);
			vAlba.establecerControlador(cac);
			fila.getVAlbaranes().getV().getPanelInterior().add(vAlba);
			vAlba.setVisible(true);
		}
	}
}
