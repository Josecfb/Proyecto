package vista.clientes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.clientes.ControlaFilaPrecioCli;
import controlador.clientes.ControladorFichaCliente;
import entidades.Cliente;
import entidades.PrecioCliente;
import modelo.persistencia.DaoProvincia;
import util.JTextFieldN;
import util.JTextFieldT;
import util.Utilidades;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Vista de la ventana de la ficha de cliente
 * @author Jose Carlos
 *
 */
public class VFichaCliente extends JInternalFrame {
	private static final long serialVersionUID = 7007272565978130446L;
	private Utilidades u;
	private Cliente cli;
	private boolean modificado;
	private VListadoClientes vlc;
	private JTextField tNumero;
	private JTextFieldT tDireccion;
	private JComboBox<String> tPoblacion;
	private JTextFieldT tProvincia;
	private JTextFieldT tEmail;
	private JTextFieldN tFijo;
	private JTextFieldN tMovil;
	private JTextFieldN tCodPos;
	private JTextFieldN tSubcuenta;
	private JTextFieldT tNif;
	private JPanel pestPrincipal,pestPrecios,pMay,pMin;
	private JComboBox<String> comboTipo;
	private JTextField tNombre;
	private JTextField tApellidos;
	private JCheckBox chkConfirmado;
	private JScrollPane scrollPrecios;
	private JButton bNuevaFila, bBorrar;
	private JPanel panel;
	private  NumberFormat formatoeuro,formatoPorcentaje;
	/**
	 * El constructor recibe el Objeto Cliente y la ventana de listado de clientes
	 * @param cli Objeto Cliente
	 * @param vlc Ventana listado de clientes VListadoClientes
	 */
	public VFichaCliente(Cliente cli,VListadoClientes vlc) {
		this.cli=cli;
		this.vlc=vlc;
		u=new Utilidades();
		modificado=false;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoPorcentaje = NumberFormat.getPercentInstance();
		formatoPorcentaje.setMinimumFractionDigits(2);
		setAutoscrolls(true);
		setBorder(UIManager.getBorder("InternalFrame.border"));
		setBounds(100, 100, 997, 541);
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/clientes.png"));
		ImageIcon icones=new ImageIcon( icon.getImage().getScaledInstance(18, 18, 0));
		setFrameIcon(icones);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		if (cli!=null)
			setTitle("Ficha de "+cli.getNombre()+" "+cli.getApellidos());
		else
			setTitle("Nuevo Cliente");
		
		JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP);
		pestanas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pestanas.setBounds(93, 31, 800, 428);
		getContentPane().add(pestanas);
		
		pestPrincipal = new JPanel();
		pestanas.addTab("Datos Cliente", null, pestPrincipal, null);
		pestPrincipal.setLayout(null);
		
		JLabel lNumero = new JLabel("N\u00FAmero");
		lNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNumero.setBounds(59, 24, 55, 25);
		pestPrincipal.add(lNumero);
		
		tNumero = new JTextField();
		tNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNumero.setColumns(10);
		tNumero.setBounds(124, 24, 86, 25);
		tNumero.setHorizontalAlignment(JTextField.RIGHT);
		tNumero.setFocusable(false);
		pestPrincipal.add(tNumero);
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(49, 156, 66, 25);
		pestPrincipal.add(lDireccion);
		
		tDireccion = new JTextFieldT(40);
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(125, 156, 390, 25);
		pestPrincipal.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(271, 205, 66, 25);
		pestPrincipal.add(lPoblación);
		
		tPoblacion = new JComboBox<String>();
		tPoblacion.setEditable(true);
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPoblacion.setBounds(347, 205, 168, 25);
		pestPrincipal.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(49, 205, 66, 25);
		pestPrincipal.add(lProvincia);
		
		tProvincia = new JTextFieldT(22);
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(125, 205, 134, 25);
		pestPrincipal.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(533, 156, 95, 25);
		pestPrincipal.add(lCodPost);
		
		tEmail = new JTextFieldT(30);
		tEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEmail.setColumns(10);
		tEmail.setBounds(591, 205, 159, 25);
		pestPrincipal.add(tEmail);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(59, 253, 55, 25);
		pestPrincipal.add(lFijo);
		
		tFijo = new JTextFieldN(9,'n');
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(126, 253, 134, 25);
		pestPrincipal.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(277, 253, 47, 25);
		pestPrincipal.add(lMovil);
		
		tMovil = new JTextFieldN(9,'n');
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(334, 253, 159, 25);
		pestPrincipal.add(tMovil);
		
