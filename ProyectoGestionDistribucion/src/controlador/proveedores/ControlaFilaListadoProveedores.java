package controlador.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorProveedor;
import vista.proveedores.VFichaProveedor;
import vista.proveedores.VFilaListadoProveedores;

public class ControlaFilaListadoProveedores implements ActionListener{
	private VFilaListadoProveedores filaListadoProveedores;
	
	public ControlaFilaListadoProveedores(VFilaListadoProveedores filaListadoProveedores) {
		this.filaListadoProveedores=filaListadoProveedores;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hola");
		if(e.getSource()==filaListadoProveedores.getbEditar()) {
			System.out.println("hola"+filaListadoProveedores.getNombre().getText());
			GestorProveedor gp=new GestorProveedor();
			VFichaProveedor fp=new VFichaProveedor(gp.existe(Integer.valueOf(filaListadoProveedores.getNumero().getText())));
			ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
			fp.EstablecerManejadorVentana(cfp);
			filaListadoProveedores.getV().getPanelInterior().add(fp);
			fp.setVisible(true);
		}
	}

}
