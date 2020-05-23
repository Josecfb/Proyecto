package modelo.negocio;
import java.util.List;

import entidades.Proveedor;
import modelo.persistencia.DaoProveedor;

public class GestorProveedor {
	
	public List<Proveedor> listar(String filtroNombre){
		DaoProveedor dp=new DaoProveedor();
		return dp.Listado(filtroNombre);
	}
	public Proveedor existe(int num) {
		DaoProveedor dp=new DaoProveedor();
		return dp.existe(num);
	}
	
	public boolean[] modificarProveedor(Proveedor pro) {
		boolean[] ok = valida(pro);
		if (ok[0] && ok[1] && ok[2]) {
			DaoProveedor dp=new DaoProveedor();
			int modificado=dp.modificar(pro);
			ok[3]=modificado==0;
		}
		return ok;
	}
	
	public boolean[] nuevoProveedor(Proveedor pro) {
		boolean[] ok = valida(pro);
		if (ok[0] && ok[1] && ok[2]) {
			DaoProveedor dp=new DaoProveedor();
			int creado=dp.nuevo(pro);
			ok[3]=creado==0;
		}
		return ok;
	}
	
	private boolean[] valida(Proveedor pro) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=pro.getNombre().length()>0;
		ok[1]=pro.getNif().length()>0;
		ok[2]=pro.getEmail().length()>0;
		return ok;
	}
	
	public String borrarProveedor(Proveedor pro) {
		DaoProveedor dp=new DaoProveedor();
		if (pro!=null) {
			if (pro.getAlbaranesProveedors().size()>0) 
				return "No se puede borrar, tiene albaranes";
			if (pro.getPedidosProveedors().size()>0) 
				return "No se puede borrar, tiene pedidos";
			if (pro.getFacturasProveedors().size()>0) 
				return "No se puede borrar, tiene facturas";
			dp.borrarProveedor(pro);
			return "Proveedor borrado";
		}
		return "No hay ningún cliente que borrar";
	}
	
}
