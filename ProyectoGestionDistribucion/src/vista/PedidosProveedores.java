package vista;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import controlador.fichas.ControladorFilaPedidoPendienteProveedor;
import controlador.fichas.ControladorPedidosProveedores;
import model.Proveedor;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PedidosProveedores extends JInternalFrame {
	private JPanel panel;
	private JScrollPane scrollPendientes;
	private FilaPedidoPendienteProveedor filaPed;
	private NumberFormat formatoeuro;
	private VentanaPrincipal v;

	public PedidosProveedores(VentanaPrincipal v) {
		this.v=v;
		formatoeuro = NumberFormat.getCurrencyInstance();
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
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
		
		JButton bNuevoPendiente = new JButton();
		bNuevoPendiente.setIcon(new ImageIcon("src/img/nuevo.png"));
		toolBar.add(bNuevoPendiente);
		
		JPanel pEnviados = new JPanel();
		tabbedPane.addTab("Enviados", null, pEnviados, null);
		pEnviados.setLayout(null);
		
		JScrollPane scrollEnviados = new JScrollPane();
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
	
	public void muestraPendientes(List<Object[]> lista) {
		System.out.println("pendientes="+lista.size());
		panel = new JPanel();
		//panel.setBounds(0,0,727,650);
		panel.setPreferredSize(new Dimension(650,lista.size()*30));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);

		int i=0;
		scrollPendientes.setViewportView(panel);
		for (Object[] fila:lista) {
			i++;
			Proveedor pro=(Proveedor) fila[0];
			filaPed=new FilaPedidoPendienteProveedor(pro,v);
			ControladorFilaPedidoPendienteProveedor controla=new ControladorFilaPedidoPendienteProveedor(filaPed);
			filaPed.establecerControlador(controla);
			filaPed.setPreferredSize(new Dimension(650,30));
			if (i%2==0) filaPed.setBackground(Color.WHITE);
			
			filaPed.getlProveedor().setText(String.valueOf(pro.getNombre()));
			filaPed.getlTotal().setText(formatoeuro.format(fila[1]));
			panel.add(filaPed);
		}
	}

	public VentanaPrincipal getV() {
		return v;
	}
	
	
}
