package controlador.clientes.facturas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JTextField;
import entidades.Articulo;
import entidades.PrecioCliente;
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.clientes.facturas.VFilaFacturaCliente;
/**
 * Controla la vista de Fila de Factura de cliente
 * @author Jose Carlos
 *
 */
public class CtrlFilaFactCliente implements FocusListener, ActionListener{
	private Utilidades u;
	private VFilaFacturaCliente vFilaFact;
	private NumberFormat formatoeuro;
	/**
	 * El constructor recibe la vista de fila de factura de cliente
	 * @param vFilaFact vista de fila de factura de cliente VFilaFacturaCliente
	 */
	public CtrlFilaFactCliente(VFilaFacturaCliente vFilaFact) {
		this.vFilaFact=vFilaFact;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Cuando los campos ganan foco cambian de color de fondo y de formaro en caso de tener el signo €
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaFact.getTPrecio()) {
			vFilaFact.getTPrecio().setText(u.focoEuro(vFilaFact.getTPrecio().getText()));
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent())
			vFilaFact.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/**
	 * Cuando el campo articulo pierde foco asigna valor al precio con el valor correspondiente 
	 * en función de si el cliente tiene precio especial en dicho artículo o
	 * si el cliente es mayorista o minorista y  al código del artículo
	 * cuando el campo código pierede foco asigna valor al combobox artículo
	 * cuando las unidades pierden foco calcula el total y actualiza el total general
	 */
	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaFact.updateUI();
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent()) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.getFila().setArticuloBean(art);
			vFilaFact.updateUI();
			PrecioCliente precli=new PrecioCliente(vFilaFact.getvFactura().getFact().getCliente(), art);
			if(vFilaFact.getvFactura().getFact().getCliente().getPreciosClientes().contains(precli))
				vFilaFact.getTPrecio().setText(formatoeuro.format(vFilaFact.getvFactura().getFact().getCliente().getPreciosClientes().get(vFilaFact.getvFactura().getFact().getCliente().getPreciosClientes().indexOf(precli)).getPrecio()));
			else 
				if(vFilaFact.getvFactura().getFact().getCliente().getTipo()==1)
					vFilaFact.getTPrecio().setText(formatoeuro.format(art.getPrecioMayorista()));
				else
					vFilaFact.getTPrecio().setText(formatoeuro.format(art.getPrecioMinorista()));
			vFilaFact.gettCod().setText(String.valueOf(art.getCod()));
			vFilaFact.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
			return;
		}
		
		if (e.getSource()==vFilaFact.gettCod()) {
			vFilaFact.updateUI();
			if (!vFilaFact.gettCod().getText().equals("0"))
				vFilaFact.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaFact.gettCod().getText())));
			vFilaFact.gettCod().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaFact.gettUnidades()) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaFact.gettUnidades().getText())*u.euroADoble(vFilaFact.getTPrecio().getText())));
			vFilaFact.gettUnidades().setBackground(Color.WHITE);
			vFilaFact.getvFactura().actualizaTotal();
			return;
		}
		if (e.getSource()==vFilaFact.getTPrecio()) {
			vFilaFact.getTPrecio().setText(u.noFocoEuro(vFilaFact.getTPrecio().getText()));
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
	}
	
	/**
	 * Cuando se pulsa el botón de borrar fila elimina dicha fila de la factura
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaFact.getbBorrar()) {
			vFilaFact.getvFactura().getcFecha().requestFocus();
			vFilaFact.getvFactura().getPanel().remove(vFilaFact);
			vFilaFact.getvFactura().actualizaTotal();
			vFilaFact.getvFactura().updateUI();
		}
	}
}
