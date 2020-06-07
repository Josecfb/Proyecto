package controlador.clientes.albaranes;

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
import vista.clientes.albaranes.VFilaAlbaranCliente;
/**
 * Controla los eventos en la fila de albarán de cliente
 * @author Jose Carlos
 *
 */
public class CtrlFilaAlbCliente implements FocusListener, ActionListener, KeyListener{
	private VFilaAlbaranCliente vFilaAlb;
	private NumberFormat formatoeuro;
	private Utilidades u;
	/**
	 * Al constructor se le pasa la vista de fila de albaran
	 * @param vFilaAlb vista de fila de albaran VFilaAlbaranCliente
	 */
	public CtrlFilaAlbCliente(VFilaAlbaranCliente vFilaAlb) {
		this.vFilaAlb=vFilaAlb;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Cuando un campo recibe foco cambia de color y su contenido queda seleccionado
	 */
	@Override
	public void focusGained(FocusEvent e) {
		u.foco(e);
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent())
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/**
	 * Cuando el combobox de articulo pierde foco rellena el precio con el valor correspondiente en función de si el cliente tiene precio especial en dicho artículo o
	 * si el cliente es mayorista o minorista y el código de articulo
	 */
	@Override
	public void focusLost(FocusEvent e){
		Articulo art=null;
		vFilaAlb.updateUI();
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent() && vFilaAlb.getArticulo().getSelectedItem()!=null) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.getFila().setArticuloBean(art);
			vFilaAlb.updateUI();
			PrecioCliente precli=new PrecioCliente(vFilaAlb.getvAlbaran().getAlb().getClienteBean(), art);
			//System.out.println();
			if(vFilaAlb.getvAlbaran().getAlb().getClienteBean().getPreciosClientes().contains(precli))
				vFilaAlb.gettPrecio().setText(formatoeuro.format(vFilaAlb.getvAlbaran().getAlb().getClienteBean().getPreciosClientes().get(vFilaAlb.getvAlbaran().getAlb().getClienteBean().getPreciosClientes().indexOf(precli)).getPrecio()));
			else 
				if(vFilaAlb.getvAlbaran().getAlb().getClienteBean().getTipo()==1)
					vFilaAlb.gettPrecio().setText(formatoeuro.format(art.getPrecioMayorista()));
				else
					vFilaAlb.gettPrecio().setText(formatoeuro.format(art.getPrecioMinorista()));
			vFilaAlb.gettCod().setText(String.valueOf(art.getCod()));
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
			
		}
		
		if (e.getSource()==vFilaAlb.gettCod()) {
			vFilaAlb.updateUI();
			if (!vFilaAlb.gettCod().getText().equals("0"))
				vFilaAlb.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaAlb.gettCod().getText())));
			vFilaAlb.gettCod().setBackground(Color.WHITE);
			
		}
		/**
		 * Al perder foco los campos recuperan su color original y al salir de las unidades calcula los totales
		 */
		if (e.getSource()==vFilaAlb.gettUnidades()) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaAlb.gettUnidades().getText())*u.euroADoble(vFilaAlb.gettPrecio().getText())));
			vFilaAlb.gettUnidades().setBackground(Color.WHITE);
			vFilaAlb.getvAlbaran().actualizaTotal();
			if (!vFilaAlb.gettCod().getText().equals("0") && !vFilaAlb.gettUnidades().getText().equals("0") && !hayFilasVacias())
				vFilaAlb.getvAlbaran().nuevaFila();
		}
		u.nofoco(e);
	}
	
	public boolean hayFilasVacias() {
		Component[] filas=vFilaAlb.getvAlbaran().getPanel().getComponents();
		for(Component componente:filas) {
			VFilaAlbaranCliente fila=(VFilaAlbaranCliente) componente;
			if(fila.gettCod().getText().equals("0"))
				return true;
		}
		return false;
	}
	
	/**
	 * Cuando se pulsa el botón borrar elimina la fila del albaran
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaAlb.getbBorrar()) {
			vFilaAlb.getvAlbaran().getcFecha().requestFocus();
			vFilaAlb.getvAlbaran().getPanel().remove(vFilaAlb);
			vFilaAlb.getvAlbaran().actualizaTotal();
			vFilaAlb.getvAlbaran().updateUI();
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
	}

}
