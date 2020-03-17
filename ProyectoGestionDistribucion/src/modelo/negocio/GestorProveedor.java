package modelo.negocio;
import java.util.List;
import model.Proveedor;
import modelo.persistencia.DaoProveedor;

public class GestorProveedor {
	
	public List<Proveedor> listar(String filtroNombre){
		DaoProveedor dp=new DaoProveedor();
		return dp.Listado(filtroNombre);
	}
	public Proveedor existe(int num) {
		DaoProveedor dp=new DaoProveedor();
		return dp.existe(num);
	}
	
	public boolean[] ModificarProveedor(Proveedor pro) {
		
		boolean[] ok=new boolean[4];
		for (int i=0;i<ok.length-1;i++)
			ok[i]=true;
		ok[3]=false;
		ok[0]=pro.getNombre().length()>0;
		ok[1]=pro.getNif().length()>0;
		ok[2]=pro.getEmail().length()>0;
		for (int i=0;i<3;i++)
			if (ok[i])
				System.out.println(i+" si");
		if (ok[0] && ok[1] && ok[2]) {
			DaoProveedor dp=new DaoProveedor();
			System.out.println("en gestor "+pro.getNombre());
			int modificado=dp.Modificar(pro);
			ok[3]=modificado==0;
		}
		return ok;
	}
	
}
