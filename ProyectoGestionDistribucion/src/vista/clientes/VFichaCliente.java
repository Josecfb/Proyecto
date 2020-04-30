package vista.clientes;

import java.awt.Component;
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
import modelo.persistencia.DaoProvincia;

import javax.swing.JComboBox;

public class VFichaCliente extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7007272565978130446L;
	private Cliente cli;
	private JTextField tNumero;
	private JTextField tDireccion;
	private JComboBox<String> tPoblacion;
	private JTextField tProvincia;
	private JTextField tEmail;
	private JTextField tFijo;
	private JTextField tMovil;
	private JTextField tCodPos;
	private JTextField tSubcuenta;
	private JTextField tNif;
	private JPanel panel,pMay,pMin;
	private JComboBox<String> comboTipo;
	private JTextField tNombre;
	private JTextField tApellidos;


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
		
		panel = new JPanel();
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
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(49, 156, 66, 25);
		panel.add(lDireccion);
		
		tDireccion = new JTextField();
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(125, 156, 390, 25);
		panel.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(271, 205, 66, 25);
		panel.add(lPoblación);
		
		tPoblacion = new JComboBox<String>();
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPoblacion.setBounds(347, 205, 168, 25);
		panel.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(49, 205, 66, 25);
		panel.add(lProvincia);
		
		tProvincia = new JTextField();
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(125, 205, 134, 25);
		panel.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(533, 156, 95, 25);
		panel.add(lCodPost);
		
		tEmail = new JTextField();
		tEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEmail.setColumns(10);
		tEmail.setBounds(591, 205, 159, 25);
		panel.add(tEmail);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(59, 253, 55, 25);
		panel.add(lFijo);
		
		tFijo = new JTextField();
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(126, 253, 134, 25);
		panel.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(277, 253, 47, 25);
		panel.add(lMovil);
		
		tMovil = new JTextField();
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(334, 253, 159, 25);
		panel.add(tMovil);
		
		tCodPos = new JTextField();
		tCodPos.setText((String) null);
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(637, 156, 113, 25);
		panel.add(tCodPos);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(538, 205, 47, 25);
		panel.add(lEMail);
		
		tSubcuenta = new JTextField();
		tSubcuenta.setText((String) null);
		tSubcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tSubcuenta.setColumns(10);
		tSubcuenta.setBounds(591, 253, 159, 25);
		tSubcuenta.setHorizontalAlignment(JTextField.RIGHT);
		tSubcuenta.setText("0");
		panel.add(tSubcuenta);
		
		JLabel lSubcuent = new JLabel("Sub cta.");
		lSubcuent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSubcuent.setBounds(519, 253, 60, 25);
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
		
		pMay = new JPanel();
		pMay.setBounds(10, 60, 106, 74);
		pMay.setLayout(null);
		pMay.setVisible(true);
		panel.add(pMay);
		
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
		panel.add(pMin);
		
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
		panel.add(tNombre);
		
		tApellidos = new JTextField();
		tApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tApellidos.setColumns(10);
		tApellidos.setBounds(124, 108, 626, 25);
		panel.add(tApellidos);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Pedidos", null, panel_1, null);
		if (cli!=null)
			llenaFicha(cli);
	}
	
	private void llenaFicha(Cliente cli) {
		tNumero.setText(String.valueOf(cli.getNumero()));
		if (cli.getTipo()==1) 
			comboTipo.setSelectedItem("Mayorista");
			
		else {
			comboTipo.setSelectedItem("Mayorista");
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
		DaoProvincia dp=new DaoProvincia();
		for (String poblacion:dp.nomPoblaciones(cli.getCodPost()))
			tPoblacion.addItem(poblacion);
		tPoblacion.setSelectedItem(cli.getPoblacion());
	}
	
	@SuppressWarnings("rawtypes")
	public void establecerManejadorVentana(ControladorFichaCliente cfc) {
		this.addInternalFrameListener(cfc);
		Component[] componentes=panel.getComponents();
		for (Component componente:componentes) {
			if (componente.getClass()==JTextField.class) {
				componente.addFocusListener(cfc);
				componente.addKeyListener(cfc);
			}
			comboTipo.getEditor().getEditorComponent().addFocusListener(cfc);
			if (componente.getClass()==JComboBox.class)
				((JComboBox) componente).getEditor().getEditorComponent().addFocusListener(cfc);
		}
		comboTipo.getEditor().getEditorComponent().addFocusListener(cfc);
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

	public JComboBox<String> getComboTipo() {
		return comboTipo;
	}

	public JPanel getpMay() {
		return pMay;
	}

	public JPanel getpMin() {
		return pMin;
	}
}