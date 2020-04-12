package vista.clientes;

import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.clientes.ControladorFichaCliente;
import model.Cliente;
import javax.swing.JComboBox;

public class VFichaCliente extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7007272565978130446L;
	private Cliente cli;
	private JTextField tNumero;
	private JTextField tDireccion;
	private JTextField tPoblacion;
	private JTextField tProvincia;
	private JTextField tEmail;
	private JTextField tFijo;
	private JTextField tMovil;
	private JTextField tNombre;
	private JTextField tCodPos;
	private JTextField tSubcuenta;
	private JTextField tNif;
	private JTextField tApellidos;
	private JTextField tNomComercial;
	private JTextField tNomFiscal;
	private JComboBox<String> comboTipo;


	public VFichaCliente(Cliente cli) {
		this.cli=cli;
		setAutoscrolls(true);
		setBorder(UIManager.getBorder("InternalFrame.border"));
		setBounds(100, 100, 997, 512);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ficha Cliente");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(25, 11, 800, 428);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Datos Cliente", null, panel, null);
		panel.setLayout(null);
		
		JLabel lNumero = new JLabel("N\u00FAmero");
		lNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNumero.setBounds(59, 24, 55, 25);
		panel.add(lNumero);
		
		tNumero = new JTextField();
		tNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNumero.setColumns(10);
		tNumero.setBounds(124, 24, 86, 25);
		tNumero.setHorizontalAlignment(JTextField.RIGHT);
		tNumero.setFocusable(false);
		panel.add(tNumero);
		
		JLabel lNombre_1 = new JLabel("Nombre");
		lNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1.setBounds(59, 72, 55, 25);
		panel.add(lNombre_1);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(124, 72, 247, 25);
		panel.add(tNombre);
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(49, 219, 66, 25);
		panel.add(lDireccion);
		
		tDireccion = new JTextField();
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(125, 219, 390, 25);
		panel.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(49, 268, 66, 25);
		panel.add(lPoblación);
		
		tPoblacion = new JTextField();
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPoblacion.setColumns(10);
		tPoblacion.setBounds(125, 268, 168, 25);
		panel.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(305, 268, 66, 25);
		panel.add(lProvincia);
		
		tProvincia = new JTextField();
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(381, 268, 134, 25);
		panel.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(533, 219, 95, 25);
		panel.add(lCodPost);
		
		tEmail = new JTextField();
		tEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEmail.setColumns(10);
		tEmail.setBounds(591, 268, 159, 25);
		panel.add(tEmail);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(59, 316, 55, 25);
		panel.add(lFijo);
		
		tFijo = new JTextField();
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(126, 316, 134, 25);
		panel.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(280, 316, 47, 25);
		panel.add(lMovil);
		
		tMovil = new JTextField();
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(334, 316, 159, 25);
		panel.add(tMovil);
		
		tCodPos = new JTextField();
		tCodPos.setText((String) null);
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(637, 219, 113, 25);
		panel.add(tCodPos);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(538, 268, 47, 25);
		panel.add(lEMail);
		
		tSubcuenta = new JTextField();
		tSubcuenta.setText((String) null);
		tSubcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tSubcuenta.setColumns(10);
		tSubcuenta.setBounds(591, 316, 159, 25);
		tSubcuenta.setHorizontalAlignment(JTextField.RIGHT);
		tSubcuenta.setText("0");
		panel.add(tSubcuenta);
		
		JLabel lSubcuent = new JLabel("Sub cta.");
		lSubcuent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSubcuent.setBounds(519, 316, 60, 25);
		panel.add(lSubcuent);
		
		JLabel lNif = new JLabel("NIF");
		lNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNif.setBounds(564, 24, 34, 25);
		panel.add(lNif);
		
		tNif = new JTextField();
		tNif.setText((String) null);
		tNif.setHorizontalAlignment(SwingConstants.RIGHT);
		tNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNif.setColumns(10);
		tNif.setBounds(591, 24, 159, 25);
		panel.add(tNif);
		
		comboTipo = new JComboBox<String>();
		comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTipo.setBounds(313, 24, 162, 25);
		comboTipo.addItem("Mayorista");
		comboTipo.addItem("Minorista");
		comboTipo.setEditable(true);
		comboTipo.setSelectedItem(null);
		panel.add(comboTipo);
		
		JLabel lTipo = new JLabel("Tipo");
		lTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTipo.setBounds(248, 24, 55, 25);
		panel.add(lTipo);
		
		tApellidos = new JTextField();
		tApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tApellidos.setColumns(10);
		tApellidos.setBounds(451, 72, 299, 25);
		panel.add(tApellidos);
		
		JLabel lNombre_1_1 = new JLabel("Apellidos");
		lNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1_1.setBounds(383, 72, 55, 25);
		panel.add(lNombre_1_1);
		
		JLabel lNombre_1_2 = new JLabel("Nom. Comercial");
		lNombre_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1_2.setBounds(10, 123, 104, 25);
		panel.add(lNombre_1_2);
		
		tNomComercial = new JTextField();
		tNomComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNomComercial.setColumns(10);
		tNomComercial.setBounds(124, 123, 626, 25);
		panel.add(tNomComercial);
		
		JLabel lNomFis = new JLabel("Nom. Fiscal");
		lNomFis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNomFis.setBounds(21, 171, 90, 25);
		panel.add(lNomFis);
		
		tNomFiscal = new JTextField();
		tNomFiscal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNomFiscal.setColumns(10);
		tNomFiscal.setBounds(124, 171, 626, 25);
		panel.add(tNomFiscal);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Pedidos", null, panel_1, null);
		if (cli!=null)
			llenaFicha(cli);
	}
	
	private void llenaFicha(Cliente cli) {
		tNumero.setText(String.valueOf(cli.getNumero()));
		if (cli.getTipo()==1)
			comboTipo.setSelectedItem("Mayorista");
		else
			comboTipo.setSelectedItem("Mayorista");
		tNif.setText(cli.getNifCif());
		tNombre.setText(cli.getNombre());
		tApellidos.setText(cli.getApellidos());
		tCodPos.setText(cli.getCodPost());
		tFijo.setText(cli.getTelefonoFijo());
		tMovil.setText(cli.getTelefonoMovil());
		tNomComercial.setText(cli.getNombreComercial());
		tNomFiscal.setText(cli.getNombreFiscal());
		tDireccion.setText(cli.getDireccion());
		tEmail.setText(cli.getEmail());
		tSubcuenta.setText(String.valueOf(cli.getNumCuentaContable()));
		tProvincia.setText(cli.getProvincia());
		tPoblacion.setText(cli.getPoblacion());
	}
	
	public void establecerManejadorVentana(ControladorFichaCliente cfc) {
		this.addInternalFrameListener(cfc);
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

	public JTextField gettPoblacion() {
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

	public JTextField gettNomComercial() {
		return tNomComercial;
	}

	public JTextField gettNomFiscal() {
		return tNomFiscal;
	}

	public JComboBox<String> getComboTipo() {
		return comboTipo;
	}
	
	
}