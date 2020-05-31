package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import entidades.Articulo;
import entidades.FacturaCliente;
import entidades.FilaFacturaCliente;

public class DaoFacturaCliente {
	private EntityManager em;
	
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	
	public int nuevaFactura(FacturaCliente fact) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(fact);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
	public int modificaFactura(FacturaCliente fact) {
		abrir();
		if (em==null) return -1;
		FacturaCliente antigua=em.find(FacturaCliente.class, fact.getNum());
		em.getTransaction().begin();
		for (FilaFacturaCliente fi:antigua.getFilasFacturasClientes()) 
			em.remove(fi);
		em.getTransaction().commit();
		em.getTransaction().begin();
		antigua=fact;
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
		System.gc();
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<FacturaCliente> listarFacturas(){
		abrir();
		if (em==null) return null;
		List<FacturaCliente> lista=em.createQuery("select fact from FacturaCliente fact order by fact.fecha desc").getResultList();
		em.close();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<FilaFacturaCliente> generaFilas(FacturaCliente fact){
		List<FilaFacturaCliente> filasFact=new ArrayList<FilaFacturaCliente>();
		abrir();
		if (em==null) return null;
		List<Object[]> filas;
		filas=em.createQuery("select fil.articuloBean,sum(fil.cantidad),fil.precio from FilasAlbaranCliente fil where fil.albaranCliente.facturasCliente=:fact group by fil.articuloBean").setParameter("fact", fact).getResultList();
		for (Object[] fila:filas) {
			Articulo art=(Articulo) fila[0];
			FilaFacturaCliente filaFact=new FilaFacturaCliente();
			filaFact.setFacturasCliente(fact);
			filaFact.setArticuloBean(art);
			int cantidad=((Long) fila[1]).intValue();
			filaFact.setCantidad(cantidad);
			filaFact.setPrecio((double) fila[2]);
			filasFact.add(filaFact);
		}
		return filasFact;
	}
	
	public int modificaFacturaGenerada(FacturaCliente fact) {
		abrir();
		if (em==null) return -1;
		FacturaCliente antigua=em.find(FacturaCliente.class, fact.getNum());
		antigua.getFilasFacturasClientes().clear();
		for (FilaFacturaCliente fila:fact.getFilasFacturasClientes())
			antigua.getFilasFacturasClientes().add(fila);
		em.getTransaction().begin();
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

}
