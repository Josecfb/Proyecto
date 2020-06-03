package vista.proveedores.pedidos;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import controlador.proveedores.pedidos.ControladorFilaPedidoPendienteProveedor;
import controlador.proveedores.pedidos.ControladorPedidosProveedores;
import entidades.PedidoProveedor;
import entidades.Proveedor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import vista.VentanaPrincipal;
/**
 * Ventana del listado de pedidos de proveedores
 * @author Jose Carlos
 *
 */
public class VPedidosProveedores extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private JPanel panelPendientes, panelEnviados, panelRecibidos;
	private JScrollPane scrollPendientes, scrollEnviados, scrollRecibidos;
	private VFilaPedidosProveedor filaPed;
	private VentanaPrincipal v;
	private JButton bNuevoPendiente;
	/**
	 * El constructor recibe la ventana principal
	 * @param v VentanaPrincipal
	 */
	public VPedidosProveedores(VentanaPrincipal v) {
		this.v=v;
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
		scrollEnviados.setBounds(10, 11, 783, 439);
		pEnviados.add(scrollEnviados);
		
		JPanel pRecibidos = new JPanel();
		tabbedPane.addTab("Recibidos", null, pRecibidos, null);
		pRecibidos.setLayout(null);
		
		scrollRecibidos = new JScrollPane();
		scrollRecibidos.setBounds(10, 11, 783, 439);
		pRecibidos.add(scrollRecibidos);
		new ControladorPedidosProveedores(this);
	}
	/**
	 * Muesta las filas con los pedidos de proveedores
	 * @param lista List de PedidoProveedor
	 */
	public void muestraPedidos(List<PedidoProveedor> lista) {
		panelPendientes = new JPanel();
		panelPendientes.setPreferredSize(new Dimension(650,lista.size()*30));
		panelPendientes.setBackground(Color.WHITE);
		panelPendientes.setBorder(null);
		
		panelEnviados = new JPanel();
		panelEnviados.setPreferredSize(new Dimension(650,lista.size()*30));
		panelEnviados.setBackground(Color.WHITE);
		panelEnviados.setBorder(null);
		
		panelRecibidos = new JPanel();
		panelRecibidos.setPreferredSize(new Dimension(650,lista.size()*30));
		panelRecibidos.setBackground(Color.WHITE);
		panelRecibidos.setBorder(null);

		scrollPendientes.setViewportView(panelPendientes);
		scrollEnviados.setViewportView(panelEnviados);
		scrollRecibidos.setViewportView(panelRecibidos);
		for (PedidoProveedor fila:lista) {
			Proveedor pro=fila.getProveedore();
			filaPed=new VFilaPedidosProveedor(fila,this);
			ControladorFilaPedidoPendienteProveedor controla=new ControladorFilaPedidoPendienteProveedor(filaPed);
			filaPed.establecerControlador(controla);
			filaPed.setPreferredSize(new Dimension(650,30));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			filaPed.getlProveedor().setText(String.valueOf(pro.getNombre()));
			if (fila.getFecha()!=null) filaPed.getlFecha().setText(formatter.format(fila.getFecha()));
			filaPed.getlNum().setText(String.valueOf(fila.getNum()));
			if (fila.getEnviado() && fila.getConfirmado())
				panelRecibidos.add(filaPed);
			else
				if (fila.getEnviado())
					panelEnviados.add(filaPed);
				else
					panelPendientes.add(filaPed);
		}
	}
	/**
	 * Establece el controlador para la ventana del listado de pedidos de proveedor
	 * @param controlador
	 */
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
