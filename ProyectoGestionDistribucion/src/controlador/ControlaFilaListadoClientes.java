package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorCliente;
import vista.FichaCliente;
import vista.FilaListadoClientes;

public class ControlaFilaListadoClientes implements ActionListener{
	private FilaListadoClientes filaListadoClientes;
	
	public ControlaFilaListadoClientes(FilaListadoClientes filaListadoClientes) {
		this.filaListadoClientes=filaListadoClientes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("editar cli");
		FichaCliente fc;
		GestorCliente gc=new GestorCliente();
		if(e.getSource()==filaListadoClientes.getbEditar())
			fc=new FichaCliente(gc.existe(Integer.valueOf(filaListadoClientes.getNumero().getText())));
		else
			fc=new FichaCliente(null);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		filaListadoClientes.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}

}
