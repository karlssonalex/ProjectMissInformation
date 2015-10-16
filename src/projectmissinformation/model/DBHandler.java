package projectmissinformation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBHandler {
	
private static final String PERSISTENCE_UNIT_NAME = "ProjectMissInformation";
	
	private EntityManagerFactory factory;
	
	private List<User> userList;
	
	/***
	 * @author Charlotte
	 */
	public DBHandler() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		userList = new ArrayList<User>();
	}
	
	/***
	 * @author Charlotte
	 * @return
	 */
	public List<User> getUsers() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		userList = q.getResultList();
		em.close();
		
		return userList;
	}
	
	/***
	 * @author Charlotte
	 * @param user
	 */
	/*public void addUserToDB(User user) {
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
	}*/
	
	/***
	 * @author Charlotte
	 */
	public void createUser(String username, String password) {
		EntityManager em = factory.createEntityManager();
		User tempUser = new User();
		em.getTransaction().begin();
		
		tempUser.setName(username);
		tempUser.setPassword(password);
		tempUser.setAdmin(0);
		
		em.getTransaction().commit();	
		em.close();
		
		System.out.println(username + password);
		
		
	}
	
	/**
	 * @author Axel
	 * @param question The question asked by the user
	 * @param user The name of the user asking the question
	 */
	public void addQuestion(String question_, String user_) {
		Question question = new Question();
		question.setQuestion(question_);
		question.setAnswer(user_);
		
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		em.close();
	}

}
