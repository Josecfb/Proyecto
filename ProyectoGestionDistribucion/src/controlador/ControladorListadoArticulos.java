package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Articulo;
import modelo.negocio.GestorArticulo;
import vista.ListadoArticulos;

public class ControladorListadoArticulos implements ActionListener{
	private ListadoArticulos listado;

	
	public ControladorListadoArticulos(ListadoArticulos listado) {
		listar(listado);		
	}

	public void listar(ListadoArticulos listado) {
		this.listado=listado;
		List<Articulo> filas;
		GestorArticulo gp=new GestorArticulo();
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
