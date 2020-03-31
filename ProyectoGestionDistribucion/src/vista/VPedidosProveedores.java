package vista;

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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import controlador.fichas.ControladorFilaPedidoPendienteProveedor;
import controlador.fichas.ControladorPedidosProveedores;
import model.PedidoProveedor;
import model.Proveedor;



public class VPedidosProveedores extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private JPanel panelPendientes, panelEnviados;
	private JScrollPane scrollPendientes, scrollEnviados;
	private VFilaPedidoPendienteProveedor filaPed;
	//private NumberFormat formatoeuro;
	private VentanaPrincipal v;
	private JButton bNuevoPendiente;

	public VPedidosProveedores(VentanaPrincipal v) {
		this.v=v;
		//formatoeuro = NumberFormat.getCurrencyInstance();
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setTitle("Pedidos Proveedores");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setBounds(10, 11, 808, 495);
		getContentPane().add(tabbedPane);
		
		JPanel pPendientes = new JPanel();
		tabbedPane.addTab("Pendientes", null, pPendientes, null);
		pPendientes.setLayout(null);
		
		scrollPendientes = new JScrollPane();
		scrollPendientes.setBounds(10, 67, 783, 383);
		pPendientes.add(scrollPendientes);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 87, 60);
		pPendientes.add(toolBar);
		
		bNuevoPendiente = new JButton();
		bNuevoPendiente.setIcon(new ImageIcon("src/img/nuevo.png"));
		toolBar.add(bNuevoPendiente);
		
		JPanel pEnviados = new JPanel();
		tabbedPane.addTab("Enviados", null, pEnviados, null);
		pEnviados.setLayout(null);
		
		scrollEnviados = new JScrollPane();
		scrollEnviados.setBounds(10, 11, 783, 277);
		pEnviados.add(scrollEnviados);
		
		JPanel pRecibidos = new JPanel();
		tabbedPane.addTab("Recibidos", null, pRecibidos, null);
		pRecibidos.setLayout(null);
		
		JScrollPane scrollRecibidos = new JScrollPane();
		scrollRecibidos.setBounds(10, 51, 783, 237);
		pRecibidos.add(scrollRecibidos);
		ControladorPedidosProveedores cpp=new ControladorPedidosProveedores(this);
	}
	
	public void muestraPendientes(List<PedidoProveedor> lista) {
		System.out.println("pendientes="+lista.size());
		panelPendientes = new JPanel();
		panelPendientes.setPreferredSize(new Dimension(650,lista.size()*30));
		panelPendientes.setBackground(Color.WHITE);
		panelPendientes.setBorder(null);
		panelEnviados = new JPanel();
		panelEnviados.setPreferredSize(new Dimension(650,lista.size()*30));
		panelEnviados.setBackground(Color.WHITE);
		panelEnviados.setBorder(null);

		int i=0;
		scrollPendientes.setViewportView(panelPendientes);
		scrollEnviados.setViewportView(panelEnviados);
		for (PedidoProveedor fila:lista) {
			i++;
			Proveedor pro=fila.getProveedore();
			filaPed=new VFilaPedidoPendienteProveedor(fila,this);
			ControladorFilaPedidoPendienteProveedor controla=new ControladorFilaPedidoPendienteProveedor(filaPed);
			filaPed.establecerControlador(controla);
			filaPed.setPreferredSize(new Dimension(650,30));
			if (i%2==0) filaPed.setBackground(Color.WHITE);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			filaPed.getlProveedor().setText(String.valueOf(pro.getNombre()));
			if (fila.getFecha()!=null) filaPed.getlFecha().setText(formatter.format(fila.getFecha()));
			if (fila.getEnviado())
				panelEnviados.add(filaPed);
			else
				panelPendientes.add(filaPed);
		}
	}
	
	public void establecerManejador(ControladorPedidosProveedores controlador) {
		bNuevoPendiente.addActionListener(controlador);
	}

	public VentanaPrincipal getV() {
		return v;
	}

	public JButton getbNuevoPendiente() {
		return bNuevoPendiente;
	}

	public JPanel getPanel() {
		return panelPendientes;
	}
	
	
}
