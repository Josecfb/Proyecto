package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Cliente;
import modelo.negocio.GestorCliente;
import vista.ListadoClientes;
import vista.ListadoProveedores;

public class ControladorListadoClientes implements ActionListener{
	private ListadoClientes listado;

	
	public ControladorListadoClientes(ListadoClientes listado) {
		this.listado=listado;
		GestorCliente gc=new GestorCliente();
		List<Cliente> filas=gc.listar();
		System.out.println(filas.size());
		listado.muestra(filas);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
