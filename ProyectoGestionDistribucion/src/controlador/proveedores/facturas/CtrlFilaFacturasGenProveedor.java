package controlador.proveedores.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFilaFacturasProveedor;

/**
 * Controla las filas del listado de facturas de proveedor
 * @author Jose Carlos
 *
 */
public class CtrlFilaFacturasGenProveedor implements ActionListener{
	private VFilaFacturasProveedor vFila;
	private VFacturaProveedor vFact;
	/**
	 * Constructor
	 * @param vFila Vista de la fila del listado de facturas de proveedor
	 */
	public CtrlFilaFacturasGenProveedor(VFilaFacturasProveedor vFila) {
		this.vFila=vFila;
	}
	/**
	 * Edita la factura de la fila
	 */
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
