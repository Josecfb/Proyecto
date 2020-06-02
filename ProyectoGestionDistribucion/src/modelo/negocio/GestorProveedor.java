package modelo.negocio;
import java.util.List;

import entidades.Proveedor;
import modelo.persistencia.DaoProveedor;
/**
 * Gestor de Proveedor
 * @author Jose Carlos
 *
 */
public class GestorProveedor {
	private DaoProveedor dp;
	/**
	 * El constructor crea un objeto DaoProveedor
	 */
	public GestorProveedor() {
		dp=new DaoProveedor();
	}
	/**
	 * Llama al método listar de DaoProveedor
	 * @param filtroNombre Cadena que actua como filtro para el nombre
	 * @return List de Proveedor
	 */
	public List<Proveedor> listar(String filtroNombre){
		return dp.listar(filtroNombre);
	}
	/**
	 * Llama al método existe de DaoProveedor
	 * @param num número de proveedor
	 * @return Objeto Proveedor
	 */
	public Proveedor existe(int num) {
		return dp.existe(num);
	}
	/**
	 * Llama al método modificar de DaoProveedor
	 * @param pro Objeto Proveedor
	 * @return 0 sin nombre, 1 sin nif, 2 sin EMail 3 correcto
	 */
	public boolean[] modificarProveedor(Proveedor pro) {
		boolean[] ok = valida(pro);
		if (ok[0] && ok[1] && ok[2]) {
			int modificado=dp.modificar(pro);
			ok[3]=modificado==0;
		}
		return ok;
	}
	/**
	 * Llama al método nuevo de DaoProveedor
	 * @param pro Objeto Provedor
	 * @return 0 sin nombre, 1 sin nif, 2 sin EMail 3 correcto
	 */
	public boolean[] nuevoProveedor(Proveedor pro) {
		boolean[] ok = valida(pro);
		if (ok[0] && ok[1] && ok[2]) {
			int creado=dp.nuevo(pro);
			ok[3]=creado==0;
		}
		return ok;
	}
	/**
	 * valida posibles errores en los campos
	 * @param pro Objeto Proveedor
	 * @return 0 sin nombre, 1 sin nif, 2 sin EMail 3 correcto
	 */
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
	/**
	 * Llama al método borrarProveedor de DaoProveedor
	 * @param pro Objeto Proveedor
	 * @return cadena con resultado de posible error
	 */
	public String borrarProveedor(Proveedor pro) {
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
		return "No hay ningún proveedor que borrar";
	}
	
}
