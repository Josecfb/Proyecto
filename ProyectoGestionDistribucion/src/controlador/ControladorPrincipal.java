package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.articulos.ControladorListadoArticulos;
import controlador.clientes.ControladorListadoClientes;
import controlador.proveedores.ControladorListadoProveedores;
import modelo.persistencia.DaoDatosEmpresa;
import vista.VentanaPrincipal;
import vista.articulos.VListadoArticulos;
import vista.clientes.VListadoClientes;
import vista.empresa.VFichaEmpresa;
import vista.proveedores.VListadoProveedores;

/**
 * 
 * @author Jose Carlos
 *
 */
public class ControladorPrincipal implements ActionListener{
	/**
	 * 
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * 
	 * @param ventanaPrincipal
	 */
	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
	}
	
	@Override
	/**
	 * 
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
	
	private void listadoProveedores() {
		VListadoProveedores lp=new VListadoProveedores(ventanaPrincipal);
		ControladorListadoProveedores controla =new ControladorListadoProveedores(lp);
		lp.establecerControlador(controla);
		ventanaPrincipal.getPanelInterior().add(lp);
		lp.setVisible(true);
	}
	
	private void listadoClientes() {
		VListadoClientes lc=new VListadoClientes(ventanaPrincipal);
		ControladorListadoClientes controlacli=new ControladorListadoClientes(lc);
		lc.establecerControlador(controlacli);
		ventanaPrincipal.getPanelInterior().add(lc);
		lc.setVisible(true);
	}
	
	private void listadoArticulos() {
		VListadoArticulos la=new VListadoArticulos(ventanaPrincipal);
		ControladorListadoArticulos controlala=new ControladorListadoArticulos(la);
		la.establecerControlador(controlala);
		ventanaPrincipal.getPanelInterior().add(la);
		la.setVisible(true);
	}
	
	private void configuracion() {
		DaoDatosEmpresa dde =new DaoDatosEmpresa();
		VFichaEmpresa vFichaEmp=new VFichaEmpresa(dde.empresa());
		ventanaPrincipal.getPanelInterior().add(vFichaEmp);
		vFichaEmp.setVisible(true);
	}
	
}
