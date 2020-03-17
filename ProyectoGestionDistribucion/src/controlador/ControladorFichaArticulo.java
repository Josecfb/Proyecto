package controlador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import model.Articulo;
import model.Proveedor;
import modelo.negocio.GestorArticulo;
import vista.FichaArticulo;

public class ControladorFichaArticulo implements InternalFrameListener{
	private FichaArticulo fichaArticulo;
	
	public ControladorFichaArticulo(FichaArticulo fichaArticulo) {
		this.fichaArticulo=fichaArticulo;
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		Articulo artModif=new Articulo();
		artModif.setCod(Integer.valueOf(fichaArticulo.gettCodigo().getText()));
		artModif.setCodpro(fichaArticulo.gettCProv().getText());
		artModif.setNombre(fichaArticulo.gettNombre().getText());
		artModif.setCoste(euroADoble(fichaArticulo.gettCoste().getText()));
		artModif.setPrecioMayorista(euroADoble(fichaArticulo.gettPrecioMay().getText()));
		artModif.setPrecioMinorista(euroADoble(fichaArticulo.gettPrecioMin().getText()));
		artModif.setIva(porcentajeADoble(fichaArticulo.gettIva().getText()));
		artModif.setUnidadesCaja(Integer.valueOf(fichaArticulo.gettUnidadesCaja().getText()));
		artModif.setProveedorBean((Proveedor) fichaArticulo.getComboProveedor().getSelectedItem());
		artModif.setStock(Integer.valueOf(fichaArticulo.gettStock().getText()));
		artModif.setStockMinimo(Integer.valueOf(fichaArticulo.gettStockMin().getText()));
		GestorArticulo ga=new GestorArticulo();
		boolean[] ok=new boolean[6];
		ok=ga.modificarArticulo(artModif);
		if (ok[5])
			fichaArticulo.dispose();
		else {
			System.out.println("error");
			if (!ok[0])
				JOptionPane.showMessageDialog(new JFrame(),"Nombre vacío","error",JOptionPane.ERROR_MESSAGE);
			if (!ok[1])
				JOptionPane.showMessageDialog(new JFrame(),"Nif vacío","error",JOptionPane.ERROR_MESSAGE);
			if (!ok[2])
				JOptionPane.showMessageDialog(new JFrame(),"Email vacío","error",JOptionPane.ERROR_MESSAGE);
		}
		
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
	public Double euroADoble(String cad) {
		return Double.valueOf(cad.split(" ")[0].split(",")[0]+"."+cad.split(" ")[0].split(",")[1]);
	}
	public Double porcentajeADoble(String cad) {
		return Double.valueOf(cad.split("%")[0])/100;
	}

}
