package controlador.proveedores.albaranes;

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

import model.AlbaranProveedor;
import model.Articulo;
import model.FilaAlbaranProveedor;
import model.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import vista.proveedores.albaranes.VAlbaranProveedor;
import vista.proveedores.albaranes.VFilaAlbaranProveedor;


public class ControladorAlbaranProveedor implements InternalFrameListener, FocusListener,ActionListener{
	private GestorAlbaranProve gap;
	private VAlbaranProveedor vAlbaran;
	private List<FilaAlbaranProveedor> filasAlb;
	
	public ControladorAlbaranProveedor(VAlbaranProveedor vAlbaran) {
		this.vAlbaran=vAlbaran;
		gap=new GestorAlbaranProve();
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
		AlbaranProveedor albModif=new AlbaranProveedor();
		albModif.setNum(Integer.valueOf(vAlbaran.gettNumAlb().getText()));
		asignaCampos(albModif);
		ponFilas(albModif);
		vAlbaran.muestraFilas(albModif);
		
			
		int ok=gap.modificaAlbaran(albModif);
		if (ok==0) {
			ControladorAlbaranesProveedores cap = new ControladorAlbaranesProveedores(vAlbaran.getvAlbsPro());
			cap.listar(vAlbaran.getvAlbsPro());
			
			
		}
//		//else 
//			//muestraErrores(ok);		
	}
	
	private void nuevoAlbaran() {
		AlbaranProveedor albaranNuevo=new AlbaranProveedor();
		asignaCampos(albaranNuevo);
		int ok=gap.nuevoAlbaran(albaranNuevo);
		ponFilas(albaranNuevo);
		gap.modificaAlbaranGenerado(albaranNuevo);
		if (ok==0)
			vAlbaran.dispose();
	}
	
	private void asignaCampos(AlbaranProveedor albModif) {
		albModif.setFecha(vAlbaran.getcFecha().getDate());
		albModif.setProveedore((Proveedor) vAlbaran.getComboProveedor().getSelectedItem());
		albModif.setActualizadoAlmacen(vAlbaran.getChecAlmacen().isSelected());
		vAlbaran.getPanel().updateUI();	
	}

	private void ponFilas(AlbaranProveedor albModif) {
		FilaAlbaranProveedor filaModif;
		Component[] componentes=vAlbaran.getPanel().getComponents();
		filasAlb=new ArrayList<FilaAlbaranProveedor>();
		for (Component fila:componentes) {
			filaModif=new FilaAlbaranProveedor();
			VFilaAlbaranProveedor fil=(VFilaAlbaranProveedor) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,albModif);
			filasAlb.add(filaModif);
		}
		albModif.setFilasAlbaranProveedors(filasAlb);
	}
	
	private void asignaCamposFila(VFilaAlbaranProveedor fila,FilaAlbaranProveedor filaModif,AlbaranProveedor albModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setAlbaranesProveedor(albModif);
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
		if (e.getSource()==vAlbaran.getbNuevaFila())
			vAlbaran.nuevaFila();
		if (e.getSource()==vAlbaran.getChecAlmacen()) {
			if (vAlbaran.getChecAlmacen().isSelected())
				gap.actualizaAlmacen(vAlbaran.getAlb(),1);
			else
				gap.actualizaAlmacen(vAlbaran.getAlb(),-1);
			modificaAlbaran();
			
			vAlbaran.getChecAlmacen().requestFocus();
		}
				
		
	}
	
}
