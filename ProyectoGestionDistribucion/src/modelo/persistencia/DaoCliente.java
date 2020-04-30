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
				lista=em.createQuery("SELECT cli FROM Cliente cli where cli.nombre LIKE :filtroNombre").setParameter("filtroNombre","%"+filtroNombre+"%").getResultList();
			ab.cerrarConexion();
			return lista;
		}
	}
	
	public int modificar(Cliente cli) {
		Cliente antiguo=existe(cli.getNumero());
		em.getTransaction().begin();
		if (em==null) return -1;
		antiguo.setNumero(cli.getNumero());
		antiguo.setTipo(cli.getTipo());
		antiguo.setNifCif(cli.getNifCif());
		antiguo.setNombre(cli.getNombre());
		antiguo.setApellidos(cli.getApellidos());
		antiguo.setEmail(cli.getEmail());
		antiguo.setTelefonoFijo(cli.getTelefonoFijo());
		antiguo.setTelefonoMovil(cli.getTelefonoMovil());
		antiguo.setDireccion(cli.getDireccion());
		antiguo.setCodPost(cli.getCodPost());
		antiguo.setNumCuentaContable(cli.getNumCuentaContable());
		antiguo.setProvincia(cli.getProvincia());
		antiguo.setPoblacion(cli.getPoblacion());
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
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
}
