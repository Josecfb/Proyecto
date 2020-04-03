package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import model.AlbaranProveedor;
import model.FilaPedidoProveedor;
import model.FilasAlbaranProveedor;
import model.PedidoProveedor;

public class DaoAlbaranProve {
	private EntityManager em;

	public int nuevoAlbaran(AlbaranProveedor alb) {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return -1;
		
		em.persist(alb);
		
		em.getTransaction().commit();
		System.out.println("fecha albaran "+alb.getFecha()+"y numero "+alb.getNum());
		em.close();
		return 0;
	}
	
	public List<FilasAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return null;
		List<Object[]> filas;
		//filas=em.createQuery("select fil from FilaPedidoProveedor fil where fil.pedidosProveedor")
		return null;
	}
	
}
