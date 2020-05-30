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
	 * El constructor recibe la ventana del listado de art�culos
	 * @param listado
	 */
	public ControladorListadoArticulos(VListadoArticulos listado) {
		this.listado=listado;
		listar();		
	}
	/**
	 * lee la lista de articulos y la muestra dentro de la ventana del listado de art�culos
	 */
	public void listar() {
		List<Articulo> filas;
		GestorArticulo gp=new GestorArticulo();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	/**
	 * Al pulsar un bot�n
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Bot�n filtrar
		if (e.getSource()==listado.getbFiltrar()) 
			listar();
		// Bot�n filtro
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		// Bot�n nuevo art�culo
		if (e.getSource()==listado.getbNuevo())
			nuevoArticulo();
		// Bot�n Actualizar listado de art�culos
		if (e.getSource()==listado.getbActualizar())
			listar();
		
	}
	/**
	 * activa y desactiva el bot�n filtro y el cuadro donde se escribe el filtro para nombre de art�culo
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
	 * Abre una nueva ventana de ficha de art�culo vac�a
	 */
	private void nuevoArticulo() {
		VFichaArticulo fa=new VFichaArticulo(null,listado);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		listado.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}
}
