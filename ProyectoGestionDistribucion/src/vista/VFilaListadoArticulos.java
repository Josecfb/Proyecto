package vista;

import javax.swing.JPanel;

import controlador.ControlaFilaListadoArticulos;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VFilaListadoArticulos extends JPanel {

	private static final long serialVersionUID = 4813844448451936303L;
	private VentanaPrincipal v;
	private JLabel codigo, cProv, nombre,familia, coste, precioMay, precioMin, stock, stockMin;
	private JButton bEditar;

	public VFilaListadoArticulos(VentanaPrincipal v) {
		this.v=v;
		setLayout(null);
		setSize(975, 20);
		setBackground(new Color(240,240,240));
		inicializar();
		setVisible(true);
	}
	
	private void inicializar() {
		codigo=new JLabel();
		cProv=new JLabel();
		nombre=new JLabel();
		familia=new JLabel();
		coste=new JLabel();
		precioMay=new JLabel();
		precioMin=new JLabel();
		stock=new JLabel();
		stockMin=new JLabel();
		codigo.setBounds(10, 5, 45, 10);
		cProv.setBounds(65, 5, 70, 10);
		nombre.setBounds(145, 5, 267, 10);
		familia.setBounds(422, 5, 129, 10);
		coste.setBounds(561, 5, 70, 10);
		precioMay.setBounds(641, 5, 88, 10);
		precioMin.setBounds(739, 5, 98, 10);
		stock.setBounds(847, 5, 98, 10);
		stockMin.setBounds(935, 5, 98, 10);
		add(codigo);
		add(cProv);
		add(nombre);
		add(familia);
		add(coste);
		add(precioMay);
		add(precioMin);
		add(stock);
		add(stockMin);
		bEditar=new JButton();
		bEditar.setBounds(1070, 0, 20, 20);
		bEditar.setIcon(new ImageIcon("src/img/pen.png"));
		add(bEditar);
	}
	
	public void establecerControlador(ControlaFilaListadoArticulos controlador) {
		bEditar.addActionListener(controlador);
	}

	public JLabel getCodigo() {
		return codigo;
	}

	public JLabel getcProv() {
		return cProv;
	}

	public JLabel getNombre() {
		return nombre;
	}

	public JLabel getCoste() {
		return coste;
	}

	public JLabel getPrecioMay() {
		return precioMay;
	}

	public JLabel getPrecioMin() {
		return precioMin;
	}

	public JLabel getStock() {
		return stock;
	}

	public JLabel getStockMin() {
		return stockMin;
	}

	public JButton getbEditar() {
		return bEditar;
	}

	public void setbEditar(JButton bEditar) {
		this.bEditar = bEditar;
	}

	public VentanaPrincipal getV() {
		return v;
	}

	public JLabel getFamilia() {
		return familia;
	}
	
	
	
}
