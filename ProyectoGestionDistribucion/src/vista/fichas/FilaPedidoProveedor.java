package vista.fichas;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Proveedor;

public class FilaPedidoProveedor extends JPanel {
	private JTextField tCProv;
	private JTextField tNomArt;
	private JTextField tUnidades;
	private JTextField tCajas;
	private JTextField tCoste;
	private JTextField tTotal;

	public FilaPedidoProveedor() {

		setLayout(null);
		
		tCProv = new JTextField();
		tCProv.setBounds(20, 5, 47, 20);
		add(tCProv);
		tCProv.setColumns(10);
		
		tNomArt = new JTextField();
		tNomArt.setColumns(10);
		tNomArt.setBounds(77, 5, 297, 20);
		add(tNomArt);
		
		tUnidades = new JTextField();
		tUnidades.setColumns(10);
		tUnidades.setBounds(384, 5, 32, 20);
		add(tUnidades);
		
		tCajas = new JTextField();
		tCajas.setColumns(10);
		tCajas.setBounds(426, 5, 47, 20);
		add(tCajas);
		
		tCoste = new JTextField();
		tCoste.setColumns(10);
		tCoste.setBounds(483, 5, 64, 20);
		add(tCoste);
		
		tTotal = new JTextField();
		tTotal.setColumns(10);
		tTotal.setBounds(557, 5, 76, 20);
		add(tTotal);
		setVisible(true);
	}

	public JTextField gettCProv() {
		return tCProv;
	}

	public JTextField gettNomArt() {
		return tNomArt;
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
	
	

}
