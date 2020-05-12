package vista.articulos;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.articulos.ControladorFichaArticulo;
import model.Articulo;
import model.Familia;
import model.Proveedor;
import modelo.negocio.GestorFamilia;
import modelo.negocio.GestorProveedor;
import vista.VentanaPrincipal;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class VFichaArticulo extends JInternalFrame {

	private static final long serialVersionUID = 3871990475316407616L;
	private VentanaPrincipal v;
	private JTextField tCodigo;
	private JTextField tNombre;
	private JTextField tCProv;
	private JTextField tCoste;
	private JTextField tPrecioMay;
	private JTextField tPrecioMin;
	private JTextField tIva;
	private JTextField tUnidadesCaja;
	private JTextField tStock;
	private JTextField tStockMin;
	private JComboBox<Familia> comboFamilia;
	private JComboBox<Proveedor> comboProveedor;
	private NumberFormat formatoeuro, formatoPorcentaje;
	private Articulo art;
	private JLabel lFoto;
	private JPanel panel;

	public VFichaArticulo(Articulo art,VentanaPrincipal v) {
		this.art=art;
		this.v=v;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoPorcentaje = NumberFormat.getPercentInstance();
		setBounds(100, 100, 870, 476);
		getContentPane().setLayout(null);
		setTitle("Ficha de Artículos");
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(51, 47, 772, 373);
		getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Datos Articulo", null, panel, null);
		panel.setLayout(null);
		
		JLabel lCodigo = new JLabel("C\u00F3digo");
		lCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodigo.setBounds(31, 40, 55, 25);
		panel.add(lCodigo);
		
		tCodigo = new JTextField();
		tCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodigo.setBounds(96, 40, 86, 25);
		tCodigo.setHorizontalAlignment(JTextField.RIGHT);
		tCodigo.setFocusable(false);
		panel.add(tCodigo);
		
		JLabel lNombre = new JLabel("Nombre");
		lNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre.setBounds(216, 40, 55, 25);
		panel.add(lNombre);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(298, 40, 437, 25);
		panel.add(tNombre);
		
		tCProv = new JTextField();
		tCProv.setHorizontalAlignment(SwingConstants.RIGHT);
		tCProv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCProv.setColumns(10);
		tCProv.setBounds(96, 92, 86, 25);
		panel.add(tCProv);
		
		JLabel lCodPro = new JLabel("C. Prov.");
		lCodPro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPro.setBounds(31, 92, 55, 25);
		panel.add(lCodPro);
		
		JLabel lProveedor = new JLabel("Proveedor");
		lProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProveedor.setBounds(216, 92, 72, 25);
		panel.add(lProveedor);
		
		comboProveedor = new JComboBox<Proveedor>();
		comboProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboProveedor.setBounds(298, 92, 437, 25);
		comboProveedor.setEditable(true);
		GestorProveedor gp=new GestorProveedor();
		List<Proveedor> proveedores;
		proveedores=gp.listar("");
		for (Proveedor pro:proveedores)
			comboProveedor.addItem(pro);
		comboProveedor.setSelectedItem(null);
		panel.add(comboProveedor);
		
		JLabel lCoste = new JLabel("Coste");
		lCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCoste.setBounds(31, 146, 55, 25);
		panel.add(lCoste);
		
		tCoste = new JTextField();
		tCoste.setHorizontalAlignment(SwingConstants.RIGHT);
		tCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCoste.setColumns(10);
		tCoste.setBounds(96, 146, 134, 25);
		tCoste.setText("0,00 €");
		panel.add(tCoste);
		
		tPrecioMay = new JTextField();
		tPrecioMay.setHorizontalAlignment(SwingConstants.RIGHT);
		tPrecioMay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPrecioMay.setColumns(10);
		tPrecioMay.setBounds(363, 146, 134, 25);
		tPrecioMay.setText("0,00 €");
		panel.add(tPrecioMay);
		
		JLabel lPrecioMay = new JLabel("Precio May.");
		lPrecioMay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPrecioMay.setBounds(281, 146, 72, 25);
		panel.add(lPrecioMay);
		
		tPrecioMin = new JTextField();
		tPrecioMin.setHorizontalAlignment(SwingConstants.RIGHT);
		tPrecioMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPrecioMin.setColumns(10);
		tPrecioMin.setBounds(601, 146, 134, 25);
		tPrecioMin.setText("0,00 €");
		panel.add(tPrecioMin);
		
		JLabel lPrecioMin = new JLabel("Precio Min.");
		lPrecioMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPrecioMin.setBounds(519, 146, 72, 25);
		panel.add(lPrecioMin);
		
		JLabel lIva = new JLabel("IVA");
		lIva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lIva.setBounds(31, 197, 55, 25);
		panel.add(lIva);
		
		tIva = new JTextField();
		tIva.setHorizontalAlignment(SwingConstants.RIGHT);
		tIva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tIva.setColumns(10);
		tIva.setBounds(96, 197, 72, 25);
		tIva.setText("0%");
		panel.add(tIva);
		
		tUnidadesCaja = new JTextField();
		tUnidadesCaja.setHorizontalAlignment(SwingConstants.RIGHT);
		tUnidadesCaja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tUnidadesCaja.setColumns(10);
		tUnidadesCaja.setBounds(304, 197, 72, 25);
		tUnidadesCaja.setText("0");
		panel.add(tUnidadesCaja);
		
		JLabel lUCaja = new JLabel("Unidades/Caja");
		lUCaja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lUCaja.setBounds(201, 197, 93, 25);
		panel.add(lUCaja);
		
		JLabel lFamilia = new JLabel("Familia");
		lFamilia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFamilia.setBounds(400, 197, 72, 25);
		panel.add(lFamilia);
		
		comboFamilia = new JComboBox<Familia>();
		comboFamilia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboFamilia.setBounds(482, 197, 253, 25);
		comboFamilia.setEditable(true);
		GestorFamilia gf=new GestorFamilia();
		List<Familia> familias;
		familias=gf.listar("");
		for (Familia fam:familias)
			comboFamilia.addItem(fam);
		comboFamilia.setSelectedItem(null);
		panel.add(comboFamilia);
		
		tStock = new JTextField();
		tStock.setHorizontalAlignment(SwingConstants.RIGHT);
		tStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tStock.setColumns(10);
		tStock.setBounds(96, 251, 72, 25);
		tStock.setText("0");
		panel.add(tStock);
		
		JLabel lStock = new JLabel("Stock");
		lStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lStock.setBounds(31, 251, 55, 25);
		panel.add(lStock);
		
		tStockMin = new JTextField();
		tStockMin.setHorizontalAlignment(SwingConstants.RIGHT);
		tStockMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tStockMin.setColumns(10);
		tStockMin.setBounds(290, 251, 86, 25);
		tStockMin.setText("0");
		panel.add(tStockMin);
		
		JLabel lStockMin = new JLabel("Stock Min.");
		lStockMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lStockMin.setBounds(201, 251, 70, 25);
		panel.add(lStockMin);
		
		JPanel pFoto = new JPanel();
		tabbedPane.addTab("Imagen", null, pFoto, null);
		pFoto.setLayout(null);
		
		lFoto = new JLabel("");
		lFoto.setBounds(98, 27, 603, 274);
		pFoto.add(lFoto);
		if (art!=null)
			llenaFicha(art);
	}
	
	private void llenaFicha(Articulo art) {
		tCodigo.setText(String.valueOf(art.getCod()));
		tNombre.setText(art.getNombre());
		tCProv.setText(String.valueOf(art.getCodpro()));
		comboProveedor.setSelectedItem(art.getProveedorBean());
		tCoste.setText(formatoeuro.format(art.getCoste()));
		tPrecioMay.setText(formatoeuro.format(art.getPrecioMayorista()));
		tPrecioMin.setText(formatoeuro.format(art.getPrecioMinorista()));
		tIva.setText(formatoPorcentaje.format(art.getIva()));
		tUnidadesCaja.setText(String.valueOf(art.getUnidadesCaja()));
		comboFamilia.setSelectedItem(art.getFamiliaBean());
		tStock.setText(String.valueOf(art.getStock()));
		tStockMin.setText(String.valueOf(art.getStockMinimo()));
		lFoto.setIcon(new ImageIcon(new ImageIcon("src/fotos/"+String.valueOf(art.getCod())+".jpg").getImage().getScaledInstance(603, 274, Image.SCALE_DEFAULT)));
	}
	
	@SuppressWarnings("rawtypes")
	public void EstablecerManejadorVentana(ControladorFichaArticulo manejador) {
		this.addInternalFrameListener(manejador);
		Component[] componentes=panel.getComponents();
		comboFamilia.addFocusListener(manejador);
		for (Component componente:componentes) {
			if (componente.getClass()==JTextField.class)
				componente.addFocusListener(manejador);
			if (componente.getClass()==JComboBox.class)
				((JComboBox) componente).getEditor().getEditorComponent().addFocusListener(manejador);
		}
	}

	public Articulo getArt() {
		return art;
	}

	public JTextField gettCodigo() {
		return tCodigo;
	}

	public JTextField gettNombre() {
		return tNombre;
	}

	public JTextField gettCProv() {
		return tCProv;
	}

	public JTextField gettCoste() {
		return tCoste;
	}

	public JTextField gettPrecioMay() {
		return tPrecioMay;
	}

	public JTextField gettPrecioMin() {
		return tPrecioMin;
	}

	public JTextField gettIva() {
		return tIva;
	}


	public JTextField gettStock() {
		return tStock;
	}

	public JTextField gettStockMin() {
		return tStockMin;
	}

	public JTextField gettUnidadesCaja() {
		return tUnidadesCaja;
	}

	public JComboBox<Familia> getComboFamilia() {
		return comboFamilia;
	}

	public JComboBox<Proveedor> getComboProveedor() {
		return comboProveedor;
	}

	public VentanaPrincipal getV() {
		return v;
	}
	
}
