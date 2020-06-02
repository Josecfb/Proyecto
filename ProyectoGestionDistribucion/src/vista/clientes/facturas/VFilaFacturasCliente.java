package vista.clientes.facturas;

import javax.swing.JPanel;
import controlador.clientes.facturas.CtrlFilaFacturasGenCliente;
import entidades.FacturaCliente;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
/**
 * 
 * @author Jose Carlos
 *
 */
public class VFilaFacturasCliente extends JPanel {

	private static final long serialVersionUID = 4665261804339480581L;
	private JLabel lProveedor, lFecha,lNum;
	private JButton bEditar;
	private FacturaCliente fact;
	private VFacturasClientes vFacturas;



	public VFilaFacturasCliente(FacturaCliente fact,VFacturasClientes vFacturas) {
		setBackground(Color.WHITE);
		this.vFacturas=vFacturas;
		this.fact=fact;
		setLayout(null);
		
		lProveedor = new JLabel("");
		lProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProveedor.setBounds(68, 5, 406, 20);
		add(lProveedor);
		
		bEditar = new JButton("");
		bEditar.setBounds(600, 5, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
		
		lFecha = new JLabel("");
		lFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFecha.setBounds(484, 5, 101, 20);
		add(lFecha);
		
		lNum = new JLabel("");
		lNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNum.setBounds(10, 5, 48, 20);
		add(lNum);
	}
	
	public void establecerControlador(CtrlFilaFacturasGenCliente controlador) {
		bEditar.addActionListener(controlador);
	}

	public JLabel getlProveedor() {
		return lProveedor;
	}

	public JLabel getlFecha() {
		return lFecha;
	}
	
	public JLabel getlNum() {
		return lNum;
	}

	public JButton getbEditar() {
		return bEditar;
	}

	public FacturaCliente getFact() {
		return fact;
	}

	public VFacturasClientes getVFacturas() {
		return vFacturas;
	}

	
	
}
