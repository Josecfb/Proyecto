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
		System.out.println("hola"+filaListadoArticulos.getNombre().getText());
		GestorArticulo ga=new GestorArticulo();
		if(e.getSource()==filaListadoArticulos.getbEditar())
			fa=new VFichaArticulo(ga.existe(Integer.valueOf(filaListadoArticulos.getCodigo().getText())));
		else
			fa=new VFichaArticulo(null);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		filaListadoArticulos.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}

}
