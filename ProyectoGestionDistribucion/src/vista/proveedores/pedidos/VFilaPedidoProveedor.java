package vista.proveedores.pedidos;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.proveedores.pedidos.ControladorFilaPedidoProveedor;
import entidades.Articulo;
import entidades.FilaPedidoProveedor;
import entidades.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import util.JTextFieldN;
import util.Utilidades;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Vista de las filas de la ventana de pedidos de proveedor
 * @author Jose Carlos
 *
 */
public class VFilaPedidoProveedor extends JPanel {
	private static final long serialVersionUID = -3446443914975183188L;
	private Utilidades u;
	private JTextFieldN tCod;
	private JComboBox<Articulo> articulo;
	private JTextField tUnidades;
	private JTextFieldN tCajas;
	private JTextFieldN tCoste;
	private JTextField tTotal;
	private JButton bBorrar;
	private PedidoProveedor ped;
	private VPedidoProveedor vPedido;
	private FilaPedidoProveedor fila;

	public VFilaPedidoProveedor(VPedidoProveedor vPedido,FilaPedidoProveedor fila) {
		this.fila=fila;
		this.vPedido=vPedido;
		u=new  Utilidades();
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
		asignaArticulosCombo();
		//articulo.setEditable(true);
		articulo.setSelectedItem(null);
		articulo.setBounds(67, 1, 363, 20);
		articulo.setAutoscrolls(true);
		AutoCompleteDecorator.decorate(articulo);
		add(articulo);
		
		tUnidades = new JTextField();
		tUnidades.setColumns(10);
		tUnidades.setBounds(482, 1, 32, 20);
		tUnidades.setHorizontalAlignment(JTextField.RIGHT);
		tUnidades.setText("0");
		tUnidades.setFocusable(false);
		add(tUnidades);
		
		tCajas = new JTextFieldN(3,'n');
		tCajas.setColumns(10);
		tCajas.setBounds(440, 1, 32, 20);
		tCajas.setHorizontalAlignment(JTextField.RIGHT);
		tCajas.setText("0");
		add(tCajas);
		
		tCoste = new JTextFieldN(5,'m');
		tCoste.setColumns(10);
		tCoste.setBounds(524, 1, 64, 20);
		tCoste.setHorizontalAlignment(JTextField.RIGHT);
		tCoste.setText("0,00 €");
		add(tCoste);
		
		tTotal = new JTextField();
		tTotal.setColumns(10);
		tTotal.setBounds(598, 1, 76, 20);
		tTotal.setHorizontalAlignment(JTextField.RIGHT);
		tTotal.setFocusable(false);
		tTotal.setText("0,00 €");
		add(tTotal);
		
		bBorrar = new JButton("");
		bBorrar.setBounds(681, 0, 20, 20);
		bBorrar.setIcon(new ImageIcon("src/img/borrarfila.png"));
		add(bBorrar);
		setVisible(true);
	}

	private void asignaArticulosCombo() {
		
		List<Articulo> articulos=new GestorArticulo().deUnProveedor(vPedido.getPed().getProveedore());
			for (Articulo art:articulos)
				articulo.addItem(art);
	}
	
	public void establecerControlador(ControladorFilaPedidoProveedor controla) {
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

	public JTextField gettCajas() {
		return tCajas;
	}

	public JTextField gettCoste() {
		return tCoste;
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

	public VPedidoProveedor getvPedido() {
		return vPedido;
	}

	public FilaPedidoProveedor getFila() {
		return fila;
	}
	
	
}
