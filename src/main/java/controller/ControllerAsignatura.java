package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;



public class ControllerAsignatura {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");
	
	/**
	 * 
	 * @return
	 */
	public static List<Asignatura> findAll() {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM asignatura;", Asignatura.class);
		List<Asignatura> l = (List<Asignatura>) q.getResultList();
		
		em.close();
		return l;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static Asignatura findByIdAsignatura (int IdAsignatura) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM asignatura where id = ?;", Asignatura.class);
		q.setParameter(1,IdAsignatura);
		List<Asignatura> l = (List<Asignatura>) q.getResultList();
		
		em.close();
		return l;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static List<Asignatura> findByNoIdDocente (int IdDocente) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select idAsignatura from asignaturaspordocente where idDocente != 1 group by idAsignatura;", Asignaturaspordocente.class);
		q.setParameter(1,IdDocente );
		List<Asignatura> l = (List<Asignatura>) q.getResultList();
		
		em.close();
		return l;
	}
	
	
	


}
