package vista.clientes;

import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controlador.clientes.ControlaFilaPrecioCli;
import entidades.Articulo;
import entidades.Cliente;
import modelo.negocio.GestorArticulo;
import util.JTextFieldN;
import util.Utilidades;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * Vista de la fila de precio especial para cliente
 * @author Jose Carlos
 *
 */
public class VFilaPrecioCliente extends JPanel {
	private static final long serialVersionUID = 1396732755308443918L;
	private Utilidades u;
	private Cliente cli;
	private VFichaCliente vFicha;
	private JTextFieldN tCodArt;
	private JTextFieldN tPorcent;
	private JTextFieldN tPrecio;
	private JLabel lPrecioReal;
	private JComboBox<Articulo> comboArt;
	private JButton bBorrar;
	/**
	 * El controlador recibe la ventana de ficha de cliente y el objeto Cliente
	 * @param vFicha Ventana de ficha de cliente VFichaCliente
	 * @param cli Objeto Cliente
	 */
	public VFilaPrecioCliente(VFichaCliente vFicha,Cliente cli) {
		this.vFicha=vFicha;
		this.cli=cli;
		u=new Utilidades();
		setLayout(null);
		
		tCodArt = new JTextFieldN(4,'n');
		tCodArt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tCodArt.setBounds(10, 5, 41, 20);
		add(tCodArt);
		tCodArt.setColumns(10);
		
		tPorcent = new JTextFieldN(5,'p');
		tPorcent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tPorcent.setColumns(10);
		tPorcent.setBounds(423, 5, 64, 20);
		tPorcent.setText("0,00 %");
		add(tPorcent);
		
		tPrecio = new JTextFieldN(5,'m');
		tPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tPrecio.setColumns(10);
		tPrecio.setBounds(497, 5, 84, 20);
		tPrecio.setText("0,00 €");
		add(tPrecio);
		
		comboArt = new JComboBox<Articulo>();
		comboArt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboArt.setBounds(61, 5, 352, 20);
		comboArt.setEditable(true);
		GestorArticulo ga=new GestorArticulo();
		List<Articulo> artis=ga.listar("");
		for (Articulo art:artis)
			comboArt.addItem(art);
		comboArt.setSelectedItem(null);
		AutoCompleteDecorator.decorate(comboArt);
		add(comboArt);
		
		
		bBorrar = new JButton("");
		bBorrar.setBounds(679, 5, 20, 20);
		bBorrar.setIcon(new ImageIcon("src/img/borrarfila.png"));
		add(bBorrar);
		
		lPrecioReal = new JLabel("");
		lPrecioReal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lPrecioReal.setBounds(591, 5, 67, 20);
		add(lPrecioReal);

	}
	/**
	 * Establece el controlador de la vista de fila de precio especial de cliente
	 * @param controlador ControlaFilaPrecioCli
	 */
	public void establecerControlador(ControlaFilaPrecioCli controlador) {
		bBorrar.addActionListener(controlador);

		u.addFocusKey(this,controlador,controlador);
		comboArt.getEditor().getEditorComponent().addFocusListener(controlador);
	}

	public JTextField gettCodArt() {
		return tCodArt;
	}

	public JTextField gettPorcent() {
		return tPorcent;
	}

	public JTextField gettPrecio() {
		return tPrecio;
	}

	public JComboBox<Articulo> getComboArt() {
		return comboArt;
	}

	public JButton getbBorrar() {
		return bBorrar;
	}

	public JLabel getlPrecioReal() {
		return lPrecioReal;
	}

	public Cliente getCli() {
		return cli;
	}

	public VFichaCliente getvFicha() {
		return vFicha;
	}
	
	
	
}
