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
import entidades.FacturasCliente;
import entidades.FilasFacturasCliente;
import modelo.negocio.GestorFacturaCliente;
import vista.clientes.facturas.VFacturaCliente;
import vista.clientes.facturas.VFilaFacturaCliente;

public class ControladorFacturaCliente implements InternalFrameListener, FocusListener,ActionListener{
	private GestorFacturaCliente gfc;
	private VFacturaCliente vFactura;
	private List<FilasFacturasCliente> filasFact;
	
	public ControladorFacturaCliente(VFacturaCliente vFactura) {
		this.vFactura=vFactura;
		gfc=new GestorFacturaCliente();
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
		FacturasCliente factModif=new FacturasCliente();
		factModif.setNum(Integer.valueOf(vFactura.gettNumAlb().getText()));
		asignaCampos(factModif);
		ponFilas(factModif);
		vFactura.muestraFilas(factModif);
		
			
		int ok=gfc.modificaFactura(factModif);
		if (ok==0) {
			ControladorFacturasClientes cfc = new ControladorFacturasClientes(vFactura.getvFactsPro());
			cfc.listar(vFactura.getvFactsPro());
		
		}
//		//else 
//			//muestraErrores(ok);		
	}
	
	private void nuevaFactura() {
		FacturasCliente facturaNUeva=new FacturasCliente();
		asignaCampos(facturaNUeva);
		int ok=gfc.nuevaFactura(facturaNUeva);
		ponFilas(facturaNUeva);
		gfc.modificaFacturaGenerada(facturaNUeva);
		if (ok==0)
			vFactura.dispose();
	}
	
	private void asignaCampos(FacturasCliente factModif) {
		factModif.setFecha(vFactura.getcFecha().getDate());
		factModif.setCliente((Cliente) vFactura.getComboCliente().getSelectedItem());
		factModif.setPagada(vFactura.getChecPagada().isSelected());
		vFactura.getPanel().updateUI();	
	}

	private void ponFilas(FacturasCliente factModif) {
		FilasFacturasCliente filaModif;
		Component[] componentes=vFactura.getPanel().getComponents();
		filasFact=new ArrayList<FilasFacturasCliente>();
		for (Component fila:componentes) {
			filaModif=new FilasFacturasCliente();
			VFilaFacturaCliente fil=(VFilaFacturaCliente) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,factModif);
			filasFact.add(filaModif);
		}
		factModif.setFilasFacturasClientes(filasFact);
	}
	
	private void asignaCamposFila(VFilaFacturaCliente fila,FilasFacturasCliente filaModif,FacturasCliente factModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setFacturasCliente(factModif);
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
