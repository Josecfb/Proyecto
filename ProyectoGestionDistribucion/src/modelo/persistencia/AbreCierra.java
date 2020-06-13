package modelo.persistencia;

import java.util.HashMap;
import java.util.Map;

//import java.util.HashMap;
//import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Abre y cierra la conexión con la base de datos
 * @author Jose Carlos
 *
 */
public class AbreCierra {
	private EntityManager em;
	
	/**
	 * Asigna los parámetros de conexión y crea la conexión
	 * @return em EntityManager 
	 *
	 */
	public EntityManager abrirConexion() {
		try {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put ("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/GestionDistribucion");
			properties.put ("javax.persistence.jdbc.user", "root");
			properties.put ("javax.persistence.jdbc.password", "$Mis2Perro");
			EntityManagerFactory factoria=Persistence.createEntityManagerFactory("ProyectoGestionDistribucion");
			em=factoria.createEntityManager();
			return em;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cierra la conexión
	 * @return true conexion cerrada false error al cerrar la conexión 
	 */
	public boolean cerrarConexion() {
		try {
			em.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
