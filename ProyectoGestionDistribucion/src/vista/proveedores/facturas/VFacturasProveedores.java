package vista.proveedores.facturas;

import javax.swing.JInternalFrame;
import java.awt.Font;

import vista.VentanaPrincipal;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import controlador.fichas.ControladorFacturasProveedores;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class VFacturasProveedores extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private VentanaPrincipal v;
	private JButton bNueva;

	public VFacturasProveedores(VentanaPrincipal v) {
		this.v=v;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 808, 442);
		getContentPane().add(scrollPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 135, 53);
		getContentPane().add(toolBar);
		
		bNueva = new JButton("");
		bNueva.setIcon(new ImageIcon("src/img/nuevo.png"));
		toolBar.add(bNueva);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setTitle("Facturas Proveedores");
	}
	
	public void establecerManejador(ControladorFacturasProveedores cfp) {
		bNueva.addActionListener(cfp);
	}

	public VentanaPrincipal getV() {
		return v;
	}

	public JButton getbNueva() {
		return bNueva;
	}
	
}
