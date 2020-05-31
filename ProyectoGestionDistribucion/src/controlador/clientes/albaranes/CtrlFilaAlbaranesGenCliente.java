package controlador.clientes.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.clientes.albaranes.VAlbaranCliente;
import vista.clientes.albaranes.VFilaAlbaranGeneradoCliente;
/**
 * Controla la pulsacion del botón editar albaran que hay en cada fila del listado de albaranes
 * @author Jose Carlos
 *
 */
public class CtrlFilaAlbaranesGenCliente implements ActionListener{
	private VFilaAlbaranGeneradoCliente fila;
	private VAlbaranCliente vAlba;
	/**
	 *El constructor recibe la vista de fila del listado de albaranes
	 * @param fila
	 */
	public CtrlFilaAlbaranesGenCliente(VFilaAlbaranGeneradoCliente fila) {
		this.fila=fila;
	}
	/**
	 * Cuando se pulsa el botón de editar albarán se abre una ventana donde es posible modificar el albarán
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fila.getbEditar()) {
			vAlba=new VAlbaranCliente(fila.getAlb(),fila.getVAlbaranes());
			ControladorAlbaranCliente cac=new ControladorAlbaranCliente(vAlba);
			vAlba.establecerControlador(cac);
			fila.getVAlbaranes().getV().getPanelInterior().add(vAlba);
			vAlba.setVisible(true);
		}
	}
}
