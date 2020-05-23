package controlador.proveedores.facturas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JTextField;

import entidades.Articulo;
import modelo.negocio.GestorArticulo;
import vista.proveedores.facturas.VFilaFacturaProveedor;

public class CtrlFilaFactProve implements FocusListener, ActionListener{
	private VFilaFacturaProveedor vFilaFact;
	private NumberFormat formatoeuro;
	
	public CtrlFilaFactProve(VFilaFacturaProveedor vFilaFact) {
		this.vFilaFact=vFilaFact;
		formatoeuro = NumberFormat.getCurrencyInstance();
	}
	

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaFact.gettCoste()) {
			vFilaFact.gettCoste().setText(focoEuro(vFilaFact.gettCoste().getText()));
		}
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent())
			vFilaFact.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}

	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaFact.updateUI();
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent()) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.getFila().setArticuloBean(art);
			vFilaFact.updateUI();
			vFilaFact.gettCoste().setText(formatoeuro.format(art.getCoste())); 
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
		if (e.getSource()==vFilaFact.gettCajas()) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaFact.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaFact.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaFact.gettUnidades().getText())*euroADoble(vFilaFact.gettCoste().getText())));
			vFilaFact.gettCajas().setBackground(Color.WHITE);
			vFilaFact.getvFactura().actualizaTotal();
			return;
		}
		if (e.getSource()==vFilaFact.gettCoste()) {
			vFilaFact.gettCoste().setText(noFocoEuro(vFilaFact.gettCoste().getText()));
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
		if (e.getSource()==vFilaFact.getbBorrar()) {
			vFilaFact.getvFactura().getcFecha().requestFocus();
			vFilaFact.getvFactura().getPanel().remove(vFilaFact);
			vFilaFact.getvFactura().actualizaTotal();
			vFilaFact.getvFactura().updateUI();
		}
		
	}

}
