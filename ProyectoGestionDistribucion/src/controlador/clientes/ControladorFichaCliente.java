package controlador.clientes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import model.Cliente;
import modelo.negocio.GestorCliente;
import vista.clientes.VFichaCliente;

public class ControladorFichaCliente implements InternalFrameListener {
	private VFichaCliente fichaCliente;
	private GestorCliente gc;

	public ControladorFichaCliente(VFichaCliente fichaCliente) {
		this.fichaCliente=fichaCliente;
		gc=new GestorCliente();
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION)
			if (fichaCliente.getCli()!=null)
				modificaCliente();
			else
				nuevoCliente();
		else
			fichaCliente.dispose();
	}
	
	private void nuevoCliente() {
		Cliente cliNuevo=new Cliente();
		asignaCampos(cliNuevo);
		boolean[] ok=new boolean[4];
		ok=gc.nuevoCliente(cliNuevo);
		if (ok[3])
			fichaCliente.dispose();
		else {
			muestraErrores(ok);
		}
	}

	private void modificaCliente() {
		Cliente cliModif=new Cliente();
		cliModif.setNumero(Integer.valueOf(fichaCliente.gettNumero().getText()));
		asignaCampos(cliModif);
		boolean[] ok=new boolean[4];
		ok=gc.modificarProveedor(cliModif);
		if (ok[3])
			fichaCliente.dispose();
		else 
			muestraErrores(ok);	
	}
	
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Nombre o nombre comercial vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Nif vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Email vacío","error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void asignaCampos(Cliente cliModif) {
		if (fichaCliente.getComboTipo().getSelectedItem()!=null)
			if (fichaCliente.getComboTipo().getSelectedItem().equals("Mayorista"))
				cliModif.setTipo(1);
			else
				cliModif.setTipo(2);
		cliModif.setNifCif(fichaCliente.gettNif().getText());
		cliModif.setNombre(fichaCliente.gettNombre().getText());
		cliModif.setApellidos(fichaCliente.gettApellidos().getText());
		cliModif.setEmail(fichaCliente.gettEmail().getText());
		cliModif.setTelefonoFijo(fichaCliente.gettFijo().getText());
		cliModif.setTelefonoMovil(fichaCliente.gettMovil().getText());
		cliModif.setNombreComercial(fichaCliente.gettNomComercial().getText());
		cliModif.setNombreFiscal(fichaCliente.gettNomFiscal().getText());
		cliModif.setDireccion(fichaCliente.gettDireccion().getText());
		cliModif.setCodPost(fichaCliente.gettCodPos().getText());
		if (fichaCliente.gettSubcuenta().getText()==null) fichaCliente.gettSubcuenta().setText("0");
		cliModif.setNumCuentaContable(Integer.valueOf(fichaCliente.gettSubcuenta().getText()));
		cliModif.setPoblacion(fichaCliente.gettPoblacion().getText());
		cliModif.setProvincia(fichaCliente.gettProvincia().getText());
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
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
}
