package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.ListadoProveedores;
import vista.VentanaPrincipal;

public class ControladorListadoProveedores implements ActionListener{
	private ListadoProveedores listado;

	
	public ControladorListadoProveedores(ListadoProveedores listado) {
		this.listado=listado;
		GestorProveedor gp=new GestorProveedor();
		List<Proveedor> filas=gp.listar();
		System.out.println(filas.size());
		listado.muestra(filas);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
