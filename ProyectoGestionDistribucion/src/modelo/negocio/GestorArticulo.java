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
	 * Llama al m�todo deUnProveedor de DaoArticulo
	 * @param pro Objeto entidad Proveedor
	 * @return List de objetos Articulo
	 */
	public List<Articulo> deUnProveedor(Proveedor pro){
		return da.deUnProveedor(pro);
	}
	/**
	 * Llama al m�todo listado de DaoArticulo
	 * @param filtroNombre cadena para filtro
	 * @return List de objetos Articulo
	 */
	public List<Articulo> listar(String filtroNombre){
		return da.listado(filtroNombre);
	}
	/**
	 * Llama al m�todo existe de DaoArticulo
	 * @param num n�mero de art�culo
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num) {
		return da.existe(num);
	}
	/**
	 * Llama al m�todo existe de DaoArticulo
	 * @param num numero de art�culo
	 * @param pro Objeto entidad Proveedor
	 * @return Objeto Articulo
	 */
	public Articulo existe(int num,Proveedor pro) {
		return da.existe(num,pro);
	}
	/**
	 * Llama al m�todo modificar de DaoArticulo
	 * @param art Articulo
	 * @return [0]=false, nombre vac�o [1]=false, coste menor que cero [2]=false, precio mayorista menor que cero [3]=false,  precio minorista menor que cero [4]=false, sin proveedor [5]=true, modificado con �xito
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
	 * Llama al m�todo nuevo de DaoArticulo
	 * @param art Articulo
	 * @return [0]=false nombre vac�o [1]=false coste menor que cero [2]=false  precio mayorista menor que cero [3]=false  precio minorista menor que cero [4]=false  sin proveedor [5]=true modificado con �xito
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
	 * Comprueba errores en el art�culo
	 * @param art Objeto Articulo
	 * @return array de boolean [0]=false->nombre vac�o [1]=false coste menor que cero [2]=false precio mayorista menor que cero [3]=false precio minorista menor que cero [4]=false  sin proveedor [5]=true modificado con �xito
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
	 * Llama al m�todo actualizaArticulosAlbaranProve de DaoArticulo
	 * @param albModif Objeto AlbaranProveedor
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranProve(AlbaranProveedor albModif,int masmenos) {
		da.actualizaArticulosAlbaranProve(albModif,masmenos);
	}
	/**
	 * Llama al m�todo actualizaArticulosAlbaranCliente de DaoArticulo
	 * @param albModif Ojeto AlbaranCliente
	 * @param masmenos 1 suma -1 resta
	 */
	public void actualizaArticulosAlbaranCliente(AlbaranCliente albModif,int masmenos) {
		da.actualizaArticulosAlbaranCliente(albModif,masmenos);
	}
	/**
	 * Llama al m�todo borrarArticulo de DaoArticulo
	 * @param art Objeto Articulo
	 * @return Cadena con mensaje de error
	 */
	public String borrarArticulo(Articulo art) {
		if (art!=null) {
			if (art.getFilasAlbaranClientes().size()>0) 
				return "No se puede borrar, est� en albaranes de clientes";
			if (art.getFilasAlbaranProveedors().size()>0) 
				return "No se puede borrar, est� en albaranes de proveedores";
			if (art.getFilasFacturasClientes().size()>0) 
				return "No se puede borrar, est� en facturas de clientes";
			if (art.getFilasFacturasProveedors().size()>0) 
				return "No se puede borrar, est� en facturas de proveedores";
			if (art.getFilasPedidosClientes().size()>0) 
				return "No se puede borrar, est� en pedidos de clientes";
			if (art.getFilasPedidosProveedors().size()>0) 
				return "No se puede borrar, est� en pedidos de proveedores";
			da.borrarArticulo(art);
			return "Art�culo borrado";
		}
		return "No hay ning�n art�culo que borrar";
	}
}
