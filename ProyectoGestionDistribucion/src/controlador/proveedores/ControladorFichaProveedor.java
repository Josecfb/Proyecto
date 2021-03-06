package controlador.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import entidades.Proveedor;
import modelo.negocio.GestorProveedor;
import modelo.persistencia.DaoProvincia;
import util.Utilidades;
import vista.proveedores.VFichaProveedor;
/**
 * Controla la ventana ficha de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorFichaProveedor implements InternalFrameListener, KeyListener, FocusListener, ActionListener, ItemListener{
	private VFichaProveedor fichaProveedor;
	private Utilidades u;
	/**
	 * El constructor recibe la vista de la ficha de proveedor
	 * @param fichaProveedor vista de la ficha de proveedor VFichaProveedor
	 */
	public ControladorFichaProveedor(VFichaProveedor fichaProveedor) {
		this.fichaProveedor=fichaProveedor;
		u=new Utilidades();
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}
	/**
	 * Cuando se cierra la ventana pregunta si se desea guardar, en caso afirmativo, 
	 * se modifica o crea un proveedor dependiendo de si la ficha era nueva u de un proveedor existente
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		if(fichaProveedor.isModificado()) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "�Desea guardar?");
		if (res==JOptionPane.YES_OPTION) {
			if (fichaProveedor.getPro()!=null)
				modificaProveedor();
			else
				nuevoProvedor();
			ControladorListadoProveedores clp=new ControladorListadoProveedores(fichaProveedor.getVListProv());
			clp.listar();
		}
		else 
			fichaProveedor.dispose();
		}else fichaProveedor.dispose();
	}
	/**
	 * Crea un nuevo proveedor llamando al m�todo nuevoProveedor del GestorProveedor
	 */
	private void nuevoProvedor() {
		Proveedor proNuevo=new Proveedor();
		asignaCampos(proNuevo);
		GestorProveedor gp=new GestorProveedor();
		boolean[] ok=new boolean[4];
		ok=gp.nuevoProveedor(proNuevo);
		if (ok[3])
			fichaProveedor.dispose();
		else {
			muestraErrores(ok);
		}
	}
	/**
	 * Modifica un proveedor existente llamando al m�todo modificarProveedor de GestorProveedor
	 */
	private void modificaProveedor() {
		Proveedor proModif=new Proveedor();
		proModif.setNumero(Integer.valueOf(fichaProveedor.gettNumero().getText()));
		asignaCampos(proModif);
		GestorProveedor gp=new GestorProveedor();
		boolean[] ok=new boolean[4];
		ok=gp.modificarProveedor(proModif);
		if (ok[3])
			fichaProveedor.dispose();
		else 
			muestraErrores(ok);	
	}
	/**
	 * Asigna los campos de la ficha del proveedor al objeto proModif
	 * @param proModif Proveedor
	 */
	private void asignaCampos(Proveedor proModif) {
		proModif.setNombre(fichaProveedor.gettNombre().getText());
		proModif.setDireccion(fichaProveedor.gettDireccion().getText());
		proModif.setCodPost(fichaProveedor.gettCodPos().getText());
		proModif.setNif(fichaProveedor.gettNif().getText());
		proModif.setEmail(fichaProveedor.gettEMail().getText());
		proModif.setNumCuentaContable(Integer.valueOf(fichaProveedor.gettSubcuenta().getText()));
		proModif.setTelefonoFijo(fichaProveedor.gettFijo().getText());
		proModif.setTelefonoMovil(fichaProveedor.gettMovil().getText());
		proModif.setProvincia(fichaProveedor.gettProvincia().getText());
		proModif.setPoblacion((String) fichaProveedor.gettPoblacion().getSelectedItem());
		
	}
	/**
	 * Muestra errores en caso de algunos campos vac�os
	 * @param ok
	 */
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Nombre vac�o","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Nif vac�o","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Email vac�o","error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	/** 
	 * Cambia el color de los campos cuando reciben foco
	 */
	@Override
	public void focusGained(FocusEvent e) {
		u.foco(e);
	}
	/** 
	 * cuando el codigo postal pierde foco, asigna valores a la Provinca y a la poblaci�n
	 * recupera el color de los campos 
	 */
	@Override
	public void focusLost(FocusEvent e) {
		u.nofoco(e);
		if (e.getSource()==fichaProveedor.gettCodPos() && !fichaProveedor.gettCodPos().getText().equals("")) {
			DaoProvincia dp = new DaoProvincia();
			fichaProveedor.gettProvincia().setText(dp.nomProvincia(fichaProveedor.gettCodPos().getText()));
			List<String> poblaciones=dp.nomPoblaciones(fichaProveedor.gettCodPos().getText());
			for (String poblacion:poblaciones) {
				fichaProveedor.gettPoblacion().addItem(poblacion);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Controla que no se exceda la longitud de los campos
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		fichaProveedor.setModificado(true);
		u.controlaTeclas(e);
	}
	/**
	 * Elimina el proveedor al pulsar el bot�n llamando al m�todo borrarProveedor
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fichaProveedor.gettPoblacion())
			fichaProveedor.setModificado(true);
		if (e.getSource()==fichaProveedor.getbBorrar()) {
			borrarProveedor();
		}
		if (e.getSource()==fichaProveedor.gettPoblacion())
			fichaProveedor.setModificado(true);
		System.out.println(fichaProveedor.isModificado());
	}
	/**
	 * Pide confirmacion de borrado de proveedor en caso afirmativo
	 * lo borra llamando al m�todo borrarProveedor del GestorProveedor
	 */
	private void borrarProveedor() {
		
		int res=JOptionPane.showConfirmDialog(new JFrame(), "�Est� seguro que quiere eliminar este proveedor premanentemente?");
		GestorProveedor gp=new GestorProveedor();
		if (res==JOptionPane.YES_OPTION)
			JOptionPane.showMessageDialog(fichaProveedor, gp.borrarProveedor(fichaProveedor.getPro()), "Borrar proveedor", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==fichaProveedor.gettPoblacion())
			fichaProveedor.setModificado(true);
		System.out.println(fichaProveedor.isModificado());
	}

}
