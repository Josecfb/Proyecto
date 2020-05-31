package controlador.clientes.albaranes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entidades.AlbaranCliente;
import modelo.negocio.GestorAlbaranCliente;
import vista.clientes.albaranes.VAlbaranesClientes;
import vista.clientes.albaranes.VGeneraAlbaranCliente;
/**
 * Controla el listado de albaranes y el bot�n para generar albaranes implementa ActionListener
 * @author Jose Carlos
 *
 */
public class ControladorAlbaranesClientes implements ActionListener{
	private VAlbaranesClientes vAlbaranes;
	/**
	 * El constructor recibe como par�metro la ventana de listado de albaranes y ejecuta el m�todo listar
	 * @param vAlbaranes
	 */
	public ControladorAlbaranesClientes(VAlbaranesClientes vAlbaranes) {
		listar(vAlbaranes);
	}
	/**
	 * Llena la ventana vAlbaranes con la lista de los albaranes llamando al m�todo
	 * listarAlbaranes del GestorAlbaranCliente
	 * @param vAlbaranes
	 */
	public void listar(VAlbaranesClientes vAlbaranes) {
		this.vAlbaranes=vAlbaranes;
		List<AlbaranCliente> filas;
		GestorAlbaranCliente gac=new GestorAlbaranCliente();
		filas=gac.listarAlbaranes();
		vAlbaranes.muestraPendientes(filas);
	}
	/**
	 * Cuando se pulsa sobre el bot�n bNuevoGenerado se abre la ventana del asistente para generar albar�n de cliente
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vAlbaranes.getbNuevoGenerado()) {
			VGeneraAlbaranCliente vGenAlb=new VGeneraAlbaranCliente(vAlbaranes.getV());
			CtrlGenAlbCli cgac=new CtrlGenAlbCli(vGenAlb,vAlbaranes);
			vGenAlb.establecerControlador(cgac);
			vAlbaranes.getV().getPanelInterior().add(vGenAlb);
			
			vGenAlb.setVisible(true);
		}
	}
	
}
