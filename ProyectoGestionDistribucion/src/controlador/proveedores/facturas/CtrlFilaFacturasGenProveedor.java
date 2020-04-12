package controlador.proveedores.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFilaFacturasProveedor;


public class CtrlFilaFacturasGenProveedor implements ActionListener{
	private VFilaFacturasProveedor vFila;
	private VFacturaProveedor vFact;
	
	public CtrlFilaFacturasGenProveedor(VFilaFacturasProveedor vFila) {
		this.vFila=vFila;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFila.getbEditar()) {
			vFact=new VFacturaProveedor(vFila.getFact(),vFila.getVFacturas());
			ControladorFacturaProveedor cfp=new ControladorFacturaProveedor(vFact);
			vFact.establecerControlador(cfp);
			vFila.getVFacturas().getV().getPanelInterior().add(vFact);
			vFact.setVisible(true);
		}
	}
}
