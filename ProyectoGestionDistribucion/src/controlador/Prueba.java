package controlador;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Proveedor;

public class Prueba {

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("ProyectoGestionDistribucion").createEntityManager();
		Proveedor pro;
		pro=em.find(Proveedor.class, 2);
		System.out.println(pro.getNombre());
		em.getTransaction().begin();
		pro.setNombre("mierda");
		em.merge(pro);
		em.getTransaction().commit();
		em.close();
	}

}
