package controlador.clientes.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.AlbaranCliente;
import modelo.negocio.GestorAlbaranCliente;
import vista.clientes.albaranes.VAlbaranesClientes;
import vista.clientes.albaranes.VGeneraAlbaranCliente;

public class ControladorAlbaranesClientes implements ActionListener{
	private VAlbaranesClientes vAlbaranes;
	
	public ControladorAlbaranesClientes(VAlbaranesClientes vAlbaranes) {
		listar(vAlbaranes);
	}
	public void listar(VAlbaranesClientes vAlbaranes) {
		this.vAlbaranes=vAlbaranes;
		List<AlbaranCliente> filas;
		GestorAlbaranCliente gac=new GestorAlbaranCliente();
		filas=gac.listarAlbaranes();
		vAlbaranes.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vAlbaranes.getbNuevoGenerado()) {
			System.out.println("Nuevo albaran cliente");
			VGeneraAlbaranCliente vGenAlb=new VGeneraAlbaranCliente(vAlbaranes.getV());
			CtrlGenAlbCli cgac=new CtrlGenAlbCli(vGenAlb,vAlbaranes);
			vGenAlb.establecerControlador(cgac);
			vAlbaranes.getV().getPanelInterior().add(vGenAlb);
			
			vGenAlb.setVisible(true);
		}
	}
	
}
