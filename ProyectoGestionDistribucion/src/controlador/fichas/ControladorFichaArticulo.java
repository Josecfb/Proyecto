package controlador.fichas;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import model.Articulo;
import model.Familia;
import model.Proveedor;
import modelo.negocio.GestorArticulo;
import vista.articulos.VFichaArticulo;

public class ControladorFichaArticulo implements InternalFrameListener, FocusListener {
	private VFichaArticulo fichaArticulo;
	private GestorArticulo ga;
	
	
	public ControladorFichaArticulo(VFichaArticulo fichaArticulo) {
		this.fichaArticulo=fichaArticulo;
		ga=new GestorArticulo();
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

			int res=JOptionPane.showConfirmDialog(new JFrame(), "�Desea guardar?");
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
		if (e.getSource().getClass()==JTextField.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.selectAll();
			campo.setBackground(new Color(240,240,255));
		}

		if (e.getSource()==JComboBox.class) {
			System.out.println("combo");
			@SuppressWarnings("rawtypes")
			JComboBox jComboBox = (JComboBox) e.getSource();
			jComboBox.getEditor().getEditorComponent().setBackground(Color.BLUE);
		}
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
		if (e.getSource().getClass()==JTextField.class || e.getSource().getClass()==JComboBox.class) {
			JTextField campo=(JTextField) e.getSource();
			campo.setBackground(Color.WHITE);
		}
	}
	
	private void modificaArticulo() {
		Articulo artModif=new Articulo();
		artModif.setCod(Integer.valueOf(fichaArticulo.gettCodigo().getText()));
		asignaCampos(artModif);
		ga=new GestorArticulo();
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
			JOptionPane.showMessageDialog(new JFrame(),"Nombre vac�o","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[1])
			JOptionPane.showMessageDialog(new JFrame(),"Coste menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[2])
			JOptionPane.showMessageDialog(new JFrame(),"Precio mayorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[3])
			JOptionPane.showMessageDialog(new JFrame(),"Precio minorista menor que cero","error",JOptionPane.ERROR_MESSAGE);
		if (!ok[4])
			JOptionPane.showMessageDialog(new JFrame(),"Art�culo sin proveedor","error",JOptionPane.ERROR_MESSAGE);
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
		return cad+" �";
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
