package modelo.negocio;
import java.util.List;

import model.Cliente;
import modelo.persistencia.DaoCliente;


public class GestorCliente {
	public List<Cliente> listar(String filtroNombre){
		DaoCliente dp=new DaoCliente();
		return dp.Listado(filtroNombre);
	}
	
	public Cliente existe(int num) {
		DaoCliente da=new DaoCliente();
		return da.existe(num);
	}
	
	public boolean[] modificarProveedor(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
			DaoCliente dc=new DaoCliente();
			int modificado=dc.modificar(cli);
			ok[3]=modificado==0;
		}
		return ok;
	}
	
	public boolean[] nuevoCliente(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
			DaoCliente dc=new DaoCliente();
			int creado=dc.nuevo(cli);
			ok[3]=creado==0;
		}
		return ok;
	}
	
	private boolean[] valida(Cliente cli) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=cli.getNombre().length()>0 || cli.getNombreComercial().length()>0;
		ok[1]=cli.getNifCif().length()>0;
		ok[2]=cli.getEmail().length()>0;
		return ok;
	}
}
