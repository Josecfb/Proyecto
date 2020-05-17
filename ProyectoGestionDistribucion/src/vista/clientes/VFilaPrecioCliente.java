package vista.clientes;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controlador.clientes.ControlaFilaPrecioCli;
import model.Articulo;
import model.Cliente;
import model.PrecioCliente;
import modelo.negocio.GestorArticulo;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VFilaPrecioCliente extends JPanel {
	private static final long serialVersionUID = 1396732755308443918L;
	private Cliente cli;
	private JTextField tCodArt;
	private JTextField tPorcent;
	private JTextField tPrecio;
	private JLabel lPrecioReal;
	private JComboBox<Articulo> comboArt;
	private JButton bNuevo;

	public VFilaPrecioCliente(Cliente cli) {
		this.cli=cli;
		setLayout(null);
		
		tCodArt = new JTextField();
		tCodArt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tCodArt.setBounds(10, 5, 41, 20);
		add(tCodArt);
		tCodArt.setColumns(10);
		
		tPorcent = new JTextField();
		tPorcent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tPorcent.setColumns(10);
		tPorcent.setBounds(423, 5, 64, 20);
		tPorcent.setText("0,00 %");
		add(tPorcent);
		
		tPrecio = new JTextField();
		tPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tPrecio.setColumns(10);
		tPrecio.setBounds(497, 5, 84, 20);
		tPrecio.setText("0,00 €");
		add(tPrecio);
		
		comboArt = new JComboBox<Articulo>();
		comboArt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboArt.setBounds(61, 5, 352, 20);
		comboArt.setEditable(true);
		GestorArticulo ga=new GestorArticulo();
		List<Articulo> artis=ga.listar("");
		for (Articulo art:artis)
			comboArt.addItem(art);
		comboArt.setSelectedItem(null);
		AutoCompleteDecorator.decorate(comboArt);
		add(comboArt);
		
		
		bNuevo = new JButton("");
		bNuevo.setBounds(679, 5, 20, 20);
		add(bNuevo);
		
		lPrecioReal = new JLabel("");
		lPrecioReal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lPrecioReal.setBounds(591, 5, 67, 20);
		add(lPrecioReal);

	}
	
	public void establecerControlador(ControlaFilaPrecioCli controlador) {
		bNuevo.addActionListener(controlador);
		Component[] componentes=getComponents();
		JTextField jt=null;
		for (Component componente:componentes) 
			if (componente.getClass()==JTextField.class) {
				jt = (JTextField) componente;
				jt.addFocusListener(controlador);
			}
		comboArt.getEditor().getEditorComponent().addFocusListener(controlador);
	}

	public JTextField gettCodArt() {
		return tCodArt;
	}

	public JTextField gettPorcent() {
		return tPorcent;
	}

	public JTextField gettPrecio() {
		return tPrecio;
	}

	public JComboBox<Articulo> getComboArt() {
		return comboArt;
	}

	public JButton getbNuevo() {
		return bNuevo;
	}

	public JLabel getlPrecioReal() {
		return lPrecioReal;
	}

	public Cliente getCli() {
		return cli;
	}
	
	
	
}
