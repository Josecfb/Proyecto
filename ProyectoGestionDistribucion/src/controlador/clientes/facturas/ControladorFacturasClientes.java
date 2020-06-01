package controlador.clientes.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import entidades.FacturaCliente;
import modelo.negocio.GestorFacturaCliente;
import vista.clientes.facturas.VFacturasClientes;
import vista.clientes.facturas.VGeneraFacturaCliente;
/**
 * Controla el listado de facturas de cliente
 * @author Jose Carlos
 *
 */
public class ControladorFacturasClientes implements ActionListener{
	private VFacturasClientes vFacturas;
	/**
	 * El constructor ejecuta el método listar
	 * @param vFacturas Vista de la ventana de listado de facturas de cliente VFacturasClientes
	 */
	public ControladorFacturasClientes(VFacturasClientes vFacturas) {
		listar(vFacturas);
	}
	/**
	 * Obtiene el lisatdo de facturas llamando al método listarFacturas del GestorFacturaCliente
	 * @param vFacturas Vista de la ventana de listado de facturas de cliente VFacturasClientes
	 */
	public void listar(VFacturasClientes vFacturas) {
		this.vFacturas=vFacturas;
		List<FacturaCliente> filas;
		GestorFacturaCliente gfc=new GestorFacturaCliente();
		filas=gfc.listarFacturas();
		vFacturas.muestraPendientes(filas);
	}
	/**
	 * Cuando se pulsa el botón nueva se abre una ventana para crear un factura nueva
	 */
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
