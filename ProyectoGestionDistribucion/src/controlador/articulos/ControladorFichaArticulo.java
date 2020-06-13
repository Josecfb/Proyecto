package controlador.articulos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import entidades.Articulo;
import entidades.Familia;
import entidades.Proveedor;
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.articulos.VFichaArticulo;
/**
 * Implementa InternalFrameListener, FocusListener, ActionListener, KeyListener
 * @author Jose Carlos
 *
 */
public class ControladorFichaArticulo implements InternalFrameListener, FocusListener, ActionListener, KeyListener, ItemListener {
	private VFichaArticulo fichaArticulo;
	private GestorArticulo ga;
	private Utilidades u;
	
	/**
	 * 
	 * @param fichaArticulo Ventana de la ficha de artículo
	 */
	public ControladorFichaArticulo(VFichaArticulo fichaArticulo) {
		this.fichaArticulo=fichaArticulo;
		ga=new GestorArticulo();
		u=new Utilidades();
	}
	/**
	 * Al cerrar la ventana
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		if(fichaArticulo.isModificado()) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION) {
				//Si la ficha de artículo se abrió con un articulo lo modifica, si nó lo crea nuevo
				if (fichaArticulo.getArt()!=null)
					modificaArticulo();
				else
					nuevoArticulo();
				ControladorListadoArticulos cla=new ControladorListadoArticulos(fichaArticulo.getVListadoArt());
				//Actualiza el listado de artículos
				cla.listar();
			}
			else 
				fichaArticulo.dispose();
		}
		else 
			fichaArticulo.dispose();
	}
	/**
	 * Cuando un campo gana foco cambia de color de fondo y su formato en caso de ser noneda o porcentaje
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==fichaArticulo.getComboFamilia().getEditor().getEditorComponent()) 
			fichaArticulo.getComboFamilia().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
		if (e.getSource()==fichaArticulo.getComboProveedor().getEditor().getEditorComponent()) 
			fichaArticulo.getComboProveedor().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
		u.foco(e);
	}

	/**
	 * Cuando un campo pierde el foco cambia de color de fondo y su formato en caso de ser noneda o porcentaje
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource()==fichaArticulo.getComboProveedor().getEditor().getEditorComponent()) 
			fichaArticulo.getComboProveedor().getEditor().getEditorComponent().setBackground(new Color(255,255,255));
		if (e.getSource()==fichaArticulo.getComboFamilia().getEditor().getEditorComponent()) 
			fichaArticulo.getComboFamilia().getEditor().getEditorComponent().setBackground(new Color(255,255,255));
		u.nofoco(e);
	}

	/**
	 * Asigna los datos del formulario a un objeto Articulo y lo modifica en la base de datos
	 */
	private void modificaArticulo() {
		Articulo artModif=new Articulo();
		artModif.setCod(Integer.valueOf(fichaArticulo.gettCodigo().getText()));
		asignaCampos(artModif);
		ga=new GestorArticulo();
		boolean[] ok=new boolean[6];
		ok=ga.modificarArticulo(artModif);
		if (ok[5])
			fichaArticulo.dispose();
		else 
			muestraErrores(ok);		
	}
	/**
	 * Asigna los datos del formulario a un objeto Articulo y lo crea nuevo en la base de datos
	 */
	private void nuevoArticulo() {
		Articulo artNuevo=new Articulo();
		asignaCampos(artNuevo);
		boolean[] ok=new boolean[6];
		ok=ga.nuevoArticulo(artNuevo);
		if (ok[5])
			fichaArticulo.dispose();
		else {
			muestraErrores(ok);
		}
	}
	/**
	 * rellena los atributos del objeto artModif con los datos del formulario
	 * @param artModif de tipo Articulo
	 */
	private void asignaCampos(Articulo artModif) {
		artModif.setCodpro(fichaArticulo.gettCProv().getText());
		artModif.setNombre(fichaArticulo.gettNombre().getText());
		artModif.setCoste(u.euroADoble(fichaArticulo.gettCoste().getText()));
		artModif.setPrecioMayorista(u.euroADoble(fichaArticulo.gettPrecioMay().getText()));
		artModif.setPrecioMinorista(u.euroADoble(fichaArticulo.gettPrecioMin().getText()));
		artModif.setIva(u.porcentajeADoble(fichaArticulo.gettIva().getText()));
		artModif.setUnidadesCaja(Integer.valueOf(fichaArticulo.gettUnidadesCaja().getText()));
		artModif.setProveedorBean((Proveedor) fichaArticulo.getComboProveedor().getSelectedItem());
		artModif.setFamiliaBean((Familia) fichaArticulo.getComboFamilia().getSelectedItem());
		artModif.setStock(Integer.valueOf(fichaArticulo.gettStock().getText()));
		artModif.setStockMinimo(Integer.valueOf(fichaArticulo.gettStockMin().getText()));
		artModif.setReservados(Integer.valueOf(fichaArticulo.gettReservados().getText()));
	}
	
	/**
	 * dependiendo del valor de cada elemento del array ok muestra mensajes de error
	 * @param ok array de valores boolean con las banderas de los errores
	 */
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Nombre vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Coste menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Precio mayorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[3])
			JOptionPane.showMessageDialog(new JFrame(),"Precio minorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[4])
			JOptionPane.showMessageDialog(new JFrame(),"Artículo sin proveedor","error",JOptionPane.ERROR_MESSAGE);
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
		
	}
	/**
	 * Al pulsar sobre el botón borrar llama al método borrarArtículo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fichaArticulo.getbBorrar())
			borrarArticulo();
	}
	/**
	 * Confirma eliminación del artículo, si es afirmativo llama al metodo borrarArtículo de la clase GestorArticulo
	 */
	private void borrarArticulo() {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Está seguro que quiere eliminar este artículo premanentemente?");
		if (res==JOptionPane.YES_OPTION)
			JOptionPane.showMessageDialog(fichaArticulo, ga.borrarArticulo(fichaArticulo.getArt()), "Borrar artículo", JOptionPane.INFORMATION_MESSAGE);
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
	 * Controla que no se excedan las longitudes de los campos
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		u.controlaTeclas(e);
		fichaArticulo.setModificado(true);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		fichaArticulo.setModificado(true);
		
	}
	
	
}
