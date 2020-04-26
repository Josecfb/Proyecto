package controlador.articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorArticulo;
import vista.articulos.VFichaArticulo;
import vista.articulos.VFilaListadoArticulos;


public class ControlaFilaListadoArticulos implements ActionListener{
	private VFilaListadoArticulos filaListadoArticulos;
	
	public ControlaFilaListadoArticulos(VFilaListadoArticulos filaListadoArticulos) {
		this.filaListadoArticulos=filaListadoArticulos;
	}

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
		filaListadoArticulos.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}

}
