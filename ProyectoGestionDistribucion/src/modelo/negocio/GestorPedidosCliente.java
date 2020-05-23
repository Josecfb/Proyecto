package modelo.negocio;

import java.util.List;

import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasPedidosCliente;
import entidades.PedidoCliente;
import modelo.persistencia.DaoPedidosClientes;

public class GestorPedidosCliente {
	private DaoPedidosClientes dpc;
	
	public PedidoCliente existe(int num) {
		return dpc.existe(num);
	}
	
	public GestorPedidosCliente() {
		dpc=new DaoPedidosClientes();
	}

	
	public List<FilasPedidosCliente> listaFilasPedido(PedidoCliente pedido){
		return dpc.listaFilasPedido(pedido);
	}
	
	public FilasPedidosCliente existeFila(PedidoCliente ped, Articulo art) {
		return dpc.existeFila(ped,art);
	}
	
	public int modificarPedido(PedidoCliente ped) {
		
		return dpc.modificarPedido(ped);
	}
	
	public boolean[] nuevoPedido(PedidoCliente ped) {
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=ped.getClienteBean()!=null;
		ok[1]=ped.getFecha()!=null;
		int res=0;
		if (ok[0] && ok[1]) {
			res=dpc.nuevoPedido(ped);
			ok[2]=res==-1;
			ok[3]=res==0;
		}
		return ok;
	}
	
	public List<PedidoCliente> listar(Cliente cli){
		return dpc.listadoPendientes(cli);
	}
	
	public List<PedidoCliente> listaEnviados(Cliente cli){
		return dpc.listaEnviados(cli);
	}
}
