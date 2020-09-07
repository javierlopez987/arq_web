package tudai.jpa.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tudai.jpa.model.Direccion;
import tudai.jpa.model.Persona;

public class Programa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = emf.createEntityManager();
		select(em);
		em.close();
		emf.close();
	}
	
	public static void insert(EntityManager em) {
		em.getTransaction().begin();
		Direccion d = new Direccion("Tandil", "Toncovich 574");
		em.persist(d);
		Persona j = new Persona(1, "Javier", 28, d);
		Persona i = new Persona(2, "Ines", 26, d);
		em.persist(j);
		em.persist(i);
		em.getTransaction().commit();
	}

	public static void select(EntityManager em) {
		em.getTransaction().begin();
		Persona j = em.find(Persona.class, 1);
		System.out.println(j);
		@SuppressWarnings("unchecked")
		List<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
		personas.forEach(p -> System.out.println(p));
		em.getTransaction().commit();
	}
}
