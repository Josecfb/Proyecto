package controlador.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.negocio.GestorProveedor;
import vista.proveedores.VFichaProveedor;
import vista.proveedores.VFilaListadoProveedores;
/**
 * Controla las filas de la ventana del listado de proveedores
 * @author Jose Carlos
 *
 */
public class ControlaFilaListadoProveedores implements ActionListener{
	private VFilaListadoProveedores filaListadoProveedores;
	/**
	 * El constructor recibe la vista de la fila del listado de proveedores
	 * @param filaListadoProveedores vista de la fila del listado de proveedores VFilaListadoProveedores
	 */
	public ControlaFilaListadoProveedores(VFilaListadoProveedores filaListadoProveedores) {
		this.filaListadoProveedores=filaListadoProveedores;
	}
	/**
	 * Cuando se pulsa el boton editar abre la ficha del provvedor para modificarlo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==filaListadoProveedores.getbEditar()) {
			GestorProveedor gp=new GestorProveedor();
			VFichaProveedor fp=new VFichaProveedor(gp.existe(Integer.valueOf(filaListadoProveedores.getNumero().getText())),filaListadoProveedores.getVListProv());
			ControladorFichaProveedor cfp=new ControladorFichaProveedor(fp);
			fp.EstablecerManejadorVentana(cfp);
			filaListadoProveedores.getVListProv().getV().getPanelInterior().add(fp);
			fp.setVisible(true);
		}
	}

}
