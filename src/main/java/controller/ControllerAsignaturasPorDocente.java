package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;
import model.Valoracionmateria;




public class ControllerAsignaturasPorDocente {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static List<Asignaturaspordocente> findByIdDocente (int IdDocente) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente where idDocente = ?;", Asignaturaspordocente.class);
		q.setParameter(1,IdDocente );
		List<Asignaturaspordocente> l = (List<Asignaturaspordocente>) q.getResultList();
		
		em.close();
		return l;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static List<Asignaturaspordocente> findByNoIdDocente (int IdDocente) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select idAsignatura from asignaturaspordocente where idDocente != 1 group by idAsignatura;", Asignaturaspordocente.class);
		q.setParameter(1,IdDocente );
		List<Asignaturaspordocente> l = (List<Asignaturaspordocente>) q.getResultList();
		
		em.close();
		return l;
	}
	
	
	/**
	 * 
	 * @param l
	 */
	public static void insertar (Asignaturaspordocente l) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
		em.close();
	}

}
