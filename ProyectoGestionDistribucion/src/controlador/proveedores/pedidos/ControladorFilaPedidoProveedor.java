package controlador.proveedores.pedidos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JTextField;
import model.Articulo;
import modelo.negocio.GestorArticulo;
import vista.proveedores.pedidos.VFilaPedidoProveedor;

public class ControladorFilaPedidoProveedor implements FocusListener, ActionListener{
	private VFilaPedidoProveedor vFilaPedido;
	private NumberFormat formatoeuro;
	
	public ControladorFilaPedidoProveedor(VFilaPedidoProveedor vFilaPedido) {
		this.vFilaPedido=vFilaPedido;
		formatoeuro = NumberFormat.getCurrencyInstance();
	}
	

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
			if (!vFilaPedido.gettCod().getText().equals("0"))
				vFilaPedido.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaPedido.gettCod().getText())));
			vFilaPedido.gettCod().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaPedido.gettCajas()) {
			vFilaPedido.updateUI();
			art=(Articulo) vFilaPedido.getArticulo().getSelectedItem();
			vFilaPedido.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaPedido.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaPedido.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaPedido.gettUnidades().getText())*euroADoble(vFilaPedido.gettCoste().getText())));
			vFilaPedido.gettCajas().setBackground(Color.WHITE);
			vFilaPedido.getvPedido().actualizaTotal();
			return;
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
	}
	
	
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	
	public String focoEuro(String cad) {
		return cad.split(" ")[0];
	}
	
	public String noFocoEuro(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+" €";
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaPedido.getbBorrar()) {
			vFilaPedido.getvPedido().getcFecha().requestFocus();
			vFilaPedido.getvPedido().getPanel().remove(vFilaPedido);
			vFilaPedido.getvPedido().updateUI();
		}
		
	}

}
