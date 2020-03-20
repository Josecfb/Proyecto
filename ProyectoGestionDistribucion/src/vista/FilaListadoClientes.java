package vista;

import javax.swing.JPanel;

import controlador.ControlaFilaListadoClientes;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FilaListadoClientes extends JPanel {

	private static final long serialVersionUID = 4813844448451936303L;
	private JLabel numero, nif, nombreComercial, nombreFiscal,codPost, poblacion, provincia, fijo, movil;
	private JButton bEditar;
	private VentanaPrincipal v;

	public FilaListadoClientes(VentanaPrincipal v) {
		this.v=v;
		setLayout(null);
		setSize(975, 20);
		setBackground(new Color(240,240,240));
		inicializar();
		setVisible(true);
	}
	
	private void inicializar() {
		numero=new JLabel("Número");
		nif=new JLabel("Nif");
		nombreComercial=new JLabel("Nombre Comercial");
		nombreFiscal=new JLabel("Nombre Fiscal");
		codPost=new JLabel("Cod. Pos.");
		poblacion=new JLabel("Población");
		provincia=new JLabel("Provincia");
		fijo=new JLabel("Fijo");
		movil=new JLabel("Movil");
		numero.setBounds(10, 5, 40, 10);
		nif.setBounds(60, 5, 70, 10);
		nombreComercial.setBounds(140, 5, 200, 10);
		nombreFiscal.setBounds(350, 5, 200, 10);
		codPost.setBounds(560, 5, 60, 10);
		poblacion.setBounds(630, 5, 90, 10);
		provincia.setBounds(740, 5, 90, 10);
		fijo.setBounds(870, 5, 70, 10);
		movil.setBounds(950, 5, 70, 10);
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
		bEditar.setBounds(1070, 0, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
	}
	
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

	public VentanaPrincipal getV() {
		return v;
	}
	
	
	
}
