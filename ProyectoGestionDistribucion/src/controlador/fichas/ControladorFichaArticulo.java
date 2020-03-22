package controlador.fichas;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import model.Articulo;
import model.Familia;
import model.Proveedor;
import modelo.negocio.GestorArticulo;
import vista.fichas.FichaArticulo;

public class ControladorFichaArticulo implements InternalFrameListener, FocusListener {
	private FichaArticulo fichaArticulo;
	
	public ControladorFichaArticulo(FichaArticulo fichaArticulo) {
		this.fichaArticulo=fichaArticulo;
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		int res=JOptionPane.showConfirmDialog(new JFrame(), "¿Desea guardar?");
		if (res==JOptionPane.YES_OPTION)
			if (fichaArticulo.getArt()!=null)
				modificaArticulo();
			else
				nuevoArticulo();
		else
			fichaArticulo.dispose();
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource()==fichaArticulo.gettPrecioMay())
			fichaArticulo.gettPrecioMay().setText(focoEuro(fichaArticulo.gettPrecioMay().getText()));
		if (e.getSource()==fichaArticulo.gettPrecioMin())
			fichaArticulo.gettPrecioMin().setText(focoEuro(fichaArticulo.gettPrecioMin().getText()));	
		if (e.getSource()==fichaArticulo.gettCoste())
			fichaArticulo.gettCoste().setText(focoEuro(fichaArticulo.gettCoste().getText()));
		if (e.getSource()==fichaArticulo.gettIva())
			fichaArticulo.gettIva().setText(focoPorcentaje(fichaArticulo.gettIva().getText()));
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource()==fichaArticulo.gettPrecioMay())
			fichaArticulo.gettPrecioMay().setText(noFocoEuro(fichaArticulo.gettPrecioMay().getText()));
		if (e.getSource()==fichaArticulo.gettPrecioMin())
			fichaArticulo.gettPrecioMin().setText(noFocoEuro(fichaArticulo.gettPrecioMin().getText()));
		if (e.getSource()==fichaArticulo.gettCoste())
			fichaArticulo.gettCoste().setText(noFocoEuro(fichaArticulo.gettCoste().getText()));
		if (e.getSource()==fichaArticulo.gettIva())
			fichaArticulo.gettIva().setText(fichaArticulo.gettIva().getText()+"%");
	}
	
	private void modificaArticulo() {
		Articulo artModif=new Articulo();
		artModif.setCod(Integer.valueOf(fichaArticulo.gettCodigo().getText()));
		asignaCampos(artModif);
		GestorArticulo ga=new GestorArticulo();
		boolean[] ok=new boolean[6];
		ok=ga.modificarArticulo(artModif);
		if (ok[5])
			fichaArticulo.dispose();
		else 
			muestraErrores(ok);		
	}
	
	private void nuevoArticulo() {
		Articulo artNuevo=new Articulo();
		asignaCampos(artNuevo);
		GestorArticulo ga=new GestorArticulo();
		boolean[] ok=new boolean[6];
		ok=ga.nuevoArticulo(artNuevo);
		if (ok[5])
			fichaArticulo.dispose();
		else {
			muestraErrores(ok);
		}
	}

	private void asignaCampos(Articulo artModif) {
		artModif.setCodpro(fichaArticulo.gettCProv().getText());
		artModif.setNombre(fichaArticulo.gettNombre().getText());
		artModif.setCoste(euroADoble(fichaArticulo.gettCoste().getText()));
		artModif.setPrecioMayorista(euroADoble(fichaArticulo.gettPrecioMay().getText()));
		artModif.setPrecioMinorista(euroADoble(fichaArticulo.gettPrecioMin().getText()));
		artModif.setIva(porcentajeADoble(fichaArticulo.gettIva().getText()));
		artModif.setUnidadesCaja(Integer.valueOf(fichaArticulo.gettUnidadesCaja().getText()));
		artModif.setProveedorBean((Proveedor) fichaArticulo.getComboProveedor().getSelectedItem());
		artModif.setFamiliaBean((Familia) fichaArticulo.getComboFamilia().getSelectedItem());
		artModif.setStock(Integer.valueOf(fichaArticulo.gettStock().getText()));
		artModif.setStockMinimo(Integer.valueOf(fichaArticulo.gettStockMin().getText()));
	}

	private void muestraErrores(boolean[] ok) {
		if (!ok[0])
			JOptionPane.showMessageDialog(new JFrame(),"Nombre vacío","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Coste menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Precio mayorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[3])
			JOptionPane.showMessageDialog(new JFrame(),"Precio minorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[4])
			JOptionPane.showMessageDialog(new JFrame(),"Artículo sin proveedor","error",JOptionPane.ERROR_MESSAGE);
	}


	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	
	public String focoEuro(String cad) {
		return cad.split(" ")[0];
	}
	
	public String focoPorcentaje(String cad) {
		return cad.split("%")[0];
	}
	
	public String noFocoEuro(String cad) {
		if (!cad.contains(",")) cad+=",00";
		return cad+" €";
	}
	
	public Double porcentajeADoble(String cad) {
		return Double.valueOf(cad.split("%")[0])/100;
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}

}
