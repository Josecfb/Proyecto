package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Diversos métodos que son útiles en otras clases
 * @author Jose Carlos
 *
 */
public class Utilidades {

	/**
	 * Recibe una cadena en formato euro y retorna el valor double
	 * @param cad cadena en formato euro
	 * @return double
	 */
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	
	/**
	 * Recibe una cadena con signo € y la retorna sin el signo €
	 * @param cad con signo €
	 * @return cadena sin signo €
	 */
	public String focoEuro(String cad) {
		return cad.split(" ")[0];
	}

	/**
	 * Recibe una cadena sin signo € y le pone el signo €
	 * @param cad sin signo €
	 * @return con signo €
	 */
	public String noFocoEuro(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+" €";
	}
	
	public String noFocoPorcentaje(String cad) {
		if (cad.contains(","))
				return cad+'%';
		else 
			return cad+",00%";
	}
	
	/**
	 * Recibe una cadena sin signo % y le pone el signo %
	 * @param cad sin signo %
	 * @return con signo %
	 */
	public Double porcentajeADoble(String cad) {
		if (cad.contains(","))
			return Double.valueOf(cad.split("%")[0].split(",")[0]+"."+cad.split("%")[0].split(",")[1])/100;
		else
			return Double.valueOf(cad.split("%")[0])/100;
	}
	/**
	 * Quita el signo de %
	 * @param cad cadena co signo %
	 * @return cadena sin %
	 */
	public String focoPorcentaje(String cad) {
		return cad.split("%")[0];
	}
	
	@SuppressWarnings("rawtypes")
	public void addFocusKey(JPanel panel,FocusListener fControlador,KeyListener kControlador) {
		Component[] componentes=panel.getComponents();
		for (Component componente:componentes) {
			if (componente.getClass()==JTextField.class || componente.getClass()==JTextFieldN.class || componente.getClass()==JTextFieldT.class) {
				componente.addFocusListener(fControlador);
				componente.addKeyListener(kControlador);
			}
			if (componente.getClass()==JComboBox.class) 
				((JComboBox) componente).getEditor().getEditorComponent().addFocusListener(fControlador);
		}
	}
	public void controlaTeclas(KeyEvent e) {
		if (e.getSource().getClass()==JTextFieldT.class) {
			JTextFieldT campoT=(JTextFieldT) e.getSource();
			if (e.getSource()==campoT) 
				if (campoT.noCumple())
					e.consume();
		}
			
		if (e.getSource().getClass()==JTextFieldN.class) {
			JTextFieldN campoN=(JTextFieldN) e.getSource();
			if (e.getSource()==campoN) 
				if (campoN.noCumple(e))
					e.consume();
		}
	}
	public void foco(FocusEvent e) {
		if (e.getSource().getClass()==JTextFieldN.class) {
			JTextFieldN campo=(JTextFieldN) e.getSource();
			if (campo.getTipo()=='m')
				campo.setText(focoEuro(campo.getText()));
			if (campo.getTipo()=='p')
				campo.setText(focoPorcentaje(campo.getText()));
		}
		if (e.getSource().getClass()==JTextField.class || e.getSource().getClass()==JTextFieldN.class || e.getSource().getClass()==JTextFieldT.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}
		
		
	}
	public void nofoco(FocusEvent e) {
		if (e.getSource().getClass()==JTextField.class || e.getSource().getClass()==JTextFieldN.class || e.getSource().getClass()==JTextFieldT.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
		if (e.getSource().getClass()==JTextFieldN.class) {
			JTextFieldN campo=(JTextFieldN) e.getSource();
			if (campo.getTipo()=='m')
				campo.setText(noFocoEuro(campo.getText()));
			if (campo.getTipo()=='p')
				campo.setText(noFocoPorcentaje(campo.getText()));
		}
	}
}
