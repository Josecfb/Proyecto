package controlador.clientes.albaranes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.AlbaranCliente;
import model.Cliente;
import model.PedidoCliente;
import model.PedidoProveedor;
import model.Proveedor;
import modelo.negocio.GestorAlbaranCliente;
import modelo.negocio.GestorPedidosCliente;
import modelo.negocio.GestorPedidosProve;
import vista.clientes.albaranes.VAlbaranCliente;
import vista.clientes.albaranes.VAlbaranesClientes;
import vista.clientes.albaranes.VFilaPedGeneraAlbCliente;
import vista.clientes.albaranes.VGeneraAlbaranCliente;

public class CtrlGenAlbCli implements ActionListener, FocusListener{
	private VGeneraAlbaranCliente vGenAlvCli; 
	private VAlbaranesClientes vAlbsCli;

	
	public CtrlGenAlbCli(VGeneraAlbaranCliente vGenAlvCli,VAlbaranesClientes vAlbsCli) {
		this.vGenAlvCli=vGenAlvCli;
		this.vAlbsCli=vAlbsCli;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenAlvCli.getComboCliente().getEditor().getEditorComponent()) {
			GestorPedidosProve gpp=new GestorPedidosProve();
			List<PedidoProveedor> listaPed =gpp.listaEnviados(((Proveedor) vGenAlvCli.getComboCliente().getSelectedItem()));
			vGenAlvCli.muestraPedidos(listaPed);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenAlvCli.getbSiguiente()) {
			vGenAlvCli.getComboCliente().setVisible(false);
			vGenAlvCli.getScrollPane().setVisible(true);
			vGenAlvCli.getlProve().setVisible(false);
			vGenAlvCli.getbSiguiente().setVisible(false);
			vGenAlvCli.getLinea1().setText("Selecione los pedidos de los cuales procede el albarán");
			vGenAlvCli.getLinea2().setText("y pulse el botón Aceptar. su Albarán quedará generado");
			vGenAlvCli.getbAceptar().setVisible(true);
		}
		
		if (e.getSource()==vGenAlvCli.getbCancelar()) {
			vGenAlvCli.dispose();
		}
		
		if (e.getSource()==vGenAlvCli.getbAceptar()) {
			GestorPedidosCliente gpc=new GestorPedidosCliente();
			GestorAlbaranCliente gac=new GestorAlbaranCliente();
			AlbaranCliente alb=new AlbaranCliente();
			alb.setClienteBean((Cliente) vGenAlvCli.getComboCliente().getSelectedItem());
			alb.setFecha(new Date());
	
			Component[] compnentes;
			compnentes = vGenAlvCli.getPanelFila().getComponents();
			List<PedidoCliente> pedidos=new ArrayList<PedidoCliente>();
			for (Component compo:compnentes) {
				VFilaPedGeneraAlbCliente vfila;
				vfila=(VFilaPedGeneraAlbCliente) compo;
				if (vfila.getChecMarca().isSelected()) {	
					PedidoCliente ped=gpc.existe(Integer.parseInt(vfila.getlNum().getText()));
					if (!ped.getConfirmado())
						pedidos.add(ped);
				}
			}
			alb.setPedidosClientes(pedidos);
			
			gac.nuevoAlbaran(alb);
			for (PedidoCliente ped:alb.getPedidosClientes()) {
				ped.setAlbaranCliente(alb);
				ped.setConfirmado(true);
				gpc.modificarPedido(ped);
			}
			alb.setFilasAlbaranClientes(gac.generaFilas(alb));
			gac.modificaAlbaranGenerado(alb);
			VAlbaranCliente vAlb=new VAlbaranCliente(alb,vAlbsCli);
			ControladorAlbaranCliente cap=new ControladorAlbaranCliente(vAlb);
			vAlb.establecerControlador(cap);
			vGenAlvCli.getV().getPanelInterior().add(vAlb);
			vAlb.setVisible(true);
			vGenAlvCli.dispose();
		}
		
	}

	public VAlbaranesClientes getvAlbsPro() {
		return vAlbsCli;
	}
	
	
	

}
