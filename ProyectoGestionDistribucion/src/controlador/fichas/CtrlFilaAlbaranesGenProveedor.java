package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VAlbaranesProveedores;
import vista.VFilaAlbaranGeneradoProveedor;
import vista.fichas.VAlbaranProveedor;


public class CtrlFilaAlbaranesGenProveedor implements ActionListener{
	private VFilaAlbaranGeneradoProveedor fila;
	private VAlbaranProveedor vAlba;
	
	public CtrlFilaAlbaranesGenProveedor(VFilaAlbaranGeneradoProveedor fila) {
		this.fila=fila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			System.out.println("editar albaran");
			vAlba=new VAlbaranProveedor(fila.getAlb());
			ControladorAlbaranProveedor cap=new ControladorAlbaranProveedor(vAlba);
			vAlba.establecerControlador(cap);
//			if (fila.getVpedidos().getV()==null) System.out.println("nulo");
			fila.getVAlbaranes().getV().getPanelInterior().add(vAlba);
			vAlba.setVisible(true);
		}
	}
}
