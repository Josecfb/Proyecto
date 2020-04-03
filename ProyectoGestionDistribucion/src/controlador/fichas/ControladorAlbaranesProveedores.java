package controlador.fichas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controlador.CtrlGenAlbProv;
import model.AlbaranProveedor;
import model.PedidoProveedor;
import modelo.negocio.GestorPedidosProve;
import vista.VAlbaranesProveedores;
import vista.VPedidosProveedores;
import vista.fichas.VGeneraAlbaranProve;
import vista.fichas.VPedidoProveedor;

public class ControladorAlbaranesProveedores implements ActionListener{
	private VAlbaranesProveedores vAlbaranes;
	
	public ControladorAlbaranesProveedores(VAlbaranesProveedores vAlbaranes) {
		listar(vAlbaranes);
	}
	public void listar(VAlbaranesProveedores vAlbaranes) {
		this.vAlbaranes=vAlbaranes;
		List<AlbaranProveedor> filas;
		GestorPedidosProve gpp=new GestorPedidosProve();
//		filas=gpp.listar();
//		System.out.println(filas.size());
//		vAlbaranes.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vAlbaranes.getbNuevoGenerado()) {
			VGeneraAlbaranProve vGenAlb=new VGeneraAlbaranProve();
			CtrlGenAlbProv cpp=new CtrlGenAlbProv(vGenAlb);
			vGenAlb.establecerControlador(cpp);
			vAlbaranes.getV().getPanelInterior().add(vGenAlb);
			vGenAlb.setVisible(true);
		}
	}
}
