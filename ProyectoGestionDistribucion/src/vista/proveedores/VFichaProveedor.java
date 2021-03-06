package vista.proveedores;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import modelo.persistencia.DaoProvincia;
import util.JTextFieldN;
import util.JTextFieldT;
import util.Utilidades;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import controlador.proveedores.ControladorFichaProveedor;
import entidades.Proveedor;
/**
 * Ventana de proveedor
 * @author Jose Carlos
 *
 */
public class VFichaProveedor extends JInternalFrame {

	private static final long serialVersionUID = -4232463625349427321L;
	private Utilidades u;
	private VListadoProveedores v;
	private Proveedor pro;
	private JTextField tNumero;
	private JTextFieldT tDireccion;
	private JComboBox<String> tPoblacion;
	private JTextFieldT tProvincia;
	private JTextFieldN tCodPos;
	private JTextFieldN tFijo;
	private JTextFieldN tMovil;
	private JTextFieldT tNombre;
	private JTextFieldT tEMail;
	private JTextFieldN tSubcuenta;
	private JTextFieldT tNif;
	private JPanel panel;
	private JButton bBorrar;
	private boolean modificado;
	/**
	 * El constructor recibe el objeto entidad Proveedor y la ventana del listado de proveedores VListadoProveedores
	 * @param pro objeto entidad Proveedor
	 * @param v ventana del listado de proveedores VListadoProveedores
	 */
	public VFichaProveedor(Proveedor pro,VListadoProveedores v) {
		this.pro=pro;
		this.v=v;
		modificado=false;
		u=new Utilidades();
		setAutoscrolls(true);
		setBorder(UIManager.getBorder("InternalFrame.border"));
		setBounds(100, 100, 997, 481);
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/proveedores.png"));
		ImageIcon icones=new ImageIcon( icon.getImage().getScaledInstance(18, 18, 0));
		setFrameIcon(icones);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		if (pro!=null)
			setTitle("Ficha de "+pro.getNombre());
		else
			setTitle("Nuevo Proveedor");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(99, 11, 800, 374);
		getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Datos Proveedor", null, panel, null);
		panel.setLayout(null);
		
		JLabel lNumero = new JLabel("N\u00FAmero");
		lNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNumero.setBounds(110, 47, 55, 25);
		panel.add(lNumero);
		
		tNumero = new JTextField();
		tNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNumero.setColumns(10);
		tNumero.setBounds(175, 47, 86, 25);
		tNumero.setHorizontalAlignment(JTextField.RIGHT);
		tNumero.setFocusable(false);
		panel.add(tNumero);
		
		JLabel lNombre_1 = new JLabel("Nombre");
		lNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1.setBounds(282, 47, 55, 25);
		panel.add(lNombre_1);
		
		tNombre = new JTextFieldT(30);
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(347, 47, 395, 25);
		panel.add(tNombre);
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(99, 102, 66, 25);
		panel.add(lDireccion);
		
		tDireccion = new JTextFieldT(40);
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(175, 102, 567, 25);
		panel.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(418, 210, 66, 25);
		panel.add(lPoblación);
		
		tPoblacion = new JComboBox<String>();
		tPoblacion.setEditable(true);
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPoblacion.setBounds(494, 210, 248, 25);
		panel.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(99, 212, 66, 25);
		panel.add(lProvincia);
		
		tProvincia = new JTextFieldT(22);
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(175, 210, 223, 25);
		panel.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(70, 157, 95, 25);
		panel.add(lCodPost);
		
		tCodPos = new JTextFieldN(9,'n');
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(175, 157, 111, 25);
		panel.add(tCodPos);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(310, 157, 55, 25);
		panel.add(lFijo);
		
		tFijo = new JTextFieldN(9,'n');
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(375, 157, 134, 25);
		panel.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(529, 154, 47, 25);
		panel.add(lMovil);
		
		tMovil = new JTextFieldN(9,'n');
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(583, 157, 159, 25);
		panel.add(tMovil);
		
		tEMail = new JTextFieldT(30);
		tEMail.setText((String) null);
		tEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEMail.setColumns(10);
		tEMail.setBounds(175, 267, 200, 25);
		panel.add(tEMail);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(118, 267, 47, 25);
		panel.add(lEMail);
		
		tSubcuenta = new JTextFieldN(11,'n');
		tSubcuenta.setText((String) null);
		tSubcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tSubcuenta.setColumns(10);
		tSubcuenta.setBounds(444, 267, 104, 25);
		tSubcuenta.setHorizontalAlignment(JTextField.RIGHT);
		tSubcuenta.setText("0");
		panel.add(tSubcuenta);
		
		JLabel lSubcuent = new JLabel("Sub cta.");
		lSubcuent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSubcuent.setBounds(385, 267, 60, 25);
		panel.add(lSubcuent);
		
		JLabel lNif = new JLabel("NIF");
		lNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNif.setBounds(556, 267, 34, 25);
		panel.add(lNif);
		
		tNif = new JTextFieldT(9);
		tNif.setText((String) null);
		tNif.setHorizontalAlignment(SwingConstants.RIGHT);
		tNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNif.setColumns(10);
		tNif.setBounds(583, 267, 159, 25);
		panel.add(tNif);
		bBorrar = new JButton("");
		bBorrar.setBounds(924, 385, 49, 51);
		getContentPane().add(bBorrar);
		bBorrar.setIcon(new ImageIcon(new ImageIcon("src/img/eliminar.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		if (pro!=null)
			llenaFicha(pro);
		
	}
	/**
	 * Rellena los campos de la ventana con los datos del proveedor
	 * @param pro objeto entidad Proveedor
	 */
	public void llenaFicha(Proveedor pro) {
		tNumero.setText(String.valueOf(pro.getNumero()));
		tNombre.setText(pro.getNombre());
		tDireccion.setText(pro.getDireccion());
		
		tProvincia.setText(pro.getProvincia());
		tCodPos.setText(pro.getCodPost());
		DaoProvincia dp=new DaoProvincia();
		for (String poblacion:dp.nomPoblaciones(pro.getCodPost()))
			tPoblacion.addItem(poblacion);
		tPoblacion.setSelectedItem(pro.getPoblacion());
		tFijo.setText(pro.getTelefonoFijo());
		tMovil.setText(pro.getTelefonoMovil());
		tEMail.setText(pro.getEmail());
		tSubcuenta.setText(String.valueOf(pro.getNumCuentaContable()));
		tNif.setText(pro.getNif());
	}
	/**
	 * Establece el controlador de la ventana de ficha de proveedor
	 * @param manejador ControladorFichaProveedor
	 */
	public void EstablecerManejadorVentana(ControladorFichaProveedor manejador) {
		this.addInternalFrameListener(manejador);
		tProvincia.addFocusListener(manejador);
		tPoblacion.addItemListener(manejador);
		u.addFocusKey(panel, manejador, manejador);
		bBorrar.addActionListener(manejador);
	}
	public JTextField gettDireccion() {
		return tDireccion;
	}
	public JComboBox<String> gettPoblacion() {
		return tPoblacion;
	}
	public JTextField gettProvincia() {
		return tProvincia;
	}
	public JTextField gettCodPos() {
		return tCodPos;
	}
	public JTextField gettFijo() {
		return tFijo;
	}
	public JTextField gettMovil() {
		return tMovil;
	}
	public JTextField gettNombre() {
		return tNombre;
	}
	public JTextField gettEMail() {
		return tEMail;
	}
	public JTextField gettSubcuenta() {
		return tSubcuenta;
	}
	public JTextField gettNif() {
		return tNif;
	}
	public JTextField gettNumero() {
		return tNumero;
	}

	public Proveedor getPro() {
		return pro;
	}

	public VListadoProveedores getVListProv() {
		return v;
	}

	public JButton getbBorrar() {
		return bBorrar;
	}
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	
	
}
