package vista.clientes.facturas;

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
import controlador.clientes.facturas.CtrlGenFactCliente;
import entidades.AlbaranProveedor;
import entidades.Cliente;
import modelo.negocio.GestorCliente;
import vista.VentanaPrincipal;
import javax.swing.JButton;

public class VGeneraFacturaCliente extends JInternalFrame {

	private static final long serialVersionUID = -8073649338631908268L;
	private VentanaPrincipal v;
	private JComboBox<Cliente> comboCli;
	private JLabel lProve, linea1,linea2;
	private JButton bAceptar, bCancelar, bSiguiente;
	private JPanel panelFila;
	private JScrollPane scrollPane;
	private VFilaAlbGeneraFactCliente vfila;

	public VGeneraFacturaCliente(VentanaPrincipal v) {
		this.v=v;
		setBounds(100, 100, 746, 382);
		getContentPane().setLayout(null);
		
		comboCli = new JComboBox<Cliente>();
		comboCli.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCli.setEditable(true);
		comboCli.setBounds(149, 145, 473, 25);
		GestorCliente gc=new GestorCliente();
		List<Cliente> clien = gc.listar("");
		for (Cliente cli:clien)
			comboCli.addItem(cli);
		comboCli.setSelectedItem(null);
		getContentPane().add(comboCli);
		
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

	public void muestraAlbaranes(List<AlbaranProveedor> listaAlb) {
		panelFila=new JPanel();
			panelFila.setPreferredSize(new Dimension(410,180));
			panelFila.setBackground(Color.WHITE);
			scrollPane.setViewportView(panelFila);
		for (AlbaranProveedor alb:listaAlb) {
			vfila=new VFilaAlbGeneraFactCliente();
			vfila.setPreferredSize(new Dimension(400,40));
			vfila.getlNum().setText(String.valueOf(alb.getNum()));
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			vfila.getlFecha().setText(formatoFecha.format(alb.getFecha()));
			panelFila.add(vfila);
		}
	}
	
	public void establecerControlador(CtrlGenFactCliente contolador) {
		comboCli.getEditor().getEditorComponent().addFocusListener(contolador);
		bAceptar.addActionListener(contolador);
		bCancelar.addActionListener(contolador);
		bSiguiente.addActionListener(contolador);
	}

	public JComboBox<Cliente> getComboCli() {
		return comboCli;
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
