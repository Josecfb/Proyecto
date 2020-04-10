package controlador.fichas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.proveedores.VFichaProveedor;

public class ControladorFichaProveedor implements InternalFrameListener{
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

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION)
			if (fichaProveedor.getPro()!=null)
				modificaProveedor();
			else
				nuevoProvedor();
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

}
