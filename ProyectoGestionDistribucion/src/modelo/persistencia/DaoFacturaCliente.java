package modelo.persistencia;

import javax.persistence.EntityManager;
import entidades.FacturasCliente;

public class DaoFacturaCliente {
	private EntityManager em;
	
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	
	public int nuevaFactura(FacturasCliente fact) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(fact);
		em.getTransaction().commit();
		em.close();
		return 0;
	}


}
