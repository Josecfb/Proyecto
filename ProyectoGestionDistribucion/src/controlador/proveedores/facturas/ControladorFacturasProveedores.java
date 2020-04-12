package controlador.proveedores.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.FacturaProveedor;
import modelo.negocio.GestorFacturaProve;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.facturas.VGeneraFacturaProve;

public class ControladorFacturasProveedores implements ActionListener{
	private VFacturasProveedores vFacturas;
	
	public ControladorFacturasProveedores(VFacturasProveedores vFacturas) {
		listar(vFacturas);
	}
	public void listar(VFacturasProveedores vFacturas) {
		this.vFacturas=vFacturas;
		List<FacturaProveedor> filas;
		GestorFacturaProve gfp=new GestorFacturaProve();
		filas=gfp.listarFacturas();
		vFacturas.muestraPendientes(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFacturas.getbNueva()) {
			VGeneraFacturaProve vGenFact=new VGeneraFacturaProve(vFacturas.getV());
			CtrlGenFactProv cgfp=new CtrlGenFactProv(vGenFact,vFacturas);
			vGenFact.establecerControlador(cgfp);
			vFacturas.getV().getPanelInterior().add(vGenFact);
			vGenFact.setVisible(true);
		}
	}
	
}
