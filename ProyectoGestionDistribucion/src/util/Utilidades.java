package util;
/**
 * Diversos métodos que son útiles en otras clases
 * @author Jose Carlos
 *
 */
public class Utilidades {

	/**
	 * Recibe una cadena en formato euro y retorna el valor double
	 * @param cad cadena en formato euro
	 * @return double
	 */
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	
	/**
	 * Recibe una cadena con signo € y la retorna sin el signo €
	 * @param cad con signo €
	 * @return cadena sin signo €
	 */
	public String focoEuro(String cad) {
		return cad.split(" ")[0];
	}

	/**
	 * Recibe una cadena sin signo € y le pone el signo €
	 * @param cad sin signo €
	 * @return con signo €
	 */
	public String noFocoEuro(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+" €";
	}
	/**
	 * Recibe una cadena sin signo % y le pone el signo %
	 * @param cad sin signo %
	 * @return con signo %
	 */
	public Double porcentajeADoble(String cad) {
		return Double.valueOf(cad.split("%")[0])/100;
	}
	public String focoPorcentaje(String cad) {
		return cad.split("%")[0];
	}

}
