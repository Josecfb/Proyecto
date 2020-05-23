package controlador.proveedores.pedidos;

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
import entidades.FilaPedidoProveedor;
import entidades.FilasPedidosProveedorPK;
import entidades.PedidoProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorPedidosProve;
import vista.pdf.PdfPedidoProveedor;
import vista.proveedores.pedidos.VFilaPedidoProveedor;
import vista.proveedores.pedidos.VPedidoProveedor;

public class ControladorPedidoProveedor implements InternalFrameListener, FocusListener,ActionListener{
	private GestorPedidosProve gpp;
	private VPedidoProveedor vpedidoProveedor;
	private List<FilaPedidoProveedor> filasmodificadas;
	
	public ControladorPedidoProveedor(VPedidoProveedor pedidoProveedor) {
		this.vpedidoProveedor=pedidoProveedor;
		gpp=new GestorPedidosProve();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
			if (res==JOptionPane.YES_OPTION) {
				if (!vpedidoProveedor.gettNumpedido().getText().equals(""))
					modificaPedido();
				else
					nuevoPedido();
				actualizarListaPedidos();
			}
			else
				vpedidoProveedor.dispose();
	}
	
	private void modificaPedido() {
		PedidoProveedor pedModif=new PedidoProveedor();
		pedModif.setNum(Integer.valueOf(vpedidoProveedor.gettNumpedido().getText()));
		asignaCampos(pedModif);
		ponFilas(pedModif);
		int ok=gpp.modificarPedido(pedModif);
		if (ok==0) 
			vpedidoProveedor.dispose();
//		else 
//			muestraErrores(ok);		
	}

	private void actualizarListaPedidos() {
		ControladorPedidosProveedores cpp = new ControladorPedidosProveedores(vpedidoProveedor.getVpedidos());
		cpp.listar(vpedidoProveedor.getVpedidos());
	}
	
	private void nuevoPedido() {
		PedidoProveedor pedidoNuevo=new PedidoProveedor();
		asignaCampos(pedidoNuevo);
		boolean[] ok=new boolean[4];
		ok=gpp.nuevoPedido(pedidoNuevo);
		ponFilas(pedidoNuevo);
		if (ok[3]) gpp.modificarPedido(pedidoNuevo);
		if (ok[3]) 
			vpedidoProveedor.dispose();
		else
			muestraErrores(ok);
		
	}
	
	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Proveedor vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Fecha vacía","error",JOptionPane.ERROR_MESSAGE);
		
	}
	

	private void asignaCampos(PedidoProveedor pedModif) {
		pedModif.setFecha(vpedidoProveedor.getcFecha().getDate());
		pedModif.setProveedore((Proveedor) vpedidoProveedor.getComboProveedor().getSelectedItem());
		pedModif.setConfirmado(vpedidoProveedor.getChecConfirmado().isSelected());
		pedModif.setEnviado(vpedidoProveedor.getChecEnviado().isSelected());	
		if (pedModif.getEnviado() && !pedModif.getConfirmado())
			new PdfPedidoProveedor(pedModif);
		vpedidoProveedor.getPanel().updateUI();
		
	}

	private void ponFilas(PedidoProveedor pedModif) {
		FilaPedidoProveedor filaModif;
		Component[] componentes=vpedidoProveedor.getPanel().getComponents();
		filasmodificadas=new ArrayList<FilaPedidoProveedor>();
		for (Component fila:componentes) {
			filaModif=new FilaPedidoProveedor();
			VFilaPedidoProveedor fil=(VFilaPedidoProveedor) fila;
			fil.updateUI();
			if (fil.getFila()!=null)
				asignaCamposFila(fil,filaModif,pedModif);
			filasmodificadas.add(filaModif);
		}
		pedModif.setFilaPedidoProveedor(filasmodificadas);
	}
	
	private void asignaCamposFila(VFilaPedidoProveedor fila,FilaPedidoProveedor filaModif,PedidoProveedor pedModif) {
		fila.updateUI();
		FilasPedidosProveedorPK clave=new FilasPedidosProveedorPK();
		fila.getArticulo().requestFocus();
		Articulo arti=(Articulo) fila.getArticulo().getSelectedItem();
		clave.setArticulo((((Articulo) fila.getArticulo().getSelectedItem()).getCod()));
		clave.setPedido(pedModif.getNum());
		filaModif.setId(clave);
		filaModif.setPedidosProveedor(pedModif);
		filaModif.setArticuloBean(arti);
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
	}
	
	public List<FilaPedidoProveedor> articulosPendientesPedido(PedidoProveedor pedido){
		return gpp.listaFilasPedido(pedido);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vpedidoProveedor.getComboProveedor().getEditor().getEditorComponent() && vpedidoProveedor.getComboProveedor().getSelectedItem()!=null) {
			vpedidoProveedor.getPanel().updateUI();
			PedidoProveedor pp=new PedidoProveedor();
			pp.setProveedore((Proveedor) vpedidoProveedor.getComboProveedor().getSelectedItem());
			vpedidoProveedor.setPed(pp);
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
		if (e.getSource()==vpedidoProveedor.getbNuevaFila())
			vpedidoProveedor.nuevaFila();
		
	}
	
}
