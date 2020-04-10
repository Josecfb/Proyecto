package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;

import controlador.fichas.ControladorAlbaranesProveedores;
import controlador.fichas.ControladorFacturasProveedores;
import controlador.fichas.ControladorFichaProveedor;
import controlador.fichas.ControladorPedidosProveedores;
import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.proveedores.VFichaProveedor;
import vista.proveedores.VListadoProveedores;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.pedidos.VPedidosProveedores;

public class ControladorListadoProveedores implements ActionListener{
	private VListadoProveedores listado;
	
	public ControladorListadoProveedores(VListadoProveedores listado) {
		listar(listado);		
	}

	public void listar(VListadoProveedores listado) {
		this.listado=listado;
		List<Proveedor> filas;
		GestorProveedor gp=new GestorProveedor();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		System.out.println(filas.size());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) 
			filtrar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoProveedor();
		if (e.getSource()==listado.getbActualizar())
			actualizar();
		if (e.getSource()==listado.getbPedidos())
			abrePedidos();
		if (e.getSource()==listado.getbAlbaranes())
			abreAlbaranes();
		if (e.getSource()==listado.getbFacturas())
			abreFacturas();
	}
	
	private void filtrar() {
		listado.getPanel().updateUI();
		listar(listado);
	}
	
	private void muestraFiltros() {
		System.out.println("filtros pro");
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
	private void actualizar() {
		listado.getPanel().updateUI();
	}
	
	private void nuevoProveedor() {
		VFichaProveedor fp=new VFichaProveedor(null);
		ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
		fp.EstablecerManejadorVentana(cfp);
		listado.getV().getPanelInterior().add(fp);
		fp.setVisible(true);
	}
	
	private void abrePedidos() {
		VPedidosProveedores pp=new VPedidosProveedores(listado.getV());
		ControladorPedidosProveedores cpp=new ControladorPedidosProveedores(pp);
		pp.establecerManejador(cpp);
		listado.getV().getPanelInterior().add(pp);
		pp.setVisible(true);
	}
	
	private void abreAlbaranes() {
		VAlbaranesProveedores vap=new VAlbaranesProveedores(listado.getV());
		ControladorAlbaranesProveedores cap=new ControladorAlbaranesProveedores(vap);
		vap.establecerManejador(cap);
		listado.getV().getPanelInterior().add(vap);
		vap.setVisible(true);
	}
	
	private void abreFacturas() {
		VFacturasProveedores vfp=new VFacturasProveedores(listado.getV());
		ControladorFacturasProveedores cfp=new ControladorFacturasProveedores(vfp);
		vfp.establecerManejador(cfp);
		listado.getV().getPanelInterior().add(vfp);
		vfp.setVisible(true);
	}
}
