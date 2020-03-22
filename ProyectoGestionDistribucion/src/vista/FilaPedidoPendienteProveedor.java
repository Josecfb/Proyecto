package vista;

import javax.swing.JPanel;

import controlador.fichas.ControladorFilaPedidoPendienteProveedor;
import model.Proveedor;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FilaPedidoPendienteProveedor extends JPanel {
	private JLabel lProveedor, lTotal;
	private JButton bEditar;
	private Proveedor pro;
	private VentanaPrincipal v;

	public FilaPedidoPendienteProveedor(Proveedor pro,VentanaPrincipal v) {
		this.v=v;
		this.pro=pro;
		setLayout(null);
		
		lProveedor = new JLabel("");
		lProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProveedor.setBounds(24, 5, 400, 20);
		add(lProveedor);
		
		bEditar = new JButton("");
		bEditar.setBounds(600, 5, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
		
		lTotal = new JLabel("");
		lTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTotal.setBounds(484, 5, 101, 20);
		add(lTotal);
	}
	
	public void establecerControlador(ControladorFilaPedidoPendienteProveedor controlador) {
		bEditar.addActionListener(controlador);
	}

	public JLabel getlProveedor() {
		return lProveedor;
	}

	public JLabel getlTotal() {
		return lTotal;
	}

	public JButton getbEditar() {
		return bEditar;
	}

	public Proveedor getPro() {
		return pro;
	}

	public VentanaPrincipal getV() {
		return v;
	}
	
	
}
