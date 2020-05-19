package controlador.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;

import controlador.clientes.pedidos.ControladorPedidosClientes;
import controlador.proveedores.pedidos.ControladorPedidosProveedores;
import model.Cliente;
import modelo.negocio.GestorCliente;
import vista.clientes.VFichaCliente;
import vista.clientes.VListadoClientes;
import vista.clientes.pedidos.VPedidosClientes;
import vista.proveedores.pedidos.VPedidosProveedores;

public class ControladorListadoClientes implements ActionListener{
	private VListadoClientes listado;

	
	public ControladorListadoClientes(VListadoClientes listado) {
		listar(listado);		
	}
	/**
	 * Obtiene la lista con todos los clientes y llama al método muestra(filas de la ventana listado de clientes
	 * @param listado Ventana listado de clientes
	 */
	private void listar(VListadoClientes listado) {
		this.listado=listado;
		GestorCliente gc=new GestorCliente();
		List<Cliente> filas=gc.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) 
			filtrar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoCliente();
		if (e.getSource()==listado.getbActualizar())
			actualizar();
		if (e.getSource()==listado.getbPedidos())
			abrePedidos();
	}
	
	private void abrePedidos() {
		VPedidosClientes pp=new VPedidosClientes(listado.getV());
		listado.getV().getPanelInterior().add(pp);
		pp.setVisible(true);
	}
	
	private void muestraFiltros() {
		if (listado.getbFiltrar().isVisible()) {
			listado.getbFiltrar().setVisible(false);
			listado.getTFiltroNombre().setVisible(false);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/filtro.png"));
		}
		else {
			listado.getbFiltrar().setVisible(true);
			listado.getTFiltroNombre().setVisible(true);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/nofiltro.png"));
		}
	}

	private void filtrar() {
		listado.getPanel().updateUI();
		listar(listado);
	}
	
	private void actualizar() {
		listado.getPanel().updateUI();
	}
	
	private void nuevoCliente() {
		VFichaCliente fc=new VFichaCliente(null);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		listado.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}
	

}
