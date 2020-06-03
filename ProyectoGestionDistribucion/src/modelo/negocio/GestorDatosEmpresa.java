package modelo.negocio;

import entidades.Datosempresa;
import modelo.persistencia.DaoDatosEmpresa;
/**
 * Se encarga del objeto Empresa
 * @author Jose Carlos
 *
 */
public class GestorDatosEmpresa {
	
	/**
	 * Llama al método mofificaEmpresa de DaoDatosEmpresa
	 * @param empresa Datosempresa
	 */
	public void guardaEmpresa(Datosempresa empresa) {
		DaoDatosEmpresa ddd=new DaoDatosEmpresa();
		ddd.modificaEmpresa(empresa);
	}
}
