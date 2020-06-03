package vista.proveedores.facturas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
/**
 * Vista de la fila con el albaran de la ventana del asistente para generar facturas de proveedor
 * @author Jose Carlos
 *
 */
public class VFilaAlbGeneraFactProve extends JPanel {

	private static final long serialVersionUID = -1711496554895544388L;
	private JLabel lNum,lFecha;
	private JCheckBox checMarca;
	/**
	 * Constructor vacío
	 */
	public VFilaAlbGeneraFactProve() {
		setLayout(null);
		
		lNum = new JLabel("New label");
		lNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNum.setBounds(20, 10, 65, 20);
		add(lNum);
		
		lFecha = new JLabel("New label");
		lFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFecha.setBounds(116, 10, 146, 20);
		add(lFecha);
		
		checMarca = new JCheckBox("");
		checMarca.setBounds(359, 10, 28, 23);
		add(checMarca);

	}

	public JLabel getlNum() {
		return lNum;
	}

	public JLabel getlFecha() {
		return lFecha;
	}

	public JCheckBox getChecMarca() {
		return checMarca;
	}
	
	
	
}
