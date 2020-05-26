package controlador.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorCliente;
import vista.clientes.VFichaCliente;
import vista.clientes.VFilaListadoClientes;

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
			fc=new VFichaCliente(gc.existe(Integer.valueOf(filaListadoClientes.getNumero().getText())),filaListadoClientes.getVListCli());
		else
			fc=new VFichaCliente(null,filaListadoClientes.getVListCli());
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		filaListadoClientes.getVListCli().getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}

}
