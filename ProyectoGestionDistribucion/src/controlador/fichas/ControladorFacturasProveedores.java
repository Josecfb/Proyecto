package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controlador.CtrlGenAlbProv;
import model.AlbaranProveedor;
import modelo.negocio.GestorAlbaranProve;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.albaranes.VGeneraAlbaranProve;
import vista.proveedores.facturas.VFacturasProveedores;

public class ControladorFacturasProveedores implements ActionListener{
	private VFacturasProveedores vFacturas;
	
	public ControladorFacturasProveedores(VFacturasProveedores vFacturas) {
		listar(vFacturas);
	}
	public void listar(VFacturasProveedores vFacturas) {
		this.vFacturas=vFacturas;
		List<AlbaranProveedor> filas;
//		GestorAlbaranProve gap=new GestorAlbaranProve();
//		filas=gap.listarAlbaranes();
//		vFacturas.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource()==vFacturas.getbNuevoGenerado()) {
//			VGeneraAlbaranProve vGenAlb=new VGeneraAlbaranProve(vFacturas.getV());
//			CtrlGenAlbProv cpp=new CtrlGenAlbProv(vGenAlb,vFacturas);
//			vGenAlb.establecerControlador(cpp);
//			vFacturas.getV().getPanelInterior().add(vGenAlb);
//			vGenAlb.setVisible(true);
//		}
	}
	
}
