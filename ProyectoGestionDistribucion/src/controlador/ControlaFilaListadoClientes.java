package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.fichas.ControladorFichaCliente;
import modelo.negocio.GestorCliente;
import vista.VFilaListadoClientes;
import vista.fichas.VFichaCliente;

public class ControlaFilaListadoClientes implements ActionListener{
	private VFilaListadoClientes filaListadoClientes;
	
	public ControlaFilaListadoClientes(VFilaListadoClientes filaListadoClientes) {
		this.filaListadoClientes=filaListadoClientes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("editar cli");
		VFichaCliente fc;
		GestorCliente gc=new GestorCliente();
		if(e.getSource()==filaListadoClientes.getbEditar())
			fc=new VFichaCliente(gc.existe(Integer.valueOf(filaListadoClientes.getNumero().getText())));
		else
			fc=new VFichaCliente(null);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		filaListadoClientes.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}

}
