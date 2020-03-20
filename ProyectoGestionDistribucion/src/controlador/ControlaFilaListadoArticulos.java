package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.negocio.GestorArticulo;
import vista.FichaArticulo;
import vista.FilaListadoArticulos;


public class ControlaFilaListadoArticulos implements ActionListener{
	private FilaListadoArticulos filaListadoArticulos;
	
	public ControlaFilaListadoArticulos(FilaListadoArticulos filaListadoArticulos) {
		this.filaListadoArticulos=filaListadoArticulos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FichaArticulo fa;
		System.out.println("hola"+filaListadoArticulos.getNombre().getText());
		GestorArticulo ga=new GestorArticulo();
		if(e.getSource()==filaListadoArticulos.getbEditar())
			fa=new FichaArticulo(ga.existe(Integer.valueOf(filaListadoArticulos.getCodigo().getText())));
		else
			fa=new FichaArticulo(null);
		ControladorFichaArticulo cfa=new ControladorFichaArticulo(fa);
		fa.EstablecerManejadorVentana(cfa);
		filaListadoArticulos.getV().getPanelInterior().add(fa);
		fa.setVisible(true);
	}

}
