package vista.clientes.pedidos;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controlador.clientes.pedidos.ControladorFilaPedidoCliente;
import controlador.proveedores.pedidos.ControladorFilaPedidoProveedor;
import model.Articulo;
import model.FilaPedidoProveedor;
import model.FilasPedidosCliente;
import model.PedidoProveedor;
import modelo.negocio.GestorArticulo;
import java.awt.Font;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VFilaPedidoCliente extends JPanel {

	private static final long serialVersionUID = -3446443914975183188L;
	private JTextField tCod;
	private JComboBox<Articulo> articulo;
	private JTextField tUnidades;
	private JTextField tCajas;
	private JTextField tCoste;
	private JTextField tTotal;
	private JButton bBorrar;
	private PedidoProveedor ped;
	private VPedidoCliente vPedido;
	private FilasPedidosCliente fila;

	public VFilaPedidoCliente(VPedidoCliente vPedido,FilasPedidosCliente fila) {
		this.fila=fila;
		this.vPedido=vPedido;
		setBackground(SystemColor.control);
		setLayout(null);
		
		tCod = new JTextField();
		tCod.setBounds(10, 1, 47, 20);
		tCod.setHorizontalAlignment(JTextField.RIGHT);
		tCod.setText("0");
		add(tCod);
		tCod.setColumns(10);
		articulo = new JComboBox<Articulo>();
		articulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		articulo.setEditable(true);
		articulo.setSelectedItem(null);
		articulo.setBounds(67, 1, 363, 20);
		articulo.setAutoscrolls(true);
		AutoCompleteDecorator.decorate(articulo);
		add(articulo);
		
		tUnidades = new JTextField();
		tUnidades.setColumns(10);
		tUnidades.setBounds(482, 1, 32, 20);
		tUnidades.setHorizontalAlignment(JTextField.RIGHT);
		tUnidades.setText("0");
		tUnidades.setFocusable(false);
		add(tUnidades);
		
		tCajas = new JTextField();
		tCajas.setColumns(10);
		tCajas.setBounds(440, 1, 32, 20);
		tCajas.setHorizontalAlignment(JTextField.RIGHT);
		tCajas.setText("0");
		add(tCajas);
		
		tCoste = new JTextField();
		tCoste.setColumns(10);
		tCoste.setBounds(524, 1, 64, 20);
		tCoste.setHorizontalAlignment(JTextField.RIGHT);
		tCoste.setText("0,00 €");
		add(tCoste);
		
		tTotal = new JTextField();
		tTotal.setColumns(10);
		tTotal.setBounds(598, 1, 76, 20);
		tTotal.setHorizontalAlignment(JTextField.RIGHT);
		tTotal.setFocusable(false);
		tTotal.setText("0,00 €");
		add(tTotal);
		
		bBorrar = new JButton("");
		bBorrar.setBounds(681, 0, 20, 20);
		bBorrar.setIcon(new ImageIcon("src/img/borrarfila.png"));
		add(bBorrar);
		setVisible(true);
	}


	
	public void establecerControlador(ControladorFilaPedidoCliente controla) {
		Component[] componentes=getComponents();
		JTextField jt=null;
		for (Component componente:componentes) 
			if (componente.getClass()==JTextField.class) {
				jt = (JTextField) componente;
				jt.addFocusListener(controla);
			}
		articulo.getEditor().getEditorComponent().addFocusListener(controla);
		bBorrar.addActionListener(controla);
	}

	public JTextField gettCod() {
		return tCod;
	}



	public JComboBox<Articulo> getArticulo() {
		return articulo;
	}

	public JTextField gettUnidades() {
		return tUnidades;
	}

	public JTextField gettCajas() {
		return tCajas;
	}

	public JTextField gettCoste() {
		return tCoste;
	}

	public JTextField gettTotal() {
		return tTotal;
	}

	public PedidoProveedor getPed() {
		return ped;
	}

	public JButton getbBorrar() {
		return bBorrar;
	}

	public VPedidoCliente getvPedido() {
		return vPedido;
	}

	public FilasPedidosCliente getFila() {
		return fila;
	}
	
	
}
