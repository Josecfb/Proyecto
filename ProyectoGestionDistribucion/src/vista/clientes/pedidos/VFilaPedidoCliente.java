package vista.clientes.pedidos;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.clientes.pedidos.ControladorFilaPedidoCliente;
import entidades.Articulo;
import entidades.FilasPedidosCliente;
import entidades.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import util.JTextFieldN;
import util.Utilidades;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Vista de la fila de la ventana de pedidos de cliente
 * @author Jose Carlos
 *
 */
public class VFilaPedidoCliente extends JPanel {
	private static final long serialVersionUID = -3446443914975183188L;
	private Utilidades u;
	private JTextFieldN tCod;
	private JComboBox<Articulo> articulo;
	private JTextFieldN tUnidades;
	private JTextFieldN tPrecio;
	private JTextField tTotal;
	private JButton bBorrar;
	private PedidoProveedor ped;
	private VPedidoCliente vPedido;
	private FilasPedidosCliente fila;
	/**
	 * El constructor recibe la ventana de pedido de cliente y el objeto FilasPedidoCliente
	 * @param vPedido ventana de pedido de cliente VPedidoCliente
	 * @param fila objeto FilasPedidoCliente
	 */
	public VFilaPedidoCliente(VPedidoCliente vPedido,FilasPedidosCliente fila) {
		this.fila=fila;
		this.vPedido=vPedido;
		u=new Utilidades();
		setBackground(SystemColor.control);
		setLayout(null);
		
		tCod = new JTextFieldN(4,'n');
		tCod.setBounds(10, 1, 47, 20);
		tCod.setHorizontalAlignment(JTextField.RIGHT);
		tCod.setText("0");
		add(tCod);
		tCod.setColumns(10);
		articulo = new JComboBox<Articulo>();
		articulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		articulo.setEditable(true);
		articulo.setSelectedItem(null);
		articulo.setBounds(67, 1, 363, 20);
		articulo.setAutoscrolls(true);
		GestorArticulo ga=new GestorArticulo();
		List<Articulo> artis=ga.listar("");
		for (Articulo arti:artis)
			articulo.addItem(arti);
		AutoCompleteDecorator.decorate(articulo);
		articulo.setSelectedItem(null);
		add(articulo);
		
		tUnidades = new JTextFieldN(3,'n');
		tUnidades.setColumns(10);
		tUnidades.setBounds(440, 1, 32, 20);
		tUnidades.setHorizontalAlignment(JTextField.RIGHT);
		tUnidades.setText("0");
		add(tUnidades);
		
		tPrecio = new JTextFieldN(5,'m');
		tPrecio.setColumns(10);
		tPrecio.setBounds(482, 1, 64, 20);
		tPrecio.setHorizontalAlignment(JTextField.RIGHT);
		tPrecio.setText("0,00 €");
		add(tPrecio);
		
		tTotal = new JTextField();
		tTotal.setColumns(10);
		tTotal.setBounds(556, 1, 76, 20);
		tTotal.setHorizontalAlignment(JTextField.RIGHT);
		tTotal.setFocusable(false);
		tTotal.setText("0,00 €");
		add(tTotal);
		
		bBorrar = new JButton("");
		bBorrar.setBounds(639, 0, 20, 20);
		bBorrar.setIcon(new ImageIcon("src/img/borrarfila.png"));
		add(bBorrar);
		setVisible(true);
	}
	/**
	 * Establece en controlador para la vista de fila de pedido de cliente
	 * @param controla ControladorFilaPedidoCliente
	 */
	public void establecerControlador(ControladorFilaPedidoCliente controla) {
		u.addFocusKey(this, controla, controla);
		articulo.getEditor().getEditorComponent().addFocusListener(controla);
		bBorrar.addActionListener(controla);
	}

	public JTextField gettCod() {
		return tCod;
	}



	public JComboBox<Articulo> getArticulo() {
		return articulo;
	}

	public JTextField gettUnidades() {
		return tUnidades;
	}


	public JTextField gettPrecio() {
		return tPrecio;
	}

	public JTextField gettTotal() {
		return tTotal;
	}

	public PedidoProveedor getPed() {
		return ped;
	}

	public JButton getbBorrar() {
		return bBorrar;
	}

	public VPedidoCliente getvPedido() {
		return vPedido;
	}

	public FilasPedidosCliente getFila() {
		return fila;
	}
	
	
}
