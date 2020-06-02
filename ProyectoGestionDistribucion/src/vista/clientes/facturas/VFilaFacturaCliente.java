package vista.clientes.facturas;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.clientes.facturas.CtrlFilaFactCliente;
import entidades.Articulo;
import entidades.FilaFacturaCliente;
import entidades.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import java.awt.Font;
import java.awt.Component;
import java.awt.SystemColor;
/**
 * Vista de la fila de la ventana d factura de cliente
 * @author Jose Carlos
 *
 */
public class VFilaFacturaCliente extends JPanel {

	private static final long serialVersionUID = -3446443914975183188L;
	private JTextField tCod;
	private JComboBox<Articulo> articulo;
	private JTextField tUnidades;
	private JTextField tPrecio;
	private JTextField tTotal;
	private JButton bBorrar;
	private PedidoProveedor ped;
	private VFacturaCliente vFactura;
	private FilaFacturaCliente fila;
	/**
	 * El constructor recibe la ventana de factura de cliente y el objeto FilaFacturaCliente
	 * @param vFactura ventana de factura de cliente
	 * @param fila objeto FilaFacturaCliente
	 */
	public VFilaFacturaCliente(VFacturaCliente vFactura,FilaFacturaCliente fila) {
		this.fila=fila;
		this.vFactura=vFactura;
		setBackground(SystemColor.control);
		setLayout(null);
		
		tCod = new JTextField();
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
		articulo.setBounds(67, 1, 382, 20);
		articulo.setAutoscrolls(true);
		AutoCompleteDecorator.decorate(articulo);
		add(articulo);
		
		tUnidades = new JTextField();
		tUnidades.setColumns(10);
		tUnidades.setBounds(459, 1, 55, 20);
		tUnidades.setHorizontalAlignment(JTextField.RIGHT);
		tUnidades.setText("0");
		add(tUnidades);
		
		tPrecio = new JTextField();
		tPrecio.setColumns(10);
		tPrecio.setBounds(524, 1, 64, 20);
		tPrecio.setHorizontalAlignment(JTextField.RIGHT);
		tPrecio.setText("0,00 €");
		add(tPrecio);
		
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
	/**
	 * asigna los items del combobox de articulo
	 */
	private void asignaArticulosCombo() {
		
		List<Articulo> articulos=new GestorArticulo().listar("");
			for (Articulo art:articulos)
				articulo.addItem(art);
	}
	/**
	 * Establece el controlador para la fila de la ventana de factura d ecliente
	 * @param controla CtrlFilaFactCliente
	 */
	public void establecerControlador(CtrlFilaFactCliente controla) {
		Component[] componentes=getComponents();
		JTextField jt=null;
		for (Component componente:componentes) 
			if (componente.getClass()==JTextField.class) {
				jt = (JTextField) componente;
				jt.addFocusListener(controla);
			}
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

	public JTextField getTPrecio() {
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

	public VFacturaCliente getvFactura() {
		return vFactura;
	}

	public FilaFacturaCliente getFila() {
		return fila;
	}
	
	
}
