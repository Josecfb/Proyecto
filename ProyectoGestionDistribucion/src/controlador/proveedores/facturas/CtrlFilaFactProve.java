package controlador.proveedores.facturas;

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
import modelo.negocio.GestorArticulo;
import util.Utilidades;
import vista.proveedores.facturas.VFilaFacturaProveedor;
/**
 * Controla la vista de la fila de factura de proveedor
 * @author Jose Carlos
 *
 */
public class CtrlFilaFactProve implements FocusListener, ActionListener, KeyListener{
	private VFilaFacturaProveedor vFilaFact;
	private NumberFormat formatoeuro;
	private Utilidades u;
	/**
	 * Constructor
	 * @param vFilaFact vista de la fila de factura de proveedor VFilaFacturaProveedor
	 */
	public CtrlFilaFactProve(VFilaFacturaProveedor vFilaFact) {
		this.vFilaFact=vFilaFact;
		formatoeuro = NumberFormat.getCurrencyInstance();
		u=new Utilidades();
	}
	
	/**
	 * Al recibir foco los campos cambian de color de fondo
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaFact.gettCoste()) {
			vFilaFact.gettCoste().setText(u.focoEuro(vFilaFact.gettCoste().getText()));
		}
		u.foco(e);
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent())
			vFilaFact.getArticulo().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}
	/** 
	 * Cuando el combo de articulo pierde foco rellena los campos precio y codigo de artículo
	 * cuando el codigo de articulo pierde foco rellena el combo de artículo  
	 * cuando el campo cajas pierde foco calcula las unidades y el total
	 */
	@Override
	public void focusLost(FocusEvent e) throws NullPointerException, NumberFormatException {

		Articulo art=null;
		vFilaFact.updateUI();
		if (e.getSource()==vFilaFact.getArticulo().getEditor().getEditorComponent() && vFilaFact.getArticulo().getSelectedItem()!=null ) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.getFila().setArticuloBean(art);
			vFilaFact.updateUI();
			vFilaFact.gettCoste().setText(formatoeuro.format(art.getCoste())); 
			vFilaFact.gettCod().setText(String.valueOf(art.getCod()));
			vFilaFact.getArticulo().getEditor().getEditorComponent().setBackground(Color.WHITE);
		}
		
		if (e.getSource()==vFilaFact.gettCod()) {
			vFilaFact.updateUI();
			if (!vFilaFact.gettCod().getText().equals("0"))
				vFilaFact.getArticulo().setSelectedItem(new GestorArticulo().existe(Integer.parseInt(vFilaFact.gettCod().getText())));
		}
		if (e.getSource()==vFilaFact.gettCajas() && !vFilaFact.gettCajas().getText().equals("0")) {
			vFilaFact.updateUI();
			art=(Articulo) vFilaFact.getArticulo().getSelectedItem();
			vFilaFact.gettUnidades().setText(String.valueOf(Integer.parseInt(vFilaFact.gettCajas().getText())*art.getUnidadesCaja()));
			vFilaFact.gettTotal().setText(formatoeuro.format(Integer.parseInt(vFilaFact.gettUnidades().getText())*u.euroADoble(vFilaFact.gettCoste().getText())));
			vFilaFact.getvFactura().actualizaTotal();
			if (!vFilaFact.gettCod().getText().equals("0") && !vFilaFact.gettCajas().getText().equals("0") && !hayFilasVacias())
				vFilaFact.getvFactura().nuevaFila();
		}
		u.nofoco(e);
	}
	
	public boolean hayFilasVacias() {
		Component[] filas=vFilaFact.getvFactura().getPanel().getComponents();
		for(Component componente:filas) {
			VFilaFacturaProveedor fila=(VFilaFacturaProveedor) componente;
			if(fila.gettCod().getText().equals("0"))
				return true;
		}
		return false;
	}
	/**
	 * Borra una fila de la factura
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaFact.getbBorrar()) {
			vFilaFact.getvFactura().getcFecha().requestFocus();
			vFilaFact.getvFactura().getPanel().remove(vFilaFact);
			vFilaFact.getvFactura().actualizaTotal();
			vFilaFact.getvFactura().updateUI();
			vFilaFact.getvFactura().setModificado(true);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		u.controlaTeclas(e);
		vFilaFact.getvFactura().setModificado(true);
	}

}
