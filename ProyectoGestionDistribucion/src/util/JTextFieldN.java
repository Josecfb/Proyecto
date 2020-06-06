package util;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JTextFieldN extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5685413502236641476L;
	private int largo;
	private char tipo;
	

	public JTextFieldN(int largo, char tipo) {
		this.largo = largo;
		this.tipo=tipo;
	}

	public boolean noCumple(KeyEvent e) {
		if(this.getText().length()==this.largo)
			return true;
		if (chNoEsNum(e.getKeyChar()))
			return true;
		if(this.getText().indexOf(e.getKeyChar())!=-1 && e.getKeyChar()==',') 
			return true;
		else 
			return false;
	}
	
	private boolean chNoEsNum(char letra) {
		if((int) letra>=(int) '0' && (int) letra<=(int) '9' || letra==',')
			return false;
		else 
			return true;
	}

	public char getTipo() {
		return tipo;
	}
	
}
