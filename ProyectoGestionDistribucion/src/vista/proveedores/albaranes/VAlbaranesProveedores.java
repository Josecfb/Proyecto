package vista.proveedores.albaranes;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import controlador.proveedores.albaranes.ControladorAlbaranesProveedores;
import controlador.proveedores.albaranes.CtrlFilaAlbaranesGenProveedor;
import model.AlbaranProveedor;
import model.Proveedor;
import vista.VentanaPrincipal;



public class VAlbaranesProveedores extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private JPanel panelGenerados, panelEnAlmacen, panelFacturados;
	private JScrollPane scrollGenerados, scrollEnAlmacen, scrollFacturados;
	private VFilaAlbaranGeneradoProveedor filaAlb;
	private VentanaPrincipal v;
	private JButton bNuevoGenerado;

	public VAlbaranesProveedores(VentanaPrincipal v) {
		this.v=v;
		//formatoeuro = NumberFormat.getCurrencyInstance();
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setTitle("Albaranes Proveedores");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(10, 11, 808, 495);
		getContentPane().add(tabbedPane);
		
		JPanel pGenerados = new JPanel();
		tabbedPane.addTab("Generados", null, pGenerados, null);
		pGenerados.setLayout(null);
		
		scrollGenerados = new JScrollPane();
		scrollGenerados.setBounds(10, 67, 783, 383);
		pGenerados.add(scrollGenerados);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 87, 60);
		pGenerados.add(toolBar);
		
		bNuevoGenerado = new JButton();
		bNuevoGenerado.setIcon(new ImageIcon("src/img/nuevo.png"));
		toolBar.add(bNuevoGenerado);
		
		JPanel pEnAlmacen = new JPanel();
		tabbedPane.addTab("En Almacen", null, pEnAlmacen, null);
		pEnAlmacen.setLayout(null);
		
		scrollEnAlmacen = new JScrollPane();
		scrollEnAlmacen.setBounds(10, 51, 783, 397);
		pEnAlmacen.add(scrollEnAlmacen);
		
		JPanel pFacturados = new JPanel();
		tabbedPane.addTab("Facturados", null, pFacturados, null);
		pFacturados.setLayout(null);
		
		scrollFacturados = new JScrollPane();
		scrollFacturados.setBounds(10, 62, 783, 386);
		pFacturados.add(scrollFacturados);

		new ControladorAlbaranesProveedores(this);
	}
	
	public void muestraPendientes(List<AlbaranProveedor> lista) {
		panelGenerados = new JPanel();
		panelGenerados.setPreferredSize(new Dimension(650,lista.size()*30));
		panelGenerados.setBackground(Color.WHITE);
		panelGenerados.setBorder(null);
		panelEnAlmacen = new JPanel();
		panelEnAlmacen.setPreferredSize(new Dimension(650,lista.size()*30));
		panelEnAlmacen.setBackground(Color.WHITE);
		panelEnAlmacen.setBorder(null);
		panelFacturados = new JPanel();
		panelFacturados.setPreferredSize(new Dimension(650,lista.size()*30));
		panelFacturados.setBackground(Color.WHITE);
		panelFacturados.setBorder(null);

		scrollGenerados.setViewportView(panelGenerados);
		scrollEnAlmacen.setViewportView(panelEnAlmacen);
		scrollFacturados.setViewportView(panelFacturados);
		
		for (AlbaranProveedor fila:lista) {
			Proveedor pro=fila.getProveedore();
			filaAlb=new VFilaAlbaranGeneradoProveedor(fila,this);
			CtrlFilaAlbaranesGenProveedor controla=new CtrlFilaAlbaranesGenProveedor(filaAlb);
			filaAlb.establecerControlador(controla);
			filaAlb.setPreferredSize(new Dimension(650,30));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			filaAlb.getlProveedor().setText(String.valueOf(pro.getNombre()));
			if (fila.getFecha()!=null) filaAlb.getlFecha().setText(formatter.format(fila.getFecha()));
			filaAlb.getlNum().setText(String.valueOf(fila.getNum()));
			if (fila.getActualizadoAlmacen() && fila.isFacturado())
				panelFacturados.add(filaAlb);
			else
				if (fila.getActualizadoAlmacen())
					panelEnAlmacen.add(filaAlb);
				else
					panelGenerados.add(filaAlb);
		}
	}
	
	public void establecerManejador(ControladorAlbaranesProveedores controlador) {
		bNuevoGenerado.addActionListener(controlador);
	}

	public VentanaPrincipal getV() {
		return v;
	}

	public JButton getbNuevoGenerado() {
		return bNuevoGenerado;
	}

	public JPanel getPanel() {
		return panelGenerados;
	}
}
