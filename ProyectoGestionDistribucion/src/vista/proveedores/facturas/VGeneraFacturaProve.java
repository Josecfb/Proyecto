package vista.proveedores.facturas;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.proveedores.facturas.CtrlGenFactProv;
import entidades.AlbaranProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.VentanaPrincipal;

import javax.swing.JButton;
/**
 * Ventana del asistente para generar facturas de proveedor a partir de pedidos
 * @author Jose Carlos
 *
 */
public class VGeneraFacturaProve extends JInternalFrame {

	private static final long serialVersionUID = -8073649338631908268L;
	private VentanaPrincipal v;
	private JComboBox<Proveedor> comboProve;
	private JLabel lProve, linea1,linea2;
	private JButton bAceptar, bCancelar, bSiguiente;
	private JPanel panelFila;
	private JScrollPane scrollPane;
	private VFilaAlbGeneraFactProve vfila;
	/**
	 * El constructor recibe la ventana principal
	 * @param v VentanaPrincipal
	 */
	public VGeneraFacturaProve(VentanaPrincipal v) {
		this.v=v;
		setBounds(100, 100, 746, 382);
		getContentPane().setLayout(null);
		
		comboProve = new JComboBox<Proveedor>();
		comboProve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboProve.setEditable(true);
		comboProve.setBounds(149, 145, 473, 25);
		GestorProveedor gp=new GestorProveedor();
		List<Proveedor> proves = gp.listar("");
		for (Proveedor pro:proves)
			comboProve.addItem(pro);
		comboProve.setSelectedItem(null);
		getContentPane().add(comboProve);
		
		lProve = new JLabel("Proveedor");
		lProve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProve.setBounds(53, 145, 86, 25);
		getContentPane().add(lProve);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 86, 420, 188);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bAceptar.setBounds(174, 304, 123, 30);
		bAceptar.setVisible(false);
		getContentPane().add(bAceptar);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bCancelar.setBounds(423, 304, 123, 30);
		getContentPane().add(bCancelar);
		
		linea1 = new JLabel("Este asistente le ayudar\u00E1 a generar la factura correspondiente a los albaranes de un proveedor");
		linea1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		linea1.setBounds(53, 11, 631, 25);
		getContentPane().add(linea1);
		
		linea2 = new JLabel("Comienze seleccionando el proveedor y pulse el bot\u00F3n siguiente");
		linea2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		linea2.setBounds(53, 35, 589, 25);
		getContentPane().add(linea2);
		
		bSiguiente = new JButton("Siguiente");
		bSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bSiguiente.setBounds(174, 304, 123, 30);
		getContentPane().add(bSiguiente);
		setTitle("Genera Nueva Factura Proveedor");

	}
	/**
	 * Muestra los albaranes sin facturar de un proveedor
	 * @param listaAlb List de AlbaranProveedor
	 */
	public void muestraAlbaranes(List<AlbaranProveedor> listaAlb) {
		panelFila=new JPanel();
			panelFila.setPreferredSize(new Dimension(410,180));
			panelFila.setBackground(Color.WHITE);
			scrollPane.setViewportView(panelFila);
		for (AlbaranProveedor alb:listaAlb) {
			vfila=new VFilaAlbGeneraFactProve();
			vfila.setPreferredSize(new Dimension(400,40));
			vfila.getlNum().setText(String.valueOf(alb.getNum()));
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			vfila.getlFecha().setText(formatoFecha.format(alb.getFecha()));
			panelFila.add(vfila);
		}
	}
	/**
	 * Establece el controlador de la ventyana del asistente para generar facturas de proveedor
	 * @param contolador CtrlGenFactProv
	 */
	public void establecerControlador(CtrlGenFactProv contolador) {
		comboProve.getEditor().getEditorComponent().addFocusListener(contolador);
		bAceptar.addActionListener(contolador);
		bCancelar.addActionListener(contolador);
		bSiguiente.addActionListener(contolador);
	}

	public JComboBox<Proveedor> getComboProve() {
		return comboProve;
	}

	public JButton getbAceptar() {
		return bAceptar;
	}

	public JButton getbCancelar() {
		return bCancelar;
	}
	
	public JLabel getlProve() {
		return lProve;
	}
	

	public JLabel getLinea1() {
		return linea1;
	}

	public JLabel getLinea2() {
		return linea2;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JButton getbSiguiente() {
		return bSiguiente;
	}

	public JPanel getPanelFila() {
		return panelFila;
	}

	public VentanaPrincipal getV() {
		return v;
	}
}
