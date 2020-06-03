package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.articulos.ControladorListadoArticulos;
import controlador.clientes.ControladorListadoClientes;
import controlador.config.ControladorFichaConfig;
import controlador.proveedores.ControladorListadoProveedores;
import modelo.persistencia.DaoDatosEmpresa;
import vista.VentanaPrincipal;
import vista.articulos.VListadoArticulos;
import vista.clientes.VListadoClientes;
import vista.empresa.VFichaEmpresa;
import vista.proveedores.VListadoProveedores;

/**
 * Controla las acciones sobre los botones de la ventana principal
 * @author Jose Carlos
 *
 */
public class ControladorPrincipal implements ActionListener{
	/**
	 * 
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * El constructor recibe como parámetro la ventana principal
	 * @param ventanaPrincipal VentanaPrincipal
	 */
	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
	}
	
	@Override
	/**
	 * Decide las acciones de los cuatro botones de la ventana principal
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ventanaPrincipal.getListarProveedores() || e.getSource()==ventanaPrincipal.getbProveedores()) 
			listadoProveedores();
		if (e.getSource()==ventanaPrincipal.getListarClientes() || e.getSource()==ventanaPrincipal.getbClientes())
			listadoClientes();
		if (e.getSource()==ventanaPrincipal.getListarArticulos() || e.getSource()==ventanaPrincipal.getbArticulos())
			listadoArticulos();
		if (e.getSource()==ventanaPrincipal.getbConfiguracion())
			configuracion();
	}
	/**
	 * Abre la ventana de listado de proveedores y le asigna su controlador
	 */
	private void listadoProveedores() {
		VListadoProveedores lp=new VListadoProveedores(ventanaPrincipal);
		ControladorListadoProveedores controla =new ControladorListadoProveedores(lp);
		lp.establecerControlador(controla);
		ventanaPrincipal.getPanelInterior().add(lp);
		lp.setVisible(true);
	}
	/**
	 * Abre la ventana de listado de clientes y le asigna su controlador
	 */
	private void listadoClientes() {
		VListadoClientes lc=new VListadoClientes(ventanaPrincipal);
		ControladorListadoClientes controlacli=new ControladorListadoClientes(lc);
		lc.establecerControlador(controlacli);
		ventanaPrincipal.getPanelInterior().add(lc);
		lc.setVisible(true);
	}
	/**
	 * Abre la ventana de listado de artículos y le asigna su controlador
	 */
	private void listadoArticulos() {
		VListadoArticulos la=new VListadoArticulos(ventanaPrincipal);
		ControladorListadoArticulos controlala=new ControladorListadoArticulos(la);
		la.establecerControlador(controlala);
		ventanaPrincipal.getPanelInterior().add(la);
		la.setVisible(true);
	}
	/**
	 * Abre la ventana de confuguración donde se introducen los datos de la empresa
	 */
	private void configuracion() {
		DaoDatosEmpresa dde =new DaoDatosEmpresa();
		VFichaEmpresa vFichaEmp=new VFichaEmpresa(dde.empresa());
		ControladorFichaConfig cfc=new ControladorFichaConfig(vFichaEmp);
		vFichaEmp.EstablecerManejadorVentana(cfc);
		ventanaPrincipal.getPanelInterior().add(vFichaEmp);
		vFichaEmp.setVisible(true);
	}
	
}
