package modelo.negocio;

import java.util.List;

import entidades.AlbaranCliente;
import entidades.Cliente;
import entidades.FilasAlbaranCliente;
import modelo.persistencia.DaoAlbaranCliente;
/**
 * Gestor de Albaranes de cliente
 * @author Jose Carlos
 *
 */
public class GestorAlbaranCliente {
	DaoAlbaranCliente dac;
	/**
	 * El constructor crea un objeto DaoAlbaranCliente
	 */
	public GestorAlbaranCliente() {
		dac=new DaoAlbaranCliente();
	}
	/**
	 * Llama al método nuevoAlbaran de DaoAlbaranCliente
	 * @param alb objeto entidad AlbaranCliente
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int nuevoAlbaran(AlbaranCliente alb) {
		return dac.nuevoAlbaran(alb);
	}
	/**
	 * Llama al método modificaAlbaranGenerado de DaoAlbaranCliente
	 * @param alb objeto entidad AlbaranCliente
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int modificaAlbaranGenerado(AlbaranCliente alb) {
		return dac.modificaAlbaranGenerado(alb);
	}
	/**
	 * Llama al método facturaAlbaran de DaoAlbaranCliente
	 * @param alb objeto entidad AlbaranCliente
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int facturaAlbaran(AlbaranCliente alb) {
		return dac.facturaAlbaran(alb);
	}
	/**
	 * Llama al método modificaAlbaran de DaoAlbaranCliente
	 * @param alb objeto entidad AlbaranCliente
	 * @return entero 0 en caso de éxito -1 error
	 */
	public int modificaAlbaran(AlbaranCliente alb) {
		return dac.modificaAlbaran(alb);
	}
	/**
	 * Llama al método generaFilas de DaoAlbaranCliente 
	 * @param alb objeto entidad AlbaranCliente
	 * @return Lista de filas del albarán List<FilasAlbaranCliente>
	 */
	public List<FilasAlbaranCliente> generaFilas(AlbaranCliente alb){
		return dac.generaFilas(alb);
	}
	/**
	 * Llama al método listarAlbaranes de DaoAlbaranCliente
	 * @return Lista de albaranes List de AlbaranCliente
	 */
	public List<AlbaranCliente> listarAlbaranes(){
		return dac.listarAlbaranes();
	}
	/**
	 * Llama al método actualizaArticulosAlbaranCliente de GestorArticulo
	 * @param albModif objeto entidad AlbaranCliente
	 * @param masmenos entero 1 suma -1 resta
	 */
	public void actualizaAlmacen(AlbaranCliente albModif,int masmenos) {
		GestorArticulo ga=new GestorArticulo();
		ga.actualizaArticulosAlbaranCliente(albModif,masmenos);
	}
	/**
	 * Llama al método listaAlbaranesAlmacen dde DaoAlbaranCliente
	 * @param cli Objeto entidad Cliente
	 * @return Lista de albaranes de un cliente List de AlbaranCliente
	 */
	public List<AlbaranCliente> listaAlbaranesAlmacen(Cliente cli){
		return dac.listaAlbaranesAlmacen(cli);
	}
	/**
	 * Llama al método existe de DaoAlbaranCliente
	 * @param num entero número de albarán
	 * @return Objeto AlbaranCliente
	 */
	public AlbaranCliente existe(int num) {
		return dac.existe(num);
	}
}