		tCodPos = new JTextFieldN(5,'n');
		tCodPos.setText((String) null);
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(637, 156, 113, 25);
		pestPrincipal.add(tCodPos);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(538, 205, 47, 25);
		pestPrincipal.add(lEMail);
		
		tSubcuenta = new JTextFieldN(11,'n');
		tSubcuenta.setText((String) null);
		tSubcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tSubcuenta.setColumns(10);
		tSubcuenta.setBounds(591, 253, 159, 25);
		tSubcuenta.setHorizontalAlignment(JTextField.RIGHT);
		tSubcuenta.setText("0");
		pestPrincipal.add(tSubcuenta);
		
		JLabel lSubcuent = new JLabel("Sub cta.");
		lSubcuent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSubcuent.setBounds(519, 253, 60, 25);
		pestPrincipal.add(lSubcuent);
		
		JLabel lNif = new JLabel("NIF");
		lNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNif.setBounds(564, 24, 34, 25);
		pestPrincipal.add(lNif);
		
		tNif = new JTextFieldT(9);
		tNif.setText((String) null);
		tNif.setHorizontalAlignment(SwingConstants.RIGHT);
		tNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNif.setColumns(10);
		tNif.setBounds(591, 24, 159, 25);
		pestPrincipal.add(tNif);
		
		comboTipo = new JComboBox<String>();
		comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTipo.setBounds(313, 24, 162, 25);
		comboTipo.addItem("Mayorista");
		comboTipo.addItem("Minorista");
		comboTipo.setEditable(true);
		comboTipo.setSelectedItem(null);
		pestPrincipal.add(comboTipo);
		
		JLabel lTipo = new JLabel("Tipo");
		lTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTipo.setBounds(248, 24, 55, 25);
		pestPrincipal.add(lTipo);
		
		pMay = new JPanel();
		pMay.setBounds(10, 60, 106, 74);
		pMay.setLayout(null);
		pMay.setVisible(true);
		pestPrincipal.add(pMay);
		
		JLabel lNombre_1_2 = new JLabel("Nom. Comercial");
		lNombre_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1_2.setBounds(0, 0, 104, 25);
		pMay.add(lNombre_1_2);
		
		JLabel lNomFis = new JLabel("Nom. Fiscal");
		lNomFis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNomFis.setBounds(11, 48, 90, 25);
		pMay.add(lNomFis);
		
		pMin = new JPanel();
		pMin.setLayout(null);
		pMin.setBounds(10, 60, 106, 74);
		pMin.setVisible(false);
		pestPrincipal.add(pMin);
		
		JLabel lNombre_1 = new JLabel("Nombre");
		lNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1.setBounds(0, 0, 104, 25);
		pMin.add(lNombre_1);
		
		JLabel lNombre_1_1 = new JLabel("Apellidos");
		lNombre_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1_1.setBounds(11, 48, 90, 25);
		pMin.add(lNombre_1_1);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(124, 60, 626, 25);
		pestPrincipal.add(tNombre);
		
		tApellidos = new JTextField();
		tApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tApellidos.setColumns(10);
		tApellidos.setBounds(124, 108, 626, 25);
		pestPrincipal.add(tApellidos);
		
		chkConfirmado = new JCheckBox("Confirmado");
		chkConfirmado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkConfirmado.setBounds(113, 296, 97, 23);
		pestPrincipal.add(chkConfirmado);
		
		pestPrecios = new JPanel();
		pestPrecios.setLayout(null);
		pestanas.addTab("Precios Esp.", null, pestPrecios, null);
		
