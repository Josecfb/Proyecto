package modelo.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.AlbaranCliente;
import entidades.FacturaProveedor;
import entidades.FacturasCliente;
import entidades.FilaFacturaProveedor;
import entidades.FilasAlbaranCliente;
import entidades.FilasFacturasCliente;
import modelo.persistencia.DaoFacturaCliente;
import vista.pdf.PdfFacturaCliente;

public class GestorFacturaCliente {
	DaoFacturaCliente dfc;
	public GestorFacturaCliente() {
		dfc=new DaoFacturaCliente();
	}
	
	public void generaFacturaAlbaranMinorista(AlbaranCliente alb) {
		FacturasCliente fac =new FacturasCliente();
		fac.setCliente(alb.getClienteBean());
		fac.setFecha(new Date());
		fac.setPagada(false);
		fac.setAlbaranClientes(new ArrayList<AlbaranCliente>());
		fac.getAlbaranClientes().add(alb);
		//dfc.nuevaFactura(fac);
		List<FilasFacturasCliente> filasFacturasClientes=new ArrayList<FilasFacturasCliente>();
		List<FilasAlbaranCliente> filasA=alb.getFilasAlbaranClientes();
		for (FilasAlbaranCliente filaAlb:filasA) {
			FilasFacturasCliente filaFac=new FilasFacturasCliente();
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
	
	public int modificaFactura(FacturasCliente fact) {
		return dfc.modificaFactura(fact);
	}
	
	public List<FacturasCliente> listarFacturas(){
		return dfc.listarFacturas();
	}
	
	public int nuevaFactura(FacturasCliente fact) {
		return dfc.nuevaFactura(fact);
	}
	
	public List<FilasFacturasCliente> generaFilas(FacturasCliente fac){
		return dfc.generaFilas(fac);
	}
	
	public int modificaFacturaGenerada(FacturasCliente fact) {
		return dfc.modificaFacturaGenerada(fact);
	}

}
