package vista;


import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;

import controlador.ControlaFilaListadoProveedores;
import controlador.ControladorListadoProveedores;
import model.Proveedor;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ListadoProveedores extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private VentanaPrincipal v;
	private JScrollPane scroll;
	private FilaListadoProveedores fila;
	private JLabel numero, nombre, direccion, codPost, poblacion, provincia, fijo, movil;
	private JTextField TFiltroNombre;
	private JButton bFiltrar;
	private JPanel panel;


	public ListadoProveedores(VentanaPrincipal v) {
		this.v=v;
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Listado de Proveedores");
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(10, 10, 1120, 600);
		getContentPane().setLayout(null);
		inicializar();

	}
	
	private void inicializar() {
		Color fondo=new Color(100,100,100);
		numero=new JLabel("Número");
		numero.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nombre=new JLabel("Nombre");
		nombre.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		direccion=new JLabel("Dirección");
		direccion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		codPost=new JLabel("Cod. Pos.");
		codPost.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		poblacion=new JLabel("Población");
		poblacion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		provincia=new JLabel("Provincia");
		provincia.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		fijo=new JLabel("Fijo");
		fijo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		movil=new JLabel("Movil");
		movil.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		numero.setBounds(10, 37, 45, 20);
		nombre.setBounds(65, 37, 200, 20);
		direccion.setBounds(280, 37, 300, 20);
		codPost.setBounds(590, 37, 60, 20);
		poblacion.setBounds(660, 37, 120, 20);
		provincia.setBounds(795, 37, 100, 20);
		fijo.setBounds(910, 37, 70, 20);
		movil.setBounds(990, 37, 70, 20);
		numero.setOpaque(true);
		nombre.setOpaque(true);
		direccion.setOpaque(true);
		codPost.setOpaque(true);
		poblacion.setOpaque(true);
		provincia.setOpaque(true);
		fijo.setOpaque(true);
		movil.setOpaque(true);
		numero.setBackground(fondo);
		nombre.setBackground(fondo);
		direccion.setBackground(fondo);
		codPost.setBackground(fondo);
		poblacion.setBackground(fondo);
		provincia.setBackground(fondo);
		fijo.setBackground(fondo);
		movil.setBackground(fondo);
		getContentPane().add(numero);
		numero.setForeground(Color.WHITE);
		nombre.setForeground(Color.WHITE);
		direccion.setForeground(Color.WHITE);
		codPost.setForeground(Color.WHITE);
		poblacion.setForeground(Color.WHITE);
		provincia.setForeground(Color.WHITE);
		fijo.setForeground(Color.WHITE);
		movil.setForeground(Color.WHITE);
		getContentPane().add(numero);
		getContentPane().add(nombre);
		getContentPane().add(direccion);
		getContentPane().add(codPost);
		getContentPane().add(poblacion);
		getContentPane().add(provincia);
		getContentPane().add(fijo);
		getContentPane().add(movil);
		
		scroll =new JScrollPane();
		scroll.setBackground(fondo);
		scroll.setBounds(10, 61, 1100, 575);
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


		ControladorListadoProveedores controladorListadoProveedores=new ControladorListadoProveedores(this);
		controladorListadoProveedores.listar(this);

		
	}
	
	public void muestra(List<Proveedor> filas) {
		
		//FilaListadoProveedores fila;
		panel = new JPanel();


		//panel.setBounds(0,0,727,600);
		panel.setPreferredSize(new Dimension(800,600));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);

		int i=0;
		scroll.setViewportView(panel);
		for (Proveedor pro:filas) {
			i++;
			
			
			fila=new FilaListadoProveedores(v);
			ControlaFilaListadoProveedores controlaFila=new ControlaFilaListadoProveedores(fila);
			fila.establecerControlador(controlaFila);
			fila.setPreferredSize(new Dimension(1100,20));
			
			if (i%2==0) fila.setBackground(Color.WHITE);
			fila.getNumero().setText(String.valueOf(pro.getNumero()));
			fila.getNombre().setText(pro.getNombre());
			fila.getDireccion().setText(pro.getDireccion());
			fila.getCodPost().setText(pro.getCodPost());
			fila.getProvincia().setText(pro.getProvincia());
			fila.getPoblacion().setText(pro.getPoblacion());
			fila.getFijo().setText(pro.getTelefonoFijo());
			fila.getMovil().setText(pro.getTelefonoMovil());
			panel.add(fila);
		}
	}
	
	public void establecerControlador(ControladorListadoProveedores controlador) {
		bFiltrar.addActionListener(controlador);
	}

	public JTextField getTFiltroNombre() {
		return TFiltroNombre;
	}

	public JButton getbFiltrar() {
		return bFiltrar;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JScrollPane getScroll() {
		return scroll;
	}
	
	
}
