package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import entidades.Articulo;
import entidades.FacturaCliente;
import entidades.FilaFacturaCliente;
/**
 * Gestiona la persistencia de Facturas de cliente
 * @author Jose Carlos
 *
 */
public class DaoFacturaCliente {
	private EntityManager em;
	/**
	 * Abre la conexión
	 */
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	/**
	 * Persiste una factura nueva
	 * @param fact Objeto FacturaCliente
	 * @return -1 error 0 correcto
	 */
	public int nuevaFactura(FacturaCliente fact) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(fact);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Modifica una factura existente
	 * @param fact Objet FacturaCliente
	 * @return -1 error 0 correcto
	 */
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
	/**
	 * Obtiene la lista de facturas
	 * @return List de FacturaCliente
	 */
	@SuppressWarnings("unchecked")
	public List<FacturaCliente> listarFacturas(){
		abrir();
		if (em==null) return null;
		List<FacturaCliente> lista=em.createQuery("select fact from FacturaCliente fact order by fact.fecha desc, fact.num desc").getResultList();
		em.close();
		return lista;
	}
	/**
	 * Genera las filas de una factura generada a partir de albaranes de cliente
	 * @param fact Objeto FacturaCliente
	 * @return List de FilaFacturaCliente
	 */
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
	/**
	 * Modifica una factura existente
	 * @param fact Objeto FacturaCliente
	 * @return -1 error 0 correcto
	 */
	public int modificaFacturaGenerada(FacturaCliente fact) {
		abrir();
		if (em==null) return -1;
		FacturaCliente antigua=em.find(FacturaCliente.class, fact.getNum());
		antigua.getFilasFacturasClientes().clear();
		for (FilaFacturaCliente fila:fact.getFilasFacturasClientes())
			antigua.getFilasFacturasClientes().add(fila);
		System.out.println("numero de filas fact "+fact.getFilasFacturasClientes().size());
		System.out.println("numero de filas antigua "+antigua.getFilasFacturasClientes().size());
		em.getTransaction().begin();
		em.merge(antigua);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

}
