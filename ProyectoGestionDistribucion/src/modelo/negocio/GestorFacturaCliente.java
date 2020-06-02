package modelo.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entidades.AlbaranCliente;
import entidades.FacturaCliente;
import entidades.FilasAlbaranCliente;
import entidades.FilaFacturaCliente;
import modelo.persistencia.DaoFacturaCliente;
import vista.pdf.PdfFacturaCliente;
/**
 * Gestor de FacturaCliente
 * @author Jose Carlos
 *
 */
public class GestorFacturaCliente {
	DaoFacturaCliente dfc;
	/**
	 * El constructor crea un objeto DaoFacturaCliente
	 */
	public GestorFacturaCliente() {
		dfc=new DaoFacturaCliente();
	}
	/**
	 * Genera una factura a partir de un albaran de cliente minorista
	 * Llama al método nuevaFactura de DaoFacturaCliente
	 * @param alb Objeto AlbaranCliente
	 */
	public void generaFacturaAlbaranMinorista(AlbaranCliente alb) {
		FacturaCliente fac =new FacturaCliente();
		fac.setCliente(alb.getClienteBean());
		fac.setFecha(new Date());
		fac.setPagada(false);
		fac.setAlbaranClientes(new ArrayList<AlbaranCliente>());
		fac.getAlbaranClientes().add(alb);
		List<FilaFacturaCliente> filasFacturasClientes=new ArrayList<FilaFacturaCliente>();
		List<FilasAlbaranCliente> filasA=alb.getFilasAlbaranClientes();
		for (FilasAlbaranCliente filaAlb:filasA) {
			FilaFacturaCliente filaFac=new FilaFacturaCliente();
			filaFac.setArticuloBean(filaAlb.getArticuloBean());
			filaFac.setCantidad(filaAlb.getCantidad());
			filaFac.setPrecio(filaAlb.getPrecio());
			filaFac.setFacturasCliente(fac);
			filasFacturasClientes.add(filaFac);
		}
		fac.setFilasFacturasClientes(filasFacturasClientes);
		dfc.nuevaFactura(fac);
		new PdfFacturaCliente(fac);
	}
	/**
	 * Llama al método modificaFactura de DaoFacturaCliente
	 * @param fact Objeto FacturaCliente
	 * @return entero -1 error 0 correcto
	 */
	public int modificaFactura(FacturaCliente fact) {
		return dfc.modificaFactura(fact);
	}
	/**
	 * Obtiene la lista de facturas llama al método listarFacturas de DaoFacturaCliente
	 * @return List de FacturaCliente
	 */
	public List<FacturaCliente> listarFacturas(){
		return dfc.listarFacturas();
	}
	/**
	 * Llama al método nuevaFactura de DaoFacturaCliente
	 * @param fact Objeto Factura
	 * @return entero -1 error 0 correcto
	 */
	public int nuevaFactura(FacturaCliente fact) {
		return dfc.nuevaFactura(fact);
	}
	/**
	 * Llama al método generaFilas de DaoFacturaCliente
	 * @param fac Objeto FacturaCliente
	 * @return List de FilaFacturaCliente
	 */
	public List<FilaFacturaCliente> generaFilas(FacturaCliente fac){
		return dfc.generaFilas(fac);
	}
	/**
	 * Llama al método modificaFacturaGenerada de DaoFacturaCliente
	 * @param fact Objeto FacturaCliente
	 * @return entero -1 error 0 correcto
	 */
	public int modificaFacturaGenerada(FacturaCliente fact) {
		return dfc.modificaFacturaGenerada(fact);
	}

}
