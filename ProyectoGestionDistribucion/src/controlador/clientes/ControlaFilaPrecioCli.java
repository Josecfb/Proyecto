package controlador.clientes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.JTextField;

import entidades.Articulo;
import util.Utilidades;
import vista.clientes.VFilaPrecioCliente;
/**
 * controla las filas con los precios de artículos especiales de un cliente
 * @author Jose Carlos
 *
 */
public class ControlaFilaPrecioCli implements FocusListener, ActionListener{
	private NumberFormat formatoeuro,formatoPorcentaje;
	private VFilaPrecioCliente vFilaPre;
	private Utilidades u;
	/**
	 * Al constructor se le pasa lavista de la fila de precio cliente
	 * @param vFilaPre Vista de las filas de precios de un cliente VFilaPrecioCliente
	 */
	public ControlaFilaPrecioCli(VFilaPrecioCliente vFilaPre) {
		this.vFilaPre=vFilaPre;
		u=new Utilidades();
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoPorcentaje = NumberFormat.getPercentInstance();
		formatoPorcentaje.setMinimumFractionDigits(2);
	}
	/**
	 * Al pulsar el botón borrar fila borra el precio de artículo especial del cliente
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaPre.getbBorrar()) {
			
			vFilaPre.getvFicha().getPanel().remove(vFilaPre);
			vFilaPre.getvFicha().updateUI();
		}
		
	}
	/**
	 * Realiza cambios en los campos cuando estos ganan foco
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaPre.gettPrecio())
			vFilaPre.gettPrecio().setText(u.focoEuro(vFilaPre.gettPrecio().getText()));
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPorcent().setText(u.focoPorcentaje(vFilaPre.gettPorcent().getText()));
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaPre.getComboArt().getEditor().getEditorComponent())
			vFilaPre.getComboArt().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/**
	 * Cuando se pierde foco del cuadro combinado de articulo se muestra su precio en un label dependiendo de 
	 * si el cliente es mayorista o minorista el precio será diferente
	 * Cuando el porcentaje pierde foco calcula el precio especial para el cliente
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource()==vFilaPre.gettPrecio()) 
			vFilaPre.gettPrecio().setText(u.noFocoEuro(vFilaPre.gettPrecio().getText()));
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPorcent().setText(vFilaPre.gettPorcent().getText()+"%");
		Articulo art=null;
		if (e.getSource()==vFilaPre.getComboArt().getEditor().getEditorComponent()) {
			art=(Articulo) vFilaPre.getComboArt().getSelectedItem();
			vFilaPre.updateUI();
			System.out.println(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista());
			if (vFilaPre.gettPrecio().getText().equals("0,00 €"))
				vFilaPre.gettPrecio().setText(formatoeuro.format(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista())); 
			vFilaPre.gettCodArt().setText(String.valueOf(art.getCod()));
			vFilaPre.getlPrecioReal().setText(formatoeuro.format(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista()));
			double porcen=(u.euroADoble(vFilaPre.getlPrecioReal().getText())-u.euroADoble(vFilaPre.gettPrecio().getText()))/u.euroADoble((vFilaPre.getlPrecioReal().getText()));
			System.out.println(porcen);
			vFilaPre.gettPorcent().setText(formatoPorcentaje.format(porcen));
			vFilaPre.getComboArt().getEditor().getEditorComponent().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPrecio().setText(formatoeuro.format(u.euroADoble(vFilaPre.getlPrecioReal().getText())*(1-u.porcentajeADoble(vFilaPre.gettPorcent().getText())/100)));
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
		
	}

}
