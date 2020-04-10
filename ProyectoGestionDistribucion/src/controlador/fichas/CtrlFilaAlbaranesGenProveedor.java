package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.albaranes.VAlbaranProveedor;
import vista.proveedores.albaranes.VFilaAlbaranGeneradoProveedor;


public class CtrlFilaAlbaranesGenProveedor implements ActionListener{
	private VFilaAlbaranGeneradoProveedor fila;
	private VAlbaranProveedor vAlba;
	
	public CtrlFilaAlbaranesGenProveedor(VFilaAlbaranGeneradoProveedor fila) {
		this.fila=fila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			vAlba=new VAlbaranProveedor(fila.getAlb(),fila.getVAlbaranes());
			ControladorAlbaranProveedor cap=new ControladorAlbaranProveedor(vAlba);
			vAlba.establecerControlador(cap);
			fila.getVAlbaranes().getV().getPanelInterior().add(vAlba);
			vAlba.setVisible(true);
		}
	}
}
