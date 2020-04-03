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
//import controlador.ControladorListadoArticulos;
import controlador.ControladorListadoProveedores;
import model.Proveedor;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;


public class VListadoProveedores extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private VentanaPrincipal v;
	private JScrollPane scroll;
	private VFilaListadoProveedores fila;
	private JLabel numero, nombre, direccion, codPost, poblacion, provincia, fijo, movil;
	private JTextField TFiltroNombre;
	private JPanel panel;
	private JButton bFiltrar,bNuevo;
	private JToggleButton bFiltros;
	private JButton bActualizar;
	private JButton bFacturas;
	private JButton bPedidos;
	private JButton bAlbaranes;


	public VListadoProveedores(VentanaPrincipal v) {
		this.v=v;
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
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
		numero.setBounds(10, 91, 45, 20);
		nombre.setBounds(65, 91, 200, 20);
		direccion.setBounds(280, 91, 300, 20);
		codPost.setBounds(590, 91, 60, 20);
		poblacion.setBounds(660, 91, 120, 20);
		provincia.setBounds(795, 91, 100, 20);
		fijo.setBounds(910, 91, 70, 20);
		movil.setBounds(990, 91, 70, 20);
		numero.setOpaque(true);
		nombre.setOpaque(true);
		direccion.setOpaque(true);
		codPost.setOpaque(true);
		poblacion.setOpaque(true);
		provincia.setOpaque(true);
		fijo.setOpaque(true);
		movil.setOpaque(true);
		numero.setBackground(new Color(0, 0, 128));
		nombre.setBackground(new Color(0, 0, 128));
		direccion.setBackground(new Color(0, 0, 128));
		codPost.setBackground(new Color(0, 0, 128));
		poblacion.setBackground(new Color(0, 0, 128));
		provincia.setBackground(new Color(0, 0, 128));
		fijo.setBackground(new Color(0, 0, 128));
		movil.setBackground(new Color(0, 0, 128));
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
		scroll.setBounds(10, 115, 1100, 444);
		scroll.setBorder(null);
		getContentPane().add(scroll);
		
		TFiltroNombre = new JTextField();
		TFiltroNombre.setBounds(54, 67, 201, 20);
		getContentPane().add(TFiltroNombre);
		TFiltroNombre.setVisible(false);
		TFiltroNombre.setColumns(10);
		
		bFiltrar = new JButton();
		bFiltrar.setBounds(270, 66, 20, 20);
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

		ControladorListadoProveedores controladorListadoProveedores=new ControladorListadoProveedores(this);
		controladorListadoProveedores.listar(this);

		
	}
	
	public void muestra(List<Proveedor> filas) {
		
		//FilaListadoProveedores fila;
		panel = new JPanel();


		//panel.setBounds(0,0,727,600);
		panel.setPreferredSize(new Dimension(800,filas.size()*25));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);

		int i=0;
		scroll.setViewportView(panel);
		for (Proveedor pro:filas) {
			i++;
			
			
			fila=new VFilaListadoProveedores(v);
			ControlaFilaListadoProveedores controlaFila=new ControlaFilaListadoProveedores(fila);
			fila.establecerControlador(controlaFila);
			fila.setPreferredSize(new Dimension(1100,20));
			
			if (i%2==0) fila.setBackground(new Color(234,236,255));
			else fila.setBackground(Color.WHITE);
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
		bNuevo.addActionListener(controlador);
		bFiltros.addActionListener(controlador);
		bActualizar.addActionListener(controlador);
		bPedidos.addActionListener(controlador);
		bAlbaranes.addActionListener(controlador);
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

	public JButton getbNuevo() {
		return bNuevo;
	}

	public JToggleButton getbFiltros() {
		return bFiltros;
	}

	public JButton getbActualizar() {
		return bActualizar;
	}

	public JButton getbFacturas() {
		return bFacturas;
	}

	public JButton getbPedidos() {
		return bPedidos;
	}

	public JButton getbAlbaranes() {
		return bAlbaranes;
	}

	public VentanaPrincipal getV() {
		return v;
	}
	
	
}
