package vista.proveedores.albaranes;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.proveedores.albaranes.CtrlFilaAlbProve;
import entidades.Articulo;
import entidades.FilaAlbaranProveedor;
import entidades.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import util.JTextFieldN;
import util.Utilidades;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Vista de fila de la ventana albarán de proveedor
 * @author Jose Carlos
 *
 */
public class VFilaAlbaranProveedor extends JPanel {
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
	private VAlbaranProveedor vAlbaran;
	private FilaAlbaranProveedor fila;
	/**
	 * El constructor recibe el objeto entidad FilaAlbaranProveedor y la ventana de albarán de proveedor VAlbaranProveedor
	 * @param vAlbaran ventana de albarán de proveedor VAlbaranProveedor
	 * @param fila objeto entidad FilaAlbaranProveedor
	 */
	public VFilaAlbaranProveedor(VAlbaranProveedor vAlbaran,FilaAlbaranProveedor fila) {
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
		
		List<Articulo> articulos=new GestorArticulo().deUnProveedor(vAlbaran.getAlb().getProveedore());
			for (Articulo art:articulos)
				articulo.addItem(art);
	}
	/**
	 * Establece el controlador para la fila de la ventana de albar´n de proveedor
	 * @param controla CtrlFilaAlbProve
	 */
	public void establecerControlador(CtrlFilaAlbProve controla) {
		u.addFocusKey(this, controla, controla);
		articulo.getEditor().getEditorComponent().addFocusListener(controla);
		articulo.addItemListener(controla);
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

	public VAlbaranProveedor getvAlbaran() {
		return vAlbaran;
	}

	public FilaAlbaranProveedor getFila() {
		return fila;
	}
	
	
}
