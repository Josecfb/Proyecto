package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;


import model.Cliente;

public class DaoCliente {
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	public List<Cliente> Listado(){
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			List<Cliente> lista=em.createQuery("SELECT cli FROM Cliente cli").getResultList();
			//ab.cerrarConexion();
			ab.cerrarConexion();
			return lista;
		}
	}
}
