package modelo.negocio;
import java.util.List;



import model.Articulo;
import modelo.persistencia.DaoArticulo;

public class GestorArticulo {
	
	public List<Articulo> listar(String filtroNombre){
		DaoArticulo da=new DaoArticulo();
		return da.Listado(filtroNombre);
	}
	public Articulo existe(int num) {
		DaoArticulo da=new DaoArticulo();
		return da.existe(num);
	}
	
	public boolean[] modificarArticulo(Articulo art) {
		
		boolean[] ok=new boolean[6];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[5]=false;
		ok[0]=art.getNombre().length()>0;
		ok[1]=art.getCoste()>0;
		ok[2]=art.getPrecioMayorista()>0;
		ok[3]=art.getPrecioMinorista()>0;
		ok[4]=art.getProveedorBean()==null;
		for (int i=0;i<6;i++)
			if (ok[i])
				System.out.println(i+" si");
		if (ok[0] && ok[1] && ok[2] && ok[3] && ok[4]) {
			DaoArticulo da=new DaoArticulo();
			System.out.println("en gestor "+art.getNombre());
			int modificado=da.modificar(art);
			ok[5]=modificado==0;
		}
		return ok;
	}
	
}
