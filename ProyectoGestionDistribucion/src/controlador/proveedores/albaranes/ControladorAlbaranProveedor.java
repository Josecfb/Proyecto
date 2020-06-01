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

import entidades.AlbaranProveedor;
import entidades.Articulo;
import entidades.FilaAlbaranProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import util.Utilidades;
import vista.proveedores.albaranes.VAlbaranProveedor;
import vista.proveedores.albaranes.VFilaAlbaranProveedor;

/**
 * controla la ventana del Albaran de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorAlbaranProveedor implements InternalFrameListener, FocusListener,ActionListener{
	private GestorAlbaranProve gap;
	private VAlbaranProveedor vAlbaran;
	private List<FilaAlbaranProveedor> filasAlb;
	private Utilidades u;
	/**
	 * El constructor recibe como argumento la ventana de albarán de proveedor
	 * @param vAlbaran Vista de la ventana de albarán de proveedor VAlbaranProveedor
	 */
	public ControladorAlbaranProveedor(VAlbaranProveedor vAlbaran) {
		this.vAlbaran=vAlbaran;
		gap=new GestorAlbaranProve();
		u=new Utilidades();
	}
	/**
	 * Al cerrar la ventana confirma si se desea guardar los cambios, en caso afirmativo, lo modifica o lo crea nuevo 
	 * dependiendo de si se estaba modificando o creando uno nuevo
	 */
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
	/**
	 * Modifica el albaran editado llamando a l método modificaAlbaran del GestorAlbaranProveedor
	 * y actualiza el listado de albaranes
	 */
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
	}
	/**
	 * Crea un nuevo albaran llamando al método nuevoAlbaran del GestorAlbaranProveedor
	 */
	private void nuevoAlbaran() {
		AlbaranProveedor albaranNuevo=new AlbaranProveedor();
		asignaCampos(albaranNuevo);
		
		ponFilas(albaranNuevo);
		int ok=gap.nuevoAlbaran(albaranNuevo);
		if (ok==0)
			vAlbaran.dispose();
	}
	/**
	 * Asigna los campos de la cabecera de la ventana del albaran de proveedor al objeto albModif
	 * @param albModif AlbaranProveedor Objeto entidad Albaran de proveedor AlbaranProveedor
	 */
	private void asignaCampos(AlbaranProveedor albModif) {
		albModif.setFecha(vAlbaran.getcFecha().getDate());
		albModif.setProveedore((Proveedor) vAlbaran.getComboProveedor().getSelectedItem());
		albModif.setActualizadoAlmacen(vAlbaran.getChecAlmacen().isSelected());
		vAlbaran.getPanel().updateUI();	
	}
	/**
	 * Asigna los datos de las filas de la ventana del albaran al objeto albModif
	 * Si hay filas repetidas las suma
	 * @param albModif
	 */
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
			if (filasAlb.contains(filaModif))
				filasAlb.get(filasAlb.indexOf(filaModif)).setCantidad(filasAlb.get(filasAlb.indexOf(filaModif)).getCantidad()+filaModif.getCantidad());
			else
				filasAlb.add(filaModif);
		}
		albModif.setFilasAlbaranProveedors(filasAlb);
	}
	/**
	 * Asigna los datos de una fila de la ventana albaran proveedor
	 * @param fila Vista de la fila de albaran de proveedor VFilaAlbaranProveedor
	 * @param filaModif Objeto entidad de fila de albaran de proveedor FilaAlbaranProveedor
	 * @param albModif Objeto entidad albarán de proveedor AlbaranProveedor
	 */
	private void asignaCamposFila(VFilaAlbaranProveedor fila,FilaAlbaranProveedor filaModif,AlbaranProveedor albModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setAlbaranesProveedor(albModif);
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
	 * Crea nuevas filas en el albaran al pulsar el botón
	 */
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
