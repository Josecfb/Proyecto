package controlador.proveedores;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import model.Proveedor;
import modelo.negocio.GestorProveedor;
import modelo.persistencia.DaoCliente;
import modelo.persistencia.DaoProvincia;
import vista.proveedores.VFichaProveedor;
import vista.proveedores.VListadoProveedores;

public class ControladorFichaProveedor implements InternalFrameListener, KeyListener, FocusListener{
	private VFichaProveedor fichaProveedor;
	
	public ControladorFichaProveedor(VFichaProveedor fichaProveedor) {
		this.fichaProveedor=fichaProveedor;
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}

	@SuppressWarnings("unused")
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION) {
			if (fichaProveedor.getPro()!=null)
				modificaProveedor();
			else
				nuevoProvedor();
			Component[] componentes=fichaProveedor.getV().getPanelInterior().getComponents();
			for (Component componente:componentes)
				if (componente.getClass()==VListadoProveedores.class) {
					VListadoProveedores lista=(VListadoProveedores) componente;
					lista.getbActualizar().doClick();
				}
		}
		else 
			fichaProveedor.dispose();
	}
	
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
	
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Nombre vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Nif vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Email vacío","error",JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
		if (e.getSource()==fichaProveedor.gettCodPos() && !fichaProveedor.gettCodPos().equals("")) {
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

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource()==fichaProveedor.gettNombre())
			if(fichaProveedor.gettNombre().getText().length()==30)
				e.consume();
		if (e.getSource()==fichaProveedor.gettDireccion())
			if(fichaProveedor.gettDireccion().getText().length()==40)
				e.consume();
		if (e.getSource()==fichaProveedor.gettProvincia())
			if(fichaProveedor.gettProvincia().getText().length()==22)
				e.consume();
		if (e.getSource()==fichaProveedor.gettCodPos())
			if(fichaProveedor.gettCodPos().getText().length()==5)
				e.consume();
		if (e.getSource()==fichaProveedor.gettMovil())
			if(fichaProveedor.gettMovil().getText().length()==9)
				e.consume();
		if (e.getSource()==fichaProveedor.gettFijo())
			if(fichaProveedor.gettFijo().getText().length()==9)
				e.consume();
		if (e.getSource()==fichaProveedor.gettEMail())
			if(fichaProveedor.gettEMail().getText().length()==30)
				e.consume();
		if (e.getSource()==fichaProveedor.gettSubcuenta())
			if(fichaProveedor.gettSubcuenta().getText().length()==11)
				e.consume();
		if (e.getSource()==fichaProveedor.gettNif())
			if(fichaProveedor.gettNif().getText().length()==9)
				e.consume();
	}

}
