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

import entidades.AlbaranCliente;
import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasAlbaranCliente;
import modelo.negocio.GestorAlbaranCliente;
import modelo.negocio.GestorFacturaCliente;
import util.Util;
import vista.clientes.albaranes.VAlbaranCliente;
import vista.clientes.albaranes.VFilaAlbaranCliente;
/**
 * Controla el comportamiento de la ventana albar�n de un cliente
 * @author Jose Carlos
 *
 */
public class ControladorAlbaranCliente implements InternalFrameListener, FocusListener,ActionListener{
	private GestorAlbaranCliente gac;
	private VAlbaranCliente vAlbaran;
	private List<FilasAlbaranCliente> filasAlb;
	private Util u;
	/**
	 * El constructor recibe la ventana del albaran del cliente
	 * @param vAlbaran
	 */
	public ControladorAlbaranCliente(VAlbaranCliente vAlbaran) {
		this.vAlbaran=vAlbaran;
		u=new Util();
		gac=new GestorAlbaranCliente();
	}
	/**
	 * Cuando se cierra la ventana pregunta si desea guardar, en caso afirmativo si el albaran es nuevo lo crea nuevo en la base de datos 
	 * y si el albar�n es editado lo modifica en la base de datos
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "�Desea guardar?");
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
	 * Modifica el albaran, en caso de que est� actualizado en almacen y el clienet es minorista, 
	 * si no est� facturado crea la factura en pdf y se la envia al cliente por email
	 */
	private void modificaAlbaran() {
		AlbaranCliente albModif=new AlbaranCliente();
		albModif.setNum(Integer.valueOf(vAlbaran.gettNumAlb().getText()));
		asignaCampos(albModif);
		ponFilas(albModif);
		vAlbaran.muestraFilas(albModif);
		if (albModif.isActualizadoAlmacen() && !albModif.getFacturado() && albModif.getClienteBean().getTipo()==0) {
			GestorFacturaCliente gfc=new GestorFacturaCliente();
			gfc.generaFacturaAlbaranMinorista(albModif);
			albModif.setFacturado(true);
			vAlbaran.correoFactura();
		}
		int ok=gac.modificaAlbaran(albModif);
		if (ok==0) {
			ControladorAlbaranesClientes cac = new ControladorAlbaranesClientes(vAlbaran.getvAlbsCli());
			cac.listar(vAlbaran.getvAlbsCli());
			
			
		}
//		//else 
//			//muestraErrores(ok);		
	}
	/**
	 * Guarda el albaran como nuevo en la base de datos
	 */
	private void nuevoAlbaran() {
		AlbaranCliente albaranNuevo=new AlbaranCliente();
		asignaCampos(albaranNuevo);
		int ok=gac.nuevoAlbaran(albaranNuevo);
		ponFilas(albaranNuevo);
		gac.modificaAlbaranGenerado(albaranNuevo);
		if (ok==0)
			vAlbaran.dispose();
	}
	/**
	 * Asigna los campos de la cabecera de la  ficha del albaran al objeto albModif
	 * @param albModif
	 */
	private void asignaCampos(AlbaranCliente albModif) {
		albModif.setFecha(vAlbaran.getcFecha().getDate());
		albModif.setClienteBean((Cliente) vAlbaran.getComboCliente().getSelectedItem());
		albModif.setActualizadoAlmacen(vAlbaran.getChecAlmacen().isSelected());
		albModif.setFacturado(vAlbaran.getChecFacturado().isSelected());
		vAlbaran.getPanel().updateUI();	
	}
	/**
	 * Asigna las filas de la ficha del albaran al objeto albModif
	 * @param albModif
	 */
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
	/**
	 * Asigna los campos de una fila del albaran a la fila de albaran de la entidad albModif
	 * @param fila
	 * @param filaModif
	 * @param albModif
	 */
	private void asignaCamposFila(VFilaAlbaranCliente fila,FilasAlbaranCliente filaModif,AlbaranCliente albModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setAlbaranCliente(albModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
		filaModif.setPrecio(u.euroADoble(fila.gettPrecio().getText()));
	}
	/**
	 * Asigna las acciones para el bot�n de nueva fila y la casilla de verificaci�n de actualizar alamacen
	 */
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
