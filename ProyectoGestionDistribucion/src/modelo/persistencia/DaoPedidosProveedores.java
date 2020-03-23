package modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

import model.Articulo;
import model.PedidosProveedor;
import model.FilasPedidosProveedor;
import model.Proveedor;

public class DaoPedidosProveedores {
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PedidosProveedor> listadoPendientes(){
		List<Proveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select distinct art.proveedorBean from Articulo art where art.stock-art.stockMinimo<0 and art.enPedido=0").getResultList();
		for (Proveedor proPedido:lista) {
			PedidosProveedor pedido=new PedidosProveedor();
			List<PedidosProveedor> pedidosSinEnviar=em.createQuery("select ped from PedidosProveedor ped where ped.enviado=0 and ped.proveedore=:propedido").setParameter("propedido", proPedido).getResultList();
			if (pedidosSinEnviar.size()==0) {
				pedido.setProveedore(proPedido);
				em.getTransaction().begin();
				em.persist(pedido);
				em.getTransaction().commit();
				List<Articulo> listaArt=em.createQuery("select art from Articulo art where art.proveedorBean=:pro and art.enPedido=0 and art.stock-art.stockMinimo<0").setParameter("pro", proPedido).getResultList();
				System.out.println("numero de filas"+listaArt.size());
				for (Articulo art:listaArt) {					
					FilasPedidosProveedor filaPed=new FilasPedidosProveedor();
					filaPed.setPedidosProveedor(pedido);
					filaPed.setArticuloBean(art);
					filaPed.setCantidad((int) Math.ceil((double)(art.getStockMinimo()-art.getStock())/art.getUnidadesCaja())*art.getUnidadesCaja());
					em.getTransaction().begin();
					em.persist(filaPed);
					em.getTransaction().commit();
				}
			}
		}
		List<PedidosProveedor> listaPedidos=em.createQuery("select ped from PedidosProveedor ped where ped.enviado=0").getResultList();
		ab.cerrarConexion();
		return listaPedidos;
	}
	
	@SuppressWarnings("unchecked")
	public List<FilasPedidosProveedor> articulosPendientesPedido(PedidosProveedor pedido){
		List<FilasPedidosProveedor> lista;
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
		if (em==null)
			return null;
		else
			lista=em.createQuery("select fil from FilasPedidosProveedor fil where fil.pedidosProveedor=:pedido").setParameter("pedido", pedido).getResultList();
		ab.cerrarConexion();
		return lista;
	}
	
}
