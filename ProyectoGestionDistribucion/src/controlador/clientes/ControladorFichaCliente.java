package controlador.clientes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import entidades.Articulo;
import entidades.Cliente;
import entidades.PrecioCliente;
import modelo.negocio.GestorCliente;
import modelo.persistencia.DaoProvincia;
import util.Utilidades;
import vista.clientes.VFichaCliente;
import vista.clientes.VFilaPrecioCliente;
/**
 * Controla la ficha de un cliente, implementa InternalFrameListener, KeyListener, FocusListener, ActionListener
 * @author Jose Carlos
 *
 */
public class ControladorFichaCliente implements InternalFrameListener, KeyListener, FocusListener, ActionListener, ItemListener {
	private VFichaCliente fichaCliente;
	private GestorCliente gc;
	private List<PrecioCliente> filasPrecio;
	private Utilidades u;
	/**
	 * Recibe la vista de la ficha del cliente
	 * @param fichaCliente vista de la ficha de cliente
	 */
	public ControladorFichaCliente(VFichaCliente fichaCliente) {
		this.fichaCliente=fichaCliente;
		gc=new GestorCliente();
		u=new Utilidades();
	}
	/**
	 * Al cerrar la ficha del cliente, Si la ficha era de un cliente existente lo modifica y si era sin cliente lo crea nuevo en la base de datos
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		if(fichaCliente.isModificado()) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION) {
				// Si la ficha era de un cliente existente lo modifica y si era sin cliente lo crea nuevo en la base de datos
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
		else
			fichaCliente.dispose();
	}
	/**
	 * Guarda nuevo cliente
	 */
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
	/**
	 * Modifica cliente existente
	 */
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
	/**
	 * Dependiendo de los valores de array ok muestra diferentes errores
	 * @param ok
	 */
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
	/**
	 * Asigna los datos de la ficha al objeto cliModif
	 * @param cliModif objeto de la clase Cliente
	 */
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
		cliModif.setConfirmado(fichaCliente.getChkConfirmado().isSelected());
	}
	/**
	 * Asigna los precios de articulos especiales encontrados el la ficha del cliente para el cliente
	 * @param cliModif Cliente que va a recibir los precios especiales de articulos
	 */
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
	/**
	 * Asigna cada dato de la fila de precios cliente de la ficha del cliente a su lista de precios especiales
	 * @param vFila Fila de la ficha con un precio especial
	 * @param filaModif precio articulo cliente
	 * @param cliModif cliente al que se le asignan los precios
	 */
	private void asignaCamposFila(VFilaPrecioCliente vFila,PrecioCliente filaModif,Cliente cliModif) {
		vFila.updateUI();
		vFila.getComboArt().requestFocus();
		Articulo arti=(Articulo) vFila.getComboArt().getSelectedItem();
		filaModif.setClienteBean(cliModif);
		filaModif.setArticuloBean(arti);
		filaModif.setPrecio(u.euroADoble(vFila.gettPrecio().getText()));
	}


	/**
	 * Controla que no se excedan las longitudes de los campos
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		u.controlaTeclas(e);
		fichaCliente.setModificado(true);
	}
	/**
	 * Cuando un campo recibe foco cambia el color de fondo y selecciona su contenido
	 */
	@Override
	public void focusGained(FocusEvent e) {
		u.foco(e);
		if (e.getSource()==fichaCliente.getComboTipo().getEditor().getEditorComponent()) 
			fichaCliente.getComboTipo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/**
	 * Cuando un campo pierde foco cambia de color, al salir de tipo de cliente pinta label con nombre y apellidos o nombre comercial y fiscal
	 * al salir de código postal asigna la provincia y la población
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void focusLost(FocusEvent e) {
		u.nofoco(e);
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
	/**
	 * Al pulsar el boton nueva fila de precio crea una nueva fila para precio especial
	 * y controla la pulsacion del botón de borrar cliente
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fichaCliente.getbNuevaFila()) {
			fichaCliente.nuevaFilaPrecio();
			fichaCliente.setModificado(true);
		}
		if (e.getSource()==fichaCliente.getbBorrar()) {
			borrarCliente();
		}
		if (e.getSource()==fichaCliente.getChkConfirmado()) {
			fichaCliente.setModificado(true);
		}
		
	}
	
	/**
	 * Confirma si se desea borrar el cliente
	 * y llama al metodo borrar cliente de la clase GestorCliente
	 */
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==fichaCliente.getComboTipo())
			fichaCliente.setModificado(true);
		if (e.getSource()==fichaCliente.gettPoblacion())
			fichaCliente.setModificado(true);
		
	}

}
