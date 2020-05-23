package vista.proveedores.pedidos;

import javax.swing.JPanel;

import controlador.proveedores.pedidos.ControladorFilaPedidoPendienteProveedor;
import entidades.PedidoProveedor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class VFilaPedidoPendienteProveedor extends JPanel {

	private static final long serialVersionUID = 4665261804339480581L;
	private JLabel lProveedor, lFecha, lNum;
	private JButton bEditar;
	private PedidoProveedor ped;
	private VPedidosProveedores vpedidos;

	public VFilaPedidoPendienteProveedor(PedidoProveedor ped,VPedidosProveedores vpedidos) {
		setBackground(Color.WHITE);
		this.vpedidos=vpedidos;
		this.ped=ped;
		setLayout(null);
		
		lProveedor = new JLabel("");
		lProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProveedor.setBounds(74, 5, 400, 20);
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
		lNum.setBounds(10, 5, 54, 20);
		add(lNum);
	}
	
	public void establecerControlador(ControladorFilaPedidoPendienteProveedor controlador) {
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

	public PedidoProveedor getPed() {
		return ped;
	}

	public VPedidosProveedores getVpedidos() {
		return vpedidos;
	}
}
