package controlador.proveedores.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.AlbaranProveedor;
import modelo.negocio.GestorAlbaranProve;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.albaranes.VGeneraAlbaranProve;

public class ControladorAlbaranesProveedores implements ActionListener{
	private VAlbaranesProveedores vAlbaranes;
	
	public ControladorAlbaranesProveedores(VAlbaranesProveedores vAlbaranes) {
		listar(vAlbaranes);
	}
	public void listar(VAlbaranesProveedores vAlbaranes) {
		this.vAlbaranes=vAlbaranes;
		List<AlbaranProveedor> filas;
		GestorAlbaranProve gap=new GestorAlbaranProve();
		filas=gap.listarAlbaranes();
		vAlbaranes.muestraPendientes(filas);
	}
	
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
