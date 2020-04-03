package modelo.negocio;

import model.AlbaranProveedor;
import modelo.persistencia.DaoAlbaranProve;

public class GestorAlbaranProve {
	DaoAlbaranProve dap;
	
	public GestorAlbaranProve() {
		dap=new DaoAlbaranProve();
	}
	
	public int nuevoAlbaran(AlbaranProveedor alb) {
		
		return dap.nuevoAlbaran(alb);
	}
}
