package vista.proveedores.albaranes;

import javax.swing.JPanel;

import controlador.proveedores.albaranes.CtrlFilaAlbaranesGenProveedor;
import entidades.AlbaranProveedor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class VFilaAlbaranGeneradoProveedor extends JPanel {

	private static final long serialVersionUID = 4665261804339480581L;
	private JLabel lProveedor, lFecha,lNum;
	private JButton bEditar;
	private AlbaranProveedor alb;
	private VAlbaranesProveedores vAlbaranes;


	public VFilaAlbaranGeneradoProveedor(AlbaranProveedor alb,VAlbaranesProveedores vAlbaranes) {
		setBackground(Color.WHITE);
		this.vAlbaranes=vAlbaranes;
		this.alb=alb;
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
	
	public void establecerControlador(CtrlFilaAlbaranesGenProveedor controlador) {
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

	public AlbaranProveedor getAlb() {
		return alb;
	}

	public VAlbaranesProveedores getVAlbaranes() {
		return vAlbaranes;
	}

	
	
}
