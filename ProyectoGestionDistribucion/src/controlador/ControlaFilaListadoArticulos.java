package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorArticulo;
import vista.FichaProveedor;
import vista.FilaListadoArticulos;
import vista.VentanaPrincipal;

public class ControlaFilaListadoArticulos implements ActionListener{
	private FilaListadoArticulos filaListadoArticulos;
	
	public ControlaFilaListadoArticulos(FilaListadoArticulos filaListadoArticulos) {
		this.filaListadoArticulos=filaListadoArticulos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hola");
		if(e.getSource()==filaListadoArticulos.getbEditar()) {
			System.out.println("hola"+filaListadoArticulos.getNombre().getText());
			GestorArticulo ga=new GestorArticulo();
			//FichaProveedor fp=new FichaProveedor(gp.existe(Integer.valueOf(filaListadoArticulos.getNumero().getText())));
//			ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
//			fp.EstablecerManejadorVentana(cfp);
//			filaListadoArticulos.getV().getPanelInterior().add(fp);
//			fp.setVisible(true);
		}
	}

}
