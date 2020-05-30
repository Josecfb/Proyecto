package controlador.articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;

import entidades.Articulo;
import modelo.negocio.GestorArticulo;
import vista.articulos.VFichaArticulo;
import vista.articulos.VListadoArticulos;
/**
 * Controla las acciones dentro del listado de articulos implementa ActionListener
 * @author Jose Carlos
 *
 */
public class ControladorListadoArticulos implements ActionListener{
	private VListadoArticulos listado;
	/**
	 * El constructor recibe la ventana del listado de artículos
	 * @param listado
	 */
	public ControladorListadoArticulos(VListadoArticulos listado) {
		this.listado=listado;
		listar();		
	}
	/**
	 * lee la lista de articulos y la muestra dentro de la ventana del listado de artículos
	 */
	public void listar() {
		List<Articulo> filas;
		GestorArticulo gp=new GestorArticulo();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	/**
	 * Al pulsar un botón
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Botón filtrar
		if (e.getSource()==listado.getbFiltrar()) 
			listar();
		// Botón filtro
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		// Botón nuevo artículo
		if (e.getSource()==listado.getbNuevo())
			nuevoArticulo();
		// Botón Actualizar listado de artículos
		if (e.getSource()==listado.getbActualizar())
			listar();
		
	}
	/**
	 * activa y desactiva el botón filtro y el cuadro donde se escribe el filtro para nombre de artículo
	 */
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
	/**
	 * Abre una nueva ventana de ficha de artículo vacía
	 */
	private void nuevoArticulo() {
		VFichaArticulo fa=new VFichaArticulo(null,listado);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		listado.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}
}
