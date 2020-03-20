package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;

import model.Proveedor;
import modelo.negocio.GestorProveedor;
import vista.FichaProveedor;
import vista.ListadoProveedores;

public class ControladorListadoProveedores implements ActionListener{
	private ListadoProveedores listado;
	
	public ControladorListadoProveedores(ListadoProveedores listado) {
		listar(listado);		
	}

	public void listar(ListadoProveedores listado) {
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
		GestorProveedor ga=new GestorProveedor();
		FichaProveedor fp=new FichaProveedor(null);
		ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
		fp.EstablecerManejadorVentana(cfp);
		listado.getV().getPanelInterior().add(fp);
		fp.setVisible(true);
	}
}
