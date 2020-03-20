package vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import controlador.ControlaFilaListadoArticulos;
import controlador.ControladorListadoArticulos;
import model.Articulo;
import javax.swing.border.BevelBorder;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;


public class ListadoArticulos extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private VentanaPrincipal v;
	private JScrollPane scroll;
	private FilaListadoArticulos fila;
	private JLabel Lcod, LCProv, lNombre, Lcoste, LPrecioMay, LPrecioMin, lStock, lStockMin;
	private JTextField TFiltroNombre;
	private JButton bFiltrar,bNuevo;
	private JPanel panel;
	private JToggleButton bFiltros;
	private JButton bActualizar, bPedidos,bAlbaranes,bFacturas;


	public ListadoArticulos(VentanaPrincipal v) {
		this.v=v;
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setTitle("Listado de Art\u00EDculos");
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(10, 10, 1120, 600);
		getContentPane().setLayout(null);
		inicializar();

	}
	
	private void inicializar() {
		Color fondo=new Color(100,100,100);
		Lcod=new JLabel("C\u00F3digo");
		Lcod.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		LCProv=new JLabel("C.Prov.");
		LCProv.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lNombre=new JLabel("Nombre");
		lNombre.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Lcoste=new JLabel("Coste");
		Lcoste.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		LPrecioMay=new JLabel("Precio May.");
		LPrecioMay.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		LPrecioMin=new JLabel("Precio Min.");
		LPrecioMin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lStock=new JLabel("Stock");
		lStock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lStockMin=new JLabel("Stock Min.");
		lStockMin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Lcod.setBounds(10, 102, 45, 20);
		LCProv.setBounds(65, 102, 70, 20);
		lNombre.setBounds(145, 102, 267, 20);
		Lcoste.setBounds(561, 102, 70, 20);
		LPrecioMay.setBounds(641, 102, 88, 20);
		LPrecioMin.setBounds(739, 102, 98, 20);
		lStock.setBounds(847, 102, 80, 20);
		lStockMin.setBounds(935, 102, 98, 20);
		Lcod.setOpaque(true);
		LCProv.setOpaque(true);
		lNombre.setOpaque(true);
		Lcoste.setOpaque(true);
		LPrecioMay.setOpaque(true);
		LPrecioMin.setOpaque(true);
		lStock.setOpaque(true);
		lStockMin.setOpaque(true);
		Lcod.setBackground(new Color(0, 0, 128));
		LCProv.setBackground(new Color(0, 0, 128));
		lNombre.setBackground(new Color(0, 0, 128));
		Lcoste.setBackground(new Color(0, 0, 128));
		LPrecioMay.setBackground(new Color(0, 0, 128));
		LPrecioMin.setBackground(new Color(0, 0, 128));
		lStock.setBackground(new Color(0, 0, 128));
		lStockMin.setBackground(new Color(0, 0, 128));
		getContentPane().add(Lcod);
		Lcod.setForeground(Color.WHITE);
		LCProv.setForeground(Color.WHITE);
		lNombre.setForeground(Color.WHITE);
		Lcoste.setForeground(Color.WHITE);
		LPrecioMay.setForeground(Color.WHITE);
		LPrecioMin.setForeground(Color.WHITE);
		lStock.setForeground(Color.WHITE);
		lStockMin.setForeground(Color.WHITE);
		getContentPane().add(Lcod);
		getContentPane().add(LCProv);
		getContentPane().add(lNombre);
		getContentPane().add(Lcoste);
		getContentPane().add(LPrecioMay);
		getContentPane().add(LPrecioMin);
		getContentPane().add(lStock);
		getContentPane().add(lStockMin);
		
		scroll =new JScrollPane();
		scroll.setBackground(fondo);
		scroll.setBounds(10, 127, 1100, 470);
		scroll.setBorder(null);
		getContentPane().add(scroll);
		
		JLabel LFam = new JLabel("Familia");
		LFam.setOpaque(true);
		LFam.setForeground(Color.WHITE);
		LFam.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		LFam.setBackground(new Color(0, 0, 128));
		LFam.setBounds(422, 102, 129, 20);
		getContentPane().add(LFam);

		TFiltroNombre = new JTextField();
		TFiltroNombre.setBounds(64, 75, 201, 20);
		getContentPane().add(TFiltroNombre);
		TFiltroNombre.setVisible(false);
		TFiltroNombre.setColumns(10);
		
		bFiltrar = new JButton();
		bFiltrar.setBounds(280, 74, 20, 20);
		bFiltrar.setIcon(new ImageIcon("src/img/filter.png"));
		bFiltrar.setVisible(false);
		getContentPane().add(bFiltrar);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setSize(new Dimension(0, 90));
		toolBar.setMaximumSize(new Dimension(13, 90));
		toolBar.setPreferredSize(new Dimension(13, 90));
		toolBar.setBounds(0, 0, 385, 63);
		getContentPane().add(toolBar);
		
		bNuevo = new JButton();
		bNuevo.setText("Nuevo");
		bNuevo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bNuevo);
		bNuevo.setIcon(new ImageIcon("src/img/nuevo.png"));
		bNuevo.setMaximumSize(new Dimension(60, 60));
		bNuevo.setHorizontalTextPosition(SwingConstants.CENTER );
		bNuevo.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bFiltros = new JToggleButton("Filtro");
		bFiltros.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bFiltros);
		bFiltros.setIcon(new ImageIcon("src/img/filtro.png"));
		bFiltros.setMaximumSize(new Dimension(60, 60));
		bFiltros.setHorizontalTextPosition(SwingConstants.CENTER );
		bFiltros.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bActualizar = new JButton("Actualizar");
		bActualizar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bActualizar);
		bActualizar.setIcon(new ImageIcon("src/img/actualizar.png"));
		bActualizar.setMaximumSize(new Dimension(60, 60));
		bActualizar.setHorizontalTextPosition(SwingConstants.CENTER );
		bActualizar.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bPedidos = new JButton("Pedidos");
		bPedidos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bPedidos);
		bPedidos.setIcon(new ImageIcon("src/img/pedidoproveedor.png"));
		bPedidos.setMaximumSize(new Dimension(60, 60));
		bPedidos.setHorizontalTextPosition(SwingConstants.CENTER );
		bPedidos.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bAlbaranes = new JButton("Albaranes");
		bAlbaranes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bAlbaranes);
		bAlbaranes.setIcon(new ImageIcon("src/img/albaran.png"));
		bAlbaranes.setMaximumSize(new Dimension(60, 60));
		bAlbaranes.setHorizontalTextPosition(SwingConstants.CENTER );
		bAlbaranes.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		bFacturas = new JButton("Facturas");
		bFacturas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(bFacturas);
		bFacturas.setIcon(new ImageIcon("src/img/facturas.png"));
		bFacturas.setMaximumSize(new Dimension(60, 60));
		bFacturas.setHorizontalTextPosition(SwingConstants.CENTER );
		bFacturas.setVerticalTextPosition( SwingConstants.BOTTOM );

		//ControladorListadoProveedores controladorListadoProveedores=new ControladorListadoProveedores(this);

		
	}
	
	public void muestra(List<Articulo> filas) {
		
		//FilaListadoProveedores fila;
		panel = new JPanel();


		panel.setBounds(0,0,727,1200);
		panel.setPreferredSize(new Dimension(800,filas.size()*30));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);

		int i=0;
		scroll.setViewportView(panel);
		for (Articulo arti:filas) {
			i++;
			fila=new FilaListadoArticulos(v);
			ControlaFilaListadoArticulos controlaFila=new ControlaFilaListadoArticulos(fila);
			fila.establecerControlador(controlaFila);
			fila.setPreferredSize(new Dimension(1100,20));
			if (i%2==0) fila.setBackground(Color.WHITE);
			fila.getCodigo().setText(String.valueOf(arti.getCod()));
			fila.getcProv().setText(arti.getCodpro());
			fila.getNombre().setText(arti.getNombre());
			fila.getFamilia().setText(arti.getFamiliaBean().getNombre());
			fila.getCoste().setText(String.valueOf(arti.getCoste()));
			fila.getPrecioMay().setText(String.valueOf(arti.getPrecioMayorista()));
			fila.getPrecioMin().setText(String.valueOf(arti.getPrecioMinorista()));
			fila.getStock().setText(String.valueOf(arti.getStock()));
			fila.getStockMin().setText(String.valueOf(arti.getStockMinimo()));
			panel.add(fila);
		}
	}
	
	public void establecerControlador(ControladorListadoArticulos controlador) {
		bFiltrar.addActionListener(controlador);
		bNuevo.addActionListener(controlador);
		bFiltros.addActionListener(controlador);
		bActualizar.addActionListener(controlador);
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

	public JButton getbNuevo() {
		return bNuevo;
	}

	public JToggleButton getbFiltros() {
		return bFiltros;
	}

	public JButton getbActualizar() {
		return bActualizar;
	}

	public VentanaPrincipal getV() {
		return v;
	}
	
}
