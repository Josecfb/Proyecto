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

public class GestorFacturaCliente {
	DaoFacturaCliente dfc;
	public GestorFacturaCliente() {
		dfc=new DaoFacturaCliente();
	}
	
	public void generaFacturaAlbaranMinorista(AlbaranCliente alb) {
		FacturaCliente fac =new FacturaCliente();
		fac.setCliente(alb.getClienteBean());
		fac.setFecha(new Date());
		fac.setPagada(false);
		fac.setAlbaranClientes(new ArrayList<AlbaranCliente>());
		fac.getAlbaranClientes().add(alb);
		//dfc.nuevaFactura(fac);
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
	
	public int modificaFactura(FacturaCliente fact) {
		return dfc.modificaFactura(fact);
	}
	
	public List<FacturaCliente> listarFacturas(){
		return dfc.listarFacturas();
	}
	
	public int nuevaFactura(FacturaCliente fact) {
		return dfc.nuevaFactura(fact);
	}
	
	public List<FilaFacturaCliente> generaFilas(FacturaCliente fac){
		return dfc.generaFilas(fac);
	}
	
	public int modificaFacturaGenerada(FacturaCliente fact) {
		return dfc.modificaFacturaGenerada(fact);
	}

}
