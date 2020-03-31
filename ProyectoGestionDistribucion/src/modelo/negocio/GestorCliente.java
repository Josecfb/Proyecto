package modelo.negocio;
import java.util.List;

import model.Cliente;
import modelo.persistencia.DaoCliente;


public class GestorCliente {
	private DaoCliente dc;
	
	public GestorCliente() {
		dc=new DaoCliente();
	}
	public List<Cliente> listar(String filtroNombre){
		return dc.Listado(filtroNombre);
	}
	
	public Cliente existe(int num) {
		return dc.existe(num);
	}
	
	public boolean[] modificarProveedor(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
			int modificado=dc.modificar(cli);
			ok[3]=modificado==0;
		}
		return ok;
	}
	
	public boolean[] nuevoCliente(Cliente cli) {
		boolean[] ok = valida(cli);
		if (ok[0] && ok[1] && ok[2]) {
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
