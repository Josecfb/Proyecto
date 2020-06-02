package modelo.negocio;

import java.util.List;

import entidades.Familia;
import modelo.persistencia.DaoFamilia;
/**
 * Gestor de familias de artículos (no terminado)
 * @author Jose Carlos
 *
 */
public class GestorFamilia {
	/**
	 * Llama al método listado de DaoFamilia
	 * @param filtroNombre cadena que actua como filtro para el nombre
	 * @return List de Familia
	 */
	public List<Familia> listar(String filtroNombre){
		DaoFamilia df=new DaoFamilia();
		return df.listado(filtroNombre);
	}
}
