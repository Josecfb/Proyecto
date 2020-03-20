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
}
