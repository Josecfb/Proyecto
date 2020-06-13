package controlador.proveedores.albaranes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.AlbaranProveedor;
import entidades.PedidoProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import modelo.negocio.GestorPedidosProve;
import vista.proveedores.albaranes.VAlbaranProveedor;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.albaranes.VFilaPedGeneraAlbProve;
import vista.proveedores.albaranes.VGeneraAlbaranProve;
/**
 * Controla la ventana del asistente para generar albaranes a partir de los pedidos a proveedores
 * @author Jose Carlos
 *
 */
public class CtrlGenAlbProv implements ActionListener, FocusListener{
	private VGeneraAlbaranProve vGenAlvPro; 
	private VAlbaranesProveedores vAlbsPro;

	/**
	 * El constructor recibe la ventana del asistente para generar albaranes y la ventana del listado de albaranes 
	 * @param vGenAlvPro Vista de la ventana del asistente para generar albaranes vGenAlvPro
	 * @param vAlbsPro Vista de la ventana del listado de albaranes VAlbaranesProveedores
	 */
	public CtrlGenAlbProv(VGeneraAlbaranProve vGenAlvPro,VAlbaranesProveedores vAlbsPro) {
		this.vGenAlvPro=vGenAlvPro;
		this.vAlbsPro=vAlbsPro;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Cuando el cuadro combinado de proveedor pierde el foco genera la lista de pedidos sin albar�n de ese proveedor
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenAlvPro.getComboProve().getEditor().getEditorComponent()) {
			GestorPedidosProve gpp=new GestorPedidosProve();
			List<PedidoProveedor> listaPed =gpp.listaEnviados(((Proveedor) vGenAlvPro.getComboProve().getSelectedItem()));
			vGenAlvPro.muestraPedidos(listaPed);
		}
	}
	/**
	 * Cuando se pulsa el bot�n de siguiente se oculta el combobox de proveedor, y muestra la lista de pedidos sin albar�n y el bot�n aceptar
	 * Si se pulsa cancelar se cierra la ventana del asistente
	 * Si se pulsa Aceptar genera el albar�n con los pedidos seleccionados
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenAlvPro.getbSiguiente()) {
			vGenAlvPro.getComboProve().setVisible(false);
			vGenAlvPro.getScrollPane().setVisible(true);
			vGenAlvPro.getlProve().setVisible(false);
			vGenAlvPro.getbSiguiente().setVisible(false);
			vGenAlvPro.getLinea1().setText("Selecione los pedidos de los cuales procede el albar�n");
			vGenAlvPro.getLinea2().setText("y pulse el bot�n Aceptar. su Albar�n quedar� generado");
			vGenAlvPro.getbAceptar().setVisible(true);
		}
		
		if (e.getSource()==vGenAlvPro.getbCancelar()) {
			vGenAlvPro.dispose();
		}
		
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
			VAlbaranProveedor vAlb=new VAlbaranProveedor(alb,vAlbsPro);
			ControladorAlbaranProveedor cap=new ControladorAlbaranProveedor(vAlb);
			vAlb.establecerControlador(cap);
			vAlb.setModificado(true);
			vGenAlvPro.getV().getPanelInterior().add(vAlb);
			vAlb.setVisible(true);
			vGenAlvPro.dispose();
		}
	}

	public VAlbaranesProveedores getvAlbsPro() {
		return vAlbsPro;
	}
	
}
