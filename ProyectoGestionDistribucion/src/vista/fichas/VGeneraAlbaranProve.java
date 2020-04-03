package vista.fichas;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.CtrlGenAlbProv;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorProveedor;

import javax.swing.JButton;

public class VGeneraAlbaranProve extends JInternalFrame {

	private static final long serialVersionUID = -8073649338631908268L;
	private JComboBox<Proveedor> comboProve;
	private JButton bAceptar, bCancelar;
	private JPanel panelFila;
	private JScrollPane scrollPane;
	private VFilaPedGeneraAlbProve vfila;

	public VGeneraAlbaranProve() {
		setBounds(100, 100, 746, 382);
		getContentPane().setLayout(null);
		
		comboProve = new JComboBox<Proveedor>();
		comboProve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboProve.setEditable(true);
		comboProve.setBounds(174, 38, 473, 25);
		GestorProveedor gp=new GestorProveedor();
		List<Proveedor> proves = gp.listar("");
		System.out.println("proveedores obtenidos="+proves.size());
		for (Proveedor pro:proves)
			comboProve.addItem(pro);
		comboProve.setSelectedItem(null);
		getContentPane().add(comboProve);
		
		JLabel lProve = new JLabel("Proveedor");
		lProve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lProve.setBounds(78, 38, 86, 25);
		getContentPane().add(lProve);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 86, 400, 188);
		getContentPane().add(scrollPane);
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bAceptar.setBounds(174, 304, 123, 30);
		getContentPane().add(bAceptar);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bCancelar.setBounds(423, 304, 123, 30);
		getContentPane().add(bCancelar);
		setTitle("Genera Nuevo Albaran Proveedor");

	}

	public void muestraPedidos(List<PedidoProveedor> listaPed) {
		panelFila=new JPanel();
			panelFila.setPreferredSize(new Dimension(400,180));
			panelFila.setBackground(Color.WHITE);
			scrollPane.setViewportView(panelFila);
		for (PedidoProveedor ped:listaPed) {
			vfila=new VFilaPedGeneraAlbProve();
			vfila.setPreferredSize(new Dimension(400,40));
			vfila.getlNum().setText(String.valueOf(ped.getNum()));
			vfila.getlFecha().setText(String.valueOf(ped.getFecha()));
			System.out.println(vfila.getlNum().getText());
			panelFila.add(vfila);
		}
	}
	
	public void establecerControlador(CtrlGenAlbProv contolador) {
		comboProve.getEditor().getEditorComponent().addFocusListener(contolador);
		bAceptar.addActionListener(contolador);
		bCancelar.addActionListener(contolador);
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

	public JPanel getPanelFila() {
		return panelFila;
	}

}
