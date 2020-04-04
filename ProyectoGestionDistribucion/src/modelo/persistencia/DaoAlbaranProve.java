package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import model.AlbaranProveedor;
import model.Articulo;
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
	
	@SuppressWarnings("unchecked")
	public List<FilasAlbaranProveedor> generaFilas(AlbaranProveedor alb){
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		em.getTransaction().begin();
		if (em==null) return null;
		List<Object[]> filas;
		filas=em.createQuery("select fil.articuloBean,sum(fil.cantidad) from FilaPedidoProveedor fil where fil.pedidosProveedor.albaranesProveedor=:alb group by fil.articuloBean").setParameter("alb", alb).getResultList();
		for (Object[] fila:filas) {
			Articulo art=(Articulo) fila[0];
			
			System.out.println(art.getNombre()+"\t"+fila[1]+"\t"+fila[2]);
		}
		return null;
	}
	
}
