package vista;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import controlador.ControladorFichaProveedor;
import model.Proveedor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FichaProveedor extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4232463625349427321L;
	private JTextField tNumero;
	private JTextField tDireccion;
	private JTextField tPoblacion;
	private JTextField tProvincia;
	private JTextField tCodPos;
	private JTextField tFijo;
	private JTextField tMovil;
	private JTextField tNombre;
	private JTextField tEMail;
	private JTextField tSubcuenta;
	private JTextField tNif;



	public FichaProveedor(Proveedor pro) {
		setBounds(100, 100, 997, 439);
		getContentPane().setLayout(null);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ficha Proveedor");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(99, 11, 800, 374);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
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
		tNumero.setText(String.valueOf(pro.getNumero()));
		tNumero.setFocusable(false);
		panel.add(tNumero);
		
		JLabel lNombre_1 = new JLabel("Nombre");
		lNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1.setBounds(271, 54, 55, 25);
		panel.add(lNombre_1);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(347, 47, 395, 25);
		tNombre.setText(pro.getNombre());
		panel.add(tNombre);
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(99, 101, 66, 25);
		panel.add(lDireccion);
		
		tDireccion = new JTextField();
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(175, 101, 567, 25);
		tDireccion.setText(pro.getDireccion());
		panel.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(99, 156, 66, 25);
		panel.add(lPoblación);
		
		tPoblacion = new JTextField();
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPoblacion.setColumns(10);
		tPoblacion.setBounds(175, 156, 248, 25);
		tPoblacion.setText(pro.getPoblacion());
		panel.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(443, 156, 66, 25);
		panel.add(lProvincia);
		
		tProvincia = new JTextField();
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(519, 156, 223, 25);
		tProvincia.setText(pro.getProvincia());
		panel.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(70, 211, 95, 25);
		panel.add(lCodPost);
		
		tCodPos = new JTextField();
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(175, 211, 111, 25);
		tCodPos.setText(pro.getCodPost());
		panel.add(tCodPos);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(308, 211, 55, 25);
		panel.add(lFijo);
		
		tFijo = new JTextField();
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(375, 211, 134, 25);
		tFijo.setText(pro.getTelefonoFijo());
		panel.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(529, 211, 47, 25);
		panel.add(lMovil);
		
		tMovil = new JTextField();
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(583, 211, 159, 25);
		tMovil.setText(pro.getTelefonoMovil());
		panel.add(tMovil);
		
		tEMail = new JTextField();
		tEMail.setText((String) null);
		tEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEMail.setColumns(10);
		tEMail.setBounds(175, 268, 200, 25);
		tEMail.setText(pro.getEmail());
		panel.add(tEMail);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(118, 268, 47, 25);
		panel.add(lEMail);
		
		tSubcuenta = new JTextField();
		tSubcuenta.setText((String) null);
		tSubcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tSubcuenta.setColumns(10);
		tSubcuenta.setBounds(445, 268, 104, 25);
		tSubcuenta.setHorizontalAlignment(JTextField.RIGHT);
		tSubcuenta.setText(String.valueOf(pro.getNumCuentaContable()));
		panel.add(tSubcuenta);
		
		JLabel lSubcuent = new JLabel("Sub cta.");
		lSubcuent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSubcuent.setBounds(385, 268, 60, 25);
		panel.add(lSubcuent);
		
		JLabel lNif = new JLabel("NIF");
		lNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNif.setBounds(556, 268, 34, 25);
		panel.add(lNif);
		
		tNif = new JTextField();
		tNif.setText((String) null);
		tNif.setHorizontalAlignment(SwingConstants.RIGHT);
		tNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNif.setColumns(10);
		tNif.setBounds(583, 268, 159, 25);
		tNif.setText(pro.getNif());
		panel.add(tNif);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Facturas", null, panel_1, null);
		
	}
	public void EstablecerManejadorVentana(ControladorFichaProveedor manejador) {
		this.addInternalFrameListener(manejador);
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

	
	
}
