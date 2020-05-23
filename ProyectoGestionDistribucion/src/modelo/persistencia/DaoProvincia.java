package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.Provincia;

public class DaoProvincia {
	private EntityManager em;
	
	private void abrir() {
		AbreCierra ab=new AbreCierra();
		em=ab.abrirConexion();
	}
	
	public String nomProvincia(String codPost) {
		String codigo=codPost.substring(0, 2);
		abrir();
		String provincia=em.find(Provincia.class, codigo).getProvincia();
		em.close();
		return provincia;
	}
	@SuppressWarnings("unchecked")
	public List<String> nomPoblaciones(String codPost) {
		abrir();
		System.out.println(codPost);
		List<String> poblaciones=em.createQuery("select cod.poblacione.poblacion from Codigospostale cod where cod.id.codigoPostal=:codPost").setParameter("codPost", codPost).getResultList();
		for (String poblacion:poblaciones) {
			System.out.println(poblacion);
		}
		em.close();
		return poblaciones;
	}
}
