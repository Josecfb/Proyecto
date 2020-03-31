package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;

import controlador.fichas.ControladorFichaCliente;
import model.Cliente;
import modelo.negocio.GestorCliente;
import vista.VListadoClientes;
import vista.fichas.VFichaCliente;

public class ControladorListadoClientes implements ActionListener{
	private VListadoClientes listado;

	
	public ControladorListadoClientes(VListadoClientes listado) {
		listar(listado);		
	}

	private void listar(VListadoClientes listado) {
		this.listado=listado;
		GestorCliente gc=new GestorCliente();
		List<Cliente> filas=gc.listar(listado.getTFiltroNombre().getText());
		listado.muestra(filas);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==listado.getbFiltrar()) 
			filtrar();
		if (e.getSource()==listado.getbFiltros()) 
			muestraFiltros();
		if (e.getSource()==listado.getbNuevo())
			nuevoCliente();
		if (e.getSource()==listado.getbActualizar())
			actualizar();
	}
	
	private void muestraFiltros() {
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

	private void filtrar() {
		listado.getPanel().updateUI();
		listar(listado);
		System.out.println("Boton filtro");
	}
	
	private void actualizar() {
		listado.getPanel().updateUI();
	}
	
	private void nuevoCliente() {
		System.out.println("nuevo cliente");
		GestorCliente gc=new GestorCliente();
		VFichaCliente fc=new VFichaCliente(null);
		ControladorFichaCliente cfc=new ControladorFichaCliente(fc);
		fc.establecerManejadorVentana(cfc);
		System.out.println("nuevo articulo");
		listado.getV().getPanelInterior().add(fc);
		fc.setVisible(true);
	}
	

}
