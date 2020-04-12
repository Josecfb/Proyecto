package controlador.proveedores.facturas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.AlbaranProveedor;
import model.FacturaProveedor;
import model.Proveedor;
import modelo.negocio.GestorAlbaranProve;
import modelo.negocio.GestorFacturaProve;
import vista.proveedores.facturas.VFacturaProveedor;
import vista.proveedores.facturas.VFacturasProveedores;
import vista.proveedores.facturas.VFilaAlbGeneraFactProve;
import vista.proveedores.facturas.VGeneraFacturaProve;

public class CtrlGenFactProv implements ActionListener, FocusListener{
	private VGeneraFacturaProve vGenFactPro; 
	private VFacturasProveedores vFactsPro;

	
	public CtrlGenFactProv(VGeneraFacturaProve vGenFactPro,VFacturasProveedores vFactsPro) {
		this.vGenFactPro=vGenFactPro;
		this.vFactsPro=vFactsPro;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource()==vGenFactPro.getComboProve().getEditor().getEditorComponent()) {
			System.out.println("Muestra los pedidos de "+((Proveedor) vGenFactPro.getComboProve().getSelectedItem()).getNombre());
			GestorAlbaranProve gap=new GestorAlbaranProve();
			List<AlbaranProveedor> listaAlb =gap.listaAlbaranesAlmacen(((Proveedor) vGenFactPro.getComboProve().getSelectedItem()));
			vGenFactPro.muestraAlbaranes(listaAlb);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==vGenFactPro.getbSiguiente()) {
			vGenFactPro.getComboProve().setVisible(false);
			vGenFactPro.getScrollPane().setVisible(true);
			vGenFactPro.getlProve().setVisible(false);
			vGenFactPro.getbSiguiente().setVisible(false);
			vGenFactPro.getLinea1().setText("Selecione los albaranes de los cuales procede la factura");
			vGenFactPro.getLinea2().setText("y pulse el botón Aceptar. su factura quedará generada");
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
				System.out.println("el albaran tiene filas= "+alb.getFilasAlbaranProveedors().size());
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
