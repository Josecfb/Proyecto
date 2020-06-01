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

/**
 * Controla las pulsaciones de los botones del listado de clientes
 * y genera la lista de clientes
 * @author Jose Carlos
 *
 */
public class ControladorListadoClientes implements ActionListener{
	private VListadoClientes listado;

	/**
	 * El constructor ejecuta directamnte el método listar
	 * @param listado Ventana de listado de clientes VListadoClientes
	 */
	public ControladorListadoClientes(VListadoClientes listado) {
		this.listado=listado;
		listar();		
	}
	/**
	 * Obtiene la lista con todos los clientes y llama al método muestra(filas de la ventana listado de clientes
	 */
	public void listar() {
		GestorCliente gc=new GestorCliente();
		List<Cliente> filas=gc.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	/**
	 * Si se pulsa un botón, activa filtro, ejecuta filtro, nuevo cliente, actualizar listado, ventana de pedidos, ventana de albaranes y ventana de facturas
	 */
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
	/**
	 * Abre la ventana de pedidos del cliente
	 */
	private void abrePedidos() {
		VPedidosClientes pc=new VPedidosClientes(listado.getV());
		listado.getV().getPanelInterior().add(pc);
		pc.setVisible(true);
	}
	/**
	 * Abre la ventana de facturas del cliente
	 */
	private void abreFacturas() {
		VFacturasClientes vfc=new VFacturasClientes(listado.getV());
		ControladorFacturasClientes cfc=new ControladorFacturasClientes(vfc);
		vfc.establecerManejador(cfc);
		listado.getV().getPanelInterior().add(vfc);
		vfc.setVisible(true);
	}
	/**
	 * Abre la ventana de albaranes del cliente
	 */
	private void abreAlbaranes() {
		VAlbaranesClientes vac=new VAlbaranesClientes(listado.getV());
		ControladorAlbaranesClientes cac=new ControladorAlbaranesClientes(vac);
		vac.establecerManejador(cac);
		listado.getV().getPanelInterior().add(vac);
		vac.setVisible(true);
	}
	/**
	 * Activa desactiva el filtro por nombre de la lista de clientes
	 */
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
	/**
	 * Abre una ficha para nuevo cliente
	 */
	private void nuevoCliente() {
		VFichaCliente fc=new VFichaCliente(null,listado);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		listado.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}
	

}
