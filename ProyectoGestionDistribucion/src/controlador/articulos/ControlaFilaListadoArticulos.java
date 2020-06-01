package controlador.articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorArticulo;
import vista.articulos.VFichaArticulo;
import vista.articulos.VFilaListadoArticulos;

/**
 * Controla la pulsación del boton editar articulo de cada fila del listado de artículos
 * @author Jose Carlos
 *
 */
public class ControlaFilaListadoArticulos implements ActionListener{
	private VFilaListadoArticulos filaListadoArticulos;
	/**
	 * Recibe la vista de una fila de articulo del listado de artículos
	 * @param filaListadoArticulos vista de una fila de articulo del listado de artículos
	 */
	public ControlaFilaListadoArticulos(VFilaListadoArticulos filaListadoArticulos) {
		this.filaListadoArticulos=filaListadoArticulos;
	}
	/**
	 * Si se pulsa el botón editar, abre una ventana dentro de la principal con la ficha de ese artículo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		VFichaArticulo fa;
		GestorArticulo ga=new GestorArticulo();
		if(e.getSource()==filaListadoArticulos.getbEditar())
			fa=new VFichaArticulo(ga.existe(Integer.valueOf(filaListadoArticulos.getCodigo().getText())),filaListadoArticulos.getV());
		else
			fa=new VFichaArticulo(null,filaListadoArticulos.getV());
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		filaListadoArticulos.getV().getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}

}
