package controlador.clientes.facturas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import entidades.Articulo;
import entidades.Cliente;
import entidades.FacturaCliente;
import entidades.FilaFacturaCliente;
import modelo.negocio.GestorFacturaCliente;
import util.Utilidades;
import vista.clientes.facturas.VFacturaCliente;
import vista.clientes.facturas.VFilaFacturaCliente;
/**
 * Controla la ventana de facturas de cliente
 * @author Jose Carlos
 *
 */
public class ControladorFacturaCliente implements InternalFrameListener, FocusListener,ActionListener{
	private Utilidades u;
	private GestorFacturaCliente gfc;
	private VFacturaCliente vFactura;
	private List<FilaFacturaCliente> filasFact;
	
	/**
	 * El constructor recibe la ventana factura de cliente
	 * @param vFactura vista de la ventana factura de cliente VFacturaCliente
	 */
	public ControladorFacturaCliente(VFacturaCliente vFactura) {
		u=new Utilidades();
		this.vFactura=vFactura;
		gfc=new GestorFacturaCliente();
	}
	
	/**
	 * Cuando se va ha cerrar la ventana pregunta si se desea guardar, en caso afirmativo, si es de una factura existente la modifica, en caso contrario la crea nueva
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION)
				if (vFactura.getFact()!=null) {
					modificaFactura();
					vFactura.dispose();
				}
				else
					nuevaFactura();
			else
				vFactura.dispose();
	}
	
	/**
	 * Modifica una factura existente
	 */
	private void modificaFactura() {
		FacturaCliente factModif=new FacturaCliente();
		factModif.setNum(Integer.valueOf(vFactura.gettNumAlb().getText()));
		asignaCampos(factModif);
		ponFilas(factModif);
		vFactura.muestraFilas(factModif);
		int ok=gfc.modificaFactura(factModif);
		if (ok==0) {
			ControladorFacturasClientes cfc = new ControladorFacturasClientes(vFactura.getvFactsPro());
			cfc.listar(vFactura.getvFactsPro());
		}
	}
	
	/**
	 * Crea una nueva factura asignandole los datos de la ventana factura y llamando al método nuevaFactura de la clase GestorFacturaCliente
	 */
	private void nuevaFactura() {
		FacturaCliente facturaNUeva=new FacturaCliente();
		asignaCampos(facturaNUeva);
		ponFilas(facturaNUeva);
		int ok=gfc.nuevaFactura(facturaNUeva);
		if (ok==0)
			vFactura.dispose();
	}
	/**
	 * Asigna los campos de la cabecera de la ventana factura al objeto factModif
	 * @param factModif Entidad Factura
	 */
	private void asignaCampos(FacturaCliente factModif) {
		factModif.setFecha(vFactura.getcFecha().getDate());
		factModif.setCliente((Cliente) vFactura.getComboCliente().getSelectedItem());
		factModif.setPagada(vFactura.getChecPagada().isSelected());
		vFactura.getPanel().updateUI();	
	}
	/**
	 * Asigna los datos de las filas de la ventana factura a las filas de factura del objeto factModif
	 * si se repiten filas con el mismo artículo se suman las cantidades
	 * @param factModif Objeto FacturaCliente
	 */
	private void ponFilas(FacturaCliente factModif) {
		FilaFacturaCliente filaModif;
		Component[] componentes=vFactura.getPanel().getComponents();
		filasFact=new ArrayList<FilaFacturaCliente>();
		for (Component fila:componentes) {
			filaModif=new FilaFacturaCliente();
			VFilaFacturaCliente fil=(VFilaFacturaCliente) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,factModif);
			if (filasFact.contains(filaModif))
				filasFact.get(filasFact.indexOf(filaModif)).setCantidad(filasFact.get(filasFact.indexOf(filaModif)).getCantidad()+filaModif.getCantidad());
			else
				filasFact.add(filaModif);
		}
		factModif.setFilasFacturasClientes(filasFact);
	}
	/**
	 * Asigna los datos de una fila de la ventana Factura a una fila de la entidad factModif
	 * @param fila Vista fila factura cliente
	 * @param filaModif Objeto FilaFacturaCliente
	 * @param factModif Objeto FacturaCliente
	 */
	private void asignaCamposFila(VFilaFacturaCliente fila,FilaFacturaCliente filaModif,FacturaCliente factModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setFacturasCliente(factModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
		filaModif.setPrecio(u.euroADoble(fila.getTPrecio().getText()));
	}
	/**
	 * controla la pulsacion del botón nueva fila y del checkbox de factura pagada
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFactura.getbNuevaFila())
			vFactura.nuevaFila();
		if (e.getSource()==vFactura.getChecPagada()) {
			modificaFactura();
			vFactura.getChecPagada().requestFocus();
		}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
