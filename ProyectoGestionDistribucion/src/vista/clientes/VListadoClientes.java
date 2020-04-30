package vista.clientes;

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
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controlador.clientes.ControlaFilaListadoClientes;
import controlador.clientes.ControladorListadoClientes;
import model.Cliente;
import vista.VentanaPrincipal;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class VListadoClientes extends JInternalFrame {

	private static final long serialVersionUID = -4178570588145846454L;
	private JScrollPane scroll;
	private VFilaListadoClientes fila;
	private JLabel numero, nif, nombreComercial, nombreFiscal,codPost, poblacion, provincia, fijo, movil;
	private JTextField TFiltroNombre;
	private JPanel panel;
	private JButton bFiltrar;
	private JToolBar toolBar;
	private JButton bNuevo;
	private JToggleButton bFiltros;
	private JButton bActualizar;
	private JButton bPedidos;
	private JButton bAlbaranes;
	private JButton bFacturas;
	private VentanaPrincipal v;


	public VListadoClientes(VentanaPrincipal v) {
		this.v=v;
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
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
		numero.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nif=new JLabel("Nif");
		nif.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nombreComercial=new JLabel("Nombre Comercial");
		nombreComercial.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nombreFiscal=new JLabel("Nombre Fiscal");
		nombreFiscal.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		numero.setBounds(10, 98, 40, 20);
		nif.setBounds(60, 98, 70, 20);
		nombreComercial.setBounds(140, 98, 200, 20);
		nombreFiscal.setBounds(350, 98, 200, 20);
		codPost.setBounds(560, 98, 60, 20);
		poblacion.setBounds(630, 98, 90, 20);
		provincia.setBounds(740, 98, 90, 20);
		fijo.setBounds(870, 98, 70, 20);
		movil.setBounds(950, 98, 70, 20);
		numero.setOpaque(true);
		nif.setOpaque(true);
		nombreComercial.setOpaque(true);
		nombreFiscal.setOpaque(true);
		codPost.setOpaque(true);
		poblacion.setOpaque(true);
		provincia.setOpaque(true);
		fijo.setOpaque(true);
		movil.setOpaque(true);
		numero.setBackground(new Color(0, 0, 128));
		nif.setBackground(new Color(0, 0, 128));
		nombreComercial.setBackground(new Color(0, 0, 128));
		nombreFiscal.setBackground(new Color(0, 0, 128));
		codPost.setBackground(new Color(0, 0, 128));
		poblacion.setBackground(new Color(0, 0, 128));
		provincia.setBackground(new Color(0, 0, 128));
		fijo.setBackground(new Color(0, 0, 128));
		movil.setBackground(new Color(0, 0, 128));
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
		scroll.setBounds(10, 117, 1100, 442);
		scroll.setBorder(null);
		getContentPane().add(scroll);
		TFiltroNombre = new JTextField();
		TFiltroNombre.setBounds(64, 67, 201, 20);
		getContentPane().add(TFiltroNombre);
		TFiltroNombre.setColumns(10);
		TFiltroNombre.setVisible(false);
		
		bFiltrar = new JButton();
		bFiltrar.setBounds(280, 66, 20, 20);
		bFiltrar.setIcon(new ImageIcon("src/img/filter.png"));
		bFiltrar.setVisible(false);
		getContentPane().add(bFiltrar);
		
		toolBar = new JToolBar();
		toolBar.setSize(new Dimension(0, 90));
		toolBar.setPreferredSize(new Dimension(13, 90));
		toolBar.setMaximumSize(new Dimension(13, 90));
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
		
		ControladorListadoClientes controladorListadoClientes=new ControladorListadoClientes(this);		
	}
	
	public void muestra(List<Cliente> filas) {
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,filas.size()*25));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		int i=0;
		scroll.setViewportView(panel);
		for (Cliente cli:filas) {
			i++;
			fila=new VFilaListadoClientes(v);
			ControlaFilaListadoClientes controlaFila=new ControlaFilaListadoClientes(fila);
			fila.establecerControlador(controlaFila);
			fila.setPreferredSize(new Dimension(1100,20));
			if (i%2==0) fila.setBackground(Color.WHITE);
			fila.getNumero().setText(String.valueOf(cli.getNumero()));
			fila.getNombreComercial().setText(cli.getNombre());
			fila.getNombreFiscal().setText(cli.getApellidos());
			fila.getNif().setText(cli.getNifCif());
			fila.getCodPost().setText(cli.getCodPost());
			fila.getProvincia().setText(cli.getProvincia());
			fila.getPoblacion().setText(cli.getPoblacion());
			fila.getFijo().setText(cli.getTelefonoFijo());
			fila.getMovil().setText(cli.getTelefonoMovil());
			panel.add(fila);
		}
	}
	
	public void establecerControlador(ControladorListadoClientes controlador) {
		bFiltrar.addActionListener(controlador);
		bNuevo.addActionListener(controlador);
		bFiltros.addActionListener(controlador);
		bActualizar.addActionListener(controlador);
	}

	public VentanaPrincipal getV() {
		return v;
	}

	public JTextField getTFiltroNombre() {
		return TFiltroNombre;
	}

	public JButton getbFiltrar() {
		return bFiltrar;
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

	public JButton getbPedidos() {
		return bPedidos;
	}

	public JButton getbAlbaranes() {
		return bAlbaranes;
	}

	public JButton getbFacturas() {
		return bFacturas;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getNumero() {
		return numero;
	}
	
	

}
