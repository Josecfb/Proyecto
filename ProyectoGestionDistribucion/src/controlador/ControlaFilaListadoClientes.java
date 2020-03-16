package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FilaListadoClientes;

public class ControlaFilaListadoClientes implements ActionListener{
	private FilaListadoClientes filaListadoClientes;
	
	public ControlaFilaListadoClientes(FilaListadoClientes filaListadoClientes) {
		this.filaListadoClientes=filaListadoClientes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hola");
		if(e.getSource()==filaListadoClientes.getbEditar())
			System.out.println("hola"+filaListadoClientes.getNombreComercial().getText());
		
	}

}
