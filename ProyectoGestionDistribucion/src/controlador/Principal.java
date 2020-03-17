package controlador;

import vista.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		ControladorPrincipal controladorPrincipal=new ControladorPrincipal(ventanaPrincipal);
		ventanaPrincipal.establecerControlador(controladorPrincipal);
	}
}
