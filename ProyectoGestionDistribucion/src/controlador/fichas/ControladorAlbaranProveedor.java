package controlador.fichas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import model.AlbaranProveedor;
import model.Articulo;
import model.FilaPedidoProveedor;
import model.FilaAlbaranProveedor;
import model.FilasPedidosProveedorPK;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import modelo.negocio.GestorPedidosProve;
import vista.fichas.VAlbaranProveedor;
import vista.fichas.VFilaPedidoProveedor;
import vista.fichas.VPedidoProveedor;
import vista.pdf.PdfPedidoProveedor;

public class ControladorAlbaranProveedor implements InternalFrameListener, FocusListener,ActionListener{
	private GestorAlbaranProve gap;
	private VAlbaranProveedor vAlbaran;
	private List<FilaAlbaranProveedor> filasAlb;
	
	public ControladorAlbaranProveedor(VAlbaranProveedor vAlbaran) {
		this.vAlbaran=vAlbaran;
		gap=new GestorAlbaranProve();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
//			int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
//			if (res==JOptionPane.YES_OPTION)
//				if (!vAlbaran.gettNumpedido().getText().equals(""))
//					modificaPedido();
//				else
//					nuevoAlbaran();
//			else
//				vAlbaran.dispose();
	}
	
//	private void modificaPedido() {
//		PedidoProveedor pedModif=new PedidoProveedor();
//		pedModif.setNum(Integer.valueOf(vAlbaran.gettNumpedido().getText()));
//		System.out.println("numero de pedido para modificar "+pedModif.getNum());
//		asignaCampos(pedModif);
//		ponFilas(pedModif);
//		int ok=gap.modificarPedido(pedModif);
//		if (ok==0) {
////			Component[] componentes =vpedidoProveedor.getV().getPanelInterior().getComponents();
////			for(Component componente:componentes)
////				(JInternalFrame) componente.up
//			ControladorPedidosProveedores cpp = new ControladorPedidosProveedores(vAlbaran.getVpedidos());
//			cpp.listar(vAlbaran.getVpedidos());
//			vAlbaran.dispose();
//			
//		}
//		//else 
//			//muestraErrores(ok);		
//	}
	
	private void nuevoAlbaran() {
//		AlbaranProveedor albaranNuevo=new AlbaranProveedor();
//		asignaCampos(albaranNuevo);
//		int ok=gap.nuevoPedido(albaranNuevo);
//		ponFilas(albaranNuevo);
//		gap.modificarPedido(albaranNuevo);
//		if (ok==0)
//			vAlbaran.dispose();
//		
	}
	
	

	private void asignaCampos(AlbaranProveedor albModif) {
//		FilaPedidoProveedor filaModif;
//		albModif.setFecha(vAlbaran.getcFecha().getDate());
//		System.out.println("fecha "+vAlbaran.getcFecha().getDate());
//		albModif.setProveedore((Proveedor) vAlbaran.getComboProveedor().getSelectedItem());
//		albModif.setConfirmado(vAlbaran.getChecAlmacen().isSelected());
//		albModif.setEnviado(vAlbaran.getChecEnviado().isSelected());	
//		if (albModif.getEnviado())
//			new PdfPedidoProveedor(albModif);
//		vAlbaran.getPanel().updateUI();
		
	}

	private void ponFilas(PedidoProveedor pedModif) {
//		FilaPedidoProveedor filaModif;
//		Component[] componentes=vAlbaran.getPanel().getComponents();
//		System.out.println("pasa");	
//		filasAlb=new ArrayList<FilaPedidoProveedor>();
//		for (Component fila:componentes) {
//			filaModif=new FilaPedidoProveedor();
//			VFilaPedidoProveedor fil=(VFilaPedidoProveedor) fila;
//			fil.updateUI();
//			if (fil.getFila()!=null)
//				asignaCamposFila(fil,filaModif,pedModif);
//			filasAlb.add(filaModif);
//		}
//		pedModif.setFilaPedidoProveedor(filasAlb);
//		for (FilaPedidoProveedor fila:pedModif.getFilaPedidoProveedor())
//			System.out.println("nueva cantidad "+fila.getCantidad());
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
		System.out.println("cantidad nueva "+fila.gettUnidades().getText()+" cantidad antigua "+filaModif.getCantidad());
		filaModif.setCantidad(Integer.parseInt(fila.gettUnidades().getText()));
	}
	
//	public List<FilaPedidoProveedor> articulosPendientesPedido(PedidoProveedor pedido){
////		return gap.listaFilasPedido(pedido);
//	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
//		if (arg0.getSource()==vAlbaran.getComboProveedor().getEditor().getEditorComponent() && vAlbaran.getComboProveedor().getSelectedItem()!=null) {
//			vAlbaran.getPanel().updateUI();
//			PedidoProveedor pp=new PedidoProveedor();
//			pp.setProveedore((Proveedor) vAlbaran.getComboProveedor().getSelectedItem());
//			vAlbaran.setPed(pp);
//		}
		
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
		if (e.getSource()==vAlbaran.getbNuevaFila())
			vAlbaran.nuevaFila();
		
	}
	
}
