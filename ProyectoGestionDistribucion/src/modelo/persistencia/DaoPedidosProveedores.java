package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import model.Articulo;
import model.Proveedor;

public class DaoPedidosProveedores {
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listadoPendientes(){
		List<Object[]> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select distinct art.proveedorBean, sum(function('ceil',(art.stockMinimo-art.stock)/art.unidadesCaja)*art.unidadesCaja*art.coste) from Articulo art where art.stock-art.stockMinimo<0 and art.enPedido=0 group by art.proveedorBean").getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
	public List<Articulo> articulosPendientesPedido(Proveedor pro){
		List<Articulo> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select art from Articulo art where art.proveedorBean=:pro and art.enPedido=0 and art.stock-art.stockMinimo<0").setParameter("pro", pro).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
}
