package controlador.proveedores.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.AlbaranProveedor;
import modelo.negocio.GestorAlbaranProve;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.albaranes.VGeneraAlbaranProve;
/**
 * Controla la ventana del listado de albaranes de proveedores
 * @author Jose Carlos
 *
 */
public class ControladorAlbaranesProveedores implements ActionListener{
	private VAlbaranesProveedores vAlbaranes;
	/**
	 * El constructor recibe la ventana del listado de albaranes de proveedores
	 * @param vAlbaranes Vista de la ventana del listado de albaranes de proveedores VAlbaranesProveedores
	 */
	public ControladorAlbaranesProveedores(VAlbaranesProveedores vAlbaranes) {
		listar(vAlbaranes);
	}
	/**
	 * Obtiene la lista de los albaranes de los proveedores llamando al método
	 * listarAlbaranes del GestorAlbaranProve
	 * @param vAlbaranes Vista de la ventana del listado de albaranes de proveedores VAlbaranesProveedores
	 */
	public void listar(VAlbaranesProveedores vAlbaranes) {
		this.vAlbaranes=vAlbaranes;
		List<AlbaranProveedor> filas;
		GestorAlbaranProve gap=new GestorAlbaranProve();
		filas=gap.listarAlbaranes();
		vAlbaranes.muestraAlbaranes(filas);
	}
	/**
	 * Al pulsar sobre el boton nuevo se abre la ventana del asistente para crear albaranes de proveedor
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vAlbaranes.getbNuevoGenerado()) {
			VGeneraAlbaranProve vGenAlb=new VGeneraAlbaranProve(vAlbaranes.getV());
			CtrlGenAlbProv cpp=new CtrlGenAlbProv(vGenAlb,vAlbaranes);
			vGenAlb.establecerControlador(cpp);
			vAlbaranes.getV().getPanelInterior().add(vGenAlb);
			vGenAlb.setVisible(true);
		}
	}
	
}
