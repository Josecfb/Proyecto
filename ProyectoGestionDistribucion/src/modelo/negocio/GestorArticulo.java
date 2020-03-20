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
	/**
	 * 
	 * @param art Articulo
	 * @return [0]=false->nombre vacío [1]=false->coste menor que cero [2]=false-> precio mayorista menor que cero [3]=false-> precio minorista menor que cero [4]=false-> sin proveedor [5]=true->modificado con éxito
	 */
	public boolean[] modificarArticulo(Articulo art) {
		boolean[] ok = valida(art);
		if (ok[0] && ok[1] && ok[2] && ok[3] && ok[4]) {
			DaoArticulo da=new DaoArticulo();
			int modificado=da.modificar(art);
			ok[5]=modificado==0;
		}
		return ok;
	}
	
	/**
	 * 
	 * @param art Articulo
	 * @return [0]=false->nombre vacío [1]=false->coste menor que cero [2]=false-> precio mayorista menor que cero [3]=false-> precio minorista menor que cero [4]=false-> sin proveedor [5]=true->modificado con éxito
	 */
	public boolean[] nuevoArticulo(Articulo art) {
		boolean[] ok = valida(art);
		if (ok[0] && ok[1] && ok[2] && ok[3] && ok[4]) {
			DaoArticulo da=new DaoArticulo();
			int creado=da.nuevo(art);
			ok[5]=creado==0;
		}
		return ok;
	}
	
	private boolean[] valida(Articulo art) {
		boolean[] ok=new boolean[6];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[5]=false;
		ok[0]=art.getNombre().length()>0;
		ok[1]=art.getCoste()>0;
		ok[2]=art.getPrecioMayorista()>0;
		ok[3]=art.getPrecioMinorista()>0;
		ok[4]=art.getProveedorBean()!=null;
		return ok;
	}
	
}
