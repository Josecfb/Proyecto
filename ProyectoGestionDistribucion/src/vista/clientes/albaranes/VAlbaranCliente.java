package vista.clientes.albaranes;


import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import controlador.clientes.albaranes.ControladorAlbaranCliente;
import controlador.clientes.albaranes.CtrlFilaAlbCliente;
import entidades.AlbaranCliente;
import entidades.Cliente;
import entidades.FilasAlbaranCliente;
import modelo.negocio.GestorCliente;
import util.Utilidades;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VAlbaranCliente extends JInternalFrame {

	private static final long serialVersionUID = 4339097703466328107L;
	private VAlbaranesClientes vAlbsCli;
	private JDateChooser cFecha;
	private JComboBox<Cliente> comboCliente;
	private AlbaranCliente alb;
	private JScrollPane scrollPendientes;
	private VFilaAlbaranCliente vFilaAlb;
	private JPanel panel;
	private JLabel lBase,lIva,lTotal;
	private NumberFormat formatoeuro, formatoentero;
	private JTextField tNumAlb;
	private JCheckBox checAlmacen, checFacturado;
	private JButton bNuevaFila;
	private Utilidades u;


	public VAlbaranCliente(AlbaranCliente alb,VAlbaranesClientes vAlbsCli) {
		u=new Utilidades();
		this.vAlbsCli=vAlbsCli;
		this.alb=alb;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoentero=NumberFormat.getIntegerInstance();
		setBounds(100, 100, 759, 519);
		getContentPane().setLayout(null);
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		comboCliente = new JComboBox<Cliente>();
		comboCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCliente.setBounds(293, 24, 427, 25);
		comboCliente.setEditable(true);
		for (Cliente cli:new GestorCliente().listar("")) 
			comboCliente.addItem(cli);
		comboCliente.setSelectedItem(null);
		getContentPane().add(comboCliente);
		
		JLabel lblNewLabel = new JLabel("Cliente");
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
		
		lBase = new JLabel("");
		lBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lBase.setBounds(580, 392, 89, 25);
		lBase.setHorizontalAlignment(JTextField.RIGHT);
		getContentPane().add(lBase);
		
		lIva = new JLabel("0,00 \u20AC");
		lIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lIva.setBounds(580, 423, 89, 25);
		lIva.setHorizontalAlignment(JTextField.RIGHT);
		getContentPane().add(lIva);
		
		lTotal = new JLabel("0,00 \u20AC");
		lTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lTotal.setBounds(580, 453, 89, 25);
		lTotal.setHorizontalAlignment(JTextField.RIGHT);
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
		
		JLabel lblNewLabel_4 = new JLabel("Unidades");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBackground(new Color(0, 0, 128));
		lblNewLabel_4.setBounds(462, 102, 55, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBackground(new Color(0, 0, 128));
		lblNewLabel_5.setBounds(527, 102, 68, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBackground(new Color(0, 0, 128));
		lblNewLabel_6.setBounds(605, 102, 76, 18);
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
		
		checAlmacen = new JCheckBox("Baja almacen");
		checAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checAlmacen.setBounds(311, 61, 105, 23);
		getContentPane().add(checAlmacen);

		bNuevaFila = new JButton("");
		bNuevaFila.setBounds(578, 60, 35, 35);
		bNuevaFila.setIcon(new ImageIcon("src/img/nuevafila.png"));
		getContentPane().add(bNuevaFila);
		
		JLabel lblBase = new JLabel("Base");
		lblBase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBase.setBounds(481, 392, 89, 25);
		getContentPane().add(lblBase);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIva.setBounds(481, 423, 89, 25);
		getContentPane().add(lblIva);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(481, 453, 89, 25);
		getContentPane().add(lblTotal);
		
		checFacturado = new JCheckBox("Facturado");
		checFacturado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checFacturado.setBounds(430, 61, 141, 23);
		getContentPane().add(checFacturado);
		
		setTitle("Albaran Cliente");
		//contrAlbPro.articulosPendientesPedido(alb);
		muestraFilas(alb);
		if (alb!=null)
			llenaFicha();
		
	}

	public void nuevaFila() {
		FilasAlbaranCliente fil = new FilasAlbaranCliente();
		fil.setAlbaranCliente(alb);
		vFilaAlb=new VFilaAlbaranCliente(this,fil);
		CtrlFilaAlbCliente controla=new CtrlFilaAlbCliente(vFilaAlb);
		vFilaAlb.establecerControlador(controla);
		panel.setPreferredSize(new Dimension(710,panel.getHeight()+23));
		vFilaAlb.setPreferredSize(new Dimension(710,30));
		panel.add(vFilaAlb);
		panel.updateUI();
	}
	
	public void llenaFicha() {
		tNumAlb.setText(String.valueOf(alb.getNum()));
		comboCliente.setSelectedItem(alb.getClienteBean());
		cFecha.setDate(alb.getFecha());
		checAlmacen.setSelected(alb.isActualizadoAlmacen());
		checFacturado.setSelected(alb.getFacturado());
		if (alb.isActualizadoAlmacen()) {
			tNumAlb.setFocusable(false);
			comboCliente.setFocusable(false);
		}

	}
	
	public void muestraFilas(AlbaranCliente albaran) {
		
		List<FilasAlbaranCliente> filas=alb.getFilasAlbaranClientes();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,filas.size()*30));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		
		scrollPendientes.setViewportView(panel);
		for (FilasAlbaranCliente fil:filas) {
			vFilaAlb=new VFilaAlbaranCliente(this,fil);
			CtrlFilaAlbCliente controla=new CtrlFilaAlbCliente(vFilaAlb);
			vFilaAlb.establecerControlador(controla);
			vFilaAlb.setPreferredSize(new Dimension(710,23));
			vFilaAlb.gettCod().setText(String.valueOf(fil.getArticuloBean().getCod()));
			vFilaAlb.getArticulo().setSelectedItem(fil.getArticuloBean());
			vFilaAlb.gettUnidades().setText(formatoentero.format(fil.getCantidad()));
			vFilaAlb.gettPrecio().setText(formatoeuro.format(fil.getPrecio()));
			vFilaAlb.gettTotal().setText(formatoeuro.format(fil.getCantidad()*fil.getPrecio()));
			if (albaran.isActualizadoAlmacen()) {
				vFilaAlb.gettCod().setFocusable(false);
				vFilaAlb.getArticulo().setEnabled(false);
				vFilaAlb.gettUnidades().setFocusable(false);
				vFilaAlb.gettPrecio().setFocusable(false);
				vFilaAlb.gettTotal().setFocusable(false);
				vFilaAlb.getbBorrar().setEnabled(false);
			}
			panel.add(vFilaAlb);
			vFilaAlb.updateUI();
		}
		actualizaTotal();
	}
	
	public void actualizaTotal() {

		Component[] filas=panel.getComponents();
		double total=0;
		for (Component fila:filas) {
			VFilaAlbaranCliente fil=(VFilaAlbaranCliente) fila;
			total+=u.euroADoble(fil.gettPrecio().getText())*Integer.parseInt(fil.gettUnidades().getText());
		}
		lBase.setText(formatoeuro.format(total));
		lIva.setText(formatoeuro.format(total*0.1));
		lTotal.setText(formatoeuro.format(total*1.1));
	}

	
	public void correoFactura() {
		JOptionPane.showMessageDialog(new JFrame(),"Se ha generado y enviado la factura correspondiente por EMail al cliente","Factura enviadad",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void establecerControlador(ControladorAlbaranCliente controlador) {
		this.addInternalFrameListener(controlador);
		comboCliente.getEditor().getEditorComponent().addFocusListener(controlador);
		bNuevaFila.addActionListener(controlador);
		checAlmacen.addActionListener(controlador);
	}
	
	public JComboBox<Cliente> getComboCliente() {
		return comboCliente;
	}


	public AlbaranCliente getAlb() {
		return alb;
	}

	public JTextField gettNumAlb() {
		return tNumAlb;
	}

	public JDateChooser getcFecha() {
		return cFecha;
	}

	public JCheckBox getChecAlmacen() {
		return checAlmacen;
	}
	

	public JCheckBox getChecFacturado() {
		return checFacturado;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getbNuevaFila() {
		return bNuevaFila;
	}

	public VAlbaranesClientes getvAlbsCli() {
		return vAlbsCli;
	}
}
