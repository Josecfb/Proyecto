package vista.pdf;

import modelo.persistencia.DaoDatosEmpresa;
import vista.correo.EMailFacturaCliente;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entidades.Datosempresa;
import entidades.FacturasCliente;
import entidades.FilasFacturasCliente;

public class PdfFacturaCliente {
	NumberFormat formatoeuro;

	public PdfFacturaCliente(FacturasCliente fac) {
		formatoeuro = NumberFormat.getCurrencyInstance();
		Paragraph p;
		Font fuente=FontFactory.getFont("arial",12);
		TabSettings tab=new TabSettings(300f);

		Document factura=new Document();
		DaoDatosEmpresa de=new DaoDatosEmpresa();
		Datosempresa empresa=de.empresa();
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String archivo="src/facturasclientepdf/"+fac.getNum()+"_"+formatoFecha.format(fac.getFecha())+"_"+fac.getCliente().getNombre()+".pdf";
		try {
			FileOutputStream ficheroPdf = new FileOutputStream(archivo);
			PdfWriter.getInstance(factura,ficheroPdf).setInitialLeading(20);
			factura.open();
			
			p=new Paragraph();
			p.add(empresa.getNombre());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk(fac.getCliente().getNombre()+" "+fac.getCliente().getApellidos()));
			p.setFont(fuente);
			factura.add(p);
			
			p=new Paragraph();
			p.add(empresa.getDireccion());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk(fac.getCliente().getDireccion()));
			p.setFont(fuente);
			factura.add(p);
			
			p=new Paragraph();
			p.add(empresa.getCodpos()+" "+empresa.getPoblacion());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk(fac.getCliente().getCodPost()+" "+fac.getCliente().getPoblacion()));
			p.setFont(fuente);
			factura.add(p);
			
			p=new Paragraph();
			p.add(empresa.getProvincia());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk(fac.getCliente().getProvincia()));
			p.setFont(fuente);
			factura.add(p);
			
			p=new Paragraph();
			p.add("NIF: "+empresa.getNif());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk("NIF: "+fac.getCliente().getNifCif()));
			p.setFont(fuente);
			factura.add(p);
			
			p=new Paragraph();
			p.add("Teléfono: "+empresa.getFijo());
			p.setTabSettings(tab);
			p.setFont(fuente);
			factura.add(p);
			

			factura.add(new Paragraph(" ",FontFactory.getFont("arial",12)));
			
			p=new Paragraph();
			p.add("Factura num: "+fac.getNum());
			p.setTabSettings(tab);
			p.add(Chunk.TABBING);
			p.add(new Chunk("Fecha: "+formatoFecha.format(fac.getFecha())));
			p.setFont(fuente);
			factura.add(p);
			

			factura.add(new Paragraph(" ",FontFactory.getFont("arial",10)));
			PdfPTable tabla = new PdfPTable(5);
			float[] anchoCols = new float[5];
			anchoCols[0]=50f;
			anchoCols[1]=200f;
			anchoCols[2]=50f;
			anchoCols[3]=50f;
			anchoCols[4]=50f;
			tabla.setWidths(anchoCols);
			PdfPCell[] celda=new PdfPCell[5];
			for (int i=0;i<celda.length;i++)
				celda[i]=new PdfPCell();
			celda[0].setPhrase(new Phrase("Código"));
			celda[1].setPhrase(new Phrase("Nombre del Artículo"));
			celda[2].setPhrase(new Phrase("Cantidad"));
			celda[3].setPhrase(new Phrase("Precio"));
			celda[4].setPhrase(new Phrase("Total"));
			for (int i=0;i<celda.length;i++) {
				celda[i].setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				celda[i].setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				tabla.addCell(celda[i]);
			}
			List<FilasFacturasCliente> filas=new ArrayList<FilasFacturasCliente>();
			filas=fac.getFilasFacturasClientes();
			for (int i=0;i<celda.length;i++) 
				celda[i].setBackgroundColor(BaseColor.WHITE);
			celda[0].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[1].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celda[2].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[3].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[4].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			double base=0;
			for (FilasFacturasCliente fila:filas) {
				celda[0].setPhrase(new Phrase(String.valueOf(fila.getArticuloBean().getCodpro())));
				celda[1].setPhrase(new Phrase(fila.getArticuloBean().getNombre()));
				celda[2].setPhrase(new Phrase(String.valueOf(fila.getCantidad())));
				celda[3].setPhrase(new Phrase(formatoeuro.format(fila.getPrecio())));
				celda[4].setPhrase(new Phrase(formatoeuro.format(fila.getCantidad()*fila.getPrecio())));
				base+=fila.getCantidad()*fila.getPrecio();
				for (int i=0;i<celda.length;i++)
					tabla.addCell(celda[i]);
			}
			//celda[0]=new PdfPCell();
			
			
			celda[0].setColspan(2);
			celda[0].setBorderWidth(0f);
			celda[0].setPhrase(new Phrase(""));
			tabla.addCell(celda[0]);
			celda[1].setPhrase(new Phrase("BASE"));
			celda[1].setColspan(2);
			celda[1].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla.addCell(celda[1]);
			celda[4].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[4].setPhrase(new Phrase(formatoeuro.format(base)));
			tabla.addCell(celda[4]);
			
			tabla.addCell(celda[0]);
			celda[1].setPhrase(new Phrase("IVA"));
			celda[1].setColspan(2);
			celda[1].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla.addCell(celda[1]);
			celda[4].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[4].setPhrase(new Phrase(formatoeuro.format(base*0.1)));
			tabla.addCell(celda[4]);

			tabla.addCell(celda[0]);
			celda[1].setPhrase(new Phrase("Total Factura"));
			celda[1].setColspan(2);
			celda[1].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla.addCell(celda[1]);
			celda[4].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celda[4].setPhrase(new Phrase(formatoeuro.format(base*1.1)));
			tabla.addCell(celda[4]);
			
			factura.add(tabla);
			factura.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		new EMailFacturaCliente(fac, archivo);
	}
}
