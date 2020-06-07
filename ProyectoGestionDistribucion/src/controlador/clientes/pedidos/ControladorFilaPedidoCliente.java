package controlador.clientes.pedidos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import entidades.Articulo;
import entidades.PrecioCliente;
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.clientes.pedidos.VFilaPedidoCliente;
/**
 * Controla la vista de fila de pedido de cliente
 * @author Jose Carlos
 *
 */
public class ControladorFilaPedidoCliente implements FocusListener, ActionListener, KeyListener{
	private VFilaPedidoCliente vFilaPedido;
	private NumberFormat formatoeuro;
	private Utilidades u; 
	/**
	 * El constructor recibe como argumento la vista de la fila de pedido de cliente
	 * @param vFilaPedido vista de la fila de pedido de cliente VFilaPedidoCliente
	 */
	public ControladorFilaPedidoCliente(VFilaPedidoCliente vFilaPedido) {
		this.vFilaPedido=vFilaPedido;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Cuando los campos ganan foco cambian de color de fondo y de formaro en caso de tener el signo €
	 */
	@Override
	public void focusGained(FocusEvent e) {
		u.foco(e);
		if (e.getSource()==vFilaPedido.getArticulo().getEditor().getEditorComponent())
			vFilaPedido.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}

	/**
	 * Cuando el campo articulo pierde foco asigna valor al precio con el valor correspondiente 
	 * en función de si el cliente tiene precio especial en dicho artículo o
	 * si el cliente es mayorista o minorista y  al código del artículo
	 * cuando el campo código pierde foco asigna valor al combobox artículo
	 * cuando las unidades pierden foco calcula el total y actualiza el total general
	 */
	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaPedido.updateUI();
		if (e.getSource()==vFilaPedido.getArticulo().getEditor().getEditorComponent() && !vFilaPedido.getArticulo().getSelectedItem().equals("")) {
			System.out.println(vFilaPedido.getArticulo().getSelectedItem());
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.getFila().setArticuloBean(art);
			vFilaPedido.updateUI();
			vFilaPedido.gettCod().setText(String.valueOf(art.getCod()));
			vFilaPedido.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
			PrecioCliente precli=new PrecioCliente(vFilaPedido.getvPedido().getPed().getClienteBean(), art);
			if(vFilaPedido.getvPedido().getPed().getClienteBean().getPreciosClientes().contains(precli))
				vFilaPedido.gettPrecio().setText(formatoeuro.format(vFilaPedido.getvPedido().getPed().getClienteBean().getPreciosClientes().get(vFilaPedido.getvPedido().getPed().getClienteBean().getPreciosClientes().indexOf(precli)).getPrecio()));
			else 
				if(vFilaPedido.getvPedido().getPed().getClienteBean().getTipo()==1)
					vFilaPedido.gettPrecio().setText(formatoeuro.format(art.getPrecioMayorista()));
				else
					vFilaPedido.gettPrecio().setText(formatoeuro.format(art.getPrecioMinorista()));
			
		}
		
		if (e.getSource()==vFilaPedido.gettUnidades()) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaPedido.gettUnidades().getText())*u.euroADoble(vFilaPedido.gettPrecio().getText())));
			vFilaPedido.getvPedido().actualizaTotal();
			if (!vFilaPedido.gettCod().getText().equals("0") && !vFilaPedido.gettUnidades().getText().equals("0") && !hayFilasVacias())
				vFilaPedido.getvPedido().nuevaFila();
		}
		
		if (e.getSource()==vFilaPedido.gettCod()) {
			vFilaPedido.updateUI();
			if (!vFilaPedido.gettCod().getText().equals("0"))
				vFilaPedido.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaPedido.gettCod().getText())));
			vFilaPedido.gettCod().setBackground(Color.WHITE);
			
		}
		u.nofoco(e);
	}
	
	public boolean hayFilasVacias() {
		Component[] filas=vFilaPedido.getvPedido().getPanel().getComponents();
		for(Component componente:filas) {
			VFilaPedidoCliente fila=(VFilaPedidoCliente) componente;
			if(fila.gettCod().getText().equals("0"))
				return true;
		}
		return false;
	}
	
	/**
	 * Al pulsar el botón elimina la fila del pedido del cliente
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaPedido.getbBorrar()) {
			vFilaPedido.getvPedido().getcFecha().requestFocus();
			vFilaPedido.getvPedido().getPanel().remove(vFilaPedido);
			vFilaPedido.getvPedido().updateUI();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		u.controlaTeclas(e);
	}

}
