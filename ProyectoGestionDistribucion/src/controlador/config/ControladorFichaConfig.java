package controlador.config;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import entidades.Datosempresa;
import modelo.negocio.GestorDatosEmpresa;
import vista.empresa.VFichaEmpresa;
/**
 * Controla la ventana de configuración de los datos de la empresa
 * @author Jose Carlos
 *
 */
public class ControladorFichaConfig implements FocusListener,InternalFrameListener{
	private VFichaEmpresa vFichaEmp;
	private GestorDatosEmpresa gde;
	/**
	 * El Constructor recibe la ventana de la ficha de empresa
	 * @param vFichaEmp vista de ventana de la ficha de empresa VFichaEmpresa
	 */
	public ControladorFichaConfig(VFichaEmpresa vFichaEmp) {
		this.vFichaEmp = vFichaEmp;
	}
	/**
	 * Cuando un campo recibe foco cambia su color de fondo 
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
	}
	/**
	 * Cuando un campo pierde foco recupera su color de fondo
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class || e.getSource().getClass()==JComboBox.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Al cerrar la ventana pregunta si se desea guardar los cambios, en caso afirmativo llama al metodo modificaEmpresa si no cierra la ventana
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION) {
			modificaEmpresa();
		}
		else 
			vFichaEmp.dispose();
	}
	/**
	 * Llama al método guardaEmpresa del GestorDatosEmpresa
	 */
	private void modificaEmpresa() {
		Datosempresa empresa=new Datosempresa();
		asignaCampos(empresa);
		gde=new GestorDatosEmpresa();
		gde.guardaEmpresa(empresa);
		vFichaEmp.dispose();

	}
	/**
	 * Asigna los campos de la ventana al objeto empModif
	 * @param empModif
	 */
	private void asignaCampos(Datosempresa empModif) {
		empModif.setNif(vFichaEmp.gettNif().getText());
		empModif.setNombre(vFichaEmp.gettNombre().getText());
		empModif.setCodpos(vFichaEmp.gettCodPos().getText());
		empModif.setDireccion(vFichaEmp.gettDireccion().getText());
		empModif.setEmail(vFichaEmp.gettEMail().getText());
		empModif.setFijo(vFichaEmp.gettFijo().getText());
		empModif.setMovil(vFichaEmp.gettMovil().getText());
		empModif.setPoblacion(vFichaEmp.gettPoblacion().getText());
		empModif.setProvincia(vFichaEmp.gettProvincia().getText());
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
