package modelo.negocio;

import java.util.List;

import entidades.AlbaranProveedor;
import entidades.FilaAlbaranProveedor;
import entidades.Proveedor;
import modelo.persistencia.DaoAlbaranProve;
/**
 * gestor de Albaranes de proveedor
 * @author Jose Carlos
 *
 */
public class GestorAlbaranProve {
	DaoAlbaranProve dap;
	/**
	 * El constructor crea un objeto DaoAlbaranProve
	 */
	public GestorAlbaranProve() {
		dap=new DaoAlbaranProve();
	}
	/**
	 * Llama al método nuevoAlbaran de DaoAlbaranProve
	 * @param alb Objeto entidad AlbaranProveedor
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int nuevoAlbaran(AlbaranProveedor alb) {
		return dap.nuevoAlbaran(alb);
	}
	/**
	 * Llama al método modificaAlbaranGenerado de DaoAlbaranProve
	 * @param alb Objeto entidad AlbaranProveedor
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int modificaAlbaranGenerado(AlbaranProveedor alb) {
		return dap.modificaAlbaranGenerado(alb);
	}
	/**
	 * Llama al método facturaAlbaran de DaoAlbaranProve
	 * @param alb Objeto entidad AlbaranProveedor
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int facturaAlbaran(AlbaranProveedor alb) {
		return dap.facturaAlbaran(alb);
	}
	/**
	 * Llama al método modificaAlbaran de DaoAlbaranProve
	 * @param alb Objeto entidad AlbaranProveedor
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int modificaAlbaran(AlbaranProveedor alb) {
		return dap.modificaAlbaran(alb);
	}
	/**
	 * Llama al método genera filas de DaoAlbaranProve
	 * @param alb Objeto entidad AlbaranProveedor
	 * @return lista de albaranes de un proveedor List de FilaAlbaranProveedor
	 */
	public List<FilaAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		return dap.generaFilas(alb);
	}
	/**
	 * Llama al método listarAlbaranes de DaoAlbaranProve
	 * @return lista de albaranes List de FilaAlbaranProveedor
	 */
	public List<AlbaranProveedor> listarAlbaranes(){
		return dap.listarAlbaranes();
	}
	/**
	 * Llama al método actualizaArticulosAlbaranProve de GestorArticulo
	 * @param albModif Objeto entidad AlbaranProveedor
	 * @param masmenos entero 1 suma -1 resta
	 */
	public void actualizaAlmacen(AlbaranProveedor albModif,int masmenos) {
		GestorArticulo ga=new GestorArticulo();
		ga.actualizaArticulosAlbaranProve(albModif,masmenos);
	}
	/**
	 * Llama al método listaAlbaranesAlmacen de DaoAlbaranProve
	 * @param pro Objeto entidad por
	 * @return List de AlbaranProveedor
	 */
	public List<AlbaranProveedor> listaAlbaranesAlmacen(Proveedor pro){
		return dap.listaAlbaranesAlmacen(pro);
	}
	/**
	 * Llama al método existe de DaoAlbaranProve
	 * @param num Número de albarán
	 * @return Objeto entidad AlbaranProveedor
	 */
	public AlbaranProveedor existe(int num) {
		return dap.existe(num);
	}
}
