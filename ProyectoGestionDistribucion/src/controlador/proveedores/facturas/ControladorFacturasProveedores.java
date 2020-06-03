package controlador.proveedores.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.FacturaProveedor;
import modelo.negocio.GestorFacturaProve;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.facturas.VGeneraFacturaProve;
/**
 * Controla la ventana del listado de facturas del proveedor
 * @author Jose Carlos
 *
 */
public class ControladorFacturasProveedores implements ActionListener{
	private VFacturasProveedores vFacturas;
	/**
	 * El constructor ejecuta el método listar
	 * @param vFacturas Vista de la ventana del listado de facturas VFacturasProveedores
	 */
	public ControladorFacturasProveedores(VFacturasProveedores vFacturas) {
		listar(vFacturas);
	}
	/**
	 * Obtiene el listado de facturas de proveedores
	 * @param vFacturas Vista de la ventana del listado de facturas VFacturasProveedores
	 */
	public void listar(VFacturasProveedores vFacturas) {
		this.vFacturas=vFacturas;
		List<FacturaProveedor> filas;
		GestorFacturaProve gfp=new GestorFacturaProve();
		filas=gfp.listarFacturas();
		vFacturas.muestraFacturas(filas);
	}
	/**
	 * Cuando se pulsa el botón nueva factura se abre una ventana del asistente para generar facturas a partir de albaranes
	 */
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
