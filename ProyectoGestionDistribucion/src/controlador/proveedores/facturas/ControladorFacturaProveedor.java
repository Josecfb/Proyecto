package controlador.proveedores.facturas;

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
import entidades.FacturaProveedor;
import entidades.FilaFacturaProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorFacturaProve;
import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFilaFacturaProveedor;


public class ControladorFacturaProveedor implements InternalFrameListener, FocusListener,ActionListener{
	private GestorFacturaProve gfp;
	private VFacturaProveedor vFactura;
	private List<FilaFacturaProveedor> filasFact;
	
	public ControladorFacturaProveedor(VFacturaProveedor vFactura) {
		this.vFactura=vFactura;
		gfp=new GestorFacturaProve();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
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
//		//else 
//			//muestraErrores(ok);		
	}
	
	private void nuevaFactura() {
		FacturaProveedor facturaNUeva=new FacturaProveedor();
		asignaCampos(facturaNUeva);
		int ok=gfp.nuevaFactura(facturaNUeva);
		ponFilas(facturaNUeva);
		gfp.modificaFacturaGenerada(facturaNUeva);
		if (ok==0)
			vFactura.dispose();
	}
	
	private void asignaCampos(FacturaProveedor factModif) {
		factModif.setFecha(vFactura.getcFecha().getDate());
		factModif.setProveedore((Proveedor) vFactura.getComboProveedor().getSelectedItem());
		factModif.setPagada(vFactura.getChecPagada().isSelected());
		vFactura.getPanel().updateUI();	
	}

	private void ponFilas(FacturaProveedor factModif) {
		FilaFacturaProveedor filaModif;
		Component[] componentes=vFactura.getPanel().getComponents();
		filasFact=new ArrayList<FilaFacturaProveedor>();
		for (Component fila:componentes) {
			filaModif=new FilaFacturaProveedor();
			VFilaFacturaProveedor fil=(VFilaFacturaProveedor) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,factModif);
			filasFact.add(filaModif);
		}
		factModif.setFilasFacturasProveedors(filasFact);
	}
	
	private void asignaCamposFila(VFilaFacturaProveedor fila,FilaFacturaProveedor filaModif,FacturaProveedor factModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setFacturasProveedor(factModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
		filaModif.setPrecio(euroADoble(fila.gettCoste().getText()));
	}
	
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFactura.getbNuevaFila())
			vFactura.nuevaFila();
		if (e.getSource()==vFactura.getChecPagada()) {
			modificaFactura();
			
			vFactura.getChecPagada().requestFocus();
		}
				
		
	}
	
}
