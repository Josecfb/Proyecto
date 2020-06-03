package modelo.negocio;
import java.util.List;

import entidades.AlbaranCliente;
import entidades.AlbaranProveedor;
import entidades.Articulo;
import entidades.Proveedor;
import modelo.persistencia.DaoArticulo;
/**
 * Gestor de Articulos
 * @author Jose Carlos
 *
 */
public class GestorArticulo {
	private DaoArticulo da;
	/**
	 * El constructor crea un objeto DaoArticulo
	 */
	public GestorArticulo() {
		da=new DaoArticulo();
	}
	
	/**
	 * Llama al método deUnProveedor de DaoArticulo
	 * @param pro Objeto entidad Proveedor
	 * @return List de objetos Articulo
	 */
	public List<Articulo> deUnProveedor(Proveedor pro){
		return da.deUnProveedor(pro);
	}
	/**
	 * Llama al método listado de DaoArticulo
	 * @param filtroNombre cadena para filtro
	 * @return List de objetos Articulo
	 */
	public List<Articulo> listar(String filtroNombre){
		return da.listado(filtroNombre);
	}
	/**
	 * Llama al método existe de DaoArticulo
	 * @param num número de artículo
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num) {
		return da.existe(num);
	}
	/**
	 * Llama al método existe de DaoArticulo
	 * @param num numero de artículo
	 * @param pro Objeto entidad Proveedor
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num,Proveedor pro) {
		return da.existe(num,pro);
	}
	/**
	 * Llama al método modificar de DaoArticulo
	 * @param art Articulo
	 * @return [0]=false, nombre vacío [1]=false, coste menor que cero [2]=false, precio mayorista menor que cero [3]=false,  precio minorista menor que cero [4]=false, sin proveedor [5]=true, modificado con éxito
	 */
	public boolean[] modificarArticulo(Articulo art) {
		boolean[] ok = valida(art);
		if (ok[0] && ok[1] && ok[2] && ok[3] && ok[4]) {
			int modificado=da.modificar(art);
			ok[5]=modificado==0;
		}
		return ok;
	}
	
	/**
	 * Llama al método nuevo de DaoArticulo
	 * @param art Articulo
	 * @return [0]=false nombre vacío [1]=false coste menor que cero [2]=false  precio mayorista menor que cero [3]=false  precio minorista menor que cero [4]=false  sin proveedor [5]=true modificado con éxito
	 */
	public boolean[] nuevoArticulo(Articulo art) {
		boolean[] ok = valida(art);
		if (ok[0] && ok[1] && ok[2] && ok[3] && ok[4]) {
			int creado=da.nuevo(art);
			ok[5]=creado==0;
		}
		return ok;
	}
	/**
	 * Comprueba errores en el artículo
	 * @param art Objeto Articulo
	 * @return array de boolean [0]=false->nombre vacío [1]=false coste menor que cero [2]=false precio mayorista menor que cero [3]=false precio minorista menor que cero [4]=false  sin proveedor [5]=true modificado con éxito
	 */
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
	/**
	 * Llama al método actualizaArticulosAlbaranProve de DaoArticulo
	 * @param albModif Objeto AlbaranProveedor
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranProve(AlbaranProveedor albModif,int masmenos) {
		da.actualizaArticulosAlbaranProve(albModif,masmenos);
	}
	/**
	 * Llama al método actualizaArticulosAlbaranCliente de DaoArticulo
	 * @param albModif Ojeto AlbaranCliente
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranCliente(AlbaranCliente albModif,int masmenos) {
		da.actualizaArticulosAlbaranCliente(albModif,masmenos);
	}
	/**
	 * Llama al método borrarArticulo de DaoArticulo
	 * @param art Objeto Articulo
	 * @return Cadena con mensaje de error
	 */
	public String borrarArticulo(Articulo art) {
		if (art!=null) {
			if (art.getFilasAlbaranClientes().size()>0) 
				return "No se puede borrar, está en albaranes de clientes";
			if (art.getFilasAlbaranProveedors().size()>0) 
				return "No se puede borrar, está en albaranes de proveedores";
			if (art.getFilasFacturasClientes().size()>0) 
				return "No se puede borrar, está en facturas de clientes";
			if (art.getFilasFacturasProveedors().size()>0) 
				return "No se puede borrar, está en facturas de proveedores";
			if (art.getFilasPedidosClientes().size()>0) 
				return "No se puede borrar, está en pedidos de clientes";
			if (art.getFilasPedidosProveedors().size()>0) 
				return "No se puede borrar, está en pedidos de proveedores";
			da.borrarArticulo(art);
			return "Artículo borrado";
		}
		return "No hay ningún artículo que borrar";
	}
}
