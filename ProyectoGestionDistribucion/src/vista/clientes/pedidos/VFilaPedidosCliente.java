package vista.clientes.pedidos;

import javax.swing.JPanel;

import controlador.clientes.pedidos.ControladorFilaPedidoPendienteCliente;
import entidades.PedidoCliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
/**
 * Vista de la fila de pedido de la ventana listado de pedidos de clientes
 * @author Jose Carlos
 *
 */
public class VFilaPedidosCliente extends JPanel {

	private static final long serialVersionUID = 4665261804339480581L;
	private JLabel lCliente, lFecha, lNum;
	private JButton bEditar;
	private PedidoCliente ped;
	private VPedidosClientes vpedidos;
	/**
	 * El constructor recibe el objeto PedidoCliente y la ventana de listado de pedidos de cliente VPedidosClientes
	 * @param ped PedidoCliente
	 * @param vpedidos ventana de listado de pedidos de cliente VPedidosClientes
	 */
	public VFilaPedidosCliente(PedidoCliente ped,VPedidosClientes vpedidos) {
		setBackground(Color.WHITE);
		this.vpedidos=vpedidos;
		this.ped=ped;
		setLayout(null);
		
		lCliente = new JLabel("");
		lCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCliente.setBounds(74, 5, 400, 20);
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
		lNum.setBounds(10, 5, 54, 20);
		add(lNum);
	}
	/**
	 * Establece el controlador para la fila de la ventana listado de pedidos de clientes
	 * @param controlador ControladorFilaPedidoPendienteCliente
	 */
	public void establecerControlador(ControladorFilaPedidoPendienteCliente controlador) {
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

	public PedidoCliente getPed() {
		return ped;
	}

	public VPedidosClientes getVpedidos() {
		return vpedidos;
	}
}
