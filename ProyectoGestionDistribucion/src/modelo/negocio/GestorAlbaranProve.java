package modelo.negocio;

import java.util.List;

import model.AlbaranProveedor;
import model.FilaAlbaranProveedor;
import model.Proveedor;
import modelo.persistencia.DaoAlbaranProve;

public class GestorAlbaranProve {
	DaoAlbaranProve dap;
	
	public GestorAlbaranProve() {
		dap=new DaoAlbaranProve();
	}
	
	public int nuevoAlbaran(AlbaranProveedor alb) {
		return dap.nuevoAlbaran(alb);
	}
	
	public int modificaAlbaranGenerado(AlbaranProveedor alb) {
		return dap.modificaAlbaranGenerado(alb);
	}
	
	public int facturaAlbaran(AlbaranProveedor alb) {
		return dap.facturaAlbaran(alb);
	}
	
	public int modificaAlbaran(AlbaranProveedor alb) {
		return dap.modificaAlbaran(alb);
	}
	
	public List<FilaAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		return dap.generaFilas(alb);
	}
	
	public List<AlbaranProveedor> listarAlbaranes(){
		return dap.listarAlbaranes();
	}
	public void actualizaAlmacen(AlbaranProveedor albModif,int masmenos) {
		GestorArticulo ga=new GestorArticulo();
		ga.actualizaArticulosAlbaranProve(albModif,masmenos);
	}
	public List<AlbaranProveedor> listaAlbaranesAlmacen(Proveedor pro){
		return dap.listaAlbaranesAlmacen(pro);
	}
	
	public AlbaranProveedor existe(int num) {
		return dap.existe(num);
	}
}
