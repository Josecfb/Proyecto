package vista.fichas;


import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controlador.fichas.ControladorAlbaranProveedor;
import controlador.fichas.ControladorFilaPedidoProveedor;
import controlador.fichas.ControladorPedidoProveedor;
import model.AlbaranProveedor;
import model.FilaPedidoProveedor;
import model.FilaAlbaranProveedor;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.VPedidosProveedores;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class VAlbaranProveedor extends JInternalFrame {

	private static final long serialVersionUID = 4339097703466328107L;
	//private VPedidosProveedores vpedidos;
	private JDateChooser cFecha;
	private JComboBox<Proveedor> comboProveedor;
	private AlbaranProveedor alb;
	private ControladorAlbaranProveedor contrAlbPro;
	private JScrollPane scrollPendientes;
	private VFilaAlbaranProveedor vFilaAlb;
	private JPanel panel;
	private JLabel lTotal;
	private NumberFormat formatoeuro, formatoentero;
	private JTextField tNumAlb;
	private JCheckBox checAlmacen;
	private JButton bNuevaFila;


	public VAlbaranProveedor(AlbaranProveedor alb) {
		//this.vpedidos=vpedidos;
		this.alb=alb;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoentero=NumberFormat.getIntegerInstance();
		contrAlbPro=new ControladorAlbaranProveedor(this);
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
		lblNewLabel_1.setBounds(39, 102, 45, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(94, 102, 358, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cajas");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBackground(new Color(0, 0, 128));
		lblNewLabel_3.setBounds(462, 102, 38, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Unidades");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBackground(new Color(0, 0, 128));
		lblNewLabel_4.setBounds(510, 102, 29, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Coste");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBackground(new Color(0, 0, 128));
		lblNewLabel_5.setBounds(549, 102, 64, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBackground(new Color(0, 0, 128));
		lblNewLabel_6.setBounds(623, 102, 76, 18);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lNum = new JLabel("Numero");
		lNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNum.setBounds(35, 24, 49, 25);
		getContentPane().add(lNum);
		
		tNumAlb = new JTextField();
		tNumAlb.setColumns(10);
		tNumAlb.setBounds(94, 24, 64, 25);
		tNumAlb.setFocusable(false);
		getContentPane().add(tNumAlb);
		
		checAlmacen = new JCheckBox("Confirmado");
		checAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checAlmacen.setBounds(311, 61, 97, 23);
		getContentPane().add(checAlmacen);

		bNuevaFila = new JButton("");
		bNuevaFila.setBounds(578, 60, 35, 35);
		getContentPane().add(bNuevaFila);
		
		setTitle("Albaran Proveedor");
		//contrAlbPro.articulosPendientesPedido(alb);
		muestraFilas(alb);
		if (alb!=null)
			llenaFicha();
		
	}

	public void nuevaFila() {
		FilaAlbaranProveedor fil = new FilaAlbaranProveedor();
		fil.setAlbaranesProveedor(alb);
		vFilaAlb=new VFilaAlbaranProveedor(this,fil);
		//ControladorFilaPedidoProveedor controla=new ControladorFilaPedidoProveedor(vFilaAlb);
		//vFilaAlb.establecerControlador(controla);
		vFilaAlb.setPreferredSize(new Dimension(710,30));
		panel.add(vFilaAlb);
		panel.updateUI();
	}
	
	public void llenaFicha() {
		tNumAlb.setText(String.valueOf(alb.getNum()));
		comboProveedor.setSelectedItem(alb.getProveedore());
		cFecha.setDate(alb.getFecha());
		checAlmacen.setSelected(alb.getActualizadoAlmacen());

	}
	
	public void muestraFilas(AlbaranProveedor albaran) {
		
		List<FilaAlbaranProveedor> filas=alb.getFilasAlbaranProveedors();
		System.out.println("filas "+filas.size());
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,filas.size()*30));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		
		int i=0;
		scrollPendientes.setViewportView(panel);
		double total=0;
		//ControladorFilaPedidoProveedor cfpp =new ControladorFilaPedidoProveedor(vFilaAlb);
		for (FilaAlbaranProveedor fil:filas) {
			i++;
			System.out.println("fila num: "+i);
			vFilaAlb=new VFilaAlbaranProveedor(this,fil);
			//ControladorFilaPedidoProveedor controla=new ControladorFilaPedidoProveedor(vFilaAlb);
			//vFilaAlb.establecerControlador(controla);
			vFilaAlb.setPreferredSize(new Dimension(710,23));
			vFilaAlb.gettCod().setText(String.valueOf(fil.getArticuloBean().getCod()));
			vFilaAlb.getArticulo().setSelectedItem(fil.getArticuloBean());
			vFilaAlb.gettUnidades().setText(formatoentero.format(fil.getCantidad()));
			vFilaAlb.gettCajas().setText(String.valueOf(fil.getCantidad()/fil.getArticuloBean().getUnidadesCaja()));
			vFilaAlb.gettCoste().setText(formatoeuro.format(fil.getArticuloBean().getCoste()));
			vFilaAlb.gettTotal().setText(formatoeuro.format(fil.getCantidad()*fil.getArticuloBean().getCoste()));
			panel.add(vFilaAlb);
			//total+=cfpp.euroADoble(vFilaAlb.gettCoste().getText())*Integer.parseInt(vFilaAlb.gettUnidades().getText());
			vFilaAlb.updateUI();
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
	
	public void establecerControlador(ControladorAlbaranProveedor controlador) {
		this.addInternalFrameListener(controlador);
		comboProveedor.getEditor().getEditorComponent().addFocusListener(controlador);
		bNuevaFila.addActionListener(controlador);
	}
	
	

	public JComboBox<Proveedor> getComboProveedor() {
		return comboProveedor;
	}


	public AlbaranProveedor getAlb() {
		return alb;
	}

	public JTextField gettNumpedido() {
		return tNumAlb;
	}

	public JDateChooser getcFecha() {
		return cFecha;
	}

	public JCheckBox getChecAlmacen() {
		return checAlmacen;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getbNuevaFila() {
		return bNuevaFila;
	}
}
