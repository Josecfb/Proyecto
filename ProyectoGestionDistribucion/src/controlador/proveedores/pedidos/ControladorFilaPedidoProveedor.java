package controlador.proveedores.pedidos;

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
public class ControladorFilaPedidoProveedor implements FocusListener, ActionListener, KeyListener, ItemListener{
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
		u.foco(e);
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
		if (e.getSource()==vFilaPedido.getArticulo().getEditor().getEditorComponent() && vFilaPedido.getArticulo().getSelectedItem()!=null) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.getFila().setArticuloBean(art);
			vFilaPedido.updateUI();
			vFilaPedido.gettCoste().setText(formatoeuro.format(art.getCoste())); 
			vFilaPedido.gettCod().setText(String.valueOf(art.getCod()));
			vFilaPedido.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
		}
		
		if (e.getSource()==vFilaPedido.gettCod()) {
			vFilaPedido.updateUI();
			GestorArticulo ga=new GestorArticulo();
			if (!vFilaPedido.gettCod().getText().equals("0"))
				vFilaPedido.getArticulo().setSelectedItem(ga.existe(Integer.parseInt(vFilaPedido.gettCod().getText()),(Proveedor)vFilaPedido.getvPedido().getComboProveedor().getSelectedItem()));
			vFilaPedido.gettCod().setBackground(Color.WHITE);
		}
		if (e.getSource()==vFilaPedido.gettCajas()  && !vFilaPedido.gettCajas().getText().equals("0")) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaPedido.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaPedido.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaPedido.gettUnidades().getText())*u.euroADoble(vFilaPedido.gettCoste().getText())));
			vFilaPedido.gettCajas().setBackground(Color.WHITE);
			vFilaPedido.getvPedido().actualizaTotal();
			if (!vFilaPedido.gettCod().getText().equals("0") && !vFilaPedido.gettCajas().getText().equals("0") && !hayFilasVacias())
				vFilaPedido.getvPedido().nuevaFila();
		}
		u.nofoco(e);
	}
	
	public boolean hayFilasVacias() {
		Component[] filas=vFilaPedido.getvPedido().getPanel().getComponents();
		for(Component componente:filas) {
			VFilaPedidoProveedor fila=(VFilaPedidoProveedor) componente;
			if(fila.gettCod().getText().equals("0"))
				return true;
		}
		return false;
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
			vFilaPedido.getvPedido().setModificado(true);
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
		vFilaPedido.getvPedido().setModificado(true);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		vFilaPedido.getvPedido().setModificado(true);
	}

}