		scrollPrecios = new JScrollPane();
		scrollPrecios.setBounds(10, 51, 775, 330);
		pestPrecios.add(scrollPrecios);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 87, 40);
		pestPrecios.add(toolBar);
		
		bNuevaFila = new JButton("");
		bNuevaFila.setIcon(new ImageIcon("src/img/nuevafila.png"));
		toolBar.add(bNuevaFila);
		
		bBorrar = new JButton("");
		bBorrar.setBounds(924, 445, 49, 51);
		getContentPane().add(bBorrar);
		bBorrar.setIcon(new ImageIcon(new ImageIcon("src/img/eliminar.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		if (cli!=null) {
			llenaFicha(cli);
			muestraFilasPrecios();
		}
	}
	/**
	 * Rellena la ficha de cliente con los datos de un cliente
	 * @param cli Objeto Cliente
	 */
	private void llenaFicha(Cliente cli) {
		tNumero.setText(String.valueOf(cli.getNumero()));
		if (cli.getTipo()==1) 
			comboTipo.setSelectedItem("Mayorista");
			
		else {
			comboTipo.setSelectedItem("Minorista");
			pMay.setVisible(false);
			pMin.setVisible(true);
		}
		tNif.setText(cli.getNifCif());
		tNombre.setText(cli.getNombre());
		tApellidos.setText(cli.getApellidos());
		tCodPos.setText(cli.getCodPost());
		tFijo.setText(cli.getTelefonoFijo());
		tMovil.setText(cli.getTelefonoMovil());
		tDireccion.setText(cli.getDireccion());
		tEmail.setText(cli.getEmail());
		tSubcuenta.setText(String.valueOf(cli.getNumCuentaContable()));
		tProvincia.setText(cli.getProvincia());
		chkConfirmado.setSelected(cli.isConfirmado());
		DaoProvincia dp=new DaoProvincia();
		for (String poblacion:dp.nomPoblaciones(cli.getCodPost()))
			tPoblacion.addItem(poblacion);
		tPoblacion.setSelectedItem(cli.getPoblacion());
	}
	/**
	 * Rellena las filas con los precios especiales para el cliente
	 */
	private void muestraFilasPrecios() {
		List<PrecioCliente> lPrecios=cli.getPreciosClientes();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,lPrecios.size()*30));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		scrollPrecios.setViewportView(panel);
		for (PrecioCliente filaPre:lPrecios) {
			System.out.println(lPrecios.size());
			VFilaPrecioCliente vFila=new VFilaPrecioCliente(this,cli);
			ControlaFilaPrecioCli cfpc=new ControlaFilaPrecioCli(vFila);
			vFila.establecerControlador(cfpc);
			vFila.setPreferredSize(new Dimension(775,30));
			vFila.gettCodArt().setText(String.valueOf(filaPre.getArticuloBean().getCod()));
			vFila.getComboArt().setSelectedItem(filaPre.getArticuloBean());
			vFila.gettPrecio().setText(formatoeuro.format(filaPre.getPrecio()));
			double precioReal=cli.getTipo()==0?filaPre.getArticuloBean().getPrecioMinorista():filaPre.getArticuloBean().getPrecioMayorista();
			vFila.getlPrecioReal().setText(formatoeuro.format(precioReal));
			vFila.gettPorcent().setText(formatoPorcentaje.format((precioReal-filaPre.getPrecio())/precioReal));
			panel.add(vFila);
		}
	}
	/**
	 * Establece el controlador de la ventana de cliente
	 * @param cfc ControladorFichaCliente
	 */
	@SuppressWarnings("rawtypes")
	public void establecerManejadorVentana(ControladorFichaCliente cfc) {
		this.addInternalFrameListener(cfc);
		Component[] componentes=pestPrincipal.getComponents();
		for (Component componente:componentes) 
			if (componente.getClass()==JComboBox.class)
				((JComboBox) componente).getEditor().getEditorComponent().addFocusListener(cfc);
		u.addFocusKey(pestPrincipal,cfc,cfc);
		bNuevaFila.addActionListener(cfc);
		bBorrar.addActionListener(cfc);
		chkConfirmado.addActionListener(cfc);
		comboTipo.addItemListener(cfc);
		tPoblacion.addItemListener(cfc);
	}
	/**
	 * Crea una nueva fila para precio de cliente
	 */
	public void nuevaFilaPrecio() {
		PrecioCliente fil = new PrecioCliente();
		fil.setClienteBean(cli);
		VFilaPrecioCliente vFilaPrecio=new VFilaPrecioCliente(this,cli);
		ControlaFilaPrecioCli controla=new ControlaFilaPrecioCli(vFilaPrecio);
		vFilaPrecio.establecerControlador(controla);
		panel.setPreferredSize(new Dimension(710,panel.getHeight()+23));
		vFilaPrecio.setPreferredSize(new Dimension(775,30));
		panel.add(vFilaPrecio);
		panel.updateUI();
	}

	public Cliente getCli() {
		return cli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField gettNumero() {
		return tNumero;
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

	public JTextField gettEmail() {
		return tEmail;
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

	public JTextField gettCodPos() {
		return tCodPos;
	}

	public JTextField gettSubcuenta() {
		return tSubcuenta;
	}

	public JTextField gettNif() {
		return tNif;
	}

	public JTextField gettApellidos() {
		return tApellidos;
	}

	public JCheckBox getChkConfirmado() {
		return chkConfirmado;
	}

	public JComboBox<String> getComboTipo() {
		return comboTipo;
	}

	public JPanel getpMay() {
		return pMay;
	}

	public JPanel getpMin() {
		return pMin;
	}

	public JButton getbNuevaFila() {
		return bNuevaFila;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getbBorrar() {
		return bBorrar;
	}

	public VListadoClientes getVlc() {
		return vlc;
	}
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
}