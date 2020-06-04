package controlador;

import vista.VentanaPrincipal;
/**
 * Es la clase Principal y tiene el método main con el que arranca el programa
 * @author Jose Carlos
 *
 */
public class Principal {
	/**
	 * 
	 * @param args del maim
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		ControladorPrincipal controladorPrincipal=new ControladorPrincipal(ventanaPrincipal);
		ventanaPrincipal.establecerControlador(controladorPrincipal); 
	}
}
