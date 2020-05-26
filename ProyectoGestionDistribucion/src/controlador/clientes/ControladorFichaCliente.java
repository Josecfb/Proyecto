package controlador.clientes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import entidades.Articulo;
import entidades.Cliente;
import entidades.PrecioCliente;
import modelo.negocio.GestorCliente;
import modelo.persistencia.DaoProvincia;
import vista.clientes.VFichaCliente;
import vista.clientes.VFilaPrecioCliente;

public class ControladorFichaCliente implements InternalFrameListener, KeyListener, FocusListener, ActionListener {
	private VFichaCliente fichaCliente;
	private GestorCliente gc;
	private List<PrecioCliente> filasPrecio;

	public ControladorFichaCliente(VFichaCliente fichaCliente) {
		this.fichaCliente=fichaCliente;
		gc=new GestorCliente();
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION) {
			if (fichaCliente.getCli()!=null)
				modificaCliente();
			else
				nuevoCliente();
		ControladorListadoClientes clc=new ControladorListadoClientes(fichaCliente.getVlc());
		clc.listar();
		}
		else
			fichaCliente.dispose();
	}
	
	private void nuevoCliente() {
		Cliente cliNuevo=new Cliente();
		asignaCampos(cliNuevo);
		boolean[] ok=new boolean[4];
		//ponFilasPrecios(cliNuevo);
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
		ponFilasPrecios(cliModif);
		boolean[] ok=new boolean[4];
		ok=gc.modificarCliente(cliModif);
		if (ok[3])
			fichaCliente.dispose();
		else 
			muestraErrores(ok);	
	}
	
	private void muestraErrores(boolean[] ok) {
		String mensaje="";
		if (!ok[0])
			mensaje+="Nombre o nombre comercial vacío. \n";
		if (!ok[1])
			mensaje+="Nif vacío. \n";
		if (!ok[2])
			mensaje+="Email vacío \n";
		JOptionPane.showMessageDialog(new JFrame(),mensaje,"error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void asignaCampos(Cliente cliModif) {
		if (fichaCliente.getComboTipo().getSelectedItem()!=null)
			if (fichaCliente.getComboTipo().getSelectedItem().equals("Mayorista"))
				cliModif.setTipo(1);
			else
				cliModif.setTipo(0);
		cliModif.setNifCif(fichaCliente.gettNif().getText());
		cliModif.setNombre(fichaCliente.gettNombre().getText());
		cliModif.setApellidos(fichaCliente.gettApellidos().getText());
		cliModif.setEmail(fichaCliente.gettEmail().getText());
		cliModif.setTelefonoFijo(fichaCliente.gettFijo().getText());
		cliModif.setTelefonoMovil(fichaCliente.gettMovil().getText());
		cliModif.setDireccion(fichaCliente.gettDireccion().getText());
		cliModif.setCodPost(fichaCliente.gettCodPos().getText());
		if (fichaCliente.gettSubcuenta().getText()==null) fichaCliente.gettSubcuenta().setText("0");
		cliModif.setNumCuentaContable(Integer.valueOf(fichaCliente.gettSubcuenta().getText()));
		cliModif.setPoblacion((String) fichaCliente.gettPoblacion().getSelectedItem());
		cliModif.setProvincia(fichaCliente.gettProvincia().getText());
	}
	
	private void ponFilasPrecios(Cliente cliModif) {
		PrecioCliente filaModif;
		Component[] componentes=fichaCliente.getPanel().getComponents();
		filasPrecio=new ArrayList<PrecioCliente>();
		for (Component fila:componentes) {
			filaModif=new PrecioCliente();
			VFilaPrecioCliente fil=(VFilaPrecioCliente) fila;
			fil.updateUI();
			asignaCamposFila(fil,filaModif,cliModif);
			filasPrecio.add(filaModif);
		}
		cliModif.setPreciosClientes(filasPrecio);
	}
	
	private void asignaCamposFila(VFilaPrecioCliente fila,PrecioCliente filaModif,Cliente cliModif) {
		fila.updateUI();
		fila.getComboArt().requestFocus();
		Articulo arti=(Articulo) fila.getComboArt().getSelectedItem();
		filaModif.setClienteBean(cliModif);
		filaModif.setArticuloBean(arti);
		filaModif.setPrecio(euroADoble(fila.gettPrecio().getText()));
	}
	
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource()==fichaCliente.gettNif())
			if(fichaCliente.gettNif().getText().length()==9)
				e.consume();
		if (e.getSource()==fichaCliente.gettNombre())
			if(fichaCliente.gettNombre().getText().length()==20)
				e.consume();
		if (e.getSource()==fichaCliente.gettApellidos())
			if(fichaCliente.gettApellidos().getText().length()==20)
				e.consume();
		if (e.getSource()==fichaCliente.gettDireccion())
			if(fichaCliente.gettDireccion().getText().length()==40)
				e.consume();
		if (e.getSource()==fichaCliente.gettProvincia())
			if(fichaCliente.gettProvincia().getText().length()==22)
				e.consume();
		if (e.getSource()==fichaCliente.gettEmail())
			if(fichaCliente.gettEmail().getText().length()==30)
				e.consume();
		if (e.getSource()==fichaCliente.gettFijo())
			if(fichaCliente.gettFijo().getText().length()==9)
				e.consume();
		if (e.getSource()==fichaCliente.gettMovil())
			if(fichaCliente.gettMovil().getText().length()==9)
				e.consume();
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==fichaCliente.getComboTipo().getEditor().getEditorComponent()) 
			fichaCliente.getComboTipo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
		if (e.getSource()==fichaCliente.getComboTipo().getEditor().getEditorComponent()) {
			if (fichaCliente.getComboTipo().getSelectedItem()=="Minorista") {
				fichaCliente.getpMay().setVisible(false);
				fichaCliente.getpMin().setVisible(true);
			}
			fichaCliente.getComboTipo().getEditor().getEditorComponent().setBackground(Color.WHITE);
		}
		if (e.getSource()==fichaCliente.gettCodPos() && !fichaCliente.gettCodPos().equals("")) {
			DaoProvincia dp = new DaoProvincia();
			fichaCliente.gettProvincia().setText(dp.nomProvincia(fichaCliente.gettCodPos().getText()));
			List<String> poblaciones=dp.nomPoblaciones(fichaCliente.gettCodPos().getText());
			for (String poblacion:poblaciones) {
				fichaCliente.gettPoblacion().addItem(poblacion);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fichaCliente.getbNuevaFila()) {
			fichaCliente.nuevaFilaPrecio();
		}
		if (e.getSource()==fichaCliente.getbBorrar()) {
			borrarCliente();
		}
		
	}
	

	private void borrarCliente() {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Está seguro que quiere eliminar este cliente premanentemente?");
		if (res==JOptionPane.YES_OPTION)
			JOptionPane.showMessageDialog(fichaCliente, gc.borrarCliente(fichaCliente.getCli()), "Borrar cliente", JOptionPane.INFORMATION_MESSAGE);
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
