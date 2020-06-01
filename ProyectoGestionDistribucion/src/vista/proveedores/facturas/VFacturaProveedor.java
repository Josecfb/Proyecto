package vista.proveedores.facturas;


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

import controlador.proveedores.facturas.ControladorFacturaProveedor;
import controlador.proveedores.facturas.CtrlFilaFactProve;
import entidades.FacturaProveedor;
import entidades.FilaFacturaProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorProveedor;
import util.Utilidades;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VFacturaProveedor extends JInternalFrame {

	private static final long serialVersionUID = 4339097703466328107L;
	private VFacturasProveedores vFactsPro;
	private JDateChooser cFecha;
	private JComboBox<Proveedor> comboProveedor;
	private FacturaProveedor fact;
	private JScrollPane scrollPendientes;
	private VFilaFacturaProveedor vFilaFact;
	private JPanel panel;
	private JLabel lBase,lIva,lTotal;
	private NumberFormat formatoeuro, formatoentero;
	private JTextField tNumFact;
	private JCheckBox checPagada;
	private JButton bNuevaFila;
	private Utilidades u;


	public VFacturaProveedor(FacturaProveedor fact,VFacturasProveedores vFactsPro) {
		this.vFactsPro=vFactsPro;
		this.fact=fact;
		formatoeuro = NumberFormat.getCurrencyInstance();
		formatoentero=NumberFormat.getIntegerInstance();
		u=new Utilidades();
		setBounds(100, 100, 759, 519);
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
		
		lBase = new JLabel("");
		lBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lBase.setBounds(610, 392, 89, 25);
		lBase.setHorizontalAlignment(JTextField.RIGHT);
		getContentPane().add(lBase);
		
		lIva = new JLabel("0,00 \u20AC");
		lIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lIva.setBounds(610, 423, 89, 25);
		lIva.setHorizontalAlignment(JTextField.RIGHT);
		getContentPane().add(lIva);
		
		lTotal = new JLabel("0,00 \u20AC");
		lTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lTotal.setBounds(610, 453, 89, 25);
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
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
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
		
		tNumFact = new JTextField();
		tNumFact.setColumns(10);
		tNumFact.setBounds(94, 24, 64, 25);
		tNumFact.setFocusable(false);
		getContentPane().add(tNumFact);
		
		checPagada = new JCheckBox("Pagada");
		checPagada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checPagada.setBounds(311, 61, 141, 23);
		getContentPane().add(checPagada);

		bNuevaFila = new JButton("");
		bNuevaFila.setBounds(578, 60, 35, 35);
		bNuevaFila.setIcon(new ImageIcon("src/img/nuevafila.png"));
		getContentPane().add(bNuevaFila);
		
		JLabel lblBase = new JLabel("Base");
		lblBase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBase.setBounds(511, 392, 89, 25);
		getContentPane().add(lblBase);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIva.setBounds(511, 423, 89, 25);
		getContentPane().add(lblIva);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(511, 453, 89, 25);
		getContentPane().add(lblTotal);
		
		setTitle("Factura Proveedor");
		//contrAlbPro.articulosPendientesPedido(alb);
		muestraFilas(fact);
		if (fact!=null)
			llenaFicha();
		
	}

	public void nuevaFila() {
		FilaFacturaProveedor fil = new FilaFacturaProveedor();
		fil.setFacturasProveedor(fact);
		vFilaFact=new VFilaFacturaProveedor(this,fil);
		CtrlFilaFactProve controla=new CtrlFilaFactProve(vFilaFact);
		vFilaFact.establecerControlador(controla);
		panel.setPreferredSize(new Dimension(710,panel.getHeight()+23));
		vFilaFact.setPreferredSize(new Dimension(710,30));
		panel.add(vFilaFact);
		panel.updateUI();
	}
	
	public void llenaFicha() {
		tNumFact.setText(String.valueOf(fact.getNum()));
		comboProveedor.setSelectedItem(fact.getProveedore());
		cFecha.setDate(fact.getFecha());
		checPagada.setSelected(fact.getPagada());
		if (fact.getPagada()) {
			tNumFact.setFocusable(false);
			comboProveedor.setFocusable(false);
		}

	}
	
	public void muestraFilas(FacturaProveedor factura) {
		
		List<FilaFacturaProveedor> filas=fact.getFilasFacturasProveedors();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,filas.size()*30));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		
		scrollPendientes.setViewportView(panel);
		for (FilaFacturaProveedor fil:filas) {
			vFilaFact=new VFilaFacturaProveedor(this,fil);
			CtrlFilaFactProve controla=new CtrlFilaFactProve(vFilaFact);
			vFilaFact.establecerControlador(controla);
			vFilaFact.setPreferredSize(new Dimension(710,23));
			vFilaFact.gettCod().setText(String.valueOf(fil.getArticuloBean().getCod()));
			vFilaFact.getArticulo().setSelectedItem(fil.getArticuloBean());
			vFilaFact.gettUnidades().setText(formatoentero.format(fil.getCantidad()));
			vFilaFact.gettCajas().setText(String.valueOf(fil.getCantidad()/fil.getArticuloBean().getUnidadesCaja()));
			vFilaFact.gettCoste().setText(formatoeuro.format(fil.getArticuloBean().getCoste()));
			vFilaFact.gettTotal().setText(formatoeuro.format(fil.getCantidad()*fil.getArticuloBean().getCoste()));
			if (factura.getPagada()) {
				vFilaFact.gettCod().setFocusable(false);
				vFilaFact.getArticulo().setEnabled(false);
				vFilaFact.gettUnidades().setFocusable(false);
				vFilaFact.gettCajas().setFocusable(false);
				vFilaFact.gettCoste().setFocusable(false);
				vFilaFact.gettTotal().setFocusable(false);
				vFilaFact.getbBorrar().setEnabled(false);
			}
			panel.add(vFilaFact);
			vFilaFact.updateUI();
		}
		actualizaTotal();
	}
	
	public void actualizaTotal() {

		Component[] filas=panel.getComponents();
		double total=0;
		for (Component fila:filas) {
			VFilaFacturaProveedor fil=(VFilaFacturaProveedor) fila;
			total+=u.euroADoble(fil.gettCoste().getText())*Integer.parseInt(fil.gettUnidades().getText());
		}
		lBase.setText(formatoeuro.format(total));
		lIva.setText(formatoeuro.format(total*0.1));
		lTotal.setText(formatoeuro.format(total*1.1));
	}
	
	public void establecerControlador(ControladorFacturaProveedor controlador) {
		this.addInternalFrameListener(controlador);
		comboProveedor.getEditor().getEditorComponent().addFocusListener(controlador);
		bNuevaFila.addActionListener(controlador);
		checPagada.addActionListener(controlador);
	}
	
	public JComboBox<Proveedor> getComboProveedor() {
		return comboProveedor;
	}


	public FacturaProveedor getFact() {
		return fact;
	}

	public JTextField gettNumAlb() {
		return tNumFact;
	}

	public JDateChooser getcFecha() {
		return cFecha;
	}

	public JCheckBox getChecPagada() {
		return checPagada;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getbNuevaFila() {
		return bNuevaFila;
	}
	
	public VFacturasProveedores getvFactsPro() {
		return vFactsPro;
	}

}
