package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VentanaPrincipal;


public class ControladorPrincipal implements ActionListener{
	private VentanaPrincipal ventanaPrincipal;

	
	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ventanaPrincipal.getListarProveedores() || e.getSource()==ventanaPrincipal.getbProveedores()) 
			ventanaPrincipal.listadoProveedores();
		if (e.getSource()==ventanaPrincipal.getListarClientes() || e.getSource()==ventanaPrincipal.getbClientes())
			ventanaPrincipal.listadoClientes();
		if (e.getSource()==ventanaPrincipal.getListarArticulos() || e.getSource()==ventanaPrincipal.getbArticulos())
			ventanaPrincipal.listadoArticulos();
		
	}

}
