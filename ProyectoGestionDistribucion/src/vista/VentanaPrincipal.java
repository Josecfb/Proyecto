package vista;



import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controlador.ControladorListadoArticulos;
import controlador.ControladorListadoProveedores;
import controlador.ControladorPrincipal;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Rectangle;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = -2711106703828276308L;
	private JMenuBar barra;
	private JMenu menuFichero, menuProveedores, menuClientes, menuArticulos;
	private JMenuItem salir,listarProveedores,listarClientes,listarArticulos;
	private JDesktopPane panelInterior;

	
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
		ListadoProveedores lp=new ListadoProveedores(this);
		ControladorListadoProveedores controla =new ControladorListadoProveedores(lp);
		lp.establecerControlador(controla);
		panelInterior.add(lp);
		lp.setVisible(true);
	}
	
	public void listadoClientes() {
		ListadoClientes lc=new ListadoClientes();
		panelInterior.add(lc);
		lc.setVisible(true);
	}
	
	public void listadoArticulos() {
		ListadoArticulos la=new ListadoArticulos(this);
		ControladorListadoArticulos controlala=new ControladorListadoArticulos(la);
		la.establecerControlador(controlala);
		panelInterior.add(la);
		la.setVisible(true);
		
	}
	
	public void establecerControlador(ControladorPrincipal controlador) {
		listarProveedores.addActionListener(controlador);
		listarClientes.addActionListener(controlador);
		listarArticulos.addActionListener(controlador);
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
	
	
}
