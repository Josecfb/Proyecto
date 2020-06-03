package vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controlador.ControladorPrincipal;
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
import java.awt.Toolkit;
/**
 * Vista de la ventana principal
 * @author Jose Carlos
 *
 */
public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = -2711106703828276308L;
	/**
	 * 
	 */
	private JMenuBar barra;
	private JMenu menuFichero, menuProveedores, menuClientes, menuArticulos;
	private JMenuItem salir,listarProveedores,listarClientes,listarArticulos;
	private JDesktopPane panelInterior;
	private JToolBar toolBar;
	private JButton bArticulos;
	private JButton bClientes;
	private JButton bProveedores;
	private JButton bConfiguracion;
	/**
	 * Constructor de la ventana principal
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icono.png")));
		setTitle("Destión Distribución");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 487, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//getContentPane().setLayout(null);
		inicializar();
		this.setVisible(true);
	
	}
	/**
	 * Inicializa componentes
	 */
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
		
		bConfiguracion = new JButton("Empresa");
		bConfiguracion.setMaximumSize(new Dimension(90, 90));
		toolBar.add(bConfiguracion);
		bConfiguracion.setIcon(new ImageIcon(new ImageIcon("src/img/config.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		bConfiguracion.setHorizontalTextPosition(SwingConstants.CENTER );
		bConfiguracion.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		panelInterior.setVisible(true);
	}
	/**
	 * Crea los menús de la ventana principal
	 */
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
	/**
	 * Establece el controlador ControladorPrincipal de la ventana principal
	 * @param controlador ControladorPrincipal
	 */
	public void establecerControlador(ControladorPrincipal controlador) {
		listarProveedores.addActionListener(controlador);
		listarClientes.addActionListener(controlador);
		listarArticulos.addActionListener(controlador);
		bArticulos.addActionListener(controlador);
		bClientes.addActionListener(controlador);
		bProveedores.addActionListener(controlador);
		bConfiguracion.addActionListener(controlador);
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
	public JButton getbConfiguracion() {
		return bConfiguracion;
	}
	
	
}
