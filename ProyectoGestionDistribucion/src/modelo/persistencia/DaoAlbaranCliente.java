package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import entidades.AlbaranCliente;
import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasAlbaranCliente;

public class DaoAlbaranCliente {
	private EntityManager em;

	public int nuevoAlbaran(AlbaranCliente alb) {
		abrir();
		if (em==null) return -1;
		em.getTransaction().begin();
		em.persist(alb);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
	
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
	
	@SuppressWarnings("unchecked")
	public List<AlbaranCliente> listarAlbaranes(){
		abrir();
		if (em==null) return null;
		List<AlbaranCliente> lista=em.createQuery("select alb from AlbaranCliente alb").getResultList();
		em.close();
		return lista;
	}

	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	
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
	
	@SuppressWarnings("unchecked")
	public List<AlbaranCliente> listaAlbaranesAlmacen(Cliente cli){
		abrir();
		if (em==null) return null;
		List<AlbaranCliente> lista=em.createQuery("select alb from AlbaranCliente alb where alb.clienteBean=:cli and alb.actualizadoAlmacen=true and alb.facturado=false").setParameter("cli", cli).getResultList();
		em.close();
		return lista;
	}
	
	public AlbaranCliente existe(int num) {
		abrir();
		if (em==null) return null;
		AlbaranCliente alb=em.find(AlbaranCliente.class, num);
		em.close();
		return alb;
	}
	
}
