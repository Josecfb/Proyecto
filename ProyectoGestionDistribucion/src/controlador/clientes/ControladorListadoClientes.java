package controlador.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import controlador.clientes.albaranes.ControladorAlbaranesClientes;
import controlador.clientes.facturas.ControladorFacturasClientes;
import entidades.Cliente;
import modelo.negocio.GestorCliente;
import vista.clientes.VFichaCliente;
import vista.clientes.VListadoClientes;
import vista.clientes.albaranes.VAlbaranesClientes;
import vista.clientes.facturas.VFacturasClientes;
import vista.clientes.pedidos.VPedidosClientes;


public class ControladorListadoClientes implements ActionListener{
	private VListadoClientes listado;

	
	public ControladorListadoClientes(VListadoClientes listado) {
		this.listado=listado;
		listar();		
	}
	/**
	 * Obtiene la lista con todos los clientes y llama al método muestra(filas de la ventana listado de clientes
	 * @param listado Ventana listado de clientes
	 */
	public void listar() {
		GestorCliente gc=new GestorCliente();
		List<Cliente> filas=gc.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) 
			listar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoCliente();
		if (e.getSource()==listado.getbActualizar())
			listar();
		if (e.getSource()==listado.getbPedidos())
			abrePedidos();
		if (e.getSource()==listado.getbAlbaranes()) {
			abreAlbaranes();
		}
		if (e.getSource()==listado.getbFacturas()) {
			abreFacturas();
		}
	}
	
	private void abrePedidos() {
		VPedidosClientes pc=new VPedidosClientes(listado.getV());
		listado.getV().getPanelInterior().add(pc);
		pc.setVisible(true);
	}
	
	private void abreFacturas() {
		VFacturasClientes vfc=new VFacturasClientes(listado.getV());
		ControladorFacturasClientes cfc=new ControladorFacturasClientes(vfc);
		vfc.establecerManejador(cfc);
		listado.getV().getPanelInterior().add(vfc);
		vfc.setVisible(true);
	}
	
	private void abreAlbaranes() {
		VAlbaranesClientes vac=new VAlbaranesClientes(listado.getV());
		ControladorAlbaranesClientes cac=new ControladorAlbaranesClientes(vac);
		vac.establecerManejador(cac);
		listado.getV().getPanelInterior().add(vac);
		vac.setVisible(true);
	}
	
	private void muestraFiltros() {
		if (listado.getbFiltrar().isVisible()) {
			listado.getbFiltrar().setVisible(false);
			listado.getTFiltroNombre().setVisible(false);
			listado.getTFiltroNombre().setText("");
			listar();
			listado.getbFiltros().setIcon(new ImageIcon("src/img/filtro.png"));
		}
		else {
			listado.getbFiltrar().setVisible(true);
			listado.getTFiltroNombre().setVisible(true);
			listado.getbFiltros().setIcon(new ImageIcon("src/img/nofiltro.png"));
		}
	}

	private void nuevoCliente() {
		VFichaCliente fc=new VFichaCliente(null,listado);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		listado.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}
	

}
