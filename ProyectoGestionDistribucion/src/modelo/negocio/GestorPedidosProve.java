package modelo.negocio;

import java.util.List;

import model.Articulo;
import model.FilaPedidoProveedor;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.persistencia.DaoPedidosProveedores;

public class GestorPedidosProve {
	private DaoPedidosProveedores dpp;
	
	public PedidoProveedor existe(int num) {
		return dpp.existe(num);
	}
	
	public GestorPedidosProve() {
		dpp=new DaoPedidosProveedores();
	}

	
	public List<FilaPedidoProveedor> listaFilasPedido(PedidoProveedor pedido){
		return dpp.listaFilasPedido(pedido);
	}
	
	public FilaPedidoProveedor existeFila(PedidoProveedor ped, Articulo art) {
		return dpp.existeFila(ped,art);
	}
	
	public int modificarPedido(PedidoProveedor ped) {
		
		return dpp.modificarPedido(ped);
	}
	
	public boolean[] nuevoPedido(PedidoProveedor ped) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=ped.getProveedore()!=null;
		ok[1]=ped.getFecha()!=null;
		int res=0;
		if (ok[0] && ok[1]) {
			res=dpp.nuevoPedido(ped);
			ok[2]=res==-1;
			ok[3]=res==0;
		}
		return ok;
	}
	
	public List<PedidoProveedor> listar(Proveedor pro){
		return dpp.listadoPendientes(pro);
	}
	
	public List<PedidoProveedor> listaEnviados(Proveedor pro){
		return dpp.listaEnviados(pro);
	}
}
