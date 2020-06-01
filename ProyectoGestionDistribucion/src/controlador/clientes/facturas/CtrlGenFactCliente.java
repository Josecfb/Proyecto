package controlador.clientes.facturas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entidades.AlbaranCliente;
import entidades.Cliente;
import entidades.FacturaCliente;
import modelo.negocio.GestorAlbaranCliente;
import modelo.negocio.GestorFacturaCliente;
import vista.clientes.facturas.VFacturaCliente;
import vista.clientes.facturas.VFacturasClientes;
import vista.clientes.facturas.VFilaAlbGeneraFactCliente;
import vista.clientes.facturas.VGeneraFacturaCliente;
/**
 * Controla la ventana del asistente para generar facturas de cliente a partir de albaranes
 * @author Jose Carlos
 *
 */
public class CtrlGenFactCliente implements ActionListener, FocusListener{
	private VGeneraFacturaCliente vGenFactCliente; 
	private VFacturasClientes vFactsCli;

	/**
	 * El constructor recibe como parámetros la ventana del listado de facturas del cliente y la ventana del asistente
	 * para generar facturas a partir de albaranes de cliente
	 * @param vGenFactCliente Vista de la ventana del asistente para generar facturas vGenFactCliente
	 * @param vFactsCli Vista de la ventana del listado de facturas del cliente VFacturasClientes
	 */
	public CtrlGenFactCliente(VGeneraFacturaCliente vGenFactCliente,VFacturasClientes vFactsCli) {
		this.vGenFactCliente=vGenFactCliente;
		this.vFactsCli=vFactsCli;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Cuando el combobox de cliente pierde el foco llena la lista con los albaranes de ese cliente que estén sin facturar
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenFactCliente.getComboCli().getEditor().getEditorComponent()) {
			GestorAlbaranCliente gac=new GestorAlbaranCliente();
			List<AlbaranCliente> listaAlb;
			listaAlb =gac.listaAlbaranesAlmacen(((Cliente) vGenFactCliente.getComboCli().getSelectedItem()));
			vGenFactCliente.muestraAlbaranes(listaAlb);
		}
	}
	/**
	 * Cuando se pulsa el botón siguiente muestra la lista de albaranes sin facturar y oculta el combobox de cliente
	 * Cuando se pulsa el botón cancelar se cierra la ventana del asistente
	 * Cuando se pulsa el botón aceptar se genera la factura 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenFactCliente.getbSiguiente()) {
			vGenFactCliente.getComboCli().setVisible(false);
			vGenFactCliente.getScrollPane().setVisible(true);
			vGenFactCliente.getlProve().setVisible(false);
			vGenFactCliente.getbSiguiente().setVisible(false);
			vGenFactCliente.getLinea1().setText("Selecione los albaranes de los cuales procede la factura");
			vGenFactCliente.getLinea2().setText("y pulse el botón Aceptar. su factura quedará generada");
			vGenFactCliente.getbAceptar().setVisible(true);
		}
		
		if (e.getSource()==vGenFactCliente.getbCancelar()) {
			vGenFactCliente.dispose();
		}
		
		if (e.getSource()==vGenFactCliente.getbAceptar()) {
			GestorAlbaranCliente gac=new GestorAlbaranCliente();
			GestorFacturaCliente gfc=new GestorFacturaCliente();
			FacturaCliente fac=new FacturaCliente();
			fac.setCliente((Cliente) vGenFactCliente.getComboCli().getSelectedItem());
			fac.setFecha(new Date());
			Component[] componentes;
			componentes = vGenFactCliente.getPanelFila().getComponents();
			List<AlbaranCliente> albaranes=new ArrayList<AlbaranCliente>();
			for (Component compo:componentes) {
				VFilaAlbGeneraFactCliente vfila;
				vfila=(VFilaAlbGeneraFactCliente) compo;
				if (vfila.getChecMarca().isSelected()) {	
					AlbaranCliente alb=gac.existe(Integer.parseInt(vfila.getlNum().getText()));
					if (!alb.getFacturado())
						albaranes.add(alb);
				}
			}
			fac.setAlbaranClientes(albaranes);
			gfc.nuevaFactura(fac);
			for (AlbaranCliente alb:fac.getAlbaranClientes()) {
				alb.setFacturasCliente(fac);
				alb.setFacturado(true);
				gac.facturaAlbaran(alb); 
			}
			fac.setFilasFacturasClientes(gfc.generaFilas(fac));
			gfc.modificaFacturaGenerada(fac);
			VFacturaCliente vFact=new VFacturaCliente(fac,vFactsCli);
			ControladorFacturaCliente cfc=new ControladorFacturaCliente(vFact);
			vFact.establecerControlador(cfc);
			vGenFactCliente.getV().getPanelInterior().add(vFact);
			vFact.setVisible(true);
			vGenFactCliente.dispose();
		}
		
	}

	public VFacturasClientes getvFactsCli() {
		return vFactsCli;
	}

}
