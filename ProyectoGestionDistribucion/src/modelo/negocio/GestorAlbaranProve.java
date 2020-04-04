package modelo.negocio;

import java.util.List;

import model.AlbaranProveedor;
import model.FilasAlbaranProveedor;
import modelo.persistencia.DaoAlbaranProve;

public class GestorAlbaranProve {
	DaoAlbaranProve dap;
	
	public GestorAlbaranProve() {
		dap=new DaoAlbaranProve();
	}
	
	public int nuevoAlbaran(AlbaranProveedor alb) {
		
		return dap.nuevoAlbaran(alb);
	}
	
	public List<FilasAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		return dap.generaFilas(alb);
	}
}
