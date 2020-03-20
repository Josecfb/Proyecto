package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;

import model.Articulo;
import modelo.negocio.GestorArticulo;
import vista.FichaArticulo;
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
		
		if (e.getSource()==listado.getbFiltrar()) 
			filtrar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoArticulo();
		if (e.getSource()==listado.getbActualizar())
			actualizar();
		
	}

	private void muestraFiltros() {
		if (listado.getbFiltrar().isVisible()) {
			listado.getbFiltrar().setVisible(false);
			listado.getTFiltroNombre().setVisible(false);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/filtro.png"));
		}
		else {
			listado.getbFiltrar().setVisible(true);
			listado.getTFiltroNombre().setVisible(true);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/nofiltro.png"));
		}
	}

	private void filtrar() {
		listado.getPanel().updateUI();
		listar(listado);
	}
	
	private void actualizar() {
		listado.getPanel().updateUI();
	}
	
	private void nuevoArticulo() {
		GestorArticulo ga=new GestorArticulo();
		FichaArticulo fa=new FichaArticulo(null);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		System.out.println("nuevo articulo");
		listado.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}
}
