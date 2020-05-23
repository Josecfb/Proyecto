package controlador.clientes.albaranes;

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

import model.AlbaranCliente;
import model.Articulo;
import model.Cliente;
import model.FilasAlbaranCliente;
import modelo.negocio.GestorAlbaranCliente;
import vista.clientes.albaranes.VAlbaranCliente;
import vista.clientes.albaranes.VFilaAlbaranCliente;

public class ControladorAlbaranCliente implements InternalFrameListener, FocusListener,ActionListener{
	private GestorAlbaranCliente gac;
	private VAlbaranCliente vAlbaran;
	private List<FilasAlbaranCliente> filasAlb;
	
	public ControladorAlbaranCliente(VAlbaranCliente vAlbaran) {
		this.vAlbaran=vAlbaran;
		gac=new GestorAlbaranCliente();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION)
				if (!vAlbaran.gettNumAlb().getText().equals("")) {
					modificaAlbaran();
					vAlbaran.dispose();
				}
				else
					nuevoAlbaran();
			else
				vAlbaran.dispose();
	}
	
	private void modificaAlbaran() {
		AlbaranCliente albModif=new AlbaranCliente();
		albModif.setNum(Integer.valueOf(vAlbaran.gettNumAlb().getText()));
		asignaCampos(albModif);
		ponFilas(albModif);
		vAlbaran.muestraFilas(albModif);
		
			
		int ok=gac.modificaAlbaran(albModif);
		if (ok==0) {
			ControladorAlbaranesClientes cac = new ControladorAlbaranesClientes(vAlbaran.getvAlbsCli());
			cac.listar(vAlbaran.getvAlbsCli());
			
			
		}
//		//else 
//			//muestraErrores(ok);		
	}
	
	private void nuevoAlbaran() {
		AlbaranCliente albaranNuevo=new AlbaranCliente();
		asignaCampos(albaranNuevo);
		int ok=gac.nuevoAlbaran(albaranNuevo);
		ponFilas(albaranNuevo);
		gac.modificaAlbaranGenerado(albaranNuevo);
		if (ok==0)
			vAlbaran.dispose();
	}
	
	private void asignaCampos(AlbaranCliente albModif) {
		albModif.setFecha(vAlbaran.getcFecha().getDate());
		albModif.setClienteBean((Cliente) vAlbaran.getComboCliente().getSelectedItem());
		albModif.setActualizadoAlmacen(vAlbaran.getChecAlmacen().isSelected());
		vAlbaran.getPanel().updateUI();	
	}

	private void ponFilas(AlbaranCliente albModif) {
		FilasAlbaranCliente filaModif;
		Component[] componentes=vAlbaran.getPanel().getComponents();
		filasAlb=new ArrayList<FilasAlbaranCliente>();
		for (Component fila:componentes) {
			filaModif=new FilasAlbaranCliente();
			VFilaAlbaranCliente fil=(VFilaAlbaranCliente) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,albModif);
			filasAlb.add(filaModif);
		}
		albModif.setFilasAlbaranClientes(filasAlb);
	}
	
	private void asignaCamposFila(VFilaAlbaranCliente fila,FilasAlbaranCliente filaModif,AlbaranCliente albModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setAlbaranCliente(albModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
		filaModif.setPrecio(euroADoble(fila.gettPrecio().getText()));
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
		if (e.getSource()==vAlbaran.getbNuevaFila())
			vAlbaran.nuevaFila();
		if (e.getSource()==vAlbaran.getChecAlmacen()) {
			if (vAlbaran.getChecAlmacen().isSelected())
				gac.actualizaAlmacen(vAlbaran.getAlb(),-1);
			else
				gac.actualizaAlmacen(vAlbaran.getAlb(),1);
			modificaAlbaran();
			vAlbaran.getChecAlmacen().requestFocus();
		}
				
		
	}
	
}
