package vista.proveedores.facturas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import vista.VentanaPrincipal;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import controlador.proveedores.facturas.ControladorFacturasProveedores;
import controlador.proveedores.facturas.CtrlFilaFacturasGenProveedor;
import model.FacturaProveedor;
import model.Proveedor;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VFacturasProveedores extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private VentanaPrincipal v;
	private JButton bNueva;
	private JPanel panelGeneradas;
	private JScrollPane scrollPane;
	private VFilaFacturasProveedor vFilaFact;

	public VFacturasProveedores(VentanaPrincipal v) {
		this.v=v;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
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
	
	public void muestraPendientes(List<FacturaProveedor> lista) {
		System.out.println("pendientes="+lista.size());
		panelGeneradas = new JPanel();
		panelGeneradas.setPreferredSize(new Dimension(650,lista.size()*30));
		panelGeneradas.setBackground(Color.WHITE);
		panelGeneradas.setBorder(null);

		scrollPane.setViewportView(panelGeneradas);
		for (FacturaProveedor fila:lista) {
			Proveedor pro=fila.getProveedore();
			vFilaFact=new VFilaFacturasProveedor(fila,this);
			CtrlFilaFacturasGenProveedor controla=new CtrlFilaFacturasGenProveedor(vFilaFact);
			vFilaFact.establecerControlador(controla);
			vFilaFact.setPreferredSize(new Dimension(650,30));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			vFilaFact.getlProveedor().setText(String.valueOf(pro.getNombre()));
			if (fila.getFecha()!=null) vFilaFact.getlFecha().setText(formatter.format(fila.getFecha()));
			vFilaFact.getlNum().setText(String.valueOf(fila.getNum()));
			panelGeneradas.add(vFilaFact);
		}
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
