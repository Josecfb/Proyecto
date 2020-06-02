package modelo.negocio;
import java.util.List;

import entidades.Cliente;
import modelo.persistencia.DaoCliente;

/**
 * Gestor de cliente
 * @author Jose Carlos
 *
 */
public class GestorCliente {
	private DaoCliente dc;
	/**
	 * El constructor crea un objeto DaoCliente
	 */
	public GestorCliente() {
		dc=new DaoCliente();
	}
	/**
	 * Llama al método listado de DaoCliente
	 * @param filtroNombre Cadena que actua como filtro
	 * @return List de Cliente
	 */
	public List<Cliente> listar(String filtroNombre){
		return dc.listado(filtroNombre);
	}
	/**
	 * Llama ak método existe de DaoCliente
	 * @param num entero número de artículo
	 * @return Objeto Cliente
	 */
	public Cliente existe(int num) {
		return dc.existe(num);
	}
	/**
	 * Llama al método modificar de DaoCliente
	 * @param cli Objeto Cliente
	 * @return Array de boolean con banderas de errores 0 sin nombre 1 sin NIF 2 sin EMail 3 todo correcto
	 */
	public boolean[] modificarCliente(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
			int modificado=dc.modificar(cli);
			ok[3]=modificado==0;
		}
		return ok;
	}
	/**
	 * Llama al método nuevo de DaoCliente
	 * @param cli Objeto Cliente
	 * @return Array de boolean con banderas de errores 0 sin nombre 1 sin NIF 2 sin EMail 3 todo correcto
	 */
	public boolean[] nuevoCliente(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
			int creado=dc.nuevo(cli);
			ok[3]=creado==0;
		}
		return ok;
	}
	/**
	 * Valída posibles errores en campos
	 * @param cli Objeto Cliente
	 * @return Array de boolean con banderas de errores 0 sin nombre 1 sin NIF 2 sin EMail 3 todo correcto
	 */
	private boolean[] valida(Cliente cli) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=cli.getNombre().length()>0;
		ok[1]=cli.getNifCif().length()>0;
		ok[2]=cli.getEmail().length()>0;
		return ok;
	}
	/**
	 * Llama al método borrarCliente de DaoCliente
	 * @param cli Objeto cliente
	 * @return Cadena con respuesta
	 */
	public String borrarCliente(Cliente cli) {
		if (cli!=null) {
			if (cli.getAlbaranClientes().size()>0) 
				return "No se puede borrar, tiene albaranes";
			if (cli.getPedidosClientes().size()>0) 
				return "No se puede borrar, tiene pedidos";
			if (cli.getFacturasCliente().size()>0) 
				return "No se puede borrar, tiene facturas";
			dc.borrarCliente(cli);
			return "Cliente borrado";
		}
		return "No hay ningún cliente que borrar";
	}
}
