package controlador.clientes.pedidos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import entidades.Articulo;
import entidades.Cliente;
import entidades.FilasPedidosCliente;
import entidades.PedidoCliente;
import modelo.negocio.GestorPedidosCliente;
import util.Utilidades;
import vista.clientes.pedidos.VPedidoCliente;
import vista.clientes.pedidos.VFilaPedidoCliente;
/**
 * Controla la ventana de un pedido de cliente
 * @author Jose Carlos
 *
 */
public class ControladorPedidoCliente implements InternalFrameListener, FocusListener,ActionListener{
	private Utilidades u;
	private GestorPedidosCliente gpc;
	private VPedidoCliente vpedidoCliente;
	private List<FilasPedidosCliente> filasmodificadas;
	/**
	 * El constructor recibe como argumento la ventana de pedido de un cliente
	 * @param pedidoCliente Vista de la ventana de pedido de un cliente VPedidoCliente
	 */
	public ControladorPedidoCliente(VPedidoCliente pedidoCliente) {
		this.vpedidoCliente=pedidoCliente;
		gpc=new GestorPedidosCliente();
		u=new Utilidades();
	}
	/**
	 * Cuando se cierra la ventana pregunta si desea guardar, en caso afirmativo el albarán es editado lo modifica en la base de datos
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION)
				modificaPedido();
			else
				vpedidoCliente.dispose();
	}
	/**
	 * Modifica el pedido llamando al método modificarPedido del gestor de pedidos de cliente
	 */
	private void modificaPedido() {
		PedidoCliente pedModif=new PedidoCliente();
		pedModif.setNum(Integer.valueOf(vpedidoCliente.gettNumpedido().getText()));
		asignaCampos(pedModif);
		ponFilas(pedModif);
		int ok=gpc.modificarPedido(pedModif);
		if (ok==0) {
			actualizarListaPedidos();
			vpedidoCliente.dispose();
		}
	
	}
	/**
	 * Actualiza el listado de pedidos llamando al método listar de la ventana de la lista de pedidos
	 */
	private void actualizarListaPedidos() {
		ControladorPedidosClientes cpp = new ControladorPedidosClientes(vpedidoCliente.getVpedidos());
		cpp.listar(vpedidoCliente.getVpedidos());
	}
	/**
	 * Asigna los campos de la cabecera de la ventana del pedido al objeto pedModif
	 * @param pedModif
	 */
	private void asignaCampos(PedidoCliente pedModif) {
		pedModif.setFecha(vpedidoCliente.getcFecha().getDate());
		pedModif.setClienteBean((Cliente) vpedidoCliente.getComboCliente().getSelectedItem());
		pedModif.setEnviado(vpedidoCliente.getChecEnviado().isSelected());	
		
		vpedidoCliente.getPanel().updateUI();
		
	}
	/**
	 * Asigna las filas de la ficha del pedido al objeto pedModif 
	 * si se repiten filas con el mismo artículo se suman las cantidades
	 * @param pedModif
	 */
	private void ponFilas(PedidoCliente pedModif) {
		FilasPedidosCliente filaModif;
		Component[] componentes=vpedidoCliente.getPanel().getComponents();
		filasmodificadas=new ArrayList<FilasPedidosCliente>();
		for (Component fila:componentes) {
			filaModif=new FilasPedidosCliente();
			VFilaPedidoCliente fil=(VFilaPedidoCliente) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,pedModif);
			if (filasmodificadas.contains(filaModif))
				filasmodificadas.get(filasmodificadas.indexOf(filaModif)).setCantidad(filasmodificadas.get(filasmodificadas.indexOf(filaModif)).getCantidad()+filaModif.getCantidad());
			else
				filasmodificadas.add(filaModif);
		}
		pedModif.setFilasPedidosClientes(filasmodificadas);
	}
	/**
	 * Asigna los campos de una fila del pedido a la fila de pedido de la entidad pedModif
	 * @param vFila Vista de la fila del pedido de cliente VFilaPedidoCliente 
	 * @param filaModif Objeto entidad fila de pedido de cliente FilasPedidosCliente
	 * @param pedModif Objeto entidad pedido de cliente PedidoCliente
	 */
	private void asignaCamposFila(VFilaPedidoCliente vFila,FilasPedidosCliente filaModif,PedidoCliente pedModif) {
		vFila.updateUI();
		vFila.getArticulo().requestFocus();
		Articulo arti=(Articulo) vFila.getArticulo().getSelectedItem();
		filaModif.setPedidosCliente(pedModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(vFila.gettUnidades().getText()));
		filaModif.setPrecio(u.euroADoble(vFila.gettPrecio().getText()));
	}
	
	/**
	 * Retorna las filas del pedido del cliente
	 * @param pedido Objeto entidad pedido de cliente PedidoCliente
	 * @return lista de filas del pedido de cliente List de FilasPedidosCliente
	 */
	public List<FilasPedidosCliente> articulosPendientesPedido(PedidoCliente pedido){
		return gpc.listaFilasPedido(pedido);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Al salir del combobox de cliente asigna el cliente al pedido
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vpedidoCliente.getComboCliente().getEditor().getEditorComponent() && vpedidoCliente.getComboCliente().getSelectedItem()!=null) {
			vpedidoCliente.getPanel().updateUI();
			PedidoCliente pc=new PedidoCliente();
			pc.setClienteBean((Cliente) vpedidoCliente.getComboCliente().getSelectedItem());
			vpedidoCliente.setPed(pc);
		}
		
	}
	/**
	 * Crea un fila nueva en el pedido al pulsar el botón de nueva fila
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vpedidoCliente.getbNuevaFila())
			vpedidoCliente.nuevaFila();
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
