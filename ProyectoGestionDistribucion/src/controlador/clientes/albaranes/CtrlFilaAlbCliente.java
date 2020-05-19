package controlador.clientes.albaranes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JTextField;
import model.Articulo;
import modelo.negocio.GestorArticulo;
import vista.clientes.albaranes.VFilaAlbaranCliente;

public class CtrlFilaAlbCliente implements FocusListener, ActionListener{
	private VFilaAlbaranCliente vFilaAlb;
	private NumberFormat formatoeuro;
	
	public CtrlFilaAlbCliente(VFilaAlbaranCliente vFilaAlb) {
		this.vFilaAlb=vFilaAlb;
		formatoeuro = NumberFormat.getCurrencyInstance();
	}
	

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent()) {
			if (vFilaAlb.getArticulo().getSelectedItem()==null) {

			}
			
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent())
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}

	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaAlb.updateUI();
		if (e.getSource()==vFilaAlb.getArticulo().getEditor().getEditorComponent()) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.getFila().setArticuloBean(art);
			vFilaAlb.updateUI();
			vFilaAlb.gettCoste().setText(formatoeuro.format(art.getCoste())); 
			vFilaAlb.gettCod().setText(String.valueOf(art.getCod()));
			vFilaAlb.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
			return;
		}
		
		if (e.getSource()==vFilaAlb.gettCod()) {
			vFilaAlb.updateUI();
			if (!vFilaAlb.gettCod().getText().equals("0"))
				vFilaAlb.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaAlb.gettCod().getText())));
			vFilaAlb.gettCod().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaAlb.gettCajas()) {
			vFilaAlb.updateUI();
			art=(Articulo) vFilaAlb.getArticulo().getSelectedItem();
			vFilaAlb.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaAlb.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaAlb.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaAlb.gettUnidades().getText())*euroADoble(vFilaAlb.gettCoste().getText())));
			vFilaAlb.gettCajas().setBackground(Color.WHITE);
			vFilaAlb.getvAlbaran().actualizaTotal();
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
		if (e.getSource()==vFilaAlb.getbBorrar()) {
			vFilaAlb.getvAlbaran().getcFecha().requestFocus();
			vFilaAlb.getvAlbaran().getPanel().remove(vFilaAlb);
			vFilaAlb.getvAlbaran().actualizaTotal();
			vFilaAlb.getvAlbaran().updateUI();
		}
		
	}

}
