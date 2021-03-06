package vista.clientes;

import javax.swing.JPanel;
import controlador.clientes.ControlaFilaListadoClientes;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * Vista de las filas del listado de clientes
 * @author Jose Carlos
 *
 */
public class VFilaListadoClientes extends JPanel {

	private static final long serialVersionUID = 4813844448451936303L;
	private JLabel numero, nif, nombreComercial, nombreFiscal,codPost, poblacion, provincia, fijo, movil;
	private JButton bEditar;
	private VListadoClientes v;
	/**
	 * El constructor recibe la ventana de listado de clientes
	 * @param v ventana de listado de clientes VListadoClientes
	 */
	public VFilaListadoClientes(VListadoClientes v) {
		this.v=v;
		setLayout(null);
		setSize(1074, 25);
		setBackground(new Color(240,240,240));
		inicializar();
		setVisible(true);
	}
	/**
	 * Inicializa la vista de la fila del listado de clientes
	 */
	private void inicializar() {
		numero=new JLabel("N�mero");
		nif=new JLabel("Nif");
		nombreComercial=new JLabel("Nombre Comercial");
		nombreFiscal=new JLabel("Nombre Fiscal");
		codPost=new JLabel("Cod. Pos.");
		poblacion=new JLabel("Poblaci�n");
		provincia=new JLabel("Provincia");
		fijo=new JLabel("Fijo");
		movil=new JLabel("Movil");
		numero.setBounds(10, 5, 40, 15);
		nif.setBounds(60, 5, 70, 15);
		nombreComercial.setBounds(140, 5, 200, 15);
		nombreFiscal.setBounds(350, 5, 200, 15);
		codPost.setBounds(560, 5, 60, 15);
		poblacion.setBounds(630, 5, 90, 15);
		provincia.setBounds(740, 5, 90, 15);
		fijo.setBounds(870, 5, 70, 15);
		movil.setBounds(950, 5, 70, 15);
		add(numero);
		add(nif);
		add(nombreComercial);
		add(nombreFiscal);
		add(codPost);
		add(poblacion);
		add(provincia);
		add(fijo);
		add(movil);
		bEditar=new JButton();
		bEditar.setBounds(1070, 2, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
	}
	/**
	 * Establece el controlador para la fila 
	 * @param controlador ControlaFilaListadoClientes
	 */
	public void establecerControlador(ControlaFilaListadoClientes controlador) {
		bEditar.addActionListener(controlador);
	}

	

	public JLabel getNumero() {
		return numero;
	}

	public JLabel getNif() {
		return nif;
	}

	public JLabel getNombreComercial() {
		return nombreComercial;
	}

	public JLabel getNombreFiscal() {
		return nombreFiscal;
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

	public VListadoClientes getVListCli() {
		return v;
	}
	
	
	
}
