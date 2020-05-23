package vista.clientes.albaranes;

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
import controlador.clientes.albaranes.ControladorAlbaranesClientes;
import controlador.clientes.albaranes.CtrlFilaAlbaranesGenCliente;
import entidades.AlbaranCliente;
import entidades.Cliente;
import vista.VentanaPrincipal;



public class VAlbaranesClientes extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private JPanel panelGenerados, panelEnAlmacen, panelFacturados;
	private JScrollPane scrollGenerados, scrollEnAlmacen, scrollFacturados;
	private VFilaAlbaranGeneradoCliente filaAlb;
	private VentanaPrincipal v;
	private JButton bNuevoGenerado;

	public VAlbaranesClientes(VentanaPrincipal v) {
		this.v=v;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setTitle("Albaranes de Clientes");
		
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

		new ControladorAlbaranesClientes(this);
	}
	
	public void muestraPendientes(List<AlbaranCliente> lista) {
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
		
		for (AlbaranCliente fila:lista) {
			Cliente cli=fila.getClienteBean();
			filaAlb=new VFilaAlbaranGeneradoCliente(fila,this);
			CtrlFilaAlbaranesGenCliente controla=new CtrlFilaAlbaranesGenCliente(filaAlb);
			filaAlb.establecerControlador(controla);
			filaAlb.setPreferredSize(new Dimension(650,30));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			filaAlb.getlProveedor().setText(String.valueOf(cli.getNombre()));
			if (fila.getFecha()!=null) filaAlb.getlFecha().setText(formatter.format(fila.getFecha()));
			filaAlb.getlNum().setText(String.valueOf(fila.getNum()));
			if (fila.isActualizadoAlmacen() && fila.getFacturado())
				panelFacturados.add(filaAlb);
			else
				if (fila.isActualizadoAlmacen())
					panelEnAlmacen.add(filaAlb);
				else
					panelGenerados.add(filaAlb);
		}
	}
	
	public void establecerManejador(ControladorAlbaranesClientes controlador) {
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
