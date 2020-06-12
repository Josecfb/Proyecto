package controlador.proveedores.facturas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import entidades.Articulo;
import entidades.FacturaProveedor;
import entidades.FilaFacturaProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorFacturaProve;
import util.Utilidades;
import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFilaFacturaProveedor;

/**
 * Controla la ventana de factura de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorFacturaProveedor implements InternalFrameListener, FocusListener,ActionListener,ItemListener,PropertyChangeListener{
	private GestorFacturaProve gfp;
	private VFacturaProveedor vFactura;
	private List<FilaFacturaProveedor> filasFact;
	private Utilidades u;
	/**
	 * El constructor recibe la ventana factura de proveedor
	 * @param vFactura vista de la ventana factura de proveedor VFacturaProveedor
	 */
	public ControladorFacturaProveedor(VFacturaProveedor vFactura) {
		this.vFactura=vFactura;
		gfp=new GestorFacturaProve();
		u=new Utilidades();
	}
	/**
	 * Cuando se va ha cerrar la ventana pregunta si se desea guardar, en caso afirmativo, si es de una factura existente la modifica, en caso contrario la crea nueva
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		
		if(vFactura.isModificado()) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION)
				if (!vFactura.gettNumAlb().getText().equals("")) {
					modificaFactura();
					vFactura.dispose();
				}
				else
					nuevaFactura();
			else
				vFactura.dispose();
		}
		else
			vFactura.dispose();
	}
	/**
	 * Modifica una factura existente
	 */
	private void modificaFactura() {
		FacturaProveedor factModif=new FacturaProveedor();
		factModif.setNum(Integer.valueOf(vFactura.gettNumAlb().getText()));
		asignaCampos(factModif);
		ponFilas(factModif);
		vFactura.muestraFilas(factModif);
		
			
		int ok=gfp.modificaFactura(factModif);
		if (ok==0) {
			ControladorFacturasProveedores cap = new ControladorFacturasProveedores(vFactura.getvFactsPro());
			cap.listar(vFactura.getvFactsPro());
		}

	}
	/**
	 * Crea una nueva factura asignandole los datos de la ventana factura y llamando al método nuevaFactura de la clase GestorFacturaProve
	 */
	private void nuevaFactura() {
		FacturaProveedor facturaNUeva=new FacturaProveedor();
		asignaCampos(facturaNUeva);
		int ok=gfp.nuevaFactura(facturaNUeva);
		ponFilas(facturaNUeva);
		gfp.modificaFacturaGenerada(facturaNUeva);
		if (ok==0)
			vFactura.dispose();
	}
	/**
	 * Asigna los campos de la cabecera de la ventana factura al objeto factModif
	 * @param factModif Entidad objeto FacturaProveedor
	 */
	private void asignaCampos(FacturaProveedor factModif) {
		factModif.setFecha(vFactura.getcFecha().getDate());
		factModif.setProveedore((Proveedor) vFactura.getComboProveedor().getSelectedItem());
		factModif.setPagada(vFactura.getChecPagada().isSelected());
		vFactura.getPanel().updateUI();	
	}
	/**
	 * Asigna los datos de las filas de la ventana factura a las filas de factura del objeto factModif
	 * si se repiten filas con el mismo artículo se suman las cantidades
	 * @param factModif Objeto FacturaProveedor
	 */
	private void ponFilas(FacturaProveedor factModif) {
		FilaFacturaProveedor filaModif;
		Component[] componentes=vFactura.getPanel().getComponents();
		filasFact=new ArrayList<FilaFacturaProveedor>();
		for (Component fila:componentes) {
			filaModif=new FilaFacturaProveedor();
			VFilaFacturaProveedor fil=(VFilaFacturaProveedor) fila;
			fil.updateUI();
			if (!fil.gettCod().getText().equals("0")) {
				asignaCamposFila(fil,filaModif,factModif);
				if (filasFact.contains(filaModif))
					filasFact.get(filasFact.indexOf(filaModif)).setCantidad(filasFact.get(filasFact.indexOf(filaModif)).getCantidad()+filaModif.getCantidad());
				else
					filasFact.add(filaModif);
			}
		}
		factModif.setFilasFacturasProveedors(filasFact);
	}
	/**
	 * Asigna los datos de una fila de la ventana Factura a una fila de la entidad factModif
	 * @param fila Vista fila factura proveedor
	 * @param filaModif Objeto FilaFacturaProveedor
	 * @param factModif Objeto FacturaProveedor
	 */
	private void asignaCamposFila(VFilaFacturaProveedor fila,FilaFacturaProveedor filaModif,FacturaProveedor factModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setFacturasProveedor(factModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
		filaModif.setPrecio(u.euroADoble(fila.gettCoste().getText()));
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
	/**
	 * Botón de nueva fila y check de factura pagada
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFactura.getChecPagada()) {
			modificaFactura();
			vFactura.getChecPagada().requestFocus();
			vFactura.setModificado(true);
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (e.getSource()==vFactura.getcFecha()) {
			 if (!e.getPropertyName().equals("ancestor"))
				 vFactura.setModificado(true);

		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		vFactura.setModificado(true);
	}
}
