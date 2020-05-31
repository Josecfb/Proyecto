package controlador.clientes.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import entidades.FacturaCliente;
import modelo.negocio.GestorFacturaCliente;
import vista.clientes.facturas.VFacturasClientes;
import vista.clientes.facturas.VGeneraFacturaCliente;

public class ControladorFacturasClientes implements ActionListener{
	private VFacturasClientes vFacturas;
	
	public ControladorFacturasClientes(VFacturasClientes vFacturas) {
		listar(vFacturas);
	}
	
	public void listar(VFacturasClientes vFacturas) {
		this.vFacturas=vFacturas;
		List<FacturaCliente> filas;
		GestorFacturaCliente gfc=new GestorFacturaCliente();
		filas=gfc.listarFacturas();
		vFacturas.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFacturas.getbNueva()) {
			VGeneraFacturaCliente vGenFact=new VGeneraFacturaCliente(vFacturas.getV());
			CtrlGenFactCliente cgfc=new CtrlGenFactCliente(vGenFact,vFacturas);
			vGenFact.establecerControlador(cgfc);
			vFacturas.getV().getPanelInterior().add(vGenFact);
			vGenFact.setVisible(true);
		}
	}
	
}
