package modelo.negocio;

import entidades.Datosempresa;
import modelo.persistencia.DaoDatosEmpresa;

public class GestorDatosEmpresa {
	

	public void guardaEmpresa(Datosempresa empresa) {
		DaoDatosEmpresa ddd=new DaoDatosEmpresa();
		ddd.modificaEmpresa(empresa);
	}
}
