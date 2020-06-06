package vista.clientes.albaranes;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.clientes.albaranes.CtrlFilaAlbCliente;
import entidades.Articulo;
import entidades.FilasAlbaranCliente;
import entidades.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import util.JTextFieldN;
import util.Utilidades;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Vista de fila de albaran d ecliente
 * @author Jose Carlos
 *
 */
public class VFilaAlbaranCliente extends JPanel {

	private static final long serialVersionUID = -3446443914975183188L;
	Utilidades u;
	private JTextFieldN tCod;
	private JComboBox<Articulo> articulo;
	private JTextFieldN tUnidades;
	private JTextFieldN tPrecio;
	private JTextField tTotal;
	private JButton bBorrar;
	private PedidoProveedor ped;
	private VAlbaranCliente vAlbaran;
	private FilasAlbaranCliente fila;
	/**
	 * El constructor recibe la ventana de albarán de cliente y el objeto FilasAlbaranCliente
	 * @param vAlbaran Ventana de albarán de cliente
	 * @param fila FilasAlbaranCliente
	 */
	public VFilaAlbaranCliente(VAlbaranCliente vAlbaran,FilasAlbaranCliente fila) {
		this.fila=fila;
		this.vAlbaran=vAlbaran;
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
		asignaArticulosCombo();
		articulo.setEditable(true);
		articulo.setSelectedItem(null);
		articulo.setBounds(67, 1, 373, 20);
		articulo.setAutoscrolls(true);
		AutoCompleteDecorator.decorate(articulo);
		add(articulo);
		
		tUnidades = new JTextFieldN(3,'n');
		tUnidades.setColumns(10);
		tUnidades.setBounds(450, 1, 56, 20);
		tUnidades.setHorizontalAlignment(JTextField.RIGHT);
		tUnidades.setText("0");
		add(tUnidades);
		
		tPrecio = new JTextFieldN(5,'m');
		tPrecio.setColumns(10);
		tPrecio.setBounds(513, 1, 70, 20);
		tPrecio.setHorizontalAlignment(JTextField.RIGHT);
		tPrecio.setText("0,00 €");
		add(tPrecio);
		
		tTotal = new JTextField();
		tTotal.setColumns(10);
		tTotal.setBounds(593, 1, 70, 20);
		tTotal.setHorizontalAlignment(JTextField.RIGHT);
		tTotal.setFocusable(false);
		tTotal.setText("0,00 €");
		add(tTotal);
		
		bBorrar = new JButton("");
		bBorrar.setBounds(673, 1, 20, 20);
		bBorrar.setIcon(new ImageIcon("src/img/borrarfila.png"));
		add(bBorrar);
		setVisible(true);
	}
	/**
	 * Asigna los item al comboBox de artículo
	 */
	private void asignaArticulosCombo() {
		
		List<Articulo> articulos=new GestorArticulo().listar("");
			for (Articulo art:articulos)
				articulo.addItem(art);
	}
	/**
	 * Establece el controlador para la fila de albarán de cliente
	 * @param controla CtrlFilaAlbCliente
	 */
	public void establecerControlador(CtrlFilaAlbCliente controla) {
		u.addFocusKey(this,controla,controla);
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

	public VAlbaranCliente getvAlbaran() {
		return vAlbaran;
	}

	public FilasAlbaranCliente getFila() {
		return fila;
	}
	
	
}
