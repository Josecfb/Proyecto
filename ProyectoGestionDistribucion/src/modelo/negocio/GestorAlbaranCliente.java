package modelo.negocio;

import java.util.List;
import model.AlbaranCliente;
import model.Cliente;
import model.FilasAlbaranCliente;
import modelo.persistencia.DaoAlbaranCliente;

public class GestorAlbaranCliente {
	DaoAlbaranCliente dac;
	
	public GestorAlbaranCliente() {
		dac=new DaoAlbaranCliente();
	}
	
	public int nuevoAlbaran(AlbaranCliente alb) {
		return dac.nuevoAlbaran(alb);
	}
	
	public int modificaAlbaranGenerado(AlbaranCliente alb) {
		return dac.modificaAlbaranGenerado(alb);
	}
	
	public int facturaAlbaran(AlbaranCliente alb) {
		return dac.facturaAlbaran(alb);
	}
	
	public int modificaAlbaran(AlbaranCliente alb) {
		return dac.modificaAlbaran(alb);
	}
	
	public List<FilasAlbaranCliente> generaFilas(AlbaranCliente alb){
		return dac.generaFilas(alb);
	}
	
	public List<AlbaranCliente> listarAlbaranes(){
		return dac.listarAlbaranes();
	}
	public void actualizaAlmacen(AlbaranCliente albModif,int masmenos) {
		GestorArticulo ga=new GestorArticulo();
		ga.actualizaArticulosAlbaranCliente(albModif,masmenos);
	}
	public List<AlbaranCliente> listaAlbaranesAlmacen(Cliente cli){
		return dac.listaAlbaranesAlmacen(cli);
	}
	
	public AlbaranCliente existe(int num) {
		return dac.existe(num);
	}
}
