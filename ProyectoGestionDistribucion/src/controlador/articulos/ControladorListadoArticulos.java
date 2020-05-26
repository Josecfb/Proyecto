package controlador.articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;

import entidades.Articulo;
import modelo.negocio.GestorArticulo;
import vista.articulos.VFichaArticulo;
import vista.articulos.VListadoArticulos;

public class ControladorListadoArticulos implements ActionListener{
	private VListadoArticulos listado;
	
	public ControladorListadoArticulos(VListadoArticulos listado) {
		this.listado=listado;
		listar();		
	}

	public void listar() {
		List<Articulo> filas;
		GestorArticulo gp=new GestorArticulo();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==listado.getbFiltrar()) 
			listar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoArticulo();
		if (e.getSource()==listado.getbActualizar())
			listar();
		
	}

	private void muestraFiltros() {
		if (listado.getbFiltrar().isVisible()) {
			listado.getbFiltrar().setVisible(false);
			listado.getTFiltroNombre().setVisible(false);
			listado.getTFiltroNombre().setText("");
			listar();
			listado.getbFiltros().setIcon(new ImageIcon("src/img/filtro.png"));
		}
		else {
			listado.getbFiltrar().setVisible(true);
			listado.getTFiltroNombre().setVisible(true);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/nofiltro.png"));
		}
	}

	private void nuevoArticulo() {
		//GestorArticulo ga=new GestorArticulo();
		VFichaArticulo fa=new VFichaArticulo(null,listado);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		listado.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}
}
