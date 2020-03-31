package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.fichas.ControladorFichaArticulo;
import modelo.negocio.GestorArticulo;
import vista.VFilaListadoArticulos;
import vista.fichas.VFichaArticulo;


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
