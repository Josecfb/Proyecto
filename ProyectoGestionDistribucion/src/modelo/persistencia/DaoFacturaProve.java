package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Articulo;
import model.FacturaProveedor;
import model.FilaFacturaProveedor;

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
	
	@SuppressWarnings("unchecked")
	public List<FilaFacturaProveedor> generaFilas(FacturaProveedor fact){
		List<FilaFacturaProveedor> filasFact=new ArrayList<FilaFacturaProveedor>();
		abrir();
		if (em==null) return null;
		List<Object[]> filas;
		filas=em.createQuery("select fil.articuloBean,sum(fil.cantidad),fil.precio from FilaAlbaranProveedor fil where fil.albaranesProveedor.facturasProveedor=:fact group by fil.articuloBean").setParameter("fact", fact).getResultList();
		System.out.println("numero de filas en factura "+filas.size());
		for (Object[] fila:filas) {
			Articulo art=(Articulo) fila[0];
			System.out.println(art.getNombre()+"\t"+fila[1]+"\t"+fila[2]);
			FilaFacturaProveedor filaFact=new FilaFacturaProveedor();
			filaFact.setFacturasProveedor(fact);
			filaFact.setArticuloBean(art);
			int cantidad=((Long) fila[1]).intValue();
			filaFact.setCantidad(cantidad);
			filaFact.setPrecio((double) fila[2]);
			filasFact.add(filaFact);
		}
		return filasFact;
	}
	
	public int modificaFacturaGenerada(FacturaProveedor fact) {
		abrir();
		if (em==null) return -1;
		FacturaProveedor antigua=em.find(FacturaProveedor.class, fact.getNum());
		antigua.getFilasFacturasProveedors().clear();
		for (FilaFacturaProveedor fila:fact.getFilasFacturasProveedors())
			antigua.getFilasFacturasProveedors().add(fila);
		em.getTransaction().begin();
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
	public int modificaFactura(FacturaProveedor fact) {
		abrir();
		if (em==null) return -1;
		FacturaProveedor antigua=em.find(FacturaProveedor.class, fact.getNum());
		em.getTransaction().begin();
		for (FilaFacturaProveedor fi:antigua.getFilasFacturasProveedors()) 
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
	public List<FacturaProveedor> listarFacturas(){
		abrir();
		if (em==null) return null;
		List<FacturaProveedor> lista=em.createQuery("select fact from FacturaProveedor fact").getResultList();
		em.close();
		return lista;
	}
}
