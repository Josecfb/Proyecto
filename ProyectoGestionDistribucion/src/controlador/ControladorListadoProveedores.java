package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.ListadoProveedores;

public class ControladorListadoProveedores implements ActionListener{
	private ListadoProveedores listado;
	
	public ControladorListadoProveedores(ListadoProveedores listado) {
		listar(listado);		
	}

	public void listar(ListadoProveedores listado) {
		this.listado=listado;
		List<Proveedor> filas;
		GestorProveedor gp=new GestorProveedor();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		System.out.println(filas.size());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) {
			listado.getPanel().updateUI();
			listar(listado);
			System.out.println("Boton filtro");
		}
	}
	
}
