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
import entidades.Articulo;
import entidades.Familia;
import entidades.Proveedor;
import modelo.negocio.GestorFamilia;
import modelo.negocio.GestorProveedor;
import util.JTextFieldN;
import util.JTextFieldT;
import util.Utilidades;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * Vista de la ventana de la ficha de un artículo
 * @author Jose Carlos
 *
 */
public class VFichaArticulo extends JInternalFrame {

	private static final long serialVersionUID = 3871990475316407616L;
	private Utilidades u;
	private boolean modificado;
	private VListadoArticulos vla;
	private JTextField tCodigo;
	private JTextFieldT tNombre;
	private JTextFieldT tCProv;
	private JTextFieldN tCoste;
	private JTextFieldN tPrecioMay;
	private JTextFieldN tPrecioMin;
	private JTextFieldN tIva;
	private JTextFieldN tUnidadesCaja;
	private JTextFieldN tStock;
	private JTextFieldN tStockMin;
	private JComboBox<Familia> comboFamilia;
	private JComboBox<Proveedor> comboProveedor;
	private JButton bBorrar;
	private NumberFormat formatoeuro, formatoPorcentaje;
	private Articulo art;
	private JLabel lFoto;
	private JPanel panel;
	private JTextField tReservados;
	/**
	 * El constructor recibe el objeto Articulo y VListadoArticulos
	 * @param art Objeto Articulo
	 * @param vla Vista de la ventana VListadoArticulos
	 */
	public VFichaArticulo(Articulo art,VListadoArticulos vla) {
		this.art=art;
		this.vla=vla;
		u=new Utilidades();
		modificado=false;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoPorcentaje = NumberFormat.getPercentInstance();
		formatoPorcentaje.setMinimumFractionDigits(2);
		setBounds(100, 100, 870, 490);
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/articulos.png"));
		ImageIcon icones=new ImageIcon( icon.getImage().getScaledInstance(18, 18, 0));
		setFrameIcon(icones);
		getContentPane().setLayout(null);
		if (art!=null)
			setTitle("Ficha de "+art.getNombre());
		else
			setTitle("Nuevo Artículo");
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(42, 11, 772, 373);
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
		
		tNombre = new JTextFieldT(50);
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(298, 40, 437, 25);
		panel.add(tNombre);
		
		tCProv = new JTextFieldT(11);
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
		
		tCoste = new JTextFieldN(5,'m');
		tCoste.setHorizontalAlignment(SwingConstants.RIGHT);
		tCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCoste.setColumns(10);
		tCoste.setBounds(96, 146, 134, 25);
		tCoste.setText("0,00 €");
		panel.add(tCoste);
		
		tPrecioMay = new JTextFieldN(5,'m');
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
		
		tPrecioMin = new JTextFieldN(5,'m');
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
		
		tIva = new JTextFieldN(5,'p');
		tIva.setHorizontalAlignment(SwingConstants.RIGHT);
		tIva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tIva.setColumns(10);
		tIva.setBounds(96, 197, 72, 25);
		tIva.setText("0%");
		panel.add(tIva);
		
		tUnidadesCaja = new JTextFieldN(4,'n');
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
		lFamilia.setBounds(437, 197, 49, 25);
		panel.add(lFamilia);
		
		comboFamilia = new JComboBox<Familia>();
		comboFamilia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboFamilia.setBounds(505, 197, 230, 25);
		comboFamilia.setEditable(true);
		GestorFamilia gf=new GestorFamilia();
		List<Familia> familias;
		familias=gf.listar("");
		for (Familia fam:familias)
			comboFamilia.addItem(fam);
		comboFamilia.setSelectedItem(null);
		panel.add(comboFamilia);
		
		tStock = new JTextFieldN(4,'n');
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
		
		tStockMin = new JTextFieldN(4,'n');
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
		
		JLabel lblReservados = new JLabel("Reservados");
		lblReservados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReservados.setBounds(408, 251, 78, 25);
		panel.add(lblReservados);
		
		tReservados = new JTextField();
		tReservados.setText("0");
		tReservados.setHorizontalAlignment(SwingConstants.RIGHT);
		tReservados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tReservados.setColumns(10);
		tReservados.setBounds(505, 251, 86, 25);
		panel.add(tReservados);
		
		JPanel pFoto = new JPanel();
		tabbedPane.addTab("Imagen", null, pFoto, null);
		pFoto.setLayout(null);
		
		lFoto = new JLabel("");
		lFoto.setBounds(98, 27, 603, 274);
		pFoto.add(lFoto);
		bBorrar = new JButton("");
		bBorrar.setBounds(764, 395, 49, 51);
		getContentPane().add(bBorrar);
		bBorrar.setIcon(new ImageIcon(new ImageIcon("src/img/eliminar.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		if (art!=null)
			llenaFicha(art);
	}
	/**
	 * Rellena los campos de la ficha de artículo
	 * @param art Objeto Articulo
	 */
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
		tReservados.setText(String.valueOf(art.getReservados()));
		lFoto.setIcon(new ImageIcon(new ImageIcon("src/fotos/"+String.valueOf(art.getCod())+".jpg").getImage().getScaledInstance(603, 274, Image.SCALE_DEFAULT)));
	}
	/**
	 * Establece el controlador de la ventana dela ficha de artículo
	 * @param manejador Objeto ControladorFichaArticulo
	 */
	
	@SuppressWarnings("rawtypes")
	public void EstablecerManejadorVentana(ControladorFichaArticulo manejador) {
		this.addInternalFrameListener(manejador);
		bBorrar.addActionListener(manejador);
		Component[] componentes=panel.getComponents();
		for (Component componente:componentes) 
		if (componente.getClass()==JComboBox.class) {
			((JComboBox) componente).getEditor().getEditorComponent().addFocusListener(manejador);
			((JComboBox) componente).addItemListener(manejador);
		}
		u.addFocusKey(panel,manejador,manejador);

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

	public JTextField gettReservados() {
		return tReservados;
	}

	public JComboBox<Familia> getComboFamilia() {
		return comboFamilia;
	}

	public JComboBox<Proveedor> getComboProveedor() {
		return comboProveedor;
	}
	
	public JButton getbBorrar() {
		return bBorrar;
	}

	public VListadoArticulos getVListadoArt() {
		return vla;
	}
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
}
