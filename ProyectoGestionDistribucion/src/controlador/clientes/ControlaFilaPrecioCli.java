package controlador.clientes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.JTextField;

import model.Articulo;
import vista.clientes.VFilaPrecioCliente;

public class ControlaFilaPrecioCli implements FocusListener, ActionListener{
	private NumberFormat formatoeuro,formatoPorcentaje;
	private VFilaPrecioCliente vFilaPre;

	public ControlaFilaPrecioCli(VFilaPrecioCliente vFilaPre) {
		this.vFilaPre=vFilaPre;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoPorcentaje = NumberFormat.getPercentInstance();
		formatoPorcentaje.setMinimumFractionDigits(2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vFilaPre.getbBorrar()) {
			
			vFilaPre.getvFicha().getPanel().remove(vFilaPre);
			vFilaPre.getvFicha().updateUI();
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==vFilaPre.gettPrecio())
			vFilaPre.gettPrecio().setText(focoEuro(vFilaPre.gettPrecio().getText()));
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPorcent().setText(focoPorcent(vFilaPre.gettPorcent().getText()));
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		if (e.getSource()==vFilaPre.getComboArt().getEditor().getEditorComponent())
			vFilaPre.getComboArt().getEditor().getEditorComponent().setBackground(new Color(240,240,255));
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource()==vFilaPre.gettPrecio()) 
			vFilaPre.gettPrecio().setText(noFocoEuro(vFilaPre.gettPrecio().getText()));
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPorcent().setText(noFocoPorcent(vFilaPre.gettPorcent().getText()));
		Articulo art=null;
		if (e.getSource()==vFilaPre.getComboArt().getEditor().getEditorComponent()) {
			art=(Articulo) vFilaPre.getComboArt().getSelectedItem();
			vFilaPre.updateUI();
			System.out.println(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista());
			if (vFilaPre.gettPrecio().getText().equals("0,00 €"))
				vFilaPre.gettPrecio().setText(formatoeuro.format(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista())); 
			vFilaPre.gettCodArt().setText(String.valueOf(art.getCod()));
			vFilaPre.getlPrecioReal().setText(formatoeuro.format(vFilaPre.getCli().getTipo()==1?art.getPrecioMayorista():art.getPrecioMinorista()));
			double porcen=(euroADoble(vFilaPre.getlPrecioReal().getText())-euroADoble(vFilaPre.gettPrecio().getText()))/euroADoble((vFilaPre.getlPrecioReal().getText()));
			System.out.println(porcen);
			vFilaPre.gettPorcent().setText(formatoPorcentaje.format(porcen));
			vFilaPre.getComboArt().getEditor().getEditorComponent().setBackground(Color.WHITE);
			return;
		}
		if (e.getSource()==vFilaPre.gettPorcent())
			vFilaPre.gettPrecio().setText(formatoeuro.format(euroADoble(vFilaPre.getlPrecioReal().getText())*(1-porcentADoble(vFilaPre.gettPorcent().getText())/100)));
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
		
	}
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	public Double porcentADoble(String cad) {
		return Double.valueOf(cad.split("%")[0].split(",")[0]+"."+cad.split("%")[0].split(",")[1]);
	}
	public String focoEuro(String cad) {
		return cad.split(" ")[0];
	}
	public String focoPorcent(String cad) {
		return cad.split("%")[0];
	}
	
	public String noFocoEuro(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+" €";
	}
	public String noFocoPorcent(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+"%";
	}
}
