package vista.proveedores.pedidos;


import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controlador.proveedores.pedidos.ControladorFilaPedidoProveedor;
import controlador.proveedores.pedidos.ControladorPedidoProveedor;
import model.FilaPedidoProveedor;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorProveedor;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VPedidoProveedor extends JInternalFrame {

	private static final long serialVersionUID = 4339097703466328107L;
	private VPedidosProveedores vpedidos;
	private JDateChooser cFecha;
	private JComboBox<Proveedor> comboProveedor;
	private PedidoProveedor ped;
	private ControladorPedidoProveedor contrPedPro;
	private JScrollPane scrollPendientes;
	private VFilaPedidoProveedor filaPed;
	private JPanel panel;
	private JLabel lTotal;
	private NumberFormat formatoeuro, formatoentero;
	private JTextField tNumpedido;
	private JCheckBox checConfirmado, checEnviado;
	private JButton bNuevaFila;


	public VPedidoProveedor(PedidoProveedor ped,VPedidosProveedores vpedidos) {
		this.vpedidos=vpedidos;
		this.ped=ped;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoentero=NumberFormat.getIntegerInstance();
		contrPedPro=new ControladorPedidoProveedor(this);
		this.ped=ped;
		setBounds(100, 100, 759, 465);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		comboProveedor = new JComboBox<Proveedor>();
		comboProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboProveedor.setBounds(293, 24, 427, 25);
		comboProveedor.setEditable(true);
		for (Proveedor pr:new GestorProveedor().listar("")) 
			comboProveedor.addItem(pr);
		comboProveedor.setSelectedItem(null);
		getContentPane().add(comboProveedor);
		
		JLabel lblNewLabel = new JLabel("Proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(216, 24, 76, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(39, 60, 89, 25);
		getContentPane().add(lblFecha);
		
		cFecha = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		cFecha.setBounds(94, 60, 186, 25);
		cFecha.getJCalendar().setTodayButtonVisible(true);
		cFecha.getJCalendar().setTodayButtonText("Hoy");
		cFecha.getJCalendar().setNullDateButtonVisible(true);
		getContentPane().add(cFecha);

		
		scrollPendientes = new JScrollPane();
		scrollPendientes.setBounds(10, 124, 715, 259);
		getContentPane().add(scrollPendientes);
		
		lTotal = new JLabel("");
		lTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTotal.setBounds(595, 394, 89, 25);
		getContentPane().add(lTotal);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3d");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(24, 96, 45, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(79, 96, 358, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cajas");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBackground(new Color(0, 0, 128));
		lblNewLabel_3.setBounds(447, 96, 38, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Unidades");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBackground(new Color(0, 0, 128));
		lblNewLabel_4.setBounds(495, 96, 29, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Coste");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBackground(new Color(0, 0, 128));
		lblNewLabel_5.setBounds(534, 96, 64, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBackground(new Color(0, 0, 128));
		lblNewLabel_6.setBounds(608, 96, 76, 18);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lNum = new JLabel("Numero");
		lNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNum.setBounds(35, 24, 49, 25);
		getContentPane().add(lNum);
		
		tNumpedido = new JTextField();
		tNumpedido.setColumns(10);
		tNumpedido.setBounds(94, 24, 64, 25);
		tNumpedido.setFocusable(false);
		getContentPane().add(tNumpedido);
		
		checConfirmado = new JCheckBox("Confirmado");
		checConfirmado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checConfirmado.setBounds(311, 61, 97, 23);
		getContentPane().add(checConfirmado);
		
		checEnviado = new JCheckBox("Enviado");
		checEnviado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checEnviado.setBounds(442, 61, 97, 23);
		getContentPane().add(checEnviado);
		
		bNuevaFila = new JButton("");
		bNuevaFila.setBounds(578, 60, 35, 35);
		bNuevaFila.setIcon(new ImageIcon("src/img/nuevafila.png"));
		getContentPane().add(bNuevaFila);
		
		setTitle("Pedido Proveedor");
		contrPedPro.articulosPendientesPedido(ped);
		muestraFilas(ped);
		if (ped!=null)
			llenaFicha();
		
	}

	public void nuevaFila() {
		FilaPedidoProveedor fil = new FilaPedidoProveedor();
		fil.setPedidosProveedor(ped);
		filaPed=new VFilaPedidoProveedor(this,fil);
		ControladorFilaPedidoProveedor controla=new ControladorFilaPedidoProveedor(filaPed);
		filaPed.establecerControlador(controla);
		filaPed.setPreferredSize(new Dimension(710,23));
		panel.setPreferredSize(new Dimension(710,panel.getHeight()+23));
		panel.add(filaPed);
		panel.updateUI();
	}
	
	public void llenaFicha() {
		tNumpedido.setText(String.valueOf(ped.getNum()));
		comboProveedor.setSelectedItem(ped.getProveedore());
		cFecha.setDate(ped.getFecha());
		checConfirmado.setSelected(ped.getConfirmado());
		checEnviado.setSelected(ped.getEnviado());
	}
	
	public void muestraFilas(PedidoProveedor pedido) {
		
		List<FilaPedidoProveedor> filas=contrPedPro.articulosPendientesPedido(pedido);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,filas.size()*23));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		
		int i=0;
		scrollPendientes.setViewportView(panel);
		double total=0;
		ControladorFilaPedidoProveedor cfpp =new ControladorFilaPedidoProveedor(filaPed);
		for (FilaPedidoProveedor fil:filas) {
			i++;
			filaPed=new VFilaPedidoProveedor(this,fil);
			ControladorFilaPedidoProveedor controla=new ControladorFilaPedidoProveedor(filaPed);
			filaPed.establecerControlador(controla);
			filaPed.setPreferredSize(new Dimension(710,23));
			filaPed.gettCod().setText(String.valueOf(fil.getArticuloBean().getCod()));
			filaPed.getArticulo().setSelectedItem(fil.getArticuloBean());
			filaPed.gettUnidades().setText(formatoentero.format(fil.getCantidad()));
			filaPed.gettCajas().setText(String.valueOf(fil.getCantidad()/fil.getArticuloBean().getUnidadesCaja()));
			filaPed.gettCoste().setText(formatoeuro.format(fil.getArticuloBean().getCoste()));
			filaPed.gettTotal().setText(formatoeuro.format(fil.getCantidad()*fil.getArticuloBean().getCoste()));
			panel.add(filaPed);
			total+=cfpp.euroADoble(filaPed.gettCoste().getText())*Integer.parseInt(filaPed.gettUnidades().getText());
			filaPed.updateUI();
		}
		
		lTotal.setText(formatoeuro.format(total));
	}
	
	public void actualizaTotal() {

		Component[] filas=panel.getComponents();
		double total=0;
		for (Component fila:filas) {
			VFilaPedidoProveedor fil=(VFilaPedidoProveedor) fila;
			total+=(new ControladorFilaPedidoProveedor(fil).euroADoble(fil.gettCoste().getText()))*Integer.parseInt(fil.gettUnidades().getText());
		}
		lTotal.setText(formatoeuro.format(total));
	}
	
	public void establecerControlador(ControladorPedidoProveedor controlador) {
		this.addInternalFrameListener(controlador);
		comboProveedor.getEditor().getEditorComponent().addFocusListener(controlador);
		bNuevaFila.addActionListener(controlador);
	}
	
	

	public JComboBox<Proveedor> getComboProveedor() {
		return comboProveedor;
	}


	public PedidoProveedor getPed() {
		return ped;
	}

	public JTextField gettNumpedido() {
		return tNumpedido;
	}

	public JDateChooser getcFecha() {
		return cFecha;
	}

	public JCheckBox getChecConfirmado() {
		return checConfirmado;
	}

	public JCheckBox getChecEnviado() {
		return checEnviado;
	}

	public JPanel getPanel() {
		return panel;
	}

	public VPedidosProveedores getVpedidos() {
		return vpedidos;
	}

	public void setPed(PedidoProveedor ped) {
		this.ped = ped;
	}

	public JButton getbNuevaFila() {
		return bNuevaFila;
	}
}