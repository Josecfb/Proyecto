package controlador.proveedores.facturas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.AlbaranProveedor;
import entidades.FacturaProveedor;
import entidades.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import modelo.negocio.GestorFacturaProve;
import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.facturas.VFilaAlbGeneraFactProve;
import vista.proveedores.facturas.VGeneraFacturaProve;
/**
 * Controla el asistente para generar facturas de proveedor
 * @author Jose Carlos
 *
 */
public class CtrlGenFactProv implements ActionListener, FocusListener{
	private VGeneraFacturaProve vGenFactPro; 
	private VFacturasProveedores vFactsPro;

	/**
	 * El constructor recibe dos argumentos
	 * @param vGenFactPro Ventana del asistente para generar facturas de proveedor VGeneraFacturaProve
	 * @param vFactsPro ventana del listado de facturas de proveedor VFacturasProveedores
	 */
	public CtrlGenFactProv(VGeneraFacturaProve vGenFactPro,VFacturasProveedores vFactsPro) {
		this.vGenFactPro=vGenFactPro;
		this.vFactsPro=vFactsPro;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Cuando el combobox de proveedor pierde el foco genera el listado de albaranes sin facturar de ese proveedor
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenFactPro.getComboProve().getEditor().getEditorComponent()) {
			GestorAlbaranProve gap=new GestorAlbaranProve();
			List<AlbaranProveedor> listaAlb =gap.listaAlbaranesAlmacen(((Proveedor) vGenFactPro.getComboProve().getSelectedItem()));
			vGenFactPro.muestraAlbaranes(listaAlb);
		}
	}
	/**
	 * Cuando se pulsa el bot�n de siguiente se oculta el combobox de proveedor, y muestra la lista de albaranes sin facturar y el bot�n aceptar
	 * Si se pulsa cancelar se cierra la ventana del asistente
	 * Si se pulsa Aceptar genera la factura  con los albaranes seleccionados
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenFactPro.getbSiguiente()) {
			vGenFactPro.getComboProve().setVisible(false);
			vGenFactPro.getScrollPane().setVisible(true);
			vGenFactPro.getlProve().setVisible(false);
			vGenFactPro.getbSiguiente().setVisible(false);
			vGenFactPro.getLinea1().setText("Selecione los albaranes de los cuales procede la factura");
			vGenFactPro.getLinea2().setText("y pulse el bot�n Aceptar. su factura quedar� generada");
			vGenFactPro.getbAceptar().setVisible(true);
		}
		
		if (e.getSource()==vGenFactPro.getbCancelar()) {
			vGenFactPro.dispose();
		}
		
		if (e.getSource()==vGenFactPro.getbAceptar()) {
			GestorAlbaranProve gap=new GestorAlbaranProve();
			GestorFacturaProve gfp=new GestorFacturaProve();
			FacturaProveedor fac=new FacturaProveedor();
			fac.setProveedore((Proveedor) vGenFactPro.getComboProve().getSelectedItem());
			fac.setFecha(new Date());
	
			Component[] componentes;
			componentes = vGenFactPro.getPanelFila().getComponents();
			List<AlbaranProveedor> albaranes=new ArrayList<AlbaranProveedor>();
			for (Component compo:componentes) {
				VFilaAlbGeneraFactProve vfila;
				vfila=(VFilaAlbGeneraFactProve) compo;
				if (vfila.getChecMarca().isSelected()) {	
					AlbaranProveedor alb=gap.existe(Integer.parseInt(vfila.getlNum().getText()));
					if (!alb.isFacturado())
						albaranes.add(alb);
				}
			}
			fac.setAlbaranesProveedors(albaranes);
			
			gfp.nuevaFactura(fac);
			for (AlbaranProveedor alb:fac.getAlbaranesProveedors()) {
				alb.setFacturasProveedor(fac);
				alb.setFacturado(true);
				gap.facturaAlbaran(alb); 
				
			}
			fac.setFilasFacturasProveedors(gfp.generaFilas(fac));
			gfp.modificaFacturaGenerada(fac);
			VFacturaProveedor vFact=new VFacturaProveedor(fac,vFactsPro);
			ControladorFacturaProveedor cfp=new ControladorFacturaProveedor(vFact);
			vFact.establecerControlador(cfp);
			vGenFactPro.getV().getPanelInterior().add(vFact);
			vFact.setVisible(true);
			vGenFactPro.dispose();
		}
		
	}

	public VFacturasProveedores getvFactsPro() {
		return vFactsPro;
	}
	
	
	

}
