package vista.clientes.pedidos;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controlador.clientes.pedidos.ControladorPedidosClientes;
import entidades.Cliente;
import entidades.PedidoCliente;
import controlador.clientes.pedidos.ControladorFilaPedidoPendienteCliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import vista.VentanaPrincipal;

public class VPedidosClientes extends JInternalFrame {

	private static final long serialVersionUID = 8710778275789682602L;
	private JPanel panelPendientes, panelEnviados, panelRecibidos;
	private JScrollPane scrollPendientes, scrollEnviados;
	private VFilaPedidoPendienteCliente filaPed;
	private VentanaPrincipal v;

	public VPedidosClientes(VentanaPrincipal v) {
		this.v=v;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setBounds(100, 100, 844, 547);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setTitle("Pedidos Clientes");
		
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
		
		JPanel pEnviados = new JPanel();
		tabbedPane.addTab("Enviados", null, pEnviados, null);
		pEnviados.setLayout(null);
		
		scrollEnviados = new JScrollPane();
		scrollEnviados.setBounds(10, 11, 783, 439);
		pEnviados.add(scrollEnviados);
		new ControladorPedidosClientes(this);
	}
	
	public void muestraPendientes(List<PedidoCliente> lista) {
		panelPendientes = new JPanel();
		panelPendientes.setPreferredSize(new Dimension(650,lista.size()*35));
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
		for (PedidoCliente fila:lista) {
			Cliente cli=fila.getClienteBean();
			filaPed=new VFilaPedidoPendienteCliente(fila,this);
			ControladorFilaPedidoPendienteCliente controla=new ControladorFilaPedidoPendienteCliente(filaPed);
			filaPed.establecerControlador(controla);
			filaPed.setPreferredSize(new Dimension(650,30));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			filaPed.getlProveedor().setText(String.valueOf(cli.getNombre()));
			if (fila.getFecha()!=null) filaPed.getlFecha().setText(formatter.format(fila.getFecha()));
			filaPed.getlNum().setText(String.valueOf(fila.getNum()));
			if (fila.getEnviado())
				panelEnviados.add(filaPed);
			else
				panelPendientes.add(filaPed);
		}
	}
	

	public VentanaPrincipal getV() {
		return v;
	}

	public JPanel getPanel() {
		return panelPendientes;
	}
	
	
}
