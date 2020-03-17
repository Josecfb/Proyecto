package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controlador.ControlaFilaListadoClientes;
import controlador.ControladorListadoClientes;
import model.Cliente;

public class ListadoClientes extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private JScrollPane scroll;
	private FilaListadoClientes fila;
	private JLabel numero, nif, nombreComercial, nombreFiscal,codPost, poblacion, provincia, fijo, movil;
	private JTextField TFiltroNombre;
	private JButton bFiltrar;


	public ListadoClientes() {
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Listadio de Clientes");
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(10, 10, 1120, 600);
		getContentPane().setLayout(null);
		inicializar();
	}
	
	private void inicializar() {
		Color fondo=new Color(100,100,100);
		numero=new JLabel("Número");
		nif=new JLabel("Nif");
		nombreComercial=new JLabel("Nombre Comercial");
		nombreFiscal=new JLabel("Nombre Fiscal");
		codPost=new JLabel("Cod. Pos.");
		poblacion=new JLabel("Población");
		provincia=new JLabel("Provincia");
		fijo=new JLabel("Fijo");
		movil=new JLabel("Movil");
		numero.setBounds(10, 42, 40, 20);
		nif.setBounds(60, 42, 70, 20);
		nombreComercial.setBounds(140, 42, 200, 20);
		nombreFiscal.setBounds(350, 42, 200, 20);
		codPost.setBounds(560, 42, 60, 20);
		poblacion.setBounds(630, 42, 90, 20);
		provincia.setBounds(740, 42, 90, 20);
		fijo.setBounds(870, 42, 70, 20);
		movil.setBounds(950, 42, 70, 20);
		numero.setOpaque(true);
		nif.setOpaque(true);
		nombreComercial.setOpaque(true);
		nombreFiscal.setOpaque(true);
		codPost.setOpaque(true);
		poblacion.setOpaque(true);
		provincia.setOpaque(true);
		fijo.setOpaque(true);
		movil.setOpaque(true);
		numero.setBackground(fondo);
		nif.setBackground(fondo);
		nombreComercial.setBackground(fondo);
		nombreFiscal.setBackground(fondo);
		codPost.setBackground(fondo);
		poblacion.setBackground(fondo);
		provincia.setBackground(fondo);
		fijo.setBackground(fondo);
		movil.setBackground(fondo);
		numero.setForeground(Color.WHITE);
		nif.setForeground(Color.WHITE);
		nombreComercial.setForeground(Color.WHITE);
		nombreFiscal.setForeground(Color.WHITE);
		codPost.setForeground(Color.WHITE);
		poblacion.setForeground(Color.WHITE);
		provincia.setForeground(Color.WHITE);
		fijo.setForeground(Color.WHITE);
		movil.setForeground(Color.WHITE);
		getContentPane().add(numero);
		getContentPane().add(nif);
		getContentPane().add(nombreComercial);
		getContentPane().add(nombreFiscal);
		getContentPane().add(codPost);
		getContentPane().add(poblacion);
		getContentPane().add(provincia);
		getContentPane().add(fijo);
		getContentPane().add(movil);
		
		scroll =new JScrollPane();
		scroll.setBackground(fondo);
		scroll.setBounds(10, 61, 1100, getHeight()-20);
		scroll.setBorder(null);
		getContentPane().add(scroll);
		TFiltroNombre = new JTextField();
		TFiltroNombre.setBounds(64, 11, 201, 20);
		getContentPane().add(TFiltroNombre);
		TFiltroNombre.setColumns(10);
		
		bFiltrar = new JButton();
		bFiltrar.setBounds(280, 10, 20, 20);
		bFiltrar.setIcon(new ImageIcon("src/img/filter.png"));
		getContentPane().add(bFiltrar);
		ControladorListadoClientes controladorListadoClientes=new ControladorListadoClientes(this);		
	}
	
	public void muestra(List<Cliente> filas) {
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		int i=0;
		scroll.setViewportView(panel);
		for (Cliente cli:filas) {
			i++;
			fila=new FilaListadoClientes();
			ControlaFilaListadoClientes controlaFila=new ControlaFilaListadoClientes(fila);
			fila.establecerControlador(controlaFila);
			fila.setPreferredSize(new Dimension(1100,20));
			if (i%2==0) fila.setBackground(Color.WHITE);
			fila.getNumero().setText(String.valueOf(cli.getNumero()));
			fila.getNombreComercial().setText(cli.getNombreComercial());
			fila.getNombreFiscal().setText(cli.getNombreFiscal());
			fila.getNif().setText(cli.getNifCif());
			fila.getCodPost().setText(cli.getCodPost());
			fila.getProvincia().setText(cli.getProvincia());
			fila.getPoblacion().setText(cli.getPoblacion());
			fila.getFijo().setText(cli.getTelefonoFijo());
			fila.getMovil().setText(cli.getTelefonoMovil());
			panel.add(fila);
		}
	}

	public JTextField getTFiltroNombre() {
		return TFiltroNombre;
	}

	public JButton getbFiltrar() {
		return bFiltrar;
	}
	
	

}
