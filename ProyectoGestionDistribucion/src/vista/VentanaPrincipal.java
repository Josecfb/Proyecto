package vista;



import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controlador.ControladorPrincipal;
import controlador.articulos.ControladorListadoArticulos;
import controlador.clientes.ControladorListadoClientes;
import controlador.proveedores.ControladorListadoProveedores;
import vista.articulos.VListadoArticulos;
import vista.clientes.VListadoClientes;
import vista.proveedores.VListadoProveedores;

import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = -2711106703828276308L;
	private JMenuBar barra;
	private JMenu menuFichero, menuProveedores, menuClientes, menuArticulos;
	private JMenuItem salir,listarProveedores,listarClientes,listarArticulos;
	private JDesktopPane panelInterior;
	private JToolBar toolBar;
	private JButton bArticulos;
	private JButton bClientes;
	private JButton bProveedores;
	//private JButton bFacturas;
	

	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 487, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//getContentPane().setLayout(null);
		inicializar();
		this.setVisible(true);
	
	}
	private void inicializar() {
		pintaMenu();
		panelInterior = new JDesktopPane();
		panelInterior.setBackground(Color.DARK_GRAY);
		panelInterior.setBounds(new Rectangle(1, 1, 470, 239));
		getContentPane().add(panelInterior);
		
		toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(13, 90));
		toolBar.setSize(new Dimension(0, 90));
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		bArticulos = new JButton("Articulos");
		bArticulos.setMaximumSize(new Dimension(90, 90));
		toolBar.add(bArticulos);
		bArticulos.setIcon(new ImageIcon(new ImageIcon("src/img/articulos.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		bArticulos.setHorizontalTextPosition(SwingConstants.CENTER );
		bArticulos.setVerticalTextPosition( SwingConstants.BOTTOM );

		
		bClientes = new JButton("Clientes");
		bClientes.setMaximumSize(new Dimension(90, 90));
		toolBar.add(bClientes);
		bClientes.setIcon(new ImageIcon(new ImageIcon("src/img/clientes.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		bClientes.setHorizontalTextPosition(SwingConstants.CENTER );
		bClientes.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bProveedores = new JButton("Proveedores");
		bProveedores.setMaximumSize(new Dimension(90, 90));
		toolBar.add(bProveedores);
		bProveedores.setIcon(new ImageIcon(new ImageIcon("src/img/proveedores.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		bProveedores.setHorizontalTextPosition(SwingConstants.CENTER );
		bProveedores.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		panelInterior.setVisible(true);
	}
	private void pintaMenu() {
		barra=new JMenuBar();
		setJMenuBar(barra);

		menuFichero=new JMenu("Archivo");
		menuClientes=new JMenu("Clientes");
		menuArticulos=new JMenu("Articulos");
		menuProveedores=new JMenu("Proveedores");
		barra.add(menuFichero);
		barra.add(menuArticulos);
		barra.add(menuClientes);
		barra.add(menuProveedores);
		salir=new JMenuItem("Salir");
		menuFichero.add(salir);
		listarProveedores=new JMenuItem("Listado");
		listarClientes=new JMenuItem("Listado");
		listarArticulos=new JMenuItem("Listado");
		menuProveedores.add(listarProveedores);
		menuClientes.add(listarClientes);
		menuArticulos.add(listarArticulos);
	}
	public void listadoProveedores() {
		VListadoProveedores lp=new VListadoProveedores(this);
		ControladorListadoProveedores controla =new ControladorListadoProveedores(lp);
		lp.establecerControlador(controla);
		panelInterior.add(lp);
		lp.setVisible(true);
		System.out.println("hay "+panelInterior.getComponentCount());
	}
	
	public void listadoClientes() {
		VListadoClientes lc=new VListadoClientes(this);
		ControladorListadoClientes controlacli=new ControladorListadoClientes(lc);
		lc.establecerControlador(controlacli);
		panelInterior.add(lc);
		lc.setVisible(true);
	}
	
	public void listadoArticulos() {
		VListadoArticulos la=new VListadoArticulos(this);
		ControladorListadoArticulos controlala=new ControladorListadoArticulos(la);
		la.establecerControlador(controlala);
		panelInterior.add(la);
		la.setVisible(true);
		
	}
	
	public void establecerControlador(ControladorPrincipal controlador) {
		listarProveedores.addActionListener(controlador);
		listarClientes.addActionListener(controlador);
		listarArticulos.addActionListener(controlador);
		bArticulos.addActionListener(controlador);
		bClientes.addActionListener(controlador);
		bProveedores.addActionListener(controlador);
	}
	public JMenuItem getListarProveedores() {
		return listarProveedores;
	}
	public JMenuItem getListarClientes() {
		return listarClientes;
	}
	public JMenuItem getListarArticulos() {
		return listarArticulos;
	}
	public JDesktopPane getPanelInterior() {
		return panelInterior;
	}
	public JButton getbArticulos() {
		return bArticulos;
	}
	public JButton getbClientes() {
		return bClientes;
	}
	public JButton getbProveedores() {
		return bProveedores;
	}
	
	
}
