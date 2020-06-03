package vista.empresa;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import controlador.config.ControladorFichaConfig;
import entidades.Datosempresa;

public class VFichaEmpresa extends JInternalFrame {
	/**
	 * Ventana con laficha de datos de la empresa
	 */
	private static final long serialVersionUID = -4232463625349427321L;

	private Datosempresa dat;
	private JTextField tNombre;
	private JTextField tDireccion;
	private JTextField tProvincia;
	private JTextField tCodPos;
	private JTextField tFijo;
	private JTextField tMovil;
	private JTextField tEmail;
	private JTextField tNif;
	private JTextField tPoblacion;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField tColor;
	/**
	 * El constructor recibe el objeto entidad Datosempresa
	 * @param dat objeto entidad Datosempresa
	 */
	public VFichaEmpresa(Datosempresa dat) {
		this.dat=dat;
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setAutoscrolls(true);
		setBorder(UIManager.getBorder("InternalFrame.border"));
		setBounds(100, 100, 997, 481);
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/config.png"));
		ImageIcon icones=new ImageIcon( icon.getImage().getScaledInstance(18, 18, 0));
		setFrameIcon(icones);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(45, 32, 847, 365);
		getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Datos Empresa", null, panel, null);
		
		JLabel lNombre_1 = new JLabel("Nombre");
		lNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNombre_1.setBounds(99, 47, 66, 25);
		panel.add(lNombre_1);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNombre.setColumns(10);
		tNombre.setBounds(175, 47, 567, 25);
		panel.add(tNombre);
		
		JLabel lDireccion = new JLabel("Direcci\u00F3n");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lDireccion.setBounds(99, 102, 66, 25);
		panel.add(lDireccion);
		
		tDireccion = new JTextField();
		tDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDireccion.setColumns(10);
		tDireccion.setBounds(175, 102, 567, 25);
		panel.add(tDireccion);
		
		JLabel lPoblación = new JLabel("Poblaci\u00F3n");
		lPoblación.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPoblación.setBounds(418, 210, 66, 25);
		panel.add(lPoblación);
		
		tPoblacion = new JTextField();
		tPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tPoblacion.setBounds(494, 210, 248, 25);
		panel.add(tPoblacion);
		
		JLabel lProvincia = new JLabel("Provincia");
		lProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProvincia.setBounds(99, 212, 66, 25);
		panel.add(lProvincia);
		
		tProvincia = new JTextField();
		tProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tProvincia.setColumns(10);
		tProvincia.setBounds(175, 210, 223, 25);
		panel.add(tProvincia);
		
		JLabel lCodPost = new JLabel("C\u00F3digo Postal");
		lCodPost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lCodPost.setBounds(70, 157, 95, 25);
		panel.add(lCodPost);
		
		tCodPos = new JTextField();
		tCodPos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodPos.setColumns(10);
		tCodPos.setBounds(175, 157, 111, 25);
		panel.add(tCodPos);
		
		JLabel lFijo = new JLabel("T. Fijo");
		lFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFijo.setBounds(310, 157, 55, 25);
		panel.add(lFijo);
		
		tFijo = new JTextField();
		tFijo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFijo.setColumns(10);
		tFijo.setBounds(375, 157, 134, 25);
		panel.add(tFijo);
		
		JLabel lMovil = new JLabel("M\u00F3vil");
		lMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lMovil.setBounds(529, 154, 47, 25);
		panel.add(lMovil);
		
		tMovil = new JTextField();
		tMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tMovil.setColumns(10);
		tMovil.setBounds(583, 157, 159, 25);
		panel.add(tMovil);
		
		tEmail = new JTextField();
		tEmail.setText((String) null);
		tEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tEmail.setColumns(10);
		tEmail.setBounds(175, 267, 200, 25);
		panel.add(tEmail);
		
		JLabel lEMail = new JLabel("Email");
		lEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEMail.setBounds(118, 267, 47, 25);
		panel.add(lEMail);
		
		JLabel lNif = new JLabel("NIF");
		lNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNif.setBounds(556, 267, 34, 25);
		panel.add(lNif);
		
		tNif = new JTextField();
		tNif.setText((String) null);
		tNif.setHorizontalAlignment(SwingConstants.RIGHT);
		tNif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tNif.setColumns(10);
		tNif.setBounds(583, 267, 159, 25);
		panel.add(tNif);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		panel_1.setLayout(null);
		
		tColor = new JTextField();
		tColor.setBounds(78, 61, 86, 20);
		panel_1.add(tColor);
		
		if (dat!=null)
			setTitle("Ficha de "+dat.getNombre());
		else
			setTitle("Nuevo Proveedor");
		if (dat!=null)
			llenaFicha(dat);
		
	}
	/**
	 * Rellena la ficha con los datos de la empresa
	 * @param dat Objeto entidad Datosempresa
	 */
	public void llenaFicha(Datosempresa dat) {
		tNombre.setText(dat.getNombre());
		tDireccion.setText(dat.getDireccion());
		tProvincia.setText(dat.getProvincia());
		tCodPos.setText(dat.getCodpos());
		tPoblacion.setText(dat.getPoblacion());
		tFijo.setText(dat.getFijo());
		tMovil.setText(dat.getMovil());
		tEmail.setText(dat.getEmail());
		tNif.setText(dat.getNif());
	}
	/**
	 * Establece el controlador para la ventana de los datos de la empresa
	 * @param manejador ControladorFichaConfig
	 */
	public void EstablecerManejadorVentana(ControladorFichaConfig manejador) {
		this.addInternalFrameListener(manejador);
		tProvincia.addFocusListener(manejador);
		Component[] componentes=panel.getComponents();
		for (Component componente:componentes) 
			if (componente.getClass()==JTextField.class) {
				componente.addFocusListener(manejador);
			}
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
		return tEmail;
	}

	public JTextField gettNif() {
		return tNif;
	}

	public Datosempresa getEmp() {
		return dat;
	}
}
