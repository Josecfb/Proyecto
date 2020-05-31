package controlador.clientes.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.clientes.facturas.VFacturaCliente;
import vista.clientes.facturas.VFilaFacturasCliente;
/**
 * Controla las filas del listado de facturas de cliente
 * @author Jose Carlos
 *
 */
public class CtrlFilaFacturasGenCliente implements ActionListener{
	private VFilaFacturasCliente vFila;
	private VFacturaCliente vFact;
	/**
	 * El constructor recibe como parámetro la fila del listado de facturas
	 * @param vFila
	 */
	public CtrlFilaFacturasGenCliente(VFilaFacturasCliente vFila) {
		this.vFila=vFila;
	}
	/**
	 * Al pulsar el botón editar abre la ventana de la factura correspondiente para editarla
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFila.getbEditar()) {
			vFact=new VFacturaCliente(vFila.getFact(),vFila.getVFacturas());
			ControladorFacturaCliente cfc=new ControladorFacturaCliente(vFact);
			vFact.establecerControlador(cfc);
			vFila.getVFacturas().getV().getPanelInterior().add(vFact);
			vFact.setVisible(true);
		}
	}
}
