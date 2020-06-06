package util;

import javax.swing.JTextField;

public class JTextFieldT extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5699828852982298138L;
	private int largo;
	public JTextFieldT(int largo) {
		this.largo = largo;
	}
	
	public boolean noCumple() {
		if(this.getText().length()==this.largo)
			return true;
		else return false;
	}
	

}
