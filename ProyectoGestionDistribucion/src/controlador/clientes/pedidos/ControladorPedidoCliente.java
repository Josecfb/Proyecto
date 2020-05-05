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
import model.Articulo;
import model.Cliente;
import model.FilasPedidosCliente;
import model.PedidoCliente;
import modelo.negocio.GestorPedidosCliente;
import vista.clientes.pedidos.VPedidoCliente;
import vista.clientes.pedidos.VFilaPedidoCliente;

public class ControladorPedidoCliente implements InternalFrameListener, FocusListener,ActionListener{
	private GestorPedidosCliente gpc;
	private VPedidoCliente vpedidoCliente;
	private List<FilasPedidosCliente> filasmodificadas;
	
	public ControladorPedidoCliente(VPedidoCliente pedidoCliente) {
		this.vpedidoCliente=pedidoCliente;
		gpc=new GestorPedidosCliente();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION)
				if (!vpedidoCliente.gettNumpedido().getText().equals(""))
					modificaPedido();
				else
					nuevoPedido();
			else
				vpedidoCliente.dispose();
	}
	
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
		//else 
			//muestraErrores(ok);		
	}

	private void actualizarListaPedidos() {
		ControladorPedidosClientes cpp = new ControladorPedidosClientes(vpedidoCliente.getVpedidos());
		cpp.listar(vpedidoCliente.getVpedidos());
	}
	
	private void nuevoPedido() {
		PedidoCliente pedidoNuevo=new PedidoCliente();
		asignaCampos(pedidoNuevo);
		boolean[] ok=new boolean[4];
		ok=gpc.nuevoPedido(pedidoNuevo);
		ponFilas(pedidoNuevo);
		if (ok[3]) gpc.modificarPedido(pedidoNuevo);
		if (ok[3]) {
			actualizarListaPedidos();
			vpedidoCliente.dispose();
		}
		else
			muestraErrores(ok);
		
	}
	
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Proveedor vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Fecha vacía","error",JOptionPane.ERROR_MESSAGE);
		
	}
	

	private void asignaCampos(PedidoCliente pedModif) {
		pedModif.setFecha(vpedidoCliente.getcFecha().getDate());
		pedModif.setClienteBean((Cliente) vpedidoCliente.getComboCliente().getSelectedItem());
		pedModif.setConfirmado(vpedidoCliente.getChecConfirmado().isSelected());
		pedModif.setEnviado(vpedidoCliente.getChecEnviado().isSelected());	
		
		vpedidoCliente.getPanel().updateUI();
		
	}

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
			filasmodificadas.add(filaModif);
		}
		pedModif.setFilasPedidosClientes(filasmodificadas);
	}
	
	private void asignaCamposFila(VFilaPedidoCliente fila,FilasPedidosCliente filaModif,PedidoCliente pedModif) {
		fila.updateUI();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		filaModif.setPedidosCliente(pedModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
	}
	
	public List<FilasPedidosCliente> articulosPendientesPedido(PedidoCliente pedido){
		return gpc.listaFilasPedido(pedido);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vpedidoCliente.getComboCliente().getEditor().getEditorComponent() && vpedidoCliente.getComboCliente().getSelectedItem()!=null) {
			vpedidoCliente.getPanel().updateUI();
			PedidoCliente pc=new PedidoCliente();
			pc.setClienteBean((Cliente) vpedidoCliente.getComboCliente().getSelectedItem());
			vpedidoCliente.setPed(pc);
		}
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vpedidoCliente.getbNuevaFila())
			vpedidoCliente.nuevaFila();
		
	}
	
}
