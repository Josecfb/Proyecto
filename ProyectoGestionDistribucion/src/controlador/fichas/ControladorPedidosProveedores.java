package controlador.fichas;

import java.util.List;


import model.Proveedor;
import modelo.negocio.GestorPedidosProve;
import vista.PedidosProveedores;

public class ControladorPedidosProveedores {
	private PedidosProveedores pedidosProve;
	
	public ControladorPedidosProveedores(PedidosProveedores pedidosProve) {
		listar(pedidosProve);
	}
	public void listar(PedidosProveedores pedidosProve) {
		this.pedidosProve=pedidosProve;
		List<Object[]> filas;
		GestorPedidosProve gpp=new GestorPedidosProve();
		filas=gpp.listar();
		System.out.println(filas.size());
		pedidosProve.muestraPendientes(filas);
	}
}
