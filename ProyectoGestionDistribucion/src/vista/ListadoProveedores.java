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


public class ListadoProveedores extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private VentanaPrincipal v;
	private JScrollPane scroll;
	private FilaListadoProveedores fila;
	private JLabel numero, nombre, direccion, codPost, poblacion, provincia, fijo, movil;


	public ListadoProveedores(VentanaPrincipal v) {
		this.v=v;
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Listadio de Proveedores");
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(10, 10, 1120, 600);
		setLayout(null);
		inicializar();

	}
	
	private void inicializar() {
		Color fondo=new Color(100,100,100);
		numero=new JLabel("Número");
		nombre=new JLabel("Nombre");
		direccion=new JLabel("Dirección");
		codPost=new JLabel("Cod. Pos.");
		poblacion=new JLabel("Población");
		provincia=new JLabel("Provincia");
		fijo=new JLabel("Fijo");
		movil=new JLabel("Movil");
		numero.setBounds(10, 11, 45, 20);
		nombre.setBounds(65, 11, 200, 20);
		direccion.setBounds(280, 11, 300, 20);
		codPost.setBounds(590, 11, 60, 20);
		poblacion.setBounds(660, 11, 120, 20);
		provincia.setBounds(795, 11, 100, 20);
		fijo.setBounds(910, 11, 70, 20);
		movil.setBounds(990, 11, 70, 20);
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
		add(numero);
		numero.setForeground(Color.WHITE);
		nombre.setForeground(Color.WHITE);
		direccion.setForeground(Color.WHITE);
		codPost.setForeground(Color.WHITE);
		poblacion.setForeground(Color.WHITE);
		provincia.setForeground(Color.WHITE);
		fijo.setForeground(Color.WHITE);
		movil.setForeground(Color.WHITE);
		add(numero);
		add(nombre);
		add(direccion);
		add(codPost);
		add(poblacion);
		add(provincia);
		add(fijo);
		add(movil);
		
		scroll =new JScrollPane();
		scroll.setBackground(fondo);
		scroll.setBounds(10, 30, 1100, getHeight()-20);
		scroll.setBorder(null);
		add(scroll);


		ControladorListadoProveedores controladorListadoProveedores=new ControladorListadoProveedores(this);

		
	}
	
	public void muestra(List<Proveedor> filas) {
		
		//FilaListadoProveedores fila;
		JPanel panel = new JPanel();


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



	
}
