package vista.proveedores;

import javax.swing.JPanel;
import controlador.proveedores.ControlaFilaListadoProveedores;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * Vista de las fila de la ventana de listado de proveedores
 * @author Jose Carlos
 *
 */
public class VFilaListadoProveedores extends JPanel {

	private static final long serialVersionUID = 4813844448451936303L;
	private VListadoProveedores v;
	private JLabel numero, nombre, direccion, codPost, poblacion, provincia, fijo, movil;
	private JButton bEditar;
	/**
	 * El constructor recibe la ventana de listado de proveedores VListadoProveedores
	 * @param v ventana de listado de proveedores VListadoProveedores
	 */
	public VFilaListadoProveedores(VListadoProveedores v) {
		this.v=v;
		setLayout(null);
		setSize(975, 20);
		setBackground(new Color(240,240,240));
		inicializar();
		setVisible(true);
	}
	/**
	 * Inicializa los componentes de la fila
	 */
	private void inicializar() {
		numero=new JLabel();
		nombre=new JLabel();
		direccion=new JLabel();
		codPost=new JLabel();
		poblacion=new JLabel();
		provincia=new JLabel();
		fijo=new JLabel();
		movil=new JLabel();
		numero.setBounds(10, 5, 45, 10);
		nombre.setBounds(60, 5, 200, 10);
		direccion.setBounds(275, 5, 300, 10);
		codPost.setBounds(585, 5, 50, 10);
		poblacion.setBounds(660, 5, 120, 10);
		provincia.setBounds(795, 5, 100, 10);
		fijo.setBounds(910, 5, 70, 10);
		movil.setBounds(990, 5, 70, 10);
		add(numero);
		add(nombre);
		add(direccion);
		add(codPost);
		add(poblacion);
		add(provincia);
		add(fijo);
		add(movil);
		bEditar=new JButton();
		bEditar.setBounds(1070, 0, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
	}
	/**
	 * Establece el controlador de la fila del listado de proveedores
	 * @param controlador ControlaFilaListadoProveedores
	 */
	public void establecerControlador(ControlaFilaListadoProveedores controlador) {
		bEditar.addActionListener(controlador);
	}

	public JLabel getNumero() {
		return numero;
	}

	public JLabel getNombre() {
		return nombre;
	}

	public JLabel getDireccion() {
		return direccion;
	}

	public JLabel getCodPost() {
		return codPost;
	}

	public JLabel getPoblacion() {
		return poblacion;
	}

	public JLabel getProvincia() {
		return provincia;
	}

	public JLabel getFijo() {
		return fijo;
	}

	public JLabel getMovil() {
		return movil;
	}

	public JButton getbEditar() {
		return bEditar;
	}

	public void setbEditar(JButton bEditar) {
		this.bEditar = bEditar;
	}

	public VListadoProveedores getVListProv() {
		return v;
	}
	
	
	
}
