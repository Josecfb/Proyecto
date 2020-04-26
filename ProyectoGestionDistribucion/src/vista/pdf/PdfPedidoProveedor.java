package vista.pdf;

import model.Datosempresa;
import model.FilaPedidoProveedor;
import model.PedidoProveedor;
import modelo.negocio.GestorPedidosProve;
import modelo.persistencia.DaoDatosEmpresa;
import vista.correo.EnviarCorreo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//import controlador.fichas.ControladorPedidosProveedores;

public class PdfPedidoProveedor {
	private PedidoProveedor ped;

	
	public PdfPedidoProveedor(PedidoProveedor ped) {
		this.ped=ped;
		GestorPedidosProve gpp=new GestorPedidosProve();
		ped=gpp.existe(ped.getNum());
		Document pedido=new Document();
		DaoDatosEmpresa de=new DaoDatosEmpresa();
		Datosempresa empresa=de.empresa();
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String archivo="src/pedidospdf/"+formatoFecha.format(ped.getFecha())+"_"+ped.getProveedore().getNombre()+".pdf";
		try {
			FileOutputStream ficheroPdf = new FileOutputStream(archivo);
			PdfWriter.getInstance(pedido,ficheroPdf).setInitialLeading(20);
			pedido.open();
			//Font fuente = new Font(FontFamily.valueOf("arial"), 14);

			pedido.add(new Paragraph("NIF: "+empresa.getNif(), FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph("Empresa: "+empresa.getNombre(), FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph("Teléfono: "+empresa.getFijo(), FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph("Fecha: "+new SimpleDateFormat("dd/MM/yyyy").format(ped.getFecha()), FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph("Proveedor: "+ped.getProveedore().getNombre(), FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph(" ",FontFactory.getFont("arial",12)));
			pedido.add(new Paragraph("Pedido",FontFactory.getFont("arial",32)));
			pedido.add(new Paragraph(" ",FontFactory.getFont("arial",10)));
			
			
		           
            
			PdfPTable tabla = new PdfPTable(3);
			
			float[] anchoCols = new float[]{20f, 130f, 20f};
			tabla.setWidths(anchoCols);

			PdfPCell[] celda=new PdfPCell[3];
			for (int i=0;i<celda.length;i++)
				celda[i]=new PdfPCell();
			celda[0].setPhrase(new Phrase("Código"));

			celda[1].setPhrase(new Phrase("Nombre del Artículo"));
			
			celda[2].setPhrase(new Phrase("Cajas"));
			
			for (int i=0;i<celda.length;i++) {
				celda[i].setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				celda[i].setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				tabla.addCell(celda[i]);
			}
			
			List<FilaPedidoProveedor> filas=new ArrayList<FilaPedidoProveedor>();
			filas=ped.getFilaPedidoProveedor();
			for (int i=0;i<celda.length;i++) 
				celda[i].setBackgroundColor(BaseColor.WHITE);
			celda[0].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[1].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celda[2].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			for (FilaPedidoProveedor fila:filas) {
				celda[0].setPhrase(new Phrase(String.valueOf(fila.getArticuloBean().getCodpro())));
				celda[1].setPhrase(new Phrase(fila.getArticuloBean().getNombre().toLowerCase()));
				celda[2].setPhrase(new Phrase(String.valueOf(fila.getCantidad()/fila.getArticuloBean().getUnidadesCaja())));
				for (int i=0;i<celda.length;i++)
					tabla.addCell(celda[i]);
			}
			pedido.add(tabla);
			pedido.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		new EnviarCorreo(ped.getProveedore().getEmail(), archivo);
		
	}
}
