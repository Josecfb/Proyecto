package controlador;

import vista.VentanaPrincipal;
/**
 * 
 * @author Jose Carlos
 *
 */
public class Principal {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		ControladorPrincipal controladorPrincipal=new ControladorPrincipal(ventanaPrincipal);
		ventanaPrincipal.establecerControlador(controladorPrincipal);
	}
}
