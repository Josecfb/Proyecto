package controlador.proveedores.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.proveedores.albaranes.VAlbaranProveedor;
import vista.proveedores.albaranes.VFilaAlbaranesProveedores;

/**
 * Controla la vista de las filas del la ventana del listado de albaranes de proveedores
 * @author Jose Carlos
 *
 */
public class CtrlFilaAlbaranesGenProveedor implements ActionListener{
	private VFilaAlbaranesProveedores fila;
	private VAlbaranProveedor vAlba;
	/**
	 * El constructor recibe como argumento la vista de fila del listado de  albaranes de proveedor
	 * @param fila  vista de fila del listado de  albaranes de proveedor VFilaAlbaranGeneradoProveedor
	 */
	public CtrlFilaAlbaranesGenProveedor(VFilaAlbaranesProveedores fila) {
		this.fila=fila;
	}
	/**
	 * Abre la ventana del albaran seleccionado
	 */
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
