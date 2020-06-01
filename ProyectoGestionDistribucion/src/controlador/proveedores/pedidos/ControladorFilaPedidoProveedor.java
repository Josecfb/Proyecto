package controlador.proveedores.pedidos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JTextField;

import entidades.Articulo;
import entidades.Proveedor;
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.proveedores.pedidos.VFilaPedidoProveedor;
/**
 * Controla los eventos de la fila de pedido de proveedor
 * @author Jose Carlos
 *
 */
public class ControladorFilaPedidoProveedor implements FocusListener, ActionListener{
	private VFilaPedidoProveedor vFilaPedido;
	private NumberFormat formatoeuro;
	private Utilidades u;
	/**
	 * Constructor
	 * @param vFilaPedido Recibe la vista de la fila de pedido de proveedor VFilaPedidoProveedor
	 */
	public ControladorFilaPedidoProveedor(VFilaPedidoProveedor vFilaPedido) {
		this.vFilaPedido=vFilaPedido;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Cuando los campos reciben foco cambia su color de fondo
	 */
	@Override
	public void focusGained(FocusEvent e) {

		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaPedido.getArticulo().getEditor().getEditorComponent())
			vFilaPedido.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/** 
	 * Cuando el combo de articulo pierde foco rellena los campos coste y codigo de artículo
	 * cuando el codigo de articulo pierde foco rellena el combo de artículo  
	 * cuando el campo cajas pierde foco calcula las unidades y el total
	 */
	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaPedido.updateUI();
		if (e.getSource()==vFilaPedido.getArticulo().getEditor().getEditorComponent()) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.getFila().setArticuloBean(art);
			vFilaPedido.updateUI();
			vFilaPedido.gettCoste().setText(formatoeuro.format(art.getCoste())); 
			vFilaPedido.gettCod().setText(String.valueOf(art.getCod()));
			vFilaPedido.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
			return;
		}
		
		if (e.getSource()==vFilaPedido.gettCod()) {
			vFilaPedido.updateUI();
			GestorArticulo ga=new GestorArticulo();
			if (!vFilaPedido.gettCod().getText().equals("0"))
				vFilaPedido.getArticulo().setSelectedItem(ga.existe(Integer.parseInt(vFilaPedido.gettCod().getText()),(Proveedor)vFilaPedido.getvPedido().getComboProveedor().getSelectedItem()));
			vFilaPedido.gettCod().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaPedido.gettCajas()) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaPedido.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaPedido.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaPedido.gettUnidades().getText())*u.euroADoble(vFilaPedido.gettCoste().getText())));
			vFilaPedido.gettCajas().setBackground(Color.WHITE);
			vFilaPedido.getvPedido().actualizaTotal();
			return;
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
	}
	
	

	/**
	 * Cuando se pulsa el botón borrar fila elimina la fila del pedido
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaPedido.getbBorrar()) {
			vFilaPedido.getvPedido().getcFecha().requestFocus();
			vFilaPedido.getvPedido().getPanel().remove(vFilaPedido);
			vFilaPedido.getvPedido().updateUI();
		}
		
	}

}
