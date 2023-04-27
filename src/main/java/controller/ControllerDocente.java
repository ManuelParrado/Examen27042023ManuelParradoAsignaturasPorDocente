package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Docente;



public class ControllerDocente {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static List<Docente> findByLikeNombreCompleto (String nombre) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM docente where nombreCompleto like ?;", Docente.class);
		q.setParameter(1, "%" + nombre + "%");
		List<Docente> l = (List<Docente>) q.getResultList();
		
		em.close();
		return l;
	}

}
