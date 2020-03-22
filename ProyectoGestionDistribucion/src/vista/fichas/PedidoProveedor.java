package vista.fichas;


import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.fichas.ControladorFilaPedidoPendienteProveedor;
import controlador.fichas.ControladorPedidoProveedor;
import model.Articulo;
import model.Proveedor;
import modelo.negocio.GestorProveedor;

import javax.swing.JScrollPane;

public class PedidoProveedor extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4339097703466328107L;
	private JTextField tFecha;
	private JComboBox<Proveedor> comboProveedor;
	private Proveedor pro;
	private ControladorPedidoProveedor pendiente;
	private JScrollPane scrollPendientes;
	private FilaPedidoProveedor filaPed;
	private JPanel panel;
	private JLabel lTotal;


	public PedidoProveedor(Proveedor pro) {
		this.pro=pro;
		setBounds(100, 100, 759, 465);
		getContentPane().setLayout(null);
		comboProveedor = new JComboBox<Proveedor>();
		comboProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboProveedor.setBounds(137, 34, 370, 25);
		comboProveedor.setEditable(true);
		for (Proveedor pr:new GestorProveedor().listar("")) 
			comboProveedor.addItem(pr);
		comboProveedor.setSelectedItem(pro);
		getContentPane().add(comboProveedor);
		
		JLabel lblNewLabel = new JLabel("Proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(38, 34, 89, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(38, 72, 89, 25);
		getContentPane().add(lblFecha);
		
		tFecha = new JTextField();
		tFecha.setBounds(137, 74, 186, 25);
		getContentPane().add(tFecha);
		tFecha.setColumns(10);
		
		scrollPendientes = new JScrollPane();
		scrollPendientes.setBounds(10, 124, 723, 259);
		getContentPane().add(scrollPendientes);
		
		lTotal = new JLabel("");
		lTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTotal.setBounds(595, 394, 89, 25);
		getContentPane().add(lTotal);
		setTitle("Pedido Proveedor");
		muestraFilasPendientes();
	}
	
	public void muestraFilasPendientes() {
		ControladorPedidoProveedor pendiente=new ControladorPedidoProveedor(this);
		List<Articulo> filas=pendiente.articulosPendientesPedido(this);
		System.out.println("filas "+filas.size());
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(650,filas.size()*30));
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);

		int i=0;
		scrollPendientes.setViewportView(panel);
		double total=0;
		for (Articulo art:filas) {
			i++;
			System.out.println(i);
			filaPed=new FilaPedidoProveedor();
			filaPed.setPreferredSize(new Dimension(650,30));
			filaPed.gettCProv().setText(art.getCodpro());
			filaPed.gettNomArt().setText(art.getNombre());
			DecimalFormat df = new DecimalFormat("#");
			filaPed.gettUnidades().setText(String.valueOf(df.format(Math.ceil((double)(art.getStockMinimo()-art.getStock())/art.getUnidadesCaja())*art.getUnidadesCaja())));
			filaPed.gettCajas().setText(String.valueOf(df.format(Math.ceil((double)(art.getStockMinimo()-art.getStock())/art.getUnidadesCaja()))));
			filaPed.gettCoste().setText(String.valueOf(art.getCoste()));
			filaPed.gettTotal().setText(String.valueOf(art.getCoste()*Math.ceil((double)(art.getStockMinimo()-art.getStock())/art.getUnidadesCaja())*art.getUnidadesCaja()));
			panel.add(filaPed);
			total+=art.getCoste()*Math.ceil((double)(art.getStockMinimo()-art.getStock())/art.getUnidadesCaja())*art.getUnidadesCaja();
		}
		filaPed=new FilaPedidoProveedor();
		filaPed.setPreferredSize(new Dimension(650,30));
		panel.add(filaPed);
		lTotal.setText(String.valueOf(total));
	}


	public JTextField gettFecha() {
		return tFecha;
	}


	public JComboBox getComboProveedor() {
		return comboProveedor;
	}


	public Proveedor getPro() {
		return pro;
	}
}
