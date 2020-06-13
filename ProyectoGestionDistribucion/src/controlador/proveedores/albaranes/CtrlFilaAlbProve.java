package controlador.proveedores.albaranes;

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
import java.text.NumberFormat;
import javax.swing.JTextField;
import entidades.Articulo;
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.proveedores.albaranes.VFilaAlbaranProveedor;
/**
 * Controla los eventos de la fila de albaran de proveedor
 * @author Jose Carlos
 *
 */
public class CtrlFilaAlbProve implements FocusListener, ActionListener, KeyListener, ItemListener{
	private VFilaAlbaranProveedor vFilaAlb;
	private NumberFormat formatoeuro;
	private Utilidades u;
	/**
	 * Constructor recibe la vista de la fila del albaran de proveedor
	 * @param vFilaAlb vista de la fila del albaran de proveedor VFilaAlbaranProveedor
	 */
	public CtrlFilaAlbProve(VFilaAlbaranProveedor vFilaAlb) {
		this.vFilaAlb=vFilaAlb;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Cuando los campos reciben foco cambian de color
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent())
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
		u.foco(e);
	}
	/** 
	 * Cuando el combo de articulo pierde foco rellena los campos precio y codigo de artículo
	 * cuando el codigo de articulo pierde foco rellena el combo de artículo  
	 * cuando el campo cajas pierde foco calcula las unidades y el total
	 */
	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaAlb.updateUI();
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent() && vFilaAlb.getArticulo().getSelectedItem()!=null) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.getFila().setArticuloBean(art);
			vFilaAlb.updateUI();
			vFilaAlb.gettCoste().setText(formatoeuro.format(art.getCoste())); 
			vFilaAlb.gettCod().setText(String.valueOf(art.getCod()));
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
		}
		
		if (e.getSource()==vFilaAlb.gettCod()) {
			vFilaAlb.updateUI();
			if (!vFilaAlb.gettCod().getText().equals("0"))
				vFilaAlb.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaAlb.gettCod().getText())));
			vFilaAlb.gettCod().setBackground(Color.WHITE);
		}
		if (e.getSource()==vFilaAlb.gettCajas() && !vFilaAlb.gettCajas().getText().equals("0")) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaAlb.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaAlb.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaAlb.gettUnidades().getText())*u.euroADoble(vFilaAlb.gettCoste().getText())));
			vFilaAlb.gettCajas().setBackground(Color.WHITE);
			vFilaAlb.getvAlbaran().actualizaTotal();
			if (!vFilaAlb.gettCod().getText().equals("0") && !vFilaAlb.gettCajas().getText().equals("0") && !hayFilasVacias())
				vFilaAlb.getvAlbaran().nuevaFila();
			vFilaAlb.getvAlbaran().setModificado(true);
		}
		u.nofoco(e);
	}
	
	public boolean hayFilasVacias() {
		Component[] filas=vFilaAlb.getvAlbaran().getPanel().getComponents();
		for(Component componente:filas) {
			VFilaAlbaranProveedor fila=(VFilaAlbaranProveedor) componente;
			if(fila.gettCod().getText().equals("0"))
				return true;
		}
		return false;
	}
	
	/**
	 * Al pulsar el botón borra la fila del albarán
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaAlb.getbBorrar()) {
			vFilaAlb.getvAlbaran().getcFecha().requestFocus();
			vFilaAlb.getvAlbaran().getPanel().remove(vFilaAlb);
			vFilaAlb.getvAlbaran().actualizaTotal();
			vFilaAlb.getvAlbaran().updateUI();
			vFilaAlb.getvAlbaran().setModificado(true);
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
		u.controlaTeclas(e);
		vFilaAlb.getvAlbaran().setModificado(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		vFilaAlb.getvAlbaran().setModificado(true);
	}

}
