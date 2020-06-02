package modelo.negocio;

import java.util.List;

import entidades.FacturaProveedor;
import entidades.FilaFacturaProveedor;
import modelo.persistencia.DaoFacturaProve;
/**
 * Gestor de Factura de proveedor
 * @author Jose Carlos
 *
 */
public class GestorFacturaProve {
	DaoFacturaProve dfp;
	/**
	 * El constructor crea un objeto DaoFacturaProve
	 */
	public GestorFacturaProve() {
		dfp=new DaoFacturaProve();
	}
	/**
	 * Llama al método nuevaFactura de DaoFacturaProve
	 * @param fact Objeto FacturaProveedor
	 * @return -1 error 0 correcto
	 */
	public int nuevaFactura(FacturaProveedor fact) {
		return dfp.nuevaFactura(fact);
	}
	/**
	 * Genera las filas de una factura a partir de albaranes de Proveedor
	 * Llama al método generaFilas de DaoFacturaProve
	 * @param fac Objeto FacturaProveedor
	 * @return List de FilaFacturaProveedor
	 */
	public List<FilaFacturaProveedor> generaFilas(FacturaProveedor fac){
		return dfp.generaFilas(fac);
	}
	/**
	 * Llama al método modificaFacturaGenerada de DaoFacturaProve
	 * @param fact Objeto FacturaProveedor
	 * @return -1 error 0 correcto
	 */
	public int modificaFacturaGenerada(FacturaProveedor fact) {
		return dfp.modificaFacturaGenerada(fact);
	}
	/**
	 * Llama al método modificaFactura de DaoFacturaProve
	 * @param fact Objeto FacturaProveedor
	 * @return -1 error 0 correcto
	 */
	public int modificaFactura(FacturaProveedor fact) {
		return dfp.modificaFactura(fact);
	}
	/**
	 * Llama al método listarFacturas de DaoFacturaProve
	 * @return List de FActuraProveedor
	 */
	public List<FacturaProveedor> listarFacturas(){
		return dfp.listarFacturas();
	}
	

}
