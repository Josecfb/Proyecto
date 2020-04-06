package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.AlbaranProveedor;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import modelo.negocio.GestorPedidosProve;
import vista.fichas.VAlbaranProveedor;
import vista.fichas.VFilaPedGeneraAlbProve;
import vista.fichas.VGeneraAlbaranProve;

public class CtrlGenAlbProv implements ActionListener, FocusListener{
	private VGeneraAlbaranProve vGenAlvPro; 

	
	public CtrlGenAlbProv(VGeneraAlbaranProve vGenAlvPro) {
		this.vGenAlvPro=vGenAlvPro;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenAlvPro.getComboProve().getEditor().getEditorComponent()) {
			System.out.println("Muestra los pedidos de "+((Proveedor) vGenAlvPro.getComboProve().getSelectedItem()).getNombre());
			GestorPedidosProve gpp=new GestorPedidosProve();
			List<PedidoProveedor> listaPed =gpp.listaEnviados(((Proveedor) vGenAlvPro.getComboProve().getSelectedItem()));
			System.out.println("Numero de pedidos sin recibir="+listaPed.size());
			vGenAlvPro.muestraPedidos(listaPed);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenAlvPro.getbAceptar()) {
			GestorPedidosProve gpp=new GestorPedidosProve();
			GestorAlbaranProve gap=new GestorAlbaranProve();
			AlbaranProveedor alb=new AlbaranProveedor();
			alb.setProveedore((Proveedor) vGenAlvPro.getComboProve().getSelectedItem());
			alb.setFecha(new Date());
	
			Component[] compnentes;
			compnentes = vGenAlvPro.getPanelFila().getComponents();
			List<PedidoProveedor> pedidos=new ArrayList<PedidoProveedor>();
			for (Component compo:compnentes) {
				VFilaPedGeneraAlbProve vfila;
				vfila=(VFilaPedGeneraAlbProve) compo;
				if (vfila.getChecMarca().isSelected()) {	
					PedidoProveedor ped=gpp.existe(Integer.parseInt(vfila.getlNum().getText()));
					if (!ped.getConfirmado())
						pedidos.add(ped);
				}
			}
			alb.setPedidosProveedors(pedidos);
			
			gap.nuevoAlbaran(alb);
			for (PedidoProveedor ped:alb.getPedidosProveedors()) {
				ped.setAlbaranesProveedor(alb);
				ped.setConfirmado(true);
				gpp.modificarPedido(ped);
			}
			alb.setFilasAlbaranProveedors(gap.generaFilas(alb));
			gap.modificaAlbaranGenerado(alb);
			VAlbaranProveedor vAlb=new VAlbaranProveedor(alb);
			vGenAlvPro.getV().getPanelInterior().add(vAlb);
			vAlb.setVisible(true);
		}
		
	}
	
	

}
