package vista.clientes.facturas;

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
import controlador.clientes.facturas.ControladorFacturaCliente;
import controlador.clientes.facturas.CtrlFilaFactCliente;
import entidades.Cliente;
import entidades.FacturaCliente;
import entidades.FilaFacturaCliente;
import modelo.negocio.GestorCliente;
import util.Utilidades;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VFacturaCliente extends JInternalFrame {

	private static final long serialVersionUID = 4339097703466328107L;
	private VFacturasClientes vFactsCli;
	private JDateChooser cFecha;
	private JComboBox<Cliente> comboCliente;
	private FacturaCliente fact;
	private JScrollPane scrollPendientes;
	private VFilaFacturaCliente vFilaFact;
	private JPanel panel;
	private JLabel lBase,lIva,lTotal;
	private NumberFormat formatoeuro, formatoentero;
	private JTextField tNumFact;
	private JCheckBox checPagada;
	private JButton bNuevaFila;
	private Utilidades u;


	public VFacturaCliente(FacturaCliente fact,VFacturasClientes vFactsCli) {
		this.vFactsCli=vFactsCli;
		this.fact=fact;
		u=new Utilidades();
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
		for (Cliente cl:new GestorCliente().listar("")) 
			comboCliente.addItem(cl);
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
		lblNewLabel_1.setBounds(27, 102, 45, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(82, 102, 375, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Unidades");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBackground(new Color(0, 0, 128));
		lblNewLabel_4.setBounds(475, 102, 55, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBackground(new Color(0, 0, 128));
		lblNewLabel_5.setBounds(540, 102, 64, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBackground(new Color(0, 0, 128));
		lblNewLabel_6.setBounds(614, 102, 76, 18);
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
		
		setTitle("Factura Cliente");
		//contrAlbPro.articulosPendientesPedido(alb);
		muestraFilas(fact);
		if (fact!=null)
			llenaFicha();
		
	}

	public void nuevaFila() {
		FilaFacturaCliente fil = new FilaFacturaCliente();
		fil.setFacturasCliente(fact);
		vFilaFact=new VFilaFacturaCliente(this,fil);
		CtrlFilaFactCliente controla=new CtrlFilaFactCliente(vFilaFact);
		vFilaFact.establecerControlador(controla);
		panel.setPreferredSize(new Dimension(710,panel.getHeight()+23));
		vFilaFact.setPreferredSize(new Dimension(710,30));
		panel.add(vFilaFact);
		panel.updateUI();
	}
	
	public void llenaFicha() {
		tNumFact.setText(String.valueOf(fact.getNum()));
		comboCliente.setSelectedItem(fact.getCliente());
		cFecha.setDate(fact.getFecha());
		checPagada.setSelected(fact.getPagada());
		if (fact.getPagada()) {
			tNumFact.setFocusable(false);
			comboCliente.setFocusable(false);
		}

	}
	
	public void muestraFilas(FacturaCliente factura) {
		
		List<FilaFacturaCliente> filas=fact.getFilasFacturasClientes();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(710,filas.size()*30));
		panel.setBackground(SystemColor.control);
		panel.setBorder(null);
		
		scrollPendientes.setViewportView(panel);
		for (FilaFacturaCliente fil:filas) {
			vFilaFact=new VFilaFacturaCliente(this,fil);
			CtrlFilaFactCliente controla=new CtrlFilaFactCliente(vFilaFact);
			vFilaFact.establecerControlador(controla);
			vFilaFact.setPreferredSize(new Dimension(710,23));
			vFilaFact.gettCod().setText(String.valueOf(fil.getArticuloBean().getCod()));
			vFilaFact.getArticulo().setSelectedItem(fil.getArticuloBean());
			vFilaFact.gettUnidades().setText(formatoentero.format(fil.getCantidad()));
			vFilaFact.getTPrecio().setText(formatoeuro.format(fil.getArticuloBean().getCoste()));
			vFilaFact.gettTotal().setText(formatoeuro.format(fil.getCantidad()*fil.getArticuloBean().getCoste()));
			if (factura.getPagada()) {
				vFilaFact.gettCod().setFocusable(false);
				vFilaFact.getArticulo().setEnabled(false);
				vFilaFact.gettUnidades().setFocusable(false);
				vFilaFact.getTPrecio().setFocusable(false);
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
			VFilaFacturaCliente fil=(VFilaFacturaCliente) fila;
			total+=u.euroADoble(fil.getTPrecio().getText())*Integer.parseInt(fil.gettUnidades().getText());
		}
		lBase.setText(formatoeuro.format(total));
		lIva.setText(formatoeuro.format(total*0.1));
		lTotal.setText(formatoeuro.format(total*1.1));
	}
	
	public void establecerControlador(ControladorFacturaCliente controlador) {
		this.addInternalFrameListener(controlador);
		comboCliente.getEditor().getEditorComponent().addFocusListener(controlador);
		bNuevaFila.addActionListener(controlador);
		checPagada.addActionListener(controlador);
	}
	
	public JComboBox<Cliente> getComboCliente() {
		return comboCliente;
	}


	public FacturaCliente getFact() {
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
	
	public VFacturasClientes getvFactsPro() {
		return vFactsCli;
	}

}
