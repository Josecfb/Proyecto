package modelo.negocio;

import java.util.List;

import entidades.FacturaProveedor;
import entidades.FilaFacturaProveedor;
import modelo.persistencia.DaoFacturaProve;

public class GestorFacturaProve {
	DaoFacturaProve dfp;
	
	public GestorFacturaProve() {
		dfp=new DaoFacturaProve();
	}
	
	public int nuevaFactura(FacturaProveedor fact) {
		return dfp.nuevaFactura(fact);
	}

	public List<FilaFacturaProveedor> generaFilas(FacturaProveedor fac){
		return dfp.generaFilas(fac);
	}
	
	public int modificaFacturaGenerada(FacturaProveedor fact) {
		return dfp.modificaFacturaGenerada(fact);
	}
	
	public int modificaFactura(FacturaProveedor fact) {
		return dfp.modificaFactura(fact);
	}
	public List<FacturaProveedor> listarFacturas(){
		return dfp.listarFacturas();
	}
	

}
