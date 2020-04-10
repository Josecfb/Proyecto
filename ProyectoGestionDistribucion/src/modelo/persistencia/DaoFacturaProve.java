package modelo.persistencia;

import javax.persistence.EntityManager;

import model.FacturaProveedor;

public class DaoFacturaProve {
	private EntityManager em;

	public int nuevaFactura(FacturaProveedor fact) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(fact);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
}
