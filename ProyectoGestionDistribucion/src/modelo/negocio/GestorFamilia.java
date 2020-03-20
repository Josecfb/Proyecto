package modelo.negocio;

import java.util.List;

import model.Familia;
import modelo.persistencia.DaoFamilia;

public class GestorFamilia {
	public List<Familia> listar(String filtroNombre){
		DaoFamilia df=new DaoFamilia();
		return df.listado(filtroNombre);
	}
}