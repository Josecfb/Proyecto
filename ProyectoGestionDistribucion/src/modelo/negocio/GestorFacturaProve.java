package modelo.negocio;

import java.util.List;

import model.AlbaranProveedor;
import model.FacturaProveedor;
import model.FilaAlbaranProveedor;
import modelo.persistencia.DaoFacturaProve;

public class GestorFacturaProve {
	DaoFacturaProve dfp;
	
	public GestorFacturaProve() {
		dfp=new DaoFacturaProve();
	}
	
	public int nuevaFactura(FacturaProveedor fact) {
		return dfp.nuevaFactura(fact);
	}

	public List<FilaAlbaranProveedor> generaFilas(FacturaProveedor fac){
		return dfp.generaFilas(fac);
	}
	
	

}
