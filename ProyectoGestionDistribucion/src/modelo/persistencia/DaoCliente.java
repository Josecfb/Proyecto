package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import model.Cliente;

public class DaoCliente {
	private EntityManager em;
	
	
	public Cliente existe(int num) {
		Cliente cli;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else 
			cli=em.find(Cliente.class, num);
		return cli;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> Listado(String filtroNombre){
		List<Cliente> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			if (filtroNombre=="")
				lista=em.createQuery("SELECT cli FROM Cliente cli").getResultList();
			else
				lista=em.createQuery("SELECT cli FROM Cliente cli where cli.nombreComercial LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			ab.cerrarConexion();
			return lista;
		}
	}
}
