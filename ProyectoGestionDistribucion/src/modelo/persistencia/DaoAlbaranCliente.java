package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import entidades.AlbaranCliente;
import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasAlbaranCliente;
/**
 * Gestiona la persistencia de los albaranes de cliente
 * @author Jose Carlos
 *
 */
public class DaoAlbaranCliente {
	private EntityManager em;
	/**
	 * Persiste un nuevo albarán de cliente
	 * @param alb Objeto AlbaranCliente
	 * @return -1 error 0 correcto
	 */
	public int nuevoAlbaran(AlbaranCliente alb) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(alb);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Modifica el albarán generado
	 * @param alb Objeto Albaran
	 * @return -1 error 0 correcto
	 */
	public int modificaAlbaranGenerado(AlbaranCliente alb) {
		abrir();
		if (em==null) return -1;
		AlbaranCliente antiguo=em.find(AlbaranCliente.class, alb.getNum());
		antiguo.getFilasAlbaranClientes().clear();
		for (FilasAlbaranCliente fila:alb.getFilasAlbaranClientes())
			antiguo.getFilasAlbaranClientes().add(fila);
		em.getTransaction().begin();
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	/**
	 * Modifica un albarán
	 * @param alb Objeto AlbaranCliente
	 * @return -1 error 0 correcto
	 */
	public int modificaAlbaran(AlbaranCliente alb) {
		abrir();
		if (em==null) return -1;
		AlbaranCliente antiguo=em.find(AlbaranCliente.class, alb.getNum());
		em.getTransaction().begin();
		for (FilasAlbaranCliente fi:antiguo.getFilasAlbaranClientes()) 
			em.remove(fi);
		em.getTransaction().commit();
		em.getTransaction().begin();
		antiguo=alb;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		System.gc();
		return 0;
	}
	/**
	 * Modifica albaran procedente de facturas
	 * @param alb Objeto Albaran
	 * @return -1 error 0 correcto
	 */
	public int facturaAlbaran(AlbaranCliente alb) {
		abrir();
		if (em==null) return -1;
		AlbaranCliente antiguo=em.find(AlbaranCliente.class, alb.getNum());
		em.getTransaction().begin();
		antiguo=alb;
		em.merge(antiguo);
		em.getTransaction().commit();
		em.close();
		System.gc();
		return 0;
	}
	/**
	 * Obtiene la lista de todos los albaranes de cliente
	 * @return List de AlbaranCliente
	 */
	@SuppressWarnings("unchecked")
	public List<AlbaranCliente> listarAlbaranes(){
		abrir();
		if (em==null) return null;
		List<AlbaranCliente> lista=em.createQuery("select alb from AlbaranCliente alb").getResultList();
		em.close();
		return lista;
	}
	/**
	 * Abre la conexión
	 */
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	/**
	 * Genera la filas de un albaran a partir de los pedido asociados
	 * @param alb Objeto Albartan
	 * @return List de FilasAlbaranCliente
	 */
	@SuppressWarnings("unchecked")
	public List<FilasAlbaranCliente> generaFilas(AlbaranCliente alb){
		List<FilasAlbaranCliente> filasAlb=new ArrayList<FilasAlbaranCliente>();
		abrir();
		if (em==null) return null;
		List<Object[]> filas;
		filas=em.createQuery("select fil.articuloBean,sum(fil.cantidad),fil.precio from FilasPedidosCliente fil where fil.pedidosCliente.albaranCliente=:alb group by fil.articuloBean").setParameter("alb", alb).getResultList();
		System.out.println("numero de filas para albaran generado= "+filas.size());
		for (Object[] fila:filas) {
			Articulo art=(Articulo) fila[0];
			FilasAlbaranCliente filaAlb=new FilasAlbaranCliente();
			filaAlb.setAlbaranCliente(alb);
			filaAlb.setArticuloBean(art);
			int cantidad=((Long) fila[1]).intValue();
			filaAlb.setCantidad(cantidad);
			filaAlb.setPrecio((Double) fila[2]);
			filasAlb.add(filaAlb);
		}
		return filasAlb;
	}
	/**
	 * Devuelve la lista de albaranes de un cliente dado
	 * @param cli Objeto Cliente
	 * @return List AlbaranCliente
	 */
	@SuppressWarnings("unchecked")
	public List<AlbaranCliente> listaAlbaranesAlmacen(Cliente cli){
		abrir();
		if (em==null) return null;
		List<AlbaranCliente> lista=em.createQuery("select alb from AlbaranCliente alb where alb.clienteBean=:cli and alb.actualizadoAlmacen=true and alb.facturado=false").setParameter("cli", cli).getResultList();
		em.close();
		return lista;
	}
	/**
	 * Re torna el albaran de un número de cliente
	 * @param num número de cliente
	 * @return Objeto AlbaranCliente
	 */
	public AlbaranCliente existe(int num) {
		abrir();
		if (em==null) return null;
		AlbaranCliente alb=em.find(AlbaranCliente.class, num);
		em.close();
		return alb;
	}
	
}
