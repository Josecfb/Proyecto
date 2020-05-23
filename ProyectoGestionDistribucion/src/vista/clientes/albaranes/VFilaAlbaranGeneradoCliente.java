package vista.clientes.albaranes;

import javax.swing.JPanel;

import controlador.clientes.albaranes.CtrlFilaAlbaranesGenCliente;
import entidades.AlbaranCliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class VFilaAlbaranGeneradoCliente extends JPanel {

	private static final long serialVersionUID = 4665261804339480581L;
	private JLabel lCliente, lFecha,lNum;
	private JButton bEditar;
	private AlbaranCliente alb;
	private VAlbaranesClientes vAlbaranes;


	public VFilaAlbaranGeneradoCliente(AlbaranCliente alb,VAlbaranesClientes vAlbaranes) {
		setBackground(Color.WHITE);
		this.vAlbaranes=vAlbaranes;
		this.alb=alb;
		setLayout(null);
		
		lCliente = new JLabel("");
		lCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCliente.setBounds(68, 5, 406, 20);
		add(lCliente);
		
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
	
	public void establecerControlador(CtrlFilaAlbaranesGenCliente controlador) {
		bEditar.addActionListener(controlador);
	}

	public JLabel getlProveedor() {
		return lCliente;
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

	public AlbaranCliente getAlb() {
		return alb;
	}

	public VAlbaranesClientes getVAlbaranes() {
		return vAlbaranes;
	}

	
	
}
