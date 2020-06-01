package controlador.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;

import controlador.proveedores.albaranes.ControladorAlbaranesProveedores;
import controlador.proveedores.facturas.ControladorFacturasProveedores;
import controlador.proveedores.pedidos.ControladorPedidosProveedores;
import entidades.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.proveedores.VFichaProveedor;
import vista.proveedores.VListadoProveedores;
import vista.proveedores.albaranes.VAlbaranesProveedores;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.pedidos.VPedidosProveedores;
/**
 * Controla la ventana de listado de proveedores
 * @author Jose Carlos
 *
 */
public class ControladorListadoProveedores implements ActionListener{
	private VListadoProveedores listado;
	/**
	 * El constructor recibe como argumento la ventana del listado de proveedores
	 * @param listado Vista de la ventana del listado de proveedores VListadoProveedores
	 */
	public ControladorListadoProveedores(VListadoProveedores listado) {
		this.listado=listado;
		listar();		
	}
	/**
	 * Obtiene la lista de los proveedores, y llama al método muestra(filas de la ventana del listado de proveedores
	 */
	public void listar() {
		List<Proveedor> filas;
		GestorProveedor gp=new GestorProveedor();
		filas=gp.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	/**
	 * Controla el comportamiento de los botones de la ventana del listado de proveedores
	 * Botones de listar, filtro nuevo proveedor, pedidos, albaranes y facturas
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) 
			listar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoProveedor();
		if (e.getSource()==listado.getbActualizar())
			listar();
		if (e.getSource()==listado.getbPedidos())
			abrePedidos();
		if (e.getSource()==listado.getbAlbaranes())
			abreAlbaranes();
		if (e.getSource()==listado.getbFacturas())
			abreFacturas();
	}
	

	/**
	 * Activa y desactiva el filtro por nombre
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
	 * Abre la ventana de la ficha de proveedor para crear uno nuevo
	 */
	private void nuevoProveedor() {
		VFichaProveedor fp=new VFichaProveedor(null,listado);
		ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
		fp.EstablecerManejadorVentana(cfp);
		listado.getV().getPanelInterior().add(fp);
		fp.setVisible(true);
	}
	/**
	 * Abre la ventana de la lista de pedidos de proveedores
	 */
	private void abrePedidos() {
		VPedidosProveedores pp=new VPedidosProveedores(listado.getV());
		ControladorPedidosProveedores cpp=new ControladorPedidosProveedores(pp);
		pp.establecerManejador(cpp);
		listado.getV().getPanelInterior().add(pp);
		pp.setVisible(true);
	}
	/**
	 * Abre la ventana de albaranes de proveedores
	 */
	private void abreAlbaranes() {
		VAlbaranesProveedores vap=new VAlbaranesProveedores(listado.getV());
		ControladorAlbaranesProveedores cap=new ControladorAlbaranesProveedores(vap);
		vap.establecerManejador(cap);
		listado.getV().getPanelInterior().add(vap);
		vap.setVisible(true);
	}
	/**
	 * Abre la ventana de facturas de proveedores
	 */
	private void abreFacturas() {
		VFacturasProveedores vfp=new VFacturasProveedores(listado.getV());
		ControladorFacturasProveedores cfp=new ControladorFacturasProveedores(vfp);
		vfp.establecerManejador(cfp);
		listado.getV().getPanelInterior().add(vfp);
		vfp.setVisible(true);
	}
}
