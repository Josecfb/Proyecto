package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import entidades.Cliente;

/**
 * Gestiona la persistencia de Cliente
 * @author Jose Carlos
 *
 */
public class DaoCliente {
	private EntityManager em;
	
	/**
	 * Retorna el cliente si existe en la base de datos
	 * @param num número de cliente
	 * @return Objeto Cliente
	 */
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
	/**
	 * Obtiene la lista de lis clientes
	 * @param filtroNombre cadena que actua como filtro para el nombre
	 * @return List de Cliente
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> listado(String filtroNombre){
		List<Cliente> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else{
			if (filtroNombre=="")
				lista=em.createQuery("SELECT cli FROM Cliente cli").getResultList();
			else
				lista=em.createQuery("SELECT cli FROM Cliente cli where cli.nombre LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			ab.cerrarConexion();
			return lista;
		}
	}
	/**
	 * Modifica un cliente existente
	 * @param cli Objeto Clienete
	 * @return -1 error 0 correcto
	 */
	public int modificar(Cliente cli) {
		Cliente antiguo=existe(cli.getNumero());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo=cli;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Persiste un nuevo cliente
	 * @param cli Objeto Clienete
	 * @return -1 error 0 correcto
	 */
	public int nuevo(Cliente cli) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		em.persist(cli);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Elimina un cliente de la base de datos
	 * @param cli Objeto Cliente
	 */
	public void borrarCliente(Cliente cli) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		Cliente borrar=em.find(Cliente.class, cli.getNumero());
		em.getTransaction().begin();
		em.remove(borrar);
		em.getTransaction().commit();
		em.close();
	}
}
